package brandeismap;

/**
 * A Singly Linked List.
 * @author Rex Zhang
 *
 */
public class SinglyLinkedList<T> {
	LinkedListNode<T> head;
	LinkedListNode<T> tail;
	int size;
	
	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public T get(int index) {
		if (0 > index || index >= size) {
			return null;
		}
		int i = 0;
		LinkedListNode<T> e = head;
		while (i < index) {
			e = e.next;
		}
		return (e == null) ? null: e.getData();
	}
	
	public void add(T e) {
		LinkedListNode<T> node = new LinkedListNode<T>(e);
		if (size == 0) {
			head = node;
			tail = node;
			size += 1;
			return;
		}
		node.next = head;
		head = node;
		size += 1;
	}
	
	public void insert(T e) {
		LinkedListNode<T> node = new LinkedListNode<T>(e);
		if (size == 0) {
			head = node;
			tail = node;
			size += 1;
			return;
		}
		tail.setNext(node);
		tail = node;
		size += 1;
	}

	public T pop(int index) {
		if (size > 0) {
			return removeIndex(index);
		}
		return null;
	}

	public T removeIndex(int index) {
		if (index > size - 1) {
			return null;
		}
		LinkedListNode<T> dummy = new LinkedListNode<T>(null);
		LinkedListNode<T> prev = dummy;
		LinkedListNode<T> curt = head;
		prev.setNext(curt);
		for (int i = 0; i < index; i++) {
			prev = prev.getNext();
			curt = curt.getNext();
		}
		remove(prev, curt);
		return curt.getData();
	}
	
	private void remove(LinkedListNode<T> prev, LinkedListNode<T> curt) {
		prev.setNext(curt.getNext());
		if (head == curt) {
			head = curt.getNext();
		}
		if (tail == curt) {
			tail = prev;
		}
		size -= 1;
	}
}