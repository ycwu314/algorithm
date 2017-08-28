package com.example.simple;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/8/29.
 *
 * 数列的第一项为n，以后各项为前一项的平方根，求数列的前m项的和。
 */
public class SumOfSqrtItems {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                int m = scanner.nextInt();

                double sum = n;
                double k = n;
                for (int i = 1; i < m; i++) {
                    k = Math.sqrt(k);
                    sum = sum + k;
                }

                System.out.printf("%.2f\n", sum);

            }
        }
    }
}
