package ru.otus;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int [] mas = ArrayUtils.parallelSort(
                ArrayUtils.generateArray(10000, 1000),
                4);

        for (int element : mas) {
            System.out.println(element);
            }
    }
}
