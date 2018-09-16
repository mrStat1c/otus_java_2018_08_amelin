package ru.otus;

public class CustomArrayList {

    private int[] elements;
    private int firstEmptyElementIndex = 0;

    public CustomArrayList() {
        new CustomArrayList(100);
    }

    public CustomArrayList(int capacity) {
        elements = new int[capacity];
    }


    public int get(int index) {
        checkIndexAvailable(index);
        return elements[index];

    }

    private void checkIndexAvailable(int index) {
        if (index >= firstEmptyElementIndex) {
            throw new IndexOutOfBoundsException("Element with index " + index + " don't exist.");
        }
    }

    public void add(int element) {
        elements[firstEmptyElementIndex] = element;
        if (++firstEmptyElementIndex == elements.length) {
            expandList();
        }
    }

    private void expandList() {
        int[] newList = new int[(int) (elements.length * 1.5)];
        System.arraycopy(elements, 0, newList, 0, elements.length);
        elements = newList;
    }

    public void update(int index, int element) {
        checkIndexAvailable(index);
        elements[index] = element;
    }

    public void remove(int index) {
        checkIndexAvailable(index);
        System.arraycopy(elements, index + 1, elements, index, firstEmptyElementIndex - index);
        firstEmptyElementIndex--;
    }

    public void insert(int index, int element) {
        if (index == firstEmptyElementIndex) {
            add(element);
        } else {
            checkIndexAvailable(index);
            System.arraycopy(elements, index, elements, index + 1, firstEmptyElementIndex - index);
            update(index, element);
        }
    }
}
