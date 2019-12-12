package brandeismap;

/**
 * A min priority queue utilizing binary min heap.
 * @author Rex Zhang
 *
 */
public class MinPriorityQueue extends BinaryMinHeap {
	public MinPriorityQueue() {
		super();
	}

	public GraphNode poll() {
		return super.pop();
	}
	
	public void offer(GraphNode g) {
		super.push(g);
	}

	public void setPriority(GraphNode g, double newPriority) {
		g.priority = newPriority;
		heapify();
	}
	public boolean contains(GraphNode g) {
		return hashMap.containsKey(g);
	}

	public void decreaseKey(GraphNode g, double newPriority) {
		int index = hashMap.get(g);
		if (newPriority >= g.priority || index == -1) {
			return;
		}
		A[index].priority = newPriority;
		heapify_up(index);
	}

	public void increaseKey(GraphNode g, double newPriority) {
		int index = hashMap.get(g);
		if (newPriority <= g.priority || index == -1) {
			return;
		}
		A[index].priority = newPriority;
		heapify_down(index);
	}
}