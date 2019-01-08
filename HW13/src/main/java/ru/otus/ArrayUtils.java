package ru.otus;

public class ArrayUtils {

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
