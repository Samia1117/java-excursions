//import p

import logging.LogAggregator;
import threads.CounterTester;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // Log Aggregator test
//        try {
//            LogAggregator la = new LogAggregator();
//            la.aggregate(1);
//        } catch (IOException e) {
//            System.out.println("Exception = " + e.getMessage());
//        }

        // Threads tester
        try {
            CounterTester testerThread1 = new CounterTester();

            Thread t1 = new Thread(testerThread1, "t1");
            Thread t2 = new Thread(testerThread1, "t2");

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.printf("Final value of the counter is = %d\n", testerThread1.getCounter());
        } catch (InterruptedException e) {
            System.out.println(" Exception occurred " + e.getMessage());
        }
    }
}