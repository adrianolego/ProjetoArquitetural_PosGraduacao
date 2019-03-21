package com.adriano.controledefrete.client.config;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
        "com.adriano.controledefrete.client"
})
public class FeignClientConfig {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";


}
