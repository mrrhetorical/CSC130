public interface Queue<T> {
	void enqueue(T data);
	T dequeue();
	boolean isEmpty();
}
