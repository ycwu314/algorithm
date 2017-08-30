package com.example.leetcode.nsum;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/8/30.
 * <p>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? <br>
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)<br>
 * The solution set must not contain duplicate triplets.
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4}).toString());
        System.out.println(threeSum.threeSum(new int[]{0, -4, -1, -4, -2, -3, 2}).toString());
        System.out.println(threeSum.threeSum(new int[]{-2, 0, 1, 1, 2}).toString());

    }

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            // avoid produces duplicated result
            if ((i > 0 && num[i] == num[i - 1])) {
                continue;
            }
            // no need continue searching
            if (num[i] > 0) {
                break;
            }

            int m = i + 1, n = num.length - 1;
            while (m < n) {
                int k = num[i] + num[m] + num[n];
                if (k > 0) {
                    n--;
                } else if (k < 0) {
                    m++;
                } else {
                    // match
                    ArrayList<Integer> res = new ArrayList<>();
                    res.add(num[i]);
                    res.add(num[m]);
                    res.add(num[n]);
                    result.add(res);

                    // handle duplicated values
                    while (m < n && num[m] == num[m + 1]) {
                        m++;
                    }
                    while (m < n && num[n] == num[n - 1]) {
                        n--;
                    }

                    m++;
                    n--;
                }
            }
        }

        return result;
    }
}
