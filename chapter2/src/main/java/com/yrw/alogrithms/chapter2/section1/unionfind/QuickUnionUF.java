package com.yrw.alogrithms.chapter2.section1.unionfind;

import java.util.Arrays;

/**
 * Date: 2019-07-28
 * Time: 11:41
 *
 * @author yrw
 */
public class QuickUnionUF implements UF {

    private int[] a;

    public QuickUnionUF(int n) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = -1;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        a[pRoot] = qRoot;
    }

    private int findRoot(int p) {
        while (a[p] != -1) {
            p = a[p];
        }
        return p;
    }

    public static void main(String[] args) {
        QuickUnionUF uf = new QuickUnionUF(10);
        uf.union(1, 2);
        uf.union(3, 2);
        uf.union(5, 9);
        uf.union(7, 1);
        uf.union(5, 4);
        System.out.println(Arrays.toString(uf.a));

        System.out.println(uf.connected(1, 3));
        System.out.println(uf.connected(1, 7));
        System.out.println(uf.connected(1, 2));
        System.out.println(uf.connected(4, 5));
        System.out.println(uf.connected(4, 9));
        System.out.println(uf.connected(1, 9));
    }
}
