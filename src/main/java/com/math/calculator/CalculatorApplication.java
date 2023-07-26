package com.math.calculator;

import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class CalculatorApplication {
	static final private Logger LOGGER = LogManager.getLogger(CalculatorApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

	@PostConstruct
	public void started() {
		LOGGER.info("============= Started with UTC ===============");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	@Bean
	public OpenAPI apiInfo() {
		
		Contact contact = new Contact();
	    contact.setEmail("user@gmail.com");
	    contact.setName("first name last name");
	    contact.setUrl("<https://website.com>");

	    Server localServer = new Server();
	    localServer.setUrl("<http://localhost:8000>");
	    localServer.setDescription("Server URL for Local development");

	    Server productionServer = new Server();
	    productionServer.setUrl("<https://prod.com>");
	    productionServer.setDescription("Server URL in Production");

	    License mitLicense = new License()
	    .name("MIT License")
	    .url("<https://choosealicense.com/licenses/mit/>");

	    Info info = new Info()
	    .title("Calculator API")
	    .contact(contact)
	    .version("1.0")
	    .description("This API exposes endpoints to do calculation.")
	    .license(mitLicense);
	    return new OpenAPI()
	            .info(info)
	            .servers(List.of(localServer, productionServer));
		
	}

}
