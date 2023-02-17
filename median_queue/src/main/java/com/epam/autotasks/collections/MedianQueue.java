package com.epam.autotasks.collections;

import java.util.*;

class MedianQueue<E extends Comparable<E>> extends AbstractQueue<E> {
    PriorityQueue<E> left;
    PriorityQueue<E> right;

    public MedianQueue() {
        this.right = new PriorityQueue<>();
        this.left = new PriorityQueue<>(Collections.reverseOrder());
    }

    @Override
    public Iterator<E> iterator() {
        PriorityQueue<E> queue = new PriorityQueue<>(left);
        for (E e : right) queue.offer(e);
        return queue.iterator();
    }

    @Override
    public int size() {
        return left.size() + right.size();
    }

    @Override
    public boolean offer(final E e) {
        if (right.size() > 0 && e.compareTo(right.peek()) > 0) {
            right.offer(e);
        } else {
            left.offer(e);
        }
        handleBalance();
        //TODO return false if needed
        return true;
    }

    private void handleBalance() {
        if (left.size() - right.size() == 2) {
            right.add(left.remove());
        } else if (right.size() - left.size() == 2) {
            left.add(right.remove());
        }
    }

    @Override
    public E poll() {
        if (left.size() >= right.size()) {
            return left.poll();
        } else {
            return right.poll();
        }
    }

    @Override
    public E peek() {
        if (left.size() >= right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }


    }
}