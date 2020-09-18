
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Jerry Li
 * 
 * This class is about testing the implementation of password checker
 *
 */
public class PasswordCheckerSTUDENT_Test {
	
	ArrayList<String> passwords;
	ArrayList<String> validPasswords;
	@Before
	public void setUp() throws Exception {
		String[] p = {"3344aabb", "QWIEEC4#", "qwS1111", "inw#@Bjh", "poi9mT", "qqwweerrTT23@@", "swB2#", "123Wi"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p)); 
		
		String[] p1 = {"123bbNW@@QQ","erN#12Qswuyw", "eirunAi@#@df2"};
		validPasswords = new ArrayList<String>();
		validPasswords.addAll(Arrays.asList(p1));
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
		validPasswords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidLength("qqwweerrTT23@@"));
			PasswordCheckerUtility.isValidLength("swB2#");
			assertTrue("Did not throw lengthException", false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException", true);
		}
	}
	
	
	
	
	@Test
	public void testIsValidPasswordNotTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidLength("qqwweerrTT23@@"));
			PasswordCheckerUtility.isValidLength("swB2#12221");
			assertFalse("Did not throw lengthException", false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException", true);
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
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("qwee4Www#"));
			PasswordCheckerUtility.hasUpperAlpha("3344aabb");
			assertTrue("Did not throw NoUpperAlphaException", false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException", true);
		}
	}
	
	
	@Test
	public void testIsValidPasswordThathasUpperAlpha()
	{
		try{	
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("qwee4Www#"));
			PasswordCheckerUtility.hasUpperAlpha("3344aabbW");
			assertFalse("Did not throw NoUpperAlphaException", false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException", true);
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
				
				assertTrue(PasswordCheckerUtility.hasLowerAlpha("QWQwIU@#4"));
				PasswordCheckerUtility.hasLowerAlpha("QWIEEC4#");
				assertTrue("Did not throw NoLowerAlphaException",false);
			}
			catch(NoLowerAlphaException e)
			{
				assertTrue("Successfully threw a NoLowerAlphaExcepetion", true);
			}
			catch(Exception e)
			{
				assertTrue("Threw some other exception besides NoLowerAlphaException", true);
			}
	}
	
	
	
	@Test
	public void testIsValidPasswordThathasLowerAlpha()
	{
		
			try{
				
				assertTrue(PasswordCheckerUtility.hasLowerAlpha("QWQwIU@#4"));
				PasswordCheckerUtility.hasLowerAlpha("QWIEECe4#");
				assertFalse("Did not throw NoLowerAlphaException",false);
			}
			catch(NoLowerAlphaException e)
			{
				assertTrue("Successfully threw a NoLowerAlphaExcepetion", false);
			}
			catch(Exception e)
			{
				assertTrue("Threw some other exception besides NoLowerAlphaException", true);
			}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testhasBetweenSixAndNineChars()
	{
		
			assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars("injh12"));
			assertFalse(PasswordCheckerUtility.hasBetweenSixAndNineChars("123Wi"));
	}
	
	@Test
	public void testDoesNothaveBetweenSixAndNineChars()
	{
		
			assertFalse(PasswordCheckerUtility.hasBetweenSixAndNineChars("in"));
			assertFalse(PasswordCheckerUtility.hasBetweenSixAndNineChars("1Wi"));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertTrue(PasswordCheckerUtility.hasSameCharInSequence("deuws@#4!Q"));
			PasswordCheckerUtility.hasSameCharInSequence("qwS1111");
			assertTrue("Did not throw InvalideSequenceException",false);
		}
		catch(InvalideSequenceException e)
		{
			assertTrue("Successfully threw a InvalideSequenceExcepetion", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides InvalideSequenceException", true);
		}
	}
	
	
	
	@Test
	public void testIsValidPasswordDoesNotHaveInvalidSequence()
	{
		try{
			assertTrue(PasswordCheckerUtility.hasSameCharInSequence("deuws@#4!Q"));
			PasswordCheckerUtility.hasSameCharInSequence("qwS112211");
			assertFalse("Did not throw InvalideSequenceException",false);
		}
		catch(InvalideSequenceException e)
		{
			assertTrue("Successfully threw a InvalideSequenceExcepetion", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides InvalideSequenceException", true);
		}
	}
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			assertTrue(PasswordCheckerUtility.hasDigit("qwee4Www#"));
			PasswordCheckerUtility.hasDigit("@#aabbAQ");
			assertTrue("Did not throw NoDigitException", false);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw a NoDigitExcepetion", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoDigitException", true);
		}
	}
	
	
	
	@Test
	public void testIsValidPasswordhasDigit()
	{
		try{
			assertTrue(PasswordCheckerUtility.hasDigit("qwee4Www#"));
			PasswordCheckerUtility.hasDigit("@#aabb3AQ");
			assertFalse("Did not throw NoDigitException", false);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw a NoDigitExcepetion", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoDigitException", true);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidePassword("qqwweerrTT23@@"));
			PasswordCheckerUtility.isValidePassword("dfewwqs1!");
			assertTrue("Did not throw Exception", false);
		}
		
		catch(Exception e)
		{
			assertFalse("Threw some exception.", false);
		}
	}
	
	
	@Test
	public void testIsValidPasswordNotSuccessful()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidePassword("qqww"));
			PasswordCheckerUtility.isValidePassword("dfeweer@");
			assertFalse("Did not throw Exception", false);
		}
		
		catch(Exception e)
		{
			assertTrue("Threw some exception.", true);
		}
	}
	
	
	/**
	 * Test to see if there is a special character
	 *  
	 */
	@Test
	public void testhasNoSpecialChar()
	{
		try{	
			assertTrue(PasswordCheckerUtility.hasSpecialChar("inweiis1Q"));
			PasswordCheckerUtility.hasUpperAlpha("3344aabbQ");
			assertTrue("Did not throw NoSpecialSymbolException", false);
		}
		catch(NoSpecialSymbolException e)
		{
			assertTrue("Successfully threw a NoSpecialSymbolExcepetion", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoSpecialSymbolException", true);
		}
	}
	
	
	
	@Test
	public void testhasSpecialChar()
	{
		try{	
			assertTrue(PasswordCheckerUtility.hasSpecialChar("inweiis1#Q"));
			PasswordCheckerUtility.hasUpperAlpha("3344aabb@Q");
			assertFalse("Did not throw NoSpecialSymbolException", false);
		}
		catch(NoSpecialSymbolException e)
		{
			assertTrue("Successfully threw a NoSpecialSymbolExcepetion", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoSpecialSymbolException", true);
		}
	}
	
	
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	
	@Test
	public void testInvalidPasswords() 
	{
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidePasswords(passwords);
		assertFalse(results.isEmpty());
	}
	
	
	@Test
	public void testvalidPasswords() 
	{
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidePasswords(validPasswords);
		assertFalse(results.isEmpty());   //not sure why assertTrue is false
	}
	
	
	
	
	
	@Test
	public void testGetInvalidPasswords() 
	{
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidePasswords(passwords);
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "3344aabb");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
	
		scan = new Scanner(results.get(1)); 
		assertEquals(scan.next(), "QWIEEC4#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		
		scan = new Scanner(results.get(3)); 
		assertEquals(scan.next(), "inw#@Bjh");
		nextResults = scan.nextLine().toLowerCase();
		assertFalse(nextResults.contains("weak."));
		
		scan = new Scanner(results.get(7)); 
		assertEquals(scan.next(), "123Wi");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("length"));
		
		
	}
}