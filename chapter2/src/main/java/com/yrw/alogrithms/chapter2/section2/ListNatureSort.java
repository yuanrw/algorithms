//package com.yrw.alogrithms.chapter2.section2;
//
//import com.yrw.algorithms.util.Node;
//
//import java.util.Random;
//
///**
// * 2.2.17
// * Date: 2019-07-28
// * Time: 18:17
// *
// * @author yrw
// */
//public class ListNatureSort<T extends Comparable> {
//
//    public void sort(Node<T> head) {
//        Node<T> lo = head, hi = head, mid = head, pre = null;
//
//        while (lo != head || mid.getNext() != null) {
//            lo = mid = hi = head;
//            while (mid.getNext() != null && mid.getNext().getNext() != null &&
//                hi.getNext() != null && hi.getNext().getNext() != null) {
//
//                print(head);
//
//                //找到第一个有序链表
//                while (mid.getNext() != null && mid.getNext().getNext() != null &&
//                    mid.getItem().compareTo(mid.getNext().getItem()) < 0) {
//                    mid = mid.getNext();
//                }
//
//                //如果mid在之后还有数据
//                if (mid.getNext() != null) {
//                    hi = mid.getNext();
//                    //找到第二个有序链表
//                    while (hi.getNext() != null && hi.getNext().getNext() != null
//                        && hi.getItem().compareTo(hi.getNext().getItem()) < 0) {
//                        hi = hi.getNext();
//                    }
//
//                    Node<T> next = hi.getNext();
//
//                    Node<T> newHead = merge(lo, mid, hi);
//
//                    if (pre == null) {
//                        head = newHead;
//                        pre = newHead;
//                    } else {
//                        pre.setNext(newHead);
//                    }
//
//                    print(head);
//
//                    if (next != null && next.getNext() != null) {
//                        while (pre.getNext() != next) {
//                            pre = pre.getNext();
//                        }
//                        lo = next;
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 设置哨兵，方便返回，不需要辅助空间
//     *
//     * @param lo
//     * @param mid
//     * @param hi
//     * @return
//     */
//    private Node<T> merge(Node<T> lo, Node<T> mid, Node<T> hi) {
//        Node<T> oldLastNext = hi.getNext();
//        Node<T> newHead = new Node<>();
//        Node<T> cur = newHead;
//
//        Node<T> i = lo, j = mid.getNext();
//
//        mid.setNext(null);
//
//        while (i != null && j != hi.getNext()) {
//            if (i.getItem().compareTo(j.getItem()) < 0) {
//                cur.setNext(i);
//                cur = cur.getNext();
//                i = i.getNext();
//            } else {
//                cur.setNext(j);
//                cur = cur.getNext();
//                j = j.getNext();
//            }
//        }
//
//        if (i == null && j != hi.getNext()) {
//            cur.setNext(j);
//        } else if (j == hi.getNext() && i != null) {
//            cur.setNext(i);
//        }
//
//        if (mid.getNext() == null) {
//            mid.setNext(oldLastNext);
//        }
//
//        return newHead.getNext();
//    }
//
//    private void print(Node<T> head) {
//        Node<T> pre = head;
//        while (pre != null) {
//            System.out.print(pre.getItem() + " ");
//            pre = pre.getNext();
//        }
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        long seed = System.currentTimeMillis();
//        Random random = new Random(seed);
//        Node<Integer> head = new Node<>();
//
//        Node<Integer> pre = head;
//        head.setItem((int) (10 * random.nextDouble()));
//
//        for (int i = 0; i < 9; i++) {
//            Node<Integer> newNode = new Node<>();
//            newNode.setItem((int) (10 * random.nextDouble()));
//            pre.setNext(newNode);
//            pre = newNode;
//        }
//
//        ListNatureSort<Integer> listNatureSort = new ListNatureSort<>();
//        listNatureSort.sort(head);
//    }
//}
