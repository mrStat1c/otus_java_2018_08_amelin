package ru.otus;

import java.util.List;

public class Hero {

    private String specialization;
    private byte level;
    private int hp;
    private int mana;
    private long gold;
    private String[] features;
    private List<Weapon> weapons;

    public Hero(String specialization, byte level, int hp, int mana, long gold, String[] features, List<Weapon> weapons){
        this.specialization = specialization;
        this.level = level;
        this.hp = hp;
        this.mana = mana;
        this.gold = gold;
        this.features = features;
        this.weapons = weapons;
    }

}
