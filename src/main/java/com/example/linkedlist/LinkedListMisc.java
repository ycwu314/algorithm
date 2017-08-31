package com.example.linkedlist;

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
}
