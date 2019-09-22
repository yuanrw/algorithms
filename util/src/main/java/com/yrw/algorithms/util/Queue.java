package com.yrw.algorithms.util;

/**
 * 先进先出队列（尾进头出）
 * Date: 2019-07-28
 * Time: 12:14
 *
 * @author yrw
 */
public class Queue<T> {

    private Node<T> first;
    private Node<T> last;
    private int N;

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    /**
     * 入队
     *
     * @param item
     */
    public void enqueue(T item) {
        Node<T> node = new Node<>(item);

        Node<T> oldLast = last;
        last = node;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T item = first.item;

        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;

        return item;
    }
}
