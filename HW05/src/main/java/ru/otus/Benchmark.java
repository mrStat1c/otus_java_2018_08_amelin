package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class Benchmark implements BenchmarkMBean {

    private volatile int size = 0;

    public void setSize(int size) {
        this.size = size;
    }

    void start() throws InterruptedException {
        List<String> stringList = new ArrayList<String>();

        while (true) {
            for (int i = 0; i < size; i++) {
                stringList.add(String.valueOf(i));
            }
            Thread.sleep(500);
            GC.printGCMetricks();
        }
    }
}
