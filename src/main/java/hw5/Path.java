package hw5;

import hw4.Graph;

/**
 * This is abstract class for several MarvelPaths
 * @param <N> the type of the graph nodes
 * @param <T> the type of the label of graph edges
 */
public abstract class Path<N extends Comparable<N>, T extends Comparable<T> >{
    protected Graph<N, T> g; // its composition graph
    /**
     * We will create our own graph from filename
     * @param fileName the file of the graph
     */
    public abstract void createNewGraph(String fileName);

    /**
     * We will find the shortest path by usgin this method
     * @param start the start node of the graph
     * @param end the end node of the graph
     * @return the shortest path from start to end
     */
    public abstract String findPath(N start, N end);

    /**
     * To deal with unknown character in the graph
     * @param start the start node
     * @param end the end node
     * @return the information about invalid situation or null
     */
    public String UnknownChar(N start, N end){
        boolean flag = false;
        StringBuilder result = new StringBuilder();
        if (g == null || !g.getNodes().contains(start)){
            result.append("unknown character ").append(start).append("\n");
            flag = true;
        }
        if (g != null && !g.getNodes().contains(end) && !start.equals(end)){
            result.append("unknown character ").append(end).append("\n");
            flag = true;
        }
        if (flag){
            return result.toString();
        }
        return null;
    }
}
