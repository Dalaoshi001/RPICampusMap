package hw7;

import java.util.Scanner;

public class CampusPaths{
    public static void main(String[] arg){
        CampusModel cp = new CampusModel();
        cp.createNewModel("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv"); // read file
        Scanner scan = new Scanner(System.in);

        while (true){
            String str = scan.nextLine();
            if (str.equals("q")){
                break;
            }
            String line1 = "";
            String line2 = "";
            if (str.equals("r")){
                System.out.print("First building id/name, followed by Enter: ");
                line1 += scan.nextLine();
                System.out.print("Second building id/name, followed by Enter: ");
                line2 += scan.nextLine();
            }
            ViewerFactory vf = new ViewerFactory();
            CampusViewer cv = vf.getViewer(str, line1, line2);
            if (cv == null){
                System.out.println("Unknown option");
            }else{
                cv.display(cp);
            }
        }
    }
}