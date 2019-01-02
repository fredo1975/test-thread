package fr.bluechipit.thread.concurrent.list;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestConcurrentModificationsOnList {
	final static int limit = 100;
	public static final int NB_THREADS =1000;
	static class MonThread1 extends Thread{
		private final List<Integer> list;
		public MonThread1(List<Integer> ll) {
			list = ll;
		}

		@Override
		public void run() {
			//System.out.println("MonThread1 "+this.getName()+" start");
			for(int i=0;i<limit;i++){
				list.add(i);
				if(i%2==0 && i!=0){
					list.remove(1);
				}
			}
			for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
				//System.out.println(this.getName()+" MonThread1 - it.next()="+it.next());
				it.next();
			}
			//System.out.println("MonThread1 "+this.getName()+" end");
		}
	}
	
	static class MonThreadSynchronized extends Thread{
		private final List<Integer> list;
		public MonThreadSynchronized(List<Integer> ll) {
			list = ll;
		}
		@Override
		public void run() {
			//System.out.println("MonThreadSynchronized "+this.getName()+" start");
			for(int i=0;i<limit;i++){
				list.add(i);
				if(i%2==0 && i!=0){
					list.remove(1);
				}
			}
			
			synchronized(list){
				for(Iterator<Integer> it = list.iterator();it.hasNext();){
					//System.out.println(this.getName()+" MonThreadSynchronized - it.next()="+it.next());
					it.next();
				}
			}
			//System.out.println("MonThreadSynchronized "+this.getName()+" end");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<Integer>());
		List<Integer> ll = new CopyOnWriteArrayList<Integer>();
		
		long start = System.currentTimeMillis();
		MonThread1[] threads = new MonThread1[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads[i] = new MonThread1(ll);
			threads[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads[i].join();
		}
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("CopyOnWriteArrayList - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		
		start = System.currentTimeMillis();
		MonThreadSynchronized[] threads2 = new MonThreadSynchronized[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads2[i] = new MonThreadSynchronized(synchronizedList);
			threads2[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads2[i].join();
		}
		/*
		System.out.println("thread1.isAlive()="+thread1.isAlive());
		System.out.println("thread2.isAlive()="+thread2.isAlive());
		System.out.println("thread3.isAlive()="+thread3.isAlive());
		System.out.println("thread4.isAlive()="+thread4.isAlive());*/
		end = System.currentTimeMillis();
		System.out.println("Collections.synchronizedList - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		/*for(Integer integer : ll){
			System.out.println("integer="+integer);
		}*/
	}

}
