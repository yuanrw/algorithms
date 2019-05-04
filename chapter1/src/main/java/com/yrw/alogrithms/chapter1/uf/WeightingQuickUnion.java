package com.yrw.alogrithms.chapter1.uf;

import java.util.Arrays;

/**
 * Date: 2019-04-23
 * Time: 20:45
 *
 * @author yrw
 */
public class WeightingQuickUnion implements UF {

    protected int[] d;
    protected int[] sz;
    private int size;

    public WeightingQuickUnion(int length) {
        this.size = length;

        this.d = new int[length];
        for (int i = 0; i < d.length; i++) {
            d[i] = i;
        }

        this.sz = new int[length];
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
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
        if (sz[pId] > sz[qId]) {
            d[qId] = d[pId];
            sz[pId] += sz[qId];
        } else {
            d[pId] = d[qId];
            sz[qId] += sz[pId];
        }
        size--;
    }

    @Override
    public int count() {
        return size;
    }

    public static void main(String[] args) {
        WeightingQuickUnion weightingQuickUnion = new WeightingQuickUnion(10);
        weightingQuickUnion.union(9, 0);
        weightingQuickUnion.union(3, 4);
        weightingQuickUnion.union(5, 8);
        weightingQuickUnion.union(7, 2);
        weightingQuickUnion.union(2, 1);
        weightingQuickUnion.union(5, 7);
        weightingQuickUnion.union(0, 3);
        weightingQuickUnion.union(4, 2);

        System.out.println(weightingQuickUnion.count());

        System.out.println(Arrays.toString(weightingQuickUnion.d));
    }
}
