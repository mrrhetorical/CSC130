public class App {
	public static void main(String[] args) {
		Postfix p = new Postfix();
		String post = p.convertFromInfix("2 + 2 * (5 - 4)");
		int value = p.evaluate(post);
		System.out.printf("String: %s\nResult: %s\n", post, value);
	}

}