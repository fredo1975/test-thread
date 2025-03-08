package fr.bluechipit.thread.exercice.printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Printer {
    private List<String> letters = Arrays.asList(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"});
    private List<Integer> numbers = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
    private boolean isLetter = true;
    private int letterIndex=0;
    private int numberIndex=0;
    public void printLetters() {
        synchronized (this) {
            while (isLetter) {
                System.out.println(letters.get(letterIndex++) + " ");
                isLetter = false;
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            notify();
            isLetter = true;
        }
    }

    public void printNumbers() {
        synchronized (this) {
            while (!isLetter) {
                System.out.println(numbers.get(numberIndex++) + " ");
                isLetter = true;
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            notify();
            isLetter = false;
        }
    }
}