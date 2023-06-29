package fr.bluechipit.thread.callback;

public class SynchronousEventConsumer {
	private final EventListener eventListener;

    public SynchronousEventConsumer(EventListener eventListener) {
		super();
		this.eventListener = eventListener;
	}


	public String doSynchronousOperation(){
        System.out.println("Performing callback before synchronous Task");
        // any other custom operations
        return eventListener.onTrigger();
    }
}
