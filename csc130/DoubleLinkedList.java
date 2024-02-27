import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DoubleLinkedList<T> {
	public static void main(String... args) throws IOException {
		// Run tests if test parameter is given and use a simple ByteArrayInputStream instead of requiring an extra file
		if (args.length > 0 && args[0].equalsIgnoreCase("test")) {
			DoubleLinkedList<String> list = new DoubleLinkedList<>();
			ByteArrayInputStream testStream = new ByteArrayInputStream("add Apple\nadd Banana\nadd Orange\nadd Passionfruit\nadd Lemon\ndelete Banana\nremove Test".getBytes(StandardCharsets.UTF_8));
			PrintStream out = System.out;
			run(testStream, out, list);
			testStream.close();
			list.display();
			list.remove("apple");
			list.display();
			list.add("mango");
			list.add("lime");
			list.remove("broccoli");
			list.display();
			list.add("orange");
			list.display();
			list.remove("orange");
			list.display();
			list.remove("lemon");
			list.display();
			System.out.printf("Size: %s\n", list.getSize());
			return;
		}

		// Create input and output files and list
		DoubleLinkedList<String> list = new DoubleLinkedList<>();
		File inputFile = new File("input.txt");
		FileInputStream fis = new FileInputStream(inputFile);
		File outputFile = new File("output.txt");
		PrintStream printStream = new PrintStream(outputFile);

		run(fis, printStream, list);
		fis.close();
		printStream.close();
	}
	
	// Method set up to make testing easier
	private static void run(InputStream inputStream, PrintStream printStream, DoubleLinkedList<String> list) throws IOException {
		Scanner scanner = new Scanner(inputStream);
		System.setOut(printStream);
		
		// Scanner loop to read each line of input
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line == null || line.isEmpty()) {
				System.err.println("There was an issue reading the current line! (Invalid arguments)");
				continue;
			}
			String[] parts = line.split(" ");
			if (parts.length != 2) {
				System.err.println("There was an issue reading the current line! (Invalid arguments)");
				continue;
			}
			switch (parts[0].toLowerCase()) {
				case "add":
					list.add(parts[1].toLowerCase());
					break;
				case "delete":
					list.remove(parts[1].toLowerCase());
					break;
				default:
					System.err.println("Unrecognized list operation!");
			}
		}
		scanner.close();
		list.display();
	}

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
	public int getSize() {
		return size;
	}
	/**
	 * Adds the item to the stack
	 * @param data - the data to add
	 */
	public void add(T data) {
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
	 * Gets the value at the given index in the list
	 * @param index - the index of the element
	 * @return The item at the given index
	 */
	public T get(int index) throws IndexOutOfBoundsException {
		if (index >= size) {
			throw new IndexOutOfBoundsException(String.format("Index greater than the size of the list! (%s)", index));
		}
		DoubleNode p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.data;
	}
	/**
	 * Pops the element from the top of the stack
	 * @return The last element in the list
	 */
	public T pop() {
		if (size == 0) {
			throw new IndexOutOfBoundsException(String.format("Index greater than the size of the list! (%s)", 1));
		} else if (size == 1) {
			T data = head.data;
			head = null;
			tail = null;
			size = 0;
			return data;
		}
		T data = tail.data;
		tail = tail.prev;
		tail.next = null;
		size--;
		return data;
	}
	/**
	 * Removes the first occurance of the element from the list with the given data
	 * @param item - the item being removed
	 * @return true if successful
	 */
	public boolean remove(T item) {
		if (size == 0) {
			return false;
		}

		DoubleNode p = head;
		do {
			if (item.equals(p.data)) {
				if (head.equals(p)) {
					head = p.next;
				}

				if (tail.equals(p)) {
					tail = p.prev;

				}

				if (p.prev != null) {
					p.prev.next = p.next;
				}
				
				if (p.next != null) {
					p.next.prev = p.prev;
				}

				size--;
				return true;
			}

			p = p.next;
		} while (p != null);

		return false;
	}
	public void display() {
		DoubleNode p = head;
		while (p != null) {
			System.out.print(p.data);
			if (p.next != null)
				System.out.print(", ");
			p = p.next;
		}
		System.out.print("\n");
	}
}
