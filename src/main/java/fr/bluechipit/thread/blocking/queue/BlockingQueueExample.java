package fr.bluechipit.thread.blocking.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockingQueueExample {
	protected static final Logger logger = LoggerFactory.getLogger(BlockingQueueExample.class);
	public static void main(String[] args) throws InterruptedException {
		logger.info("BlockingQueueExample start");
		long startTime = System.currentTimeMillis();
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
		int limite=10;
        Producer producer = new Producer(queue,limite);
        Consumer consumer = new Consumer(queue,limite);

        Thread t1 = new Thread(producer);
        t1.start();
        Thread t2 = new Thread(consumer);
        t2.start();

        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis() - startTime;
		
		logger.info("BlockingQueueExample end in "+endTime);
	}

}
