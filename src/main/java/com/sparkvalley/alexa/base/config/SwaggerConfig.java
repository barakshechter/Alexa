package com.sparkvalley.alexa.base.config;

import java.security.Principal;

import javax.servlet.ServletContext;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * see http://swagger.io/ for more info
 * This provides the /api-docs/ json metadata controller
 * The swagger-ui exists completely as static html/js in src/main/webapp
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {
    @Autowired protected SpringSwaggerConfig springSwaggerConfig;

    @Autowired ServletContext servletContext;

    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc framework - allowing for multiple
     * swagger groups i.e. same code base multiple swagger resource listings.
     * <p>
     * accessed by /api-docs (in other words, the default group)
     */
    @Bean
    public SwaggerSpringMvcPlugin publicApi() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .apiVersion("1")
                .includePatterns("/api/.*")
                .ignoredParameterTypes(Principal.class);
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                //title
                "Alexa",
                //description
                "A file archival tool with flexible, hierarchically tagging",
                //terms of service
                "http://www.gnu.org/licenses/gpl-3.0.en.html",
                //contact email
                "barak.shechter@gmail.com",
                //license type
                "All Rights Reserved",
                //license url
                "http://www.gnu.org/licenses/gpl-3.0.en.html"
        );
    }

}

