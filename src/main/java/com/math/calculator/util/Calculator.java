package com.math.calculator.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calculator {

	private static final Logger LOGGER = LogManager.getLogger(Calculator.class.getName());

	private int a;
	private int b;
	private String operator;
	private int result;
	private String error = "";

	public Calculator(String errorGiven) {
		error = errorGiven;
	}

	public Calculator(int aGiven, int bGiven, String ops) {
		a = aGiven;
		b = bGiven;
		operator = ops;

		try {
			if ("p".equalsIgnoreCase(operator)) {
				result = aGiven + bGiven;
			} else if ("m".equalsIgnoreCase(operator)) {
				result = aGiven - bGiven;
			} else if ("x".equalsIgnoreCase(operator)) {
				result = aGiven * bGiven;
			} else if ("d".equalsIgnoreCase(operator)) {
				result = aGiven / bGiven;
			} else
				error = "Supported operators: +=p, -=m, /=d, x=x";
		} catch (Exception ex) {
			error = ex.getMessage();
		}
	}

	public static void main(String[] args) {
		Calculator c = new Calculator(1, 2, "p");
		LOGGER.info(c.toString());

		c = new Calculator(1, 0, "d");
		LOGGER.info(c.toString());

		c = new Calculator(166, 2, "xx");
		LOGGER.info(c.toString());

	}

	@Override
	public String toString() {
		return "Calculator [a=" + a + ", b=" + b + ", operator=" + operator + ", result=" + result + ", error=" + error
				+ "]";
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public String getOperator() {
		return operator;
	}

	public int getResult() {
		return result;
	}

}
