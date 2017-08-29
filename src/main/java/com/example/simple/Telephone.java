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
public class Telephone {

    public static void main(String[] args) {

        final String[] EIGHT = new String[]{"E", "I", "G", "H", "T"};
        final String[] ZERO = new String[]{"Z", "E", "R", "O"};
        final String[] TWO = new String[]{"T", "W", "O"};
        final String[] SIX = new String[]{"S", "I", "X"};
        final String[] SEVEN = new String[]{"S", "E", "V", "E", "N"};
        final String[] THREE = new String[]{"T", "H", "R", "E", "E"};
        final String[] FIVE = new String[]{"F", "I", "V", "E"};
        final String[] FOUR = new String[]{"F", "O", "U", "R"};
        final String[] ONE = new String[]{"O", "N", "E"};
        final String[] NINE = new String[]{"N", "I", "N", "E"};

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                int t = scanner.nextInt();
                Map<String, Integer> chars = new HashMap<>();
                List<String> result = new ArrayList<>();
                for (int i = 0; i < t; i++) {
                    String line = scanner.next();
                    for (int j = 0; j < line.length(); j++) {
                        String key = "" + line.charAt(j);
                        Integer count = chars.get(key);
                        if (count == null) {
                            chars.put(key, 1);
                        } else {
                            chars.put(key, count + 1);
                        }
                    }

                    List<Integer> codes = new ArrayList<>();
                    while (chars.size() > 0) {

                        if (chars.get("G") != null) {
                            removeLetter(chars, EIGHT);
                            codes.add(0);
                        } else if (chars.get("Z") != null) {
                            removeLetter(chars, ZERO);
                            codes.add(2);
                        } else if (chars.get("W") != null) {
                            removeLetter(chars, TWO);
                            codes.add(4);
                        } else if (chars.get("X") != null) {
                            removeLetter(chars, SIX);
                            codes.add(8);
                        } else if (chars.get("S") != null) {
                            removeLetter(chars, SEVEN);
                            codes.add(9);
                        } else if (chars.get("T") != null) {
                            removeLetter(chars, THREE);
                            codes.add(5);
                        } else if (chars.get("V") != null) {
                            removeLetter(chars, FIVE);
                            codes.add(7);
                        } else if (chars.get("R") != null) {
                            removeLetter(chars, FOUR);
                            codes.add(6);
                        } else if (chars.get("O") != null) {
                            removeLetter(chars, ONE);
                            codes.add(3);
                        } else {
                            // 9
                            removeLetter(chars, NINE);
                            codes.add(1);
                        }
                    }

                    Collections.sort(codes);

                    StringBuilder sb = new StringBuilder();
                    for (int num : codes) {
                        sb.append(num);
                    }
                    result.add(sb.toString());
                }

                for (String s : result) {
                    System.out.println(s);
                }


            }
        }
    }

    private static void removeLetter(Map<String, Integer> chars, String[] letters) {
        for (String letter : letters) {
            Integer count = chars.get(letter);

            if (count - 1 == 0) {
                chars.remove(letter);
            } else {
                chars.put(letter, count - 1);
            }
        }
    }


}
