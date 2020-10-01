package com.yrw.alogrithms.chapter4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 找到无环图的直径（最长的路径）
 * 找到一条最长路径的思路：任选顶点当做start做bfs，
 * 找到最远的顶点，从这个顶点再次bfs。
 * Date: 2020/10/1
 * Time: 09:36
 *
 * @author yrw
 */
public class DiameterOfGraph {

    private Graph graph;
    private boolean[] marked;

    public int findDiameter(Graph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.vertex()];

        int max = 0;

        //每个顶点当做start
        for (int i = 0; i < graph.vertex(); i++) {
            if (marked[i]) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            int lastNode = bfs(queue);

            queue.clear();
            marked = new boolean[graph.vertex()];
            queue.offer(lastNode);
            max = Math.max(max, bfsWithLength(queue));
        }

        return max;
    }

    private Integer bfs(Queue<Integer> queue) {
        Integer lastNode = null;
        while (!queue.isEmpty()) {
            int n = queue.poll();
            marked[n] = true;
            lastNode = n;

            Iterator<Integer> iterator = graph.adj(n);
            while (iterator.hasNext()) {
                int next = iterator.next();
                if (!marked[next]) {
                    queue.offer(next);
                }
            }
        }

        return lastNode;
    }

    private int bfsWithLength(Queue<Integer> queue) {
        int curNum = 1;
        int nextNum = 0;

        int len = 0;

        while (!queue.isEmpty()) {
            int n = queue.poll();
            marked[n] = true;

            Iterator<Integer> iterator = graph.adj(n);
            while (iterator.hasNext()) {
                int next = iterator.next();
                if (!marked[next]) {
                    nextNum++;
                    queue.offer(next);
                }
            }

            if (--curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                len++;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        DiameterOfGraph diameterOfGraph = new DiameterOfGraph();
        System.out.println(diameterOfGraph.findDiameter(graph));

        Graph graph1 = new Graph(0);
        System.out.println(diameterOfGraph.findDiameter(graph1));
    }
}
