package hw7;

import org.junit.Test;

import static org.junit.Assert.*;

public class ViewerFactoryTest {

    @Test
    public void generalTest(){
        ViewerFactory vf = new ViewerFactory();
        CampusModel cm = new CampusModel();
        cm.createNewModel("data/AolinGardenNodes.csv", "data/AolinGardenEdges.csv");
        vf.getViewer("b", "", "").display(cm);
        vf.getViewer("r", "1", "2").display(cm);
        vf.getViewer("r", "0", "9").display(cm);
        vf.getViewer("m", "", "").display(cm);
    }
}