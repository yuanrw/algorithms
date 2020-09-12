package com.yrw.alogrithms.chapter2.section4;

import com.yrw.algorithms.util.Node;

import java.util.PriorityQueue;

/**
 * 多路合并，多少路都可以，链表不需要排序
 * Date: 2020/9/12
 * Time: 09:22
 *
 * @author yrw
 */
public class Multiway<T extends Comparable<T>> {

    private PriorityQueue<T> priorityQueue;

    public Node<T> multiway(Node<T>[] lists) {
        this.priorityQueue = new PriorityQueue<>();

        for (Node<T> head : lists) {
            while (head != null) {
                priorityQueue.offer(head.item);
                head = head.next;
            }
        }

        Node<T> newHead = null;
        Node<T> last = null;
        while (!priorityQueue.isEmpty()) {
            Node<T> cur = new Node<>();
            if (newHead == null) {
                newHead = cur;
            }
            cur.item = priorityQueue.poll();
            if (last != null) {
                last.next = cur;
            }
            last = cur;
        }

        return newHead;
    }

    public static void main(String[] args) {
        System.out.println("case 1");
        Node<Integer> head1 = new Node<>(1);
        Node<Integer> h11 = new Node<>(3);
        Node<Integer> h12 = new Node<>(-9);

        head1.next = h11;
        h11.next = h12;

        Node<Integer> head2 = new Node<>(89);
        Node<Integer> h21 = new Node<>(-12);
        Node<Integer> h22 = new Node<>(-12);

        head2.next = h21;
        h21.next = h22;

        Node<Integer> head3 = new Node<>(1);
        Node<Integer> h31 = new Node<>(0);

        head3.next = h31;

        Node<Integer>[] listArray = new Node[3];

        listArray[0] = head1;
        listArray[1] = head2;
        listArray[2] = head3;

        Multiway<Integer> multiway = new Multiway<>();
        printList(multiway.multiway(listArray));

        System.out.println("case 2");
        printList(multiway.multiway(new Node[0]));


        System.out.println("case 3");
        head1 = new Node<>(Integer.MAX_VALUE);
        h11 = new Node<>(Integer.MAX_VALUE);
        h12 = new Node<>(-1);

        head1.next = h11;
        h11.next = h12;

        head2 = new Node<>(3);
        h21 = new Node<>(Integer.MIN_VALUE);
        h22 = new Node<>(Integer.MIN_VALUE);

        head2.next = h21;
        h21.next = h22;

        listArray = new Node[2];

        listArray[0] = head1;
        listArray[1] = head2;
        printList(multiway.multiway(listArray));
    }

    private static <T> void printList(Node<T> head) {
        while (head != null) {
            System.out.println(head.item);
            head = head.next;
        }
    }
}
