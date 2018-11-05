package com.ecorp.foundation.fraudcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * Application gateway
 *
 * @author Gustavo Adolfo Arciniegas Plazas
 * @version 1.0
 * @since 2018-11-04
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@EnableSwagger2
@SpringBootApplication(scanBasePackages = { "com.ecorp.foundation.fraudcalculator.usecase",
		"com.ecorp.foundation.fraudcalculator.apis.rest", "com.ecorp.foundation.fraudcalculator.crosscutting.handlers" })
public class Application {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
