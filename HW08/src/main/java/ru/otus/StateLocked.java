package ru.otus;

public class StateLocked implements State {


    @Override
    public void init(ATM atm) {
        atm.setState(false);
    }
}
