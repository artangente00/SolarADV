package com.PlayGroundAdv.Solar.model;

import com.PlayGroundAdv.Solar.entity.ProjectRequestEntity;

public class AdvNoteModel {
	private ProjectRequestEntity newRequest;
	private String firstName;
	private String lastname;
	private Long idPermit;
	private String timeReq;

	public AdvNoteModel() {
		super();
	}

	public AdvNoteModel(ProjectRequestEntity newRequest, String firstName, String lastname, Long idPermit,
			String timeReq) {
		super();
		this.newRequest = newRequest;
		this.firstName = firstName;
		this.lastname = lastname;
		this.idPermit = idPermit;
		this.timeReq = timeReq;
	}

	public ProjectRequestEntity getNewRequest() {
		return newRequest;
	}

	public void setNewRequest(ProjectRequestEntity newRequest) {
		this.newRequest = newRequest;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getIdPermit() {
		return idPermit;
	}

	public void setIdPermit(Long idPermit) {
		this.idPermit = idPermit;
	}

	public String getTimeReq() {
		return timeReq;
	}

	public void setTimeReq(String timeReq) {
		this.timeReq = timeReq;
	}

	@Override
	public String toString() {
		return "AdvNoteModel [newRequest=" + newRequest + ", firstName=" + firstName + ", lastname=" + lastname
				+ ", idPermit=" + idPermit + ", timeReq=" + timeReq + "]";
	}

}
