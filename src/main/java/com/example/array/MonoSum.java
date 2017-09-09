package com.example.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/9.
 * <p>
 * 现定义数组单调和为所有元素i的f(i)值之和。这里的f(i)函数定义为元素i左边(不包括其自身)小于等于它的数字之和。请设计一个高效算法，计算数组的单调和。<br>
 * <p>
 * 给定一个数组A同时给定数组的大小n，请返回数组的单调和。保证数组大小小于等于500，同时保证单调和不会超过int范围。
 */
public class MonoSum {

    public static int calcMonoSum(int[] A, int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            int s = 0;
            for (int j = 0; j < i; j++) {
                if (A[j] <= A[i]) {
                    s += A[j];
                }
            }
            sum += s;
        }
        return sum;
    }

    @Test
    public void testCalcMonoSum() {
        Assert.assertEquals(27, calcMonoSum(new int[]{1, 3, 5, 2, 4, 6}, 6));
    }
}
