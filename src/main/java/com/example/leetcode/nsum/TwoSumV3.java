package com.example.leetcode.nsum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/29.
 * <p>
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.<br>
 * You may assume that each input would have exactly one solution.<br>
 * Input: numbers={2, 7, 11, 15}, target=9<br>
 * Output: index1=1, index2=2<br>
 */
public class TwoSumV3 {

    public static void main(String[] args) {
        TwoSumV3 twoSum = new TwoSumV3();

        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{0, 4, 3, 0}, 0)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{0, 0, 0, 6, 3}, 0)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{-3, 4, 3, 90}, 0)));

    }

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        // <key,value>:  <target-numbers[i], index>
        // 比起传统方法，少了对原始数据排序
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.get(numbers[i]) != null) {
                result[0] = map.get(numbers[i]) + 1;
                result[1] = i + 1;
                break;
            } else {
                map.put(target - numbers[i], i);
            }
        }
        return result;
    }
}
