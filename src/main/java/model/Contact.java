package model;

import helper.InputSanitiser;

public class Contact {
	
	private String name;
	private String phoneNumber;
	
	public Contact(String name, String phoneNumber){
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = InputSanitiser.validString(name);
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = InputSanitiser.validPhoneNumber(phoneNumber);
	}
	
	/*
	 * Overriding Equals and HashCode method so that unique Contacts will be saved in Set
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Contact))
			return false;
		
		Contact newContact = (Contact) obj;
		
		if(this == newContact)
			return true;
		
		if(this.getName().equals(newContact.getName()) && this.getPhoneNumber().equals(newContact.getPhoneNumber())){
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.getName().hashCode() + this.getPhoneNumber().hashCode();
		
	}
	

}
