package hw7;

import hw4.Edge;
import hw4.Graph;
import hw6.MarvelPaths2;


import java.io.IOException;
import java.util.*;

public class CampusModel{
    private Map<String, String> Idnames;
    private Map<String, Map.Entry<Integer, Integer>> nodesLoc;
    private Map<String, String> idNodes;
    private Graph<String, Double> g;
    private Set<String> intersections;
    private Map<String, String> directions;

    /**
     * This is helper function to calculate the direction of some angle
     * @param angle the angle we input
     * @return the direction for our map
     */
    private String getDirection(double angle){
        if (angle >= 22.5 && angle < 67.5){
            return "NorthEast";
        }else if (angle >= 67.5 && angle < 112.5){
            return "North";
        }else if (angle >= 112.5 && angle < 157.5){
            return "NorthWest";
        }else if (angle >= 157.5 && angle < 202.5 ){
            return "West";
        }else if (angle >= 202.5 && angle < 247.5){
            return "SouthWest";
        }else if (angle >= 247.5 && angle < 292.5){
            return "South";
        }else if (angle >= 292.5 && angle < 337.5){
            return "SouthEast";
        }else{
            return "East";
        }
    }

    /**
     * This method will create new model
     * @param filename1 filename1 the first file which parsed the information about nodes
     * @param filename2 filename2 the second file which parsed the information about the relationship between nodes
     */
    public void createNewModel(String filename1, String filename2){
        Idnames = new HashMap<>();
        nodesLoc = new HashMap<>();
        Map<String, HashSet<String>> edges = new HashMap<>();
        g = new Graph<>();
        idNodes = new HashMap<>();
        intersections = new HashSet<>();
        directions = new HashMap<>();
        try {
            CampusParser.readNodes(filename1, Idnames, nodesLoc, idNodes, intersections);
            CampusParser.readEdges(filename2, edges);
            for (String nodes : Idnames.keySet()){
                g.addNode(Idnames.get(nodes));
            }
            for (Map.Entry<String, HashSet<String>> entry : edges.entrySet()){
                for (String value : entry.getValue()) {
                    double x_differ = nodesLoc.get(Idnames.get(value)).getKey() -
                            nodesLoc.get(Idnames.get(entry.getKey())).getKey();
                    double y_differ = nodesLoc.get(Idnames.get(value)).getValue()
                            - nodesLoc.get(Idnames.get(entry.getKey())).getValue();
                    double angle = Math.toDegrees(Math.atan2(0 - y_differ,  x_differ)); // calculate angle
                    while (angle < 0){
                        angle = 360 + angle;
                    }
                    while (angle > 360){
                        angle -= 360;
                    }
                    Edge<String, Double> e = new Edge<>(Idnames.get(entry.getKey()), Idnames.get(value),
                            Math.pow(Math.pow(x_differ, 2) + Math.pow(y_differ, 2), 0.5));

                    g.addEdge(e);
                    directions.put(e.getRoot() + "??"+e.getNext(), getDirection(angle));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param start start the start node
     * @param end end the end node
     * @return the shortest path from start node to the end node
     */
    public List<Edge<String, Double>> findPath(String start, String end){
        return MarvelPaths2.findPathAlgorithm(start, end, g);
    }

    /**
     * @param node node the node we want to search information
     * @return the id of this node
     */
    public String getName(String node){
        return Idnames.get(node);
    }

    /**
     * This is function for us to get all nodes names
     * @return the set of names for our nodes
     */
    public Set<String> allIds(){
        return Idnames.keySet();
    }

    /**
     * This is function for calculating the direction of our edge
     * @requires e cannot be null
     * @param e the edge we input
     * @return the direction for this edge
     */
    public String getDirect(Edge<String, Double> e){
        return directions.get(e.getRoot() + "??" + e.getNext());
    }

    /**
     * boolean function to evaluate if this id is in our intersection
     * @param id the id we input
     * @return true if this id is intersection, false other wise
     */
    public boolean isIntersection(String id){
        return intersections.contains(id);
    }

    /**
     * This function will get ID if we input this building's name
     * @param name the name of the valid building
     * @return the id of this building
     */
    public String ToID(String name){
        return idNodes.get(name);
    }

    /**
     * Evaluate our input is node name or node id
     * @param undefined the input of node name or id
     * @return true if this indicates a valid node, false other wise
     */
    public boolean IsNodes(String undefined){
        return idNodes.containsKey(undefined) || Idnames.containsKey(undefined);
    }
}
