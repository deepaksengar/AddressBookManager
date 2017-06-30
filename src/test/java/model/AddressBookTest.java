package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import helper.InputValidation;
import model.AddressBook;
import model.Contact;

public class AddressBookTest {
	
	AddressBook addressBook;
	String addressBookName = InputValidation.validString("default");
	
	@Before
	public void init(){
		addressBook = new AddressBook(addressBookName);
	}
	
	@Test
	public void testAddressBookCreation() throws Exception {
		assertEquals("AddressBook should be created with Name.","Default", 
				addressBook.getName());
		assertEquals("Address Book should initialized with 0 contacts.",0, 
				addressBook.getContacts().size());
	}
	
	@Test
	public void testAddressBookContacts() throws Exception {
		Contact contact = new Contact("john", "046-892-83");
		addressBook.addContact(contact);
		
		Contact contact2 = new Contact("john", "046-892-84");
		addressBook.addContact(contact2);
		
		assertEquals("Address Book should contain 2 contacts.",2, 
				addressBook.getContacts().size());
	}
	
	@Test
	public void testAddressBookDuplicateContacts() throws Exception {
		Contact contact = new Contact("john", "046-892-83");
		addressBook.addContact(contact);
		
		Contact contact2 = new Contact("john", "046-892-83");
		addressBook.addContact(contact2);
		//Duplicate contact should not get added in addressbook.
		assertEquals("Address Book should contain 1 contact.",1, 
				addressBook.getContacts().size());
	}

}
