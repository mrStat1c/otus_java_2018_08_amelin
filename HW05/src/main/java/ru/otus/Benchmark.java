package ru.otus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Benchmark implements BenchmarkMBean {

    private volatile int size = 0;

    public void setSize(int size) {
        this.size = size;
    }

    void start() throws InterruptedException {
        List<String> stringList = new ArrayList<String>();
        Timer timer = new Timer();
        timer.schedule(new GcPrintMetricTimer(), 0, 60 * 1000);
        try {
            while (true) {
                for (int i = 0; i < size; i++) {
                    stringList.add(String.valueOf(i));
                }
                Thread.sleep(500);
            }
        } catch (RuntimeException e) {
            return;
        }
    }
}

