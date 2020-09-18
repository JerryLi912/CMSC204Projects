import java.util.ArrayList;

import java.util.regex.*;
/**
 * 
 * @author Jerry Li
 * This class is about the implementation of password checker.
 *
 */

public class PasswordCheckerUtility {
	
	private static boolean passwords = false, passwordss = true;
	
	public PasswordCheckerUtility(){}
	
	
	public static void comparePasswords(String password, String passwordConfirm)throws UnmatchedException_Test{
		
		if (password.contentEquals(passwordConfirm) == false) {
			throw new UnmatchedException_Test();
		}
	}
	
	
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		
		if (password.contentEquals(passwordConfirm) == false) {
			return false;
		}
		
		return true;
	}
	public static boolean isValidLength(String password) throws LengthException{
		
		if (password.length() < 6 ) {
			 throw new LengthException(passwords);
			
		}
		return true;
	}
	
	public static boolean hasDigit(String password) throws NoDigitException{
		Pattern pattern1 = Pattern.compile("(.)*(\\d)(.)*");
		Matcher matcher1 = pattern1.matcher(password);
		if (matcher1.matches() == false) {
			 throw new NoDigitException(passwords);
		}
		
		return true;
	}
	
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
		
		Pattern pattern2 = Pattern.compile(".*[A-Z].*");
		Matcher matcher2 = pattern2.matcher(password);	
		if (matcher2.matches() == false) {
			throw new NoUpperAlphaException(passwords);
		}
		return true;
	}
	
	
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
		Pattern pattern3 = Pattern.compile(".*[a-z].*");
		Matcher matcher3 = pattern3.matcher(password);
		if (matcher3.matches() == false) {
		throw new NoLowerAlphaException(passwords);
		}
		return true;
	}
	
	
	public static boolean hasSpecialChar(String password) throws NoSpecialSymbolException{
		Pattern pattern4 = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher4 = pattern4.matcher(password);
		if (matcher4.matches() == true) {
			throw new NoSpecialSymbolException(passwords);
		}
		
		return true;
	}
	
	public static boolean hasSameCharInSequence(String password) throws InvalideSequenceException{
		for (int i = 0; i < password.length(); i++) {
			
			if(i < password.length()-2 && password.charAt(i) == password.charAt(i+1) && password.charAt(i+1) == password.charAt(i+2)) {
			
				throw new InvalideSequenceException(passwords);
			}
		}
		return true;
	}
	
	
	public static boolean hasBetweenSixAndNineChars(String password){
		if (password.length() >=6 && password.length() <= 9) {
			return true;
		}
		return false;
	}
	
	
	public static boolean isValidePassword(String passwordString) throws LengthException, NoDigitException, NoUpperAlphaException,
	NoLowerAlphaException, NoSpecialSymbolException, InvalideSequenceException{
		
		
		if (passwordString.length() < 6 ) {
			 throw new LengthException(passwords);
			
		}
		
		Pattern pattern1 = Pattern.compile("(.)*(\\d)(.)*");
		Matcher matcher1 = pattern1.matcher(passwordString);
		if (matcher1.matches() == false) {
			 throw new NoDigitException(passwords);
		}
		
		
		Pattern pattern2 = Pattern.compile(".*[A-Z].*");
		Matcher matcher2 = pattern2.matcher(passwordString);	
		if (matcher2.matches() == false) {
			throw new NoUpperAlphaException(passwords);
		}
		
		
		Pattern pattern3 = Pattern.compile(".*[a-z].*");
		Matcher matcher3 = pattern3.matcher(passwordString);
		if (matcher3.matches() == false) {
		throw new NoLowerAlphaException(passwords);
		}
		
		
		Pattern pattern4 = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher4 = pattern4.matcher(passwordString);
		if (matcher4.matches() == true) {
			throw new NoSpecialSymbolException(passwords);
		}
		
	
		for (int i = 0; i < passwordString.length(); i++) {
				
			if(i < passwordString.length()-2 && passwordString.charAt(i) == passwordString.charAt(i+1) && passwordString.charAt(i+1) == passwordString.charAt(i+2)) {
			
				throw new InvalideSequenceException(passwords);
			}
		}
		
		return true;
	}
	
	
	public static boolean isWeakPassword(String passwordString) throws WeakPasswordException {
	
		 
		if (passwordString.length() >=6 && passwordString.length() <= 9) {
			throw new WeakPasswordException(true);
		}
			
		
		return false;
	}
	
	public static ArrayList<String> getInvalidePasswords(ArrayList<String> passwordArray){
	
	ArrayList<String>illegalPasswords = new ArrayList<String>();	
	for (String num : passwordArray) {
			if(num != null) {
			illegalPasswords.add(passwordCheck(num));
			}
	}
		
		return illegalPasswords;
	}
	
		
	private static String passwordCheck(String password) {
		String message = "";
		
			try {
				if(isValidePassword(password) == true) {
					return message;
				}
			} catch (LengthException e) {
				
				message = password + " -> " + e + "\n";
				System.out.print(message);
			} catch (NoDigitException e) {
				
				message = password + " -> " + e + "\n";
				System.out.print(message);
			} catch (NoUpperAlphaException e) {
				
				message = password + " -> " + e + "\n";
				System.out.print(message);
			} catch (NoLowerAlphaException e) {
				
				message = password + " -> " + e + "\n";
				System.out.print(message);
			} catch (NoSpecialSymbolException e) {
				
				message = password + " -> " + e + "\n";
				System.out.print(message);
			} catch (InvalideSequenceException e) {
				
				message = password + " -> " + e + "\n";
				System.out.print(message);
			}
		
		return message;
	}
	
	
	
}

 class LengthException extends Exception {
	 final boolean value;
	 public LengthException(boolean value) {
		super("Password should be at least 6 characters long.");
		this.value = value;
	}
	 
	 public boolean getValue(boolean value) {
		 return value;
	 }
}
 
 class NoDigitException extends Exception {
	 final boolean value;
	 public NoDigitException(boolean value) {
		 super("Password should contain at least 1 digit.");
		 this.value = value;
	 }
	 
	 public boolean getValue(boolean value) {
		 return value;
	 }
 }

 class NoUpperAlphaException extends Exception {
	 final boolean value;
	 public NoUpperAlphaException(boolean value) {
		 super("Password should contain at least 1 uppercase alphabetic character.");
		 this.value = value;
	 }
	 
	 public boolean getValue(boolean value) {
		 return value;
	 }
 }
 
 class NoLowerAlphaException extends Exception {
	 final boolean value;
	 public NoLowerAlphaException(boolean value) {
		 super("Password should contain at least 1 lowercase alphabetic character.");
		 this.value = value;
	 }
	 
	 public boolean getValue(boolean value) {
		 return value;
	 }
 }
 
 class NoSpecialSymbolException extends Exception {
	 final boolean value;
	 public NoSpecialSymbolException(boolean value) {
		 super("Password should contain at least 1 special character, like @#$%^&.");
		 this.value = value;
	 }
	 
	 public boolean getValue(boolean value) {
		 return value;
	 }
 }
 
 class InvalideSequenceException extends Exception {
	 final boolean value;
	 public InvalideSequenceException(boolean value) {
		 super("Password should contain no more than two of the same character in a sequence.");
		 this.value = value;
	 }
	 
	 public boolean getValue(boolean value) {
		 return value;
	 }
 }
 
 class WeakPasswordException extends Exception {
	 final boolean value;
	 public WeakPasswordException(boolean value) {
		 super("Password contains 6 to 9 characters. It's OK but weak.");
		 this.value = value;
	 }
	 
	 public boolean getValue(boolean value) {
		 return value;
	 }
 }
 
 class UnmatchedException_Test extends Exception{
		public UnmatchedException_Test() {
			super("Passwords do not match.");
		}
	}
 
