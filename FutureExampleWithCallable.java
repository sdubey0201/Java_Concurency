package org.example;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureExampleWithCallable {
    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<List<Integer>> callableFuture = executorService.submit(() -> {
            int count = 10;
            List<Integer> list = new ArrayList<Integer>();
            while (count > 0) {
                list.add(count);
                count--;
                delay(1);
            }
            return list;
        });
        Future<List<Integer>> callableFuture1 = executorService.submit(() -> {
            int count = 20;
            List<Integer> list = new ArrayList<Integer>();
            while (count > 10) {
                list.add(count);
                count--;
                delay(1);
            }
            return list;
        });
        System.out.println("waiting for response..");
        List<Integer> list = callableFuture.get();
        List<Integer> list1 = callableFuture1.get();
        System.out.println("Completed "+list);
        System.out.println("Completed "+list1);

    }
    private static void delay(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
