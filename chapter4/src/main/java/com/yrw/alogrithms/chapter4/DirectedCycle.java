package com.yrw.alogrithms.chapter4;

import java.util.Iterator;

/**
 * 检测有向图中的环
 * Date: 2020/10/4
 * Time: 17:23
 *
 * @author yrw
 */
public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private boolean hasCycle;

    private DirectedGraph graph;

    public DirectedCycle(DirectedGraph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.vertex()];
        this.onStack = new boolean[graph.vertex()];

        //每个点作为start dfs一遍
        for (int i = 0; i < graph.vertex(); i++) {
            if (!marked[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(int ver) {
        marked[ver] = true;
        onStack[ver] = true;
        Iterator<Integer> iterator = graph.adj(ver);
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (!marked[next]) {
                dfs(next);
            } else {
                //在这个路径中找到了另一个已遍历过的顶点
                hasCycle = true;
            }
        }
        onStack[ver] = false;
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(5);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        //false
        System.out.println(new DirectedCycle(graph).hasCycle);

        DirectedGraph graph1 = new DirectedGraph(5);
        graph1.addEdge(3, 0);
        graph1.addEdge(0, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(1, 4);

        //true
        System.out.println(new DirectedCycle(graph1).hasCycle);
    }
}
