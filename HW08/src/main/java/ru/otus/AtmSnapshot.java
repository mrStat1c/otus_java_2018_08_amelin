package ru.otus;

public class AtmSnapshot {

    private final boolean isActive;
    private final String description;

    AtmSnapshot(boolean isActive, String description) {
        this.isActive = isActive;
        this.description = description;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public String getDescription() {
        return this.description;
    }
}
