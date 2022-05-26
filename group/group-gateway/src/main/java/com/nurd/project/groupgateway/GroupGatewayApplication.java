package com.nurd.project.groupgateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration(
        exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableMongoRepositories(basePackages = {"com.nurd.project.groupmanagement.db.repo"})
@ComponentScan(basePackages = {"com.nurd.project"})
@OpenAPIDefinition(info = @Info(title = "Group API", version = "1.0", description = "Group management"))

public class GroupGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupGatewayApplication.class, args);
    }

}
