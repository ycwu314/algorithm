package com.example.stack;

/**
 * Created by Administrator on 2017/8/30.
 */
public class ValidParentheses {

    public static void main(String[] args) {
        ValidParentheses p = new ValidParentheses();
        System.out.println(p.isValid("()[]{}"));
        System.out.println(p.isValid("([)]"));
        System.out.println(p.isValid("(]"));

    }

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
}
