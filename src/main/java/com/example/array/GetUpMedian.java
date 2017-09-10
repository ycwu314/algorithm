package com.example.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/9/9.
 * <p>
 * 给定两个有序数组arr1和arr2，两个数组长度都为N，求两个数组中所有数的上中位数。
 * <p>
 * 要求：时间复杂度O(logN)
 */
public class GetUpMedian {


    public static int getUpMedian(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int L1 = 0, R1 = n - 1;
        int L2 = 0, R2 = n - 1;

        /**
         * arr1, arr2 are ordered => use binary search
         */
        int mid1, mid2;
        while (L1 < R1 && L2 < R2) {
            mid1 = (L1 + R1) / 2;
            mid2 = (L2 + R2) / 2;
            if (arr1[mid1] == arr2[mid2]) {
                return arr1[mid1];
            } else if (arr1[mid1] < arr2[mid2]) {
                L1 = (L1 + R1) % 2 == 0 ? mid1 : mid1 + 1;
                R2 = mid2;
            } else {
                R1 = mid1;
                L2 = (L2 + R2) % 2 == 0 ? mid2 : mid2 + 1;
            }
        }

        return arr1[L1] < arr2[L2] ? arr1[L1] : arr2[L2];
    }

    /**
     * O(logn)
     * cost 41ms
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int getUpMedianV2(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int[] a = new int[n << 1];
        // binary search, so first copy 2 arrays into one
        // does not make use of arr1 and arr2 are both ordered!
        System.arraycopy(arr1, 0, a, 0, n);
        System.arraycopy(arr2, 0, a, n, n);
        Arrays.sort(a);

        return a[n - 1];
    }

    /**
     * try to reduce sort array size, but seems no big difference
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int getUpMedianV21(int[] arr1, int[] arr2) {
        int n = arr1.length, mid;
        int[] b = new int[n + 1];
        if (n % 2 == 0) {
            // even
            mid = n / 2 - 1;
            if (arr1[mid] == arr2[mid]) {
                return arr1[mid];
            } else if (arr1[mid] < arr2[mid]) {
                // mid+2 == n/2+1
                System.arraycopy(arr1, mid, b, 0, mid + 2);
                System.arraycopy(arr2, 0, b, mid + 2, mid + 1);
            } else {
                System.arraycopy(arr1, 0, b, mid + 2, mid + 1);
                System.arraycopy(arr2, mid, b, 0, mid + 2);
            }

        } else {
            // odd
            mid = n / 2;
            if (arr1[mid] == arr2[mid]) {
                return arr1[mid];
            } else if (arr1[mid] < arr2[mid]) {
                System.arraycopy(arr1, mid, b, 0, mid + 1);
                System.arraycopy(arr2, 0, b, mid + 1, mid + 1);
            } else {
                System.arraycopy(arr2, mid, b, 0, mid + 1);
                System.arraycopy(arr1, 0, b, mid + 1, mid + 1);
            }
        }

        Arrays.sort(b);


        return b[b.length % 2 == 0 ? b.length / 2 - 1 : b.length / 2];
    }


    /**
     * this is O(n) !!
     * cost 90ms
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int getUpMedianV3(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int k = 0;
        int i = 0, j = 0;

        int median = 0;
        while (k < n) {
            if (arr1[i] <= arr2[j]) {
                median = arr1[i++];
            } else {
                median = arr2[j++];
            }
            k++;
        }

        return median;
    }

    @Test
    public void testGetUpMedian() {
        Assert.assertEquals(2, getUpMedian(new int[]{0, 1, 2}, new int[]{3, 4, 5}));
        Assert.assertEquals(17, getUpMedian(new int[]{10, 17}, new int[]{21, 38}));
        Assert.assertEquals(3, getUpMedian(new int[]{1, 2, 3, 4}, new int[]{3, 4, 5, 6}));
        Assert.assertEquals(3, getUpMedian(new int[]{1, 2, 3, 4, 5}, new int[]{-1, -2, 3, 10, 12}));
    }


}
