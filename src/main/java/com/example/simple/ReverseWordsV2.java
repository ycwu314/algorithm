package com.example.simple;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Administrator on 2017/8/28.
 * <p>
 * 给定一个句子（只包含字母和空格）， 将句子中的单词位置反转，单词用空格分割, 单词之间只有一个空格，前后没有空格。 比如： （1） “hello xiao mi”-> “mi xiao hello”
 */
public class ReverseWordsV2 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Stack<Character> stack = new Stack<>();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                StringBuilder sb = new StringBuilder();
                for (int i = line.length() - 1; i >= 0; i--) {
                    char ch = line.charAt(i);
                    if (ch == ' ') {
                        while (!stack.isEmpty()) {
                            sb.append(stack.pop());
                        }
                        sb.append(' ');
                    } else {
                        stack.push(ch);
                    }
                }
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                System.out.println(sb.toString());
            }
        }
    }
}
