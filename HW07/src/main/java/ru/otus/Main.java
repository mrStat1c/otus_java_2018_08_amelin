package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ATM atm = new ATM();
        List<Integer> notes = new ArrayList<>();
        notes.add(5000);
        notes.add(5000);
        notes.add(2000);
        notes.add(1000);
        notes.add(1000);
        notes.add(200);
        notes.add(100);
        notes.add(50);
        notes.add(50);
        notes.add(50);
        notes.add(10);
        notes.add(10);
        notes.add(10);
        atm.takeMoney(notes);
        atm.giveMoney(6470);

        notes.clear();
        notes.add(500);
        notes.add(200);
        notes.add(200);
        notes.add(200);
        atm.takeMoney(notes);
        atm.giveMoney(600);
    }
}
