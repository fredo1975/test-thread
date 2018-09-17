package fr.bluechipit.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Consommateur extends Thread {
	protected static final Logger logger = LoggerFactory.getLogger(Consommateur.class);
	MoniteurProdCons tampon;

	public Consommateur(MoniteurProdCons t) {
		tampon = t;
	}

	@Override
	public void run() {
		logger.info("Consommateur run start");
		for(int i=0;i<10;i++){
			tampon.cons();
		}
		logger.info("Consommateur run end");
	}

}
