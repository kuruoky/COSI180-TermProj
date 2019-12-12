package brandeismap;

/**
 * Using Kruskal's Algorithm and UnionFind to generate minimum spanning tree.
 * Also constructs back tracing paths to make it a campus tour.
 * @author Rex Zhang
 *
 */
public class Kruskal {
	Graph g;
	GraphNode home;
	boolean skateboard;
	boolean minTime;
	UnionFind uf;
	
	public Kruskal(Graph g, GraphNode home, boolean skateboard, boolean minTime) {
		this.g = g;
		this.home = home;
		this.skateboard = skateboard;
		this.minTime = minTime;
		this.uf = new UnionFind(g);
	}

	public SinglyLinkedList<GraphEdge> getCampusTour() {
		kruskal();
		SinglyLinkedList<GraphEdge> tour = new SinglyLinkedList<GraphEdge>();
		SinglyLinkedList<GraphNode> stack = new SinglyLinkedList<GraphNode>();
		stack.insert(home);
		int[] seen = new int[g.vertex.length];
		while (stack.size > 0) {
			GraphNode node = stack.pop(stack.size - 1);
			seen[node.id] = 1;
			if (node.activeEdge != null) {
				GraphEdge e = node.activeEdge;
				if (tour.size > 0) {
					GraphNode lastDest = tour.tail.getData().end;
					GraphNode curtStart = e.start;
					while (curtStart != lastDest && lastDest.activeEdge != null) {
						GraphEdge rEdge = lastDest.activeEdge.getReverse();
						tour.insert(rEdge);
						lastDest = tour.tail.getData().end;
					}
				}
				tour.insert(e);
			}
			while (node.treeEdge.size > 0) {
				GraphEdge e = node.treeEdge.pop(0);
				if (seen[e.end.id] == 0) {
					stack.insert(e.end);
					e.end.activeEdge = e;
				}
			}
		}
		return tour;
	}

	public SinglyLinkedList<GraphEdge> kruskal() {
		for (int i = 5; i < g.vertex.length; i++) {
			uf.find(g.vertex[i]);
		}
		for (int i = 20; i < g.edges.length; i++) {
			g.edges[i].getTime(skateboard);
		}
		QuickSort.qs(g.edges, minTime);
		SinglyLinkedList<GraphEdge> treePaths = new SinglyLinkedList<GraphEdge>();
		for (int i = 0; i < g.edges.length; i++) {
			GraphEdge e = g.edges[i];
			GraphNode x = e.start;
			GraphNode y = e.end;
			if (x.id < 5 || y.id < 5) {
				continue;
			}
			int xFind = uf.find(x);
			int yFind = uf.find(y);
			if (xFind != yFind && yFind != uf.find(home)) {
				uf.union(y, x);
				treePaths.insert(e);
				GraphEdge revE = e.getReverse();
				treePaths.insert(revE);
			}
		}
		LinkedListNode<GraphEdge> curt = treePaths.head;
		while (curt != null) {
			GraphEdge e = curt.getData();
			e.start.treeEdge.insert(e);
			curt = curt.next;
		}
		
		return treePaths;
	}
}