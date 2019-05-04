package com.yrw.algorithms.util;

/**
 * 双向链表节点
 * Date: 2019-03-31
 * Time: 14:08
 *
 * @author yrw
 */
public class DoubleNode<T> {

    private T item;
    private DoubleNode<T> previous;
    private DoubleNode<T> next;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public DoubleNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
}
