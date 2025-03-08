package fr.bluechipit.thread.exercice.printer;

public class NumberPrinter extends Thread{
    private Printer printer;

    public NumberPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.printNumbers();
    }
}
