package fr.bluechipit.thread.exercice.printer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //ExecutorService executor = Executors.newFixedThreadPool(5);
        Printer printer = new Printer();
        LetterPrinter letterWorker = new LetterPrinter(printer);
        NumberPrinter numberWorker = new NumberPrinter(printer);
        letterWorker.start();
        numberWorker.start();
        numberWorker.join();
        letterWorker.join();
    }
}
