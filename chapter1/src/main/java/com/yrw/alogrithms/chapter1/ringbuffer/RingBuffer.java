package com.yrw.alogrithms.chapter1.ringbuffer;

/**
 * 环形缓冲区
 * Date: 2019-04-02
 * Time: 10:03
 *
 * @author yrw
 */
public interface RingBuffer<T> {

    /**
     * 重置
     */
    void reset();

    /**
     * 生产者放入元素
     *
     * @param element
     * @return
     */
    boolean put(T element);

    /**
     * 消费者消费元素
     *
     * @return
     */
    T take();

    /**
     * 缓冲区内元素数量
     *
     * @return
     */
    int size();
}
