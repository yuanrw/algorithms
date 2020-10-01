package com.yrw.alogrithms.chapter4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 非递归版的dfs
 * Date: 2020/10/1
 * Time: 07:09
 *
 * @author yrw
 */
public class NonRecursiveDfs {

    private boolean[] marked;

    private Deque<Integer> stack;

    public void dfs(Graph graph, int start) {
        this.marked = new boolean[graph.vertex()];
        this.stack = new ArrayDeque<>();

        stack.push(start);
        while (!stack.isEmpty()) {
            int n = stack.pop();
            marked[n] = true;
            System.out.println(n + " ");
            Iterator<Integer> iterator = graph.adj(n);
            while (iterator.hasNext()) {
                int next = iterator.next();
                if (!marked[next]) {
                    stack.push(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        NonRecursiveDfs dfs = new NonRecursiveDfs();
        dfs.dfs(graph, 0);
    }
}
