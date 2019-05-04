package com.yrw.algorithms.util;

/**
 * 单链表节点
 * Date: 2019-03-31
 * Time: 14:08
 *
 * @author yrw
 */
public class Node<T> {

    private T item;
    private Node<T> next;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
