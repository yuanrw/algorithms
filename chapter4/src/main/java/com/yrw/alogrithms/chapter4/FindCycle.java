package com.yrw.alogrithms.chapter4;

import java.util.Iterator;

/**
 * 检测图中是否有环
 * Date: 2020/10/1
 * Time: 08:19
 *
 * @author yrw
 */
public class FindCycle {

    private boolean[] marked;

    private boolean hasCycle;

    private Graph graph;

    public FindCycle(Graph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.vertex()];
        //每个点作为start dfs一遍
        for (int i = 0; i < graph.vertex(); i++) {
            if (!marked[i]) {
                dfs(i, i);
            }
        }
    }

    private void dfs(int last, int ver) {
        marked[ver] = true;
        Iterator<Integer> iterator = graph.adj(ver);
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (!marked[next]) {
                dfs(ver, next);
            } else if (next != last) {
                //在这个路径中找到了另一个已遍历过的顶点
                hasCycle = true;
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        //false
        System.out.println(new FindCycle(graph).hasCycle);

        Graph graph1 = new Graph(5);
        graph1.addEdge(0, 3);
        graph1.addEdge(0, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(1, 4);

        //true
        System.out.println(new FindCycle(graph1).hasCycle);
    }
}
