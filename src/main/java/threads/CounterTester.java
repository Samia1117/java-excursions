package threads;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterTester extends Thread {

    private final AtomicInteger counter = new AtomicInteger();

    public CounterTester() {
        counter.set(0);
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i ++) {
                Thread.sleep(i * 100);
                counter.getAndIncrement();
            }
        } catch (InterruptedException e) {
            System.out.printf("Thread %s was interrupted with exception %s\n", Thread.currentThread().getName(), e.getMessage());
        }
    }

    public int getCounter() {
        return counter.get();
    }
}
