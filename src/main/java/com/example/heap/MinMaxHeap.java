package com.example.heap;

import java.util.Comparator;

/**
 * Created by ycwu on 2017/9/29.
 */
public class MinMaxHeap<E> {

    // data stores from 1
    private Object[] array;
    private int size;
    private Comparator<E> comparator;

    public MinMaxHeap(int size, Comparator<E> comparator) {
        this.array = new Object[size + 1];
        this.size = 0;
        this.comparator = comparator;
    }

    public void resize() {
        if (array.length == Integer.MAX_VALUE) {
            return;
        }

        int newSize = array.length + array.length / 2;
        // overflow
        if (newSize < array.length) {
            newSize = Integer.MAX_VALUE;
        }
        Object[] newArray = new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public void offer(E x) {
        if (size >= array.length) {
            throw new ArrayIndexOutOfBoundsException(size);
        }

        int k = ++size;
        // handle first level
        if (k == 1) {
            array[k] = x;
            return;
        } else if (k == 2) {
            if (comparator.compare(x, (E) array[1]) < 0) {
                array[2] = array[1];
                array[1] = x;
                return;
            } else {
                array[2] = x;
            }

        }

        boolean stop = false;
        while (!stop) {
            Object c;
            // left sibling <= right sibling
            if (k % 2 == 0 && comparator.compare((E) (c = array[k - 1]), x) > 0) {
                array[k--] = c;
            }

            int grandpa = parent(parent(k));
            if (grandpa <= 0) {
                stop = true;
            }

            int left = 2 * grandpa + 1;
            int right = left + 1;

            if (comparator.compare(x, (E) (c = array[left])) < 0) { // n >= left(grandpa(n))
                array[k] = c;
                k = left;
            } else if (comparator.compare(x, (E) (c = array[right])) > 0) {  // n <= right(grandpa(n))
                array[k] = c;
                k = right;
            } else {
                break;
            }
        }
        array[k] = x;
    }

    /**
     * 1-based
     *
     * @param i
     * @return
     */
    public E peek(int i) {
        if (i <= 0 || i > size) {
            throw new ArrayIndexOutOfBoundsException(i);
        }

        return (E) array[i];
    }

    public int size() {
        return size;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }
}
