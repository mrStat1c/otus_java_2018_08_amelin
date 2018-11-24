package ru.otus;

import java.util.*;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

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
                ),
                Map.of("first", "text1text1text1", "second", "text2text2text2")
        );

//        System.out.println(JsonSerializator.getJsonString(hero1));
//        System.out.println(JsonSerializator.getJsonString(null));
//        System.out.println(JsonSerializator.getJsonString(List.of(1,2,3)));
        System.out.println(JsonSerializator.getJsonString(new int[]{1,2,3}));

    }

}
