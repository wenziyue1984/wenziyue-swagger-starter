package com.wenziyue.swagger.config;

import lombok.Data;

/**
 * @author wenziyue
 */
@Data
public class SwaggerProperties {
    private String title = "API Docs";
    private String description = "Powered by wenziyue-swagger-starter";
    private String version = "1.0.0";
    /** 多包用逗号隔开，默认空——不限制，扫描全项目 */
    private String basePackage = "";
    private boolean enableJwt = true;
}
