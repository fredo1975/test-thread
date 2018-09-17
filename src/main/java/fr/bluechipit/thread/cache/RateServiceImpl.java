package fr.bluechipit.thread.cache;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("rateService")
public class RateServiceImpl implements RateService {

	@Autowired
	private RateCache rateCache;

	@Override
	public Map<String, String> getRate() {
		// TODO Auto-generated method stub
		return rateCache.refreshRate();
	}

}
