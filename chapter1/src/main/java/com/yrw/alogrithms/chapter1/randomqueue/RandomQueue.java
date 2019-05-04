package com.yrw.alogrithms.chapter1.randomqueue;

/**
 * Date: 2019-04-01
 * Time: 13:33
 *
 * @author yrw
 */
public interface RandomQueue<T> {

    boolean isEmpty();

    void enqueue(T item);

    T dequeue();

    T sample();
}
