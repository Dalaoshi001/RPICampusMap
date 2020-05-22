package hw6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarvelPaths2Test{
    private MarvelPaths2 mps;

    /**
     * This test will test creat new graph from given file
     */
    @Test
    public void testCreateNewGraph(){
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test1.csv");
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test2.csv");
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test3.csv");
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test4.csv");
    }

    /**
     * This test will test if we could find valid path from simple file
     */
    @Test
    public void testFindSimplePath(){
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test4.csv");
        assertEquals(mps.findPath("c1", "c4"), "path from c1 to c4:\nc1 to" +
                " c3 with weight 1.000\nc3 to c4 with weight 1.000\ntotal cost: 2.000\n");
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test5.csv");
        assertEquals(mps.findPath("A", "C"), "path from A to C:\nA to B with weight 0.333\n" +
                "B to C with weight 0.333\ntotal cost: 0.667\n");
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test6.csv");
        assertEquals(mps.findPath("A", "D"), "path from A to D:\nA to C" +
                " with weight 0.250\nC to D with weight 0.250\ntotal cost: 0.500\n");
        assertEquals(mps.findPath("A", "B"), "path from A to B:\nA to C" +
                " with weight 0.250\nC to D with weight 0.250\nD to B with weight 0.100\ntotal" +
                " cost: 0.600\n");
        assertEquals(mps.findPath("B", "D"), "path from B to D:\nB to D with weight" +
                " 0.100\ntotal cost: 0.100\n");
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test8.csv");
        assertEquals(mps.findPath("C", "D"), "path from C to D:\n" +
                "C to A with weight 0.250\nA to D with weight 0.250\n" +
                "total cost: 0.500\n");
    }

    /**
     * This test will test the invalid path for find path method
     */
    @Test
    public void testInvalidPath(){
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test5.csv");
        assertEquals(mps.findPath("A", "E"), "unknown character E\n");
        assertEquals(mps.findPath("A", "A"), "path from A to A:\ntotal cost: 0.000\n");
        assertEquals(mps.findPath("A", "D"), "path from A to D:\nno path found\n");
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test8.csv");
        assertEquals(mps.findPath("C","D"), "path from C to D:\nC to A with weight 0.250\nA to D" +
                " with weight 0.250\ntotal cost: 0.500\n");
    }

    /**
     * This test will find the complex path from complex file
     */
    @Test
    public void testBigPath(){
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test7.csv");
        assertEquals(mps.findPath("A", "L"), "path from A to L:\nA to C with weight " +
                "0.250\nC to D with weight 0.100\nD to E with weight 0.250\n" +
                "E to L with weight 0.250\ntotal cost: 0.850\n");
        assertEquals(mps.findPath("B", "C"), "path from B to C:\nB to A with" +
                " weight 0.250\nA to C with weight 0.250\ntotal cost: 0.500\n");
        assertEquals(mps.findPath("A", "E"), "path from A to E:\nA to" +
                " C with weight 0.250\nC to D with weight 0.100\nD to E with weight 0.250\n" +
                "total cost: 0.600\n");
        assertEquals(mps.findPath("A", "L"), "path from A to L:\n" +
                "A to C with weight 0.250\nC to D with weight 0.100\n" +
                "D to E with weight 0.250\nE to L with weight 0.250\n" +
                "total cost: 0.850\n");
        mps = new MarvelPaths2();
        mps.createNewGraph("data/test9.csv");
        assertEquals(mps.findPath("A", "F"), "path from A to F:\nA to C with" +
                " weight 0.500\nC to F with weight 0.333\ntotal cost: 0.833\n");
        assertEquals(mps.findPath("A", "D"), "path from A to D:\nA to" +
                " B with weight 0.250\nB to D with weight 0.500\ntotal " +
                "cost: 0.750\n");

        assertEquals(mps.findPath("A", "E"), "path from A to E:\nA to C" +
                " with weight 0.500\nC to E with weight 0.100\ntotal cost: 0.600\n");
    }
}