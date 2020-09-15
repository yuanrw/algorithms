package com.yrw.alogrithms.chapter4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

/**
 * 给定起点s
 * 利用深度优先搜索
 * 找出起始为s的所有路径
 * Date: 2020/9/14
 * Time: 17:43
 *
 * @author yrw
 */
public class Dfs {

    private Graph g;

    private boolean[] marked;

    //保存从s到这个节点路径上的倒数第二个节点

    private int[] edgeTo;

    //起点

    private final int s;

    public Dfs(Graph g, int s) {
        this.g = g;
        this.s = s;
        this.marked = new boolean[g.vertex()];
        this.edgeTo = new int[g.vertex()];
        Arrays.fill(edgeTo, -1);
        marked[s] = true;

        dfs(s);
    }

    private void dfs(int s) {
        g.adj(s).forEachRemaining(v -> {
            if (!marked[v]) {
                marked[v] = true;
                edgeTo[v] = s;
                dfs(v);
            }
        });
    }

    /**
     * 返回从s到v是否有路径
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 顺序打印出从s开始到终点v的路径
     *
     * @return
     */
    public Iterator<Integer> pathTo(int v) {
        Deque<Integer> deque = new ArrayDeque<>();
        int cur = v;
        while (cur != s) {
            deque.push(cur);
            cur = edgeTo[cur];
        }

        deque.push(s);
        return deque.iterator();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        Dfs dfs = new Dfs(graph, 0);
        //false
        System.out.println(dfs.hasPathTo(1));
        //true
        System.out.println(dfs.hasPathTo(2));

        dfs.pathTo(4).forEachRemaining(i -> System.out.print(i + "-"));
    }
}
