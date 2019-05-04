package com.yrw.alogrithms.chapter1.ringbuffer;

/**
 * Date: 2019-04-02
 * Time: 10:04
 *
 * @author yrw
 */
public class RingBufferFillCount<T> implements RingBuffer<T> {

    private T[] elements;

    private int capacity;
    private int writePos;
    private int available;

    public RingBufferFillCount(int capacity) {
        this.capacity = capacity;
        elements = (T[]) new Object[capacity];
    }

    @Override
    public void reset() {
        writePos = 0;
        available = 0;
        elements = (T[]) new Object[capacity];
    }

    @Override
    public boolean put(T element) {
        if (available >= capacity) {
            return false;
        }
        if (writePos == capacity) {
            writePos = 0;
        }
        elements[writePos++] = element;
        available++;
        return true;
    }

    @Override
    public T take() {
        if (available == 0) {
            return null;
        }
        int nextSlot = writePos - available;
        if (nextSlot < 0) {
            nextSlot += capacity;
        }
        T next = elements[nextSlot];
        available--;
        return next;
    }

    @Override
    public int size() {
        return available;
    }

    public static void main(String[] args) {
        RingBuffer<Integer> ringBuffer = new RingBufferFillCount<>(10);
        System.out.println("size: " + ringBuffer.size());
        for (int i = 0; i < 8; i++) {
            ringBuffer.put(i);
        }

        System.out.println("put 8 over");

        for (int i = 0; i < 4; i++) {
            System.out.println(ringBuffer.take());
        }

        System.out.println("take 4 over");

        System.out.println("size: " + ringBuffer.size());

        for (int i = 0; i < 5; i++) {
            ringBuffer.put(i);
        }

        System.out.println("put 5 over");

        System.out.println("size: " + ringBuffer.size());

        for (int i = 0; i < 15; i++) {
            System.out.println(ringBuffer.take());
        }

    }
}
