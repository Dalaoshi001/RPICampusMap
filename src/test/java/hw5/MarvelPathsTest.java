package hw5;

import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class MarvelPathsTest {
    private MarvelPaths mps;
    @Test
    public void testCreateNewGraph() {
        mps = new MarvelPaths();
        mps.createNewGraph("data/test1.csv");
        mps = new MarvelPaths();
        mps.createNewGraph("data/test2.csv");
        mps = new MarvelPaths();
        mps.createNewGraph("data/test3.csv");
        mps = new MarvelPaths();
        mps.createNewGraph("data/test4.csv");
        mps = new MarvelPaths();
        mps.createNewGraph("data/marvel.csv");
    }
    @Test
    public void testShortPath() {
        mps = new MarvelPaths();
        mps.createNewGraph("data/test1.csv");
        assertEquals(mps.findPath("c1", "c3"), "path from c1 to c3:\nc1 to c3 via book1\n");
        mps = new MarvelPaths();
        mps.createNewGraph("data/test2.csv");
        assertEquals(mps.findPath("c1", "c5"), "path from c1 to c5:\nc1 to c5 via book1\n");
        mps = new MarvelPaths();
        mps.createNewGraph("data/test3.csv");
        assertEquals(mps.findPath("c1", "c6"), "path from c1 to c6:\nno path found\n");
        assertEquals(mps.findPath("c6", "c1"), "path from c6 to c1:\nno path found\n");
        assertEquals(mps.findPath("c1", "c5"), "path from c1 to c5:\n" +
                "c1 to c3 via book1\nc3 to c4 via book2\nc4 to c5 via book3\n");
        mps = new MarvelPaths();
        mps.createNewGraph("data/test4.csv");
        assertEquals(mps.findPath("c1", "c1"), "path from c1 to c1:\n");
        assertEquals(mps.findPath("c1", "c5"), "path from c1 to c5:\nno path found\n");
        assertEquals(mps.findPath("c6", "c1"), "unknown character c6\n");
    }
}