
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passArr;
	String pass = "Oink";
	String passConfirm = "oink";
	String upperCase = "OINK";
	String wDigit = "Oink7";
	@Before
	public void setUp() throws Exception {
		String[] passwords = {"667788CC#", "george3VVV#","Orc3#", "chad3366", "october20", "iCu", "StrawzzzzAAWW#", "bb22Cc", "codeProject", "XXXyy@123"};
		passArr = new ArrayList<String>();
		passArr.addAll(Arrays.asList(passwords));
	}

	@After
	public void tearDown() throws Exception {
		passArr = null;
	}
	@Test
	public void testComparePasswords() {
		Throwable exception = assertThrows(UnmatchedException.class, new Executable() {			
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.comparePasswords(pass, passConfirm);				
			}
		});
		
		assertEquals("The passwords do not match", exception.getMessage());
	}
	@Test 
	public void testComparePasswordsWithReturn() {
		assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(pass, passConfirm));
		assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(pass, pass));
	}	
	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
		assertFalse(PasswordCheckerUtility.isWeakPassword(pass));
		}catch(WeakPasswordException e) {
			assertTrue("Password contains less than 6 characters which is weak",true);
		}catch(Exception e) {
			assertTrue("Successfully throw an Exception other than WeakPasswordException",true);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("654321g"));
 
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception other than NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("654321G"));
 
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception other than NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
		assertFalse(PasswordCheckerUtility.isWeakPassword("lota*"));
		}catch(WeakPasswordException e) {
			assertTrue("Successfully threw a WeakPasswordException",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception other than WeakPasswordException",false);
		}
		
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("567@ttggG"));
		 	assertTrue("Did not throw an InvalidSequenceException",true);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertEquals(PasswordCheckerUtility.hasDigit(passArr.get(0)),true);
			assertTrue("Did not throw an NoDigitException",true);
		}catch(NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException",true);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an NoDigitException",false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Wowzerz2$"));
		}catch(LengthException e) {
			assertTrue("Successfully threw a LengthException",true);
		}catch(NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaException",true);
		}catch(NoLowerAlphaException e ) {
			assertTrue("Successfully threw a NoLowerAlphaException",true);
		}catch(NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException",true);
		}catch(NoSpecialCharacterException e) {
			assertTrue("Successfully threw a NoSpecialCharacterException",true);
		}catch(InvalidSequenceException e) {
			assertTrue("Successfully threw a InvalidSequenceException",true);
		}catch(Exception e) {
			assertTrue("Successfully threw an Exception",true);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passArr);
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "667788CC#");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		
		scan = new Scanner(results.get(1));  
		assertEquals(scan.next(), "george3VVV#");
		nextResults = scan.nextLine().toLowerCase(); 
		assertTrue(nextResults.contains("sequence"));
		
		 
		scan = new Scanner(results.get(2));  
		assertEquals(scan.next(), "Orc3#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long"));
		
				scan = new Scanner(results.get(3));  
		assertEquals(scan.next(), "chad3366");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(4));  
		assertEquals(scan.next(), "october20");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase") );
		
		 
		
		scan = new Scanner(results.get(5));  
		assertEquals(scan.next(), "iCu");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long") );
		
		scan = new Scanner(results.get(6));  
		assertEquals(scan.next(), "StrawzzzzAAWW#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(7));  
		assertEquals(scan.next(), "bb22Cc");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special") );
		
		
		scan = new Scanner(results.get(8)); 
		assertEquals(scan.next(), "codeProject");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(9));  
		assertEquals(scan.next(), "XXXyy@123");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence") );
	}
	
}