package fr.bluechipit.thread.exercice.printer;

public class BlockingConsumer extends Thread{
    private final BlockingNumberLetterProducer blockingNumberLetterProducer;
    private int size;
    public BlockingConsumer(BlockingNumberLetterProducer blockingNumberLetterProducer,int size) {
        this.blockingNumberLetterProducer = blockingNumberLetterProducer;
        this.size = size;
    }
    @Override
    public void run() {
        for(int i=0;i<size;i++){
            System.out.println(blockingNumberLetterProducer.take()+" ");
        }
    }
}
