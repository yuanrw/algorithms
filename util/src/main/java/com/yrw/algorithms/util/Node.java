package com.yrw.algorithms.util;

/**
 * 单链表节点
 * Date: 2019-03-31
 * Time: 14:08
 *
 * @author yrw
 */
public class Node<T> {

    public T item;
    public Node<T> next;

    public Node() {
    }

    public Node(T item) {
        this.item = item;
    }
}
