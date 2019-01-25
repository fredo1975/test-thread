package fr.bluechipit.threadfr.bluechipit.thread.salledebain.exercice;

public abstract class AbstractPersonne  {

	protected SalleDeBain sdb;
	protected String nom;
	public AbstractPersonne(SalleDeBain sdb,String nom) {
		super();
		this.sdb = sdb;
		this.nom=nom;
	}
}
