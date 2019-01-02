package fr.bluechipit.thread.concurrent.list;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.math.RandomUtils;

public class TestConcurrentModificationsOnList {
	final static int limit = 1000;
	public static final int NB_THREADS=10;
	class MonThreadWithCopyOnWriteArrayList extends Thread{
		private final List<Integer> list;
		public MonThreadWithCopyOnWriteArrayList(List<Integer> ll) {
			list = ll;
		}
		@Override
		public void run() {
			for(int i=0;i<limit;i++){
				list.add(RandomUtils.nextInt());
				/*
				if(i%2==0 && i!=0){
					list.remove(1);
				}*/
			}
			for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
				it.next();
			}
		}
	}
	class MonThreadWithCopyOnWriteArrayListAlreadyFullFilled extends Thread{
		private final List<Integer> list;
		public MonThreadWithCopyOnWriteArrayListAlreadyFullFilled(List<Integer> ll) {
			list = ll;
		}
		@Override
		public void run() {
			for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
				it.next();
			}
		}
	}
	class MonThreadWithSynchronizedList extends Thread{
		private final List<Integer> list;
		public MonThreadWithSynchronizedList(List<Integer> ll) {
			list = ll;
		}
		@Override
		public void run() {
			//System.out.println("MonThreadWithSynchronizedList "+this.getName()+" start");
			for(int i=0;i<limit;i++){
				list.add(RandomUtils.nextInt());
				/*
				if(i%2==0 && i!=0){
					list.remove(1);
				}*/
			}
			synchronized(list){
				for(Iterator<Integer> it = list.iterator();it.hasNext();){
					it.next();
				}
			}
			//System.out.println("MonThreadWithSynchronizedList "+this.getName()+" end");
		}
	}
	class MonThreadWithSynchronizedListAlreadyFullFilled extends Thread{
		private final List<Integer> list;
		public MonThreadWithSynchronizedListAlreadyFullFilled(List<Integer> ll) {
			list = ll;
		}
		@Override
		public void run() {
			//System.out.println("MonThreadWithSynchronizedList "+this.getName()+" start");
			synchronized(list){
				for(Iterator<Integer> it = list.iterator();it.hasNext();){
					it.next();
				}
			}
			//System.out.println("MonThreadWithSynchronizedList "+this.getName()+" end");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		TestConcurrentModificationsOnList testConcurrentModificationsOnList = new TestConcurrentModificationsOnList();
		List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<Integer>());
		//List<Integer> synchronizedList = new ArrayList<Integer>();
		List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<Integer>();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		/*******************************************************************************************************************************/
		long start = System.currentTimeMillis();
		MonThreadWithCopyOnWriteArrayList[] threads = new MonThreadWithCopyOnWriteArrayList[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads[i] = testConcurrentModificationsOnList.new MonThreadWithCopyOnWriteArrayList(copyOnWriteArrayList);
			threads[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads[i].join();
		}
		long end = System.currentTimeMillis();
		System.out.println("MonThreadWithCopyOnWriteArrayList - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		/*******************************************************************************************************************************/
		
		/*******************************************************************************************************************************/
		start = System.currentTimeMillis();
		MonThreadWithSynchronizedList[] threads2 = new MonThreadWithSynchronizedList[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads2[i] = testConcurrentModificationsOnList.new MonThreadWithSynchronizedList(synchronizedList);
			threads2[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads2[i].join();
		}
		end = System.currentTimeMillis();
		System.out.println("MonThreadWithSynchronizedList - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		/*******************************************************************************************************************************/
		
		/*******************************************************************************************************************************/
		copyOnWriteArrayList.clear();
		for(int i=0;i<limit;i++){
			copyOnWriteArrayList.add(RandomUtils.nextInt());
		}
		start = System.currentTimeMillis();
		MonThreadWithCopyOnWriteArrayListAlreadyFullFilled[] monThreadWithCopyOnWriteArrayListAlreadyFullFilled = new MonThreadWithCopyOnWriteArrayListAlreadyFullFilled[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			monThreadWithCopyOnWriteArrayListAlreadyFullFilled[i] = testConcurrentModificationsOnList.new MonThreadWithCopyOnWriteArrayListAlreadyFullFilled(copyOnWriteArrayList);
			monThreadWithCopyOnWriteArrayListAlreadyFullFilled[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			monThreadWithCopyOnWriteArrayListAlreadyFullFilled[i].join();
		}
		end = System.currentTimeMillis();
		
		System.out.println("MonThreadWithCopyOnWriteArrayListAlreadyFullFilled - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		/*******************************************************************************************************************************/
		
		
		/*******************************************************************************************************************************/
		synchronizedList.clear();
		for(int i=0;i<limit;i++){
			synchronizedList.add(RandomUtils.nextInt());
		}
		start = System.currentTimeMillis();
		MonThreadWithSynchronizedListAlreadyFullFilled[] monThreadWithSynchronizedListAlreadyFullFilled = new MonThreadWithSynchronizedListAlreadyFullFilled[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			monThreadWithSynchronizedListAlreadyFullFilled[i] = testConcurrentModificationsOnList.new MonThreadWithSynchronizedListAlreadyFullFilled(synchronizedList);
			monThreadWithSynchronizedListAlreadyFullFilled[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			monThreadWithSynchronizedListAlreadyFullFilled[i].join();
		}
		end = System.currentTimeMillis();
		System.out.println("MonThreadWithSynchronizedListAlreadyFullFilled - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		
	}
}
