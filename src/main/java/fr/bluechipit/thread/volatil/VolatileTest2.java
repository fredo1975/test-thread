package fr.bluechipit.thread.volatil;

import java.util.concurrent.CountDownLatch;

public class VolatileTest2 {

	class LoopMayNeverEnd{
		boolean stop = false;
		public void doSomeWorkUntilStop(){
			System.out.println("doSomeWorkUntilStop by thread"+Thread.currentThread().getName());
			while(!stop){
				
			}
		}
		public void stop(){
			System.out.println("stop ...");
			stop = true;
		}
	}
	class MyRunnable implements Runnable{
		CountDownLatch latch = null;
		public VolatileTest2.LoopMayNeverEnd loopMayNeverEnd;
		public MyRunnable(VolatileTest2.LoopMayNeverEnd _loopMayNeverEnd,CountDownLatch latch){
			this.loopMayNeverEnd = _loopMayNeverEnd;
			this.latch = latch;
		}
		@Override
		public void run() {
			System.out.println("run ..."+Thread.currentThread().getName());
			loopMayNeverEnd.doSomeWorkUntilStop();
			
		}
		public void stop(){
			/*try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			loopMayNeverEnd.stop();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(3);
		VolatileTest2 v = new VolatileTest2();
		final VolatileTest2.LoopMayNeverEnd loopMayNeverEnd = v.new LoopMayNeverEnd();
		/*
		Runnable runnable1 = new Runnable(){
			@Override
			public void run() {
				loopMayNeverEnd.doSomeWorkUntilStop();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void stop(){
				loopMayNeverEnd.stop();
			}
		};
		Thread t1 = new Thread(runnable1);
		t1.start();*/
		
		MyRunnable r1 = v.new MyRunnable(loopMayNeverEnd,latch);
		MyRunnable r2 = v.new MyRunnable(loopMayNeverEnd,latch);
		Thread t1 = new Thread(r1);
		t1.start();
		Thread t2 = new Thread(r2);
		t2.start();
		r2.stop();
		/*
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
		System.out.println("countDown 1");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
		System.out.println("countDown 2");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
		System.out.println("countDown 3");*/
	}

}
