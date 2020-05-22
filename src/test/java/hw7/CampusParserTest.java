package hw7;

import hw4.Edge;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import static org.junit.Assert.*;

public class CampusParserTest {

    @Test
    public void TestParser(){
        try {
            CampusParser.readNodes("data/AolinGardenNodes.csv",
                    new HashMap<>(),  new HashMap<>(), new HashMap<>(), new HashSet<>());
            CampusParser.readEdges("data/AolinGardenEdges.csv", new HashMap<>());
            CampusParser.readEdges("data/AolinGardenNodes.csv", new HashMap<>());
        } catch (IOException e) {
            System.out.println("Stop it!");
        }

    }

    @Test
    public void TestAnother(){
        try{
            CampusParser.readNodes("data/test1.csv",
                    new HashMap<>(),  new HashMap<>(), new HashMap<>(), new HashSet<>());
        }catch (IOException e){
            System.out.println("OK");
        }
    }
}