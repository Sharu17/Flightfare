package com.fbs.flightfareservice;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import com.fbs.flightfareservice.services.EmailSenderService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class FlightFareServiceApplication {
	
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(FlightFareServiceApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {

		emailSenderService.sendEmailWithAttachment("sharunimbalkar17@gmail.com",
				"Thank you travelling with us."
				+ "Here are the payment details attached below",
				"Payment Successful!",
				"C:\\Users\\Shivani Nimbalkar\\Pictures\\1.jpg");

	}
	
	@Bean
	public Docket SwaggerConfiguration() {
		//return a prepared docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.fbs.flightfareservice"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
			"Flight Fare API",
			"Sample API",
			"1.0",
			"Free to use",
			new springfox.documentation.service.Contact("Sharwari", "http://javabrains.io", "xyz@gmail.com"),
			"API License",
			"http://javabrains.io",
			java.util.Collections.emptyList());
	}


}
