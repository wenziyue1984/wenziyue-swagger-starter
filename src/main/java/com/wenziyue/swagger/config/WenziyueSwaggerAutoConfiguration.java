package com.wenziyue.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author wenziyue
 */
@Slf4j
@Configuration
@ConditionalOnClass(OpenAPI.class)
public class WenziyueSwaggerAutoConfiguration {


    /** 绑定 swagger 配置 */
    @Bean
    @ConfigurationProperties(prefix = "wenziyue.swagger")
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    /** 只在指定的 activeProfiles 下注册 OpenAPI */
    @Bean
    @ConditionalOnMissingBean
    public OpenAPI baseOpenApi(SwaggerProperties props) {

        log.info("SwaggerProps: title={}, version={}, pkg={}",
                props.getTitle(), props.getVersion(), props.getBasePackage());

        Info info = new Info()
                .title(props.getTitle())
                .description(props.getDescription())
                .version(props.getVersion());

        OpenAPI openApi = new OpenAPI().info(info);

        if (props.isEnableJwt()) {
            openApi.components(new Components()
                            .addSecuritySchemes("BearerAuth",
                                    new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")))
                    .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
        }
        return openApi;
    }
}