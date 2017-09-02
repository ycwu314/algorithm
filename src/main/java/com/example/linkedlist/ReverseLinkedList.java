package com.example.linkedlist;

import org.junit.Test;

/**
 * Created by Administrator on 2017/8/31.
 * <p>
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.<br>
 * For example:
 * Given1->2->3->4->5->NULL, m = 2 and n = 4,
 * return1->4->3->2->5->NULL.
 * <p>
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedList {

    /**
     * 1->2->3->4->5->6->NULL
     * <p>
     * 假设翻转3，4，5这3个元素。
     * 那么分成A[1,2], B[3,4,5], C[6]。
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n || head == null || head.next == null) {
            return head;
        }

        m--;
        n--;

        int count = 0;
        ListNode prev = head;
        // ph用于连接翻转后B的第一个元素
        ListNode ph = null;

        while (count < m) {
            ph = prev;
            prev = prev.next;
            count++;
        }

        // pt用于连接翻转后C的第一个元素
        ListNode pt = prev;
        ListNode curr = prev.next;
        ListNode next = null;

        while (count < n) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }

        // 翻转后prev为B的第一个元素，curr为C的第一个元素
        if (ph != null) {
            ph.next = prev;
        } else {
            head = prev;
        }
        pt.next = curr;

        return head;
    }


    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // prev | curr | next
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // prev已经是第一个元素
        head = prev;
        return head;
    }

    public ListNode reverseByRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        head = doReverseByRecursion(null, head);
        return head;
    }

    private ListNode doReverseByRecursion(ListNode prev, ListNode curr) {

        if (curr == null) {
            return prev;
        }

        ListNode next = curr.next;
        curr.next = prev;

        return doReverseByRecursion(curr, next);
    }

    /////////////////////////////////////


    @Test
    public void testReverse() {
        ListNode a1 = ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5});
        ReverseLinkedList reverse = new ReverseLinkedList();
        ListNode r1 = reverse.reverse(a1);
        System.out.println(ListNodeUtil.toString(r1));
    }


    @Test
    public void testReverseByRecursion() {
        ListNode a1 = ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5});
        ReverseLinkedList reverse = new ReverseLinkedList();
        ListNode r1 = reverse.reverseByRecursion(a1);
        System.out.println(ListNodeUtil.toString(r1));
    }

    @Test
    public void testReverseBetween() {
        ReverseLinkedList reverse = new ReverseLinkedList();

        System.out.println(ListNodeUtil.toString(reverse.reverseBetween(ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), 3, 5)));

        System.out.println(ListNodeUtil.toString(reverse.reverseBetween(ListNodeUtil.createList(new int[]{}), 3, 5)));

        System.out.println(ListNodeUtil.toString(reverse.reverseBetween(ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), 3, 8)));

        System.out.println(ListNodeUtil.toString(reverse.reverseBetween(ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), 1, 8)));

        System.out.println(ListNodeUtil.toString(reverse.reverseBetween(ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), 1, 3)));

    }
}
