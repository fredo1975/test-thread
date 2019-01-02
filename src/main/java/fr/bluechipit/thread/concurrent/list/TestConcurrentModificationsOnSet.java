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
	public static final int NB_THREADS = 10;
	final static int limit = 10000;
	final static NumberFormat formatter = new DecimalFormat("#0.00000");
	class MonThreadWithCopyOnWriteArraySet extends Thread{
		private final Set<Integer> set;
		public MonThreadWithCopyOnWriteArraySet(Set<Integer> set) {
			this.set = set;
		}

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			//System.out.println("MonThreadWithCopyOnWriteArraySet "+this.getName()+" start");
			for(int i=0;i<limit;i++){
				set.add(i);
				if(i%2==0 && i!=0){
					set.remove(1);
				}
			}
			for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
				//System.out.println(this.getName()+" MonThread1 - it.next()="+it.next());
				it.next();
			}
			long end = System.currentTimeMillis();
			System.out.println("MonThreadWithCopyOnWriteArraySet "+this.getName()+" end Execution time is "+ formatter.format((end - start) / 1000d) + " seconds");
		}
	}
	
	class MonThreadWithSynchronizedSet extends Thread{
		private final Set<Integer> set;
		public MonThreadWithSynchronizedSet(Set<Integer> set) {
			this.set = set;
		}
		@Override
		public void run() {
			long start = System.currentTimeMillis();
			//System.out.println("MonThreadWithSynchronizedSet "+this.getName()+" start");
			for(int i=0;i<limit;i++){
				set.add(i);
				if(i%2==0 && i!=0){
					set.remove(1);
				}
			}
			synchronized(set){
				for(Iterator<Integer> it = set.iterator();it.hasNext();){
					//System.out.println(this.getName()+" MonThreadSynchronized - it.next()="+it.next());
					it.next();
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("MonThreadWithSynchronizedSet "+this.getName()+" end Execution time is "+ formatter.format((end - start) / 1000d) + " seconds");
		}
	}
	class MonThreadConcurrent extends Thread{
		private final Set<Integer> set;
		public MonThreadConcurrent(Set<Integer> set) {
			this.set = set;
		}
		@Override
		public void run() {
			long start = System.currentTimeMillis();
			//System.out.println("MonThreadSynchronized "+this.getName()+" start");
			for(int i=0;i<limit;i++){
				set.add(i);
				if(i%2==0 && i!=0){
					set.remove(1);
				}
			}
			//synchronized(set){
				for(Iterator<Integer> it = set.iterator();it.hasNext();){
					//System.out.println(this.getName()+" MonThreadConcurrent - it.next()="+it.next());
					it.next();
				}
			//}
				long end = System.currentTimeMillis();
				System.out.println("MonThreadConcurrent "+this.getName()+" end Execution time is "+ formatter.format((end - start) / 1000d) + " seconds");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		TestConcurrentModificationsOnSet testConcurrentModificationsOnSet = new TestConcurrentModificationsOnSet();
		Set<Integer> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
		Set<Integer> set = new CopyOnWriteArraySet<>();
		Set<Integer> concurrentSet = new ConcurrentSkipListSet<>();
		
		/*******************************************************************************************************************************/
		long start = System.currentTimeMillis();
		MonThreadWithCopyOnWriteArraySet[] threads = new MonThreadWithCopyOnWriteArraySet[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads[i] = testConcurrentModificationsOnSet.new MonThreadWithCopyOnWriteArraySet(set);
			threads[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads[i].join();
		}
		System.out.println("MonThreadWithCopyOnWriteArraySet set.size()="+set.size());
		long end = System.currentTimeMillis();
		System.out.println("MonThreadWithCopyOnWriteArraySet - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		/*******************************************************************************************************************************/
		
		/*******************************************************************************************************************************/
		start = System.currentTimeMillis();
		MonThreadWithSynchronizedSet[] threads2 = new MonThreadWithSynchronizedSet[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads2[i] = testConcurrentModificationsOnSet.new MonThreadWithSynchronizedSet(synchronizedSet);
			threads2[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads2[i].join();
		}
		System.out.println("MonThreadWithSynchronizedSet synchronizedSet.size()="+synchronizedSet.size());
		end = System.currentTimeMillis();
		System.out.println("MonThreadWithSynchronizedSet - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		/*******************************************************************************************************************************/
		
		/*******************************************************************************************************************************/
		MonThreadConcurrent[] threads3 = new MonThreadConcurrent[NB_THREADS];
		for(int i=0;i<NB_THREADS;i++){
			threads3[i] = testConcurrentModificationsOnSet.new MonThreadConcurrent(concurrentSet);
			threads3[i].start();
		}
		for(int i=0;i<NB_THREADS;i++){
			threads3[i].join();
		}
		System.out.println("MonThreadConcurrent concurrentSet.size()="+concurrentSet.size());
		end = System.currentTimeMillis();
		System.out.println("MonThreadConcurrent - Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		/*******************************************************************************************************************************/
	}
}
