package hw4;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class GraphWrapperTest {

    private static GraphWrapper gw = null;

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  Constructor
    ///////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testConstructor() {
        new GraphWrapper();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addNode Test
    ///////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testAddNode() {
        gw = new GraphWrapper();
        gw.addNode("a");

        gw.addNode("b");
        gw.addNode("X");
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  addEdge Test
    ///////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testAddEdge(){
        gw = new GraphWrapper();
        gw.addEdge("a1", "a2", "a3");
        gw.addEdge("b1", "b2", "b3");
        gw.addEdge("c1", "c2", "c3");
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  listNodes Test
    ///////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testListNodes(){
        gw = new GraphWrapper();
        Iterator<String> iter = gw.listNodes();
        assertFalse(iter.hasNext());
        gw.addNode("a");
        iter = gw.listNodes();
        assertTrue(iter.hasNext() && iter.next().equals("a"));
        gw.addEdge("a", "b", "l1");
        gw.addEdge("a", "b", "l2");
        gw.addEdge("a", "b", "l3");

        iter = gw.listNodes();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "a");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "b");
        assertFalse(iter.hasNext());

    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  listChildren Test
    ///////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testListChildren(){
        gw = new GraphWrapper();
        gw.addEdge("a", "b", "l1");
        gw.addEdge("a", "c", "l1");
        Iterator<String> iter = gw.listChildren("a");
        assertTrue(iter.hasNext() && iter.next().equals("b(l1)"));
        assertTrue(iter.hasNext() && iter.next().equals("c(l1)"));

        gw.addEdge("a", "d", "l12");
        iter = gw.listChildren("a");
        assertTrue(iter.hasNext() && iter.next().equals("b(l1)"));
        assertTrue(iter.hasNext() && iter.next().equals("c(l1)"));
        assertTrue(iter.hasNext() && iter.next().equals("d(l12)"));
        gw = new GraphWrapper();
        gw.addNode("a");
        iter = gw.listChildren("a");
        assertFalse(iter.hasNext());
    }
}