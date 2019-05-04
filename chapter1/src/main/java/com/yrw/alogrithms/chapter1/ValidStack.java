package com.yrw.alogrithms.chapter1;

import java.util.Stack;

/**
 * Date: 2019-04-02
 * Time: 20:46
 *
 * @author yrw
 */
public class ValidStack {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < popped.length; i++) {
            if ((j < pushed.length && stack.isEmpty())
                || (j < pushed.length && stack.peek() != popped[i])) {
                stack.push(pushed[j++]);
                continue;
            }
            if (stack.peek() == popped[i]) {
                stack.pop();
                i++;
                continue;
            }

            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 3, 5, 1, 2};

        ValidStack stack = new ValidStack();
        System.out.println(stack.validateStackSequences(pushed, popped));
    }
}
