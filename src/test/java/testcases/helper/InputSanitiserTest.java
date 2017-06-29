package testcases.helper;

import static org.junit.Assert.*;
import org.junit.Test;
import helper.InputSanitiser;

public class InputSanitiserTest {
	
	@Test
	public void testValidString() throws Exception {
		assertEquals("String should be valid and return sanitised value with First Char capital.","Abctest", 
				InputSanitiser.validString(" abctest "));
	}
	
	@Test
	public void testInValidString() throws Exception {
		try{
			InputSanitiser.validString("");
			fail("Should throw IllegalArgument Exception for invalid String.");
		} catch (Exception ex){
			assertTrue(ex instanceof IllegalArgumentException);
			assertEquals("Should return error message.","String value is not present.", 
					ex.getMessage());
		}
	}
	
	@Test
	public void testValidPhoneNumber() throws Exception {
		assertEquals("Phone number should be valid and return sanitised value.","+618828282", 
				InputSanitiser.validPhoneNumber("+61 88 28 28 2"));
	}
	
	@Test
	public void testValidPhoneNumberWithHyphen() throws Exception {
		assertEquals("Phone number should be valid and return sanitised value.","08828282", 
				InputSanitiser.validPhoneNumber(" 088-28-282"));
	}
	
	@Test
	public void testInValidPhoneNumber(){
		try{
				InputSanitiser.validPhoneNumber("abc6188z28282");
				fail("Should throw IllegalArgument Exception for invalid phone number.");
		} catch (Exception ex){
			assertTrue(ex instanceof IllegalArgumentException);
			assertEquals("Should return error message.","PhoneNumber is not valid.", 
					ex.getMessage());
		}
	}
	
	@Test
	public void testInValidPhoneNumberWithOnly3Digits(){
		try{
				InputSanitiser.validPhoneNumber("  2 - 8 - 2");
				fail("Should throw IllegalArgument Exception for invalid phone number with only 3 Digits."
						+ " Valid Phone should have atleast 6 digits.");
		} catch (Exception ex){
			assertTrue(ex instanceof IllegalArgumentException);
			assertEquals("Should return error message.","PhoneNumber is not valid.", 
					ex.getMessage());
		}
	}
}
