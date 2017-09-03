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
    public static void deleteOneNode(ListNode target) {
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
        ListNode target = a1.next.next; // delete node[val=3]
        deleteOneNode(target);

        ListNode n = a1;
        while (n != null) {
            if (n.val == 3) {
                Assert.fail();
            }
            n = n.next;
        }

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

        // fast tries to move to 2^n position
        ListNode fast = head;
        // when fast reaches 2^n, slow moves to 2^(n-1), which is previous round start position of fast
        ListNode slow = head;
        // 2^k + steps = 2^n, where k=n-1 or k=n
        int steps, count = 1;

        while (fast != null) {
            count *= 2;
            steps = 0;
            ListNode start = fast;
            while (steps < count && fast != null) {
                fast = fast.next;
                steps++;
            }

            if (fast == null) {
                int k = (steps % 2 == 0) ? steps / 2 : (steps / 2 + 1);
                while (k-- > 0) {
                    slow = slow.next;
                }
            } else {
                slow = start;
            }
        }


        return slow;
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

        // slow moves 1 node for each time,
        // fast move2 2 node for each time
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
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

    /**
     * 链表环的入口
     *
     * @param head
     * @return
     */
    public static ListNode findCircularEntry(ListNode head) {
        if (head == null) {
            return null;
        }

        // slow moves 1 node for each time,
        // fast move2 2 node for each time
        ListNode slow = head, fast = head;
        boolean circular = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                circular = true;
                break;
            }

        }

        if (!circular) {
            return null;
        }

        // has circle, reset fast pointer, and moves 1 node for each time
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
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

        Assert.assertTrue(a2 == findCircularEntry(a1));

    }

    ////////////////////////////////////////

    /**
     * 两个无环链表是否相交
     *
     * @param a1
     * @param a2
     */
    public static boolean checkCrossWithoutCircle(ListNode a1, ListNode a2) {
        if (a1 == null || a2 == null) {
            return false;
        }

        ListNode t1 = a1, t2 = a2;
        while (t1 != null && t1.next != null) {
            t1 = t1.next;
        }
        while (t2 != null && t2.next != null) {
            t2 = t2.next;
        }
        return t1 == t2;

    }


    /**
     * set list 1 tail.next = list 2 head. start from tail(a1), then head(a2) will come across itself.
     * <p>
     * slower than V1
     *
     * @param a1
     * @param a2
     * @return
     */
    public static boolean checkCrossWithoutCircleV2(ListNode a1, ListNode a2) {
        if (a1 == null || a2 == null) {
            return false;
        }

        ListNode t1 = a1;
        while (t1 != null && t1.next != null) {
            t1 = t1.next;
        }

        t1.next = a2;
        ListNode h2 = t1;
        while (h2 != null && h2.next != null) {
            h2 = h2.next;
            if (h2 == t1) {
                return true;
            }
        }

        return false;

    }

    @Test
    public void testCheckCross() {
        Assert.assertFalse(checkCrossWithoutCircle(ListNodeUtil.createList(new int[]{1, 2, 3}), ListNodeUtil.createList(new int[]{1})));
        Assert.assertFalse(checkCrossWithoutCircle(ListNodeUtil.createList(new int[]{1, 2, 3}), ListNodeUtil.createList(new int[]{})));

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        ListNode a7 = new ListNode(7);

        // 1--2--3--4--5
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        // 6--7--3--4--5
        a6.next = a7;
        a7.next = a3;

        Assert.assertTrue(checkCrossWithoutCircle(a1, a6));
    }


    /**
     * can handle case for having circle
     *
     * @param a1
     * @param a2
     * @return
     */
    public static boolean checkCrossWithCircle(ListNode a1, ListNode a2) {
        if (a1 == null || a2 == null) {
            return false;
        }

        // find entry node of circle, null if it is not circular
        ListNode c1 = findCircularEntry(a1);
        ListNode c2 = findCircularEntry(a2);

        if (c1 == null && c2 == null) {
            return checkCrossWithoutCircle(a1, a2);
        } else if ((c1 == null && c2 != null) || (c1 != null && c2 == null)) {
            // one has circle, the other one has not. they cant cross each other.
            return false;
        } else if (c1 == c2) {
            return true;
        }

        // both of them have circles.
        ListNode n = c1.next;
        while (n != c1) {
            if (n == c2) {
                return true;
            }
            n = n.next;
        }

        return false;
    }

    @Test
    public void testCrossWithOrWithoutCircle() {
        // both have no circles
        Assert.assertFalse(checkCrossWithCircle(ListNodeUtil.createList(new int[]{1, 2}), ListNodeUtil.createList(new int[]{3, 4, 5})));
        Assert.assertFalse(checkCrossWithCircle(ListNodeUtil.createList(new int[]{}), ListNodeUtil.createList(new int[]{3, 4, 5})));

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        ListNode a7 = new ListNode(7);

        // 1--2--3--4--5
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        // 6--7--3--4--5
        a6.next = a7;
        a7.next = a3;

        Assert.assertTrue(checkCrossWithCircle(a1, a6));

        // one has circle, the other not
        ListNode a11 = new ListNode(11);
        ListNode a12 = new ListNode(12);
        ListNode a13 = new ListNode(13);
        ListNode a14 = new ListNode(14);
        a11.next = a12;
        a12.next = a13;
        a13.next = a14;
        a14.next = a12;
        Assert.assertFalse(checkCrossWithCircle(ListNodeUtil.createList(new int[]{1, 2, 3, 4, 5}), a11));

        // both has circle, but not cross
        ListNode a21 = new ListNode(21);
        ListNode a22 = new ListNode(22);
        ListNode a23 = new ListNode(23);
        ListNode a24 = new ListNode(24);
        a21.next = a22;
        a22.next = a23;
        a23.next = a24;
        a24.next = a22;
        Assert.assertFalse(checkCrossWithCircle(a11, a21));

        // both has circle, and cross
        a24.next = a13;
        Assert.assertTrue(checkCrossWithCircle(a11, a21));
    }
}
