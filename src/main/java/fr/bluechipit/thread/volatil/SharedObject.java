package fr.bluechipit.thread.volatil;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedObject {
	protected static final Logger logger = LoggerFactory.getLogger(SharedObject.class);
	int sharedField = 0;
	AtomicInteger atomicSharedField = new AtomicInteger(0);
	public void increment(){
		sharedField++;
		atomicSharedField.getAndIncrement();
		logger.info("sharedField="+sharedField+" atomicSharedField="+atomicSharedField.get());
	}
	public int getSharedField(){
		return sharedField;
	}
	public AtomicInteger getAtomicSharedField() {
		return atomicSharedField;
	}
	
}
