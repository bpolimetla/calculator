package com.math.calculator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.math.calculator.dto.AboutDTO;
import com.math.calculator.util.Calculator;
import com.math.calculator.util.RateLimiter;

@RestController
@CrossOrigin
public class AboutController {

	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	private static final Logger LOGGER = LogManager.getLogger(AboutController.class.getName());
	private static RateLimiter aboutRateLimiter = new RateLimiter("AboutController", 30, 30);
	private static RateLimiter calculatorRateLimiter = new RateLimiter("Calculator", 30, 30);

	@GetMapping("/api/about")
	public AboutDTO aboutMath() {

		Date present = new Date();
		String temp = "Math Help from Spring Boot " + sdf1.format(present) + " Count: " + aboutRateLimiter.getCount();

		if (!aboutRateLimiter.isAllowed()) {
			temp = "Please try after some time.";
		}

		LOGGER.info("/api/about=>" + temp);
		AboutDTO a = new AboutDTO(temp);
		return a;
	}

	@GetMapping(value = "/api/calculator", produces = MediaType.APPLICATION_JSON_VALUE)
	public Calculator calculate(@RequestParam("a") int a, @RequestParam("b") int b,
			@RequestParam("operator") String operator) {
		Calculator calc = new Calculator("Please try after some time.");
		if (calculatorRateLimiter.isAllowed()) {
			calc = new Calculator(a, b, operator);
		}
		LOGGER.info("/api/calculator=>" + calc.toString());
		return calc;
	}

}
