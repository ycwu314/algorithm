package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Administrator on 2017/9/7.
 */
public class StackMisc {

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。<br>
     * 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。<br>
     * （注意：这两个序列的长度是相等的）
     *
     * @param pushA
     * @param popA
     * @return
     */
    public static boolean isPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        for (int i : popA) {
            s2.push(i);
        }

        for (int i : pushA) {
            s1.push(i);
            if (i == s2.peek()) {
                s2.pop();
                s1.pop();
            }
        }

        while (!s2.isEmpty()) {
            int i = s2.pop();
            if (s1.pop() != i) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void testIsPopOrder() {
        Assert.assertTrue(isPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        Assert.assertFalse(isPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }


    /////////////////

    /**
     * reverse the stack recursively
     *
     * @param stack
     * @param top   size of the stack
     * @return
     */
    public static int[] reverseStackRecursively(int[] stack, int top) {
        if (top == 1) {
            return stack;
        }

        int aTop = stack[top - 1];
        reverseStackRecursively(stack, top - 1);
        insertIntoBottomOfStack(stack, top - 1, aTop);

        return stack;
    }


    /**
     * insert an element into the bottom of a stack
     *
     * @param stack
     * @param top
     * @param target
     */
    private static void insertIntoBottomOfStack(int[] stack, int top, int target) {
        // end condition: the stack is empty and can insert the target element
        if (top == 0) {
            stack[0] = target;
            return;
        }

        int pop = stack[top - 1];
        insertIntoBottomOfStack(stack, top - 1, target);
        stack[top] = pop;
    }

    @Test
    public void testInsertIntoBottomOfStack() throws Exception {
        int[] stack = new int[]{1, 2, 3, 4, 5, -1};
        insertIntoBottomOfStack(stack, 5, 6);
        System.out.println(Arrays.toString(stack));
        Assert.assertArrayEquals(new int[]{6, 1, 2, 3, 4, 5}, stack);
    }


    @Test
    public void testReverseStack() {
        int[] a = reverseStackRecursively(new int[]{1, 2, 3, 4, 5}, 5);
        System.out.println(Arrays.toString(a));
        Assert.assertArrayEquals(new int[]{5, 4, 3, 2, 1}, a);
    }
}
