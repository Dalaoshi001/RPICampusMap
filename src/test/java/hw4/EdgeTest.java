package hw4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class contains a set of test cases that can be used to test the
 * implementation of the Edge class
 */
public class EdgeTest {

    ///////////////////////////////////////////////////////////////////////////////////////
    ////  Constructor
    ///////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testConstructor(){
        String r = "a";
        String n = "b";
        new Edge<>(r, n, "a");
        new Edge<>(n, r, "b");
        new Edge<>("", "", "");
        new Edge<>(1, 2, 3);
    }

    //Test observers
    @Test
    public void testGetMethod(){
        String r = "a";
        String n = "b";
        Edge<String, String> e = new Edge<>(r, n, "c");
        assertEquals(e.getRoot(), r);
        assertEquals(e.getNext(), n);
        assertEquals(e.getLabel(), "c");
        Edge<Integer, Integer> e1 = new Edge<>(1, 2, 3);
        assertEquals(e1.getRoot(), Integer.valueOf(1));
        assertEquals(e1.getNext(), Integer.valueOf(2));
        assertEquals(e1.getLabel(), Integer.valueOf(3));
    }

    // Test compare to
    @Test
    public void testCompareTo(){
        assertEquals(new Edge<>("a", "b", "c1").compareTo(new Edge<>("b", "a", "c2")), -1);
        assertEquals(new Edge<>("ab", "b", "c1").compareTo(new Edge<>("a", "b", "c1")), 1);
        assertEquals(new Edge<>("ads", "a", "c1").compareTo(new Edge<>("ads", "ab", "c1")), -1);
        assertEquals(new Edge<>("", "b", "c").compareTo(new Edge<>("", "a", "c")), 1);
        assertEquals(new Edge<>("", "", "a").compareTo(new Edge<>("", "", "b")), -1);
        assertEquals(new Edge<>("", "", "b").compareTo(new Edge<>("", "", "a")), 1);
        assertEquals(new Edge<>(1, 2, 3).compareTo(new Edge<>(1, 2, 1)), 1);
        assertEquals(new Edge<>(1, 2, 1).compareTo(new Edge<>(1, 2, 3)), -1);
        assertEquals(new Edge<>(1, 2, 1).compareTo(new Edge<>(1, 2, 1)), 0);
    }

    // Test equal
    @Test
    public void testEqual(){
        assertEquals(new Edge<>("a", "b", "c"),
                new Edge<>("a", "b", "c"));
        assertEquals(new Edge<>("a", "b", "c").hashCode(),
                new Edge<>("a", "b", "c").hashCode());
        assertEquals(new Edge<>("", "", ""), new Edge<>("", "", ""));
        assertEquals(new Edge<>("", "", "").hashCode(), new Edge<>("", "", "").hashCode());
        assertNotEquals(new Edge<>("a", "b",
                "c"), new Edge<>("b", "b", "c"));
        assertNotEquals(new Edge<>("a", "b",
                "c"), new Edge<>("a", "a", "c"));
        assertNotEquals(new Edge<>("a", "b",
                "c"), new Edge<>("a", "b", "a"));
        assertNotEquals(new Edge<>("a", "b", "c"), new Object());
        assertEquals(new Edge<>(1, 1, 1), new Edge<>(1, 1, 1));
        assertNotEquals(new Edge<>('1', 'a', 'b'), new Edge<>(1, 1, 1));
    }
}