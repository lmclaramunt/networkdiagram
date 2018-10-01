import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;

public class NetworkDiagram {
	static Activity[] nodes;
    static List<Activity> activities;
	private static boolean start, quit;
	static Scanner input;
    
    public static void main(String[] args) {
    	start = true;
		quit = false;
		Scanner input = new Scanner(System.in);
		
		while (!quit)
		{
			if (start)
			{
				System.out.println("\nWhat would you like to do? (Enter number only)");
				System.out.println("1: Start new Network");
				System.out.println("2: Quit");
				
				String response = input.next();
				
				switch (response)
				{
				    case "1": 
				    	activities = new LinkedList<>();
				    	start = false;
				    	break;
				    case "2":
				    	quit = true;
				    	break;
				    default: 
				    	System.out.println("Your selection was invalid.");
				}
			}
			else
			{
				System.out.println("\nWhat would you like to do? (Enter number only)");
				System.out.println("1: Add Activity");
				System.out.println("2: Print Network");
				System.out.println("3: Quit");
				
				String response = input.next();
				
				switch (response)
				{
				    case "1": 
				    	addActivity(input);
				    	break;
				    case "2": 
				    	createTree();
				    	break;
				    case "3": 
				    	quit = true;
				    	break;
				    default: 
				    	System.out.println("Your selection was invalid.");
				}
			}
		}
		System.out.println("Goodbye!");
	}
	
	public static void addActivity(Scanner input)
	{
		System.out.println("Activity: ");
		String activity = input.next();
		Activity a = new Activity(activity);
		
		System.out.println("Predecessor? (y/n): ");
		String pYN = input.next();
		
		while (pYN.toLowerCase().equals("y")) {
			System.out.println("Predecessor for " + activity + ": ");
			String p = input.next();
			a.addPredecessor(p);
			
			System.out.println("More predecessors? (y/n): ");
			pYN = input.next();
		}
		
		int duration = getInt("Duration: " + activity + "?", 1, 1000, input);
		a.setDuration(duration);
		
		try
		{
			activities.add(a);
		}
		catch (Exception e)
		{
			System.out.println("Error adding activity.");
		}
	}
	
	private static int getInt(String prompt, int min, int max, Scanner input)
    {
    	int i;
    	
    	while (true)
    	{
    		System.out.println(prompt);
    		try
    		{
    			i = input.nextInt();
    			if(i < min || i > max)
		        {
		        	while(i < min || i > max)
		        	{
		        		System.out.println("Please enter an integer between " + min + " and " + max);
		        		i = input.nextInt();
		        	}
		        }
    		}
    		catch (Exception e)
    		{
    			System.out.println("Please enter an integer between " + min + " and " + max);
    			input.next();
    			continue;
    		}
    		if (i >= min && i <= max)
    			return i;
    	}
    }
	
	private static void createTree() {
    	// add activities
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
        listPaths();
    }
    
    public static void listPaths() {
        List<List<Activity>> lists = Paths.getPaths(nodes[0]);
        for(List<Activity> list : lists) {
            for(int count = 0; count < list.size(); count++) {
                System.out.print(list.get(count).getName());
                if(count != list.size() - 1) {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
    
    public Iterator<Activity> iterator()
	{
		return activities.iterator();
	}
}