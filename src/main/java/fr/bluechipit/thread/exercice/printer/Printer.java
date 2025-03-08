package fr.bluechipit.thread.exercice.printer;

import java.util.Arrays;
import java.util.List;

public class Printer {
    private final List<String> letters = Arrays.asList(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"});
    private final List<Integer> numbers = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26});
    private boolean isLetter = false;
    private int letterIndex=0;
    private int numberIndex=0;

    synchronized void printLetters() {
        while (isLetter) {

            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(letters.get(letterIndex++) + " ");
        isLetter = true;
        notify();
    }

    synchronized void printNumbers() {
        while (!isLetter) {

            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(numbers.get(numberIndex++) + " ");
        isLetter = false;
        notify();
    }
}