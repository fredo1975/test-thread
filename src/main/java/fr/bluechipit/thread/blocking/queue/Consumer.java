package fr.bluechipit.thread.blocking.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	protected BlockingQueue<String> queue = null;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }
	@Override
	public void run() {
		try {
			for(int i=0;i<1024;i++){
				//Thread.sleep(1000);
				System.out.println("consuming : "+queue.take());
			}
			/*
			while(true){
				System.out.println("consuming : "+queue.take());
			}*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
