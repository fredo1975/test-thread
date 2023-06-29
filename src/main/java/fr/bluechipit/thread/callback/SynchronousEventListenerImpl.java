package fr.bluechipit.thread.callback;

public class SynchronousEventListenerImpl implements EventListener{

	@Override
	public String onTrigger() {
		return "Synchronously running callback function";
	}

}
