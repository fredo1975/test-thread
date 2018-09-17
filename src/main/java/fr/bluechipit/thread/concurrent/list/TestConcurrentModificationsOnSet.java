package fr.bluechipit.thread.concurrent.list;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class TestConcurrentModificationsOnSet {
	public static final int NB_THREADS = 5;
	static class MonThread1 extends Thread{
		private final Set<Integer> set;
		public MonThread1(Set<Integer> set) {
			this.set = set;
		}

		@Override
		public void run() {
			//System.out.println("MonThread1 "+this.getName()+" start");
			for(int i=0;i<6;i++){
				set.add(i);
				if(i%2==0 && i!=0){
					set.remove(1);
				}
			}
			for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
				System.out.println(this.getName()+" MonThread1 - it.next()="+it.next());
				//it.next();
			}
			//System.out.println("MonThread1 "+this.getName()+" end");
		}
	}
	
	static class MonThreadSynchronized extends Thread{
		private final Set<Integer> set;
		public MonThreadSynchronized(Set<Integer> set) {
			this.set = set;
		}
		@Override
		public void run() {
			//System.out.println("MonThreadSynchronized "+this.getName()+" start");
			for(int i=0;i<6;i++){
				set.add(i);
				if(i%2==0 && i!=0){
					set.remove(1);
				}
			}
			synchronized(set){
				for(Iterator<Integer> it = set.iterator();it.hasNext();){
					System.out.println(this.getName()+" MonThreadSynchronized - it.next()="+it.next());
					
				}
			}
			//System.out.println("MonThreadSynchronized "+this.getName()+" end");
		}
	}
	static class MonThreadConcurrent extends Thread{
		private final Set<Integer> set;
		public MonThreadConcurrent(Set<Integer> set) {
			this.set = set;
		}
		@Override
		public void run() {
			//System.out.println("MonThreadSynchronized "+this.getName()+" start");
			for(int i=0;i<6;i++){
				set.add(i);
				if(i%2==0 && i!=0){
					set.remove(1);
				}
			}
			//synchronized(set){
				for(Iterator<Integer> it = set.iterator();it.hasNext();){
					System.out.println(this.getName()+" MonThreadConcurrent - it.next()="+it.next());
					
				}
			//}
			//System.out.println("MonThreadSynchronized "+this.getName()+" end");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Set<Integer> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
		Set<Integer> set = new CopyOnWriteArraySet<>();
		Set<Integer> concurrentSet = new ConcurrentSkipListSet<>();
		long start = System.currentTimeMillis();
		MonThread1[] threads = new MonThread1[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads[i] = new MonThread1(set);
			threads[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads[i].join();
		}
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		
		start = System.currentTimeMillis();
		MonThreadSynchronized[] threads2 = new MonThreadSynchronized[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads2[i] = new MonThreadSynchronized(synchronizedSet);
			threads2[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads2[i].join();
		}
		
		MonThreadConcurrent[] threads3 = new MonThreadConcurrent[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads3[i] = new MonThreadConcurrent(concurrentSet);
			threads3[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads3[i].join();
		}
	}

}
