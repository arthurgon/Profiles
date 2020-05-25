package com.telefonica.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.telefonica.api.entities.Empresa;
import com.telefonica.api.repositories.EmpresaRepository;
import com.telefonica.api.utils.PasswordUtils;

@SpringBootApplication
public class ConfigurationParametersApplication {

	//Configurado no Application.properties
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigurationParametersApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
					
		    //Gera senha criptografada (hashing)
			String senhaEncoded = PasswordUtils.gerarBCrypt("vivo360guides");
			System.out.println("Senha encoded: " + senhaEncoded);	
			//Gera a mesma senha criptografada (novo hashing)
			senhaEncoded = PasswordUtils.gerarBCrypt("vivo360guides");
			System.out.println("Senha encoded novamente: " + senhaEncoded);	
			//Valida o hashing
			System.out.println("Senha válida: " + PasswordUtils.senhaValida("vivo360guides", senhaEncoded));
		
			System.out.println("### Quantidade de elementos por página = " + this.qtdPorPagina);
			
			Empresa empresa = new Empresa();
			empresa.setRazaoSocial("GOMES SOLUÇÕES LTDA.");
			empresa.setCnpj("14292944000171");
			
			this.empresaRepository.save(empresa);
			System.out.println("Gravou");
			List<Empresa> empresas = empresaRepository.findAll();
			empresas.forEach(System.out::println);
			
			Optional<Empresa> empresaDb = empresaRepository.findById(7L);
			System.out.println("Empresa por ID: " + empresaDb);

			Empresa empresaCnpj = empresaRepository.findByCnpj("14292944000171");
			System.out.println("Empresa por CNPJ: " + empresaCnpj);
			
			//this.empresaRepository.deleteById(6L);
			
			empresas = empresaRepository.findAll();
			System.out.println("Empresas: " + empresas.size());
			
		};
	}
}
