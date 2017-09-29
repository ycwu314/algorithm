package com.example.heap;

import java.util.Comparator;

/**
 * Created by ycwu on 2017/9/29.
 */
public class HeapTestBase {

    protected final Comparator<Integer> ASC = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };

    protected final Comparator<Integer> DESC = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -o1.compareTo(o2);
        }
    };
}
