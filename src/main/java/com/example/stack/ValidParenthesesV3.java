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

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 左括号转换为右括号入栈，好处是简化了栈顶元素和ch的比较(stack.pop() != ch)
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != ch) {
                // 如果stack为空 or ch=右括号，则退出
                return false;
            }

        }

        return stack.isEmpty();
    }
}
