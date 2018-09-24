package ru.otus.tests;

import ru.otus.Test;

public class Calculator {


    public static long sum (int x, int y){
        return x + y;
    }


    public static int minus (int x, int y){
        return x - y;
    }

    @Test
    public static void testSum(){
        System.out.println("OK");
   }
}
