package ru.otus;

import java.util.List;
import java.util.Map;

public class Hero {

    private String specialization;
    private byte level;
    private int hp;
    private int mana;
    private long gold;
    private String[] features;
    private List<Weapon> weapons;
    private Map<String, String> notes;

    public Hero(
            String specialization,
            byte level,
            int hp,
            int mana,
            long gold,
            String[] features,
            List<Weapon> weapons,
            Map<String, String> notes){
        this.specialization = specialization;
        this.level = level;
        this.hp = hp;
        this.mana = mana;
        this.gold = gold;
        this.features = features;
        this.weapons = weapons;
        this.notes = notes;
    }

}
