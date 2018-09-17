package fr.bluechipit.thread.cache;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	protected static final Logger logger = LoggerFactory.getLogger(MainTest.class);
	
	public class CacheRunnable implements Runnable{
		private RateService rateService;
		private Map<String, String> map;
		
		public CacheRunnable(RateService rateService){
			this.rateService = rateService;
		}
		@Override
		public void run() {
			map = rateService.getRate();
			//logger.debug("testRefreshCache map="+map.toString());
		}
		public Map<String, String> getMap() {
			return map;
		}
		public void setMap(Map<String, String> map) {
			this.map = map;
		}
		
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RateCache rateCache = (RateCache) context.getBean("rateCache");
		RateService rateService = (RateService) context.getBean("rateService");
		MainTest mainTest = new MainTest();
		ExecutorService executor = Executors.newFixedThreadPool(150);
		for (int i = 0; i < 5; i++) {
			CacheRunnable worker = mainTest.new CacheRunnable(rateService);
			executor.execute(worker);
			Map<String, String> map = worker.getMap();
		}
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			//logger.debug("waiting for finish ...");
		}
		//Map<String, String> map = rateService.getRate();
		
	}

}
