package com.adriano.controledelogistica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ControledelogisticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControledelogisticaApplication.class, args);
    }

}
