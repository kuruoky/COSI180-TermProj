package brandeismap;

/**
 * Union find Implemented with map
 * @author Rex Zhang
 *
 */
public class UnionFind {
	IndexMap dic;
	Graph g;
	
	public UnionFind(Graph g) {
		dic = new IndexMap();
		this.g = g;
	}

	public int find(GraphNode x) {
		if (!dic.containsKey(x)) {
			dic.put(x, x.id);
		}
		if (dic.get(x) != x.id) {
			int pathCompression = this.find(g.vertex[dic.get(x)]);
			dic.put(x, pathCompression);
		}
		return dic.get(x);
	}

	public void union(GraphNode x, GraphNode y) {
		int rootY = find(y);
		dic.put(g.vertex[find(x)], rootY);
	}
}