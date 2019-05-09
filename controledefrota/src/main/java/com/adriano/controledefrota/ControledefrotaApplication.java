package com.adriano.controledefrota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ControledefrotaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControledefrotaApplication.class, args);
    }

}
