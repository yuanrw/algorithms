package com.yrw.alogrithms.chapter2.section2;

import com.yrw.algorithms.util.Node;

/**
 * 2.2.17
 * Date: 2019-09-22
 * Time: 14:47
 *
 * @author yrw
 */
public class ListNatureSort<T extends Comparable> {

    public Node<T> sortList(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> slow = head, fast = head, prev = null;
        //找到中间位置
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //把链表断开
        prev.next = null;
        Node<T> l1 = sortList(head);
        Node<T> l2 = sortList(slow);

        return merge(l1, l2);
    }

    /**
     * 合并两个有序的链表
     *
     * @param l1
     * @param l2
     * @return
     */
    private Node<T> merge(Node<T> l1, Node<T> l2) {
        Node<T> head = new Node<>(null);
        Node<T> p = head;
        while (l1 != null && l2 != null) {
            if (l1.item.compareTo(l2.item) < 0) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return head.next;
    }
}
