package com.yrw.alogrithms.chapter1.randomqueue;

import com.yrw.algorithms.util.StdRandom;

/**
 * Date: 2019-04-01
 * Time: 13:35
 *
 * @author yrw
 */
public class ArrayRandomQueue<T> implements RandomQueue<T> {

    private int n;
    private T[] content;

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void enqueue(T item) {
        if (content.length == n) {
            resize(2 * n);
        }
        content[n++] = item;
    }

    @Override
    public T dequeue() {
        if (n == 0) {
            throw new RuntimeException("empty");
        }
        if (n <= content.length / 4) {
            resize(n / 2);
        }
        int index = StdRandom.uniform(n - 1);
        T temp = content[index];
        content[index] = content[n - 1];
        content[n - 1] = temp;
        return content[--n];
    }

    @Override
    public T sample() {
        if (n == 0) {
            throw new RuntimeException("empty");
        }
        int index = StdRandom.uniform(n - 1);
        return content[index];
    }

    private void resize(int n) {
        T[] newArray = (T[]) new Object[n];
        System.arraycopy(content, 0, newArray, 0, content.length);
        this.content = newArray;
    }
}
