package com.niux.spring;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class ByteBuffer2 {
    private int limit;
    private int position;
    private int capacity;
    private int mark;
    final int offset;
    private byte[] hb;

    public ByteBuffer2(int capacity) {
        this.capacity = capacity;
        this.offset = 0;
    }

    public ByteBuffer2 clear() {
        limit = capacity;
        this.position = 0;
        mark = -1;
        return this;
    }


    /**
     * 位置重置，可以重新读
     * @return
     */
    public final ByteBuffer2 rewind() {
        position = 0;
        mark = -1;
        return this;
    }

    /**
     * 切换读写,若需要write刚才read到的数据，则需要在write执行前执行flip()，以重置操作位置指针，保存操作数据的界限，保证write数据准确。
     *
     * @return
     */
    public ByteBuffer2 flip() {
        this.limit = position;
        position = 0;
        mark = -1;
        return this;
    }

    public int remain(){
        return limit - position;
    }

    public ByteBuffer2 mark() {
        mark = position;
        return this;
    }

    /**
     * 获取元素
     *
     * @return
     */
    public byte get() {
        return hb[ix(nextGetIndex())];
    }

    //HeapByteBuffer子类实现
    public ByteBuffer2 put(byte x) {
        hb[ix(nextPutIndex())] = x;
        return this;
    }

    private int nextPutIndex() {
        if (position >= limit)
            throw new BufferOverflowException();
        return position++;
    }

    private int ix(int i) {
        return i + offset;
    }

    private int nextGetIndex() {
        if (position >= limit) {
            throw new BufferUnderflowException();
        }
        return position++;
    }
}
