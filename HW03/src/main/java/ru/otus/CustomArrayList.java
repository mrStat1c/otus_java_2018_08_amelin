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
        for (int i = 0; i < capacity; i++) {
            elements[i] = new Object();
        }
    }

    @Override
    public int size() {
        return elements.length;
    }

    public int getElementCount() {
        return firstEmptyElementIndex;
    }

    @Override
    public T get(int index) {
        if (index >= elements.length) {
            checkIndexAvailable(index);
        }
        return (T) elements[index];

    }

    @Override
    public T set(int index, T element) {
        if (element.getClass() != Object.class) {
            if (index == firstEmptyElementIndex) {
                add(element);
                return null;
            } else {
                checkIndexAvailable(index);
                T oldElement = get(index);
                System.arraycopy(elements, index, elements, index + 1, firstEmptyElementIndex - index);
                update(index, element);
                return oldElement;
            }
        } else {
            return null;
        }
    }


    private void checkIndexAvailable(int index) {
        if (index >= firstEmptyElementIndex) {
            throw new IndexOutOfBoundsException("Element with index " + index + " don't exist.");
        }
    }

    @Override
    public boolean add(T t) {
        elements[firstEmptyElementIndex] = t;
        firstEmptyElementIndex++;
        if (firstEmptyElementIndex == elements.length) {
            expandList();
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(this::add);
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < getElementCount(); i++) {
            s.append(elements[i].toString()).append(" ");
        }
        return s.toString();
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[getElementCount()];
        System.arraycopy(elements, 0, a, 0, getElementCount());
        return a;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {

            int cursorPosition = -1;

            @Override
            public T next() {
                cursorPosition++;
                return (T) elements[cursorPosition];
            }

            @Override
            public T previous() {
                cursorPosition--;
                return (T) elements[cursorPosition];
            }

            @Override
            public boolean hasNext() {
                return cursorPosition + 1 != firstEmptyElementIndex;
            }

            @Override
            public boolean hasPrevious() {
                return cursorPosition > 0;
            }

            @Override
            public int nextIndex() {
                return cursorPosition + 1;
            }

            @Override
            public int previousIndex() {
                return cursorPosition - 1;
            }

            @Override
            public void remove() {
            }

            @Override
            public void add(T t) {
            }

            @Override
            public void set(T t) {
                CustomArrayList.this.set(cursorPosition, t);
            }
        };
    }

    private void expandList() {
        int newSize = (int) (elements.length * 1.5);
        Object[] newList = new Object[newSize];
        for (int i = elements.length; i < newSize; i++) {
            newList[i] = new Object();
        }
        System.arraycopy(elements, 0, newList, 0, elements.length);
        elements = newList;
    }

    public void update(int index, T element) {
        checkIndexAvailable(index);
        elements[index] = element;
    }

    @Override
    public T remove(int index) {
        checkIndexAvailable(index);
        T removedElement = get(index);
        System.arraycopy(elements, index + 1, elements, index, firstEmptyElementIndex - index);
        firstEmptyElementIndex--;
        return removedElement;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(int index, T element) {
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

}
