package com.PlayGroundAdv.Solar.model;

public class UsersEntityResult {

	private Long id;
	private String firstName;
	private String lastName;

	public UsersEntityResult() {
		super();
	}

	public UsersEntityResult(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "UsersEntityResult [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
