package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 extends WebMvcConfigurerAdapter {
	/**
	 * swagger
	 * 
	 * @return
	 */
	@Bean
	public Docket attributeManagementApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("demo").apiInfo(apiInfo()).select()
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("demo").description("demo Engineering.").version("v1")
				.license("LGPL")
				.licenseUrl("http://192.168.66.75/demo/LICENSE").build();
	}
	
}