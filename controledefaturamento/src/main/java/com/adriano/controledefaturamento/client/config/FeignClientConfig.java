package com.adriano.controledefaturamento.client.config;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
        "com.adriano.controledefaturamento.client"
})
public class FeignClientConfig {
}
