package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Administrator on 2017/8/30.
 */
public class ValidParenthesesV2 {

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        // 左括号入栈，右括号出栈
        for (int i = 0; i < s.length(); i++) {
            // 多余的入栈操作。。。
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                char prev = stack.pop();
                char ch = s.charAt(i);
                if (!((prev == '(' && ch == ')') || (prev == '[' && ch == ']') || (prev == '{' && ch == '}'))) {
                    stack.push(prev);
                    stack.push(ch);
                }
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void testValidParentheses() {
        ValidParentheses p = new ValidParentheses();
        Assert.assertTrue(p.isValid("()[]{}"));
        Assert.assertFalse(p.isValid("([)]"));
        Assert.assertFalse(p.isValid("(]"));
    }
}
