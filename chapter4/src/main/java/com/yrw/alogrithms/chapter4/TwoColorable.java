package com.yrw.alogrithms.chapter4;

/**
 * 双色问题，是否二分图
 * Date: 2020/9/23
 * Time: 07:05
 *
 * @author yrw
 */
public class TwoColorable {

    private boolean[] marked;

    private boolean[] color;

    private boolean isTwoColorable = true;

    public TwoColorable(Graph graph) {
        this.marked = new boolean[graph.vertex()];
        this.color = new boolean[graph.vertex()];

        for (int i = 0; i < graph.vertex(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        graph.adj(v).forEachRemaining(ver -> {
            if (!marked[ver]) {
                color[ver] = !color[v];
                dfs(graph, ver);
            } else if (color[ver] == color[v]) {
                isTwoColorable = false;
            }
        });
    }

    public boolean isTwoColorable() {
        return isTwoColorable;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(0, 7);

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(5, 6);

        graph.addEdge(3, 4);

        TwoColorable twoColorable = new TwoColorable(graph);
        System.out.println(twoColorable.isTwoColorable());


        graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);

        graph.addEdge(3, 4);

        twoColorable = new TwoColorable(graph);
        System.out.println(twoColorable.isTwoColorable());
    }
}
