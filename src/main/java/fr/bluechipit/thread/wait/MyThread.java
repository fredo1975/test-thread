package fr.bluechipit.thread.wait;

public class MyThread implements Runnable {
	MyResource myResource;

	MyThread(String name, MyResource so) {
		myResource = so;
		new Thread(this, name).start();
	}

	@Override
	public void run() {
		try {
			myResource.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
