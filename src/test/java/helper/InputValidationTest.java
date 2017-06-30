package helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import helper.InputValidation;

public class InputValidationTest {
	
	@Test
	public void testValidString() throws Exception {
		assertEquals("String should be valid and return sanitised value with First Char capital.","Abctest", 
				InputValidation.validString(" abctest "));
	}
	
	@Test
	public void testInValidString() throws Exception {
		try{
			InputValidation.validString("");
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
				InputValidation.validPhoneNumber("+61 88 28 28 2"));
	}
	
	@Test
	public void testValidPhoneNumberWith3Digits() throws Exception {
		assertEquals("Phone number should be valid and return sanitised value.","000", 
				InputValidation.validPhoneNumber("000"));
	}
	
	@Test
	public void testValidPhoneNumberWithHyphen() throws Exception {
		assertEquals("Phone number should be valid and return sanitised value.","08828282", 
				InputValidation.validPhoneNumber(" 088-28-282"));
	}
	
	@Test
	public void testInValidPhoneNumber(){
		try{
				InputValidation.validPhoneNumber("abc6188z28282");
				fail("Should throw IllegalArgument Exception for invalid phone number.");
		} catch (Exception ex){
			assertTrue(ex instanceof IllegalArgumentException);
			assertEquals("Should return error message.","PhoneNumber is not valid.", 
					ex.getMessage());
		}
	}
	
	@Test
	public void testInValidPhoneNumberWithOnly2Digits(){
		try{
				InputValidation.validPhoneNumber("  2 - 8 - ");
				fail("Should throw IllegalArgument Exception for invalid phone number with only 3 Digits."
						+ " Valid Phone should have atleast 6 digits.");
		} catch (Exception ex){
			assertTrue(ex instanceof IllegalArgumentException);
			assertEquals("Should return error message.","PhoneNumber is not valid.", 
					ex.getMessage());
		}
	}
}
