package fr.bluechipit.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Decrementer implements Runnable {
	CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }
	@Override
	public void run() {
		try {
			System.out.println("Decrementer run");
            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("Decrementer first countDown");
            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("Decrementer second countDown");
            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("Decrementer third countDown");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
