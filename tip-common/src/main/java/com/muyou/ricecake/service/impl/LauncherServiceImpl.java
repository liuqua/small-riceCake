/**
 * Copyright (c) 2018-2028, sheng(shengshouquan@139.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.muyou.ricecake.service.impl;


import com.muyou.ricecake.constant.LauncherConstant;
import com.muyou.ricecake.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

/**
 * 启动参数拓展
 *
 * @author smallchil
 */
public class LauncherServiceImpl implements LauncherService {

	@Override
	public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
		Properties props = System.getProperties();
		props.setProperty("spring.cloud.nacos.discovery.server-addr", LauncherConstant.nacosAddr(profile));
		props.setProperty("spring.cloud.nacos.config.server-addr", LauncherConstant.nacosAddr(profile));
		props.setProperty("spring.cloud.sentinel.transport.dashboard", LauncherConstant.sentinelAddr(profile));
		props.setProperty("spring.zipkin.base-url", LauncherConstant.zipkinAddr(profile));
	}

}
