package com.example.stack;

import java.util.Stack;

/**
 * Created by Administrator on 2017/8/31.
 * <p>
 * Queue is FIFO
 */
public class SimulateQueue {

    // in
    Stack<Integer> stack1 = new Stack<Integer>();
    // out
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }

        // stack2 is empty
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        return stack2.pop();
    }

}
