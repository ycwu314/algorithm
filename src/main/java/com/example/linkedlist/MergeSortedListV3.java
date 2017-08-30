package com.example.linkedlist;

/**
 * Created by Administrator on 2017/8/30.
 * <p>
 * 这才是拼接。。。recursive
 */
public class MergeSortedListV3 {

    public static void main(String[] args) {
        MergeSortedListV3 merge = new MergeSortedListV3();
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

        ListNode head = new ListNode(0);
        ListNode l3 = head;
        doMerge(l1, l2, head);

        return l3.next;
    }

    private void doMerge(ListNode l1, ListNode l2, ListNode head) {
        if (l1 == null) {
            head.next = l2;
            return;
        } else if (l2 == null) {
            head.next = l1;
            return;
        }

        if (l1.val <= l2.val) {
            head.next = l1;
            head = head.next;
            doMerge(l1.next, l2, head);
        } else {
            head.next = l2;
            head = head.next;
            doMerge(l1, l2.next, head);
        }
    }
}
