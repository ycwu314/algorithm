package com.example.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */
public class ListNodeUtil {

    public static ListNode createList(int[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        ListNode head = new ListNode(0);
        ListNode list = head;
        head.val = a[0];

        for (int i = 1; i < a.length; i++) {
            ListNode n = new ListNode(a[i]);
            head.next = n;
            head = n;
        }

        return list;
    }

    public static String toString(ListNode list) {
        List<Integer> a = new ArrayList<>();
        ListNode head = list;
        while (head != null) {
            a.add(head.val);
            head = head.next;
        }
        return a.toString();
    }
}
