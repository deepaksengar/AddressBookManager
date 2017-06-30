package model;

import helper.InputValidation;

public class Contact {
	
	private String name;
	private String phoneNumber;
	
	public Contact(){
		
	}
	
	public Contact(String name, String phoneNumber){
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = InputValidation.validString(name);
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = InputValidation.validPhoneNumber(phoneNumber);
	}
	
	/*
	 * Overriding Equals and HashCode method so that unique Contacts will be saved in Set
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Contact))
			return false;
		
		if(this == obj)
			return true;
		
		Contact newContact = (Contact) obj;
		
		if(this.getName().equals(newContact.getName()) && this.getPhoneNumber().equals(newContact.getPhoneNumber())){
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.getName().hashCode() + this.getPhoneNumber().hashCode();
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ").append(this.name).append(" , PhoneNumber: ").append(this.phoneNumber);
		return sb.toString();
	}
	

}
