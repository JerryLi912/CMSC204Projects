/**
 * This class uses NotationQueue and NotationStack to perform transition from infix to postfix,
 * and from postfix to infix expressions. Also, the evaluation of postfix expression.
 * 
 * @author jerry
 *
 */
public class Notation {

	/**
	 * 
	 * @param postfixExpr The postfix expression
	 * @return Return the value calculated from the postfix expression 
	 * @throws InvalidNotationFormatException 
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		NotationStack<String> stack = new NotationStack<>();
		char c;
		double result = 0;
		try {
			for(int i = 0; i < postfixExpr.length(); i++) {
				c = postfixExpr.charAt(i);
				
				if(Character.isWhitespace(c)) {
					continue;
				}
				
				else if(Character.isDigit(c) || c == '(') {
					stack.push(c + "");
				}
				
				else if(isOperator(c)) {
					if(!stack.isEmpty() && stack.size() > 1) {
					result = evaluatePostfix(Double.parseDouble(stack.pop()), Double.parseDouble(stack.pop()), c);
					}
					stack.push(Double.toString(result));
				}
				
				else {}
			}//end for loop
			
			if(!stack.isEmpty() && stack.size() > 1) {
				throw new InvalidNotationFormatException();
			}
			
			if(stack.isEmpty()) {
				throw new InvalidNotationFormatException();
			}
			
			
		}catch(StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		return Double.parseDouble(stack.toString());
	}
	
	/**
	 * 
	 * @param postfix The postfix expression
	 * @return Return the string of the new converted infix
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
	NotationStack<String> stack = new NotationStack<>();
	char c;
	try {
		for (int i = 0; i < postfix.length(); i++) {
			c = postfix.charAt(i);
			
			if(Character.isWhitespace(c)) {
				continue;
			}
			
			else if(Character.isDigit(c)) {
				stack.push(c + "");
			}
			
			else if(isOperator(c)) {
				//pop the top 2 values from the stack
				if(stack.size() < 2) {
					throw new InvalidNotationFormatException();
				}
				
				String operator1 = stack.top();
				stack.pop();
				String operator2 = stack.top();
				stack.pop();
				//Encapsulate the resulting string within parenthesis
				stack.push("(" + operator2 + c + operator1 + ")");
			}
			else {}
		}//end of for loop
			
		if(!stack.isEmpty() && stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		}catch(StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		return stack.toString();
	}
	
	/**
	 * 
	 * @param infix The infix expression
	 * @return Return the string of the new converted postfix 
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
		
		NotationStack<Character> stack = new NotationStack<>();
		NotationQueue<Character> queue = new NotationQueue<>();
		char c;
		
		try {
		for(int i = 0; i < infix.length(); i++) {
			c = infix.charAt(i);
			
			if(Character.isWhitespace(c)) {
				continue;
			}
			
			else if(Character.isDigit(c)) {
				queue.enqueue(c);
			}
			
			else if(c == '(') {
				stack.push(c);
			}
			
			else if(isOperator(c)) {
				while(!stack.isEmpty() && precedence(stack.top()) >= precedence(c)) {
					queue.enqueue(stack.pop());
				}
				stack.push(c);
			}
			
			else if(c == ')') {
				while(!stack.isEmpty() && stack.top()!= '(') {
					queue.enqueue(stack.pop());
				}
				
				//if the stack is empty, or the ) does not match the (, then throw exception
				//not the StackUnderflowExceptio here.
				if(stack.isEmpty() || stack.top()!= '('){
					throw new InvalidNotationFormatException();
				}
				else{
					stack.pop();  //pop left '('
				}
			}
			
			else {}	
		}//end of for loop
		
		//pop the operators remain in the stack
		while(!stack.isEmpty()) {
			if(stack.top() == '(') {
				throw new InvalidNotationFormatException();
			}
			queue.enqueue(stack.pop());
		}
		
		}catch(StackUnderflowException | StackOverflowException | QueueOverflowException e) {
			 e.printStackTrace();
		}
		return queue.toString();
}
	
	//personal test Notation class
	public static void main (String args[]) {
		
		String s = "(5+4)*2";
		String s1 = "54+";
		String s2 = "354+*2+";
		try {
			System.out.println(Notation.convertInfixToPostfix(s));
			System.out.println(Notation.convertPostfixToInfix(s1));
			System.out.println(Notation.evaluatePostfixExpression(s2));
		} catch (InvalidNotationFormatException e) {
			e.printStackTrace();
		}
	}

	private static boolean isOperator(char character) {
		switch(character) {
			case '+' :
			case '-' : 
			case '*':
			case '/':
				return true;
			default :
				return false;
		}
	}

	
	private static int precedence (char character) {
		switch(character) {
			case '+' :
			case '-' : 
				return 1;
			case '*':
			case '/':
				return 2;
			default :
				return 0;
			}
		}
	
	
	
	private static double evaluatePostfix(double value1, double value2, char character) {
		switch(character) {
		case '+': 
			return value2 + value1;
		case '-':
			return value2 - value1;
		case '*':
			return value2 * value1;
		case '/':
			return value2 / value1;
		default:
			return 0;
		}
	}
}



@SuppressWarnings("serial")
class InvalidNotationFormatException extends Exception{
	InvalidNotationFormatException(){
		super("This should have thrown an InvalidNotationFormatException");
	}
}
