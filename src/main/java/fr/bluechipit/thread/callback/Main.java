package fr.bluechipit.thread.callback;

public class Main {

	public static void main(String[] args) {
		EventListener listener = new SynchronousEventListenerImpl();
		SynchronousEventConsumer synchronousEventConsumer = new SynchronousEventConsumer(listener);
		String result = synchronousEventConsumer.doSynchronousOperation();
		System.out.println(result);
	}

}
