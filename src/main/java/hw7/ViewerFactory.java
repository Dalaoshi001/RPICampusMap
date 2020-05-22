package hw7;

public class ViewerFactory {

    /**
     * The Factory for us to decide which command we will implement
     * @param type the type of the command
     * @param start the start node, sometimes is empty
     * @param end the end node, sometimes is empty
     * @return the corresponding commandViewer
     */
    public CampusViewer getViewer(String type, String start, String end){
        if (type == null){
            return null;
        }
        if (type.equals("b")){
            return new CampusBuildingViewer();
        }
        if (type.equals("r")){
            return new CampusPathViewer(start, end);
        }
        if (type.equals("m")){
            return new CampusCommandViewer();
        }
        return null;
    }
}
