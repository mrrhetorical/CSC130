public class Postfix {

	private static int getPrecedence(char op) {
		switch (op) {
			case '+':
			case '-':
				return 1;
			case  '*':
			case '/':
				return 2;
			default:
				return -1;
		}
	}

	public String convertFromInfix(String expr) {
		// Firstly remove all whitespace
		expr = expr.replaceAll("\\s+","");
		StringBuilder sb = new StringBuilder();
		Stack<Character> operatorStack = new LinkedList<>();
		Queue<Character> outputQueue = new DoubleLinkedList<>();

		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);

			if (Character.isDigit(ch)) {
				outputQueue.enqueue(ch);
			} else if (ch == '(') {
				operatorStack.push(ch);
			} else if (ch == ')') {
				while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
					outputQueue.enqueue(operatorStack.pop());
				}
				// this removes the last '('
				operatorStack.pop();
			} else {
				while (!operatorStack.isEmpty() && getPrecedence(ch) <= getPrecedence(operatorStack.peek())) {
					outputQueue.enqueue(operatorStack.pop());
				}
				operatorStack.push(ch);
			}
		}

		// Add all the operators finally
		while (!operatorStack.isEmpty()) {
			outputQueue.enqueue(operatorStack.pop());
		}

		// builds the queue into a string to return
		while (!outputQueue.isEmpty()) {
			sb.append(outputQueue.dequeue());
		}

		return sb.toString();
	}

	public int evaluate(String postfix) {
		Stack<Integer> stack = new LinkedList<>();

		for (int i = 0; i < postfix.length(); i++) {
			char ch = postfix.charAt(i);

			if (Character.isDigit(ch)) {
				stack.push(Character.getNumericValue(ch));
			} else {
				int a = stack.pop(),
						b = stack.pop();

				switch (ch) {
					case '+':
						stack.push(b + a);
						break;
					case '-':
						stack.push(b - a);
						break;
					case '*':
						stack.push(b * a);
						break;
					case '/':
						stack.push(b / a);
						break;
				}
			}
		}

		return stack.pop();
	}

}
