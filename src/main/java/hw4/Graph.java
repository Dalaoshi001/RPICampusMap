package hw4;

import hw4.Edge;

import java.util.*;

/**
 * Graph represents a mutable collection of Strings and edges
 * It is implemented as a single class.
 * This class is invisible by the client
 */
public class Graph<N extends Comparable<N>, T extends Comparable<T>> {

    // abstraction function:
    // Utilize map to represent graph with String key and edge values to correspond this String's out degree edge.
    // which is basically same idea with adjacency list

    // representation invariant:
    // The nodes store in the graph must be sorted alphabetically. With the same node, its edges must be
    // sorted firstly by the next node and then by their label.

    private Map<N, Set<Edge<N, T>>> map;

    /**
     * The constructor
     * @effects Constructs a new empty graph
     */
    public Graph(){
        map = new TreeMap<>();
    }

    /**
     * This method will add new Node to the graph or do nothing if the node exists
     * @param n The String in the term which the graph add
     * @requires n != null
     * @effects add a new String n to the graph
     */
    public void addNode(N n){
        //checkRepNode(n);
        if (map.get(n) == null){
            Set<Edge<N, T>> se = new TreeSet<>();
            map.put(n, se);
        }
    }

    /**
     * This method will add a new Edge to the graph or do nothing if the edge exists
     * @param e The edge in the term which the graph add
     * @requires e != null
     * @effects add a new edge e to the graph
     */
    public void addEdge(Edge<N, T> e){
        //checkRepEdge(e);
        N ke = e.getRoot();
        map.computeIfAbsent(ke, k -> new TreeSet<>());
        map.computeIfAbsent(e.getNext(), k -> new TreeSet<>());
        map.get(ke).add(e);
    }

    /**
     * This method will return all nodes of the graph
     * @return the set of the Strings in the graph in lexicographical order
     */
    public Set<N> getNodes(){
        return map.keySet();
    }

    /**
     * This method will return the information about the children nodes from given parent node
     * @requires n != null
     * @param n the String which we will search for
     * @return the edges that the String have
     */
    public Set<String> getChild(N n){
        if (map.get(n) == null){
            return null;
        }
        Set<String> se = new TreeSet<>();
        for (Edge<N, T> e : map.get(n)) {
            String str = (String) e.getNext();
            str += "(";
            str += e.getLabel();
            str += ")";
            se.add(str);
        }
        return se;
    }

    /**
     * This method will return a set of edges of the given node
     * @param n the node we need to find children
     * @return n's edges
     */
    public Set<Edge<N, T>> getEdge(N n){
        Set<Edge<N, T>> value = map.get(n);
        if (value == null){
            return new TreeSet<>();
        }
        return new TreeSet<>(value);
    }


//    /**
//     * @effects It will throw exception if the node has already existed
//     */
//    public void checkRepNode(String n){
//        if (nodes.contains(n)){
//            throw new RuntimeException("The node already exist!");
//        }
//    }
//    /**
//     * @effects It will throw exception if the edge i
//     */
//    public void checkRepEdge(Edge e){
//        if (edges.contains(e)){
//            throw new RuntimeException("The edge already exist!");
//        }
//    }
}
