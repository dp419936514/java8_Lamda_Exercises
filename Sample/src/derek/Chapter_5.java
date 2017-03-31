package derek;

import com.benq.derek.pojo.Artist;
import com.benq.derek.pojo.DataSource;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Derek.P.Dai on 2015/11/25.
 */
public class Chapter_5 {
    private Optional<Artist> BiggestGroup;

    //Method reference

    /**
     * artist -> artist.getName();    <=>  artist::getName
     * <p>
     * (username,pwd) -> new User(user,Pwd);   <=>    User::new
     * <p>
     * String[]::new
     */

    /**
     * stream 的顺序，根据集合是否有序。 若集合本身有序，生成的Stream也是有序的。 Stream的顺序称出现顺序。
     */
    @Test
    public void OrderedStream() {
        List<Integer> numbers = asList(1, 2, 3, 4);
        assertEquals(numbers, numbers.stream().collect(toList()));
    }

    @Test
    public void DisorderedStream() {
        Set<Integer> numbers = new HashSet<Integer>(asList(1, 2, 3, 4));
        assertEquals(numbers, numbers.stream().collect(Collectors.toSet()));
    }

    @Test
    public void OrderOfAppearance() {
        Set<Integer> numbers = new HashSet<Integer>(asList(1, 6, 3, 4));
        List<Integer> set = numbers.stream().sorted().collect(toList());
        assertEquals(asList(1, 6, 3, 4), set);
    }

    @Test
    public void RemainOrderedWhenStreamOPS() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6);
        List<Integer> stillOrdered = numbers.stream()
                .map(x -> x + 1)
                .collect(toList());
        assertEquals(stillOrdered, asList(2, 3, 4, 5, 6, 7));

        Set<Integer> unordered = new HashSet<>(numbers);
        List<Integer> stillUnordered = unordered.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());

        assertThat(stillUnordered,hasItem(2));
        assertThat(stillUnordered,hasItem(3));
        assertThat(stillUnordered,hasItem(4));
        assertThat(stillUnordered,hasItem(5));
    }


    /**
     * Use Collector
     *
     * 1.Convert to other collection
     * steam.collect(toCollection(TreeSet::new));
     *
     * 2.Convert to value
     *
     * */

    @Test
    public void testToCollection(){
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6);
        HashSet<Integer> set = numbers.stream().sorted().collect(toCollection(HashSet::new));
        ArrayList<Integer> array = numbers.stream().sorted().collect(toCollection(ArrayList::new));

        System.out.println(set.getClass() + set.toString());
        System.out.println(array.getClass() + array.toString());
    }

    @Test
    public void streamToValue(){
        BiggestGroup = DataSource.artists.stream().collect(maxBy(Comparator.comparing(artist -> artist.members.length)));
        System.out.println(BiggestGroup);
    }
    @Test
    public void getSoloArtist(){
        Map<Boolean, List<Artist>> map = DataSource.artists.stream()
                .collect(partitioningBy(artist -> artist.isSolo()));

        Map<Boolean, List<Artist>> map1 = DataSource.artists.stream()
                .collect(partitioningBy(Artist::isSolo));
        System.out.println(map);
        System.out.println(map1);
    }

    @Test
    public void groupByData(){
        Map<String[], List<Artist>> map = DataSource.artists.stream()
                .collect(groupingBy(Artist::getMembers));
        System.out.println(map);
    }


}
