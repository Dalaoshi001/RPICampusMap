package hw5;

import hw4.Edge;
import hw4.Graph;
import java.util.*;
import java.io.*;

public class MarvelParser{

	/** @param filename: filename The path to the "CSV" file that contains the <hero, book> pairs
        @param charsInBooks: charsInBooks The Map that stores parsed <book, Set-of-hero's-in-book> pairs;
        	    usually an empty Map
        @param chars: chars The Set that stores parsed characters; usually an empty Set.
        @effects adds parsed <book, Set-of-hero's-in-book> pairs to Map charsInBooks;
        		  adds parsed characters to Set chars
        @throws IOException if file cannot be read of file not a CSV file
	 */
    public static void readData(String filename, Map<String,Set<String>> charsInBooks, Set<String> chars)
    		throws IOException {

    	BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
             int i = line.indexOf("\",\"");
             if ((i == -1) || (line.charAt(0)!='\"') || (line.charAt(line.length()-1)!='\"')) {
            	 throw new IOException("File "+filename+" not a CSV (\"HERO\",\"BOOK\") file.");
             }             
             String character = line.substring(1,i);
             String book = line.substring(i+3,line.length()-1);
             
             // Adds the character to the character set. If character already in, add has no effect.
             chars.add(character);
             // Adds the character to the set for book
            Set<String> s = charsInBooks.computeIfAbsent(book, k -> new HashSet<>());
            s.add(character);
        }
    }

    /**
     * @param filename the file which we need to read data and build graph
     * @param g the graph we will build from the file
     * @effects build graph g based on the data from file
     */
    public static void buildGraph(String filename, Graph<String, String> g){
        Map<String, Set<String>> charsInBooks = new TreeMap<>();
        Set<String> chars = new TreeSet<>();
        try {
            readData(filename, charsInBooks, chars);
            for (Map.Entry<String, Set<String>> entry: charsInBooks.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> lists = new ArrayList<>(entry.getValue());
                for (int i = 0; i < lists.size(); i++) {
                    g.addNode(lists.get(i));
                    for (int j = i + 1; j < lists.size(); j++) {
                        g.addEdge(new Edge<>(lists.get(i), lists.get(j), key));
                        g.addEdge(new Edge<>(lists.get(j), lists.get(i), key));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }



//    public static void main(String[] arg) {
//
//    	String file = arg[0];
//
//    	try {
//    		Map<String, Set<String>> charsInBooks = new HashMap<String,Set<String>>();
//    		Set<String> chars = new HashSet<String>();
//    		readData(file,charsInBooks,chars);
//    		System.out.println("Read "+chars.size()+" characters who appear in "+charsInBooks.keySet().size() +" books.");
//
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
//
//    }
}
