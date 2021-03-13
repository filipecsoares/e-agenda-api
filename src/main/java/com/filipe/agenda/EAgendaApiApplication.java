package com.filipe.agenda;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EAgendaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAgendaApiApplication.class, args);
	}

	@Bean
	public springfox.documentation.spring.web.plugins.Docket docket() {
		Docket docket = new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2);
		return docket.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("E-Agenda REST API", "Lista de agendamentos para integração", "Versão API 1.0",
				"Termos de uso", new Contact("Filipe Soares", "", ""), "API License", "API License URL",
				new ArrayList<VendorExtension>());
		return apiInfo;
	}
}
