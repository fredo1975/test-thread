package fr.bluechipit.thread.nonblocking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

	private AtomicInteger value = new AtomicInteger();
    public int getValue(){
        return value.get();
    }
    public int increment(){
        return value.incrementAndGet();
    }

    // Alternative implementation as increment but just make the
    // implementation explicit
    public int incrementLongVersion(){
        int oldValue = value.get();
        while (!value.compareAndSet(oldValue, oldValue+1)){
             oldValue = value.get();
        }
        return oldValue+1;
    }
    
    public static void main(String[] args) {
    	final int NTHREDS = 10;

    	Counter counter = new Counter();
    	List<Future<Integer>> list = new ArrayList<Future<Integer>>();
        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
        for (int i = 0; i < 500; i++) {
        	Callable<Integer> worker = (() -> {
        		int number = counter.increment();
                System.out.println(number );
                return number ;
        	});
        	/*
            Callable<Integer> worker = new  Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int number = counter.increment();
                    System.out.println(number );
                    return number ;
                }
            };*/
            Future<Integer> submit = executor.submit(worker);
            list.add(submit);

        }

        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
        // Wait until all threads are finish
        while (!executor.isTerminated()) {
        }
        Set<Integer> set = new HashSet<Integer>();
        for (Future<Integer> future : list) {
            try {
            	//System.out.println(future.get() );
                set.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (list.size()!=set.size()){
            throw new RuntimeException("Double-entries!!!");
        }
    }
}
