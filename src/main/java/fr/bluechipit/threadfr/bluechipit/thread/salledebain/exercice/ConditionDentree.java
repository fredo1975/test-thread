package fr.bluechipit.threadfr.bluechipit.thread.salledebain.exercice;

public class ConditionDentree {
	private static final int NB_MAX_PERSONNE_DANS_LA_SALLE_DE_BAIN = 10;
	private int nbPersonnesDansLaSalleDeBain = 0;
	private Genre genreEnCours;
	public ConditionDentree(int nbPersonnesDansLaSalleDeBain, Genre genreEnCours) {
		super();
		this.nbPersonnesDansLaSalleDeBain = nbPersonnesDansLaSalleDeBain;
		this.genreEnCours = genreEnCours;
	}
	public int getNbPersonnesDansLaSalleDeBain() {
		return nbPersonnesDansLaSalleDeBain;
	}
	public void setNbPersonnesDansLaSalleDeBain(int nbPersonnesDansLaSalleDeBain) {
		this.nbPersonnesDansLaSalleDeBain = nbPersonnesDansLaSalleDeBain;
	}
	public Genre getGenreEnCours() {
		return genreEnCours;
	}
	public void setGenreEnCours(Genre genreEnCours) {
		this.genreEnCours = genreEnCours;
	}
	public boolean conditionGenre(Genre genre) {
		return genre != genreEnCours && genreEnCours != Genre.UNKWOWN;
	}
	public boolean conditionNbPersonnes() {
		return nbPersonnesDansLaSalleDeBain>=NB_MAX_PERSONNE_DANS_LA_SALLE_DE_BAIN;
	}
	public void entreeCondition(Genre genre) {
		nbPersonnesDansLaSalleDeBain++;
		genreEnCours = genre;
	}
	public void sortieCondition(Genre genre) {
		nbPersonnesDansLaSalleDeBain--;
		if(nbPersonnesDansLaSalleDeBain == 0) {
			genreEnCours = Genre.UNKWOWN;
		}
	}
}
