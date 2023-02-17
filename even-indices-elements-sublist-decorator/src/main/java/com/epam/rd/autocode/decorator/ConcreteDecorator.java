package com.epam.rd.autocode.decorator;

import java.util.*;

class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(List<String> sourceList) {
        super(sourceList);
    }

    @Override
    public String get(int index) {
        return sourceList.get(index * 2);
    }

    @Override
    public int size() {
        return sourceList.size() % 2 == 0 ?
                sourceList.size() / 2 :
                sourceList.size() / 2 + 1;
    }

    @Override
    public Iterator<String> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<String> listIterator() {
        return new ListIterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public String next() {
                return get(index++);
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public String previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(String s) {

            }

            @Override
            public void add(String s) {

            }
        };
    }
    @Override
    public String set(int index, String element) {
        return null;
    }

    @Override
    public void add(int index, String element) {

    }

    @Override
    public String remove(int index) {
        return null;
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
    public ListIterator<String> listIterator(int index) {
        return null;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return null;
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
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        return false;
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
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
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
}