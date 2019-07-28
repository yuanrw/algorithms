package com.yrw.alogrithms.chapter2.section1.unionfind;

import java.util.Arrays;

/**
 * Date: 2019-07-27
 * Time: 09:36
 *
 * @author yrw
 */
public class QuickFindUF implements UF {

    private int[] d;

    public QuickFindUF(int n) {
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return d[p] == d[q];
    }

    @Override
    public void union(int p, int q) {
        int needChange = d[q];
        for (int i = 0; i < d.length; i++) {
            if (d[i] == needChange) {
                d[i] = d[p];
            }
        }
    }

    public static void main(String[] args) {
        QuickFindUF uf = new QuickFindUF(10);
        uf.union(1, 2);
        uf.union(3, 2);
        uf.union(5, 9);
        uf.union(7, 1);
        uf.union(5, 4);
        System.out.println(Arrays.toString(uf.d));

        System.out.println(uf.connected(1, 3));
        System.out.println(uf.connected(1, 7));
        System.out.println(uf.connected(1, 2));
        System.out.println(uf.connected(4, 5));
        System.out.println(uf.connected(4, 9));
        System.out.println(uf.connected(1, 9));
    }
}
