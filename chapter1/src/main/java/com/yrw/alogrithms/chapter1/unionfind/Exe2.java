package com.yrw.alogrithms.chapter1.unionfind;

import java.util.Arrays;

/**
 * Union-find with specific canonical element.
 * Add a method find() to the union-find data type so that find(i) returns
 * the largest element in the connected component containing ii.
 * The operations, union(), connected(), and find() should all take
 * logarithmic time or better.
 * <p>
 * For example, if one of the connected components is {1,2,6,9},
 * then the find() method should return 99
 * for each of the four elements in the connected components.
 * Date: 2020-07-05
 * Time: 13:09
 *
 * @author yrw
 */
public class Exe2 {

    private int[] node;
    private int[] sz;

    //记录这棵树中最大的节点
    private int[] greatest;

    public Exe2(int n) {
        this.node = new int[n];
        this.greatest = new int[n];
        this.sz = new int[n];
        for (int i = 0; i < node.length; i++) {
            node[i] = i;
        }
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
        }
        for (int i = 0; i < greatest.length; i++) {
            greatest[i] = i;
        }
    }

    /**
     * 连接两个node
     */
    public void union(int i, int j) {
        int rootI = findRoot(i);
        int rootJ = findRoot(j);
        if (sz[rootI] <= sz[rootJ]) {
            node[rootI] = node[rootJ];
            sz[rootJ] = sz[rootJ] + sz[rootI];
            greatest[rootJ] = Math.max(greatest[rootI], greatest[rootJ]);
        } else {
            node[rootJ] = node[rootI];
            sz[rootI] = sz[rootI] + sz[rootJ];
            greatest[rootI] = Math.max(greatest[rootI], greatest[rootJ]);
        }
    }

    /**
     * 找到这个number的根节点
     */
    public int findRoot(int i) {
        int cur = i;
        while (node[cur] != cur) {
            cur = node[cur];
        }
        return cur;
    }

    public int find(int i) {
        return greatest[findRoot(i)];
    }

    public void printTree() {
        System.out.println(Arrays.toString(node));
    }

    public static void main(String[] args) {
        case1();
        case2();
        case3();
    }

    private static void case1() {
        Exe2 exe2 = new Exe2(5);

        exe2.union(0, 1);
        exe2.union(1, 2);
        exe2.union(2, 3);
        exe2.union(3, 4);

        exe2.printTree();

        for (int i = 0; i < 5; i++) {
            System.out.println(exe2.find(i));
        }
    }

    private static void case2() {
        Exe2 exe2 = new Exe2(5);

        exe2.union(0, 4);
        exe2.union(2, 4);
        exe2.union(3, 4);
        exe2.union(1, 0);

        exe2.printTree();

        for (int i = 0; i < 5; i++) {
            System.out.println(exe2.find(i));
        }
    }

    private static void case3() {
        Exe2 exe2 = new Exe2(1000);
        for (int i = 0; i < 500; i++) {
            exe2.union(i, i + 1);
        }

        for (int i = 501; i < 999; i++) {
            exe2.union(i, 999);
        }

        exe2.printTree();

        for (int i = 0; i < 1000; i++) {
            System.out.println(exe2.find(i));
        }
    }
}
