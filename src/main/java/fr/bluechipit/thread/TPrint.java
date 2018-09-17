package fr.bluechipit.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TPrint extends Thread {
	protected static final Logger logger = LoggerFactory.getLogger(TPrint.class);

	String txt;
	int attente;

	public TPrint(String t, int p) {
		txt = t;
		attente = p;
	}

	public void run() {
		String methodName = "run";
		logger.info(methodName+" start");
		for (int i = 0; i < 8; i++) {
			logger.info(txt + i + " ");
			try {
				sleep(attente);
				logger.info("sleep de "+attente);
			} catch (InterruptedException e) {};
		}
		logger.info(methodName+" end");
	}
}
