import java.util.List;
import java.util.Collections;
import java.util.Iterator;

public class NetworkDiagram {
    private static List<Activity> activities;
	
	public static String createTree(List<Activity> listActivity) {
    	// add activities
		activities = listActivity;
		Collections.sort(activities);
		Activity[] nodes = new Activity[activities.size()];
    	
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
        		int pathDuration = 0;				//Check where to actually put it 
        		for(Activity n : nodes) {
        			if (n.getName().equals(p.getName())) {
        				nodes[n.id].addChild(node);  
        			}
        		}
        	}
        }
        return listPaths();
        
        // ListPaths
        List<List<Activity>> lists = Paths.getPaths(nodes[0]);
        ArrayList<Integer> pathsDuration = Paths.pathDuration(lists);
        String output = "";
        int i = 0;								//To print the durations
        for(List<Activity> list : lists) {
            for(int count = 0; count < list.size(); count++) {
                output += list.get(count).getName();
                if(count != list.size() - 1) {
                    output += "-";
                }              
            }                     
            output += "  " + pathsDuration.get(i) + "\n";
            i++;
            System.out.println(i);            
        }
        return output;
    }
   
    public Iterator<Activity> iterator() {
		  return activities.iterator();
	}   
}