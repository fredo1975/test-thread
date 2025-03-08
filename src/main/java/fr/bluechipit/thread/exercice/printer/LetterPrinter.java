package fr.bluechipit.thread.exercice.printer;

public class LetterPrinter extends Thread {
    private final Printer printer;

    public LetterPrinter(Printer printer) {
        this.printer = printer;
    }


    @Override
    public void run() {
        for(int i=0;i<26;i++){
            printer.printLetters();
        }
    }
}
