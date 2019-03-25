package com.adriano.controledefaturamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class ControledefaturamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControledefaturamentoApplication.class, args);
	}

}
