package hw4;

import java.util.Objects;

/**
 * Edge represents mutable linked relationship from one String to another one
 * It is implemented as a single class
 * This class is invisible by the client
 */
public class Edge<N extends Comparable<N>, T extends Comparable<T>> implements Comparable<Edge<N, T>> {

    private N root;
    private N next;
    private T label;

    // abstraction function:
    // an edge e which have root and next represents there is a path from root to next

    // representation invariant:
    // root != null && next != null

    /**
     * The edge constructor
     * @param r represents the String points to the next
     * @param n represents the String from the root
     * @param l represents the label of this edge
     * @effects Constructs an edge has root = r and next = n
     */
    public Edge(N r, N n, T l){
        root = r;
        next = n;
        label = l;
        checkRep();
    }

    /**
     * It will return the root
     * @return the root of this edge
     */
    public N getRoot(){
        return root;
    }

    /**
     * It will return the next node
     * @return the next of this edge
     */
    public N getNext(){
        return next;
    }

    /**
     * It will return the getLabel
     * @return the label of this edge
     */
    public T getLabel(){ return label; }

    /**
     * It will change the label of the edge
     * @param value the label we want to give it to the edge
     */
    public void setLabel(T value){ label = value; }

    /**
     * check if the representation invariant holds
     */
    // Throw a Runtime Exception if the representation invariant violates
    public void checkRep(){
        if (root == null || next == null){
            throw new RuntimeException("this edge is not valid!");
        }
    }
    /**
     * Compare two Strings' value
     * @param e The Edge to be compared
     * @requires e != NULL
     * @return a negative number if this < e,
     * 0 if this = e,
     * a positive number if this > e
     */
    @Override
    public int compareTo(Edge<N, T> e) {
        if (this.getRoot().compareTo(e.getRoot()) < 0){
            return -1;
        }
        else if (this.root.compareTo(e.root) > 0){
            return 1;
        }else{
            if (this.next.compareTo(e.next) < 0){
                return -1;
            }else if (this.next.compareTo(e.next) > 0){
                return 1;
            }else{
                return this.label.compareTo(e.label);
            }
        }
    }

    // compare two Edge objects

    /**
     * Standard equal function
     * @param obj an object which need to be compared to this Edge
     * @return bool variable to evaluate if this Edge's value equal to obj's.
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj instanceof Edge){
            Edge<?, ?> e = (Edge<?, ?>)obj;
            return this.root.equals(e.root) && this.next.equals(e.next) && this.label.equals(e.label);
        }
        return false;
    }

    /** Standard hashCode function.
     @return an int that all objects equal to this will also
     return.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRoot(), getNext(), getLabel());
    }
}
