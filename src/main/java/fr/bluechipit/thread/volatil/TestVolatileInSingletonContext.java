package fr.bluechipit.thread.volatil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestVolatileInSingletonContext {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			Runnable worker = (()->{
				Singleton instance = Singleton.getInstance();
				System.out.println(instance);
			});
			executor.execute(worker);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			//System.out.println("waiting for finish ...");
		}
	}

}
