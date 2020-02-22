package fr.bluechipit.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletableFuture<String> future1 = process(executor);
		CompletableFuture<String> future2 = process(executor);

		// Block and get the result of the Future
		String result1 = future1.get();
		String result2 = future2.get();
		System.out.println(result1);
		System.out.println(result2);
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			// System.out.println("waiting for finish ...");
		}
		System.out.println("Finished all threads");
	}

	private static CompletableFuture<String> process(ExecutorService executor) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		}, executor);
		return future;
	}

}
