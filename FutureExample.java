package org.example;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> futureVoid = executorService.submit(() -> {
            int count = 10;
            while (count > 0) {
                count--;
//                System.out.println("Current Thread Count reduce delay of 1 second start " + Thread.currentThread().getName());
                delay(1);
//                System.out.println("Current Thread delay end " + Thread.currentThread().getName());
            }
        });
        if (!futureVoid.isDone()){
            System.out.println("task is still running ");
        }
        try {
            Object obj = futureVoid.get(11, TimeUnit.SECONDS);
            System.out.println("Future Return "+obj);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main exit");
    }
    private static void delay(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}


