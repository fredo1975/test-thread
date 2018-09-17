package fr.bluechipit.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoniteurProdCons {
	protected static final Logger logger = LoggerFactory.getLogger(MoniteurProdCons.class);

	String tampon;
	boolean estVide = true;

	synchronized void prod(String m) {
		//logger.info("prod start m="+m);
		while (!estVide) {
			logger.info("Producteur attend "+m);
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		logger.info("Produit : " + m);
		tampon = m;
		estVide = false;
		notify();
		//logger.info("prod end ");
	}

	synchronized void cons() {
		//logger.info("cons start ");
		while (estVide) {
			logger.info("Consommateur attend");
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		logger.info("Consomme : " + tampon);
		estVide = true;
		notify();
		//logger.info("cons end ");
	}
}
