package com.example.simple;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/8/28.
 * <p>
 * 给定一个句子（只包含字母和空格）， 将句子中的单词位置反转，单词用空格分割, 单词之间只有一个空格，前后没有空格。 比如： （1） “hello xiao mi”-> “mi xiao hello”
 */
public class ReverseWords {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                StringBuilder sb = new StringBuilder();
                for (int i = words.length - 1; i > -1; i--) {
                    sb.append(words[i]).append(' ');
                }
                sb.deleteCharAt(sb.length() - 1);
                System.out.println(sb.toString());
            }
        }
    }
}
