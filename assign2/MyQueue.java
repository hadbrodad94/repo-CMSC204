import java.util.ArrayList;

public class MyQueue<T>  implements QueueInterface<T>{
	private T queue[];
	private int frontIndex = 0;
	private int backIndex=-1;
	private static int defaultSize = 100;
	
	/** provide two constructors 
	 * 1. takes an int as the size of the queue
	 * 2. default constructor - uses a default as the size of the queue
	 * 
	 */
	public MyQueue(int size) {
		queue = (T[])new Object[size];
	}
	public MyQueue(){
		this(defaultSize);
	}
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		//checks if the front index is equal to the last index of the size
		//return frontIndex == (backIndex + 1) % queue.length;
		return backIndex == -1;
	}

	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {
		return backIndex == queue.length-1;
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	public T dequeue() throws QueueUnderflowException{
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		T temp = queue[frontIndex];
		queue[frontIndex] = null;
		frontIndex = (frontIndex + 1) % queue.length;
		backIndex--;//
		return temp;
	}

	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size() {
		return backIndex+1;
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException{
		if(isFull()) {
			throw new QueueOverflowException();
		}
		//Like a circle array. In order to be efficient with the code and not add more space to the array,
		//we use the modulus because when there are null elements in the beginning of the
		//queue, then we can add the element to the beginning of the array and set the 
		// backIndex equal to the beginning of the array.
		backIndex = (backIndex +1) % queue.length;
		queue[backIndex] = e;
		return true;
	}
	
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		String str = "";
		for(T r : queue) {
			if(r!= null) {
				str+= r;
			}
		}
		return str;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter) {
		//String temp = "";
//		for(int i =0; i < queue.length; i++) {
//			if(queue[i] != null) {
//				if(i != queue.length - 1) {
//					temp += queue[i] + delimiter;
//				}else {
//					temp += queue[i];
//				}
//			}
//		}
		StringBuilder temp = new StringBuilder();
		for(int i = 0; i < queue.length;i++) {
			if(queue[i] != null) {
				temp.append(queue[i]).append(delimiter);
			}
		}
		temp.deleteCharAt(temp.length()-1);
		return temp.toString();
	}
	
	 /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	 
	  */
	public void fill(ArrayList<T> list)throws QueueOverflowException {//added the throws QueueOverflowException b/c directions ask^
		for(T d : list) {
			if(isFull()) {
				throw new QueueOverflowException();
			}
			enqueue(d);
		}
	}
}
