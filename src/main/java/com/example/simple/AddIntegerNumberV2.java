package com.example.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/8/29.
 */
public class AddIntegerNumberV2 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));) {
            String line = null;
            while ((line = scanner.readLine()) != null) {


                if (!checkNumbers(line)) {
                    System.out.println("error");
                    continue;
                }

                String[] split = line.split(" ");
                String s1, s2;  // s1.length() <=  s2.length()

                if (split[0].length() < split[1].length()) {
                    s1 = split[0];
                    s2 = split[1];
                } else {
                    s1 = split[1];
                    s2 = split[0];
                }

                int[] result = new int[101];
                Arrays.fill(result, -1);

                int addition = 0, sum = 0;
                int k = 0;
                while (k < s1.length()) {
                    sum = s1.charAt(s1.length() - 1 - k) - '0' + s2.charAt(s2.length() - 1 - k) - '0' + addition;
                    if (sum < 10) {
                        result[k] = sum;
                        addition = 0;
                    } else {
                        result[k] = sum - 10;
                        addition = 1;
                    }
                    k++;
                }

                while (k < s2.length()) {
                    sum = s2.charAt(s2.length() - 1 - k) - '0' + addition;
                    if (sum < 10) {
                        result[k] = sum;
                        addition = 0;
                    } else {
                        result[k] = sum - 10;
                        addition = 1;
                    }
                    k++;
                }
                if (addition == 1) {
                    result[k] = 1;
                } else {
                    k--;
                }

                while (k >= 0) {
                    System.out.print(result[k--]);
                }
                System.out.println();
            }
        }
    }

    private static boolean checkNumbers(String line) {
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (!(ch == ' ' || (ch >= '0' && ch <= '9'))) {
                return false;
            }
        }
        return true;
    }
}
