package com.yrw.alogrithms.chapter3;

/**
 * Date: 2019-12-07
 * Time: 23:08
 *
 * @author yrw
 */
public class ArrayST extends AbstractST {

    private static final Integer DEFAULT_SIZE = 100;

    private String[] array;
    private int size;

    public ArrayST() {
        array = new String[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void put(Integer key, String value) {
        if (key > array.length - 1) {
            resize(key + 1);
        }

        if (key > 0) {
            array[key] = value;
            size++;
        }
    }

    @Override
    public String get(Integer key) {
        if (key > 0 && key < array.length - 1) {
            return array[key];
        }
        return null;
    }

    @Override
    public void delete(Integer key) {
        if (key > 0 && key < array.length - 1) {
            array[key] = null;
            size--;
        }
    }

    @Override
    public boolean contains(Integer key) {
        return key > 0 && key < array.length - 1 && array[key] != null;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Integer> keys() {
        return null;
    }

    private void resize(int newSize) {

    }
}
