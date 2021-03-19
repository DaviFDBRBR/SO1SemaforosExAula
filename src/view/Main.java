package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCompra;

public class Main {
	
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for (int id = 0; id < 300; id++) {
			ThreadCompra tc = new ThreadCompra(id, semaforo);
			tc.start();
		}
		
	}
	
}
