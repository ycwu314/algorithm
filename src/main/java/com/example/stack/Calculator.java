package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Administrator on 2017/9/4.
 */
public class Calculator {

    public static double calculate(String s) throws ArithmeticException {
        Stack<Integer> number = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if (isDigit(ch)) {
                number.push(ch - '0');
                continue;
            }

            // operator
            if (operator.isEmpty()) {
                if (ch == '(' || !number.isEmpty()) {
                    operator.push(ch);
                    continue;
                } else {
                    throw new ArithmeticException();
                }
            }


            char prevOp = operator.peek();
            if (prevOp == '(' && ch == ')') {
                operator.pop();
                continue;
            }

            // if '(' is in the operator stack, then it has the lowest priority
            // operator requires 2 number
            if (prevOp == '(' || number.size() == 1) {
                operator.push(ch);
            } else if (ch == ')') {
                // right parenthesis should not be pushed into stack,
                // instead, pop the stack until it meets '('
                while (operator.peek() != '(') {

                    int b = number.pop();
                    int a = number.pop();

                    number.push(compute(operator.pop(), a, b));
                }
                // pop for '('
                operator.pop();

            } else if (compare(ch, prevOp) > 0) {
                operator.push(ch);
            } else {

                while (!operator.isEmpty() && operator.peek() != '(' && compare(ch, operator.peek()) <= 0) {
                    int b = number.pop();
                    int a = number.pop();
                    number.push(compute(operator.pop(), a, b));
                }
                operator.push(ch);

            }
        }

        while (!operator.isEmpty())

        {
            char op = operator.pop();
            if (op == '(') {
                continue;
            }
            if (number.size() < 2) {
                throw new ArithmeticException();
            }
            int b = number.pop();
            int a = number.pop();

            number.push(compute(op, a, b));
        }


        if (number.size() != 1) {
            throw new ArithmeticException();
        }
        return number.pop();
    }

    private static int compute(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '%':
                return a % b;
            case '^':
                // a^b
                int sum = 1;
                for (int k = 0; k < b; k++) {
                    sum *= a;
                }
                return sum;
            default:
                // should not happen
                return 0;
        }
    }

    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static int compare(char op1, char op2) {
        return priority(op1) - priority(op2);
    }

    private static int priority(char op) {
        switch (op) {
            case '(':
                return 100;
            case ')':
                return -100;
            case '^':
                return 90;
            case '*':
            case '/':
            case '%':
                return 80;
            case '+':
            case '-':
                return 70;
            default:
                // should not happen
                return 0;
        }
    }


    @Test
    public void test() {
        Assert.assertEquals(1, calculate("(1)"), 1e-10);
        Assert.assertEquals(1, calculate("1"), 1e-10);
        Assert.assertEquals(4, calculate("(1+3)"), 1e-10);
        Assert.assertEquals(5, calculate("(1+3+1)"), 1e-10);
        Assert.assertEquals(7, calculate("(1+3*2)"), 1e-10);
        Assert.assertEquals(5, calculate("(1*3+2)"), 1e-10);
        Assert.assertEquals(4, calculate("((1+3))"), 1e-10);
        Assert.assertEquals(-4, calculate("4-3-5"), 1e-10);


        Assert.assertEquals(4, calculate("1+3"), 1e-10);
        Assert.assertEquals(5, calculate("1*3+2"), 1e-10);
        Assert.assertEquals(5, calculate("1*3+2*3-4"), 1e-10);
        Assert.assertEquals(7, calculate("1+3*4-6"), 1e-10);
        Assert.assertEquals(91, calculate("3*2^(4+2*2-1*3)-5"), 1e-10);


        Assert.assertEquals(65, calculate("2+7*(4+5)"), 1e-10);


        boolean exception = false;
        try {
            calculate("+3+5");
        } catch (ArithmeticException e) {
            exception = true;
        }
        Assert.assertTrue(exception);


        exception = false;
        try {
            calculate("(+5");
        } catch (ArithmeticException e) {
            exception = true;
        }
        Assert.assertTrue(exception);

        exception = false;
        try {
            calculate("()");
        } catch (ArithmeticException e) {
            exception = true;
        }
        Assert.assertTrue(exception);

    }
}

