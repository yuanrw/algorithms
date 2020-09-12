package com.yrw.alogrithms.leetcode;

import com.yrw.algorithms.util.Node;
import com.yrw.alogrithms.chapter2.section4.Multiway;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 23
 * 这个多路合并，输入链表是有序的
 * Date: 2020/9/12
 * Time: 09:47
 *
 * @author yrw
 */
public class MergeKList<T extends Comparable<T>> {

    private PriorityQueue<Node<T>> priorityQueue;

    public Node<T> multiway(Node<T>[] lists) {
        this.priorityQueue = new PriorityQueue<>(Comparator.comparing(a -> a.item));
        for (Node<T> head : lists) {
            if (head != null) {
                priorityQueue.offer(head);
            }
        }

        Node<T> newHead = null;
        Node<T> cur;
        Node<T> last = null;
        while (!priorityQueue.isEmpty()) {
            cur = priorityQueue.poll();
            if (newHead == null) {
                newHead = cur;
            }
            if (last != null) {
                last.next = cur;
            }
            last = cur;

            if (cur.next != null) {
                priorityQueue.offer(cur.next);
            }
        }

        return newHead;
    }

    public static void main(String[] args) {
        System.out.println("case 1");
        Node<Integer> head1 = new Node<>(1);
        Node<Integer> h11 = new Node<>(3);
        Node<Integer> h12 = new Node<>(9);

        head1.next = h11;
        h11.next = h12;

        Node<Integer> head2 = new Node<>(2);
        Node<Integer> h21 = new Node<>(4);
        Node<Integer> h22 = new Node<>(12);

        head2.next = h21;
        h21.next = h22;

        Node<Integer> head3 = new Node<>(13);
        Node<Integer> h31 = new Node<>(Integer.MAX_VALUE);

        head3.next = h31;

        Node<Integer>[] listArray = new Node[3];

        listArray[0] = head1;
        listArray[1] = head2;
        listArray[2] = head3;

        Multiway<Integer> multiway = new Multiway<>();
        printList(multiway.multiway(listArray));

        System.out.println("case 2");
        printList(multiway.multiway(new Node[0]));
    }

    private static <T> void printList(Node<T> head) {
        while (head != null) {
            System.out.println(head.item);
            head = head.next;
        }
    }
}
