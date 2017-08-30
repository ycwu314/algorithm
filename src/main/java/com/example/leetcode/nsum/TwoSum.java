package com.example.leetcode.nsum;

import java.util.*;

/**
 * Created by Administrator on 2017/8/29.
 * <p>
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.<br>
 * You may assume that each input would have exactly one solution.<br>
 * Input: numbers={2, 7, 11, 15}, target=9<br>
 * Output: index1=1, index2=2<br>
 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twoSum(new int[]{0, 4, 3, 0}, 0);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            List<Integer> indice = map.get(numbers[i]);
            if (indice == null) {
                indice = new ArrayList<>();
                indice.add(i);
                map.put(numbers[i], indice);
            } else {
                indice.add(i);
            }
        }

        int k = 0, k2 = -1;
        for (int i = 0; i < numbers.length; i++) {
            int remain = target - numbers[i];
            List<Integer> anotherPart = map.get(remain);
            if (anotherPart != null) {
                k = i;
                if (remain == numbers[i]) {
                    // case: 6=3+3
                    for (int j : anotherPart) {
                        if (j != k) {
                            k2 = j;
                            break;
                        }
                    }
                } else {
                    k2 = anotherPart.get(0);
                    break;
                }
            }
        }

        int[] result = new int[]{k + 1, k2 + 1};
        if (result[0] > result[1]) {
            int t = result[0];
            result[0] = result[1];
            result[1] = t;
        }
        return result;
    }
}
