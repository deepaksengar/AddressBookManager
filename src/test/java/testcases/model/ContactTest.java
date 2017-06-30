package testcases.model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Contact;

public class ContactTest {
	
	@Test
	public void testContactCreation() throws Exception {
		Contact contact = new Contact("john", "046-892-83");
		
		assertEquals("Contact should be created with Name.","John", 
				contact.getName());
		assertEquals("Contact should be created with Phone.","04689283", 
				contact.getPhoneNumber());
	}
	
	@Test
	public void testContactCreationNegativeWithoutName() throws Exception {
		try{
			Contact contact = new Contact("", "046-892-83");
			fail("Empty Name should not create new contact.");
		} catch (Exception ex){
			assert(ex instanceof IllegalArgumentException);
			assertEquals("Error Message.","String value is not present.", 
					ex.getMessage());
		}
	}
	
	@Test
	public void testContactCreationNegativeWithoutPhone() throws Exception {
		try{
			Contact contact = new Contact("john", "---");
			fail("Empty Name should not create new contact.");
		} catch (Exception ex){
			assert(ex instanceof IllegalArgumentException);
			assertEquals("Error Message.","PhoneNumber is not valid.", 
					ex.getMessage());
		}
	}
	
	@Test
	public void testContactDisplay() throws Exception {
		Contact contact = new Contact("john", "046-892-83");
		assertEquals("Should display name and phoneNumber.","Name: John , PhoneNumber: 04689283", 
					contact.toString());
	}

}
