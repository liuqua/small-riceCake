package com.muyou.ricecake.nacos;

import com.muyou.ricecake.constant.AppConstant;
import com.muyou.ricecake.constant.NacosConstant;
import com.muyou.ricecake.constant.SentinelConstant;
import com.muyou.ricecake.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RiceCakeNacosApplication {

    /**
     * Create an application context
     * java -jar app.jar --spring.profiles.active=prod --server.port=2333
     *
     * @param appName application name
     * @param source  The sources
     * @return an application context created from the current state
     */
    static HashMap fileList= new HashMap();
    public static ConfigurableApplicationContext run(String appName, Class source, String... args) {
        SpringApplicationBuilder builder = createSpringApplicationBuilder(appName, source, args);
        return builder.run(args);
    }

    public static SpringApplicationBuilder createSpringApplicationBuilder(String appName, Class source, String... args) {
        Assert.hasText(appName, "[appName]服务名不能为空");
        // 读取环境变量，使用spring boot的规则
        ConfigurableEnvironment environment = new StandardEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(new SimpleCommandLinePropertySource(args));
        propertySources.addLast(new MapPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, environment.getSystemProperties()));
        propertySources.addLast(new SystemEnvironmentPropertySource(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, environment.getSystemEnvironment()));
        // 获取配置的环境变量
        String[] activeProfiles = environment.getActiveProfiles();
        // 判断环境:dev、test、prod
        List<String> profiles = Arrays.asList(activeProfiles);
        // 预设的环境
        List<String> presetProfiles = new ArrayList<>(Arrays.asList(AppConstant.DEV_CODE, AppConstant.TEST_CODE, AppConstant.PROD_CODE));
        // 交集
        presetProfiles.retainAll(profiles);
        // 当前使用
        List<String> activeProfileList = new ArrayList<>(profiles);
        Function<Object[], String> joinFun = StringUtils::arrayToCommaDelimitedString;
        SpringApplicationBuilder builder = new SpringApplicationBuilder(source);
        String profile;
        if (activeProfileList.isEmpty()) {
            // 默认dev开发
            profile = AppConstant.DEV_CODE;
            activeProfileList.add(profile);
            builder.profiles(profile);
        } else if (activeProfileList.size() == 1) {
            profile = activeProfileList.get(0);
        } else {
            // 同时存在dev、test、prod环境时
            throw new RuntimeException("同时存在环境变量:[" + StringUtils.arrayToCommaDelimitedString(activeProfiles) + "]");
        }
        String startJarPath = RiceCakeNacosApplication.class.getResource("/").getPath().split("!")[0];
        String activePros = joinFun.apply(activeProfileList.toArray());
        System.out.println(String.format("----启动中，读取到的环境变量:[%s]，jar地址:[%s]----", activePros, startJarPath));

        Properties properties = new Properties();
        try {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")){
                String rootDirectory = System.getProperty("user.dir");//根目录路径
                fileFind(rootDirectory);//设置根目录下的所有文件列表名称/路径映射
                String  filePath = Optional.ofNullable(fileList.get("config.yml")).orElse("").toString();
                System.out.println(String.format("指定文件名称路径:%s",filePath));
                InputStream in = new FileInputStream(filePath);
                properties.load(in);
            } else {
                InputStream in = new FileInputStream("./config.yml");
                properties.load(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String configNacosAddr = properties.getProperty("ipport");
        String namespace = properties.getProperty("namespace");
        System.out.println("--nacosaddr:"+configNacosAddr);
        System.out.println("--namespace:" + namespace);

        Properties props = System.getProperties();
        props.setProperty("spring.application.name", appName);
        props.setProperty("spring.profiles.active", profile);
        props.setProperty("info.version", AppConstant.APPLICATION_VERSION);
        props.setProperty("info.desc", appName);
        props.setProperty("gykj.env", profile);
        props.setProperty("gykj.name", appName);
        props.setProperty("gykj.is-local", String.valueOf(isLocalDev()));
        props.setProperty("gykj.dev-mode", profile.equals(AppConstant.PROD_CODE) ? "false" : "true");
        props.setProperty("gykj.service.version", AppConstant.APPLICATION_VERSION);
        props.setProperty("spring.main.allow-bean-definition-overriding", "true");
        //nacos 地址
        props.setProperty("spring.cloud.nacos.discovery.server-addr", configNacosAddr==null? NacosConstant.getNACOS_ADDR():configNacosAddr);
        //nacos 地址
        props.setProperty("spring.cloud.nacos.config.server-addr", configNacosAddr==null?NacosConstant.getNACOS_ADDR():configNacosAddr);
        //nacos 配置前缀
        props.setProperty("spring.cloud.nacos.config.prefix", NacosConstant.getNACOS_CONFIG_PREFIX());
        //nacos 配置文件类型
        props.setProperty("spring.cloud.nacos.config.file-extension", NacosConstant.getNACOS_CONFIG_FORMAT());
        //sentinel 地址
        props.setProperty("spring.cloud.sentinel.transport.dashboard", SentinelConstant.SENTINEL_ADDR);
        props.setProperty("spring.cloud.nacos.discovery.namespace", namespace == null ? NacosConstant.getNACOS_NAMESPACE(): namespace);
        //nacos 组配置后缀
        //props.setProperty("spring.cloud.alibaba.seata.tx-service-group", appName.concat(NacosConstant.NACOS_GROUP_SUFFIX));
        // 加载自定义组件
        List<LauncherService> launcherList = new ArrayList<>();
        ServiceLoader.load(LauncherService.class).forEach(launcherList::add);
        launcherList.stream().sorted(Comparator.comparing(LauncherService::getOrder)).collect(Collectors.toList())
                .forEach(launcherService -> launcherService.launcher(builder, appName, profile));
        return builder;
    }

    //递归记录给定文件路径下的所有文件的名称\路径映射关系
    public static void fileFind(String path){
        File file=new File(path);
        File[] files = file.listFiles();
        //如果文件数组为null则返回
        if (files == null)
            return;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                //判断是不是文件夹，如果是文件夹则继续向下查找文件
                fileFind(files[i].getAbsolutePath());
            } else {
                //记录文件路径
                String filePath = files[i].getAbsolutePath().toLowerCase();
                //记录文件名
                String fileName=files[i].getName().toLowerCase();
                fileList.put(fileName, filePath);
            }
        }
    }

    /**
     * 判断是否为本地开发环境
     *
     * @return boolean
     */
    public static boolean isLocalDev() {
        String osName = System.getProperty("os.name");
        return StringUtils.hasText(osName) && !(AppConstant.OS_NAME_LINUX.equals(osName.toUpperCase()));
    }
}
