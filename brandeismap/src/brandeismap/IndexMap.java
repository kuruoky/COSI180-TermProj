package brandeismap;

/**
 * A hash map implementation
 * key = GraphNode.id; value = BinaryHeap index
 * @author Rex Zhang
 *
 */
public class IndexMap {
	int[] table;

	public IndexMap() {
		table = new int[152];
		for (int i = 0; i < 152; i++) {
			table[i] = -1;
		}
	}

	public void put(GraphNode key, int value) {
		table[key.id] = value;
	}

	public int get(GraphNode g) {
		return table[g.id];
	}

	public boolean containsKey(GraphNode g) {
		return table[g.id] != -1;
	}

	public void delete(int i) {
		if (0 <= i && i < table.length) {
			table[i] = -1;
		}
	}

	public void delete(GraphNode g) {
		delete(g.id);
	}
}