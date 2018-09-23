package ru.otus;

import java.util.Collections;

public class ProgramHW03 {

    public static void main(String[] args) {

        CustomArrayList<Integer> intCustomList1 = new CustomArrayList<>(5);
        CustomArrayList<Integer> intCustomList2 = new CustomArrayList<>(10);

        System.out.println("List 1 = " + intCustomList1);
        System.out.println("List 1 size = " + intCustomList1.size());
        System.out.println("List 2 = " + intCustomList2);
        System.out.println("List 2 size = " + intCustomList2.size());

//      Collections.addAll
        System.out.println("\n" + "Add elements to Lists");
        Collections.addAll(intCustomList1, 15, 123, 0, 42, 78934);
        Collections.addAll(intCustomList2, 11, 22, 33, 44, 55, 66, 77);
        System.out.println("List 1 = " +  intCustomList1);
        System.out.println("List 1 size = " + intCustomList1.size());
        System.out.println("List 2 = " +  intCustomList2);
        System.out.println("List 2 size = " + intCustomList2.size());

        //Collections.copy
        System.out.println("\n" + "Copy List 1 to List 2");
        Collections.copy(intCustomList2, intCustomList1);
        System.out.println("List 2 = " + intCustomList2);
        System.out.println("List 2 size = " + intCustomList2.size());

//      Collections.sort
        Collections.sort(intCustomList2);
        System.out.println("\n" + "Sorted List 2 = " + intCustomList2);
    }
}
