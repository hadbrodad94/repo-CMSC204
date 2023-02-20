import java.util.regex.Matcher;

public class Notation {
	static MyStack<Character> operatorStack;
	static MyStack<String> wowStack;
	static MyQueue<Character> postfix;
	
	/**
	 * 
	 * @param infix
	 * @return the String of the conversion of infix to postfix
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix​(String infix)throws InvalidNotationFormatException {
		MyStack<Character>operatorStack = new MyStack<>();
		MyQueue<Character> postfix = new MyQueue<Character>();
		// used for the ')' character
		boolean isFound = false;
		//accesses every character in the String
		for(int i =0; i < infix.length();i++) {
			//if character is a digit or letter, enqueue to the postfix
			if(Character.isDigit(infix.charAt(i)) || Character.isLetter(infix.charAt(i))) {
			postfix.enqueue(infix.charAt(i));
			}
			//if its a '(', push to stack
			else if(infix.charAt(i) == '(') {
			operatorStack.push(infix.charAt(i));
			}
			//if its an operator, we loop until the stack is empty and we do the following if the precedence of the top character has greater
			//precedence of the current operator in the stack: We first make sure its not a ( or any operator not mentioned in the precedence method.
			//then inside the while, we enqueue the top to the postix and push the current character to stack
			else if(infix.charAt(i) == '*' || infix.charAt(i) == '/' || infix.charAt(i) == '+' || infix.charAt(i) == '-') {
				//precedence of the top is not equal to 0 to not compare the ( char to )
			while(!operatorStack.isEmpty()&& precedence(operatorStack.top()) !=0 && precedence(operatorStack.top()) >= precedence(infix.charAt(i))) {
				postfix.enqueue(operatorStack.pop());	//if any errors, change to top
			}	
			operatorStack.push(infix.charAt(i));
			}
		//if character is ')', then we use a loop to find '(' inside the stack. 
		//if '(' is not there, then we throw exception. We find other things in the stack
		//before we find the '(', then we enqueue it to postfix.
			else if(infix.charAt(i) == ')') {
				if(operatorStack.isEmpty()) {
					throw new InvalidNotationFormatException();
				}
				while(operatorStack.top() != '(') {
					if(operatorStack.size() == 1 && operatorStack.top() != '(') {
						throw new InvalidNotationFormatException();
					}
				postfix.enqueue(operatorStack.pop());
				}
				operatorStack.pop();
				
				//This works but there is a minor bug in the code which makes 1 failure
//				while(!operatorStack.isEmpty()) {
//					if(operatorStack.top() == '(') {
//						isFound = true;
//						operatorStack.pop();
//						break;
//					}else {
//						//enques the non ')' character to the postifx when its not equal to ')'
//						postfix.enqueue(operatorStack.pop());
//					}
//				}
			//if the ( is not found, we throw exception
//			if(isFound == false) {
//				throw new InvalidNotationFormatException();
//			}
			
			}
		//after everything, we enqueue anything else to the postfix queue and return 
		//the string of the postfix.
			
		}
		
		while(!operatorStack.isEmpty()) {
			postfix.enqueue(operatorStack.pop());
			//operatorStack.pop();
		}
	return postfix.toString();
		}
	/**
	 * 
	 * @param operator
	 * @return the PEMDAS of the operation of the priority
	 */
	public static int precedence(char operator) {
		if(operator == '*' || operator == '/') {
			return 2;
		}else if(operator == '+' || operator == '-') {
			return 1;
		}else {
			return 0;
		}
	}
	/**
	 * 
	 * @param postfixExpr
	 * @return returns the math operation of the postfix operation
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression​(String postfixExpr)throws InvalidNotationFormatException {
		//operatorStack = new MyStack<>();
		//creates a String stack since Character never worked since there arent built in methods that would convert from
		//character to double or character to string to double.
		MyStack<String>wowStack = new MyStack<>();
		
		double result =0; // stores result of the operation
//		try {
		for(int i = 0; i < postfixExpr.length();i++) { //accesses each letter in postfixExpr
			char c = postfixExpr.charAt(i);
			if(Character.isDigit(c) || Character.isLetter(c) || c == '(') {// if character is digigt or '(', we push the String representation of it.
				wowStack.push(String.valueOf(c));
			}
			else if(c == '+' || c == '-' || c == '/' || c == '*') {
				if(wowStack.size() < 2) {//exception checker 
					throw new InvalidNotationFormatException();
				}
				//op1 is the second operation
				double op1 = Double.parseDouble(wowStack.pop());//op1 is the top and op2 is the one below top
				double op2 = Double.parseDouble(wowStack.pop());				
				if(c == '+') {
					result = op2 + op1; // does the operation. Focus on how it's op2 / op1 not the opposite.
				}else if(c == '-') {
					result = op2 - op1;
				}else if(c == '*') {
					result = op2 * op1;
				}else if(c =='/') {
					result =  op2 / op1;
				}
				//str+= result;
				//System.out.println((char) result);
				wowStack.push(Double.toString(result));//pushes result changed from double to String to the stack
			}
		}
		if(wowStack.size() > 1) {
			throw new InvalidNotationFormatException();
		}
//		}catch(StackOverflowException e) {
//			e.printStackTrace();
//		}catch(InvalidNotationFormatException e) {
//			e.printStackTrace();
//		}catch(StackUnderflowException e) {
//			e.printStackTrace();
//		}
		return result;//returns most recent result
		
		}
	/**
	 * 
	 * @param postfix
	 * @return the conversion of the postfix to infix String
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix​(String postfix) throws InvalidNotationFormatException{
		MyStack<String>operatorStack = new MyStack<>();
		String infix;
			for(int i =0; i < postfix.length();i++) {
				char c = postfix.charAt(i);
				if(Character.isDigit(c) || Character.isLetter(c)) {
					operatorStack.push(Character.toString(c));
				}
			switch(c) {
				case ' ':
					continue;
				case '+','*', '/','-':
					if(operatorStack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					String operand2 = operatorStack.pop();
					String operand1 = operatorStack.pop();
					infix= "("+operand1 + c + operand2+")"; // formatting
					operatorStack.push(infix);
					break;
				}
			}
			
			if(operatorStack.size() != 1) {
				throw new InvalidNotationFormatException();
			}

		return operatorStack.pop();
	}

}
