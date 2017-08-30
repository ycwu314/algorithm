package com.example.leetcode.nsum;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/8/29.
 * <p>
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.<br>
 * You may assume that each input would have exactly one solution.<br>
 * Input: numbers={2, 7, 11, 15}, target=9<br>
 * Output: index1=1, index2=2<br>
 */
public class TwoSumV2 {

    public static void main(String[] args) {
        TwoSumV2 twoSum = new TwoSumV2();

        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{0, 4, 3, 0}, 0)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{0, 0, 0, 6, 3}, 0)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{-3, 4, 3, 90}, 0)));

    }

    public int[] twoSum(int[] numbers, int target) {
        int[] array = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(array);
        int i = 0, j = array.length - 1, m1 = -1, m2 = -1;
        while (true) {
            if (i >= j) {
                break;
            }
            int k = array[i] + array[j];
            if (k > target) {
                j--;
            } else if (k < target) {
                i++;
            } else {
                // found
                int a1 = array[i];
                int a2 = array[j];

                if (a1 != a2) {
                    for (int p = 0; p < numbers.length; p++) {
                        if (numbers[p] == a1) {
                            m1 = p;
                            break;
                        }
                    }

                    for (int p = 0; p < numbers.length; p++) {
                        if (numbers[p] == a2) {
                            m2 = p;
                            break;
                        }
                    }
                } else {
                    // case: 6=3+3
                    // case: [0,0,0,6,3], target=0

                    for (int p = 0; p < numbers.length; p++) {
                        if (numbers[p] == a1) {
                            m1 = p;
                            break;
                        }
                    }

                    for (int p = m1 + 1; p < numbers.length; p++) {
                        if (numbers[p] == a1) {
                            m2 = p;
                            break;
                        }
                    }
                }
                break;
            }
        }

        int[] result = new int[2];
        if (m1 < m2) {
            result[0] = m1 + 1;
            result[1] = m2 + 1;
        } else {
            result[0] = m2 + 1;
            result[1] = m1 + 1;
        }
        return result;
    }
}
