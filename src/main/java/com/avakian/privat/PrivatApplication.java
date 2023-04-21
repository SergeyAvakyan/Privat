package com.avakian.privat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.avakian.privat.repository")
@SpringBootApplication
public class PrivatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivatApplication.class, args);
    }

}
