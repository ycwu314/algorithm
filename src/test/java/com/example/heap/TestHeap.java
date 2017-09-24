package com.example.heap;

import com.example.util.ArrayUtil;

import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/9/23.
 */
public class TestHeap {

    private final Comparator<Integer> ASC = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };

    private final Comparator<Integer> DESC = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -o1.compareTo(o2);
        }
    };

    @Test
    public void testHeapify() throws NoSuchFieldException, IllegalAccessException {
        // max heap
        testHeapifyHelper(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7}, DESC, new int[]{16, 14, 10, 8, 7, 9, 3, 2, 4, 1});
        // min heap
        testHeapifyHelper(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7}, ASC, new int[]{1, 2, 3, 4, 7, 9, 10, 14, 8, 16});
    }

    private void testHeapifyHelper(int[] input, Comparator<Integer> comparator, int[] target)
            throws NoSuchFieldException, IllegalAccessException {

        Heap<Integer> maxHeap = new Heap<>(ArrayUtil.toIntegerArray(input), comparator);

        Field array = Heap.class.getDeclaredField("array");
        array.setAccessible(true);
        Object[] arrayOfHeap = (Object[]) array.get(maxHeap);

        List<Integer> bList = new ArrayList<>();
        Stream.of(arrayOfHeap).forEach(i -> bList.add((Integer) i));
        assertArrayEquals(target, bList.stream().mapToInt(i -> i).toArray());
    }

    @Test
    public void testPoll() {
        int[] arr = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        List<Integer> aList = new ArrayList<>();
        for (int aa : arr) {
            aList.add(aa);
        }
        Heap<Integer> minHeap = new Heap<>(aList.toArray(new Integer[0]), ASC);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], minHeap.poll().intValue());
        }
    }


    @Test
    public void testOffer() {
        int[] arr = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        Heap<Integer> minHeap = new Heap<>(32, ASC);
        for (int i : arr) {
            minHeap.offer(i);
        }
        minHeap.offer(-1);
        assertEquals(-1, minHeap.peek().intValue());
    }
}
