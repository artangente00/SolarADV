package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AccountingsEntity")
public class AccountingsEntity {

	
private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="hibernate_sequenceAccounting1", sequenceName = "hibernate_sequenceAccounting1", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequenceAccounting1")
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permit;
	
	@Column(name="DATE_SEND_INVOICE")
	private String dateSendInvoice;
	
	@Column(name="DATE_SEND_PROJECT")
	private String dateSendProject ;
	
	@Column(name="DATE_SEND_REVISIONS")
	private String dateSendRevisions;
	
	@Column(name="Date_Send_Engineering_Request")
	private String dateSendEngineeringRequest;
	
	@Column(name="Date_Send_Corrections")
	private String dateSendCorrections;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermitEntity getPermit() {
		return permit;
	}

	public void setPermit(PermitEntity permit) {
		this.permit = permit;
	}

	public String getDateSendInvoice() {
		return dateSendInvoice;
	}

	public void setDateSendInvoice(String dateSendInvoice) {
		this.dateSendInvoice = dateSendInvoice;
	}

	public String getDateSendProject() {
		return dateSendProject;
	}

	public void setDateSendProject(String dateSendProject) {
		this.dateSendProject = dateSendProject;
	}

	public String getDateSendRevisions() {
		return dateSendRevisions;
	}

	public void setDateSendRevisions(String dateSendRevisions) {
		this.dateSendRevisions = dateSendRevisions;
	}

	public String getDateSendEngineeringRequest() {
		return dateSendEngineeringRequest;
	}

	public void setDateSendEngineeringRequest(String dateSendEngineeringRequest) {
		this.dateSendEngineeringRequest = dateSendEngineeringRequest;
	}

	public String getDateSendCorrections() {
		return dateSendCorrections;
	}

	public void setDateSendCorrections(String dateSendCorrections) {
		this.dateSendCorrections = dateSendCorrections;
	}

	
	
}
