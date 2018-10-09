package ru.otus;

import java.util.TimerTask;

public class GcPrintMetricTimer extends TimerTask {

    @Override
    public void run() {
        GC.printGCMetricks();
    }
}
