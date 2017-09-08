package com.example.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/9/8.
 */
public class ArrayMisc {

    static class Pair {
        int val;
        int index;

        public Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }

        public Pair() {
        }
    }

    /**
     * 对于一个无序数组，数组中元素为互不相同的整数，请返回其中最小的k个数，**顺序与原数组中元素顺序一致**。<br>
     * 给定一个整数数组A及它的大小n，同时给定k，请返回其中最小的k个数。
     *
     * @param A
     * @param n
     * @param k
     * @return
     */
    public static int[] findKthNumbers(int[] A, int n, int k) {
        // min k elements => max heap with size of k, poll first element z when A[i] < z
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.val - o1.val;
            }
        });

        for (int i = 0; i < n; i++) {
            if (maxHeap.size() < k) {
                maxHeap.offer(new Pair(A[i], i));
            } else {

                if (A[i] < maxHeap.peek().val) {
                    maxHeap.poll();
                    maxHeap.offer(new Pair(A[i], i));
                }
            }
        }

        int[] result = new int[k];
        int i = 0;
        Iterator<Pair> iterator = maxHeap.iterator();
        while (iterator.hasNext()) {
            result[i++] = iterator.next().index;
        }

        // sort by index of A
        Arrays.sort(result);

        for (i = 0; i < k; i++) {
            result[i] = A[result[i]];
        }

        return result;
    }

    @Test
    public void testFindKthNumbers() {
        Assert.assertArrayEquals(new int[]{2, 1}, findKthNumbers(new int[]{2, 3, 1, 4}, 4, 2));
    }


    ////////////////////

    /**
     * first sort, then find the smallest missing
     *
     * @param A
     * @param n
     * @return
     */
    public static int findArrayMexV3(int[] A, int n) {
        Arrays.sort(A, 0, n);

        // skip those <=0
        int i = 0;
        while (i < n && A[i] <= 0) {
            i++;
        }

        int missing = 1;
        for (; i < n; i++) {

            if (missing != A[i]) {
                break;
            }
            boolean hasSame = false;
            // skip same numbers
            while (i < n && missing == A[i]) {
                i++;
                hasSame = true;
            }
            if (hasSame) {
                i--;
            }

            missing++;
        }

        return missing;
    }

    /**
     * do not sort, but use a set to store all positive numbers
     *
     * @param A
     * @param n
     * @return
     */
    public static int findArrayMexV2(int[] A, int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (A[i] <= 0) {
                continue;
            }

            set.add(A[i]);
        }

        int missing = 1;
        while (set.contains(missing)) {
            missing++;
        }

        return missing;
    }


    /**
     * if A[i] > n, it can be discard. <br>
     * so can use an array up to n elements to store. <br>
     * if 1 does not occur, then 1 is the answer. <br>
     * if 1 to n occur, then n+1 is the answer. <br>
     *
     * @param A
     * @param n
     * @return
     */

    public static int findArrayMex(int[] A, int n) {
        boolean[] mark = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (A[i] <= 0 || A[i] > n) {
                continue;
            }
            mark[A[i] - 1] = true;
        }

        for (int i = 0; i < n; i++) {
            if (!mark[i]) {
                return i + 1;
            }
        }


        return n + 1;
    }

    @Test
    public void testFindArrayMex() throws Exception {
        Assert.assertEquals(6, findArrayMex(new int[]{1, 2, 3, 4, 5}, 5));
        Assert.assertEquals(2, findArrayMex(new int[]{-1, 1}, 2));
        Assert.assertEquals(1, findArrayMex(new int[]{-1, 2}, 2));
        Assert.assertEquals(1, findArrayMex(new int[]{-1, 2, 3, 4}, 4));
        Assert.assertEquals(1, findArrayMex(new int[]{-1, -2, -3, -4}, 4));
        Assert.assertEquals(1, findArrayMex(new int[]{-1, -2, 0, 0}, 4));

        Assert.assertEquals(10, findArrayMex(new int[]{49, 40, 28, 32, 25, 57, 32, 11, 6, 9, 29, 32, 37, 1, 39, 1, 46, 33, 4, 49, 55, 9, 4, 36, 50, 13, 29, 5, 50, 26, 27, 12, 51, 54, 26, 32, 22, 12, 57, 8, 23, 20, 8, 25, 1, 54, 48, 59, 54, 2, 20, 44, 25, 7, 5, 26, 11, 9, 40, 3, 42, 42, 2, 31, 59, 25, 14, 58, 43, 60, 5, 22, 37, 25, 27, 28, 12, 28, 2, 38, 12, 39, 38, 35, 26, 28, 26, 34, 45, 17, 45, 59, 37, 9, 50, 37, 3, 15, 56, 41, 44, 49, 37, 1, 22, 33, 27, 27, 41, 41, 44, 4, 39, 50, 20, 11, 20, 52, 43, 53, 13}, 121));
    }


}
