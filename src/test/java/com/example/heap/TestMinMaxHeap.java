package com.example.heap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ycwu on 2017/9/29.
 */
public class TestMinMaxHeap extends HeapTestBase {

    @Test
    public void testOffer() {
        int[] arr = new int[]{4, 80, 8, 60, 6, 40, 12, 20, 10, 16, 14, 30};
        MinMaxHeap<Integer> minMaxHeap = new MinMaxHeap<>(13, ASC);
        for (int a : arr) {
            minMaxHeap.offer(a);
        }

        minMaxHeap.offer(2);
        Assert.assertEquals(2, minMaxHeap.peek(1).intValue());
        Assert.assertEquals(4, minMaxHeap.peek(5).intValue());
        Assert.assertEquals(6, minMaxHeap.peek(13).intValue());
    }


}
