package com.yrw.alogrithms.chapter1;

import com.yrw.algorithms.util.Node;

/**
 * 链表的基本实现
 * Date: 2020/9/12
 * Time: 19:19
 *
 * @author yrw
 */
public class MyLinkedList<T> {

    private Node<T> head;

    public void insert(T value) {
        if (head == null) {
            head = new Node<>(value);
            return;
        }

        Node<T> cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }

        cur.next = new Node<>(value);
    }

    private void delete(T value) {
        if (head == null) {
            return;
        }

        Node<T> cur = head;
        Node<T> pre = null;
        while (!value.equals(cur.item) && cur.next != null) {
            cur = cur.next;
            pre = cur;
        }

        if (value.equals(cur.item)) {
            if (pre == null) {
                head = cur.next;
            } else {
                pre.next = cur.next;
            }
            cur.item = null;
            cur.next = null;
        }
    }

    private boolean insertAfter(T after, T value) {
        Node<T> cur = head;
        while (cur != null && !cur.item.equals(after)) {
            cur = cur.next;
        }

        if (cur == null) {
            return false;
        } else {
            Node<T> next = cur.next;
            Node<T> newNode = new Node<>(value);
            cur.next = newNode;
            newNode.next = next;
            return true;
        }
    }

    private boolean insertBefore(T before, T value) {
        Node<T> cur = head;
        Node<T> pre = null;
        while (cur != null && !cur.item.equals(before)) {
            cur = cur.next;
            pre = cur;
        }

        if (cur == null) {
            return false;
        } else {
            Node<T> newNode = new Node<>(value);
            //注意这里，插入表头！！！
            if (pre == null) {
                newNode.next = head;
                head = newNode;
            } else {
                Node<T> next = pre.next;
                pre.next = newNode;
                newNode.next = next;
            }
            return true;
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        Node<T> cur = head;
        while (cur != null) {
            s.append(cur.item).append("-");
            cur = cur.next;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.delete(1);
        System.out.println(linkedList.insertAfter(1, 2));
        System.out.println(linkedList.insertBefore(1, 2));

        linkedList.insert(2);
        linkedList.insert(Integer.MAX_VALUE);
        linkedList.insert(-999);
        linkedList.insert(0);
        linkedList.insert(232);

        System.out.println(linkedList);

        System.out.println(linkedList.insertAfter(2, 3));
        System.out.println(linkedList.insertBefore(0, 98));

        System.out.println(linkedList);

        //2, 3, max, -999, 98, 0, 232
        linkedList.delete(Integer.MAX_VALUE);
        linkedList.delete(778);
        linkedList.delete(0);
        linkedList.delete(232);
        linkedList.delete(2);
        linkedList.delete(-999);

        System.out.println(linkedList);
    }
}
