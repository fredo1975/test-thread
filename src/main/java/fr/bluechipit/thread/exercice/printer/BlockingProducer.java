package fr.bluechipit.thread.exercice.printer;

import java.util.Arrays;
import java.util.List;

public class BlockingProducer extends Thread{
    private final BlockingNumberLetterProducer blockingNumberLetterProducer;
    private final List<String> numbers;

    public BlockingProducer(BlockingNumberLetterProducer blockingNumberLetterProducer,List<String> numbers) {
        this.blockingNumberLetterProducer = blockingNumberLetterProducer;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for(int i=0;i<numbers.size();i++){
            blockingNumberLetterProducer.put(numbers.get(i));
        }
    }
}
