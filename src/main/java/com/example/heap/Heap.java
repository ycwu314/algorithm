package com.example.heap;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/9/23.
 * <p>
 * 0-based heap.
 * <pre>
 * parent(i) = (i-1)/2
 * left(i) = 2*i+1
 * right(i) = 2*i+2
 * </pre>
 */
public class Heap<E> {

    private Object[] array;
    private int count = 0;
    private Comparator<? super E> comparator;

    public Heap(int size, Comparator<? super E> comparator) {
        array = new Object[size];
        this.comparator = comparator;
    }

    public Heap(E[] array, Comparator<? super E> comparator) {
        this.array = new Object[array.length];
        System.arraycopy(array, 0, this.array, 0, array.length);
        this.comparator = comparator;
        count = array.length;
        heapify();
    }

    public E peek() {
        return (E) array[0];
    }

    public E poll() {
        if (count == 0) {
            return null;
        }
        E e = (E) array[0];

        Object a = array[count - 1];
        array[--count] = null;
        array[0] = a;

        siftDown(0, (E) array[0]);

        return e;
    }


    public void offer(E e) {
        if (count == array.length) {
            throw new ArrayIndexOutOfBoundsException("heap is full");
        }
        array[count++] = e;
        if (count > 1) {
            siftUp(count - 1, e);
        }

    }

    // build heap
    private void heapify() {
        for (int i = (count - 1) >> 1; i >= 0; i--) {
            siftDown(i, (E) array[i]);
        }
    }


    /**
     * @param i
     * @param x
     * @see java.util.PriorityQueue##siftDown(int, Object)
     */
    private void siftDown(int i, E x) {
        int half = count >>> 1;
        while (i < half) {
            int child = (i << 1) + 1;
            int right = child + 1;
            Object c = array[child];
            // find the smaller of left and right
            if (right < count && comparator.compare((E) array[right], (E) c) < 0) {
                c = array[child = right];
            }
            if (comparator.compare(x, (E) c) <= 0) {
                break;
            }

            // update i with the smaller child
            array[i] = c;
            i = child;
        }
        array[i] = x;
    }

    /**
     * only swap index i and the min object
     *
     * @param i
     */
    // assume min heap
    @Deprecated
    private void siftDownOriginal(int i) {
        int half = count >>> 1;
        while (i < half) {
            int left = (i << 1) + 1;  // 2 * i + 1;
            int right = left + 1;   // 2 * (i + 1);
            int target = i;
            if (left < count && comparator.compare((E) array[left], (E) array[target]) < 0) {
                target = left;
            }
            if (right < count && comparator.compare((E) array[right], (E) array[target]) < 0) {
                target = right;
            }
            if (target == i) {
                break;
            }

            Object tmp = array[i];
            array[i] = array[target];
            array[target] = tmp;
            i = target;
        }
    }

    private void siftUp(int i, E x) {
        while (i > 0) {
            int parent = (i - 1) >>> 2;
            Object p = array[parent];
            if (comparator.compare(x, (E) p) >= 0) {
                break;
            }
            array[i] = p;
            i = parent;
        }
        array[i] = x;
    }

    // assume min heap
    @Deprecated
    private void siftUpOriginal() {
        int i = count - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (comparator.compare((E) array[i], (E) array[parent]) < 0) {
                Object tmp = array[parent];
                array[parent] = array[i];
                array[i] = tmp;
                i = parent;
            } else {
                break;
            }
        }
    }

}
