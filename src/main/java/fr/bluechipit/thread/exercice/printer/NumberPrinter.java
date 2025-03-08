package fr.bluechipit.thread.exercice.printer;

public class NumberPrinter extends Thread{
    private final Printer printer;

    public NumberPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for(int i=0;i<26;i++){
            printer.printNumbers();
        }
    }
}
