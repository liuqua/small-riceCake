/**
 * Copyright (c) 2018-2028, sheng(shengshouquan@139.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.muyou.ricecake.constant;


import lombok.Getter;
import lombok.Setter;

/**
 * Nacos常量.
 *
 * @author sheng
 */
public class NacosConstant {

	/**
	 * 默认nacos 地址
	 */
	@Getter
	@Setter
	//static String NACOS_ADDR = "120.27.219.12:8848";
	static String NACOS_ADDR = "127.0.0.1:8848";
	// static String NACOS_ADDR = "192.168.1.79:8848";//测试环境
	 //static String NACOS_ADDR = "172.21.201.88:8848"; //蚌埠
	 //static String NACOS_ADDR = "172.21.201.82:8848"; //蚌埠-test
	//static String NACOS_ADDR = "10.31.1.64:8848"; //东莞

	/**
	 * nacos 配置前缀
	 */
	@Getter
	@Setter
	static String NACOS_CONFIG_PREFIX = "modules";

	/**
	 * nacos 组配置后缀
	 */
	String NACOS_GROUP_SUFFIX = "-group";

	/**
	 * nacos 配置文件类型
	 */
	@Getter
	@Setter
	static String NACOS_CONFIG_FORMAT = "yml";

	/**
	 * nacos json配置文件类型
	 */
	static String NACOS_CONFIG_JSON_FORMAT = "json";

	/**
	 * nacos 是否刷新
	 */
	static String NACOS_CONFIG_REFRESH = "true";

	/**
	 * nacos 分组
	 */
	static String NACOS_CONFIG_GROUP = "DEFAULT_GROUP";

	/**
	 * 默认名称空间
	 */
	@Getter
	@Setter
	static String NACOS_NAMESPACE = "public";

	/**
	 * 构建服务对应的 dataId
	 *
	 * @param appName 服务名
	 * @param profile 环境变量
	 * @return dataId
	 */
	static String dataId(String appName, String profile) {
		return dataId(appName, profile, NACOS_CONFIG_FORMAT);
	}

	/**
	 * 构建服务对应的 dataId
	 *
	 * @param appName 服务名
	 * @param profile 环境变量
	 * @param format  文件类型
	 * @return dataId
	 */
	static String dataId(String appName, String profile, String format) {
		return appName + "-" + profile + "." + format;
	}
	/**
	 * 构建服务对应的 dataId
	 *
	 * @param nacosConfig  文件类型
	 * @return void
	 */
	public static  void  setNacosConfig(String nacosConfig)
	{
		NACOS_CONFIG_PREFIX = nacosConfig;
	}

}
