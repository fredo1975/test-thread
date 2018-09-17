package fr.bluechipit.thread;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import fr.bluechipit.thread.cache.RateCache;
import fr.bluechipit.thread.cache.RateService;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@DirtiesContext
public class RateServiceTest extends AbstractJUnit4SpringContextTests {
	protected static final Logger logger = LoggerFactory.getLogger(RateCache.class);
	@Autowired
	RateService rateService;

	@Test
	public void testRefreshCache() {
		logger.debug("testRefreshCache start");
		Map<String, String> map = rateService.getRate();

	}
}
