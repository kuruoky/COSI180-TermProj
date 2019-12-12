package brandeismap;


/**
 * Node for singly linked list.
 * @author Rex Zhang
 *
 */
public class LinkedListNode<T> {
	T edge;
	LinkedListNode<T> next;
	
	public LinkedListNode(T e) {
		edge = e;
		next = null;
	}
	public void setNext(LinkedListNode<T> n) {
		next = n;
	}
	
	public LinkedListNode<T> getNext() {
		return next;
	}

	public T getData() {
		return edge;
	}
}
