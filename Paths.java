
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Paths {
    private static List<List<Activity>> getPaths0(Activity pos) {
        List<List<Activity>> retLists = new ArrayList<>();

        if(pos.getChildren().size() == 0) {
            List<Activity> leafList = new LinkedList<>();
            leafList.add(pos);
            retLists.add(leafList);
        } else {
            for (Activity node : pos.getChildren()) {
                List<List<Activity>> nodeLists = getPaths0(node);
                for (List<Activity> nodeList : nodeLists) {
                    nodeList.add(0, pos);
                    retLists.add(nodeList);
                }
            }
        }

        return retLists;
    }
    public static List<List<Activity>> getPaths(Activity head) {
        if(head == null) {
            return new ArrayList<>();
        } else {
            return getPaths0(head);
        }
    }
    
    public static ArrayList<Integer> pathDuration(List<List<Activity>> list) {
    	ArrayList<Integer> pathsDuration = new ArrayList<Integer>();
    	int duration = 0;
    	for (int i = 0; i < list.size(); i++) {
    		duration = 0;
    		for(int j = 0; j < list.get(i).size(); j++) {
    			duration += list.get(i).get(j).getDuration();
    		}
    		pathsDuration.add(duration);
    	}   	
    	return pathsDuration;
    }
}