package ru.otus;

import java.util.*;

public class CustomArrayList<T> implements List<T> {

    private Object[] elements;
    private int firstEmptyElementIndex = 0;

    public CustomArrayList() {
        new CustomArrayList(100);
    }

    public CustomArrayList(int capacity) {
        elements = new Object[capacity];
       for(int i = 0; i < capacity; i++){
           elements[i] = new Object();
       }
    }

//////////////////////////////////////    Из интерфейса List

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }


    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }



    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }


    public T set(int index, T element) {
        return null;
    }

    public void add(int index, T element) {

    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
//////////////////////////////////////

    public int size() {
        return elements.length;
    }

    public T get(int index) {
        if(index >= elements.length){
            checkIndexAvailable(index);
        }
        return (T) elements[index];

    }

    private void checkIndexAvailable(int index) {
        if (index >= firstEmptyElementIndex) {
            throw new IndexOutOfBoundsException("Element with index " + index + " don't exist.");
        }
    }

    public boolean add(T t) {
        elements[firstEmptyElementIndex] = t;
        firstEmptyElementIndex++;
        if (firstEmptyElementIndex == elements.length) {
            expandList();
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        c.forEach(this::add);
        return false;
    }

//    public void add(int element) {
//        elements[firstEmptyElementIndex] = element;
//        if (++firstEmptyElementIndex == elements.length) {
//            expandList();
//        }
//    }

    private void expandList() {
        int newSize = (int) (elements.length * 1.5);
        Object[] newList = new Object[newSize];
        for(int i = elements.length; i < newSize; i++){
            newList[i] = new Object();
        }
        System.arraycopy(elements, 0, newList, 0, elements.length);
        elements = newList;
    }

    public void update(int index, T element) {
        checkIndexAvailable(index);
        elements[index] = element;
    }

    public T remove(int index) {
        checkIndexAvailable(index);
        T removedElement = get(index);
        System.arraycopy(elements, index + 1, elements, index, firstEmptyElementIndex - index);
        firstEmptyElementIndex--;
        return removedElement;
    }


//    public void delete(int index) {
//        checkIndexAvailable(index);
//        System.arraycopy(elements, index + 1, elements, index, firstEmptyElementIndex - index);
//        firstEmptyElementIndex--;
//    }


    public void insert(int index, T element) {
        if (index == firstEmptyElementIndex) {
            add(element);
        } else {
            checkIndexAvailable(index);
            System.arraycopy(elements, index, elements, index + 1, firstEmptyElementIndex - index);
            update(index, element);
        }
    }


}
class Program1{
    public static void main(String[] args) {
        CustomArrayList<Integer> intCustomList1 = new CustomArrayList<>(5);
        CustomArrayList<Integer> intCustomList2 = new CustomArrayList<>(10);

        Collections.addAll(intCustomList1, 15, 123, 0, 42, 78934);
//        Collections.copy(intCustomList2, intCustomList1);
        Collections.sort(intCustomList2);

        System.out.println(intCustomList2);



    }
}