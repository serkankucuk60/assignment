package com.nurd.project.authgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nurd.project"})
@EntityScan(basePackages = {"com.nurd.project.authmanagement.entity"})
@EnableJpaRepositories(basePackages = {"com.nurd.project.authmanagement.repo"})
public class AuthGatewayApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH); //Postgres use default locale while querying columns, English used to avoid Turkish characters
        SpringApplication.run(AuthGatewayApplication.class, args);
    }



}
