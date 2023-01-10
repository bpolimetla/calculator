package com.math.calculator;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
