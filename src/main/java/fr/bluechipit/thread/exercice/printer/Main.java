package fr.bluechipit.thread.exercice.printer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        //ExecutorService executor = Executors.newFixedThreadPool(5);
        Printer printer = new Printer();
        LetterPrinter letterWorker = new LetterPrinter(printer);
        NumberPrinter numberWorker = new NumberPrinter(printer);
        letterWorker.start();
        numberWorker.start();
    }
}
