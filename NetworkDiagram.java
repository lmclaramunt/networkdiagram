import java.util.List;
import java.util.Scanner;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class NetworkDiagram {
	static Activity[] nodes;
    static List<Activity> activities;
	
	public static String createTree(List<Activity> listActivity) {
    	// add activities
		activities = listActivity;
    	nodes = new Activity[activities.size()];
    	
    	// Create Tree
    	int count = 0;
        for(Activity activity : activities) {
            nodes[count] = activity;
            nodes[count].setId(count);
            count++;
        }
        
        // Add predecessors as parents
        for(Activity node : nodes) {
        	for (Predecessor p : node.predecessors) {
        		for(Activity n : nodes) {
        			if (n.getName().equals(p.getName())) {
        				nodes[n.id].addChild(node);
        			}
        		}
        	}
        }
        return listPaths() + "\n";
    }
    
    public static String listPaths() {
        List<List<Activity>> lists = Paths.getPaths(nodes[0]);
        String output = "";
        for(List<Activity> list : lists) {
            for(int count = 0; count < list.size(); count++) {
                //System.out.print(list.get(count).getName());
                output += list.get(count).getName();
                if(count != list.size() - 1) {
                    //System.out.print("-");
                    output += "-";
                }
            }
           // System.out.println();
            output += "\n";
        }
        return output;
    }
    
    public Iterator<Activity> iterator()
	{
		return activities.iterator();
	}
    
}