package com.courses.utils.helper;

import java.util.Random;

public class RandomUtils {
	public static String randomId() {
		Random rand = new Random(); // instance of random class
		// generate random values from 0-24
		int int_random = rand.nextInt(99999999);
		return Integer.toString(int_random);
	}
}