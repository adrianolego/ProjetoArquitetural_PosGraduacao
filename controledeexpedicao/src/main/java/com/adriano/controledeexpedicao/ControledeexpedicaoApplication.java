package com.adriano.controledeexpedicao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ControledeexpedicaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControledeexpedicaoApplication.class, args);
    }

}
