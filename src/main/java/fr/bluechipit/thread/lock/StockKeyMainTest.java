package fr.bluechipit.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class StockKeyMainTest {
	protected static final Logger logger = LoggerFactory.getLogger(StockKeyMainTest.class);

	private static final Integer NB_STOCK_KEY = 100;

	public class StockKeyRunnable implements Runnable{
		private CacheStock cacheStock;
		private StockKey stockKey;
		
		public StockKeyRunnable(CacheStock cacheStock,StockKey stockKey){
			this.cacheStock = cacheStock;
			this.stockKey=stockKey;
		}
		@Override
		public void run() {
			cacheStock.insertStock(stockKey);
		}
		public StockKey getStockKey() {
			return stockKey;
		}
		public void setStockKey(StockKey stockKey) {
			this.stockKey = stockKey;
		}
	}
	
	public static StockKey buildStockKey() {
		StringBuilder isin = new StringBuilder(RandomStringUtils.random(10, true, true));
		StringBuilder ccy = new StringBuilder(RandomStringUtils.random(3, true, true));
		StringBuilder bookRef = new StringBuilder(RandomStringUtils.random(3, true, true));
		return new StockKey(isin.toString(), ccy.toString(), bookRef.toString());
		
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CacheStock cacheStock = (CacheStock) context.getBean("cacheStock");
		StockKeyMainTest mainTest = new StockKeyMainTest();
		StockKey stockKey0 = new StockKey("FR01223456","EUR","474");
		StockKey stockKey1 = new StockKey("FR01223456","EUR","474");
		StockKey stockKey2 = new StockKey("FR01223456","EUR","474");
		StockKey stockKey3 = new StockKey("FR01223456","EUR","474");
		StockKey stockKey4 = new StockKey("FR6543210","EUR","474");
		StockKey stockKey5 = new StockKey("FR01223456","EUR","474");
		StockKey stockKey6 = new StockKey("FR01223456","EUR","474");
		StockKey stockKey7 = new StockKey("FR01223456","EUR","474");
		
		StockKey[] stockKeyTab = new StockKey[NB_STOCK_KEY];
		for(int i=0;i<NB_STOCK_KEY;i++) {
			if(i==0) {
				stockKeyTab[i] = new StockKey("IT01223456","EUR","474");
			}else if(i==NB_STOCK_KEY-1) {
				stockKeyTab[i] = new StockKey("GB01223456","EUR","474");
			}else {
				//stockKeyTab[i]=new StockKey("FR6543210","EUR","474");
				stockKeyTab[i]=stockKey1;
			}
		}
		StopWatch stopWatch = new StopWatch("start.....");
		stopWatch.start();
		ExecutorService executor = Executors.newFixedThreadPool(20);
		for (int i = 0; i < NB_STOCK_KEY; i++) {
			StockKeyRunnable worker = mainTest.new StockKeyRunnable(cacheStock,stockKeyTab[i]);
			executor.execute(worker);
		}
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			//logger.debug("waiting for finish ...");
		}
		stopWatch.stop();

		if(cacheStock.getStockKeyMap().containsKey(stockKey1)) {
			logger.debug("cacheStock.getStockKeyMap().containsKey(stockKey1)="+cacheStock.getStockKeyMap().containsKey(stockKey1));
		}
		logger.debug("KB: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024);
        
		logger.debug("fini ........................................");
		logger.debug("getStockKeyMap="+cacheStock.getStockKeyMap().toString());
	}

}
