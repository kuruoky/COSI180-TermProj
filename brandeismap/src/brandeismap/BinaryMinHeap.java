package brandeismap;

/**
 * A typical binary Min heap
 * @author Rex Zhang
 *
 */
public class BinaryMinHeap {
	protected GraphNode[] A;
	protected IndexMap hashMap = new IndexMap();
	protected int size;
	
	public BinaryMinHeap() {
		size = 0;
		A = new GraphNode[2];
	}

	public void push(GraphNode g) {
		if (size >= A.length - 1) {
			GraphNode[] B = new GraphNode[A.length * 2];
			for (int i = 0; i < A.length; i++) {
				B[i] = A[i];
			}
			A = B;
		}
		A[size] = g;
		hashMap.put(g, size);
		size += 1;
		heapify_up(size - 1);
	}


	public GraphNode pop() {
		if (size <= 0) {
			return null;
		}
		GraphNode res = A[0];
		A[0] = A[size - 1];
		hashMap.put(A[0], 0);
		size -= 1;
		if (size <= A.length / 4) {
			GraphNode[] B = new GraphNode[A.length / 2];
			for (int i = 0; i < B.length; i++) {
				B[i] = A[i];
			}
			A = B;
		}
		heapify_down(0);
		return res;
	}

	public boolean contains(GraphNode node) {
		return hashMap.containsKey(node);
	}

	public void rebalance() {
		heapify();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void heapify() {
		for (int i = size / 2; i >= 0; i--) {
			heapify_down(i);
		}
	}

	public void heapify_down(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < size && A[l].priority < A[smallest].priority) {
			smallest = l;
		}
		if (r < size && A[r].priority < A[smallest].priority) {
			smallest = r;
		}
		if (smallest != i) {
			swap(i, smallest);
			heapify_down(smallest);
		}
	}

	public void heapify_up(int i) {
		int parent = parent(i);
		while (i > 0 && A[i].priority < A[parent].priority) {
			swap(i, parent);
			i = parent;
			parent = parent(i);
		}
	}
	
	public int left(int i) {
		return 2 * i + 1;
	}
	
	public int right(int i) {
		return 2 * i + 2;
	}
	
	public int parent(int i) {
		return (i - 1) / 2;
	}

	public void swap(int i, int p) {
		GraphNode temp = A[i];
		A[i] = A[p];
		A[p] = temp;
		hashMap.put(A[i], i);
		hashMap.put(A[p], p);
	}


	public String toString() {
		String res = "";
		for (int i = 0; i < size; i++) {
			res += i + ": " + A[i].getId() + " <" + A[i].priority + "> [" + hashMap.get(A[i]) + "]" + "\n";
		}
		return res;
	}
}