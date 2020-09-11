package com.yrw.alogrithms.chapter2.section4;

/**
 * leetcode 295
 * 快速找到中位数
 * Date: 2020/9/10
 * Time: 17:04
 *
 * @author yrw
 */
public class DynamicMedian<T extends Comparable<T>> {

    private MinPriorityQueue<T> minHeap;
    private MaxPriorityQueue<T> maxHeap;

    public DynamicMedian(int capacity) {
        minHeap = new MinPriorityQueue<>(capacity);
        maxHeap = new MaxPriorityQueue<>(capacity);
    }

    public void insert(T x) {
        if (maxHeap.size() == 0 || lessAndEquals(x, maxHeap.top())) {
            maxHeap.insert(x);
        } else {
            minHeap.insert(x);
        }

        //maxHeap堆太多
        while (maxHeap.size() > minHeap.size() + 1) {
            T tmp = maxHeap.deleteMax();
            minHeap.insert(tmp);
        }

        //minHeap堆太多
        while (minHeap.size() > maxHeap.size() + 1) {
            T tmp = minHeap.deleteMin();
            maxHeap.insert(tmp);
        }
    }

    public T findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.top();
        } else {
            return maxHeap.top();
        }
    }

    public T deleteMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.deleteMin();
        } else {
            return maxHeap.deleteMax();
        }
    }

    private boolean lessAndEquals(T a, T b) {
        return a.compareTo(b) <= 0;
    }

    public static void main(String[] args) {
        DynamicMedian<Integer> dynamicMedian = new DynamicMedian<>(7);
        dynamicMedian.insert(2);
        dynamicMedian.insert(-10);
        dynamicMedian.insert(5);
        dynamicMedian.insert(9);
        dynamicMedian.insert(111);

        System.out.println(dynamicMedian.findMedian());

        dynamicMedian.insert(112);
        System.out.println(dynamicMedian.findMedian());
        dynamicMedian.insert(-112);
        System.out.println(dynamicMedian.findMedian());

        System.out.println(dynamicMedian.deleteMedian());
        System.out.println(dynamicMedian.deleteMedian());
        System.out.println(dynamicMedian.findMedian());

        DynamicMedian<Integer> allEquals = new DynamicMedian<>(100);
        for (int i = 0; i < 100; i++) {
            allEquals.insert(5);
        }
        System.out.println(allEquals.findMedian());
        System.out.println(allEquals.deleteMedian());


        DynamicMedian<Integer> sortHeap = new DynamicMedian<>(10);
        for (int i = 0; i < 10; i++) {
            sortHeap.insert(i);
        }
        System.out.println(sortHeap.findMedian());

        DynamicMedian<Integer> reverseSortHeap = new DynamicMedian<>(10);
        for (int i = 0; i < 10; i++) {
            reverseSortHeap.insert(10 - i);
        }
        System.out.println(reverseSortHeap.findMedian());

        DynamicMedian<Integer> emptyHeap = new DynamicMedian<>(0);
        System.out.println(emptyHeap.findMedian());
        System.out.println(emptyHeap.deleteMedian());
    }
}
