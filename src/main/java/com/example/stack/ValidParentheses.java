package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/8/30.
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        char[] stack = new char[256];
        int index = 0;

        for (int i = 0; i < s.length(); i++) {
            if (index == 0) {
                stack[index++] = s.charAt(i);
                continue;
            }

            char ch = s.charAt(i);
            if ((stack[index - 1] == '(' && ch == ')') || (stack[index - 1] == '[' && ch == ']') || (stack[index - 1] == '{' && ch == '}')) {
                stack[index--] = '\0';
            } else {
                stack[index++] = ch;
            }
        }

        return index == 0;
    }

    @Test
    public void testValidParentheses() {
        ValidParentheses p = new ValidParentheses();
        Assert.assertTrue(p.isValid("()[]{}"));
        Assert.assertFalse(p.isValid("([)]"));
        Assert.assertFalse(p.isValid("(]"));
    }
}
