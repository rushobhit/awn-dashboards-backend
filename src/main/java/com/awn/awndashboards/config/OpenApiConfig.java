package com.awn.awndashboards.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AWN Dashboards API")
                        .description("""
                                Power BI Style Analytics Dashboard REST API.
                                Built on AdventureWorks PostgreSQL database (Neon).
                                Supports cross-filtering by date range, territory, and product category.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("AWN Dashboards")
                                .email("admin@awndashboards.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
