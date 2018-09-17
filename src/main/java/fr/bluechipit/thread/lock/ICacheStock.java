package fr.bluechipit.thread.lock;

import java.util.Set;

public interface ICacheStock {
	public Set<StockKey> getStockKeySet();
	public void setStockKeySet(Set<StockKey> stockKeySet);
}
