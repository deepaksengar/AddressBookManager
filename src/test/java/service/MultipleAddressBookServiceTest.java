package service;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.AddressBook;
import model.Contact;

public class MultipleAddressBookServiceTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	MultipleAddressBookService service;

	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outContent));
	    service = new MultipleAddressBookServiceImpl();
	}

	@After
	public void cleanUp() {
	    System.setOut(null);
	}
	
	public AddressBook addContact(){
		return service.addContact("frodo", "0061 3 982-123-11");
	}
	
	public AddressBook addContact(String addressBookName){
		return service.addContact(addressBookName,"frodo", "0061 3 982-123-11");
	}
	
	/*
	 * Address book will hold name and phone numbers of contact entries
	 */
	@Test
	public void testContactNameAndNumber(){
		addContact();
		Contact contact = service.getContact("frodo");
		assertEquals("Should create Contact with name Frodo","Frodo", contact.getName());
		assertEquals("Should create Contact with Phone number","0061398212311", contact.getPhoneNumber());
	}
	
	/*
	 * Users should be able to add new contact entries
	 */
	@Test
	public void testAddContact(){
		AddressBook addressBook = addContact();
		assertEquals("Should create default addressBook.","Default", addressBook.getName());
		Contact contact = service.getContact("frodo");
		assertEquals("Should create Contact with name Frodo","Frodo", contact.getName());
	}
	
	@Test
	public void testAddressBookCreationWithContact(){
		AddressBook addressBook = addContact("lotr");
		assertEquals("Should create AddressBook with name Lotr", "Lotr", addressBook.getName());
	}
	
	@Test
	public void testAddContactNegative(){
		AddressBook addBook = service.addContact("", "0061 3 982-123-11");
		assert(addBook == null);
		assertEquals("Should print .", "Contact not added. Error Message: Contact Name is not valid. String value is not present.",
				outContent.toString().trim());
	}
	/*
	 * Users should be able to print all contacts in an address book
	 */
	@Test
	public void testPrintContact(){
		addContact();
		service.printContacts();
		assertEquals("Should print addressBook name along with Contact details.",
				"Contacts from AddressBook: Default\nName: Frodo , PhoneNumber: 0061398212311",
				outContent.toString().trim());
	}
	
	@Test
	public void testPrintContactFromAddressBook(){
		addContact("lotr");
		service.printContacts("lotr");
		assertEquals("Should print addressBook name along with Contact details.",
				"Contacts from AddressBook: Lotr\nName: Frodo , PhoneNumber: 0061398212311",
				outContent.toString().trim());
	}
	
	/*
	 * Users should be able to remove existing contact entries
	 */
	@Test
	public void testRemoveContact(){
		addContact();
		
		boolean isRemoved = service.removeContact("frodo");
		
		assertTrue("Should return True when contact is removed.",isRemoved);
		
		service.printContacts();
		assertEquals("Should print no contact found, if there is not contact.", "No contacts found.",
				outContent.toString().trim());
	}
	
	@Test
	public void testRemoveContactNegative(){
		addContact();
		
		boolean isRemoved = service.removeContact("frodo-new");
		
		assertFalse("Should return False when contact is not removed.",isRemoved);
	}
	
	/*
	 * Users should be able to print a unique set of all contacts across multiple address books
	 * Users should be able to maintain multiple address books
	 */
	@Test
	public void testMultipleAddressBook(){
		AddressBook addressBookLotr = addContact("lotr");
		AddressBook addressBook = addContact();
		AddressBook addressBookNew = addContact("newAddressBook");
		
		service.addContact(addressBookLotr.getName(),"sam","049-19191-11");
		service.addContact("emergency", "000");
		service.addContact("emergency other", "000 - 132");
		service.addContact(addressBookNew.getName(), "  car service  ","     1300-008");
		service.addContact("newAddressBook", "  car service new ","     1100-007");
		
		service.printUniqueContacts();
		assertEquals("Should print uniuqe Contact details. Frodo's details should only be printed once.", 
				"Name: Car service , PhoneNumber: 1300008\n" +
				"Name: Car service new , PhoneNumber: 1100007\n" +
				"Name: Emergency , PhoneNumber: 000\n" +
				"Name: Emergency other , PhoneNumber: 000132\n" +
				"Name: Frodo , PhoneNumber: 0061398212311\n" +
				"Name: Sam , PhoneNumber: 0491919111",
				outContent.toString().trim());
	}
	
	/*
	 * Users should be able to print a unique set of all contacts across multiple address books
	 */
	@Test
	public void testPrintUniqueContact(){
		addContact("lotr"); //AddressBook: Lotr
		addContact();		//AddressBook: Default
		
		service.printUniqueContacts();
		assertEquals("Should print Contact details.", "Name: Frodo , PhoneNumber: 0061398212311",
				outContent.toString().trim());
	}

}
