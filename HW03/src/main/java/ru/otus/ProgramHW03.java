package ru.otus;

import java.util.Collections;

public class ProgramHW03 {

    public static void main(String[] args) {

        CustomArrayList<Integer> intCustomList1 = new CustomArrayList<>(5);
        CustomArrayList<Integer> intCustomList2 = new CustomArrayList<>(10);
        System.out.println("List 1 = " + intCustomList1);
        System.out.println("List 1 capacity = " + intCustomList1.size());
        System.out.println("List 1 element count = " + intCustomList1.getElementCount());
        System.out.println("List 2 = " + intCustomList2);
        System.out.println("List 2 capacity = " + intCustomList2.size());
        System.out.println("List 2 element count = " + intCustomList2.getElementCount());

        System.out.println("\n" + "Add 5 elements to List 1");
//      Collections.addAll
        Collections.addAll(intCustomList1, 15, 123, 0, 42, 78934);
        System.out.println("List 1 = " +  intCustomList1);
        System.out.println("List 1 capacity = " + intCustomList1.size());
        System.out.println("List 1 element count = " + intCustomList1.getElementCount());

        System.out.println("\n" + "Copy List 1 to List 2");
        System.out.println("\n" + "Add 5 elements to List 1");
//      Collections.copy
        Collections.copy(intCustomList2, intCustomList1);
        System.out.println("List 2 = " + intCustomList2);
        System.out.println("List 2 capacity = " + intCustomList2.size());
        System.out.println("List 2 element count = " + intCustomList2.getElementCount());

//      Collections.sort
        Collections.sort(intCustomList2);
        System.out.println("\n" + "Sorted List 2 = " + intCustomList2);
    }
}
