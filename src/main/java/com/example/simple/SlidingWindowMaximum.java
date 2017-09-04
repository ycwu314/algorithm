package com.example.simple;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/9/3.
 */
public class SlidingWindowMaximum {

    public static int[] findMaxByMaxHeap(int[] a, int w) {
        int[] b = new int[a.length - w + 1];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // reverse order
                return o2 - o1;
            }
        });

        for (int i = 0; i < w; i++) {
            maxHeap.offer(a[i]);
        }

        for (int i = 0; i < a.length - w; i++) {
            b[i] = maxHeap.peek();
            // remove the left side
            maxHeap.remove(a[i]);
            maxHeap.offer(a[i + w]);
        }
        // last round
        b[a.length - w] = maxHeap.poll();

        return b;
    }

    public static int[] findMaxByDeque(int[] a, int w) {
        int[] b = new int[a.length - w + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // prepare first w elements
        for (int i = 0; i < w; i++) {
            while ((!deque.isEmpty()) && a[i] >= a[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        for (int i = w; i < a.length; i++) {
            b[i - w] = a[deque.peekFirst()];
            while (!deque.isEmpty() && a[i] >= a[deque.peekLast()]) {
                deque.pollLast();
            }
            while (!deque.isEmpty() && deque.peekFirst() <= i - w) {
                deque.pollFirst();
            }
            deque.offerLast(i);

        }
        b[a.length - w] = a[deque.pollFirst()];

        return b;
    }

    @Test
    public void testFindMax() {
        Assert.assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, findMaxByMaxHeap(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));

        Assert.assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7}, findMaxByDeque(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));

    }
}
