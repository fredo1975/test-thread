package fr.bluechipit.thread.callback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EventListenerTest {

	@Test
	void doSynchronousOperation() {
		EventListener listener = new SynchronousEventListenerImpl();
		SynchronousEventConsumer synchronousEventConsumer = new SynchronousEventConsumer(listener);
		String result = synchronousEventConsumer.doSynchronousOperation();

		assertNotNull(result);
		assertEquals("Synchronously running callback function", result);
	}
}
