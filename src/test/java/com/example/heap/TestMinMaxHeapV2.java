package com.example.heap;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Created by Administrator on 2017/10/7.
 */
public class TestMinMaxHeapV2 extends HeapTestBase {

    @Test
    public void testOffer() {
        MinMaxHeapV2<Integer> h = new MinMaxHeapV2<>(ASC, 20);
        Integer[] arr = new Integer[]{7, 70, 40, 30, 9, 10, 15, 45, 50, 30, 20, 12};
        for (Integer i : arr) {
            h.offer(i);
        }
        h.offer(80);
        assertEquals(80, h.peek(3).intValue());
    }

    @Test
    public void testRemoveMin() {
        MinMaxHeapV2<Integer> h = new MinMaxHeapV2<>(ASC, 20);
        Integer[] arr = new Integer[]{7, 70, 40, 30, 9, 10, 15, 45, 50, 30, 20, 12};
        for (Integer i : arr) {
            h.offer(i);
        }
        Arrays.sort(arr);
        for (int i : arr) {
            assertEquals(i, h.removeMin().intValue());
        }
    }

    @Test
    public void testRemoveMax() {
        MinMaxHeapV2<Integer> h = new MinMaxHeapV2<>(ASC, 20);
        Integer[] arr = new Integer[]{7, 70, 40, 30, 9, 10, 15, 45, 50, 30, 20, 12};
        for (Integer i : arr) {
            h.offer(i);
        }
        Arrays.sort(arr, DESC);
        for (int i : arr) {
            assertEquals(i, h.removeMax().intValue());
        }
    }

    @Test
    public void testLevel() {
        MinMaxHeapV2<Integer> h = new MinMaxHeapV2<>(ASC, 20);
        assertEquals(MinMaxHeapV2.MIN, h.level(1));
        assertEquals(MinMaxHeapV2.MAX, h.level(2));
        assertEquals(MinMaxHeapV2.MAX, h.level(3));
        assertEquals(MinMaxHeapV2.MIN, h.level(4));
        assertEquals(MinMaxHeapV2.MIN, h.level(5));
        assertEquals(MinMaxHeapV2.MAX, h.level(9));
    }
}
