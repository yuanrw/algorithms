package com.yrw.alogrithms.chapter1;

import com.yrw.algorithms.util.StdRandom;

import java.util.Iterator;

/**
 * Date: 2019-03-31
 * Time: 13:06
 *
 * @author yrw
 */
public class RandomBag<T> implements Iterable<T> {

    /**
     * 指针
     */
    private int n;

    /**
     * 存储元素
     */
    private T[] content;

    public RandomBag(int capacity) {
        n = 0;
        content = (T[]) new Object[capacity];
    }

    public boolean isEmtpy() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void add(T item) {
        if (n == content.length) {
            resize(2 * n);
        }
        content[n++] = item;
    }

    private void resize(int n) {
        T[] newArray = (T[]) new Object[n];
        System.arraycopy(content, 0, newArray, 0, content.length);
        this.content = newArray;
    }

    @Override
    public RandomBagIterator iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<T> {

        private int[] randomIndex;
        private int cur;

        private RandomBagIterator() {
            cur = 0;
            randomIndex = new int[content.length];
            for (int i = 0; i < content.length; i++) {
                randomIndex[i] = i;
            }
            //打乱
            shuffle(randomIndex);
        }

        @Override
        public boolean hasNext() {
            return cur < randomIndex.length;
        }

        @Override
        public T next() {
            return content[randomIndex[cur++]];
        }
    }

    private void shuffle(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(n - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {
        RandomBag<Integer> bag = new RandomBag<>(5);
        System.out.println(bag.size());
        System.out.println(bag.isEmtpy());
        for (int i = 0; i < 10; i++) {
            bag.add(i);
        }
        System.out.println(bag.size());
        System.out.println(bag.isEmtpy());

        System.out.println();
        Iterator<Integer> iterator = bag.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
