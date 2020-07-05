package com.yrw.alogrithms.chapter1.unionfind;

import java.util.Arrays;

/**
 * Social network connectivity.
 * Given a social network containing nn members
 * and a log file containing mm timestamps
 * at which times pairs of members formed friendships,
 * design an algorithm to determine the earliest time
 * at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 * Assume that the log file is sorted by timestamp
 * and that friendship is an equivalence relation.
 * The running time of your algorithm should be
 * mlogn or better
 * and use extra space proportional to nn.
 * Date: 2020-07-05
 * Time: 11:55
 *
 * @author yrw
 */
public class Exe1 {

    private int n;
    private int[] node;
    private int[] sz;

    public Exe1(int n) {
        this.n = n;
        this.node = new int[n];
        this.sz = new int[n];
        for (int i = 0; i < node.length; i++) {
            node[i] = i;
        }
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
        }
    }

    /**
     * 连接两个好友
     */
    public void union(int i, int j) {
        int rootI = findRoot(i);
        int rootJ = findRoot(j);
        if (sz[rootI] <= sz[rootJ]) {
            node[rootI] = node[rootJ];
            sz[rootJ] = sz[rootJ] + sz[rootI];
        } else {
            node[rootJ] = node[rootI];
            sz[rootI] = sz[rootI] + sz[rootJ];
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

    /**
     * 判断所有好友是否全部连通
     *
     * @return
     */
    public boolean isAllConnected() {
        //随便取一个节点，找到root，判断sz
        int root = findRoot(0);
        return sz[root] == n;
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
        Exe1 exe1 = new Exe1(5);

        exe1.union(0, 1);
        exe1.union(1, 2);

        exe1.printTree();
        System.out.println(exe1.isAllConnected());

        exe1.union(2, 3);
        exe1.union(3, 4);

        exe1.printTree();
        System.out.println(exe1.isAllConnected());
    }

    private static void case2() {
        Exe1 exe1 = new Exe1(5);

        exe1.union(0, 4);
        exe1.union(2, 4);
        exe1.union(3, 4);
        exe1.union(1, 0);

        exe1.printTree();
        System.out.println(exe1.isAllConnected());
    }

    private static void case3() {
        Exe1 exe1 = new Exe1(1000);
        for (int i = 0; i < 500; i++) {
            exe1.union(i, i + 1);
        }

        for (int i = 501; i < 999; i++) {
            exe1.union(i, 999);
        }

        exe1.printTree();
        System.out.println(exe1.isAllConnected());
    }
}
