package fr.bluechipit.thread.wait;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		MyResource sObj = new MyResource();
	    new MyThread("MyThread", sObj);
	    for (int i = 0; i < 100; i++) {
	      Thread.sleep(100);
	      System.out.print(".");
	    }
	    sObj.start();
	}

}
