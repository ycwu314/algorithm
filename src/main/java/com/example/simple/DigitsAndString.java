package com.example.simple;

import java.util.*;

/**
 * Created by Administrator on 2017/8/28.
 * <p>
 * 设有n个正整数，将他们连接成一排，组成一个最大的多位整数。
 * 如:n=3时，3个整数13,312,343,连成的最大整数为34331213。
 * 如:n=4时,4个整数7,13,4,246连接成的最大整数为7424613。
 */
public class DigitsAndString {

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            while (scanner.hasNextInt()) {
                int count = scanner.nextInt();

                List<Integer> data = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    data.add(scanner.nextInt());
                }

                Collections.sort(data, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        int i1 = Integer.parseInt(o1 + "" + o2);
                        int i2 = Integer.parseInt(o2 + "" + o1);
                        return i1 - i2;
                    }
                });

                StringBuilder sb = new StringBuilder();
                for (int i = data.size() - 1; i > -1; i--) {
                    sb.append(data.get(i));
                }

                System.out.println(sb.toString());
            }


        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
