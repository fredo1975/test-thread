package fr.bluechipit.thread.concurrent.list;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class TestConcurrentSkipListSet {

	private static class MonThread extends Thread{
		private final Set<String> list;
		public MonThread(Set<String> ll) {
			list = ll;
		}
		@Override
		public void run() {
			//System.out.println("MonThread "+this.getName()+" start");
			/*
			Random r = new Random();
			int i = r.nextInt();
			String s = "toto"+new Integer(i).toString();
			System.out.println("s= "+s+" "+this.getName()+" start");*/
			if(this.getName().equalsIgnoreCase("Thread-0")){
				list.add("toto");
			}else if(this.getName().equalsIgnoreCase("Thread-1")){
				list.add("tata");
			}else if(this.getName().equalsIgnoreCase("Thread-2")){
				list.add("titi");
			}else if(this.getName().equalsIgnoreCase("Thread-3")){
				list.add("tutu");
			}
			
			//System.out.println("MonThread "+this.getName()+" end");
			
		}
	}
	public static void main(String[] args) throws InterruptedException  {
		Set<String> ll = new ConcurrentSkipListSet<String>();
		MonThread thread1 = new MonThread(ll);
		MonThread thread2 = new MonThread(ll);
		MonThread thread3 = new MonThread(ll);
		MonThread thread4 = new MonThread(ll);
		long start = System.currentTimeMillis();
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		/*System.out.println("thread1.isAlive()="+thread1.isAlive());
		System.out.println("thread2.isAlive()="+thread2.isAlive());
		System.out.println("thread3.isAlive()="+thread3.isAlive());
		System.out.println("thread4.isAlive()="+thread4.isAlive());*/
		for(String s : ll){
			System.out.println("s="+s);
		}
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
	}
}
