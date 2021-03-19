package controller;

import java.util.concurrent.Semaphore;

public class ThreadCompra extends Thread {
	private boolean prossegue;
	private int id;
	static int ingressosDisponiveis = 100;
	private Semaphore semaforo;

	public ThreadCompra(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		login();

		if (prossegue) {
			processarCompra();
		}

		if (prossegue) {
			
			try {
				semaforo.acquire();
				validarCompra();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}

		}
		
	}

	private void login() {
		int tempo = (int) (Math.random() * 1951 + 50);

		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		verificar(tempo, 1000);

	}

	private void processarCompra() {
		int tempo = (int) (Math.random() * 2001 + 1000);

		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		verificar(tempo, 2500);

	}

	private void verificar(int tempo, int limite) {
		if (tempo > limite) {
			System.out.println("Thread #" + id + ", o login demorou demais ou "
					+ "o tempo se esgotou e não foi possível realiza a compra");
			prossegue = false;
		} else {
			prossegue = true;
		}
	}

	private void validarCompra() {
		int ingressosQtd = (int) (Math.random() * 4 + 1);

		if (ingressosQtd <= ingressosDisponiveis) {
			ingressosDisponiveis -= ingressosQtd;
			System.out.println("Thread #" + id + ", você adquiriu: " + ingressosQtd + " ingresso(s). "
					+ "Ainda estão disponíveis: " + ingressosDisponiveis + " ingresso(s).");
		} else {
			System.out.println("Thread #" + id + ", a quantidade de ingressos requisita (" + ingressosQtd + ") não está indisponível!");
		}
	}

}