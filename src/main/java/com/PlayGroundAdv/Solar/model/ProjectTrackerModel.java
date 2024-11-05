package com.PlayGroundAdv.Solar.model;

public class ProjectTrackerModel {

	private String name;
	private String owner;
	private String status;
	private String created;
	private String currentPM;
	private String projectEditStarted;
	private String projectEditCompleted;
	private String submit;
	private String submitBy;
	private String submitADVRFI;
	private String submitContRFI;
	private String requestRevision;
	private String reopenProject;
	private String delivered;
	private String drafterDataEditStarted;
	private String drafterDataEditCompleted;
	private String downloadDrafter;
	private String drafter;
	private String advInputsEditStarted;
	private String advInputsEditCompleted;
	private String advTeamMember;
	private String timeLine;
	private String timeLineLessRfi;

	public ProjectTrackerModel() {
		super();
	}

	public ProjectTrackerModel(String name, String owner, String status, String created, String currentPM,
			String projectEditStarted, String projectEditCompleted, String submit, String submitBy, String submitADVRFI,
			String submitContRFI, String requestRevision, String reopenProject, String delivered,
			String drafterDataEditStarted, String drafterDataEditCompleted, String downloadDrafter, String drafter,
			String advInputsEditStarted, String advInputsEditCompleted, String advTeamMember, String timeLine,
			String timeLineLessRfi) {
		super();
		this.name = name;
		this.owner = owner;
		this.status = status;
		this.created = created;
		this.currentPM = currentPM;
		this.projectEditStarted = projectEditStarted;
		this.projectEditCompleted = projectEditCompleted;
		this.submit = submit;
		this.submitBy = submitBy;
		this.submitADVRFI = submitADVRFI;
		this.submitContRFI = submitContRFI;
		this.requestRevision = requestRevision;
		this.reopenProject = reopenProject;
		this.delivered = delivered;
		this.drafterDataEditStarted = drafterDataEditStarted;
		this.drafterDataEditCompleted = drafterDataEditCompleted;
		this.downloadDrafter = downloadDrafter;
		this.drafter = drafter;
		this.advInputsEditStarted = advInputsEditStarted;
		this.advInputsEditCompleted = advInputsEditCompleted;
		this.advTeamMember = advTeamMember;
		this.timeLine = timeLine;
		this.timeLineLessRfi = timeLineLessRfi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getCurrentPM() {
		return currentPM;
	}

	public void setCurrentPM(String currentPM) {
		this.currentPM = currentPM;
	}

	public String getProjectEditStarted() {
		return projectEditStarted;
	}

	public void setProjectEditStarted(String projectEditStarted) {
		this.projectEditStarted = projectEditStarted;
	}

	public String getProjectEditCompleted() {
		return projectEditCompleted;
	}

	public void setProjectEditCompleted(String projectEditCompleted) {
		this.projectEditCompleted = projectEditCompleted;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getSubmitBy() {
		return submitBy;
	}

	public void setSubmitBy(String submitBy) {
		this.submitBy = submitBy;
	}

	public String getSubmitADVRFI() {
		return submitADVRFI;
	}

	public void setSubmitADVRFI(String submitADVRFI) {
		this.submitADVRFI = submitADVRFI;
	}

	public String getSubmitContRFI() {
		return submitContRFI;
	}

	public void setSubmitContRFI(String submitContRFI) {
		this.submitContRFI = submitContRFI;
	}

	public String getRequestRevision() {
		return requestRevision;
	}

	public void setRequestRevision(String requestRevision) {
		this.requestRevision = requestRevision;
	}

	public String getReopenProject() {
		return reopenProject;
	}

	public void setReopenProject(String reopenProject) {
		this.reopenProject = reopenProject;
	}

	public String getDelivered() {
		return delivered;
	}

	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	public String getDrafterDataEditStarted() {
		return drafterDataEditStarted;
	}

	public void setDrafterDataEditStarted(String drafterDataEditStarted) {
		this.drafterDataEditStarted = drafterDataEditStarted;
	}

	public String getDrafterDataEditCompleted() {
		return drafterDataEditCompleted;
	}

	public void setDrafterDataEditCompleted(String drafterDataEditCompleted) {
		this.drafterDataEditCompleted = drafterDataEditCompleted;
	}

	public String getDownloadDrafter() {
		return downloadDrafter;
	}

	public void setDownloadDrafter(String downloadDrafter) {
		this.downloadDrafter = downloadDrafter;
	}

	public String getDrafter() {
		return drafter;
	}

	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}

	public String getAdvInputsEditStarted() {
		return advInputsEditStarted;
	}

	public void setAdvInputsEditStarted(String advInputsEditStarted) {
		this.advInputsEditStarted = advInputsEditStarted;
	}

	public String getAdvInputsEditCompleted() {
		return advInputsEditCompleted;
	}

	public void setAdvInputsEditCompleted(String advInputsEditCompleted) {
		this.advInputsEditCompleted = advInputsEditCompleted;
	}

	public String getAdvTeamMember() {
		return advTeamMember;
	}

	public void setAdvTeamMember(String advTeamMember) {
		this.advTeamMember = advTeamMember;
	}

	public String getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(String timeLine) {
		this.timeLine = timeLine;
	}

	public String getTimeLineLessRfi() {
		return timeLineLessRfi;
	}

	public void setTimeLineLessRfi(String timeLineLessRfi) {
		this.timeLineLessRfi = timeLineLessRfi;
	}

	@Override
	public String toString() {
		return "ProjectTrackerModel [name=" + name + ", owner=" + owner + ", status=" + status + ", created=" + created
				+ ", currentPM=" + currentPM + ", projectEditStarted=" + projectEditStarted + ", projectEditCompleted="
				+ projectEditCompleted + ", submit=" + submit + ", submitBy=" + submitBy + ", submitADVRFI="
				+ submitADVRFI + ", submitContRFI=" + submitContRFI + ", requestRevision=" + requestRevision
				+ ", reopenProject=" + reopenProject + ", delivered=" + delivered + ", drafterDataEditStarted="
				+ drafterDataEditStarted + ", drafterDataEditCompleted=" + drafterDataEditCompleted
				+ ", downloadDrafter=" + downloadDrafter + ", drafter=" + drafter + ", advInputsEditStarted="
				+ advInputsEditStarted + ", advInputsEditCompleted=" + advInputsEditCompleted + ", advTeamMember="
				+ advTeamMember + ", timeLine=" + timeLine + ", timeLineLessRfi=" + timeLineLessRfi + "]";
	}

}
