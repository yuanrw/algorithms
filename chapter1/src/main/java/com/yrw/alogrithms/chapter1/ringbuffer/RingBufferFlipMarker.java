package com.yrw.alogrithms.chapter1.ringbuffer;

/**
 * flipped=false表示read在write前面
 * flipped=true表示read在write后面
 * Date: 2019-04-02
 * Time: 10:38
 *
 * @author yrw
 */
public class RingBufferFlipMarker<T> implements RingBuffer<T> {

    private T[] elements;

    private int capacity;
    private int writePos;
    private int readPos;
    private boolean flipped;

    public RingBufferFlipMarker(int capacity) {
        this.capacity = capacity;
        elements = (T[]) new Object[capacity];
    }

    @Override
    public void reset() {
        writePos = 0;
        readPos = 0;
        flipped = false;
        elements = (T[]) new Object[capacity];
    }

    @Override
    public boolean put(T element) {
        if (!flipped) {
            if (writePos == capacity) {
                writePos = 0;
                flipped = true;

                if (writePos == readPos) {
                    return false;
                }
            }
        } else {
            if (writePos == readPos) {
                return false;
            }
        }
        elements[writePos++] = element;
        return true;
    }

    @Override
    public T take() {
        if (!flipped) {
            if (readPos == writePos) {
                return null;
            }
        } else {
            if (readPos == capacity) {
                readPos = 0;
                flipped = false;

                if (readPos == writePos) {
                    return null;
                }
            }
        }
        return elements[readPos++];
    }

    @Override
    public int size() {
        if (!flipped) {
            return writePos - readPos;
        } else {
            return capacity - readPos + writePos;
        }
    }

    public int remain() {
        if (!flipped) {
            return capacity - writePos;
        } else {
            return readPos - writePos;
        }
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
