package com.benq.derek.pojo;

import java.util.Arrays;

/**
 * Created by Derek.P.Dai on 2015/11/23.
 */
public class Artist {
    public String name;
    public String[] members;
    public String origin;
    public String nationality;

    public Artist(String name, String[] members, String origin) {
        this.name = name;
        this.members = members;
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", members=" + Arrays.toString(members) +
                ", origin='" + origin + '\'' +
                '}';
    }
    public boolean isSolo(){
        return this.members.length == 1 ;
    }

    public  String[] getMembers(){
        return members;
    }
}
