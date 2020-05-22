package hw5;

import hw4.Edge;
import hw4.Graph;

import java.util.*;

public class MarvelPaths extends Path<String, String> {

    /**
     * @param filename the file we will read data and create graph
     * @effects create graph by the data from file
     */
    public void createNewGraph(String filename){
        g = new Graph<>();
        MarvelParser.buildGraph(filename, g);
    }

    /**
     * @param node1 the start node which we will find path
     * @param node2 the destination node which we will find the path
     * @return the path from start node to the destination node.
     */
    public String findPath(String node1, String node2){
        Queue<String> queue = new LinkedList<>();
        Map<String, ArrayList<String>> map = new HashMap<>();
        queue.offer(node1);
        map.put(node1, new ArrayList<>());
        boolean flag = false;
        String s2 = UnknownChar(node1, node2);

        // Deal with invalid operations
        if (s2 != null){
            return  s2;
        }
        String n = node1;
        StringBuilder result;
        result = new StringBuilder("path from " + node1 + " to " + node2 + ":\n");
        if (node1.equals(node2)){
            return result.toString();
        }

        // Keep going to go over every node
        while (!queue.isEmpty()) {
            n = queue.poll();
            if (n.equals(node2)) {
                flag = true;
                break;
            }
            Set<Edge<String, String>> edges = g.getEdge(n);
            for (Edge<String, String> e : edges) {
                if (!map.containsKey(e.getNext())) {
                    String path = e.getRoot() + " to " + e.getNext() + " via " + e.getLabel();
                    ArrayList<String> paths = new ArrayList<>(map.get(e.getRoot()));
                    paths.add(path);
                    map.put(e.getNext(), paths);
                    queue.offer(e.getNext());
                }
            }
        }
        if (flag){
            ArrayList<String> s1 = map.get(n);
            for (String s : s1) {
                result.append(s).append("\n");
            }
        }else{
            result.append("no path found\n");
        }
        return result.toString();
    }

}