package com.example.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/24.
 */
public class ArrayUtil {

    public static Integer[] toIntegerArray(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for (int i : arr) {
            res.add(i);
        }
        return res.toArray(new Integer[0]);
    }
}
