package fr.bluechipit.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronzedListTest {
    static final List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> addIfAbsent(17));
        t.start();
        addIfAbsent(17);
        t.join();
        System.out.println(list);
    }

    private static void addIfAbsent(int x) {
        if (!list.contains(x)) {
            list.add(x);
        }
    }
}
