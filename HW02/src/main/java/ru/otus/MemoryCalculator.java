package ru.otus;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.util.ArrayList;
import java.util.List;

public class MemoryCalculator {
    public static void main(String[] args) {
        System.out.println("Size of Empty String = " + RamUsageEstimator.sizeOf("") + " bytes");
        List<Integer> intList = new ArrayList<Integer>();
        System.out.println("Size of Empty ArrayList = " + RamUsageEstimator.sizeOf(intList) + " bytes");
        intList.add(15);
        long arrayWithOneElSize = RamUsageEstimator.sizeOf(intList);
        System.out.println("Size of ArrayList with 1 Integer value = " + arrayWithOneElSize + " bytes");
        intList.add(5);
        intList.add(17);
        intList.add(5325235);
        intList.add(0);
        long arrayWithFiveElSize = RamUsageEstimator.sizeOf(intList);
        System.out.println("Size of ArrayList with 5 Integer value = " + arrayWithFiveElSize + " bytes");
        System.out.println("Each Integer element in ArrayList increases the size by "
                + (arrayWithFiveElSize - arrayWithOneElSize)/4 + " bytes");

    }
}
