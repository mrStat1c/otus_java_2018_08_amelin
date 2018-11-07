package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum MoneyNote {
    Rub5000(5000),
    Rub2000(2000),
    Rub1000(1000),
    Rub500(500),
    Rub200(200),
    Rub100(100),
    Rub50(50),
    Rub10(10);

    MoneyNote(int value){
        this.value = value;
    }

    private int value;

    public int getValue(){
        return value;
    }

    public static int getMinimumValue(){
        return Rub10.getValue();
    }

    public static List<Integer> getValueList(){
        return Arrays.stream(values())
                .map(MoneyNote::getValue)
                .collect(Collectors.toList());
   }
}
