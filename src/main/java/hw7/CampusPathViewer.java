package hw7;

import hw4.Edge;

import java.util.List;

public class CampusPathViewer implements CampusViewer{
    private String start;
    private String end;

    /**
     * The constructor of our CampusPathViewer
     * @param first the start node
     * @param second the end node
     */
    public CampusPathViewer(String first, String second){
        start = first;
        end = second;
    }

    /**
     * Display the information about our path
     * @param cp the campus model
     */
    @Override
    public void display(CampusModel cp) {
        boolean flag = false;
        if (cp.isIntersection(start)){
            flag = true;
            System.out.println("Unknown building: ["+ start+"]");
        }
        if (cp.isIntersection(end) && !start.equals(end)){
            flag = true;
            System.out.println("Unknown building: [" + end + "]");
        }
        if (flag){
            return;
        }
        if (!cp.IsNodes(start)){
            flag = true;
            System.out.println("Unknown building: ["+ start + "]");
        }
        if ( !cp.IsNodes(end) && !start.equals(end)){
            flag = true;
            System.out.println("Unknown building: ["+ end + "]");
        }
        if (flag){
            return;
        }
        if (cp.getName(start)!= null){
            start = cp.getName(start);
        }
        if (cp.getName(end) != null){
            end = cp.getName(end);
        }
        List<Edge<String, Double>> result = cp.findPath(start, end);
        if (result == null){
            System.out.println("There is no path from "+ start + " to " + end + ".");
        }else{
            System.out.println("Path from " + start + " to " + end + ":");
            double distance = 0.0;
            for (Edge<String, Double> e : result){
                System.out.println("\tWalk " + cp.getDirect(e) + " to (" + e.getNext() + ")");
                distance += e.getLabel();
            }
            System.out.println(String.format("Total distance: %.3f pixel units.", distance));
        }
    }
}
