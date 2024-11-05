package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

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

/*
 * @author Soumeya
 */
@Entity
@Table(name = "ProjectsTrackerEntity")
public class ProjectsTrackerEntity {
	
private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="ProjectTrackersequence",
			           sequenceName="ProjectTrackersequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ProjectTrackersequence")  
	private Long id;
	
	@Column(name="PROJECT_MANAGER")
	private String projectManager;
	
	@Column(name="PROJECT_EDIT_STARTED")
	private Date projectEditStarted;
	
	@Column(name="PROJECT_EDIT_COMPLETED")
	private Date projectEditCompleted;
	
	@Column(name="SUBMITTED")
	private Date submitted;
	
	@Column(name="SUBMITTED_BY")
	private String submittedBy;
	
	@Column(name="SUBMITTED_ADV_RFI")
	private Date submittedAdvRfi;
	
	@Column(name="SUBMITTED_CONT_RFI")
	private Date submittedContRfi;
	
	@Column(name="REQUEST_REVISION")
	private Date requestRevision;
	
	@Column(name="REOPEN_PROJECT")
	private Date reopenProject;
	
	@Column(name="DELIVRED")
	private Date delivred;
	
	@Column(name="DRAFTER_DATA_EDIT_STARTED")
	private Date drafterDataEditStarted;
	
	@Column(name="SUBMITTED_CONT_COMPLETED")
	private Date drafterDataEditCompleted;
	
	@Column(name="ADV_INPUTS_STARTED")
	private Date advInputsEditStarted;
	
	@Column(name="ADV_INPUTS_COMPLETED")
	private Date advInputsEditCompleted;
	
	@Column(name="ADV_TEAM_MEMBER")
	private String advTeamMember;
	
	@Column(name="DOWNLOAD_DRAFTER")
	private Date downloadDrafter;
	
	@Column(name="DRAFTER")
	private String drafter;
	
	@Column(name="TOTAL_PROJECT_TIME")
	private String totalProjectTime;
	
	@Column(name="PROJECT_TIME_LESS_RFI")
	private String projectTimeLessRfi;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permit;
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getProjectManager() {
		return projectManager;
	}



	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}



	public Date getProjectEditStarted() {
		return projectEditStarted;
	}



	public void setProjectEditStarted(Date projectEditStarted) {
		this.projectEditStarted = projectEditStarted;
	}



	public Date getProjectEditCompleted() {
		return projectEditCompleted;
	}



	public void setProjectEditCompleted(Date projectEditCompleted) {
		this.projectEditCompleted = projectEditCompleted;
	}



	public Date getSubmitted() {
		return submitted;
	}



	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}



	public String getSubmittedBy() {
		return submittedBy;
	}



	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}



	public Date getSubmittedAdvRfi() {
		return submittedAdvRfi;
	}



	public void setSubmittedAdvRfi(Date submittedAdvRfi) {
		this.submittedAdvRfi = submittedAdvRfi;
	}



	public Date getSubmittedContRfi() {
		return submittedContRfi;
	}



	public void setSubmittedContRfi(Date submittedContRfi) {
		this.submittedContRfi = submittedContRfi;
	}



	public Date getRequestRevision() {
		return requestRevision;
	}



	public void setRequestRevision(Date requestRevision) {
		this.requestRevision = requestRevision;
	}



	public Date getReopenProject() {
		return reopenProject;
	}



	public void setReopenProject(Date reopenProject) {
		this.reopenProject = reopenProject;
	}



	public Date getDelivred() {
		return delivred;
	}



	public void setDelivred(Date delivred) {
		this.delivred = delivred;
	}



	public Date getDrafterDataEditStarted() {
		return drafterDataEditStarted;
	}



	public void setDrafterDataEditStarted(Date drafterDataEditStarted) {
		this.drafterDataEditStarted = drafterDataEditStarted;
	}



	public Date getDrafterDataEditCompleted() {
		return drafterDataEditCompleted;
	}



	public void setDrafterDataEditCompleted(Date drafterDataEditCompleted) {
		this.drafterDataEditCompleted = drafterDataEditCompleted;
	}



	public Date getAdvInputsEditStarted() {
		return advInputsEditStarted;
	}



	public void setAdvInputsEditStarted(Date advInputsEditStarted) {
		this.advInputsEditStarted = advInputsEditStarted;
	}



	public Date getAdvInputsEditCompleted() {
		return advInputsEditCompleted;
	}



	public void setAdvInputsEditCompleted(Date advInputsEditCompleted) {
		this.advInputsEditCompleted = advInputsEditCompleted;
	}



	public String getAdvTeamMember() {
		return advTeamMember;
	}



	public void setAdvTeamMember(String advTeamMember) {
		this.advTeamMember = advTeamMember;
	}



	public Date getDownloadDrafter() {
		return downloadDrafter;
	}



	public void setDownloadDrafter(Date downloadDrafter) {
		this.downloadDrafter = downloadDrafter;
	}



	public String getDrafter() {
		return drafter;
	}



	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}



	public String getTotalProjectTime() {
		return totalProjectTime;
	}



	public void setTotalProjectTime(String totalProjectTime) {
		this.totalProjectTime = totalProjectTime;
	}



	public String getProjectTimeLessRfi() {
		return projectTimeLessRfi;
	}



	public void setProjectTimeLessRfi(String projectTimeLessRfi) {
		this.projectTimeLessRfi = projectTimeLessRfi;
	}



	public PermitEntity getPermit() {
		return permit;
	}



	public void setPermit(PermitEntity permit) {
		this.permit = permit;
	}



	@Override
	public String toString() {
		return "ProjectsTrackerEntity [id=" + id + ", projectManager=" + projectManager + ", projectEditStarted="
				+ projectEditStarted + ", projectEditCompleted=" + projectEditCompleted + ", submitted=" + submitted
				+ ", submittedBy=" + submittedBy + ", submittedAdvRfi=" + submittedAdvRfi + ", submittedContRfi="
				+ submittedContRfi + ", requestRevision=" + requestRevision + ", reopenProject=" + reopenProject
				+ ", delivred=" + delivred + ", drafterDataEditStarted=" + drafterDataEditStarted
				+ ", drafterDataEditCompleted=" + drafterDataEditCompleted + ", advInputsEditStarted="
				+ advInputsEditStarted + ", advInputsEditCompleted=" + advInputsEditCompleted + ", advTeamMember="
				+ advTeamMember + ", downloadDrafter=" + downloadDrafter + ", drafter=" + drafter
				+ ", totalProjectTime=" + totalProjectTime + ", projectTimeLessRfi=" + projectTimeLessRfi + ", permit="
				+ permit + "]";
	}
	
	

	
	
	
}
