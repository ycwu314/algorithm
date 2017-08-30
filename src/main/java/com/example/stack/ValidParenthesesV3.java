package com.example.stack;

import java.util.Stack;

/**
 * Created by Administrator on 2017/8/30.
 */
public class ValidParenthesesV3 {

    public static void main(String[] args) {
        ValidParenthesesV3 p = new ValidParenthesesV3();
        System.out.println(p.isValid("()[]{}"));
        System.out.println(p.isValid("([)]"));
        System.out.println(p.isValid("(]"));

    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != ch) {
                return false;
            }

        }

        return stack.isEmpty();
    }
}
