package com.example.misc;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/8.
 */
public class CountBitDiff {

    /**
     * 获得两个整形二进制表达位数不同的数量
     *
     * @param m 整数m
     * @param n 整数n
     * @return 整型
     */
    public int countBitDiff(int m, int n) {
        // XOR get the diff bits. 0^1=1, 0^0=0, 1^1=0
        // 15=(1111), 5=(101), 15^5=(1010)
        int diff = m ^ n;
        int c = 0;
        // find how many 1s in k
        while (diff > 0) {
            diff &= (diff - 1);
            c++;
        }
        return c;
    }

    @Test
    public void testCountBitDiff() {
        Assert.assertEquals(2, countBitDiff(15, 5));
    }

}
