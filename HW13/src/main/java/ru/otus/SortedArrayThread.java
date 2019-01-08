package ru.otus;

import java.util.Arrays;

public class SortedArrayThread implements Runnable{

    private int[] array;

    public int[] getArray() {
        return array;
    }

    public SortedArrayThread(int[] array){
        this.array = array;
    }

    public void run() {
        Arrays.sort(array);
    }
}
