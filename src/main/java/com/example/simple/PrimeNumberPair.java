package com.example.simple;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 * <p>
 * 给定一个正整数，编写程序计算有多少对质数的和等于输入的这个正整数，并输出结果。输入值小于1000。
 * 如，输入为10, 程序应该输出结果为2。（共有两对质数的和为10,分别为(5,5),(3,7)）
 */
public class PrimeNumberPair {

    public static void main(String[] args) {
        try (BufferedReader bw = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bw.readLine().trim());
            List<Integer> primeList = new ArrayList<>();
            for (int i = 2; i <= n; i++) {
                if (isPrime(i)) {
                    primeList.add(i);
                }
            }

            int count = 0;
            for (int i = 0, j = primeList.size() - 1; i <= j; ) {
                int sum = primeList.get(i) + primeList.get(j);
                if (sum == n) {
                    count++;
                    i++;
                    j--;
                } else if (sum < n) {
                    i++;
                } else {
                    // sum>n
                    j--;
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrime(int i) {
        if (i == 1) {
            return false;
        }

        int m = (int) Math.sqrt(i);
        int count = 0;
        for (int j = 2; j <= m; j++) {
            if (i % j == 0) {
                if (++count > 0) {
                    break;
                }
            }
        }
        return count == 0;
    }

    @Test
    public void testIsPrime() {
        Assert.assertTrue(isPrime(2));
        Assert.assertTrue(isPrime(13));
        Assert.assertFalse(isPrime(1));
        Assert.assertFalse(isPrime(4));
        Assert.assertFalse(isPrime(9));
    }


}
