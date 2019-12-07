package com.yrw.alogrithms.chapter3;

/**
 * Date: 2019-12-07
 * Time: 23:11
 *
 * @author yrw
 */
public interface ST<K, V> {

    void put(K key, V value);

    V get(K key);

    void delete(K key);

    boolean contains(K key);

    boolean isEmpty();

    int size();

    Iterable<K> keys();
}
