import java.util.EmptyStackException;

public class LinkedList<T> implements Stack<T> {

	class Node {
		T data;
		Node next;
		Node(T data) {
			this.data = data;
		}
	}

	private Node head;

	public LinkedList() {

	}

	@Override
	public void push(T data) {
		Node n = new Node(data);
		n.next = head;
		head = n;
	}

	@Override
	public T pop() {
		if (head == null)
			throw new EmptyStackException();

		T data = head.data;
		head = head.next;

		return data;
	}

	@Override
	public T peek() {
		if (head == null)
			throw new EmptyStackException();

		return head.data;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}
}
