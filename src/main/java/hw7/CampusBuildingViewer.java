package hw7;

import java.util.Set;
import java.util.TreeSet;

public class CampusBuildingViewer implements CampusViewer{

    /**
     * Display all valid buildings in our map
     * @param cp the model of our map
     */
    @Override
    public void display(CampusModel cp) {
        Set<String> allBuildings = new TreeSet<>();
        for (String building : cp.allIds()){
            if (cp.isIntersection(building)){
                continue;
            }
            allBuildings.add(cp.getName(building));
        }
        for (String building : allBuildings){
            System.out.println(building + ","+cp.ToID(building));
        }
    }
}
