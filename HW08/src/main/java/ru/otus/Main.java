package ru.otus;

import java.util.List;

import static ru.otus.MoneyNote.*;

public class Main {
    public static void main(String[] args) {

        AtmDepartment atmDepartment = new AtmDepartment();
        atmDepartment.addAtm("ATM1", new ATM(false, "Sberbank, Lenin square 12A"));
        atmDepartment.addAtm("ATM2", new ATM(false, "Tinkoff"));
        atmDepartment.addAtm("ATM3", new ATM(true, "VTB24 new super atm"));
        System.out.println("ATM1 isInitialActive = " + atmDepartment.getAtm("ATM1").isActive()
        + ", description = " + atmDepartment.getAtm("ATM1").getDescription());
        System.out.println("ATM2 isInitialActive = " + atmDepartment.getAtm("ATM2").isActive()
                + ", description = " + atmDepartment.getAtm("ATM2").getDescription());
        System.out.println("ATM3 isInitialActive = " + atmDepartment.getAtm("ATM3").isActive()
                + ", description = " + atmDepartment.getAtm("ATM3").getDescription());
        atmDepartment.createAtmSnapshots();
        System.out.println("MoneyRemainderFromAtms: " + atmDepartment.getMoneyRemainderFromAtms());

        System.out.println("\nAdd money to ATMs and change ATM states");
        ATM atm1 = atmDepartment.getAtm("ATM1");
        atm1.receiveMoney(List.of(Rub100, Rub5000, Rub2000));

        ATM atm2= atmDepartment.getAtm("ATM2");
        atm2.receiveMoney(List.of(Rub50, Rub50, Rub1000, Rub2000, Rub50));
        atm2.setActive(true);
        atm2.setDescription("Selhozbank ulitca dmitrovskaya 7");

        ATM atm3 = atmDepartment.getAtm("ATM3");
        atm3.receiveMoney(List.of(Rub100, Rub200, Rub10, Rub1000));
        atm3.setActive(false);
        atm3.setDescription("Moskow Bank prosto bank");

        atmDepartment.addAtm("ATM1", atm1);
        atmDepartment.addAtm("ATM2", atm2);
        atmDepartment.addAtm("ATM3", atm3);
        System.out.println("ATM1 isInitialActive = " + atmDepartment.getAtm("ATM1").isActive()
                + ", description = " + atmDepartment.getAtm("ATM1").getDescription());
        System.out.println("ATM2 isInitialActive = " + atmDepartment.getAtm("ATM2").isActive()
                + ", description = " + atmDepartment.getAtm("ATM2").getDescription());
        System.out.println("ATM3 isInitialActive = " + atmDepartment.getAtm("ATM3").isActive()
                + ", description = " + atmDepartment.getAtm("ATM3").getDescription());
        System.out.println("MoneyRemainderFromAtms: " + atmDepartment.getMoneyRemainderFromAtms());


        System.out.println("\nReset ATM states");
        atmDepartment.resetAtms();
        System.out.println("ATM1 isInitialActive = " + atmDepartment.getAtm("ATM1").isActive()
                + ", description = " + atmDepartment.getAtm("ATM1").getDescription());
        System.out.println("ATM2 isInitialActive = " + atmDepartment.getAtm("ATM2").isActive()
                + ", description = " + atmDepartment.getAtm("ATM2").getDescription());
        System.out.println("ATM3 isInitialActive = " + atmDepartment.getAtm("ATM3").isActive()
                + ", description = " + atmDepartment.getAtm("ATM3").getDescription());
    }
}
