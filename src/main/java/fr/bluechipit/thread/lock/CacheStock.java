package fr.bluechipit.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("cacheStock")
@Scope("prototype")
public class CacheStock {
	protected static final Logger logger = LoggerFactory.getLogger(CacheStock.class);
	private Map<StockKey,StockKey> stockKeyMap;
	private Lock lock = new ReentrantLock();
	public Map<StockKey, StockKey> getStockKeyMap() {
		if(MapUtils.isEmpty(stockKeyMap)) {
			stockKeyMap = new HashMap<StockKey, StockKey>();
		}
		return stockKeyMap;
	}

	public void setStockKeyMap(Map<StockKey, StockKey> stockKeyMap) {
		this.stockKeyMap = stockKeyMap;
	}

	public CacheStock() {
		logger.info("Hello CacheStock !!");
	}

	public void printHello() {
		logger.info("Hello !");
	}

	public boolean checkStockKeyMap(StockKey stockKey) {
		boolean contains = false;
		if (getStockKeyMap().containsKey(stockKey)) {
			logger.debug("############## insertStock stockKeyMap contains " + stockKey.toString()+"  ###############");
			contains = true;
		} else {
			logger.debug(
					"------------ insertStock stockKeyMap doesn't contains " + stockKey.toString() + " we are going to insert it -------------");
			getStockKeyMap().put(stockKey, stockKey);
		}
		return contains;
	}
	public void insertStock(StockKey stockKey) {
		try {
			lock.lock();
			checkStockKeyMap(stockKey);
        } finally {
        	lock.unlock();
        }
		
	}
}
