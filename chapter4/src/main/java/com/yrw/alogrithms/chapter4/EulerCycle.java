package com.yrw.alogrithms.chapter4;

import java.util.*;

/**
 * Date: 2020/10/1
 * Time: 17:48
 *
 * @author yrw
 */
public class EulerCycle {

    /**
     * 存放欧拉回路，没有就是null
     */
    private Deque<Integer> cycle = new ArrayDeque<>();

    public void findEulerCycle(Graph graph) {
        //至少有一条边
        if (graph.edge() == 0) {
            return;
        }

        //每个顶点的度都是偶数
        for (int v = 0; v < graph.vertex(); v++) {
            if (graph.degree(v) % 2 != 0) {
                return;
            }
        }

        // create local view of adjacency lists, to iterate one vertex at a time
        // the helper Edge data type is used to avoid exploring both copies of an edge v-w
        Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[graph.vertex()];
        for (int v = 0; v < graph.vertex(); v++) {
            adj[v] = new LinkedList<>();
        }

        for (int v = 0; v < graph.vertex(); v++) {
            int selfLoops = 0;
            Iterator<Integer> iterator = graph.adj(v);
            while (iterator.hasNext()) {
                int w = iterator.next();
                // careful with self loops
                if (v == w) {
                    if (selfLoops % 2 == 0) {
                        Edge e = new Edge(v, w);
                        adj[v].offer(e);
                        adj[w].offer(e);
                    }
                    selfLoops++;
                } else if (v < w) {
                    Edge e = new Edge(v, w);
                    adj[v].offer(e);
                    adj[w].offer(e);
                }
            }
        }

        // initialize stack with any non-isolated vertex
        int s = nonIsolatedVertex(graph);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(s);

        // greedily search through edges in iterative DFS style
        cycle = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].poll();
                if (edge.isUsed) {
                    continue;
                }
                edge.isUsed = true;
                stack.push(v);
                v = edge.other(v);
            }
            // push vertex with no more leaving edges to cycle
            cycle.push(v);
        }

        // check if all edges are used
        if (cycle.size() != graph.edge() + 1) {
            cycle = null;
        }
    }

    private static int nonIsolatedVertex(Graph graph) {
        for (int v = 0; v < graph.vertex(); v++) {
            if (graph.degree(v) > 0) {
                return v;
            }
        }
        return -1;
    }

    // an undirected edge, with a field to indicate whether the edge has already been used
    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }

        // returns the other vertex of the edge
        public int other(int vertex) {
            if (vertex == v) {
                return w;
            } else if (vertex == w) {
                return v;
            } else {
                throw new IllegalArgumentException("Illegal endpoint");
            }
        }
    }
}
