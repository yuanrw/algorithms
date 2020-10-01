package com.yrw.alogrithms.chapter4;

import java.util.*;

/**
 * 找到一条包含所有的边，且没有重复的路径
 * 如果路径不闭合，叫做欧拉路径，闭合叫做欧拉回路
 * <p>
 * 连通的无向图G 有欧拉路径的充要条件是：G中奇顶点（连接的边数量为奇数的顶点）的数目等于0或者2。
 * 连通的无向图G 是欧拉环（存在欧拉回路）的充要条件是：G中每个顶点的度都是偶数
 * <p>
 * 这边是找欧拉回路
 * <p>
 * Date: 2020/10/1
 * Time: 17:48
 *
 * @author yrw
 */
public class EulerCycle {

    private boolean hasEulerCycle;

    /**
     * 存放欧拉回路
     */
    private Deque<Integer> cycle = new ArrayDeque<>();

    public void findEulerCycle(Graph graph) {
        hasEulerCycle = true;
        //至少有一条边
        if (graph.edge() == 0) {
            hasEulerCycle = false;
            return;
        }

        //每个顶点的度都是偶数
        for (int v = 0; v < graph.vertex(); v++) {
            if (graph.degree(v) % 2 != 0) {
                hasEulerCycle = false;
                return;
            }
        }

        //同一条边是同一个对象Edge，可以通过used判断是否标记过
        Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[graph.vertex()];
        for (int v = 0; v < graph.vertex(); v++) {
            adj[v] = new LinkedList<>();
        }

        for (int v = 0; v < graph.vertex(); v++) {
            int selfLoops = 0;
            Iterator<Integer> iterator = graph.adj(v);
            while (iterator.hasNext()) {
                int w = iterator.next();
                if (v == w) {
                    //自旋要注意一下
                    if (selfLoops % 2 == 0) {
                        Edge e = new Edge(v, w);
                        adj[v].offer(e);
                        adj[w].offer(e);
                    }
                    selfLoops++;
                } else if (v < w) {
                    //限制v<w是为了避免重复放
                    Edge e = new Edge(v, w);
                    adj[v].offer(e);
                    adj[w].offer(e);
                }
            }
        }

        // 随机找到一个度不为0的顶点
        int s = nonIsolatedVertex(graph);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(s);

        // 从这个顶点开始dfs
        cycle = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].poll();
                //每条边只能被用一次
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

        // 检查是否所有的边都被用到
        if (cycle.size() != graph.edge() + 1) {
            hasEulerCycle = false;
            cycle.clear();
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

    /**
     * 无向边
     */
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

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        EulerCycle eulerCycle = new EulerCycle();
        eulerCycle.findEulerCycle(graph);

        System.out.println(eulerCycle.hasEulerCycle);


        graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(0, 4);

        eulerCycle.findEulerCycle(graph);

        System.out.println(eulerCycle.hasEulerCycle);

        eulerCycle.cycle.descendingIterator()
            .forEachRemaining(v -> System.out.print(v + " "));
        System.out.println();

        graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 4);

        eulerCycle.findEulerCycle(graph);

        System.out.println(eulerCycle.hasEulerCycle);
    }
}
