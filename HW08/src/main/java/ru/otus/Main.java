package ru.otus;

import java.util.List;

import static ru.otus.MoneyNote.*;

public class Main {
    public static void main(String[] args) {

        AtmDepartment atmDepartment = new AtmDepartment();
        atmDepartment.addAtm("ATM1", new ATM(false));
        atmDepartment.addAtm("ATM2", new ATM(false));
        atmDepartment.addAtm("ATM3", new ATM(true));
        System.out.println("ATM1 isInitialActive = " + atmDepartment.getAtm("ATM1").isActive());
        System.out.println("ATM2 isInitialActive = " + atmDepartment.getAtm("ATM2").isActive());
        System.out.println("ATM3 isInitialActive = " + atmDepartment.getAtm("ATM3").isActive());

        System.out.println("\n");
        ATM atm1 = atmDepartment.getAtm("ATM1");
        atm1.receiveMoney(List.of(Rub100, Rub5000, Rub2000));
        System.out.println("ATM1 balance = " + atm1.getBalance() + ", isActive = " + atm1.isActive());

        ATM atm2= atmDepartment.getAtm("ATM2");
        atm2.receiveMoney(List.of(Rub50, Rub50, Rub1000, Rub2000, Rub50));
        new StateUnlocked().init(atm2);
        System.out.println("ATM2 balance = " + atm2.getBalance() + ", isActive = " + atm2.isActive());

        ATM atm3 = atmDepartment.getAtm("ATM3");
        atm3.receiveMoney(List.of(Rub100, Rub200, Rub10, Rub1000));
        new StateLocked().init(atm3);
        System.out.println("ATM3 balance = " + atm3.getBalance() + ", isActive = " + atm3.isActive());

        atmDepartment.addAtm("ATM1", atm1);
        atmDepartment.addAtm("ATM2", atm2);
        atmDepartment.addAtm("ATM3", atm3);

        System.out.println("\n");
        System.out.println("MoneyRemainderFromAtms: " + atmDepartment.getMoneyRemainderFromAtms());
        atmDepartment.notifyAtms();

        System.out.println("\n");
        System.out.println("ATM1 isActive = " + atmDepartment.getAtm("ATM1").isActive());
        System.out.println("ATM2 isActive = " + atmDepartment.getAtm("ATM2").isActive());
        System.out.println("ATM3 isActive = " + atmDepartment.getAtm("ATM3").isActive());
    }
}
