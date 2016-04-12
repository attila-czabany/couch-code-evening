package com.scytl.couchdb.model;

public class User extends BasicDocument {

	private static final long serialVersionUID = 1L;
	
	private String firstName;

	private String lastName;
	
	private int bornIn;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getBornIn() {
		return bornIn;
	}

	public void setBornIn(int bornIn) {
		this.bornIn = bornIn;
	}
	

}
