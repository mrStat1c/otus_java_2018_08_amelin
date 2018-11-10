package ru.otus;

import java.util.*;

public class AtmDepartment {

    private Map<String, ATM> atms = new HashMap<>();

    public void addAtm(String atmName, ATM atm) {
        atms.put(atmName, atm);
    }

    public void removeAtm(String atmName) {
        atms.remove(atmName);
    }

    public ATM getAtm(String atmName) {
        return atms.get(atmName);
    }

    public void createAtmSnapshots(){
        atms.forEach((name, atm) -> AtmSnapshotHolder.setAtmSnapshot(name, atm.getSnapshot()));
    }

    public void resetAtms() {
        atms.forEach((name, atm) -> {
            System.out.println("ATM " + name + " reset");
            atm.resetState(AtmSnapshotHolder.getAtmSnapshot(name));
        });
    }

    public int getMoneyRemainderFromAtms() {
        int sum = 0;
        for (Map.Entry<String, ATM> entry : atms.entrySet()) {
            sum += entry.getValue().getBalance();
        }
        return sum;
    }

}
