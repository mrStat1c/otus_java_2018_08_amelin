package ru.otus;

import java.util.Map;

public class Amployee {

  private int id;
  private String fio;
  private long creationTime;
  private long lastAccessTime;


    public Amployee(int key, String value) {
        this.id = key;
        this.fio = value;
        this.creationTime = getCurrentTime();
        this.lastAccessTime = getCurrentTime();
    }

    protected long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setAccessed() {
        lastAccessTime = getCurrentTime();
    }
}
