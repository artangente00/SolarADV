package com.PlayGroundAdv.Solar.model;

public class AllPostalCodeModel {

	private Long id;
	private String postalCode;

	public AllPostalCodeModel(Long id, String postalCode) {
		super();
		this.id = id;
		this.postalCode = postalCode;
	}

	public AllPostalCodeModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
