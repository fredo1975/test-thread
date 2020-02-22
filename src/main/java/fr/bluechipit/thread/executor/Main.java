package fr.bluechipit.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static final int NTHREDS = 1000000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		for (int i = 0; i < 1000; i++) {
			System.out.println("i="+i+" --");
			Long l = 100000000L;
			Runnable worker = new MyRunnable(l + i);
			executor.execute(worker);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			//System.out.println("waiting for finish ...");
		}
		System.out.println("Finished all threads");
	}

}
