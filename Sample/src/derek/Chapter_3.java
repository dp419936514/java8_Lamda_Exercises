package derek;

import com.benq.derek.pojo.Artist;
import com.benq.derek.pojo.Track;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Chapter_3 {
    String[] members = {"John Lennon", "Paul McCartney", "George Harrison", "Ringo  Starr"};
    Artist[] artists = {new Artist("The Beatles", members, "Liverpool")};

    List<Track> trackList = asList(new Track("Bakai", 524), new Track("Violets for Your Furs", 378), new Track("Time Was", 451));

    // asList do not create a new list. It make a List which members are references of the array passed in.
    // The lists declared in this way have come features,
    // firstly, the Lists have no Add or Remove methods.
    // secondly, the modifications to the origin array will update these Lists.
    List<Artist> allArtist = asList(artists);


    /**
     * ============================================================================================================
     * Using stream to count the artists from London.
     */

    // Traditional way
    @Test
    public void countArtistFromLondon_T() {

        int count = 0;
        for (Artist artist : artists) {
            if (artist.origin.equals("London")) {
                count++;
            }
        }
    }

    @Test
    public void countArtistFromLondon_L() {
        //Using Stream
        //The Lambda expression in filter should implement Predictable interface, return a boolean value;
        long count = allArtist.stream()
                .filter(artist -> artist.origin.equals("London"))
                .count();

    }
    //==============================================================================================================

    @Test
    public void printArtistFromLondon() {
        //This expression will do nothing, because all the operations are lazy.
        allArtist.stream()
                .filter(artist -> {
                    System.out.println(artist.name);
                    return artist.origin.equals("London");
                });
        // Java 中，不能默认把表达式的最后一行作为整个代码块的值。Scala可以。


        //书上在这里给出的示例代码是错误的，这段代码中会先打印再过滤，要想先过滤，再打印，应该在filter方法得到的流里打印
        long count = allArtist.stream()
                .filter(artist -> {
                    System.out.println(artist.name);
                    return artist.origin.equals("London");
                }).count();

        allArtist.stream()
                .filter(artist ->
                        artist.origin.contentEquals("London")
                ).filter(artist -> {
            System.out.println(artist.name);
            return true;
        }).count();

    }

    @Test
    public void StreamToList() {
        //Stream 的collect方法，将Stream的内容转换为一个List。是一个及早求值操作
        //Stream 的of方法，通过一组初始值生成新的Stream。

        /**
         * 这种写法不能编译通过，因为上下文信息不足以类型推断。但是把Stream的生成连起来写就能通过
         *  Stream stream = Stream.of(1,2,3,4,5);
         *  List<Integer> list = stream.collect(Collectors.toList());
         */

        List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(toList());
        Assert.assertEquals(asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void Stream_Map() {
        //map操作可以将stream里的值转换为另一个类型，产生一个新的
        // 从返回类型可知，这是一个惰性操作 lazy op.


        //测试一，将字母字符串内容变成大写
        List<String> list = asList("a", "sdds", "asdSFasdaf");
        List<String> upperList = new ArrayList<>();
        for (String string : list) {
            upperList.add(string.toUpperCase());
        }
        List<String> list1 = list.stream().map(str -> str = str.toUpperCase())
                .collect(toList());
        Assert.assertEquals(list1, upperList);


        //测试二，取得所有artist的名字

        ArrayList<Artist> artists = new ArrayList<>();
        String[] shes = {"Selina", "Hebe", "Ella"};
        artists.add(new Artist("S·H·E", shes, "TaiWan"));
        artists.add(new Artist("The Beatles", members, "Liverpool"));

        List<String> nameList = artists.stream()
                .map(artist -> {
                    return artist.name;
                })
                .collect(toList());
        System.out.println(nameList);

    }

    @Test
    public void Stream_filter() {
        //filter methods would check if the element in stream

        //测试一，过滤出数组中以数字开头的成员。
        List<String> list = asList("abc", "hello", "1adb", "qw1e1", "s");

        //用正式则匹配
        List<String> list1 = list.stream().filter(str -> Pattern.matches("^[0-9].*", str))
                .collect(toList());

        List<String> list2 = list.stream().filter(str -> Character.isDigit(str.charAt(0))).collect(toList());

        Assert.assertEquals(list1, list2);
    }

    @Test
    public void Stream_FlatMap() {
        //flatMap 方法用于对stream替换值，并且将多个stream连接起来。
        //同时，stream中也是可以含有多个list的

        List<Integer> list = Stream.of(asList(1, 2, 3, 4), asList(3, 4, 5), asList(5, 7))
                .flatMap(integerList -> integerList.stream())
                .collect(toList());
        //这里是实现了Comparator接口,comparator接口要求返回一个int，比较a与b时，返回负值代表a<b,0代表a=b,正值代表a>b
        list.sort((int1, int2) -> int1 - int2);
        System.out.println(list);
    }

    @Test
    public void Stream_Max_Min() {
        //对stream中的内容求出一个最小值/最大值，该方法返回一个Optional对象，代表了一个可能存在的值，调用其get()方法可以获取到值（或者null）。
        Track shortestTrack = trackList.stream().min((track1, track2) -> track1.length - track2.length).get();

        //Comparing方法 需要待比较元素，求出一个用于比较的Key，返回一个Comparator对象
        Track shortestTrack1 = trackList.stream().min(Comparator.comparing(track -> track.length)).get();

        System.out.println(shortestTrack);
        System.out.println(shortestTrack1);
        Assert.assertEquals(shortestTrack, shortestTrack1);
    }

    @Test
    public void Reduce_Mode() {
        //所有符合以下操作的循环都是reduce模式，都可以改写
        /**
         * Object accumulator = initialValue;
         * for(Object element : Collection){
         *      accumulator = combine(accumulator,element);
         * }
         * 在这种模式下，可变值是initialValue和combine方法。
         */

        //求数组的累加和
        int count = Stream.of(1, 2, 4, 5, 7, 44, 233).reduce(0 // initialValue
                , (acc, element) -> acc + element);// acc = accumulator , the BinaryOperator is combine();

        //Sum, min, max, average, and string concatenation are all special cases of reduction
        int count1 = Stream.of(1, 2, 4, 5, 7, 44, 233).reduce(0, Integer::sum);

        System.out.println("count " + count);
        System.out.println("count1 " + count1);

    }

    public void combine_ops() {


    }
}
