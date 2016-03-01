package com.guiodai.dominio;

import java.time.LocalTime;

import org.junit.Test;

public class CronometroTest {

	@Test
	public void testCronometro() {
		int millis = 2000;
		int seconds = millis / 1000; 
		LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds);
		String time = timeOfDay.toString();
		System.out.println(time);

	}

}
