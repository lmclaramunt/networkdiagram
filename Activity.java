import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <center>
 * <table cellpadding="5" cellspacing="5">
 *  <tr>
 *  <td valign="top">
 *   Course: CSE 360<br>
 *   Section Line Number: 89049<br>
 *   Project: Activity Network<br>
 *  </td>
 *  
 *  <td valign="top">
 *   Contributor: Anthony Benites,<br>
 *   Arizona State Univeristy<br>
 *  </td>
 * 
 *  <td valign="top">
 *   Contributor: Luis Claramunt <br>
 *   Arizona State Univeristy<br>
 *  </td>
 * 
 * <td valign="top">
 *   Contributor: Enrique Almaraz<br>
 *   Arizona State Univeristy<br>
 *  </td>
 *  </tr>
 * </table>
 * </center>
 */

public class Activity implements Comparable<Activity> {
    private String name;
    public int id, duration, rank;
    private Activity parent;
    private List<Activity> children;
    public List <String> predecessors;

    public Activity(String name) {
        this.setName(name);
        this.children = new LinkedList<>();
        this.predecessors = new LinkedList<>();
        this.duration = 0;
    }
    
    // Predecessor List
    public Iterator<String> iterator() { return predecessors.iterator(); }
    
    public boolean exists(String p2) {
		boolean exists = false;
		for(String p : predecessors) {
			if (p.toLowerCase().equals(p2.toLowerCase()))
				exists = true;
		}
		return exists;
	}
    
    public void addPredecessor(String p) {
		if(!exists(p)) {
			this.predecessors.add(p);
			this.rank = this.rank + 1;
		}
	}

    // Child Node
    public void addChild(Activity child) {
    	boolean exists = false;
    	for (Activity c: this.children) {
    		if (c.getName().equals(child.getName())) {
    			exists = true;
    		}
    	}
    	if (!exists) {
    		this.children.add(child);
    		child.setParent(this);
    	}
    }
	
	public String printPredecessors() {		
		String names = new String();
		for (int i = 0; i < predecessors.size(); i++) {
			names += predecessors.get(i) + " ";
		}
		return names;
	}
	
	public String toString() {
		String result = "Node: "  + name  + " \nPredecessors: " +
				printPredecessors() + "\nDuration: " + duration + "\n____________\n";				
		return result;
	}
	
	public int compareTo(Activity i) { return this.predecessors.size() - i.predecessors.size(); }
	
	public List<Activity> getChildren() { return Collections.unmodifiableList(this.children); }

    private void setParent(Activity parent) { this.parent = parent; }

    public Activity getParent() { return this.parent; }
    
    public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public int getDuration() { return duration; }

	public void setDuration(int duration) { this.duration = duration; }

}