package com.adriano.controledelogistica.client.config;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
        "com.adriano.controledefrete.client"
})
public class FeignClientConfig {
}
