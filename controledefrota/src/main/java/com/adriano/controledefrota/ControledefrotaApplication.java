package com.adriano.controledefrota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ControledefrotaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControledefrotaApplication.class, args);
    }

}
