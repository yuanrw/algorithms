package com.yrw.alogrithms.chapter1.uf;

import java.util.Arrays;

/**
 * 把连通量做成树
 * d[i] = x
 * i表示触点，x表示树根
 * Date: 2019-04-23
 * Time: 20:45
 *
 * @author yrw
 */
public class QuickUnion implements UF {

    protected int[] d;
    private int size;

    public QuickUnion(int length) {
        this.size = length;
        this.d = new int[length];
        for (int i = 0; i < d.length; i++) {
            d[i] = i;
        }
    }

    @Override
    public int find(int p) {
        while (d[p] != p) {
            p = d[p];
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        return pId == qId;
    }

    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        d[pId] = qId;
        size--;
    }

    @Override
    public int count() {
        return size;
    }

    public static void main(String[] args) {
        QuickUnion quickUnion = new QuickUnion(10);
        quickUnion.union(9, 0);
        quickUnion.union(3, 4);
        quickUnion.union(5, 8);
        quickUnion.union(7, 2);
        quickUnion.union(2, 1);
        quickUnion.union(5, 7);
        quickUnion.union(0, 3);
        quickUnion.union(4, 2);

        System.out.println(quickUnion.count());

        System.out.println(Arrays.toString(quickUnion.d));
    }
}
