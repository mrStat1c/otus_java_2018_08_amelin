package ru.otus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class AmployeeCasheEngine {

    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;
    private final long idleTimeMs;

    private final Map<Integer, Amployee> amployees = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    AmployeeCasheEngine(int maxElements, long idleTimeMs) {
        this.maxElements = maxElements;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
    }

    public void put(Amployee element) {
        if (amployees.size() == maxElements) {
            int firstKey = amployees.keySet().iterator().next();
            amployees.remove(firstKey);
        }

        int key = element.getId();
        amployees.put(key, element);

        if (idleTimeMs != 0) {
            TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
            timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
        }
    }

    public Amployee get(int key) {
        Amployee element = amployees.get(key);
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

    private TimerTask getTimerTask(final int key, Function<Amployee, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                Amployee element = amployees.get(key);
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    amployees.remove(key);
                    this.cancel();
                }
            }
        };
    }


    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
