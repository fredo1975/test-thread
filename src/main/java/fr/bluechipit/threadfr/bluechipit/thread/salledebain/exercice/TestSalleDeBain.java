package fr.bluechipit.threadfr.bluechipit.thread.salledebain.exercice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSalleDeBain implements Runnable{
	protected static final Logger logger = LoggerFactory.getLogger(TestSalleDeBain.class);
	private static final int NB_THREDS = 200;
	private static final int NB_PERSONNE_DANS_LA_SALLE_DEBAIN=3;
	
	public static void main(String[] args) {
		SalleDeBain sdb = new SalleDeBain();
		ExecutorService executor = Executors.newFixedThreadPool(NB_THREDS);
		StopWatch t = new StopWatch();
		t.start();
		for (int i = 0; i < NB_THREDS; i++) {
			Runnable workerM = new Masculin(sdb,RandomStringUtils.random(4,true,false));
			Runnable workerF = new Feminin(sdb,RandomStringUtils.random(4,false,true));
			executor.execute(workerM);
			executor.execute(workerF);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			//System.out.println("waiting for finish ...");
		}
		t.stop();
		logger.info("end main "+t.getTime()+" ms");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
