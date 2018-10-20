package ru.otus;

public class Employee {

  private int id;
  private String fio;
  private long creationTime;
  private long lastAccessTime;


    public Employee(int key, String fio) {
        this.id = key;
        this.fio = fio;
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


    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setAccessed() {
        lastAccessTime = getCurrentTime();
    }
}
