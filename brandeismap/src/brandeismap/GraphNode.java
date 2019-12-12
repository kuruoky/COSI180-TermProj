package brandeismap;

/**
 * A graph node class with adjacency matrix
 * @author Rex Zhang
 *
 */
public class GraphNode {
	Graph g;
	int id;
	String label;
	int x;
	int y;
	String name;
	double priority = Integer.MAX_VALUE;
	boolean isHome = false;
	boolean isGoal = false;
	SinglyLinkedList<GraphEdge> children = new SinglyLinkedList<GraphEdge>();
	GraphEdge activeEdge;
	SinglyLinkedList<GraphEdge> treeEdge = new SinglyLinkedList<GraphEdge>();;

	public GraphNode(int id, String label, int x, int y, String name) {
		this.id = id;
		this.label = label;
		this.x = x;
		this.y = y;
		this.name = name;
	}
		
	public int getId() {
		return this.id;
	}
	
	public void addEdge(GraphEdge edge) {
		children.insert(edge);
	}
	
	public String toString() {
		return String.format("%d %s %d %d %s", id, label, x, y, name);
	}
	
	public String printNode() {
		return String.format("(%s) %s", label, name);
	}
}