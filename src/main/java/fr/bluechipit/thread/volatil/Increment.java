package fr.bluechipit.thread.volatil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Increment extends Thread {
	protected static final Logger logger = LoggerFactory.getLogger(Increment.class);
	public SharedObject sharedObject;
	public Increment(SharedObject sharedObject) {
		super();
		this.sharedObject = sharedObject;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sharedObject.increment();
		//logger.info("sharedField="+sharedObject.sharedField+" atomicSharedField="+sharedObject.atomicSharedField.get());
		
	}

	
}
