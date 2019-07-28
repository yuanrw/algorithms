package com.yrw.alogrithms.chapter2.section1.unionfind;

/**
 * Date: 2019-07-27
 * Time: 09:48
 *
 * @author yrw
 */
public interface UF {

    boolean connected(int p, int q);

    void union(int p, int q);
}
