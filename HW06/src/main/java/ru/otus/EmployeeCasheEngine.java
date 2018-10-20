package ru.otus;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class EmployeeCasheEngine {

    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;
    private final long idleTimeMs;

    private final Map<Integer, SoftReference<Employee>> employees = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    EmployeeCasheEngine(int maxElements, long idleTimeMs) {
        this.maxElements = maxElements;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
    }

    public void put(Employee element) {
        if (employees.size() == maxElements) {
            int firstKey = employees.keySet().iterator().next();
            employees.remove(firstKey);
        }

        int key = element.getId();
        employees.put(key, new SoftReference<>(element));

        if (idleTimeMs != 0) {
            TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
            timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
        }
    }

    public Employee get(int key) {
        Employee element = employees.get(key) == null ? null: employees.get(key).get();
        if (element != null) {
            hit++;
            element.setAccessed();
        } else {
            miss++;
        }
        return element;
    }

    public int getHitCount() {
        return hit;
    }

    public int getMissCount() {
        return miss;
    }

    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final int key, Function<Employee, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                Employee element = employees.get(key).get();
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    employees.remove(key);
                    this.cancel();
                }
            }
        };
    }


    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
