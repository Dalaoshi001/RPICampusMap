package hw7;

import hw4.Edge;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CampusModelTest {

    @Test
    public void GeneralTest(){
        CampusModel cp = new CampusModel();
        cp.createNewModel("data/AolinGardenNodes.csv", "data/AolinGardenEdges.csv");
        List<Edge<String, Double>> li = cp.findPath("Gate", "Zhou");
        StringBuilder str = new StringBuilder();
        for (Edge<String, Double> e : li){
            str.append(e.getRoot()).append(" -> ").append(e.getNext()).append(": ").append(String.format("%.3f", e.getLabel())).append("\n");
        }
        assertEquals(str.toString(), "Gate -> Mountain: 1.414\nMountain " +
                "-> Zhou: 2.236\n");
        assertEquals(cp.getDirect(new Edge<>("Gate", "Mountain", 1.414)), "SouthEast");
        assertFalse(cp.isIntersection("Gate"));
        assertTrue(cp.IsNodes("Gate"));
        assertEquals(cp.ToID("Gate"), "1");
        cp.allIds();
        assertEquals(cp.getName("1"), "Gate");
    }
}