package com.example.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/9/24.
 */
public class LuckyNumbers {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bw = new BufferedReader(new InputStreamReader(System.in))) {
            String line;

            while ((line = bw.readLine()) != null) {
                int n = Integer.parseInt(line);

                int count = 0;
                for (int i = 1; i <= n; i++) {
                    // fx=
                    int s1 = 0;
                    char[] arr1 = String.valueOf(i).toCharArray();
                    for (char c : arr1) {
                        s1 += c - '0';
                    }
                    // gx=
                    char[] arr2 = Integer.toBinaryString(i).toCharArray();
                    int s2 = 0;
                    for (char c : arr2) {
                        s2 += c - '0';
                    }
                    if (s1 == s2) {
                        count++;
                    }
                }
                System.out.println(count);
            }
        }
    }


}
