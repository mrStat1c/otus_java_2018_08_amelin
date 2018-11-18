package ru.otus;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Hero hero1 = new Hero(
                "knight",
                (byte) 10,
                500,
                65,
                38500L,
                new String[]{"high defence", "high faith"},
                Arrays.asList(
                        new Weapon("knight sword", false, (short) 120),
                        new Weapon("knight arch", true, (short) 90)
                )
        );

        String jsonString = JsonSerializator.convertToJsonString(hero1);
        System.out.println(jsonString);

    }

}
