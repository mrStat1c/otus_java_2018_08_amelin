package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class AtmSnapshotHolder {

    private static Map<String, AtmSnapshot> atmSnapshots = new HashMap<>();

    public static AtmSnapshot getAtmSnapshot(String atmName) {
        return atmSnapshots.get(atmName);
    }

    public static void setAtmSnapshot(String atmName, AtmSnapshot atmSnapshot) {
        atmSnapshots.put(atmName, atmSnapshot);
    }
}
