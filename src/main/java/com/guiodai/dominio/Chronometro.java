package com.guiodai.dominio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chronometro extends Thread {

	public void run() {

		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		while (true) {
			
			
			System.out.println(sdf.format(new Date(System.currentTimeMillis())));
		}
	}

}
