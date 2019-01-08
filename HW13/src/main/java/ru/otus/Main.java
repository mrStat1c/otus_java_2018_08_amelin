package ru.otus;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int[] mas = new int[100000];
        Random random = new Random();

        for (int i = 0; i < mas.length; i++) {
            mas[i] = random.nextInt(1000);
        }

        int[] mas1 = new int[25000];
        int[] mas2 = new int[25000];
        int[] mas3 = new int[25000];
        int[] mas4 = new int[25000];

        System.arraycopy(mas, 0, mas1, 0, 25000);
        System.arraycopy(mas, 25000, mas2, 0, 25000);
        System.arraycopy(mas, 50000, mas3, 0, 25000);
        System.arraycopy(mas, 75000, mas4, 0, 25000);

        SortedArrayThread thread1 = new SortedArrayThread(mas1);
        SortedArrayThread thread2 = new SortedArrayThread(mas2);
        SortedArrayThread thread3 = new SortedArrayThread(mas3);
        SortedArrayThread thread4 = new SortedArrayThread(mas4);

        thread1.run();
        thread2.run();
        thread3.run();
        thread4.run();
        mas1 = thread1.getArray();
        mas2 = thread2.getArray();
        mas3 = thread3.getArray();
        mas4 = thread4.getArray();

        mas = ArrayUtils.merge(ArrayUtils.merge(ArrayUtils.merge(mas1, mas2), mas3), mas4);

        for (int i = 0; i < mas.length; i++){
            System.out.println(mas[i]);
        }
    }
}
