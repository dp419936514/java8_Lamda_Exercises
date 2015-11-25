package derek.exercises;

import com.benq.derek.pojo.Album;
import com.benq.derek.pojo.Artist;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * Created by Derek.P.Dai on 2015/11/24.
 */
public class Chapter_3 {
    String[] shes = {"Selina", "Hebe", "Ella"};

    String[] members = {"John Lennon", "Paul McCartney", "George Harrison", "Ringo  Starr"};

    List<Artist> artists = asList(new Artist("S·H·E", shes, "TaiWan"), new Artist("The Beatles", members, "Liverpool"));

    //编写一个求和函数，计算流中所有数的和
    public int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, number) -> acc + number);
    }

    @Test
    public void addupTest() {
        Assert.assertEquals(10, addUp(asList(1, 2, 3, 4).stream()));
    }


    //编写一个函数，接受艺术家列表作为参数，返回一个字符串列表，包含艺术家的姓名和国籍
    public List<String> getArtistNameAndNationality(List<Artist> artists) {
        return artists.stream().map(artist -> {
            return "name = " + artist.name + " Nationality : " + artist.nationality;
        }).collect(Collectors.toList());
    }

    @Test
    public void getArtNameNatTest() {


        System.out.println(getArtistNameAndNationality(artists));
    }

    //编写一个函数，接受专辑列表作为参数，返回一个由最多包含3首歌曲的专辑组成的列表

    public List<Album> getAlbumWithFewTrack(List<Album> albums) {
        return albums.stream().filter(album -> album.tracks.length <= 3).collect(Collectors.toList());
    }


    //迭代 修改如下代码，将外部迭代转换为内部迭代

    /**
     * int totalMembers = 0;
     * for(Artist artist :artists){
     * Stream<Artist> members = artist.getMember();
     * totalMembers += members.count();
     * }
     */
    //TODO 能不能直接用一个reduce就做完？
    public int countAristListMembers(List<Artist> artists) {
        return artists.stream()
                .map(artist -> artist.members.length)
                .reduce(0, Integer::sum);
    }


    @Test
    public void countAristListMembersTest() {
        System.out.println(countAristListMembers(artists));
    }


    //求值，根据Steam方法的签名，判断是惰性求值还是及早求值?是不是高阶函数？
    // boolean anyMatch(Predicate<? super T> predicate);     及早求值 非高阶函数
    // Stream<T> limit(long maxSize);                        惰性求值 非高阶函数


    //计算一个字符串中小写字母的个数
    public long countLowerCaseInString(String string) {
        return string.chars().filter(mint -> Character.isLowerCase(mint)).count();
    }

    @Test
    public void countLowerCaseInStringTest() {
        System.out.println(countLowerCaseInString("countLowerCaseInString  ??@@$%^"));
    }


    //在一个字符串列表中，找出包含最多小写字母的字符串，对于空列表，返回Optional<String>对象
    public Optional<String> getStringWithMaxLowerCase(List<String> stringList) {
        return stringList.stream().max(Comparator.comparing(string -> countLowerCaseInString(string)));
    }
    @Test
    public void getStringWithMaxLowerCaseTest(){
        List<String> stringList = asList("getStringWithMaxLowerCase","countLowerCaseInStringTest","countAristListMembersTest");
        System.out.println(getStringWithMaxLowerCase(stringList));
    }


    /*********************************************** Advanced practice **************************************************************************/
    //只用reduce和Lambda表达式写出实现Stream上 map / filter 操作的代码，不想返回Stream可以返回一个List



}

