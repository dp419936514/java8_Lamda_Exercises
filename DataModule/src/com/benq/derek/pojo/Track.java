package com.benq.derek.pojo;

/**
 * Created by Derek.P.Dai on 2015/11/23.
 */
public class Track {
    public String name;
    public int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
