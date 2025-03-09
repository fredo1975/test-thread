package fr.bluechipit.thread.exercice.printer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final List<String> numbers = Arrays.asList(new String[]{"A","1", "B","2", "C","3", "D","4", "E","5", "F","6", "G","7", "H","8", "I","9", "J","10", "K","11", "L","12", "M","13", "N","14", "O","15", "P","16", "Q","17", "R","18", "S","19", "T","20", "U","21", "V","22", "W","23", "X","24", "Y","25", "Z","26"});
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        BlockingNumberLetterProducer blockingNumberLetterProducer = new BlockingNumberLetterProducer(queue);
        BlockingProducer blockingProducer = new BlockingProducer(blockingNumberLetterProducer,numbers);
        BlockingConsumer blockingNumberPrinter = new BlockingConsumer(blockingNumberLetterProducer,numbers.size());
        blockingNumberPrinter.start();
        blockingProducer.start();

        blockingProducer.join();
        blockingNumberPrinter.join();
        long end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" ms");
    }
}
