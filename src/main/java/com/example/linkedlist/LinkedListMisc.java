package com.example.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/8/31.
 */
public class LinkedListMisc {

    /**
     * 在O(1)时间删除链表节点
     *
     * @param target
     */
    public void deleteOneNode(ListNode target) {
        // cant not delete the tail node in such way
        if (target == null || target.next == null) {
            return;
        }

        ListNode next = target.next;
        target.val = next.val;
        target.next = next.next;

        next.next = null;
    }

    @Test
    public void testDeleteOneNode() {
        ListNode a1 = ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5});
        LinkedListMisc misc = new LinkedListMisc();
        ListNode target = a1.next.next;
        System.out.println("delete " + target.val);
        misc.deleteOneNode(target);
        System.out.println(ListNodeUtil.toString(a1));
    }


    /////////////////////////

    /**
     * let k=0 means the tail node
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getLastKNode(ListNode head, int k) {
        if (head == null || head.next == null || k < 0) {
            return null;
        }

        int count = 0;
        ListNode p1 = head;
        ListNode p2 = head;

        while (count++ < k && p2 != null) {
            p2 = p2.next;
        }

        // consider k > length(head)
        if (p2 == null) {
            return null;
        }
        // now pos(p2)-pos(p1)=k

        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    @Test
    public void testGetLastKNode() {
        LinkedListMisc misc = new LinkedListMisc();
        ListNode a1 = ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5});
        Assert.assertEquals(5, misc.getLastKNode(a1, 0).val);
        Assert.assertEquals(1, misc.getLastKNode(a1, 4).val);

        Assert.assertEquals(null, misc.getLastKNode(a1, 10));
    }


    /**
     * let k=1 means the tail node
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getLastKNode2(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return null;
        }

        int count = 0;
        ListNode p1 = head;
        ListNode p2 = head;

        while (count++ < k && p2 != null) {
            p2 = p2.next;
        }

        // consider k > length(head)
        if (p2 == null) {
            return null;
        }
        // now pos(p2)-pos(p1)=k

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    @Test
    public void testGetLastKNode2() {
        LinkedListMisc misc = new LinkedListMisc();
        ListNode a1 = ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5});
        Assert.assertEquals(null, misc.getLastKNode2(a1, 0));
        Assert.assertEquals(5, misc.getLastKNode2(a1, 1).val);
        Assert.assertEquals(4, misc.getLastKNode2(a1, 2).val);
        Assert.assertEquals(null, misc.getLastKNode2(a1, 10));

        Assert.assertEquals(2, misc.getLastKNode2(ListNodeUtil.createList(new int[]{1, 2}), 1).val);

    }
}
