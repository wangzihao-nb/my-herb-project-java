package org.herb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("百草居 - 中医知识管理系统 API")
                        .version("1.0.0")
                        .description("百草居是一个综合性的中医知识管理系统，提供中药材、方剂、中成药、中医典籍等知识的查询和管理功能")
                        .contact(new Contact()
                                .name("百草居开发团队")
                                .email("support@baicaoju.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("开发环境"),
                        new Server().url("https://api.baicaoju.com").description("生产环境")
                ));
    }
}
