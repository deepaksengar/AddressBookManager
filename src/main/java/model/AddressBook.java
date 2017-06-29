package model;

import java.util.HashSet;
import java.util.Set;

import helper.InputSanitiser;

public class AddressBook {
	
	private final String DEFAULT_NAME = "default";
	
	private String name;
	private Set<Contact> contacts;
	
	public AddressBook(){
		this.contacts = new HashSet<>();
		this.name = DEFAULT_NAME;
	}
	
	public AddressBook(Set<Contact> contacts){
		this();
		this.contacts.addAll(contacts);
	}
	
	public AddressBook(String name, Set<Contact> contacts){
		this.contacts = new HashSet<>(contacts);
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = InputSanitiser.validString(name);
	}
	
	public Set<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
}
