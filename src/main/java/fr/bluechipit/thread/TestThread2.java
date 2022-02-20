package fr.bluechipit.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestThread2 extends Thread{
	protected static final Logger logger = LoggerFactory.getLogger(TestThread2.class);
	Singleton1 s;
	private String text;
	ThreadLocal<String> tl = new ThreadLocal<>();
	public void run(){
		s = Singleton1.getInstance();
		tl.set(text);
	}
	public Singleton1 getInstance(){
		return s;
	}
	
	public TestThread2(String text) {
		super();
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public static void main(String[] args) throws InterruptedException {
		TestThread2 t1 = new TestThread2("t1");
		TestThread2 t2 = new TestThread2("t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		Singleton1 s1 = t1.getInstance();
		Singleton1 s2 = t2.getInstance();
		logger.info(s1.toString());
		logger.info(t1.getText());
		logger.info(s2.toString());
		logger.info(t2.getText());
		Singleton1 s3 = t2.getInstance();
		logger.info(s3.toString());
	}
}
