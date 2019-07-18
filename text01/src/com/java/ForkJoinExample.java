package com.java;

import java.util.concurrent.*;

public class ForkJoinExample extends RecursiveTask<Integer>{
    private final int threshold = 5;
    private int first;
    private int last;

    private ForkJoinExample(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if(last - first <= threshold){
            for (int i = first; i < last; i++) {
                result += i;
            }
        }else{
            int middle = last - (last - first) / 2;
            ForkJoinExample firstFork = new ForkJoinExample(first, middle);
            ForkJoinExample lastFork = new ForkJoinExample(middle + 1, last);
            firstFork.fork();
            lastFork.fork();
            result = firstFork.join() + lastFork.join();
        }
        return result;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinExample example = new ForkJoinExample(1, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> task = forkJoinPool.submit(example);
        System.out.println(task.get());
    }
}
