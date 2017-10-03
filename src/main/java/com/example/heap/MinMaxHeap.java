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

    public E removeMin() {
        if (size == 0) {
            return null;
        }

        E min = (E) array[1];
        Object x = array[size];
        array[size--] = null;

        if (size == 0) {
            return min;
        }

        int k = 1;
        while (true) {
            Object c;
            // element(left) <= element(right)
            int sibling = k + 1;
            if (sibling <= size && sibling % 2 == 0 && comparator.compare((E) x, (E) (c = array[sibling])) > 0) {
                array[k] = c;
                k = sibling;
                continue;
            }

            // compare with min{k's left child, k's sibling's left child}
            int smaller = 2 * k + 1;
            if (smaller > size) {
                break;
            }

            c = array[smaller];
            int right = 2 * sibling + 1;
            if (right <= size && comparator.compare((E) c, (E) array[right]) > 0) {
                c = array[smaller = right];
            }

            if (comparator.compare((E) x, (E) c) > 0) {
                array[k] = c;
                k = smaller;
            } else {
                break;
            }

        }
        array[k] = x;
        return min;

    }

    public E removeMax() {
        if (size == 0) {
            return null;
        }

        E max, x;
        if (size == 1 || size == 2) {
            max = (E) (size == 1 ? array[1] : array[2]);
            array[size--] = null;
            return max;
        } else {
            max = (E) array[2];
            x = (E) array[size];
            array[size--] = null;
        }

        int k = 2;
        while (true) {
            Object c;
            int sibling = k - 1;
            // element(left) <= element(right)
            if (k % 2 == 0 && comparator.compare(x, (E) (c = array[sibling])) < 0) {
                array[k] = c;
                k = sibling;
                continue;
            }

            int larger = 2 * (sibling + 1);
            if (larger > size) {
                break;
            }

            c = array[larger];
            int right = 2 * (k + 1);
            if (right <= size && comparator.compare((E) array[right], (E) array[larger]) > 0) {
                c = array[larger = right];
            }

            if (comparator.compare(x, (E) c) < 0) {
                array[k] = c;
                k = larger;
            } else {
                break;
            }

        }

        array[k] = x;
        return max;
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
