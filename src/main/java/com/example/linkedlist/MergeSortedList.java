package com.example.linkedlist;

/**
 * Created by Administrator on 2017/8/30.
 *
 * 这不是拼接。。。
 */
public class MergeSortedList {

    public static void main(String[] args) {
        MergeSortedList merge = new MergeSortedList();
        System.out.println(ListNodeUtil.toString(merge.mergeTwoLists(ListNodeUtil.createList(new int[]{5}), ListNodeUtil.createList(new int[]{1, 2, 4}))));
        System.out.println(ListNodeUtil.toString(merge.mergeTwoLists(ListNodeUtil.createList(new int[]{-9, 3}), ListNodeUtil.createList(new int[]{5, 7}))));
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode l3 = new ListNode(0);
        ListNode head = l3;
        if (l1.val <= l2.val) {
            head.val = l1.val;
            l1 = l1.next;
        } else {
            head.val = l2.val;
            l2 = l2.next;
        }

        outer:
        while (l1 != null && l2 != null) {
            while (l1.val <= l2.val) {
                ListNode next = new ListNode(l1.val);
                head.next = next;
                head = next;
                l1 = l1.next;

                if (l1 == null) {
                    break outer;
                }
            }
            while (l2.val <= l1.val) {
                ListNode next = new ListNode(l2.val);
                head.next = next;
                head = next;
                l2 = l2.next;
                if (l2 == null) {
                    break outer;
                }
            }
        }

        // process remains
        while (l1 != null) {
            ListNode next = new ListNode(l1.val);
            head.next = next;
            head = next;
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode next = new ListNode(l2.val);
            head.next = next;
            head = next;
            l2 = l2.next;
        }


        return l3;
    }
}
