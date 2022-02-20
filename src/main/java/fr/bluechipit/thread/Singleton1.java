package fr.bluechipit.thread;

public class Singleton1 {

	private static Singleton1 instance=null;
	
	public synchronized static Singleton1 getInstance(){
		if(null == instance){
			instance = new Singleton1();
		}
		return instance;
	}
}
