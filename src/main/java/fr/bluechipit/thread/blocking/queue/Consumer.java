package fr.bluechipit.thread.blocking.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	protected BlockingQueue<String> queue = null;

	private int limite=0;
    public Consumer(BlockingQueue<String> queue,int limite) {
        this.queue = queue;
        this.limite=limite;
    }
	@Override
	public void run() {
		try {
			for(int i=0;i<limite;i++){
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
