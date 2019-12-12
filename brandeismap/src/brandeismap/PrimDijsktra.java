package brandeismap;

import java.io.FileNotFoundException;

/**
 * Getting minimum spanning tree for Campus tour
 * @author Rex Zhang
 *
 */
public class PrimDijsktra {
	Graph g;
	GraphNode home;
	boolean skateboard;
	boolean minTime;
	Prim prim;
	Dijsktra dijkstra;
	SinglyLinkedList<GraphEdge> tour;

	public PrimDijsktra(Graph g, GraphNode home, boolean skateboard, boolean minTime) {
		this.g = g;
		this.home = home;
		this.skateboard = skateboard;
		this.minTime = minTime;
		prim = new Prim(g, home, skateboard, minTime);
	}

	public SinglyLinkedList<GraphEdge> getCampusTour() throws FileNotFoundException {
		prim.prim();
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
					if (curtStart.id != lastDest.id && lastDest.activeEdge != null) {
						GraphNode curtEnd = e.end;
						Graph newG = cloneGraph(lastDest, curtEnd);
						Dijsktra dijkstra = new Dijsktra(newG, newG.home, newG.goal, skateboard, minTime);
						SinglyLinkedList<GraphEdge> shortestPath = dijkstra.getMinPath();
						LinkedListNode<GraphEdge> shortCurt = shortestPath.head;
						while (shortCurt != null) {
							tour.insert(shortCurt.getData());
							shortCurt = shortCurt.next;
						}
						node = g.vertex[newG.goal.id];
					}
				}
				if (tour.size == 0 || tour.tail.getData().end.id != e.end.id) {
					tour.insert(e);
				}
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
	
	public Graph cloneGraph(GraphNode lastDest, GraphNode curtEnd) throws FileNotFoundException {
		Graph newG = new Graph();
		GraphNode newLastDest = newG.vertex[lastDest.id];
		GraphNode newCurtEnd = newG.vertex[curtEnd.id];
		newLastDest.isHome = true;
		newCurtEnd.isGoal = true;
		newG.home = newLastDest;
		newG.goal = newCurtEnd;
		return newG;
	}
}