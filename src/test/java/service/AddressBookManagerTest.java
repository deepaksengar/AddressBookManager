package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.AddressBook;

public class AddressBookManagerTest {
	
	AddressBookManager addressBookManager;
	
	@Before
	public void init(){
		addressBookManager = new AddressBookManager();
	}
	
	public void addContact(){
		addressBookManager.addContact("frodo", "+61-3-9118-1919");
	}
	
	public void addContactInDifferentAddressBook(){
		addressBookManager.addContact("MyNewAddressBook","frodo", "+61-3-9118-1919");
	}
	
	@Test
	public void addMultipleContacts(){
		addContact();
		addressBookManager.addContact("emergency", "000");
		assertEquals("Should have only one address book", 1, addressBookManager.getAddressBooks().size());
		assertEquals("Default address book should have 2 contacts",2,addressBookManager.getAddressBooks().stream().findAny().get().getContacts().size());
	}
	
	@Test
	public void testInitialNoAddressBook(){
		assertTrue("Initially AddressBook Manager will not have any address book.",addressBookManager.getAddressBooks().size() == 0);
	}
	
	@Test
	public void testAddContact(){
		assertTrue("Initially AddressBook Manager will not have any address book.",addressBookManager.getAddressBooks().size() == 0);
		addContact();
		assertTrue("AddressBook Manager will create default address book.",addressBookManager.getAddressBooks().size() == 1);
		assertEquals("Name of AddressBook will be Default.","Default", addressBookManager.getAddressBooks().iterator().next().getName());
		
		AddressBook addressBook = addressBookManager.getAddressBooks().iterator().next();
		assertEquals("Name of AddressBook will be Default.","Default", addressBook.getName());
	}
	
	@Test
	public void testRemoveContact(){
		addContact();
		AddressBook addressBook = addressBookManager.getAddressBooks().iterator().next();
		assertEquals("AddressBook will have one contact.",1,addressBook.getContacts().size());
		assertTrue("Remove method should return TRUE in case of contact has been removed",addressBookManager.removeContact("frodo"));
		assertEquals("AddressBook will have Zero contact after remove call.",0,addressBook.getContacts().size());
	}
	
	@Test
	public void testRemoveContactNegative(){
		addContact();
		boolean value = !addressBookManager.removeContact("frodo1");
		assertTrue("Remove method should return FALSE in case of contact doesn't exist.",value);
	}
	
	@Test
	public void testRemoveContactNegativeWithNoAddressBook(){
		boolean value = !addressBookManager.removeContact("frodo1");
		assertTrue("Remove method should return FALSE in case of No AddressBook exist.",value);
	}
	
	@Test
	public void testAddressBookWithName(){
		assertTrue("Initially AddressBook Manager will not have any address book.",addressBookManager.getAddressBooks().size() == 0);
		addContactInDifferentAddressBook();
		assertTrue("AddressBook Manager will create address book.",addressBookManager.getAddressBooks().size() == 1);
		assertEquals("Name of AddressBook will be user-defined.","MyNewAddressBook", addressBookManager.getAddressBooks().iterator().next().getName());
	}
	
	@Test
	public void testMultipleAddressBook(){
		assertTrue("Initially AddressBook Manager will not have any address book.",addressBookManager.getAddressBooks().size() == 0);
		addContactInDifferentAddressBook();
		addContact();
		assertTrue("AddressBook Manager will have TWO address books.",addressBookManager.getAddressBooks().size() == 2);
	}
	
	@Test
	public void testUniqueContacts(){
		addContactInDifferentAddressBook();
		addContact();
		assertTrue("Address Book merge will return One contact as Unique as 2 addressBook has same contact.",
					addressBookManager.mergeAllAddressBook().size() == 1);
		
	}
	
	@Test
	public void testRemoveContactsFromAllAddressBooksAndMerge(){
		addContactInDifferentAddressBook();
		AddressBook addressBook = addressBookManager.getAddressBooks().iterator().next();
		assertEquals("AddressBook - MyNewAddressBook will have one contact.",1,addressBook.getContacts().size());
		
		addressBookManager.removeContact("MyNewAddressBook","frodo");
		
		addContact();
		addressBookManager.removeContact("frodo");
		
		assertTrue("Address Book merge will return 0 contact.",
					addressBookManager.mergeAllAddressBook().size() == 0);
		
	}
}
