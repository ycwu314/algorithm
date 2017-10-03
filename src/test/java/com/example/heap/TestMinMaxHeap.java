package com.example.heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by ycwu on 2017/9/29.
 */
public class TestMinMaxHeap extends HeapTestBase {

    @Test
    public void testOffer() {
        int[] arr = new int[]{4, 80, 8, 60, 6, 50, 12, 20, 10, 16, 14, 30};
        MinMaxHeap<Integer> minMaxHeap = new MinMaxHeap<>(13, ASC);
        for (int a : arr) {
            minMaxHeap.offer(a);
        }

        minMaxHeap.offer(2);
        Assert.assertEquals(2, minMaxHeap.peek(1).intValue());
        Assert.assertEquals(4, minMaxHeap.peek(5).intValue());
        Assert.assertEquals(6, minMaxHeap.peek(13).intValue());
    }

    @Test
    public void testRemoveMin() {
        int[] arr = new int[]{4, 80, 8, 60, 6, 50, 12, 20, 10, 16, 14, 30, 40};
        MinMaxHeap<Integer> minMaxHeap = new MinMaxHeap<>(13, ASC);
        for (int a : arr) {
            minMaxHeap.offer(a);
        }

        Arrays.sort(arr);
        for (int a : arr) {
            Assert.assertEquals(a, minMaxHeap.removeMin().intValue());
        }
    }

    @Test
    public void testRemoveMax() {
        int[] arr = new int[]{4, 80, 8, 60, 6, 50, 12, 20, 10, 16, 14, 30, 40};
        MinMaxHeap<Integer> minMaxHeap = new MinMaxHeap<>(13, ASC);
        for (int a : arr) {
            minMaxHeap.offer(a);
        }

        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            Assert.assertEquals(arr[i], minMaxHeap.removeMax().intValue());
        }
    }

}
