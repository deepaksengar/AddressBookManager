package model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import helper.InputValidation;

public class AddressBook {
	
	private String name;
	private Set<Contact> contacts;
	
	public AddressBook(String addressBookName){
		this.setName(addressBookName);
	}
	
	
	public AddressBook(String name, Set<Contact> contacts){
		this.contacts = new HashSet<>(contacts);
		this.setName(name);
	}
	
	public AddressBook addContact(Contact contact){
		this.getContacts().add(contact);
		return this;
	}
	
	public boolean removeContact(Contact contact){
		return this.getContacts().remove(contact);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = InputValidation.validString(name);
	}
	
	public Set<Contact> getContacts() {
		if(contacts == null)
			contacts = new HashSet<>();
		
		return contacts;
	}
	
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof AddressBook){
	        return (this.name.equals(((AddressBook) obj).getName())); 
	    }            		
	    else
	        return false;
	}
	
	@Override
	public int hashCode(){
		return this.name.hashCode() + this.getContacts().hashCode() + this.name.hashCode() * this.getContacts().size() * 31;
	}


	public boolean removeContact(String contactName) {
		Optional<Contact> contact = this.getContacts().stream()
									.filter(c -> c.getName().equals(InputValidation.validString(contactName)))
									.findFirst();
		
		return this.removeContact(contact.orElse(null));
	}
	
}
