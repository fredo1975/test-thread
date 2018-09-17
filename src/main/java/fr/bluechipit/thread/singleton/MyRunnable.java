package fr.bluechipit.thread.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnable implements Runnable {
	protected static final Logger logger = LoggerFactory.getLogger(MyRunnable.class);
	private SingletonShare instance;
	private Long name;
	
	public SingletonShare getInstance() {
		return instance;
	}

	public void setInstance(SingletonShare instance) {
		this.instance = instance;
	}

	public Long getName() {
		return name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public MyRunnable(SingletonShare instance) {
		super();
		this.instance = instance;
	}

	public MyRunnable(SingletonShare instance, Long name) {
		super();
		this.instance = instance;
		this.name = name;
	}

	@Override
	public void run() {
		//logger.info("start run");
		logger.info("run name="+name);
		String res = instance.process(name);
		logger.info("run res="+res);
		//logger.info("end run");
	}

}
