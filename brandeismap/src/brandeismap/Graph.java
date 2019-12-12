package brandeismap;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 * This is the graph to hold vertices and edges.
 * Although it is called a graph, it functions more like a graph builder.
 * @author Rex Zhang
 *
 */
public class Graph {
	String vertexFile;
	String edgeFile;
	GraphNode[] vertex = new GraphNode[152];
	GraphEdge[] edges = new GraphEdge[596];
	GraphNode home = null;
	GraphNode goal = null;

	public Graph() throws FileNotFoundException {
		this(null, null);
	}

	public Graph(String s1, String s2) throws FileNotFoundException {
		vertexFile = s1 != null ? s1 : "MapDataVertices.txt";
		edgeFile = s2 != null ? s2 : "MapDataEdges.txt";
		constructVertex();
		constructEdges();
	}

	public void constructVertex() {
		File f = new File(vertexFile);
		try {
			Scanner input = new Scanner(f);
			while (input.hasNextLine()) {
				String line = input.nextLine();
				Scanner token = new Scanner(line);
				if (!token.hasNextInt()) {
					continue;
				}
				int id = token.nextInt();
				String label = token.next();
				int x = token.nextInt();
				int y = token.nextInt();
				String name = token.nextLine().trim();
				GraphNode node = new GraphNode(id, label, x, y, name.substring(1, name.length() - 1));
				node.g = this;
				vertex[id] = node;
				token.close();
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.print("File Not Found");
		}
	}

	public void constructEdges() {
		File f = new File(edgeFile);
		try {
			Scanner input = new Scanner(f);
			while (input.hasNextLine()) {
				String line = input.nextLine();
				Scanner token = new Scanner(line);
				if (!token.hasNextInt()) {
					continue;
				}
				int id = token.nextInt();
				token.next();
				token.next();
				int v1 = token.nextInt();
				int v2 = token.nextInt();
				int length = token.nextInt();
				int angel = token.nextInt();
				String direction = token.next();
				String condition = token.next();
				String name = token.nextLine().trim();
				GraphEdge edge = new GraphEdge(id, vertex[v1], vertex[v2], length, angel, direction,
						condition.substring(1, 2), name.substring(1, name.length() - 1));
				edges[id] = edge;
				vertex[v1].addEdge(edge);
				token.close();
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}