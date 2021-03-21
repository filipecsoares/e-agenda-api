package com.filipe.agenda.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket docket() {
		Docket docket = new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2);
		Parameter authorizationParam = new ParameterBuilder().name("Authorization").description("Header para token JWT")
				.modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		return docket.select().apis(RequestHandlerSelectors.basePackage("com.filipe.agenda"))
				.paths(PathSelectors.ant("/**")).build().globalOperationParameters(Arrays.asList(authorizationParam));
	}
}
