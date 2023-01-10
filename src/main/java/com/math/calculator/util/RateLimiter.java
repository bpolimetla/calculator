package com.math.calculator.util;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Bhavani P Polimetla
 * @since 12-14-2022
 */
public class RateLimiter {

	static final private Logger LOGGER = LogManager.getLogger(RateLimiter.class.getName());

	private String name = "";
	private int count = 1;
	private int duration = 60;
	private int maxCount = 5;
	private Date startTime = new Date();

	public RateLimiter(String nameGiven, int durationGiven, int maxCountGiven) {

		name = nameGiven;
		duration = durationGiven;
		maxCount = maxCountGiven;
		startTime = new Date();
		LOGGER.info("RateLimiter initilized:" + toString());
	}

	public boolean isAllowed() {
		boolean isAllowed = false;

		Date present = new Date();

		long seconds = (present.getTime() - startTime.getTime()) / 1000;
		LOGGER.info("RateLimiter:" + name + " seconds: " + seconds + " count: " + count);
		if (seconds <= duration && count > maxCount) {
			LOGGER.info("Please try after some time." + name);
			isAllowed = false;
		} else if (seconds > duration) {
			LOGGER.info("exceeded duration, resetting");
			startTime = present;
			count = 1;
			isAllowed = true;
		} else if (seconds <= duration && count <= maxCount) {
			LOGGER.info("with in limit");
			isAllowed = true;
		}
		count++;
		return isAllowed;
	}

	public static void main(String args[]) {
		RateLimiter rl = new RateLimiter("TestController", 60, 5);
		// LOGGER.info("Hello Java");
		for (int i = 1; i <= 10; i++) {
			LOGGER.info("RL=>" + rl.toString());
			if (rl.isAllowed())
				LOGGER.info("Alowed for " + i);
			else {
				LOGGER.info("Not Alowed for " + i);
				try {
					Thread.sleep(1000 * 30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public String toString() {
		return "RateLimiter [name=" + name + ", count=" + count + ", duration=" + duration + ", maxCount=" + maxCount
				+ ", startTime=" + startTime + "]";
	}

	public int getCount() {
		return count;
	}

}
