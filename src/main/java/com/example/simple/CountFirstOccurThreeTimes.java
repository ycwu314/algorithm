package com.example.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/8/28.
 * 给定一个英文字符串,请写一段代码找出这个字符串中首先出现三次的那个英文字符。
 */
public class CountFirstOccurThreeTimes {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                char[] array = line.toCharArray();
                for (int i = 0; i < array.length; i++) {
                    char ch = array[i];
                    if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                        continue;
                    }

                    String key = ch + "";
                    Integer count = map.get(key);
                    if (count == null) {
                        map.put(key, 1);
                    } else if (count >= 2) {
                        System.out.println(key);
                        map.clear();
                        break;
                    } else {
                        map.put(key, count + 1);
                    }
                }
            }
        }
    }
}
