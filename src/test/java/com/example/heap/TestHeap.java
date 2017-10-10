package com.example.heap;

import com.example.util.ArrayUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2017/9/23.
 */
public class TestHeap extends HeapTestBase {

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

    @Test
    public void testRemoveAtLastElement() {
        Heap<Integer> minHeap = new Heap<>(8, ASC);
        minHeap.offer(1);
        minHeap.offer(2);
        minHeap.offer(3);
        // indexed from 0
        minHeap.removeAt(2);
        assertEquals(1, minHeap.peek(0).intValue());
        assertEquals(2, minHeap.peek(1).intValue());
    }

    /////////////////////////////////////////////////////////

    /**
     * <a href="http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/9-BinTree/heap-delete.html">Deleting a node (at a specific location) from a heap</a>
     */
    @Test
    public void testRemoveAtWithSiftUp() {
        Integer[] arr = new Integer[]{1, 9, 22, 17, 11, 33, 27, 21, 19};
        Heap<Integer> minHeap = new Heap<>(arr, ASC);

        assertEquals(33, minHeap.removeAt(5).intValue());
        assertEquals(19, minHeap.peek(2).intValue());
        assertEquals(22, minHeap.peek(5).intValue());
    }

    @Test
    public void testRemoveAtWithSiftDown() {
        Integer[] arr = new Integer[]{1, 5, 6, 9, 11, 8, 15, 17, 21};
        Heap<Integer> minHeap = new Heap<>(arr, ASC);

        assertEquals(5, minHeap.removeAt(1).intValue());
        assertEquals(9, minHeap.peek(1).intValue());
        assertEquals(17, minHeap.peek(3).intValue());
        assertEquals(21, minHeap.peek(7).intValue());
    }

    @Test
    public void testRemoveAtTop() {
        Heap<Integer> minHeap = new Heap<>(8, ASC);
        minHeap.offer(3);
        minHeap.offer(2);
        assertEquals(2, minHeap.removeAt(0).intValue());
        assertEquals(3, minHeap.peek(0).intValue());
    }

}
