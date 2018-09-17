package fr.bluechipit.thread.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonShare {
	protected static final Logger logger = LoggerFactory.getLogger(SingletonShare.class);
	private static SingletonShare instance;
	private static final int NTHREDS = 100;
	private SingletonShare(){
		
	}
	
	public static SingletonShare getInstance(){
		if(null == instance){
			instance = new SingletonShare();
		}
		return instance;
	}
	
	public String process(long l){
		logger.info("start process l="+l);
		StringBuilder sb = new StringBuilder("SingletonShare_");
		sb.append(l);
		logger.info("end process sb="+sb.toString());
		return sb.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("start main");
		SingletonShare inst = getInstance();
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		for (long l = 0; l < NTHREDS; l++) {
			//logger.info("l="+l);
			//Long l = 100000000L;
			Runnable worker = new fr.bluechipit.thread.singleton.MyRunnable(inst, l);
			executor.execute(worker);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			//System.out.println("waiting for finish ...");
		}
		logger.info("end main");
	}

}
