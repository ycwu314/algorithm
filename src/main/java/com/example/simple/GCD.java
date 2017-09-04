package com.example.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/4.
 * <p>
 * greatest common divisor
 */
public class GCD {

    ////////////////////////////////

    /**
     * 欧几里德算法
     *
     * @param a
     * @param b
     * @return
     */

    public static int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        // make sure a > b
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }

        int r = 1;
        while (r > 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static int gcdByRecursive(int a, int b) {
        if (a == b) {
            return a;
        }
        // make sure a > b
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }

        return gcdRecursiveInternal(a, b);

    }

    private static int gcdRecursiveInternal(int a, int b) {
        int c = a % b;
        return c == 0 ? b : gcdRecursiveInternal(b, c);
    }

    @Test
    public void testGcd() {
        Assert.assertEquals(5, gcd(25, 10));
        Assert.assertEquals(1, gcd(25, 13));
        Assert.assertEquals(13, gcd(26, 13));
    }

    @Test
    public void testGcdByRecursive() {
        Assert.assertEquals(5, gcdByRecursive(25, 10));
        Assert.assertEquals(1, gcdByRecursive(25, 13));
        Assert.assertEquals(13, gcdByRecursive(26, 13));
    }

    ////////////////////////////////

    /**
     * 辗转相除法
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcdV2(int a, int b) {
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }

        int c;
        while (a != b) {
            c = a - b;
            if (c > b) {
                a = c;
            } else {
                a = b;
                b = c;
            }
        }

        return a;
    }

    public static int gcdV2ByRecursive(int a, int b) {
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }

        return gcdV2RecursiveInternal(a, b);
    }

    private static int gcdV2RecursiveInternal(int a, int b) {
        if (a == b) {
            return a;
        }

        int c = a - b;
        if (c > b) {
            return gcdV2RecursiveInternal(c, b);
        } else {
            return gcdV2RecursiveInternal(b, c);
        }
    }


    @Test
    public void testGcdV2() {
        Assert.assertEquals(5, gcdV2(5, 10));
        Assert.assertEquals(1, gcdV2(5, 11));
        Assert.assertEquals(3, gcdV2(3, 12));
        Assert.assertEquals(4, gcdV2(8, 12));
    }

    @Test
    public void testGcdV2Recursive() {
        Assert.assertEquals(5, gcdV2ByRecursive(5, 10));
        Assert.assertEquals(1, gcdV2ByRecursive(5, 11));
        Assert.assertEquals(3, gcdV2ByRecursive(3, 12));
        Assert.assertEquals(4, gcdV2ByRecursive(8, 12));
    }

    ////////////////////
    public static int gcdV3(int a, int b) {
        // how many 2s
        int k = 1;
        while (a != b) {

            if ((a & 1) == 0 && (b & 1) == 0) {
                a >>= 1;
                b >>= 1;
                k <<= 1;
            } else if ((a & 1) != 0 && (b & 1) == 0) {
                b >>= 1;
            } else if ((a & 1) == 0 && (b & 1) != 0) {
                a >>= 1;
            } else {
                // both a,b are odd

                // make sure a always >= b
                int t;
                if (a < b) {
                    t = a;
                    a = b;
                    b = t;
                }

                t = a - b;
                if (t > b) {
                    a = t;
                } else {
                    a = b;
                    b = t;
                }

            }

        }

        return a * k;
    }

    public static int gcdV3Recursive(int a, int b) {
        if (a == b) {
            return a;
        }

        if ((a & 1) == 0 && (b & 1) == 0) {
            return gcdV3Recursive(a >> 1, b >> 1) << 1;
        } else if ((a & 1) == 0 && (b & 1) != 0) {
            return gcdV3Recursive(a >> 1, b);
        } else if ((a & 1) != 0 && (b & 1) == 0) {
            return gcdV3Recursive(a, b >> 1);
        } else {
            if (a < b) {
                int t = a;
                a = b;
                b = t;
            }

            return gcdV3Recursive(b, a - b);
        }
    }

    @Test
    public void testGcdV3() {
        Assert.assertEquals(5, gcdV3(5, 10));
        Assert.assertEquals(1, gcdV3(5, 11));
        Assert.assertEquals(3, gcdV3(3, 12));
        Assert.assertEquals(4, gcdV3(8, 12));
    }

    @Test
    public void testGcdV3Recursive() {
        Assert.assertEquals(5, gcdV3Recursive(5, 10));
        Assert.assertEquals(1, gcdV3Recursive(5, 11));
        Assert.assertEquals(3, gcdV3Recursive(3, 12));
        Assert.assertEquals(4, gcdV3Recursive(8, 12));
    }

}
