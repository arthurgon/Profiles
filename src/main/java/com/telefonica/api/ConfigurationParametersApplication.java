package com.telefonica.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.telefonica.api.utils.PasswordUtils;

@SpringBootApplication
public class ConfigurationParametersApplication {

	//Configurado no Application.properties
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigurationParametersApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
					
		    //Gera senha criptografada (hashing)
			String senhaEncoded = PasswordUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded: " + senhaEncoded);	
			//Gera a mesma senha criptografada (novo hashing)
			senhaEncoded = PasswordUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded novamente: " + senhaEncoded);	
			//Valida o hashing
			System.out.println("Senha válida: " + PasswordUtils.senhaValida("123456", senhaEncoded));
		
			System.out.println("### Quantidade de elementos por página = " + this.qtdPorPagina);	
		};
	}
}
