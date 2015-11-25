package com.benq.derek.pojo;

import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Created by Derek.P.Dai on 2015/11/23.
 */
public class Album {
    public String name;
    public Track[] tracks;
    public Artist[] musicians;

    public Stream<Artist> getMusicianStream(){
        return asList(musicians).stream();
    }

}
