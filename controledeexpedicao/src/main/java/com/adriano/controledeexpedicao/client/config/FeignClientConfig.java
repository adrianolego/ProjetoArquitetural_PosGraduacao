package com.adriano.controledeexpedicao.client.config;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
        "com.adriano.controledeexpedicao.client"
})
public class FeignClientConfig {
}
