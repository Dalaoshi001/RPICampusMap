package hw6;

import hw4.Edge;
import hw4.Graph;
import hw5.MarvelParser;
import hw5.Path;

import java.util.Set;

import java.util.*;

public class MarvelPaths2 extends Path<String, Double> {

    /**
     * @param fileName the name of the file
     * @effects create graph by the data from file
     */
    @Override
    public void createNewGraph(String fileName){
        // Try to initialize the variable
        Map<String, Set<String>> charInBooks = new TreeMap<>();
        Set<String> chars = new HashSet<>();
        Map<String, Map<String, Edge<String, Double>>> ma = new HashMap<>();
        try{ // If the fileName is invalid try to catch exception and print wrong information
            MarvelParser.readData(fileName, charInBooks, chars);
            g = new Graph<>();
            for (String aChar : chars) { // add every node to the graph
                g.addNode(aChar);
            }

            // Try to add edge to the map and prepare for edge in the graph
            for (Map.Entry<String, Set<String>> entry : charInBooks.entrySet()) {
                ArrayList<String> arr = new ArrayList<>(entry.getValue());
                for (int i = 0; i < arr.size() - 1; i++) {
                    for (int j = i + 1; j < arr.size(); j++) {
                        ma.computeIfAbsent(arr.get(i), k -> new HashMap<>());
                        ma.computeIfAbsent(arr.get(j), k -> new HashMap<>());
                        if (ma.get(arr.get(i)).get(arr.get(j)) == null) {
                            ma.get(arr.get(i)).put(arr.get(j), new Edge<>(
                                    arr.get(i), arr.get(j), 1.0
                            ));
                            ma.get(arr.get(j)).put(arr.get(i), new Edge<>(
                                    arr.get(j), arr.get(i), 1.0
                            ));
                        } else {
                            double d = ma.get(arr.get(i)).get(arr.get(j)).getLabel();
                            ma.get(arr.get(i)).get(arr.get(j)).setLabel(d+1);
                            ma.get(arr.get(j)).get(arr.get(i)).setLabel(d+1);
                        }
                    }
                }
            }
            // add edges to the graph
            for (Map.Entry<String, Map<String, Edge<String, Double>>> entry: ma.entrySet()){
                Map<String, Edge<String, Double>> mp = entry.getValue();
                for (Map.Entry<String, Edge<String, Double>> entry1: mp.entrySet()){
                    Edge<String, Double> e = entry1.getValue();
                    e.setLabel(1.0 / e.getLabel());
                    g.addEdge(e);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This is the main algorithm method for us to utilize Dijkstra and maintains the concept for open/closed
     * @param start start the start node
     * @param end end the destination node
     * @param g the graph which we will search for node
     * @return the list of edges which we experience
     */
    public static List<Edge<String, Double>> findPathAlgorithm(String start, String end, Graph<String, Double> g){
        if (start == null || end == null){
            return null;
        }
        // Initialize variables
        Comparator<Edge<String, Double>> order = Comparator.comparing(Edge::getLabel);
        PriorityQueue<Edge<String, Double>> active = new PriorityQueue<>(order);
        Set<String> finished = new HashSet<>();
        Map<String, ArrayList<Edge<String, Double>>> map = new HashMap<>();
        Map<String, Double> totalDistance = new HashMap<>();
        boolean flag = false;

        // Dijkstra's algorithm
        Double minPath;
        String minDest = start;
        Edge<String, Double> temp = null;
        active.add(new Edge<>(start, start, 0.000));
        totalDistance.put(start, 0.000);
        map.put(start, new ArrayList<>());
        while (!active.isEmpty()){ // keep going if there are still unvisited edges
            temp = active.poll();
            minPath = temp.getLabel();
            minDest = temp.getNext();
            if (finished.contains(minDest)){ // which means this node has been visited
                continue;
            }
            if (!temp.getNext().equals(start)){ // try to add the information to the new node
                ArrayList<Edge<String, Double>> s = map.get(temp.getRoot());
                ArrayList<Edge<String, Double>> p = new ArrayList<>(s);
                p.add(new Edge<>(temp.getRoot(), temp.getNext(),
                        totalDistance.get(temp.getNext()) - totalDistance.get(temp.getRoot())));
                map.put(minDest, p);
            }
            if (minDest.equals(end)){ // which means it reaches the destination.
                flag = true;
                break;
            }
            Set<Edge<String, Double>> edges = g.getEdge(minDest);
            for(Edge<String, Double> e: edges){ // update the distance to the start node and decrease Key
                if (!finished.contains(e.getNext())){
                    if (totalDistance.get(e.getNext()) == null || totalDistance.get(e.getNext()) > minPath + e.getLabel()) {
                        Double newPath = minPath + e.getLabel();
                        totalDistance.put(e.getNext(), newPath);
                        active.add(new Edge<>(e.getRoot(), e.getNext(), newPath));
                    }
                }
            }
            finished.add(minDest);
        }
        if (flag){
            return map.get(minDest);
        }else{
            return null;
        }

    }

    /**
     *
     * @param start the string node which start the path
     * @param end the destination node
     * @return the path from start node to the end node
     */
    public String findPath(String start, String end){
        String s2 = UnknownChar(start, end);
        if (s2 != null){
            return  s2;
        }
        StringBuilder result;

        // utilize result to store the information
        result = new StringBuilder("path from " + start + " to " + end + ":\n");
        if (start.equals(end)){
            result.append("total cost: 0.000\n");
            return result.toString();
        }
        List<Edge<String, Double>> resultList = findPathAlgorithm(start, end, g);

        if (resultList == null){
            result.append("no path found\n");
        }else{
            double totalCost = 0.0;
            for (Edge<String, Double> edge : resultList){
                result.append(edge.getRoot()).append(" to ").append(edge.getNext())
                        .append(String.format(" with weight %.3f\n", edge.getLabel()));
                totalCost += edge.getLabel();
            }
            result.append(String.format("total cost: %.3f\n", totalCost));
        }
        return result.toString();
    }
}