package fr.bluechipit.thread.volatil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private boolean pleaseStop;
	protected static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) throws InterruptedException {
		StoppableTask st = new StoppableTask(false);
		st.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		st.tellMeToStop();
		
		SharedObject sharedObject = new SharedObject();
		logger.info("sharedField="+sharedObject.getSharedField()+" AtomicSharedField="+sharedObject.getAtomicSharedField().get());
		sharedObject.increment();
		Increment incr1 = new Increment(sharedObject);
		incr1.start();
		Increment incr2 = new Increment(sharedObject);
		//Thread.sleep(1);
		//incr1.join();
		incr2.start();
		Increment incr3 = new Increment(sharedObject);
		//Thread.sleep(1);
		//incr2.join();
		incr3.start();
		//Thread.sleep(1);
		//incr3.join();
		//Thread.sleep(1000);
		sharedObject.increment();
		sharedObject.increment();
		logger.info("sharedField="+sharedObject.getSharedField()+" AtomicSharedField="+sharedObject.getAtomicSharedField().get());
		
	}
	
}
