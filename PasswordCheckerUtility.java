import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordCheckerUtility {
	
	public static void comparePasswords(String password,String passwordConfirm) throws UnmatchedException {
		if(!(password.equals(passwordConfirm))) {
			throw new UnmatchedException();
		}
	}
	
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if((password.equals(passwordConfirm))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String> updated = new ArrayList<String>();
		
		//enhanced loop through every password, checks if its valid using the method, and if it catches an exception of the ones we created, it will add the password to a new
		//arraylist.
		for(String s : passwords) {
			try {
				isValidPassword(s);
			}catch(Exception e){
				updated.add(s + " " + e.getMessage());
			}
		}
		
		return updated;
	}
	
	public static boolean hasBetweenSixAndNineChars(String password) {
		if(password.length() >= 6 && password.length() <=9) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean hasDigit(String password)throws NoDigitException {
			String[] temp = password.split("");
	        for (String str : temp) //Checks every password in the ArrayList 
	        	
			if (str.matches(".*\\d.*")) // Checks that the password has a digit
			{
				return true;
			}
	        throw new NoDigitException();
//		char [] letters = password.toCharArray();
//		boolean result = false;
//		for(char l : letters) {
//			switch(l) {
//				case '1':
//				case '2':
//				case '3':
//				case '4':
//				case '5':
//				case '6':
//				case '7':
//				case '8':
//				case '9':
//				case '0':
//					result = true;
//					break;
//			}
//			if(result) {
//				return result;
//			}
//			throw new NoDigitException();
//			
//		}
			
//		int count = 0;
//		for(int i = 0; i < password.length(); i++){
//			if(password.charAt(i) == '1' || password.charAt(i) == '2' || password.charAt(i) == '3' || password.charAt(i) == '4' || password.charAt(i) == '5' || password.charAt(i) == '6'|| password.charAt(i) == '7'|| password.charAt(i) == '8'|| password.charAt(i) == '9' || password.charAt(i) == '0') {
//				count++;
//			}
//		}
//		if(count >=1) {
//			return true;
//		}else {
//			throw new NoDigitException();
//		}
	}
	
	public static boolean hasLowerAlpha(String password)throws NoLowerAlphaException {
		String [] arr = password.split("");
		for(String s : arr) {
			if(s.matches("[a-z]")) {
				return true;
			}
		}
		throw new NoLowerAlphaException();
//		String re = "[a-z]+";
//		
//		Pattern pt = Pattern.compile(re);
//		Matcher mt = pt.matcher(password);
//		boolean result = mt.matches();
//		if(result) {
//			return result;
//		}
//			throw new NoLowerAlphaException();
		
	}
	
	public static boolean hasSpecialChar(String password)throws NoSpecialCharacterException {
		String [] arr = password.split("");
		for(String s : arr) {
			if(s.matches("[^\\w]")) {
				return true;
			}
		}
		throw new NoSpecialCharacterException();
//		String re = "[$&+,:;=?@#|'<>.-^*()%!]";
//		Pattern pt = Pattern.compile(re);
//		Matcher mt = pt.matcher(password);
//		boolean result = mt.matches();
//		if( result) {
//			return result;
//		}else {
//			throw new NoSpecialCharacterException();
//		}
		
	}
	public static boolean hasUpperAlpha(String password)throws NoUpperAlphaException {
		String [] arr = password.split("");
		for(String s : arr) {
			if(s.matches("[A-Z]")) {
				return true;
			}
		}
		throw new NoUpperAlphaException();
//		String re = "[A-Z]+";
//		
//		Pattern pt = Pattern.compile(re);
//		Matcher mt = pt.matcher(password);
//		boolean result = mt.matches();
//		if(result) {
//			return result;
//		}else {
//			throw new NoUpperAlphaException();
//		}
	}
	
	public static boolean isValidLength(String password)throws LengthException {
		if(password.length() >= 6) {
			return true;
		}else {
			throw new LengthException();
		}
		
	}
	
	public static boolean isValidPassword(String password) throws LengthException,NoUpperAlphaException,NoLowerAlphaException,NoDigitException,NoSpecialCharacterException,InvalidSequenceException {
		
		if(isValidLength(password)) {
			
			if(hasUpperAlpha(password) && hasLowerAlpha(password) && hasDigit(password) &&
					hasSpecialChar(password) && NoSameCharInSequence(password)) {
				
				return true;
			}
			
		}
		
		return false;
		//will throw exception if false/ doesn't meet the criteria of it being a valid length, having at least 1 upper case letter, lower case,
		//has at least 1 number, has special character, and doesnt have more than 2 repeating characters in the same sequence. Returns true, if everything didn't throw an exception
	}
	
	//Checks if password is VALID and the length is NOT between 6-9 characters. false if the password is valid
	public static boolean isWeakPassword(String password) throws WeakPasswordException{
		if(password.length() < 6 || password.length() > 9) {
			return false;
		}else {
			throw new WeakPasswordException();
		}
	}
	
	//Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
		for(int i = 0; i < password.length()-2;i++) {
			if(password.charAt(i) == password.charAt(i+1) && password.charAt(i) == password.charAt(i+2) && password.charAt(i+1) == password.charAt(i+2) ) {
				throw new InvalidSequenceException();
			}
		}
		return true;
//		for(int i = 0; i < password.length() -1; i++) {
//			if(password.charAt(i) == password.charAt(i+1)) {
//				throw new InvalidSequenceException();
//			}
//		}
//		return true;
//		String re = "(.)\1{2,}";
//		
//		Pattern pt = Pattern.compile(re);
//		Matcher mt = pt.matcher(password);
//		boolean result = mt.matches();
//		if(result) {
//			return result;
//		}else {
//			throw new InvalidSequenceException();
//		}
	}
}
