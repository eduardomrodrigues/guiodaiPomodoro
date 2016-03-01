package com.guiodai.dominio;

public class Cronometro extends Thread{

	private int segundos = 0;

	private int minutos = 0;

	private int horas = 0;

	private String timeFormatado;

	public String getTimeFormatado() {
		return timeFormatado;
	}

	public void run() {

		while (true) {
			this.rodarCronometro();
		}
	}

	public String showTime() {

		return this.getHorasFormatada() + ":" + this.getMinutosFormatado() + ":" + this.getSegundosFormatado();

	}

	private void rodarCronometro() {
		this.rodarSegundos();
		minutos++;
		if (minutos == 60) {
			horas++;
		}

	}

	private void rodarSegundos() {

		for (int i = 0; i < 60; i++) {
			try {
				Thread.sleep(999);
				this.segundos = i;
				this.timeFormatado = this.showTime();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public String getSegundosFormatado() {
		if (segundos < 10) {
			return "0" + segundos;
		}

		return "" + segundos;
	}

	public String getMinutosFormatado() {
		if (minutos < 10) {
			return "0" + minutos;
		}

		return "" + minutos;
	}

	public String getHorasFormatada() {
		if (horas < 10) {
			return "0" + horas;
		}
		return "" + horas;
	}

}
