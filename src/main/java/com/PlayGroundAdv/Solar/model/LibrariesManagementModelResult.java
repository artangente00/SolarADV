package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class LibrariesManagementModelResult {

	private Long id;
	private String type;
	private String manufacturer;
	private String model;
	private String ownerFirstName;
	private String ownerLastName;
	private Date addDate;

	public LibrariesManagementModelResult() {
		super();
	}

	public LibrariesManagementModelResult(Long id, String manufacturer, String model) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
	}

	public LibrariesManagementModelResult(Long id, String manufacturer, String model, Date addDate,
			String ownerFirstName, String ownerLastName) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.addDate = addDate;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
	}

	public LibrariesManagementModelResult(Long id, String manufacturer, String model, Date addDate,
			String ownerFirstName, String ownerLastName, String type) {
		super();
		this.id = id;
		this.type = type;
		this.manufacturer = manufacturer;
		this.model = model;
		this.addDate = addDate;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

}
