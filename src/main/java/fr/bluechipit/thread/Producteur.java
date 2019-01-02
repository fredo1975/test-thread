package fr.bluechipit.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producteur extends Thread {
	protected static final Logger logger = LoggerFactory.getLogger(Producteur.class);
	MoniteurProdCons tampon;

	public Producteur(MoniteurProdCons t) {
		tampon = t;
	}

	@Override
	public void run() {
		logger.info("Producteur run start");
		for(int i=0;i<10;i++){
			tampon.prod("message"+i);
		}
		logger.info("Producteur run end");
	}

}
