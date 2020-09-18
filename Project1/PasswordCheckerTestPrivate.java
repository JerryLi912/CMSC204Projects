

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class PasswordCheckerTestPrivate {
	ArrayList<String> invalidPasswordsArray;
	ArrayList<String> validPasswordsArray;
	String password = "Hello";
	String passwordConfirm = "hello";
	String allCaps = "HELLO";
	String withDigit = "Hello6";
	String withSpecialChar = "Hello!";
	String withNoDuplicate = "GoodBye!";
	String between6And9Chars = "JavaisFun";
	String longPassword = "JavaisFunveryfun";

	@BeforeEach
	void setUp() throws Exception {
		String[] containsInvalidPwd = {"334455BB", "Im2cool4U", "george2ZZZ", "4sale", "bertha22", "4wardMarch", 
				"august30", "Applesxx", "aa11b", "pilotProject", "myPassword", 
				"myPassword2"};
		invalidPasswordsArray = new ArrayList<String>();
		invalidPasswordsArray.addAll(Arrays.asList(containsInvalidPwd));		
		
		String[] allValidPasswords = {"a1A#b1Bc1Cd1D", "Hello@123", "4heB#rex7", "4saleHe!", "myPassword2%"};
		validPasswordsArray = new ArrayList<String>();
		validPasswordsArray.addAll(Arrays.asList(allValidPasswords));		
	}

	@AfterEach
	void tearDown() throws Exception {
		invalidPasswordsArray = null;
		validPasswordsArray= null;
	}

	@Test
	void testComparePasswords() {
		Throwable exception = assertThrows(UnmatchedException_Test.class, new Executable() {			
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.comparePasswords(password, passwordConfirm);				
			}
		});
		
		assertEquals("Passwords do not match.", exception.getMessage());
	}
	
	@Test 
	void testComparePasswordsWithReturn() {
		assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
		assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
	}	
	
	@Test
	void testValidLengthValid() {
		try {
			assertTrue(PasswordCheckerUtility.isValidLength("Beautiful"));
		} catch (LengthException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testInValidLength() {
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength(password);
			}			
		});
		assertEquals("Password should be at least 6 characters long.", exception.getMessage());
	}
	
	@Test
	void testHasUpperAlphaValid() {
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("Beautiful"));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDoesNotHaveUpperAlpha() {
		Throwable exception = assertThrows(NoUpperAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasUpperAlpha(passwordConfirm);
			}			
		});
		assertEquals("Password should contain at least 1 uppercase alphabetic character.", exception.getMessage());		
	}
	
	@Test
	public void testHasLowerAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(password));
		} catch (NoLowerAlphaException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testDoesNotHaveLowerAlpha() {
		Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasLowerAlpha(allCaps);
			}			
		});
		assertEquals("Password should contain at least 1 lowercase alphabetic character.", exception.getMessage());
		
	}
	
	@Test
	public void testHasDigit() {
		try {
			assertTrue(PasswordCheckerUtility.hasDigit(withDigit));
		} catch (NoDigitException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testDoesNotHaveDigit() {
		Throwable exception = assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasDigit(password);
			}			
		});
		assertEquals("Password should contain at least 1 digit.", exception.getMessage());		
	}
	
	@Test
	public void testHasSpecialChar() {
		try {
			assertTrue(PasswordCheckerUtility.hasSpecialChar(withSpecialChar));
		} catch (NoSpecialSymbolException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testDoesNotHaveSpecialChar() {
		Throwable exception = assertThrows(NoSpecialSymbolException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSpecialChar(password);
			}			
		});
		assertEquals("Password should contain at least 1 special character, like @#$%^&.", exception.getMessage());		
	}
	
	@Test
	public void testHasSameCharInSequence() {
		Throwable exception = assertThrows(InvalideSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSameCharInSequence("AAAbb@123");
			}			
		});
		assertEquals("Password should contain no more than two of the same character in a sequence.", exception.getMessage());	
	}
	
	@Test
	public void testDoesNotHaveSameCharInSequence() {
		try {
			assertTrue(PasswordCheckerUtility.hasSpecialChar(withNoDuplicate));
		} catch (NoSpecialSymbolException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testHasBetweenSixAndNineChars() {
		
		assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(between6And9Chars));	
		assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(withSpecialChar));	
		assertFalse(PasswordCheckerUtility.hasBetweenSixAndNineChars(longPassword));	
	}
	
	@Test
	public void testGetInvalidPasswords() {
	
		
		
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidePasswords(invalidPasswordsArray);
		assertEquals(results.size(), 12);
		assertEquals(results.get(0), "334455BB -> Password should contain at least 1 lowercase alphabetic character.");
		assertEquals(results.get(1), "Im2cool4U -> Password should contain at least 1 special character, like @#$%^&.");
		assertEquals(results.get(2), "george2ZZZ -> Password should contain at least 1 special character, like @#$%^&.");
		assertEquals(results.get(3), "4sale -> Password contains 6 to 9 characters. It's OK but weak.");
		assertEquals(results.get(4), "bertha22 -> Password should contain at least 1 uppercase alphabetic character."); 
		assertEquals(results.get(5), "4wardMarch -> Password should contain at least 1 special character, like @#$%^&.");
		assertEquals(results.get(6), "august30 -> Password should contain at least 1 uppercase alphabetic character.");
		assertEquals(results.get(7), "Applesxx -> Password should contain at least 1 digit.");
		assertEquals(results.get(8), "aa11b -> Password contains 6 to 9 characters. It's OK but weak.");
		assertEquals(results.get(9), "pilotProject -> Password should contain at least 1 digit.");
		assertEquals(results.get(10),"myPassword -> Password should contain at least 1 digit.");
		assertEquals(results.get(11),"myPassword2 -> Password should contain at least 1 special character, like @#$%^&.");
	}
	
	@Test
	public void testGetValidPasswords() {
		 ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidePasswords(validPasswordsArray);
		assertTrue(results.isEmpty());
		
		
		
	
	}
	

}