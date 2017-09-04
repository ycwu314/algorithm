package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Administrator on 2017/9/4.
 */
public class ConvertNumber {


    public static int hexToTen(String hex) {
        // cant handle 0x prefix
        if (hex.startsWith("0x")) {
            return Integer.parseInt(hex.substring(2), 16);
        }
        return Integer.parseInt(hex, 16);
    }

    @Test
    public void testHexToTen() {
        Assert.assertEquals(8, hexToTen("8"));
        Assert.assertEquals(8, hexToTen("08"));
        Assert.assertEquals(10, hexToTen("0xA"));
        Assert.assertEquals(10, hexToTen("A"));
        Assert.assertEquals(10, hexToTen("a"));
    }

    /**
     * 秦九韶算法
     *
     * @param hex
     * @return
     */
    public static int hexToTenV2(String hex) {

        int num = 0;
        for (int i = hex.startsWith("0x") ? 2 : 0; i < hex.length(); i++) {
            char ch = hex.charAt(i);
            if (ch >= '0' && ch <= '9') {
                num = 16 * num + (ch - '0');
            } else if (ch >= 'A' && ch <= 'F') {
                num = 16 * num + (ch - 'A') + 10;
            } else if (ch >= 'a' && ch <= 'f') {
                num = 16 * num + (ch - 'a') + 10;
            }
        }

        return num;
    }

    @Test
    public void testHexToTenV2() {
        Assert.assertEquals(8, hexToTenV2("8"));
        Assert.assertEquals(8, hexToTenV2("08"));
        Assert.assertEquals(10, hexToTenV2("0xA"));
        Assert.assertEquals(10, hexToTenV2("A"));
        Assert.assertEquals(10, hexToTenV2("a"));
        Assert.assertEquals(627, hexToTenV2("273"));
    }

    /////////////////////////////

    public static int binToTen(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = sum * 2 + s.charAt(i) - '0';
        }

        return sum;
    }

    @Test
    public void testBinToTen() {
        Assert.assertEquals(15, binToTen("1111"));
        Assert.assertEquals(15, binToTen("001111"));
        Assert.assertEquals(487, binToTen("111100111"));
    }

    /////////////////////////////

    /**
     * 辗转相除法
     *
     * @param num
     * @return
     */
    public static String tenToBinary(int num) {
        Stack<Character> stack = new Stack<>();
        int n = num, r = 0;
        while (n > 0) {
            r = n % 2;
            stack.push(r == 1 ? '1' : '0');
            n = n / 2;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    @Test
    public void testTenToBinary() {
        Assert.assertEquals("1111", tenToBinary(15));
        Assert.assertEquals("1100", tenToBinary(12));
    }

    public static String tenToHex(int num) {
        Stack<Character> stack = new Stack<>();
        int n = num, r = 0;
        while (n > 0) {
            r = n % 16;
            n = n / 16;
            stack.push(r < 10 ? (char) ('0' + r) : (char) (r - 10 + 'A'));
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    @Test
    public void testTenToHex(){
        Assert.assertEquals("2D", tenToHex(45));
    }
}
