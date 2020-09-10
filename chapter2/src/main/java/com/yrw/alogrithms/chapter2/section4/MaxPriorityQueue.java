package com.yrw.alogrithms.chapter2.section4;

import java.util.Arrays;

/**
 * 基于Binary heap实现的优先队列
 * 队列无法resize
 * 为了方便，id从1开始
 * Date: 2020/9/10
 * Time: 08:18
 *
 * @author yrw
 */
public class MaxPriorityQueue<T extends Comparable<T>> {

    private final T[] heap;
    private int N;

    public MaxPriorityQueue(int capacity) {
        heap = (T[]) new Comparable[capacity + 1];
    }

    /**
     * 在堆中插入元素
     *
     * @param x
     */
    public void insert(T x) {
        if (N == heap.length) {
            throw new RuntimeException("overflow");
        }
        heap[++N] = x;
        swim(N);
    }

    /**
     * 删掉堆中的最大元素
     */
    public T deleteMax() {
        if (N == 0) {
            throw new RuntimeException("empty queue");
        }
        T max = heap[1];
        swap(1, N);
        heap[N--] = null;
        sink(1);
        return max;
    }

    public T top() {
        return heap[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void swim(int index) {
        while (index > 1 && less(heap[index / 2], heap[index])) {
            swap(index / 2, index);
            index /= 2;
        }
    }

    private void sink(int index) {
        //判断有child
        while (2 * index <= N) {
            int j = 2 * index;
            //和兄弟比较，选择较大的child
            if (j < N && less(heap[j], heap[j + 1])) {
                j++;
            }
            if (!less(heap[index], heap[j])) {
                break;
            }
            swap(index, j);
            index = j;
        }
    }

    private void swap(int idx1, int idx2) {
        T tmp = heap[idx1];
        heap[idx1] = heap[idx2];
        heap[idx2] = tmp;
    }

    private boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> maxPriorityQueue = new MaxPriorityQueue<>(5);
        maxPriorityQueue.insert(2);
        maxPriorityQueue.insert(-10);
        maxPriorityQueue.insert(5);
        maxPriorityQueue.insert(9);
        maxPriorityQueue.insert(111);

        System.out.println(maxPriorityQueue);

        System.out.println(maxPriorityQueue.deleteMax());
        System.out.println(maxPriorityQueue.deleteMax());
        System.out.println(maxPriorityQueue.deleteMax());

        System.out.println(maxPriorityQueue);
    }
}
