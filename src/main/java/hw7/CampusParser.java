package hw7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CampusParser {

    /**
     * @param filename filename The path to the "CSV" file
     * @param nodesIds nodesIds The Map that stores parsed <Id, nodesName> pairs;
     *                 usually an empty Map
     * @param nodesLoc nodesLoc The Map that stores parsed <Id, nodesLocation> pairs;
     *                 usually an empty Map
     * @throws IOException if file cannot be read of file not a valid CSV file.
     */
    public static void readNodes(String filename, Map<String, String> nodesIds, Map<String, Map.Entry<Integer
            , Integer>> nodesLoc, Map<String, String> idNodes, Set<String> intersections)
    throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null){
            String[] info = line.split(",");
            if (info.length != 4){
                throw new IOException("File "+filename+" not a CSV (\"names\",\"ids\", \"x\",\"y\") file.");
            }
            String sub = info[0];
            if (info[0].equals("")){
                intersections.add(info[1]);
                sub = "Intersection " + info[1];
            }
            nodesIds.put(info[1], sub);
            idNodes.put(sub, info[1]);
            nodesLoc.put(sub, new AbstractMap.SimpleEntry<>(Integer.valueOf(info[2]), Integer.valueOf(info[3])));
        }
    }

    /**
     * @param filename filename The path to the "CSV" file
     * @param edges edges The Map that stores parsed <node, node> pairs;
     *              usually an empty Map
     * @throws IOException if file cannot be read of file not a valid CSV file
     */
    public static void readEdges(String filename, Map<String, HashSet<String>> edges) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null){
            String[] info = line.split(",");
            if (info.length != 2){
                throw new IOException("File "+filename+" not a CSV (\"nodes\",\"nodes\") file.");
            }
            edges.computeIfAbsent(info[0], k -> new HashSet<>());
            edges.get(info[0]).add(info[1]);
            edges.computeIfAbsent(info[1], k -> new HashSet<>());
            edges.get(info[1]).add(info[0]);
        }
    }
}
