package fr.bluechipit.thread.lock.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.bluechipit.thread.lock.CacheStock;
import fr.bluechipit.thread.lock.StockKey;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CacheStockTest {
	protected static final Logger logger = LoggerFactory.getLogger(CacheStockTest.class);
	@Autowired
	CacheStock cacheStock;

	private static final StockKey stockKey = new StockKey("FR01223456","EUR","474");
	@Test
	public void testLock() {
		logger.debug("testLock start");
		cacheStock.printHello();
		cacheStock.insertStock(stockKey);
		logger.debug("testLock end");
		
	}
}
