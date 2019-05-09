package com.adriano.controledefrete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ControledefreteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControledefreteApplication.class, args);
    }

}
