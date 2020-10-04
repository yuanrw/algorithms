package com.yrw.alogrithms.chapter4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 有向图，邻接表
 * Date: 2020/10/4
 * Time: 17:36
 *
 * @author yrw
 */
public class DirectedGraph {
    /**
     * 顶点数目
     */
    private final int v;
    private int e;
    private final List<Integer>[] adj;

    public DirectedGraph(int v) {
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
     * 连接两个节点v->w
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
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
}
