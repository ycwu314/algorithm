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


    ////////////////////////////////////////

    /**
     * 求链表的中间节点，如果链表的长度为偶数，返回中间两个节点的任意一个，若为奇数，则返回中间节点。
     *
     * @param head
     * @return
     */
    public static ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode p1 = head;
        ListNode p2 = head;
        int steps, count = 1;

        while (p2 != null) {
            count *= 2;
            steps = 0;
            ListNode start = p2;
            while (steps < count && p2 != null) {
                p2 = p2.next;
                steps++;
            }

            if (p2 == null) {
                int k = (steps % 2 == 0) ? steps / 2 : (steps / 2 + 1);
                while (k-- > 0) {
                    p1 = p1.next;
                }
            } else {
                p1 = start;
            }
        }


        return p1;
    }

    @Test
    public void testFindMiddle() {
        Assert.assertEquals(1, findMiddle(ListNodeUtil.createList(new int[]{1, 2})).val);
        Assert.assertEquals(1, findMiddle(ListNodeUtil.createList(new int[]{1})).val);
        Assert.assertEquals(null, findMiddle(ListNodeUtil.createList(new int[]{})));
        Assert.assertEquals(3, findMiddle(ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5, 6})).val);
        Assert.assertEquals(5, findMiddle(ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})).val);
    }

    ////////////////////////////////////////

    /**
     * 判断链表是否有环
     *
     * @param head
     * @return
     */
    public static boolean checkCircular(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode p1 = head, p2 = head;
        while (p2 != null && p2.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;

            if (p1 == p2) {
                return true;
            }
        }
        return false;

    }

    @Test
    public void testCheckCircular() {
        Assert.assertFalse(checkCircular(ListNodeUtil.createList(new int[]{1})));
        Assert.assertFalse(checkCircular(ListNodeUtil.createList(new int[]{1, 2})));
        Assert.assertFalse(checkCircular(ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8})));

        ListNode a1 = new ListNode(1);
        a1.next = a1;
        Assert.assertTrue(checkCircular(a1));

        ListNode a11 = new ListNode(11);
        ListNode a2 = new ListNode(2);
        a11.next = a2;
        ListNode a3 = new ListNode(3);
        a2.next = a3;
        ListNode a4 = new ListNode(4);
        a4.next = a11;
        Assert.assertTrue(checkCircular(a1));
    }


    ////////////////////////////////////////

    public static ListNode findCircularEntry(ListNode head) {
        if(head==null){
            return null;
        }

        ListNode p1 = head, p2 = head;
        boolean circular = false;
        while (p2 != null && p2.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;
            if (p2 == p1) {
                circular = true;
                break;
            }

        }

        if (!circular) {
            return null;
        }

        // has circle
        p2 = head;
        while (p2 != p1) {
            p2 = p2.next;
            p1 = p1.next;
        }

        return p2;
    }


    @Test
    public void testFindCircularEntry() {
        Assert.assertNull(findCircularEntry(ListNodeUtil.createList(new int[]{})));
        Assert.assertNull(findCircularEntry(ListNodeUtil.createList(new int[]{1})));
        Assert.assertNull(findCircularEntry(ListNodeUtil.createList(new int[]{1, 2})));
        Assert.assertNull(findCircularEntry(ListNodeUtil.createList(new int[]{1, 2, 3})));

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a2;

        Assert.assertTrue(a2== findCircularEntry(a1));

    }
}
