package com.superchakra.train.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {
    @Bean
    public GroupedOpenApi UserApi()
    {
        return GroupedOpenApi.builder().group("media-user").pathsToMatch("/user/**").build();
    }
    @Bean
    public OpenAPI docsOpenApi()
    {
        return new OpenAPI()
                .info(new Info().title("train")
                        .description("后端接口测试文档")
                        .version("v1.0.0"));
    }
}
