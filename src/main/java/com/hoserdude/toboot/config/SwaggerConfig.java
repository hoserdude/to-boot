package com.hoserdude.toboot.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.openid.OpenIDAuthenticationToken;

@Configuration
@EnableSwagger
public class SwaggerConfig {

    /**
     * Tells Swagger what to care about.  In our case we don't need swagger to care
     * about injected parameters that Spring gives us, so we ignore them.
     * @param springSwaggerConfig
     * @return
     */
    @Bean
    public SwaggerSpringMvcPlugin corePlugin(SpringSwaggerConfig springSwaggerConfig){
        return new SwaggerSpringMvcPlugin(springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns("/api.*")
                .ignoredParameterTypes(Authentication.class, OpenIDAuthenticationToken.class)
                .swaggerGroup("core");
    }

    @Bean
    SwaggerSpringMvcPlugin managementPlugin(SpringSwaggerConfig springSwaggerConfig) {
        return new SwaggerSpringMvcPlugin(springSwaggerConfig)
                .includePatterns("/manage.*")
                .swaggerGroup("management");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Todo API",
                "APIs related to Getting Stuff Done",
                "terms",
                "todos@hoserdude.com",
                "",
                ""
        );
        return apiInfo;
    }
}
