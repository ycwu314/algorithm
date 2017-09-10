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


    ///////////////////////

    /**
     * 有一个二维数组(n*n),写程序实现从右上角到左下角沿主对角线方向打印。<br>
     * 给定一个二位数组arr及题目中的参数n，请返回结果数组。
     * <p>
     * TODO any faster way?
     *
     * @param arr
     * @param n
     * @return
     */
    public static int[] arrayPrint(int[][] arr, int n) {
        int[] ra = new int[n * n];
        int z = 0;
        // 从[n-1][n-1]开始打印右上角部分
        for (int i = 0; i < n; i++) {           // 打印的回合数
            for (int j = 0; j <= i; j++) {      // 每个回合打印的个数
                ra[z++] = arr[j][n - 1 - i + j];
            }
        }

        // 从[1][1]开始打印左下角部分
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                ra[z++] = arr[n - i + j][j];
            }
        }

        return ra;
    }


    public static void print2DArray(int[][] a) {
        for (int[] t : a) {
            System.out.println(Arrays.toString(t));
        }
    }


    public static void printArrayWithRowSize(int[] a, int rowSize) {
        int k = 0;
        for (int t : a) {
            if (k == rowSize) {
                System.out.println();
                k = 0;
            }
            System.out.print(t + " ");
            k++;
        }
        System.out.println();
    }

    @Test
    public void testArrayPrint() {
        int n = 4;
        int[][] arr = new int[n][n];
        for (int i = 0, j = 1; i < n; i++) {
            for (int k = 0; k < n; k++) {
                arr[i][k] = j++;
            }
        }

        int[] ra = arrayPrint(arr, n);
        Assert.assertArrayEquals(new int[]{4, 3, 8, 2, 7, 12, 1, 6, 11, 16, 5, 10, 15, 9, 14, 13}, ra);
        printArrayWithRowSize(ra, n);
    }


    ///////////////////////////

    // 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
    // num1,num2分别为长度为1的数组。传出参数
    // 将num1[0],num2[0]设置为返回结果
    public static void findNumsAppearOnce(int[] array, int num1[], int num2[]) {
        Set<Integer> set = new HashSet<>();
        for (int i : array) {
            if (set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }
        Iterator<Integer> it = set.iterator();
        num1[0] = it.next();
        num2[0] = it.next();
    }


    public static void findNumsAppearOnceV2(int[] array, int num1[], int num2[]) {
        num1[0] = 0;
        num1[0] = 0;

        int s = 0;
        for (int i : array) {
            s ^= i;
        }

        // must have at least 1 bit is different
        // find which bit is different
        int b = 1;
        while ((s & b) != b) {
            b <<= 1;
        }

        // split the array into 2 parts by the different bit
        for (int i : array) {
            if ((i & b) != b) {
                num1[0] ^= i;
            } else {
                num2[0] ^= i;
            }
        }
    }

    @Test
    public void testFindNumsAppearOnce() {
        int[] a = new int[1], b = new int[1];
        findNumsAppearOnceV2(new int[]{2, 4, 3, 6, 3, 2, 5, 5}, a, b);
        Assert.assertTrue((a[0] == 4 && b[0] == 6) || (b[0] == 4 && a[0] == 6));
    }
}
