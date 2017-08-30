package com.example.linkedlist;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/8/30.
 */
public class MergeNSortedLists {

    public static void main(String[] args) {
        ListNode a1 = ListNodeUtil.createList(new int[]{2, 50, 100});
        ListNode a2 = ListNodeUtil.createList(new int[]{1, 4, 7, 8, 9, 10, 30});
        ListNode a3 = ListNodeUtil.createList(new int[]{3, 4, 5, 6, 20});
        ArrayList<ListNode> arrayList = new ArrayList<>();
        arrayList.add(a1);
        arrayList.add(a2);
        arrayList.add(a3);

        MergeNSortedLists merge = new MergeNSortedLists();
        System.out.println(ListNodeUtil.toString(merge.mergeKLists(arrayList)));
    }

    public ListNode mergeKLists(ArrayList<ListNode> lists) {

        PriorityQueue<Pair> minHeap = new PriorityQueue<>();

        ListNode head = new ListNode(0);
        ListNode result = head;

        // init
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                minHeap.offer(new Pair(lists.get(i).val, i));
                lists.set(i, lists.get(i).next);
            }
        }

        while (!minHeap.isEmpty()) {
            Pair p = minHeap.poll();
            head.next = new ListNode(p.val);
            head = head.next;

            ListNode from = lists.get(p.from);
            if (from != null) {
                minHeap.offer(new Pair(from.val, p.from));
                lists.set(p.from, from.next);

            }
        }

        return result.next;
    }


    static class Pair implements Comparable<Pair> {
        public Pair() {
        }

        public Pair(int val, int from) {
            this.val = val;
            this.from = from;
        }

        int val;
        int from;

        @Override
        public int compareTo(Pair o) {
            return this.val - o.val;
        }
    }
}
