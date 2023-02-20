import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTest {
	
	public MyStack<String> stringS;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleS
	public MyStack<Double> doubleS;
	// STUDENT: add variables as needed for your student tests
	//public Double 
	public Double g = 1.0, h = 2.0, i = 3.0, j = 4.0, k = 5.0, l = 6.0, m = 7.0;
	@BeforeEach
	public void setUp() throws Exception {
		stringS = new MyStack<String>(5);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);
		
		//STUDENT: add setup for doubleS for student tests
		doubleS = new MyStack<Double>(7);
		doubleS.push(g);
		doubleS.push(h);
		doubleS.push(i);
	}

	@AfterEach
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
	}

	@Test
	public void testIsEmpty() throws StackUnderflowException {
		assertEquals(false,stringS.isEmpty());
		stringS.pop();
		stringS.pop();
		stringS.pop();
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() throws StackOverflowException {
		assertEquals(false, stringS.isFull());
		stringS.push(d);
		stringS.push(e);
		assertEquals(true, stringS.isFull());
	}

	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		}
		catch (StackUnderflowException e){
			assertTrue("This should have caused an StackUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackUnderflowException", false);
		}
	}

	@Test
	public void testPopStudent() {
		//Use the doubleQ for student tests
		//Use the doubleQ for student tests
		try {
			assertEquals(i, doubleS.pop());
			assertEquals(h, doubleS.pop());
			assertEquals(g, doubleS.pop());
			//should cause StackUnderflowException
			doubleS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		} catch (StackUnderflowException e){
			assertTrue("This should have caused an StackUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackUnderflowException", false);
		}
		
	}
	
	@Test
	public void testTop() throws StackUnderflowException, StackOverflowException {
		assertEquals(c, stringS.top());
		stringS.push(d);
		assertEquals(d, stringS.top());
		stringS.pop();
		stringS.pop();
		assertEquals(b, stringS.top());		
	}

	@Test
	public void testSize() throws StackOverflowException, StackUnderflowException {
		assertEquals(3, stringS.size());
		stringS.push(d);
		assertEquals(4, stringS.size());
		stringS.pop();
		stringS.pop();
		assertEquals(2, stringS.size());
	}

	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue("This should have caused an StackOverflowException", false);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}

	@Test
	public void testPushStudent() {
		//Use the doubleQ for student tests
		assertEquals(3, doubleS.size());
		try {
			assertEquals(true, doubleS.push(j));
			assertEquals(4, doubleS.size());
			assertEquals(true, doubleS.push(k));
			assertEquals(5, doubleS.size());
			assertEquals(true, doubleS.push(l));
			assertEquals(true, doubleS.push(m));
			assertEquals(7, doubleS.size());
			//stack should cause overflowexception next statement
			doubleS.push(m);
			assertTrue("This should have caused an StackOverflowException", false);
		} catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
		
	}
	
	@Test
	public void testToString() throws StackOverflowException {
		assertEquals("abc", stringS.toString());
		stringS.push(d);
		assertEquals("abcd", stringS.toString());
		stringS.push(e);
		assertEquals("abcde", stringS.toString());
	}

	@Test
	public void testToStringStudent() throws StackOverflowException{
		//Use the doubleQ for student tests
		assertEquals("1.02.03.0", doubleS.toString());
		doubleS.push(j);
		assertEquals("1.02.03.04.0", doubleS.toString());
		doubleS.push(k);
		assertEquals("1.02.03.04.05.0", doubleS.toString());	
	}
	
	@Test
	public void testToStringDelimiter() throws StackOverflowException {
		assertEquals("a%b%c", stringS.toString("%"));
		stringS.push(d);
		assertEquals("a&b&c&d", stringS.toString("&"));
		stringS.push(e);
		assertEquals("a/b/c/d/e", stringS.toString("/"));
	}

	@Test
	public void testFill() throws StackOverflowException, StackUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringS = new MyStack<String>(5);
		//fill with an ArrayList
		stringS.fill(fill);
		assertEquals(3,stringS.size());
		assertEquals("carrot", stringS.pop());
		assertEquals("banana", stringS.pop());
		assertEquals("apple", stringS.pop());		
	}

}
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//
//public class MyStackTest {
//	public MyStack<String> stringS;
//	public String a="a", b="b", c="c", d="d", e="e", f="f";
//	public ArrayList<String> fill = new ArrayList<String>();
//	
//	// STUDENT: student tests will use the doubleS
//	public MyStack<Double> doubleS;
//	// STUDENT: add variables as needed for your student tests
//	public Double g = 1.0, h = 2.0, i = 3.0, j = 4.0, k = 5.0, l = 6.0, m = 7.0;
//	@BeforeEach
//	public void setUp() throws Exception {
//		stringS = new MyStack<String>(5);
//		stringS.push(a);
//		stringS.push(b);
//		stringS.push(c);
//		
//		//STUDENT: add setup for doubleS for student tests
//		doubleS = new MyStack<Double>(7);
//		doubleS.push(g);
//		doubleS.push(h);
//		doubleS.push(i);
//	}
//
//	@AfterEach
//	public void tearDown() throws Exception {
//		stringS = null;
//		doubleS = null;
//	}
//
//	@Test
//	public void testIsEmpty()throws StackUnderflowException {	
//			assertEquals(false,stringS.isEmpty());
//			stringS.pop();
//			stringS.pop();
//			stringS.pop();
//			assertEquals(true, stringS.isEmpty());
//	}
//
//	@Test
//	public void testIsFull()throws StackOverflowException {
//		//System.out.println("ISFULL TES:\n\n");
//			assertEquals(false, stringS.isFull());
//			stringS.push(d);
//			//System.out.println("Stack is: " + stringS);
//			//System.out.println("Size is: "+ stringS.size());
//			stringS.push(e);
//			//System.out.println("Stack is: " + stringS);
//			//System.out.println("Size is: "+ stringS.size());
//			assertEquals(true, stringS.isFull());
//			
//	}
//
//	@Test
//	public void testPop()throws StackUnderflowException {
//		//System.out.println("TEST POP CASE:\n\n");
//			assertEquals(c, stringS.pop());
//			//System.out.println(stringS);
//			assertEquals(b, stringS.pop());
//			//System.out.println(stringS);
//			assertEquals(a, stringS.pop());
//			//System.out.println(stringS);
//			//Queue is empty, next statement should cause QueueUnderFlowException
//			stringS.pop();
//			//System.out.println(stringS);
//			assertTrue("This should have caused an StackUnderflowException", false);
//	}
//
//	@Test
//	public void testPopStudent() throws StackUnderflowException {
//		//Use the doubleQ for student tests
//		assertEquals(i, doubleS.pop());
//		assertEquals(h, doubleS.pop());
//		assertEquals(g, doubleS.pop());
//		//should cause StackUnderflowException
//		doubleS.pop();
//		assertTrue("This should have caused an StackUnderflowException", false);
//	}
//	
//	@Test
//	public void testTop() throws StackUnderflowException, StackOverflowException{
//			//System.out.println("testTop method TEST:\n");
//			assertEquals(c, stringS.top());
//			stringS.push(d);
//			assertEquals(d, stringS.top());
//			stringS.pop();
//			stringS.pop();
//			assertEquals(b, stringS.top());		
//	}
//
//	@Test
//	public void testSize() throws StackUnderflowException, StackOverflowException {
//			assertEquals(3, stringS.size());
//			stringS.push(d);
//			assertEquals(4, stringS.size());
//			stringS.pop();
//			stringS.pop();
//			assertEquals(2, stringS.size());
//	}
//
//	@Test
//	public void testPush() throws StackOverflowException {
//			assertEquals(3, stringS.size());
//			assertEquals(true, stringS.push(d));
//			assertEquals(4, stringS.size());
//			assertEquals(true, stringS.push(e));
//			assertEquals(5, stringS.size());
//			//Queue is full, next statement should cause QueueOverFlowException
//			stringS.push(f);
//			assertTrue("This should have caused an StackOverflowException", false);
//	}
//
//	@Test
//	public void testPushStudent() throws StackOverflowException{
//		assertEquals(3, doubleS.size());
//		assertEquals(true, doubleS.push(j));
//		assertEquals(4, doubleS.size());
//		assertEquals(true, doubleS.push(k));
//		assertEquals(5, doubleS.size());
//		assertEquals(true, doubleS.push(l));
//		assertEquals(true, doubleS.push(m));
//		assertEquals(7, doubleS.size());
//		//stack should cause overflowexception next statement
//		doubleS.push(m);
//		assertTrue("This should have caused an StackOverflowException", false);
//	}
//	
//	@Test
//	public void testToString()  throws StackUnderflowException, StackOverflowException{
//			assertEquals("abc", stringS.toString());
//			stringS.push(d);
//			assertEquals("abcd", stringS.toString());
//			stringS.push(e);
//			assertEquals("abcde", stringS.toString());	
//	}
//
//	@Test
//	public void testToStringStudent()throws StackOverflowException {
//		//Use the doubleQ for student tests
//		assertEquals("1.02.03.0", doubleS.toString());
//		doubleS.push(j);
//		assertEquals("1.02.03.04.0", doubleS.toString());
//		doubleS.push(k);
//		assertEquals("1.02.03.04.05.0", doubleS.toString());	
//	}
//	
//	@Test
//	public void testToStringDelimiter() throws StackUnderflowException, StackOverflowException {
//			assertEquals("a%b%c", stringS.toString("%"));
//			stringS.push(d);
//			assertEquals("a&b&c&d", stringS.toString("&"));
//			stringS.push(e);
//			assertEquals("a/b/c/d/e", stringS.toString("/"));
//	}
//
//	@Test
//	public void testFill()  throws StackUnderflowException, StackOverflowException{
//			fill.add("apple");
//			fill.add("banana");
//			fill.add("carrot");
//			//start with an empty queue
//			stringS = new MyStack<String>(5);
//			//fill with an ArrayList
//			stringS.fill(fill);
//			assertEquals(3,stringS.size());
//			assertEquals("carrot", stringS.pop());
//			assertEquals("banana", stringS.pop());
//			assertEquals("apple", stringS.pop());	
//	}
//
//}
