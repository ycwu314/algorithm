package com.example.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by ycwu on 2017/9/25.
 * <p>
 * 设计一种数据结构，动态求解中位数。插入时间复杂度为logN，返回中位数为O(1)。<br>
 * <p>
 * <a href="http://posts.careerengine.us/p/57479aeb532b8ee75e0c4536">Google面试题14 | 寻找中位数</a>
 * <p>
 * <a href="http://gaocegege.com/Blog/algorithm/dynamicmedian">最大最小堆计算动态中位数</a>
 * <pre>
 * 假设数列L分成两个集合A, B。
 * 1. A中所有元素都小于等于B
 * 2. A的元素个数==B的元素个数，或者只比B多一个
 *
 * 那么中位数是：
 * 1. L元素个数为奇数，中位数=max(A)
 * 2. L元素个数为偶数，中位数=avg(max(A), min(B))
 *
 * 用最大堆表示A，用最小堆表示B
 * </pre>
 */
public class RunningMedian<T extends Number> {

    /**
     * store elements that are greater than the median
     */
    private PriorityQueue<T> minHeap = new PriorityQueue<>();
    /**
     * store elements that are less than or equals to the median
     */
    private PriorityQueue<T> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public Double getMedian() {
        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            return (minHeap.peek().doubleValue() + maxHeap.peek().doubleValue()) / 2.0d;
        } else {
            return maxHeap.peek().doubleValue();
        }
    }

    public Double add(T x) {
        // rule 1
        maxHeap.add(x);
        minHeap.offer(maxHeap.poll());

        // rule 2
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        return getMedian();
    }
}
