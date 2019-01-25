package fr.bluechipit.threadfr.bluechipit.thread.salledebain.exercice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Masculin extends AbstractPersonne implements Runnable{
	protected static final Logger logger = LoggerFactory.getLogger(Masculin.class);
	private Genre genre = Genre.MASCULIN;
	
	public Masculin(SalleDeBain sdb,String nom) {
		super(sdb,nom);
	}
	@Override
	public void run() {
		try {
			super.sdb.entrer(genre,nom);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.sdb.sortir(genre,nom);
	}
	
}
