package ru.otus;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int[] mas = new int[100000];
        Random random = new Random();

        for (int i = 0; i < mas.length; i++) {
            mas[i] = random.nextInt(1000);
        }

        mas = ArrayUtils.parallelSort(mas, 4);

        for (int element : mas) {
            System.out.println(element);
            }
    }
}
