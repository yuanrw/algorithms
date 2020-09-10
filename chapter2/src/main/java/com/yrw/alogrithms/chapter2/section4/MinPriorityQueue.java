package com.yrw.alogrithms.chapter2.section4;

import java.util.Arrays;

/**
 * Date: 2020/9/10
 * Time: 18:50
 *
 * @author yrw
 */
public class MinPriorityQueue<T extends Comparable<T>> {

    private final T[] heap;
    private int N;

    public MinPriorityQueue(int capacity) {
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
     * 删掉堆中的最小元素
     */
    public T deleteMin() {
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
        while (index > 1 && greater(heap[index / 2], heap[index])) {
            swap(index / 2, index);
            index /= 2;
        }
    }

    private void sink(int index) {
        //判断有child
        while (2 * index <= N) {
            int j = 2 * index;
            //和兄弟比较，选择较小的child
            if (j < N && greater(heap[j], heap[j + 1])) {
                j++;
            }
            //跟较小的交换
            if (!greater(heap[index], heap[j])) {
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

    private boolean greater(T a, T b) {
        return a.compareTo(b) > 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

    public static void main(String[] args) {
        MinPriorityQueue<Integer> minPriorityQueue = new MinPriorityQueue<>(5);
        minPriorityQueue.insert(2);
        minPriorityQueue.insert(-10);
        minPriorityQueue.insert(5);
        minPriorityQueue.insert(9);
        minPriorityQueue.insert(111);

        System.out.println(minPriorityQueue);

        System.out.println(minPriorityQueue.deleteMin());
        System.out.println(minPriorityQueue.deleteMin());
        System.out.println(minPriorityQueue.deleteMin());

        System.out.println(minPriorityQueue);
    }
}
