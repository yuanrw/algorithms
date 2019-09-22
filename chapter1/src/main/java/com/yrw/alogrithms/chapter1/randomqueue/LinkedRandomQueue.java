package com.yrw.alogrithms.chapter1.randomqueue;

import com.yrw.algorithms.util.Node;
import com.yrw.algorithms.util.StdRandom;

/**
 * Date: 2019-03-31
 * Time: 14:05
 *
 * @author yrw
 */
public class LinkedRandomQueue<T> implements RandomQueue<T> {

    private int size;
    private Node<T> head;

    public LinkedRandomQueue() {
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void enqueue(T item) {
        if (head == null) {
            head = new Node<>(item);
        } else {
            Node<T> cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            Node<T> newNode = new Node<>(item);
            cur.next = newNode;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (head == null) {
            throw new RuntimeException("empty");
        }
        if (head.next == null) {
            T ret = head.item;
            head = null;

            size--;
            return ret;
        }

        int i = 1;
        Node<T> cur = head;
        Node<T> pre = cur;
        while (cur.next != null) {
            i++;
            pre = cur;
            cur = cur.next;
        }

        swapRandom(head, cur, i);

        T ret = cur.item;
        pre.next = null;

        size--;
        return ret;
    }

    @Override
    public T sample() {
        if (head == null) {
            throw new RuntimeException("empty");
        }
        if (head.next == null) {
            return head.item;
        }

        int index = StdRandom.uniform(size - 1);

        Node<T> cur = head;
        while (index-- > 0) {
            cur = cur.next;
        }
        return cur.item;
    }

    private void swapRandom(Node<T> head, Node<T> end, int size) {
        int index = StdRandom.uniform(size - 1);
        Node<T> cur = head;
        while (index-- > 0) {
            cur = cur.next;
        }
        T temp = cur.item;
        cur.item = end.item;
        end.item = temp;
    }

    public static void main(String[] args) {
        LinkedRandomQueue<Integer> linkedRandomQueue = new LinkedRandomQueue<>();
        System.out.println(linkedRandomQueue.isEmpty());
        for (int i = 0; i < 10; i++) {
            linkedRandomQueue.enqueue(i);
        }
        System.out.println(linkedRandomQueue.isEmpty());

        for (int i = 0; i < 10; i++) {
            System.out.println("sample: " + linkedRandomQueue.sample());
            System.out.println(linkedRandomQueue.dequeue());
        }
        System.out.println(linkedRandomQueue.isEmpty());

    }
}

