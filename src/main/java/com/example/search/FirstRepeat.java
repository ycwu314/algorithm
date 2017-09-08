package com.example.search;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/8.
 */
public class FirstRepeat {
    public static char findFirstRepeat(String A, int n) {
        int[] count = new int[128];
        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);
            if (count[ch] == 1) {
                return ch;
            } else {
                count[ch]++;
            }
        }

        return '0';
    }

    @Test
    public void testFindFirstRepeat() {
        Assert.assertEquals('y', findFirstRepeat("qywyer23tdd", 11));
    }
}
