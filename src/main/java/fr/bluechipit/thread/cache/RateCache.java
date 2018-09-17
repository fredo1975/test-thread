package fr.bluechipit.thread.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("rateCache")
public class RateCache {
	protected static final Logger logger = LoggerFactory.getLogger(RateCache.class);

	public Map<String, String> refreshRate() {
		logger.debug("refreshRate refreshRate start");
		Map<String, String> rateCache = new HashMap<String, String>();
		rateCache = Collections.synchronizedMap(rateCache);
		for (int i = 0; i < 2000; i++) {
			String in = String.valueOf(i);
			rateCache.put(in, UUID.randomUUID().toString());
		}
		logger.debug("refreshRate refreshRate end");
		return rateCache;
	}
}
