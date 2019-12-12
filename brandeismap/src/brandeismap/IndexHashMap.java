package brandeismap;

/**
 * HashMap Implementation
 * Used probing to handle collisions.
 * @author Rex Zhang
 *
 */
public class IndexHashMap {

	protected Entry[] table;
	protected int size;
	protected double loadNum;
	protected int[] PRIMES = {47, 103, 211, 443, 887, 1879, 4001};
	
	public IndexHashMap() {
		this.size = PRIMES[0];
		table = new Entry[size];
		loadNum = 0;
	}
	
	public int hashFunction(GraphNode key) {
		String id = (String) key.toString();
		String[] ids = id.split(" ");
		int hashValue = 0;
		for (String s : ids) {
			int tempValue = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				tempValue = (tempValue * 31 + (int)c) % size;
			}
			hashValue = (hashValue + tempValue) % size;
		}
		return hashValue;
	}
	
	public int probing_insert(int hashValue, GraphNode key) {
		for (int i = 0; i < size; i++) {
			int index = (hashValue + i * i) % size;
			Entry obj = table[index];
			if (table[index] == null || obj.getValue() == -1 || obj.getKey().getId() == (key.getId())) {
				return index;
			}
		}
		return -1;
	}
	
	public int probing_search(int hashValue, GraphNode key) {
		for (int i = 0; i < size; i++) {
			int index = (hashValue + i * i) % size;
			Entry obj = table[index];
			if (table[index] == null || obj.getKey().getId() == (key.getId())) {
				return index;
			}
		}
		return -1;
	}
	
	public void put(GraphNode key, int value) {
		int hashValue = hashFunction(key);
		int index = probing_insert(hashValue, key);
		while (index == -1) {
			double_size();
			hashValue = hashFunction(key);
			index = probing_insert(hashValue, key);
		}
		if (table[index] != null && table[index].getKey().getId() == (key.getId())) {
			table[index] = new Entry(table[index].getKey(), value);
		} else {
			table[index] = new Entry(key, value);
		}
		loadNum += 1;
		if (loadFactor() > 0.5) double_size();
	}
	
	public int get(GraphNode g) {
		int hashValue = hashFunction(g);
		int index = probing_search(hashValue, g);
		if (index >= 0 && table[index] != null && table[index].getKey().getId() == (g.getId())) {
			return table[index].getValue();
		}
		return -1;
	}
	
	public boolean containsKey(GraphNode g) {
		int hashValue = hashFunction(g);
		int  index = probing_search(hashValue, g);
		return (table[index] != null && table[index].getKey().getId() == (g.getId()) && table[index].getValue() != -1);
	}
	
	public void deleteNode(GraphNode key) {
		int hashValue = hashFunction(key);
		int index = probing_search(hashValue, key);
		if (index >= 0 && index < size && table[index] != null && table[index].getKey().getId() == (key.getId())) {
			deleteIndex(index);
		}
	}
	
	public void deleteIndex(int index) {
		if (index >= 0 && index < size && table[index] != null && table[index].getValue() != -1) {
			table[index].setValue(-1);
			loadNum -= 1;
		}
	}
	
	public void double_size() {
		if (size >= PRIMES[6]) {
			size *= 2;
		} else {
			for (int i = 0; i < 7; i++) {
				if (PRIMES[i] > size) {
					size = PRIMES[i];
					break;
				}
			}
		}
		re_insert();
	}
	
	public void half_size() {
		if (size / 2 > PRIMES[6]) {
			size /= 2;
		} else {
			for (int i = 6; i >= 0; i--) {
				if (PRIMES[i] <= size / 2) {
					size = PRIMES[i];
					break;
				}
			}
		}
		re_insert();
	}
	
	public void re_insert() {
		Entry[] temp = table.clone();
		table = new Entry[size];
		for (Entry e : temp) {
			if (e != null && e.getValue() != -1) {
				put(e.getKey(), e.getValue());
			}
		}
	}
	
	public double loadFactor() {
		return loadNum * 1.0 / size;
	}
	
	public String toString() {
		String res = "";
		for (int i = 0; i < size; i++) {
			res += i + ": " + table[i] + "\n";
		}
		return res;
	}
}