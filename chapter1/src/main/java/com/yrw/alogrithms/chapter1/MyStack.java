package com.yrw.alogrithms.chapter1;

import java.util.Iterator;

/**
 * Date: 2019-03-10
 * Time: 13:39
 *
 * @author yrw
 */
public class MyStack {

    private String[] content;

    /**
     * size
     */
    private int n;

    public MyStack(int cap) {
        content = new String[cap];
    }

    public void push(String item) {
        if (isFull()) {
            resize(content.length * 2);
        }
        content[n++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        } else {
            String v = content[--n];
            content[n] = null;
            if (n <= content.length / 4) {
                resize(content.length / 2);
            }
            return v;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return this.n;
    }

    private boolean isFull() {
        return this.n == content.length;
    }

    private void resize(int cap) {
        String[] newContent = new String[cap];
        System.arraycopy(content, 0, newContent, 0, n);

        this.content = newContent;
    }

    public static MyStack copy(MyStack stack) {
        MyStack myStack = new MyStack(stack.content.length);

        String[] temp = new String[stack.content.length];
        for (int i = stack.content.length - 1; i >= 0; i--) {
            temp[i] = stack.content[i];
        }

        for (int i = 0; i < stack.content.length; i++) {
            myStack.push(stack.content[i]);
        }

        return myStack;
    }

    public MyStackIterator iterator() {
        return new MyStackIterator();
    }

    class MyStackIterator implements Iterator<String> {

        private int p;

        public MyStackIterator() {
            p = n == 0 ? 0 : n - 1;
        }

        @Override
        public boolean hasNext() {
            return p >= 0;
        }

        @Override
        public String next() {
            return content[p--];
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(5);
        for (int i = 0; i < 20; i++) {
            myStack.push(i + "");
        }
        Iterator<String> iterator = myStack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(myStack.isEmpty());

        MyStack copyStack = MyStack.copy(myStack);
        iterator = copyStack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(myStack.pop());
        }

        System.out.println(myStack.isEmpty());

    }
}
