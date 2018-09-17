package fr.bluechipit.thread.volatil;

public class StoppableTask extends Thread {
	
	private boolean pleaseStop;
	public StoppableTask(boolean pleaseStop) {
		super();
		this.pleaseStop = pleaseStop;
	}
	@Override
	public void run() {
		System.out.println("run ...");
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		while (!pleaseStop) {
			System.out.println("not stopped and doing some stuff ........");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("end run");
	}
	public void tellMeToStop() {
		pleaseStop = true;
	}
}
