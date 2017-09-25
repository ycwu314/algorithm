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
 */
public class RunningMedian<T> {

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
            return (Double.parseDouble(minHeap.peek().toString()) + Double.parseDouble(maxHeap.peek().toString())) / 2.0d;
        } else {
            return Double.parseDouble(minHeap.peek().toString());
        }
    }

    public Double add(T x) {
        maxHeap.add(x);
        int diff = maxHeap.size() - minHeap.size();
        // maintain that size(maxHeap)-size(minHeap) in {0, 1}
        if (!(diff == 0 || diff == 1)) {
            T move = maxHeap.poll();
            minHeap.add(move);
        }
        return getMedian();
    }
}
