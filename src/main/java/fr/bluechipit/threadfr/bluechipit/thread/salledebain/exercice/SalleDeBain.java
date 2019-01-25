package fr.bluechipit.threadfr.bluechipit.thread.salledebain.exercice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalleDeBain {
	protected static final Logger logger = LoggerFactory.getLogger(SalleDeBain.class);
	private static final int NB_MAX_PERSONNE_DANS_LA_SALLE_DE_BAIN = 3;
	private int nbPersonnesDansLaSalleDeBain = 0;
	private Genre genreEnCours = null;
	Map<Genre,String> map = new HashMap<>();

	public void entrer(Genre genre,String nom) throws InterruptedException {
		logger.info(nom+ " veut entrer - genre:"+genre.name());
		synchronized (this) {
			while(nbPersonnesDansLaSalleDeBain>=3) {
				wait();
			}
			nbPersonnesDansLaSalleDeBain++;
			logger.info(nom+ " est entrée - genre:"+genre.name()+" "+nbPersonnesDansLaSalleDeBain+" dans la salle de bain");
		}
	}

	public void sortir(Genre genre,String nom) {
		synchronized (this) {
			nbPersonnesDansLaSalleDeBain--;
			logger.info(nom+ " est sortie - genre:"+genre.name()+" "+nbPersonnesDansLaSalleDeBain+" dans la salle de bain");
			notifyAll();
		}
	}
}
