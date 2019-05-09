package com.adriano.controledefaturamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ControledefaturamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControledefaturamentoApplication.class, args);
    }

}
