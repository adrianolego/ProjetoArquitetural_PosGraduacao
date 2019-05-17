package com.adriano.controledeexpedicao.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableEurekaClient
@Profile({"default", "docker-compose"})
public class DiscoveryClientConfig {

}