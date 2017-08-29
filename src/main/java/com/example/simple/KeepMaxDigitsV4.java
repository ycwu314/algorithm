package com.example.simple;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/8/28.
 * <p>
 * 给定一个十进制的正整数number，选择从里面去掉一部分数字，希望保留下来的数字组成的正整数最大。
 * <p>
 * 输入为两行内容，第一行是正整数number，1 ≤ length(number) ≤ 1000。第二行是希望去掉的数字数量cnt 1 ≤ cnt < length(number)。
 */
public class KeepMaxDigitsV4 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String number = scanner.nextLine();
                int count = scanner.nextInt();

                char CH_BLANK = 'z';
                char CH_REMOVED = 'r';
                char[] array = new char[1000];
                Arrays.fill(array, CH_BLANK);
                for (int i = 0; i < number.length(); i++) {
                    array[i] = number.charAt(i);
                }


                outer:
                while (count > 0) {

                    for (int i = 1; i < number.length(); i++) {

                        if (array[i] == CH_REMOVED) {
                            continue;
                        }
                        for (int prev = i - 1; prev >= 0; prev--) {
                            if (array[i] > array[prev]) {
                                array[prev] = CH_REMOVED;
                                count--;
                                if (count == 0) {
                                    break outer;
                                }
                            }
                        }
                    }


                    int j = 0;
                    while (j < count) {
                        array[number.length() - 1 - j] = CH_REMOVED;
                        j++;
                    }
                    break;
                }

                for (int i = 0; i < number.length(); i++) {
                    if (array[i] == CH_REMOVED) {
                        continue;
                    } else {
                        System.out.print(array[i]);
                    }
                }
            }
        }
    }
}
