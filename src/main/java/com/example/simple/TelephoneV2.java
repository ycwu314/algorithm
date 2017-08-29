package com.example.simple;

import java.util.*;

/**
 * Created by Administrator on 2017/8/29.
 * <p>
 * 题目描述
 * 继MIUI8推出手机分身功能之后，MIUI9计划推出一个电话号码分身的功能：首先将电话号码中的每个数字加上8取个位，然后使用对应的大写字母代替 （"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"）， 然后随机打乱这些字母，所生成的字符串即为电话号码对应的分身。
 * 输入描述:
 * 第一行是一个整数T（1 ≤ T ≤ 100)表示测试样例数；接下来T行，每行给定一个分身后的电话号码的分身（长度在3到10000之间）。
 * <p>
 * <p>
 * code-8=origin
 * <p>
 * (g)eight 8 -> 0
 * nine  9 -> 1
 * (z)zero  0 -> 2
 * one   1 -> 3   (when four,zero,two gone, O is unique for one)
 * (w)two   2 -> 4
 * three 3 -> 5  (when eight & two are gone, T is unique for three)
 * four  4 -> 6  (when three is gone, R is unique for four)
 * five  5 -> 7  (when seven is gone, V is unique for five)
 * (x)six   6 -> 8
 * seven 7 -> 9 (when six is gone, S is unique for seven)
 */
public class TelephoneV2 {

    public static void main(String[] args) {

        final char[] EIGHT = new char[]{'E', 'I', 'G', 'H', 'T'};
        final char[] ZERO = new char[]{'Z', 'E', 'R', 'O'};
        final char[] TWO = new char[]{'T', 'W', 'O'};
        final char[] SIX = new char[]{'S', 'I', 'X'};
        final char[] SEVEN = new char[]{'S', 'E', 'V', 'E', 'N'};
        final char[] THREE = new char[]{'T', 'H', 'R', 'E', 'E'};
        final char[] FIVE = new char[]{'F', 'I', 'V', 'E'};
        final char[] FOUR = new char[]{'F', 'O', 'U', 'R'};
        final char[] ONE = new char[]{'O', 'N', 'E'};
        final char[] NINE = new char[]{'N', 'I', 'N', 'E'};

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                int t = scanner.nextInt();

                // 替换map，减少内存消耗
                int[] chars = new int[26];
                // bucket sort
                int[] numbers = new int[10];

                List<String> result = new ArrayList<>();
                for (int i = 0; i < t; i++) {
                    String line = scanner.next();
                    Arrays.fill(chars, 0);
                    Arrays.fill(numbers, 0);

                    for (int j = 0; j < line.length(); j++) {
                        chars[line.charAt(j) - 'A']++;
                    }


                    while (chars['G' - 'A'] > 0) {
                        removeLetter(chars, EIGHT);
                        numbers[0]++;
                    }
                    while (chars['Z' - 'A'] > 0) {
                        removeLetter(chars, ZERO);
                        numbers[2]++;
                    }
                    while (chars['W' - 'A'] > 0) {
                        removeLetter(chars, TWO);
                        numbers[4]++;
                    }
                    while (chars['X' - 'A'] > 0) {
                        removeLetter(chars, SIX);
                        numbers[8]++;
                    }
                    while (chars['S' - 'A'] > 0) {
                        removeLetter(chars, SEVEN);
                        numbers[9]++;
                    }
                    while (chars['T' - 'A'] > 0) {
                        removeLetter(chars, THREE);
                        numbers[5]++;
                    }
                    while (chars['V' - 'A'] > 0) {
                        removeLetter(chars, FIVE);
                        numbers[7]++;
                    }
                    while (chars['R' - 'A'] > 0) {
                        removeLetter(chars, FOUR);
                        numbers[6]++;
                    }
                    while (chars['O' - 'A'] > 0) {
                        removeLetter(chars, ONE);
                        numbers[3]++;
                    }
                    while (chars['N' - 'A'] > 0) {
                        removeLetter(chars, NINE);
                        numbers[1]++;
                    }


                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < numbers.length; j++) {
                        if (numbers[j] > 0) {
                            for (int k = 0; k < numbers[j]; k++) {
                                sb.append(j);
                            }
                        }
                    }

                    result.add(sb.toString());
                }

                for (String s : result) {
                    System.out.println(s);
                }


            }
        }
    }


    private static void removeLetter(int[] chars, char[] letters) {
        for (char letter : letters) {
            chars[letter - 'A']--;
        }
    }


}
