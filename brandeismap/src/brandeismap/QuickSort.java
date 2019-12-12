package brandeismap;

/**
 * Quick sort algorithm for graph's edges, unit can be time or length
 * @author Rex Zhang
 *
 */
public class QuickSort {
	static boolean minTime = false;
	
	public static void qs(GraphEdge[] A, boolean mt) {
		minTime = mt;
		sort(A, 0, A.length - 1);
	}

	public static void qs(GraphEdge[] A) {
		sort(A, 0, A.length - 1);
	}

	private static void sort(GraphEdge[] A, int start, int end) {
		if (start >= end) return;
		int p = partition(A, start, end);
		sort(A, start, p - 1);
		sort(A, p + 1, end);
	}

	private static int partition(GraphEdge[] A, int start, int end) {
		int index = (start + end) / 2;
		GraphEdge pivot = A[index];
		swap(A, start, index);
		int left = start + 1;
		int right = end;
		
		while (left <= right) {
			while (left <= right && lt(A[left], pivot)) left += 1;
			while (left <= right && lt(pivot, A[right])) right -= 1;
			if (left <= right) {
				swap(A, left, right);
				left += 1;
				right -= 1;
			}
		}
		swap(A, start, right);
		return right;
	}
	
	private static void swap(GraphEdge[] A, int a, int b) {
		GraphEdge temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
	
	// handles if sort by length or sort by travel time
	private static boolean lt(GraphEdge a, GraphEdge b) {
		if (minTime) {
			return a.time - b.time <= 0;
		}
		return a.length - b.length <= 0;
	}
}