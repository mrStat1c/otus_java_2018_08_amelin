package ru.otus;

import java.util.*;

public class CustomArrayList<T> implements List<T> {

    private Object[] elements;
    private int firstEmptyElementIndex = 0;
    private String unsupportedOperationMessage = "This operation is not supported.";

    public CustomArrayList() {
        this(100);
    }

    public CustomArrayList(int capacity) {
        elements = new Object[capacity];
    }

    @Override
    public int size() {
        return firstEmptyElementIndex;
    }


    @Override
    public T get(int index) {
        checkIndexAvailable(index);
        return (T) elements[index];

    }

    @Override
    public T set(int index, T element) {
        if (index == firstEmptyElementIndex) {
            add(element);
            return null;
        } else {
            checkIndexAvailable(index);
            T oldElement = get(index);
            update(index, element);
            return oldElement;
        }
    }


    private void checkIndexAvailable(int index) {
        if (index >= firstEmptyElementIndex) {
            throw new IndexOutOfBoundsException("Element with index " + index + " don't exist.");
        }
    }

    @Override
    public boolean add(T t) {
        if (firstEmptyElementIndex == size() - 1) {
            expandList();
        }
        elements[firstEmptyElementIndex] = t;
        firstEmptyElementIndex++;
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
        for (int i = 0; i < size(); i++) {
            s.append(elements[i].toString()).append(" ");
        }
        return s.toString();
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size()];
        System.arraycopy(elements, 0, a, 0, size());
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
                throw new UnsupportedOperationException(unsupportedOperationMessage);
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException(unsupportedOperationMessage);
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
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(unsupportedOperationMessage);
    }

}
