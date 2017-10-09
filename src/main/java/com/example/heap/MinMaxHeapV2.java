package com.example.heap;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/10/7.
 * <p>
 * 1-based array
 */
public class MinMaxHeapV2<E> {

    private Object[] array;
    private Comparator<E> comparator;
    private int size = 0;

    //
    public static final int MIN = 1;
    public static final int MAX = 0;
    private static final int NOT_EXISTS = -1;


    public MinMaxHeapV2(Comparator<E> comparator, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must > 0");
        }
        array = new Object[capacity == Integer.MAX_VALUE ? Integer.MAX_VALUE : capacity + 1];
        this.comparator = comparator;
    }

    /**
     * indexed from 1
     *
     * @param i
     * @return
     */
    public E peek(int i) {
        if (i <= 0 || i > size) {
            throw new IndexOutOfBoundsException(i + "");
        }

        return (E) array[i];
    }

    public void offer(E x) {
        if (size == array.length) {
            throw new ArrayIndexOutOfBoundsException(size);
        }

        int k = ++size;
        if (k == 1) {
            array[1] = x;
            return;
        }

        int parent = k / 2;
        switch (level(k)) {
            case MIN:
                // if x > x's parent and is in MIN level, then swap them, and search in MAX level with x
                if (comparator.compare(x, (E) array[parent]) > 0) {
                    array[k] = array[parent];
                    verifyMax(parent, x);
                } else {
                    verifyMin(k, x);
                }
                break;
            case MAX:
                // if x < x's parent and is in MAX level, then swap them, and search in MIN level with x
                if (comparator.compare(x, (E) array[parent]) < 0) {
                    array[k] = array[parent];
                    verifyMin(parent, x);
                } else {
                    verifyMax(k, x);
                }
                break;
        }
    }

    public E removeMin() {
        if (size == 0) {
            return null;
        }

        E min = (E) array[1];
        E x = (E) array[size];
        array[size--] = null;
        if (size == 0) {
            return min;
        }

        int k = 1;
        while (k < size) {
            int m = findMinOfChildAndGrandchildren(k);
            if (m != NOT_EXISTS && comparator.compare((E) array[m], x) < 0) {
                array[k] = array[m];
                k = m;

                // m is the smallest and at child level (max level), then swap array[m] and x,
                // now the max level and min level are correct
                if (m <= ((k << 1) + 1)) {
                    break;
                } else {
                    // m is at grand child level(min level)
                    // when x sifts down to array[m] where is in min level,
                    // check again x is less than its parent(in max level)
                    int pm = m >> 1;
                    if (comparator.compare(x, (E) array[pm]) > 0) {
                        array[m] = array[pm];
                        k = pm;
                    }
                }
            } else {
                break;
            }
        }
        array[k] = x;
        return min;
    }

    private int findMinOfChildAndGrandchildren(int i) {
        int j1 = i << 1, j2 = i << 2;
        int[] arr = new int[]{j1, j1 + 1,       // left child and right child
                j2, j2 + 1, j2 + 2, j2 + 3      // grandchildren
        };
        int min = NOT_EXISTS;
        for (int j : arr) {
            if (j > size) {
                break;
            }

            min = (j == j1) ? j : (comparator.compare((E) array[min], (E) array[j]) > 0 ? j : min);
        }

        return min;
    }

    public E removeMax() {
        if (size == 0) {
            return null;
        }

        E max;
        if (size == 1) {
            max = (E) array[1];
            array[size--] = null;
            return max;
        }

        int k = 2;
        if (size >= 3 && comparator.compare((E) array[2], (E) array[3]) < 0) {
            k = 3;
        }

        max = (E) array[k];
        E x = (E) array[size];
        array[size--] = null;
        while (k < size) {
            int m = findMaxOfChildAndGrandchildren(k);
            if (m != NOT_EXISTS && comparator.compare((E) array[m], x) > 0) {
                array[k] = array[m];
                k = m;

                if (m <= ((k << 1) + 1)) {
                    // m is a child and at min level
                    break;
                } else {
                    // m is a grand child and at max level, parent(m) is at min level
                    int pm = m >> 1;
                    if (comparator.compare(x, (E) array[pm]) < 0) {
                        array[k] = array[pm];
                        k = pm;
                    }
                }
            } else {
                break;
            }
        }
        array[k] = x;

        return max;
    }

    private int findMaxOfChildAndGrandchildren(int i) {
        int j1 = i << 1, j2 = i << 2;
        int[] arr = new int[]{j1, j1 + 1,       // left child and right child
                j2, j2 + 1, j2 + 2, j2 + 3      // grandchildren
        };
        int max = NOT_EXISTS;
        for (int j : arr) {
            if (j > size) {
                break;
            }

            max = (j == j1) ? j : (comparator.compare((E) array[max], (E) array[j]) < 0 ? j : max);
        }

        return max;
    }

    private void verifyMin(int i, E x) {
        /**
         * since min and max level occurs level by level, so x should compare with its grandparent
         */
        int grandparent;
        while ((grandparent = i / 4) > 0 && comparator.compare(x, (E) array[grandparent]) < 0) {
            array[i] = array[grandparent];
            i = grandparent;
        }
        array[i] = x;
    }

    private void verifyMax(int i, E x) {
        /**
         * since min and max level occurs level by level, so x should compare with its grandparent
         */
        int grandparent;
        while ((grandparent = i / 4) > 0 && comparator.compare(x, (E) array[grandparent]) > 0) {
            array[i] = array[grandparent];
            i = grandparent;
        }
        array[i] = x;
    }

    public int level(int i) {
        int k = 1, j = 1;
        while ((k <<= 1) <= i) {
            j++;
        }
        // j % 2 == 0 ? MAX : MIN
        return j & 1;
    }


}
