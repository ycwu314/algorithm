package com.example.misc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/9/24.
 * <p>
 * <pre>
 *     现在有一个数组，其值为从1到10000的连续增长的数字。
 *     出于某次偶然操作，导致这个数组中丢失了某三个元素，同时顺序被打乱，
 *     现在需要你用最快的方法找出丢失的这三个元素，并且将这三个元素根据从小到大重新拼接为一个新数字，计算其除以7的余数。
 *     例：丢失的元素为336，10，8435，得到的新数字为103368435，除以七的余数为2。
 * </pre>
 */
public class MissingNumbers {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {

            boolean[] map = new boolean[10001];
            // scanner.nextInt() is slow
            String line = scanner.nextLine();
            for (String s : line.split(" ")) {
                map[Integer.parseInt(s)] = true;
            }

            int[] arr = new int[3];

            for (int i = 1, j = 0; i < map.length; i++) {
                if (!map[i]) {
                    arr[j++] = i;
                    if (j == 3) {
                        break;
                    }
                }
            }

            Arrays.sort(arr);
            long sum = Long.parseLong(arr[0] + "" + arr[1] + "" + arr[2]);
            System.out.println(sum % 7);
        }

    }
}
