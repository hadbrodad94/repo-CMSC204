import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyQueueTest {
	public MyQueue<String> stringQ;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleQ
	public MyQueue<Double> doubleQ;
	// STUDENT: add variables as needed for your student tests
	public Double g = 1.0, h = 2.0, i = 3.0, j = 4.0, k = 5.0, l = 6.0, m = 7.0;
	@BeforeEach
	public void setUp() throws Exception {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);
		
		//STUDENT: add setup for doubleQ for student tests
		doubleQ = new MyQueue<Double>(7);
		doubleQ.enqueue(g);
		doubleQ.enqueue(h);
		doubleQ.enqueue(i);
	}

	@AfterEach
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	@Test
	public void testIsEmpty() throws QueueUnderflowException{
		assertEquals(false,stringQ.isEmpty());
		stringQ.dequeue();
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());
	}

	@Test
	public void testDequeue() throws QueueOverflowException,QueueUnderflowException{
			try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
			}catch(QueueUnderflowException e) {
				assertTrue("This should have caused an QueueUnderflowException", true);
			}catch(Exception e) {
				assertTrue("This should have caused an QueueUnderflowException", true);
			}
	}
	
	@Test
	public void testDequeueStudent() throws QueueOverflowException,QueueUnderflowException{
		//Use the doubleQ for student tests
		try {
		assertEquals(g, doubleQ.dequeue());
		assertEquals(h, doubleQ.dequeue());
		assertEquals(i, doubleQ.dequeue());
		//Queue is empty, next statement should cause QueueUnderFlowException
		doubleQ.dequeue();
		assertTrue("This should have caused an QueueUnderflowException", false);
		}catch(QueueUnderflowException e) {
			assertTrue("This should have caused an QueueUnderflowException", true);
		}catch(Exception e) {
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
	}

	@Test
	public void testSize()throws QueueOverflowException,QueueUnderflowException {
		assertEquals(3, stringQ.size());
		stringQ.enqueue(d);
		assertEquals(4, stringQ.size());
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(2, stringQ.size());
	}

	@Test
	public void testEnqueue()throws QueueOverflowException,QueueUnderflowException {
		try {
		assertEquals(3, stringQ.size());
		assertEquals(true, stringQ.enqueue(d));
		assertEquals(4, stringQ.size());
		assertEquals(true, stringQ.enqueue(e));		
		assertEquals(5, stringQ.size());
		//Queue is full, next statement should cause QueueOverFlowException
		stringQ.enqueue(f);
		assertTrue("This should have caused an QueueOverflowException", false);
		}catch(QueueOverflowException e) {
			assertTrue("This should have caused an QueueOverflowException", true);
		}catch(Exception e) {
			assertTrue("This should have caused an QueueOverflowException", true);
		}
	}

	@Test
	public void testEnqueueStudent()throws QueueOverflowException,QueueUnderflowException {
		//Use the doubleQ for student tests
		try {
		assertEquals(3, doubleQ.size());
		assertEquals(true, doubleQ.enqueue(j));
		assertEquals(4, doubleQ.size());
		assertEquals(true, doubleQ.enqueue(k));		
		assertEquals(5, doubleQ.size());
		assertEquals(true, doubleQ.enqueue(l));
		assertEquals(true, doubleQ.enqueue(m));	
		assertEquals(7, doubleQ.size());
		//Queue is full, next statement should cause QueueOverFlowException
		doubleQ.enqueue(m);
		assertTrue("This should have caused an QueueOverflowException", false);
		}catch(QueueOverflowException e) {
			assertTrue("This should have caused an QueueOverflowException", true);
		}catch(Exception e) {
			assertTrue("This should have caused an QueueOverflowException", true);
		}
	}

	@Test
	public void testIsFull()throws QueueOverflowException,QueueUnderflowException {
		assertEquals(false, stringQ.isFull());
		stringQ.enqueue(d);
		stringQ.enqueue(e);
		assertEquals(true, stringQ.isFull());
	}

	@Test
	public void testToString()throws QueueOverflowException,QueueUnderflowException {
		assertEquals("abc", stringQ.toString());
		stringQ.enqueue(d);
		assertEquals("abcd", stringQ.toString());
		stringQ.enqueue(e);
		assertEquals("abcde", stringQ.toString());
	}
	
	@Test
	public void testToStringStudent()throws QueueOverflowException,QueueUnderflowException {
		//Use the doubleQ for student tests
		assertEquals("1.02.03.0", doubleQ.toString());
		doubleQ.enqueue(j);
		assertEquals("1.02.03.04.0", doubleQ.toString());
		doubleQ.enqueue(k);
		assertEquals("1.02.03.04.05.0", doubleQ.toString());
	}

	@Test
	public void testToStringDelimiter() throws QueueOverflowException,QueueUnderflowException{
		assertEquals("a%b%c", stringQ.toString("%"));
		stringQ.enqueue(d);
		assertEquals("a&b&c&d", stringQ.toString("&"));
		stringQ.enqueue(e);
		assertEquals("a/b/c/d/e", stringQ.toString("/"));
	}

	@Test
	public void testFill() throws QueueOverflowException,QueueUnderflowException{
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringQ = new MyQueue<String>(5);
		//fill with an ArrayList
		stringQ.fill(fill);
		assertEquals(3,stringQ.size());
		assertEquals("apple", stringQ.dequeue());
		assertEquals("banana", stringQ.dequeue());
		assertEquals("carrot", stringQ.dequeue());		
	}

}
