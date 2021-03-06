package com.example.linkedlist;

/**
 * Created by Administrator on 2017/8/30.
 * <p>
 * 这才是拼接。。。
 */
public class MergeSortedListV2 {

    public static void main(String[] args) {
        MergeSortedListV2 merge = new MergeSortedListV2();
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


        while (l1 != null && l2 != null) {

            if (l1.val <= l2.val) {
                head.next = l1;
                head = head.next;
                l1 = l1.next;
            } else {
                head.next = l2;
                head = head.next;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            head.next = l1;
        } else if (l2 != null) {
            head.next = l2;
        }

        return l3.next;
    }
}
