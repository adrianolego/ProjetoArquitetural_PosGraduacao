package com.adriano.controledecoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ControledecoletaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControledecoletaApplication.class, args);
    }

}
