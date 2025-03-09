package fr.bluechipit.thread.exercice.printer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class BlockingNumberLetterProducer {
    private final BlockingQueue<String> queue;
    public BlockingNumberLetterProducer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void put(String number) {
        try {
            queue.put(number);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String take() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
