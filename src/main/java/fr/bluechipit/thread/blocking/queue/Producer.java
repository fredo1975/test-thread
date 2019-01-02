package fr.bluechipit.thread.blocking.queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	protected BlockingQueue<String> queue = null;
	private int limite=0;
	public Producer(BlockingQueue<String> queue,int limite) {
        this.queue = queue;
        this.limite=limite;
    }
	@Override
	public void run() {
		try {
			for(int i=0;i<limite;i++){
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
