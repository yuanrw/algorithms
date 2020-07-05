package com.yrw.alogrithms.chapter1.unionfind;

import java.util.Arrays;

/**
 * Successor with delete. Given a set of nn integers S={0,1,...,n−1} and a sequence of requests of the following form:
 * <p>
 * Remove xx from SS
 * Find the successor of xx: the smallest yy in SS such that y≥x.
 * <p>
 * Date: 2020-07-05
 * Time: 13:44
 *
 * @author yrw
 */
public class Exe3 {
    private int[] node;
    private int[] sz;
    private int[] greatest;

    public Exe3(int n) {
        this.node = new int[n];
        this.sz = new int[n];
        this.greatest = new int[n];
        for (int i = 0; i < node.length; i++) {
            node[i] = -1;
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
    private void union(int i, int j) {
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

    public void remove(int i) {
        node[i] = i;
        if (i > 0 && node[i - 1] != -1) {
            union(i, i - 1);
        } else if (i < node.length - 1 && node[i + 1] != -1) {
            union(i, i + 1);
        }
    }

    public int findSuccessor(int i) {
        if (node[i] == -1) {
            return -1;
        }
        int gre = greatest[findRoot(i)] + 1;
        if (gre > node.length - 1) {
            return -1;
        } else {
            return gre;
        }
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
        Exe3 exe3 = new Exe3(5);

        exe3.remove(0);
        System.out.println(exe3.findSuccessor(0));
        exe3.remove(1);
        System.out.println(exe3.findSuccessor(1));
        exe3.remove(2);
        System.out.println(exe3.findSuccessor(2));
        exe3.remove(3);
        System.out.println(exe3.findSuccessor(3));
        exe3.remove(4);
        System.out.println(exe3.findSuccessor(4));
    }

    private static void case2() {
        Exe3 exe3 = new Exe3(5);

        exe3.remove(4);
        System.out.println(exe3.findSuccessor(4));
        exe3.remove(1);
        System.out.println(exe3.findSuccessor(1));
        exe3.remove(2);
        System.out.println(exe3.findSuccessor(2));
        exe3.remove(0);
        System.out.println(exe3.findSuccessor(0));
        exe3.remove(3);
        System.out.println(exe3.findSuccessor(3));
    }

    private static void case3() {
        Exe3 exe3 = new Exe3(1000);
        for (int i = 0; i < 500; i++) {
            exe3.remove(i);
        }

        //第500个没有remove

        for (int i = 501; i < 1000; i++) {
            exe3.remove(i);
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println(exe3.findSuccessor(i));
        }
    }
}
