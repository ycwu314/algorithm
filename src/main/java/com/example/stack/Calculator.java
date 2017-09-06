package com.example.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Administrator on 2017/9/4.
 */
public class Calculator {

    static final char[] SUPPORTED_OPERATORS = new char[]{'(', ')', '+', '-', '*', '/', '^', '%', '.'};

    public static double calculate(String s) throws ArithmeticException {
        Stack<Integer> number = new Stack<>();
        Stack<Character> operator = new Stack<>();

        StringBuilder numberStr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            validate(ch);

            if (ch == ' ') {
                continue;
            }

            if (isDigit(ch)) {
                numberStr.append(ch);
                continue;
            }

            // found operator
            // first push the number into stack
            convertNumber(number, numberStr);

            // only '(' can be the the first item of operator stack when number stack is empty, others cant
            if (handleEmptyOperatorStack(operator, number, ch)) {
                continue;
            }

            char prevOp = operator.peek();


            // special case for ()
            if (prevOp == '(' && ch == ')') {
                operator.pop();
            } else if (prevOp == '(') {
                // if '(' is in the operator stack, then it has the lowest priority
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
                // push into stack for higher priority
                operator.push(ch);
            } else {

                // same or lower priority than prevOp, begin pop operation stack and calculate,
                while (!operator.isEmpty() && operator.peek() != '(' && compare(ch, operator.peek()) <= 0) {
                    int b = number.pop();
                    int a = number.pop();
                    number.push(compute(operator.pop(), a, b));
                }
                operator.push(ch);
            }
        }


        // case for 1+51, digit is the last character
        convertNumber(number, numberStr);

        while (!operator.isEmpty()) {
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

    private static void validate(char ch) {
        if (ch == ' ' || (ch >= '0' && ch <= '9')) {
            return;
        }

        for (char op : SUPPORTED_OPERATORS) {
            if (op == ch) {
                return;
            }
        }

        throw new ArithmeticException("illegal char : [" + ch + "]");
    }

    private static void convertNumber(Stack<Integer> number, StringBuilder numberStr) {
        if (numberStr.length() > 0) {
            number.push(Integer.parseInt(numberStr.toString()));
            numberStr.delete(0, numberStr.length());
        }
    }

    private static boolean handleEmptyOperatorStack(Stack<Character> operator, Stack<Integer> number, char ch) {
        if (operator.isEmpty()) {
            if (ch == '(' || !number.isEmpty()) {
                operator.push(ch);
                return true;
            } else {
                throw new ArithmeticException("operator [" + ch + "] cant be ");
            }
        }

        return false;
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
    public void testCalculate() {
        Assert.assertEquals(1, calculate("(1)"), 1e-10);
        Assert.assertEquals(1, calculate("1"), 1e-10);
        Assert.assertEquals(4, calculate("(1+3)"), 1e-10);
        Assert.assertEquals(5, calculate("(1+3+1)"), 1e-10);
        Assert.assertEquals(7, calculate("(1+3*2)"), 1e-10);
        Assert.assertEquals(5, calculate("(1*3+2)"), 1e-10);
        Assert.assertEquals(4, calculate("((1+3))"), 1e-10);
        Assert.assertEquals(-4, calculate("4-3-5"), 1e-10);
        Assert.assertEquals(28, calculate("4+2*3*4"), 1e-10);


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


        Assert.assertEquals(44, calculate("11+33"), 1e-10);
    }
}

