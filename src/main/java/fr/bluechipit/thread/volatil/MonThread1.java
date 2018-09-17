package fr.bluechipit.thread.volatil;


public class MonThread1 implements Runnable {

	@Override
	public void run() {
		Singleton instance = Singleton.getInstance();
		System.out.println(instance);
	}

}
