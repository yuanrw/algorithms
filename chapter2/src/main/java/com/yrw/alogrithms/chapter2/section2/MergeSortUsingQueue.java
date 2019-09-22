package com.yrw.alogrithms.chapter2.section2;

import com.yrw.alogrithms.chapter2.section1.AbstractSort;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2.2.14/2.2.15
 * Date: 2019-07-28
 * Time: 12:34
 *
 * @author yrw
 */
public class MergeSortUsingQueue<T extends Comparable> extends AbstractSort<T> {

    private Queue<Queue<T>> queues;

    public MergeSortUsingQueue() {
        this.queues = new LinkedList<>();
    }

    @Override
    public void sort(T[] a) {
        //初始化
        for (T t : a) {
            Queue<T> q = new LinkedList<>();
            q.offer(t);

            queues.offer(q);
        }

        while (queues.size() > 1) {
            //自底向上排序
            Queue<T> q1 = queues.poll();
            Queue<T> q2 = queues.poll();
            queues.offer(merge(q1, q2));
        }

        Queue<T> q = queues.poll();
        for (int i = 0; i < a.length; i++) {
            a[i] = q.poll();
        }

    }

    private Queue<T> merge(Queue<T> q1, Queue<T> q2) {
        Queue<T> sortedQueue = new LinkedList<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty()) {
                sortedQueue.offer(q2.poll());
            } else if (q2.isEmpty()) {
                sortedQueue.offer(q1.poll());
            } else {
                if (q1.peek().compareTo(q2.peek()) < 0) {
                    sortedQueue.offer(q1.poll());
                } else {
                    sortedQueue.offer(q2.poll());
                }
            }
        }
        return sortedQueue;
    }

    public static void main(String[] args) {
        MergeSortUsingQueue<Integer> mergeSortUsingQueue = new MergeSortUsingQueue<>();
        mergeSortUsingQueue.runTest(mergeSortUsingQueue.getRandomInteger(1000));
    }
}
