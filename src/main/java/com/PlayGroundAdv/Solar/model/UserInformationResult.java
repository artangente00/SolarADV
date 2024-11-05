package com.PlayGroundAdv.Solar.model;

public class UserInformationResult {

	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String conutry;
	private String company;
	private String contcode;
	private Boolean active;
	private String address;
	private String secondAddressLine;
	private String city;
	private String state;
	private String postalCode;
	private Long idRole;

	public UserInformationResult() {
		super();
	}

	public UserInformationResult(Long id, String firstName, String lastName, String password, String email,
			String conutry, String company, String contcode, Boolean active, String address, String secondAddressLine,
			String city, String state, String postalCode, Long idRole) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.conutry = conutry;
		this.company = company;
		this.contcode = contcode;
		this.active = active;
		this.address = address;
		this.secondAddressLine = secondAddressLine;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.idRole = idRole;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConutry() {
		return conutry;
	}

	public void setConutry(String conutry) {
		this.conutry = conutry;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContcode() {
		return contcode;
	}

	public void setContcode(String contcode) {
		this.contcode = contcode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSecondAddressLine() {
		return secondAddressLine;
	}

	public void setSecondAddressLine(String secondAddressLine) {
		this.secondAddressLine = secondAddressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

}
