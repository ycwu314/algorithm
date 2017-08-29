package com.example.simple;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/8/29.
 */
public class AddIntegerNumber {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");
                String s1 = split[0];
                String s2 = split[1];

                try {
                    BigInteger a1 = new BigInteger(s1);
                    BigInteger a2 = new BigInteger(s2);
                    System.out.println(a1.add(a2).toString());
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
    }
}
