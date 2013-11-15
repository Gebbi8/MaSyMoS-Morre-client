package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 7317019489722179229L;
	
	private String firstName;
	private String lastName;
	private String email;
	private String organization;

	public Person(String firstName, String lastName, String email, String organization) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.organization = organization;
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
