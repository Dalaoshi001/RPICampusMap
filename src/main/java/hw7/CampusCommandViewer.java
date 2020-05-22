package hw7;

public class CampusCommandViewer implements CampusViewer{

    /**
     * Display valid command
     * @param cp the model of our campus map
     */
    @Override
    public void display(CampusModel cp) {
        System.out.println("b lists all buildings (only buildings) in" +
                " the form name,id in lexicographic (alphabetical) order of name.");
        System.out.println("r prompts the user for the ids or names of two " +
                "buildings (only buildings!) and prints directions for the shortest route between them.");
        System.out.println("q quits the program.");
        System.out.println("m prints a menu of all commands.");
    }
}
