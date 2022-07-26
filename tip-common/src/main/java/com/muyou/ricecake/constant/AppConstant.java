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

/**
 * 系统常量
 *
 * @author sheng
 */
public interface AppConstant {

    /**
     * 应用版本
     */
    String APPLICATION_VERSION = "1.0.0";

    /**
     * 基础包
     */
    String BASE_PACKAGES = "com.muyou";

    /**
     * 应用名前缀
     */
    String APPLICATION_NAME_PREFIX = "tip-";

    /**
     * 网关模块名称
     */
    String APPLICATION_GATEWAY_NAME = APPLICATION_NAME_PREFIX + "gateway";
    /**
     * 授权模块名称
     */
    String APPLICATION_AUTH_NAME = APPLICATION_NAME_PREFIX + "auth";
    /**
     * 后台模块名称
     */
    String APPLICATION_ADMIN_NAME = APPLICATION_NAME_PREFIX + "admin";
    /**
     * 开发环境
     */
    String DEV_CODE = "dev";
    /**
     * 生产环境
     */
    String PROD_CODE = "prod";
    /**
     * 测试环境
     */
    String TEST_CODE = "test";

    /**
     * 代码部署于 linux 上，工作默认为 mac 和 Windows
     */
    String OS_NAME_LINUX = "LINUX";
}
