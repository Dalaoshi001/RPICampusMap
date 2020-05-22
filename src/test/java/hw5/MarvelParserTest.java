package hw5;

import hw4.Graph;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class MarvelParserTest {
    @Test
    public void TestBuildGraph() {
        String file = "data/test1.csv";
        Graph<String, String> gw = new Graph<>();
        try {
            MarvelParser.buildGraph(file, gw);
            Set<String> se = gw.getChild("c1");
            Set<String> se1 = new TreeSet<>();
            se1.add("c2(book1)");
            se1.add("c3(book1)");
            assertEquals(se, se1);
            se = gw.getChild("c2");
            se1.clear();
            se1.add("c1(book1)");
            se1.add("c3(book1)");
            assertEquals(se, se1);
            se = gw.getChild("c3");
            se1.clear();
            se1.add("c1(book1)");
            se1.add("c2(book1)");
            assertEquals(se, se1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}