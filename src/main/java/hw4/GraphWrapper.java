package hw4;

import java.util.Iterator;

public class GraphWrapper {

    private Graph<String, String> gw;

    /**
     * Create a new GraphWrapper object
     */
    public GraphWrapper(){
        gw = new Graph<>();
    }

    /**
     * @param nodeData the node will be added to the GraphWrapper
     * @effects add the nodeData to the GraphWrapper
     */
    public void addNode(String nodeData){
        gw.addNode(nodeData);
    }

    /**
     * @param parentNode the parent node of edge
     * @param childNode the children node of parent
     * @param edgeLabel the label of this edge
     */
    public void addEdge(String parentNode, String childNode, String edgeLabel){
        gw.addEdge(new Edge<>(parentNode, childNode, edgeLabel));
    }

    /**
     * @return the iterator of the current String
     */
    public Iterator<String> listNodes(){
        return gw.getNodes().iterator();
    }

    /**
     * @param parentNode the node we want to find
     * @return the children of this node
     */
    public Iterator<String> listChildren(String parentNode){
        return gw.getChild(parentNode).iterator();
    }
}