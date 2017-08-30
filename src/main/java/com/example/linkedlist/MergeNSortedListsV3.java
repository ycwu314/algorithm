package com.example.linkedlist;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/30.
 */
public class MergeNSortedListsV3 {

    public static void main(String[] args) {
        ListNode a1 = ListNodeUtil.createList(new int[]{2, 50, 100});
        ListNode a2 = ListNodeUtil.createList(new int[]{1, 4, 7, 8, 9, 10, 30});
        ListNode a3 = ListNodeUtil.createList(new int[]{13, 4, 5, 6, 20});
        ListNode a4 = ListNodeUtil.createList(new int[]{-100, 3, 4, 5, 6, 200});
        ArrayList<ListNode> arrayList = new ArrayList<>();
        arrayList.add(a1);
        arrayList.add(a2);
        arrayList.add(a3);
        arrayList.add(a4);

        MergeNSortedListsV3 merge = new MergeNSortedListsV3();
        System.out.println(ListNodeUtil.toString(merge.mergeKLists(arrayList)));
    }


    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        int count = lists.size();
        while (count > 1) {
            for (int i = 0; i < count; i++) {
                int j = count - 1 - i;
                if (i < j) {
                    lists.set(i, mergeTwoLists(lists.get(i), lists.get(j)));
                    count--;
                }
            }
        }
        return lists.get(0);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode l3 = head;

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
        } else {
            head.next = l2;
        }

        return l3.next;
    }

}
