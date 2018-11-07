package ru.otus;

public class StateUnlocked implements State {

    @Override
    public void init(ATM atm) {
        atm.setState(true);
    }
}
