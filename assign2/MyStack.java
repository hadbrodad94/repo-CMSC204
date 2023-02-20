import java.util.ArrayList;

public class MyStack <T> implements StackInterface<T>  {
//	private ArrayList<T> stack;
	//count starts at 0 because we will start at 0 to push
	 int count = -1;
	 private T[] stack;
	//default size constructor
	static int maxSize = 100;
	//used for making the max size
	
	/**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
	public MyStack(int size) {
		stack = (T[]) new Object [size];  
	}
	
	public MyStack() {
		this(maxSize);
	}
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		return count == -1;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		return count == stack.length-1;
	}
	

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException{
		if(isEmpty()) {
			throw new StackUnderflowException();
		}else {
			T temp = top();
//			System.out.println("Stack is: " + stack);
//			System.out.println("Count is: "+count);
//			System.out.println("Temp is: " + temp);
//			System.out.println("Top is: " +top());
			//System.out.println("Top is: " +top());
			stack[count] = null;
			count--;
			return temp;
		}
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T top() throws StackUnderflowException{
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		return stack[count];
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return count+1;
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	public boolean push(T e) throws StackOverflowException{
		if(isFull()) {
			throw new StackOverflowException();
		}
		count++;
		stack[count] = e;
		//System.out.println("in PUSH: Stack is: " + stack);
		return true;
		
	}
	
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String temp = "";
		for(T s : stack) {
			if(s!= null) {
				temp += s;
			}
		}
		return temp;
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter) {
		String temp = "";
		for(int i = 0; i < stack.length;i++) {
			if(stack[i] != null) {
				if(i != size()-1) {//checks to see if the index is the last one 
					//so that it cannot print the delimiter in the end of the stack element
					temp+= stack[i] +delimiter;
				}else {
					temp+= stack[i];
				}
			}
		}
		return temp;
	}
	
	 /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	public void fill(ArrayList<T> list) throws StackOverflowException {//added the throws StackOverflowException in the method header bc it asked	
//		ArrayList <T> temp = new ArrayList<T>();
		for(int i =0; i < list.size();i++) {
			if(isFull()) {
				throw new StackOverflowException();
			}
			push(list.get(i));
		}
	}
	
}
