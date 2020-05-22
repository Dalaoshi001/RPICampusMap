package hw4;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Graph class
 */
public class GraphTest {

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  Constructor
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testZeroArgConstructor(){
        new Graph<String, String>();
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addString Test
    ///////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testAddString(){
        // Test from 0 String Graph
        Graph<String, String> g = new Graph<>();
        g.addNode("a");
        assertEquals(g.getNodes().size(), 1);
        g.addNode("b");
        assertEquals(g.getNodes().size(), 2);
        g.addNode("c");
        assertEquals(g.getNodes().size(), 3);

        // Test 1 String-Graph
        Graph<String, String> gn = new Graph<>();
        gn.addNode("a");
        assertEquals(gn.getNodes().size(), 1);
        gn.addNode("b");
        assertEquals(gn.getNodes().size(), 2);
        gn.addNode("c");
        assertEquals(gn.getNodes().size(), 3);

        // test another type
        Graph<Integer, Integer> g1 = new Graph<>();
        g1.addNode(1);
        g1.addNode(2);
        assertEquals(g1.getNodes().size(), 2);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addEdge Test, getChild Test and getNodes Test
    ///////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testAddEdge(){
        Graph<String, String> g = new Graph<>();
        String a = "a";
        String b = "b";
        Edge<String, String> e = new Edge<>(a, b, "c");

        g.addEdge(e);
        assertEquals(g.getChild(a).size(), 1);
        assertEquals(g.getChild(b).size(), 0);

        Set<String> edges = g.getChild(a);
        Set<Edge<String, String>> realEdges = g.getEdge(a);
        Iterator<String> iter = edges.iterator();
        String en = iter.next();
        b = "b(c)";
        Iterator<Edge<String, String>> ite = realEdges.iterator();
        Edge<String, String> ed = ite.next();
        assertEquals(ed, new Edge<>(a, "b", "c"));
        assertEquals(en, b);

        edges = g.getChild("very strange");
        assertNull(edges);

        g.addEdge(new Edge<>(b, a, "h"));
        edges = g.getChild(b);
        iter = edges.iterator();
        en = iter.next();
        a = "a(h)";
        assertEquals(en, a);
        assertEquals(g.getChild(b).size(), 1);

        Graph<Integer, Integer> g3 = new Graph<>();
        g3.addEdge(new Edge<>(1, 2, 2));
        g3.addEdge(new Edge<>(1, 3, 3));
        Set<Edge<Integer, Integer>> edges1 = g3.getEdge(1);
        Iterator<Edge<Integer, Integer>> iter1 = edges1.iterator();
        assertEquals(iter1.next(), new Edge<>(1, 2, 2));
        assertEquals(iter1.next(), new Edge<>(1, 3, 3));
        assertFalse(iter1.hasNext());
    }
}