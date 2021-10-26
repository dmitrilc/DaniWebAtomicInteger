package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Entry {

    static int counter;
    static AtomicInteger atomicCounter = new AtomicInteger();

    public static void main(String[] args) {
        //unsafe();
        safe();
    }

    private static void unsafe(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++){
            executorService.submit(() -> {
                System.out.println(counter++);
            });
        }

        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static void safe(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++){
            executorService.submit(() -> {
                System.out.println(atomicCounter.getAndIncrement());
            });
        }

        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}