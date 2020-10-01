package com.yrw.alogrithms.chapter4;

/**
 * 找出所有的连通分量
 * Date: 2020/9/14
 * Time: 17:47
 *
 * @author yrw
 */
public class FindCC {

    /**
     * 每个节点属于的连通分量
     */
    private int[] component;

    private boolean[] marked;

    /**
     * 当前在dfs哪个连通分量
     */
    private int count = -1;

    public FindCC(Graph graph) {
        this.component = new int[graph.vertex()];
        this.marked = new boolean[graph.vertex()];
        for (int i = 0; i < graph.vertex(); i++) {
            if (!marked[i]) {
                //一个新的连通分量
                count++;
                dfs(graph, i);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        component[v] = count;

        graph.adj(v).forEachRemaining(ver -> {
            if (!marked[ver]) {
                dfs(graph, ver);
            }
        });
    }

    public boolean connected(int v, int w) {
        return v < component.length - 1 && w < component.length - 1 && component[v] == component[w];
    }

    public int count() {
        return count + 1;
    }

    public int id(int v) {
        return component[v];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        FindCC findCC = new FindCC(graph);

        System.out.println(findCC.count());
        System.out.println(findCC.connected(0, 2));
        System.out.println(findCC.connected(3, 2));
        System.out.println(findCC.connected(0, 3));
        System.out.println(findCC.connected(1, 4));

        System.out.println(findCC.connected(1, 3));

        graph = new Graph(1);

        findCC = new FindCC(graph);

        System.out.println(findCC.count());
        System.out.println(findCC.connected(0, 1));
    }
}
