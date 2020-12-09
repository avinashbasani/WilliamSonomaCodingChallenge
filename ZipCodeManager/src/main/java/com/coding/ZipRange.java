package com.coding;

public class ZipRange {
    int low;
    int high;

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }


    public void setHigh(int high) {
        this.high = high;
    }

    public ZipRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public String toString() {
        return "["+low+","+high+"]";
    }

}
