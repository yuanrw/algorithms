package com.yrw.alogrithms.chapter1.uf;

import java.util.Arrays;

/**
 * 记录每个触点所在的分量，通过触点可以快速找到分量，找某个分量的所有触点只能遍历
 * d[i] = x
 * i表示触点，x表示分量
 * Date: 2019-04-23
 * Time: 20:45
 *
 * @author yrw
 */
public class QuickFind implements UF {

    private int[] d;
    private int size;

    public QuickFind(int length) {
        d = new int[length];
        for (int i = 0; i < d.length; i++) {
            d[i] = i;
        }
        size = length;
    }

    @Override
    public int find(int p) {
        return d[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return d[p] == d[q];
    }

    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < d.length; i++) {
            if (d[i] == pId) {
                d[i] = qId;
            }
        }
        size--;
    }

    @Override
    public int count() {
        return size;
    }

    public static void main(String[] args) {
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(9, 0);
        quickFind.union(3, 4);
        quickFind.union(5, 8);
        quickFind.union(7, 2);
        quickFind.union(2, 1);
        quickFind.union(5, 7);
        quickFind.union(0, 3);
        quickFind.union(4, 2);

        System.out.println(quickFind.count());

        System.out.println(Arrays.toString(quickFind.d));
    }
}
