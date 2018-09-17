package fr.bluechipit.thread;

public class Compte extends Thread {
	int valeur;
	Compte(int val) {
		valeur = val;
	}
	public void run() {
		try {
			for (;;) {
				System.out.print(valeur + "\n");
				sleep(100);
			}
		} catch (InterruptedException e) {
			return;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Compte(1).start();
		new Compte(2000).start();
	}

}
