package helper;

import java.util.regex.Pattern;

public class InputSanitiser {
	
	/*
	 * returns Trimmed String input with making first letter capital.
	 */
	public static String validString(String input) throws IllegalArgumentException {
		if (input == null || input.length() == 0) {
	        throw new IllegalArgumentException("String value is not present.");
	    }
		input = input.trim(); 
		input = input.substring(0, 1).toUpperCase() + input.substring(1);
		return input;
	}
	
	public static String validPhoneNumber(String input) throws IllegalArgumentException {
		if (input == null || input.length() == 0) {
	        throw new IllegalArgumentException("PhoneNumber is not present.");
	    }
		
		String phoneNumberRegex = "^\\+?[0-9 -]{3,20}$";
		
		input = input.trim();
		
		Pattern pattern = Pattern.compile(phoneNumberRegex);
		boolean matchesPattern = pattern.matcher(input).matches();
        
        input = input.trim().replaceAll("[- ]+","");
        
        if(!matchesPattern || input.length() < 3){
        	throw new IllegalArgumentException("PhoneNumber is not valid.");
        }
        
        return input;
	}
	
}
