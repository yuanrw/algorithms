package com.yrw.alogrithms.chapter4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 图
 * 用邻接表存储
 * Date: 2020/9/14
 * Time: 12:48
 *
 * @author yrw
 */
public class Graph {

    /**
     * 顶点数目
     */
    private final int v;
    private int e;
    private final List<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        this.adj = new List[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();

        }
    }

    /**
     * 顶点数量
     *
     * @return
     */
    public int vertex() {
        return v;
    }

    /**
     * 边数量
     *
     * @return
     */
    public int edge() {
        return e;
    }

    /**
     * 连接两个节点
     * 重复edge当做是平行边
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        e++;
    }

    /**
     * 返回这个与v相邻的节点
     *
     * @param v
     * @return
     */
    public Iterator<Integer> adj(int v) {
        return adj[v].iterator();
    }

    public int degree(int v) {
        return adj[v].size();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        System.out.println(graph.vertex());
        System.out.println(graph.edge());

        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        System.out.println(graph.vertex());
        System.out.println(graph.edge());

        Iterator<Integer> iterator = graph.adj(3);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
