package com.yrw.alogrithms.chapter1.uf;

import java.util.Arrays;

/**
 * Date: 2019-04-24
 * Time: 18:49
 *
 * @author yrw
 */
public class QuickUnionPathCompression extends QuickUnion {

    public QuickUnionPathCompression(int length) {
        super(length);
    }

    @Override
    public int find(int p) {
        int root = p;
        while (d[root] != root) {
            root = d[root];
        }
        while (d[p] != p) {
            int newP = d[p];
            d[p] = root;
            p = newP;
        }
        return root;
    }

    public static void main(String[] args) {
        QuickUnionPathCompression quickUnion = new QuickUnionPathCompression(5);
        quickUnion.union(0, 1);
        quickUnion.union(1, 2);
        quickUnion.union(2, 3);
        quickUnion.union(4, 2);

        System.out.println(Arrays.toString(quickUnion.d));
    }
}
