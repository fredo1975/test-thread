package fr.bluechipit.threadfr.bluechipit.thread.salledebain.exercice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalleDeBain {
	protected static final Logger logger = LoggerFactory.getLogger(SalleDeBain.class);
	private ConditionDentree cd;
	public SalleDeBain() {
		super();
		cd = new ConditionDentree(0,Genre.UNKWOWN);
	}

	public void entrer(Genre genre,String nom) throws InterruptedException {
		logger.info(nom+ " veut entrer - genre:"+genre.name());
		synchronized (this) {
			while(cd.conditionNbPersonnes() || cd.conditionGenre(genre)) {
				wait();
			}
			cd.entreeCondition(genre);
			logger.info(nom+ " est entrée - genre:"+genre.name()+" "+cd.getNbPersonnesDansLaSalleDeBain()+" dans la salle de bain");
		}
	}

	public void sortir(Genre genre,String nom) {
		synchronized (this) {
			cd.sortieCondition(genre);
			logger.info(nom+ " est sortie - genre:"+genre.name()+" "+cd.getNbPersonnesDansLaSalleDeBain()+" dans la salle de bain");
			notifyAll();
		}
	}
}
