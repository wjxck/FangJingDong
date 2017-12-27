package com.bwei.fangjingdong.eventbus;

/**
 * 作者：王建勋
 * 时间：2017-12-13 20:21
 * 类的用途：
 */

public class PriceAndCountEvent {
    private float price;
    private int count;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
