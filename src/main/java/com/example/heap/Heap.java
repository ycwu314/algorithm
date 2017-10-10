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
    private int size = 0;
    private Comparator<? super E> comparator;

    public Heap(int capacity, Comparator<? super E> comparator) {
        array = new Object[capacity];
        this.comparator = comparator;
    }

    public Heap(E[] array, Comparator<? super E> comparator) {
        this.array = new Object[array.length];
        System.arraycopy(array, 0, this.array, 0, array.length);
        this.comparator = comparator;
        size = array.length;
        heapify();
    }

    public void clear() {
        size = 0;
        array = new Object[array.length];
    }

    public E peek() {
        return (E) array[0];
    }

    public E peek(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return (E) array[i];
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        E e = (E) array[0];

        Object a = array[size - 1];
        array[--size] = null;
        array[0] = a;

        siftDown(0, (E) array[0]);

        return e;
    }


    public void offer(E e) {
        if (size == array.length) {
            throw new ArrayIndexOutOfBoundsException("heap is full");
        }
        array[size++] = e;
        if (size > 1) {
            siftUp(size - 1, e);
        }

    }

    // build heap
    private void heapify() {
        for (int i = (size - 1) >> 1; i >= 0; i--) {
            siftDown(i, (E) array[i]);
        }
    }


    /**
     * @param i
     * @param x
     * @see java.util.PriorityQueue##siftDown(int, Object)
     */
    private void siftDown(int i, E x) {
        int half = size >>> 1;
        while (i < half) {
            int child = (i << 1) + 1;
            int right = child + 1;
            Object c = array[child];
            // find the smaller of left and right
            if (right < size && comparator.compare((E) array[right], (E) c) < 0) {
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
        int half = size >>> 1;
        while (i < half) {
            int left = (i << 1) + 1;  // 2 * i + 1;
            int right = left + 1;   // 2 * (i + 1);
            int target = i;
            if (left < size && comparator.compare((E) array[left], (E) array[target]) < 0) {
                target = left;
            }
            if (right < size && comparator.compare((E) array[right], (E) array[target]) < 0) {
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
        int i = size - 1;
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

    public E removeAt(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException(i);
        }

        if (i == 0) {
            return poll();
        }

        E x = (E) array[i];
        E moved = (E) array[--size];
        array[size] = null;
        // i is the last element
        if (size == i) {
            return x;
        }

        int parent = (i - 1) >> 1;
        if (comparator.compare(moved, (E) array[parent]) < 0) {
            array[i] = array[parent];
            i = parent;
            siftUp(i, moved);
        } else {
            siftDown(i, moved);
        }
        return x;
    }
}
