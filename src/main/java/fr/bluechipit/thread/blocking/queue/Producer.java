package fr.bluechipit.thread.blocking.queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	protected BlockingQueue<String> queue = null;
	public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }
	@Override
	public void run() {
		try {
			for(int i=0;i<1024;i++){
				System.out.println("producing : "+i);
				queue.put(String.valueOf(i));
				//Thread.sleep(1000);
	            //Thread.sleep(100);
			}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
