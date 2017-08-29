package com.example.simple;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/8/28.
 * <p>
 * 给定一个十进制的正整数number，选择从里面去掉一部分数字，希望保留下来的数字组成的正整数最大。
 * <p>
 * 输入为两行内容，第一行是正整数number，1 ≤ length(number) ≤ 1000。第二行是希望去掉的数字数量cnt 1 ≤ cnt < length(number)。
 */
public class KeepMaxDigitsV3 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String number = scanner.nextLine();
                int count = scanner.nextInt();

                LinkedList<Integer> stack = new LinkedList<>();

                for (int i = 0; i < count; i++) {

                    boolean found = false;
                    int last = -1;
                    for (int k = 0; k < number.length() - 1; k++) {

                        if (stack.isEmpty()) {
                            stack.push(Integer.parseInt(number.charAt(k) + ""));
                        }
                        int prev = stack.pop();
                        int next = Integer.parseInt(number.charAt(k + 1) + "");
                        // 88991117
                        // 11111
                        // 54321
                        // 12345
                        if (next > prev) {
                            // found
                            stack.push(next);
                            last = k + 2;
                            found = true;
                            break;
                        } else {
                            stack.push(prev);
                            stack.push(next);
                        }
                    }

                    if (!found) {
                        stack.pop();
                    } else {
                        for (int m = last; m < number.length(); m++) {
                            stack.push(Integer.parseInt(number.charAt(m) + ""));
                        }
                    }

                    Iterator<Integer> it = stack.descendingIterator();

                    StringBuilder sb = new StringBuilder();
                    while (it.hasNext()) {
                        sb.append(it.next());
                    }
                    number = sb.toString();
                    stack.clear();
                }

                System.out.println(number);
            }
        }
    }
}
