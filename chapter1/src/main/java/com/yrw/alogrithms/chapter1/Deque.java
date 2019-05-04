package com.yrw.alogrithms.chapter1;

import java.util.Stack;

/**
 * 用两个stack实现Deque, stackForHead做队列头， stackForTail做队列尾
 * Date: 2019-04-16
 * Time: 09:41
 *
 * @author yrw
 */
public class Deque<T> {

    private Stack<T> stackForHead;
    private Stack<T> stackForTail;

    public Deque() {
        this.stackForHead = new Stack<>();
        this.stackForTail = new Stack<>();
    }

    public void pushLeft(T element) {
        stackForHead.push(element);
    }

    public void pushRight(T element) {
        stackForTail.push(element);
    }

    public T popLeft() {
        if (stackForHead.isEmpty()) {
            while (!stackForTail.isEmpty()) {
                stackForHead.push(stackForTail.pop());
            }
        }
        return stackForHead.pop();
    }

    public T popRight() {
        if (stackForTail.isEmpty()) {
            while (!stackForHead.isEmpty()) {
                stackForTail.push(stackForHead.pop());
            }
        }
        return stackForTail.pop();
    }

    public boolean isEmpty() {
        return stackForTail.isEmpty() && stackForHead.isEmpty();
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        deque.pushLeft(3);
        deque.pushLeft(2);
        deque.pushRight(4);
        deque.pushRight(5);
        deque.pushLeft(1);

        System.out.println(deque.popLeft());
        System.out.println(deque.popRight());
        System.out.println(deque.popRight());
        System.out.println(deque.popLeft());
        System.out.println(deque.popLeft());
    }
}

