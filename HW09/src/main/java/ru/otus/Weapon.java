package ru.otus;

public class Weapon {

    private String name;
    private boolean remote;
    private short damage;

    public Weapon(String name, boolean remote, short damage){
        this.name = name;
        this.remote = remote;
        this.damage = damage;
    }
}
