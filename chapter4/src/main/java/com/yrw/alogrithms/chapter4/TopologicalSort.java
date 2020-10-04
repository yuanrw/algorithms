package com.yrw.alogrithms.chapter4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 拓扑排序
 * Date: 2020/10/4
 * Time: 17:56
 *
 * @author yrw
 */
public class TopologicalSort {

    private boolean[] marked;
    private DirectedGraph graph;
    private Deque<Integer> stack;

    public void sort(DirectedGraph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.vertex()];
        this.stack = new ArrayDeque<>();

        for (int i = 0; i < graph.vertex(); i++) {
            if (!marked[i]) {
                dfs(i);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }

    private void dfs(int s) {
        marked[s] = true;
        Iterator<Integer> iterator = graph.adj(s);
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (!marked[next]) {
                dfs(next);
            }
        }

        stack.push(s);
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(5);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        TopologicalSort topologicalSort = new TopologicalSort();
        topologicalSort.sort(graph);
    }
}
