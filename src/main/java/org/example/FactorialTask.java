package org.example;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {

    private int n;
    private int start;
    private int end;
    private final Integer THRESHOLD = 5;

    public FactorialTask(Integer n) {
        this.n = n;
        this.start = 1;
        this.end = n;
    }

    public FactorialTask(int start, int end) {
        this.start = start;
        this.end = end;
        this.n = end - start + 1;
    }

    @Override
    protected Integer compute() {
        if (n <= THRESHOLD) {
            int result = 1;
            for (int i = start; i <= end; i++) {
                result *= i;
            }
            return result;
        } else  {
            int mid = (start + end) / 2;
            FactorialTask f1 = new FactorialTask(start, mid);
            FactorialTask f2 = new FactorialTask(mid+1, end);
            f1.fork();
            f2.fork();
            return f1.join() * f2.join();
        }
    }
}
