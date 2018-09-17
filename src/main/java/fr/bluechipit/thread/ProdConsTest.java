package fr.bluechipit.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProdConsTest {
	protected static final Logger logger = LoggerFactory.getLogger(ProdConsTest.class);
	public static void main(String[] args) throws InterruptedException {
		logger.info("ProdConsTest start");
		long startTime = System.currentTimeMillis();
		MoniteurProdCons tampon = new MoniteurProdCons();
		Producteur prod = new Producteur(tampon);
		prod.start();
		Consommateur cons = new Consommateur(tampon);
		cons.start();
		
		prod.join();
		cons.join();
		long endTime = System.currentTimeMillis() - startTime;
		
		logger.info("ProdConsTest end in "+endTime);
	}

}
