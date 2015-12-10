package com.benq.derek.pojo;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Derek.P.Dai on 2015/12/10.
 */
public class DataSource {
    public static String[] shes = {"Selina", "Hebe", "Ella"};

    public static String[] members = {"John Lennon", "Paul McCartney", "George Harrison", "Ringo  Starr"};

    public static List<Artist> artists = asList(new Artist("S·H·E", shes, "TaiWan"), new Artist("The Beatles", members, "Liverpool"));


    private static Album album = new Album();

    public static List<Album> albums = asList(album);
}
