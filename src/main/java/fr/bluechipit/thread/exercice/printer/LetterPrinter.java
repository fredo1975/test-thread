package fr.bluechipit.thread.exercice.printer;

public class LetterPrinter extends Thread {
    private Printer printer;

    public LetterPrinter(Printer printer) {
        this.printer = printer;
    }


    @Override
    public void run() {
        printer.printLetters();
    }
}
