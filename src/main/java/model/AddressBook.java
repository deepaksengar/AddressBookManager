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
		this.setName(DEFAULT_NAME);
	}
	
	public AddressBook(Set<Contact> contacts){
		this();
		this.contacts.addAll(contacts);
	}
	
	public AddressBook(String name, Set<Contact> contacts){
		this.contacts = new HashSet<>(contacts);
		this.setName(name);
	}
	
	public AddressBook addContact(Contact contact){
		this.getContacts().add(contact);
		return this;
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
		return this.name.hashCode() + this.contacts.hashCode() + this.contacts.size() * 31;
	}
	
}
