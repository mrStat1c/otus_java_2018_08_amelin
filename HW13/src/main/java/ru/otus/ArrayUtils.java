package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    public static int[] parallelSort(int[] array, int threadCount) throws InterruptedException {

        int partedArraySize;
        int lastPartedArraySize;
        List<int[]> partedArrays = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        if (array.length % threadCount == 0) {
            partedArraySize = array.length / threadCount;
            lastPartedArraySize = partedArraySize;
        } else {
            partedArraySize = array.length / threadCount + 1;
            lastPartedArraySize = array.length - (threadCount - 1) * partedArraySize;
        }


        for (int i = 1; i <= threadCount; i++) {
            int[] localArray;
            if (i == threadCount) {
                localArray = new int[lastPartedArraySize];
            } else {
                localArray = new int[partedArraySize];
            }
            System.arraycopy(array, partedArraySize * (i - 1), localArray, 0, localArray.length);

            threads.add(new Thread(() -> {
                Arrays.sort(localArray);
                partedArrays.add(localArray);
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int[] sortedArray = new int[0];
        for (int[] partedArray : partedArrays) {
            sortedArray = ArrayUtils.merge(sortedArray, partedArray);
        }
        return sortedArray;
    }


    public static int[] merge(int[] array1, int[] array2) {
        int array1Length = array1.length;
        int array2Length = array2.length;
        int a = 0, b = 0, length = array1Length + array2Length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            if (b < array2Length && a < array1Length) {
                if (array1[a] > array2[b]) result[i] = array2[b++];
                else result[i] = array1[a++];
            } else if (b < array2Length) {
                result[i] = array2[b++];
            } else {
                result[i] = array1[a++];
            }
        }
        return result;
    }

}
