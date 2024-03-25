public class DoubleLinkedList<T> implements Queue<T> {
	class DoubleNode {
		DoubleNode next;
		DoubleNode prev;
		T data;
		DoubleNode(T data) {
			this.data = data;
		}
	}
	private DoubleNode head;
	private DoubleNode tail;
	private int size;
	public DoubleLinkedList() {
		size = 0;
	}
	/**
	 * @return the size of the list
	 */
	/**
	 * Adds the item to the stack
	 * @param data - the data to add
	 */
	@Override
	public void enqueue(T data) {
		DoubleNode n = new DoubleNode(data);
		if (head != null) {
			DoubleNode p = head;
			while (p.next != null) {
				p = p.next;
			}
			p.next = n;
			n.prev = p;
		} else {
			head = n;
		}
		tail = n;
		size++;
	}

	/**
	 * Pops the element from the top of the stack
	 * @return The last element in the list
	 */
	@Override
	public T dequeue() {
		if (size == 0) {
			throw new IndexOutOfBoundsException(String.format("Index greater than the size of the list! (%s)", 1));
		} else if (size == 1) {
			T data = head.data;
			head = null;
			tail = null;
			size = 0;
			return data;
		}

		T data = head.data;
		head = head.next;
		if (head != null)
			head.prev = null;
		size--;
		return data;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
}
