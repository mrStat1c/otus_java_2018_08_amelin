package ru.otus;

import java.util.*;

public class ATM implements AtmObserver{

    private int balance = 0;
    private Map<Integer, Integer> notes = new HashMap<>();
    private boolean isActive;
    private String description;

    public ATM(boolean initialActivity, String description) {
        for (MoneyNote note : MoneyNote.values()) {
            notes.put(note.getValue(), 0);
        }
        this.isActive = initialActivity;
        this.description = description;
        getSnapshot();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void receiveMoney(List<MoneyNote> moneyNotes) {
        moneyNotes.forEach(this::receiveNote);
    }

    private void receiveNote(MoneyNote moneyNote) {
        int noteValue = moneyNote.getValue();
        notes.put(noteValue, notes.get(noteValue) + 1);
        balance += noteValue;
    }

    public void giveMoney(int moneySum) throws Exception {
        if (balance > moneySum) {
            List<Integer> issuedNotes = new ArrayList<>();
            int issuedSum = 0;
            int noteValue, noteCount;

            List<Integer> moneyValues = MoneyNote.getValueList();
            for(int i = 0; i < moneyValues.size(); i++){
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
                        System.out.println("Banknotes were issued:");
                        issuedNotes.forEach(System.out::println);
                        balance -= issuedSum;
                        return;
                    }
                }
                if (noteValue == MoneyNote.getMinimumValue()) {
                    int indexLastNote = issuedNotes.size() - 1;
                    int valueLastNote = issuedNotes.get(indexLastNote);
                    int indexNoteForRemove = 0;
                    int valueNoteForRemove = 0;
                    for (int k = indexLastNote; k >= 0; k--) {
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
                    for (Integer el : sublist) {
                        issuedSum -= el;
                    }
                    issuedNotes.removeAll(sublist);
                }
            }
            throw new Exception("Unable to issue required amount");
        }
    }

    public int getBalance() {
        return balance;
    }


    @Override
    public void resetState(AtmSnapshot atmSnapshot) {
        setActive(atmSnapshot.isActive());
        setDescription(atmSnapshot.getDescription());
    }


    public AtmSnapshot getSnapshot(){
        return new AtmSnapshot(this.isActive, this.description);
    }

}
