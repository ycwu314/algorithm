package com.example.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/8/28.
 * <p>
 * “水仙花数”是指一个三位数，它的各位数字的立方和等于其本身，比如：153=1^3+5^3+3^3。 现在要求输出所有在m和n范围内的水仙花数。
 */
public class ShuiXianHuaNumber {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextInt()) {
                int m = scanner.nextInt();
                int n = scanner.nextInt();

                List<Integer> result = new ArrayList<>();
                for (int i = m; i <= n; i++) {
                    int i0 = i % 10;
                    int i1 = (i / 10) % 10;
                    int i2 = (i / 10) / 10;

                    if ((i0 * i0 * i0 + i1 * i1 * i1 + i2 * i2 * i2) == i) {
                        result.add(i);
                    }
                }

                if (result.isEmpty()) {
                    System.out.println("no");
                } else {
                    for (int i = 0, len = result.size() - 1; i <= len; i++) {
                        System.out.print(result.get(i));
                        if (i != len) {
                            System.out.print(' ');
                        } else {
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}
