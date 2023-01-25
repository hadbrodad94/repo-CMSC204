import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {
	GradeBook gr1;
	GradeBook gr2;
	GradeBook gr3;
	GradeBook gr4;
	@BeforeEach
	void setUp() throws Exception {
		gr1 = new GradeBook(5);
		gr2 = new GradeBook(5);
		gr3 = new GradeBook(5);
		gr4 = new GradeBook(5);
		gr1.addScore(99.8);
		gr1.addScore(53.8);
		gr1.addScore(22.2);
		gr1.addScore(92.7);
		
		gr2.addScore(78.2);
		gr2.addScore(65.3);	
		
		//gr3 with 1 element to show the result that returns that one element
		gr3.addScore(37.7);
		
		//gr4 w/ 0 elements to show result is 0 in finalScore method
	}

	@AfterEach
	void tearDown() throws Exception {
		gr1 = gr2 = gr3 = gr4 = null;
	}

	@Test
	void testAddScore() {
		//checks to see whether each object created changed to string format is equal to the numbers in the scores array
		assertTrue(gr1.toString().equals("99.8 53.8 22.2 92.7 "));
		assertTrue(gr2.toString().equals("78.2 65.3 "));
		assertTrue(gr3.toString().equals("37.7 "));
		assertTrue(gr4.toString().equals(""));
		
		assertEquals(4,gr1.getScoreSize());
		assertEquals(2,gr2.getScoreSize());
		assertEquals(1,gr3.getScoreSize());
		assertEquals(0,gr4.getScoreSize());
	}

	@Test
	void testSum() {
		assertEquals(268.5, gr1.sum(),.0001);
		assertEquals(143.5, gr2.sum(),.0001);
		assertEquals(37.7, gr3.sum(),.0001);
		assertEquals(0.0, gr4.sum(),.0001);
	}

	@Test
	void testMinimum() {
		assertEquals(22.2,gr1.minimum(),.001);
		assertEquals(65.3,gr2.minimum(),.001);
		assertEquals(37.7,gr3.minimum(),.001);
		assertEquals(0.0,gr4.minimum(),.001);
	}

	@Test
	void testFinalScore() {
		assertEquals(246.3,gr1.finalScore());
		assertEquals(78.2,gr2.finalScore());
		assertEquals(37.7,gr3.finalScore());
		assertEquals(0,gr4.finalScore());
	}

}
