package fr.bluechipit.thread.executor;

public class MyRunnable implements Runnable {
	private final long countUntil;
	public long sum;
	MyRunnable(long countUntil) {
		this.countUntil = countUntil;
	}
	@Override
	public void run() {
		//long sum = 0;
		System.out.println("countUntil="+countUntil+" by thread"+Thread.currentThread().getName());
		for (long i = 1; i < countUntil; i++) {
			sum += i;
		}
		System.out.println("sum="+sum+" by thread"+Thread.currentThread().getName());
	}
	
}
