package com.yrw.alogrithms.chapter1.uf;

import java.util.Arrays;

/**
 * Date: 2019-04-24
 * Time: 19:31
 *
 * @author yrw
 */
public class WeightingQuickUnionPathCompression extends WeightingQuickUnion {

    public WeightingQuickUnionPathCompression(int length) {
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
        WeightingQuickUnionPathCompression quickUnion = new WeightingQuickUnionPathCompression(5);
        quickUnion.union(0, 1);
        quickUnion.union(1, 2);
        quickUnion.union(2, 3);
        quickUnion.union(3, 4);

        System.out.println(quickUnion.count());

        System.out.println(Arrays.toString(quickUnion.d));
    }
}
