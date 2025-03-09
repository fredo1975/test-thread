package fr.bluechipit.thread.exercice.printer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Printer printer = new Printer();
        LetterPrinter letterWorker = new LetterPrinter(printer);
        NumberPrinter numberWorker = new NumberPrinter(printer);
        letterWorker.start();
        numberWorker.start();
        numberWorker.join();
        letterWorker.join();
        long end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" ms");
    }
}
