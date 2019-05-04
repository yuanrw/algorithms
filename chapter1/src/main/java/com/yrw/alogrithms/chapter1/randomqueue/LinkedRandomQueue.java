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
            head = new Node<>();
            head.setItem(item);
        } else {
            Node<T> cur = head;
            while (cur.getNext() != null) {
                cur = cur.getNext();
            }
            Node<T> newNode = new Node<>();
            newNode.setItem(item);
            cur.setNext(newNode);
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (head == null) {
            throw new RuntimeException("empty");
        }
        if (head.getNext() == null) {
            T ret = head.getItem();
            head = null;

            size--;
            return ret;
        }

        int i = 1;
        Node<T> cur = head;
        Node<T> pre = cur;
        while (cur.getNext() != null) {
            i++;
            pre = cur;
            cur = cur.getNext();
        }

        swapRandom(head, cur, i);

        T ret = cur.getItem();
        pre.setNext(null);

        size--;
        return ret;
    }

    @Override
    public T sample() {
        if (head == null) {
            throw new RuntimeException("empty");
        }
        if (head.getNext() == null) {
            return head.getItem();
        }

        int index = StdRandom.uniform(size - 1);

        Node<T> cur = head;
        while (index-- > 0) {
            cur = cur.getNext();
        }
        return cur.getItem();
    }

    private void swapRandom(Node<T> head, Node<T> end, int size) {
        int index = StdRandom.uniform(size - 1);
        Node<T> cur = head;
        while (index-- > 0) {
            cur = cur.getNext();
        }
        T temp = cur.getItem();
        cur.setItem(end.getItem());
        end.setItem(temp);
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

