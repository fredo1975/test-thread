package fr.bluechipit.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestThread1 {
	protected static final Logger logger = LoggerFactory.getLogger(TestThread1.class);

	public static void main(String[] args) {
		TPrint a = new TPrint("A", 100);
		TPrint b = new TPrint("B", 200);
		a.start();
		b.start();
	}

}
