package ru.otus;

import java.util.*;

public class ATM {

    private int balance = 0;
    List<Integer> moneyValues = new ArrayList<>();
    Map<Integer, Integer> notes = new HashMap<>();

    public ATM() {
        moneyValues.add(5000);
        moneyValues.add(2000);
        moneyValues.add(1000);
        moneyValues.add(500);
        moneyValues.add(200);
        moneyValues.add(100);
        moneyValues.add(50);
        moneyValues.add(10);
        for (Integer value : moneyValues) {
            notes.put(value, 0);
        }
    }


    public void takeMoney(List<Integer> moneyNotes) {
        moneyNotes.forEach(this::takeNote);
    }

    private void takeNote(int moneyNote) {
        if (moneyValues.contains(moneyNote)) {
            notes.put(moneyNote, notes.get(moneyNote) + 1);
            balance += moneyNote;
        } else {
            System.out.println("Unknown note!");
        }
    }

    public void giveMoney(int moneySum) throws Exception {
        if (balance > moneySum) {
            List<Integer> issuedNotes = new ArrayList<>();
            int issuedSum = 0;
            int noteValue, noteCount;

            for (int i = 0; i < moneyValues.size(); i++) {
                noteValue = moneyValues.get(i);
                noteCount = notes.get(noteValue);
                while (noteCount > 0) {
                    if (issuedSum + noteValue > moneySum) {
                        break;
                    } else {
                        issuedSum += noteValue;
                        noteCount--;
                        issuedNotes.add(noteValue);
                    }
                    if (issuedSum == moneySum) {
                        for (Integer issueNote : issuedNotes) {
                            notes.put(issueNote, notes.get(issueNote) - 1);
                        }
                        balance -= issuedSum;
                        System.out.println("Выданы следующие купюры:");
                        issuedNotes.forEach(System.out::println);
                        return;
                    }
                }
                if (i == moneyValues.size() - 1) {
                    int indexLastNote = issuedNotes.size() - 1;
                    int valueLastNote = issuedNotes.get(indexLastNote);
                    int indexNoteForRemove = 0;
                    int valueNoteForRemove = 0;
                    for (int k = indexLastNote; k >= 0 ; k--) {
                        if (issuedNotes.get(k) != valueLastNote) {
                            indexNoteForRemove = k;
                            valueNoteForRemove = issuedNotes.get(indexNoteForRemove);
                            break;
                        }
                    }
                    for (int j = 0; j < moneyValues.size(); j++) {
                        if (moneyValues.get(j) == valueNoteForRemove) {
                            i = j;
                            break;
                        }
                    }
                    List<Integer> sublist = issuedNotes.subList(indexNoteForRemove, issuedNotes.size());
                    for(Integer el: sublist){
                        issuedSum -= el;
                    }
                    issuedNotes.removeAll(sublist);
                }
            }
            throw new Exception("Невозможно выдать требуемую сумму");
        }
    }

    public int getBalance() {
        return balance;
    }
}
