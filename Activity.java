import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Activity {
    private String name;
    public int id;
    private int duration;
    private Activity parent;
    private List<Activity> children;
    List <Predecessor> predecessors;

    public Activity(String name) {
        this.setName(name);
        this.children = new LinkedList<>();
        this.predecessors = new LinkedList<>();
    }
    
    // Predecessor List
    public Iterator<Predecessor> iterator()
	{
		return predecessors.iterator();
	}
    
    public boolean exists(String p2)
	{
		boolean exists = false;
		for(Predecessor p : predecessors)
		{
			if (p.getName().toLowerCase().equals(p2.toLowerCase()))
				exists = true;
		}
		return exists;
	}
    
    public void addPredecessor(String p) 
	{
		if(!exists(p))
		{
			Predecessor newP = new Predecessor(p);
			this.predecessors.add(newP);
		}
	}

    // Child Node
    public void addChild(Activity child) {
        this.children.add(child);
        child.setParent(this);
    }

    public List<Activity> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    private void setParent(Activity parent) {
        this.parent = parent;
    }

    public Activity getParent() {
        return this.parent;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}
}