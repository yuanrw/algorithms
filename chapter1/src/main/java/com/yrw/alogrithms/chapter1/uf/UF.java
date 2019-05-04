package com.yrw.alogrithms.chapter1.uf;

/**
 * 动态连通量
 * Date: 2019-04-23
 * Time: 20:45
 *
 * @author yrw
 */
public interface UF {

    /**
     * 找到触点所属的连通分量
     *
     * @param p 触点
     * @return
     */
    int find(int p);

    /**
     * 两个触点是否属于同一个连通分量
     *
     * @param p 触点1
     * @param q 触点2
     * @return
     */
    boolean connected(int p, int q);

    /**
     * 连接两个触点
     *
     * @param p 触点1
     * @param q 触点2
     */
    void union(int p, int q);

    /**
     * 连通分量的数量
     *
     * @return
     */
    int count();
}
