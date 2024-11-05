package com.PlayGroundAdv.Solar.model;

public class GetPlansetByIdResults {

	private Long idPermit;
	private String planset1;
	private String planset2;
	private String planset3;
	private String planset4;
	private String planset5;
	private String uploadPlanset1;
	private String uploadPlanset2;
	private String uploadPlanset3;
	private String uploadPlanset4;
	private String uploadPlanset5;

	public GetPlansetByIdResults() {
		super();
	}

	public GetPlansetByIdResults(Long idPermit, String planset1, String planset2, String planset3, String planset4,
			String planset5, String uploadPlanset1, String uploadPlanset2, String uploadPlanset3, String uploadPlanset4,
			String uploadPlanset5) {
		super();
		this.idPermit = idPermit;
		this.planset1 = planset1;
		this.planset2 = planset2;
		this.planset3 = planset3;
		this.planset4 = planset4;
		this.planset5 = planset5;
		this.uploadPlanset1 = uploadPlanset1;
		this.uploadPlanset2 = uploadPlanset2;
		this.uploadPlanset2 = uploadPlanset3;
		this.uploadPlanset2 = uploadPlanset4;
		this.uploadPlanset2 = uploadPlanset5;
	}

//	public GetPlansetByIdResults(String file1, String file2, String file3, String file4, String file5) {
//		super();
//		this.planset1 = file1;
//		this.planset2 = file2;
//		this.planset3 = file3;
//		this.planset4 = file4;
//		this.planset5 = file5;
//	}

	public Long getIdPermit() {
		return idPermit;
	}

	public void setIdPermit(Long idPermit) {
		this.idPermit = idPermit;
	}

	public String getPlanset1() {
		return planset1;
	}

	public void setPlanset1(String planset1) {
		this.planset1 = planset1;
	}

	public String getPlanset2() {
		return planset2;
	}

	public void setPlanset2(String planset2) {
		this.planset2 = planset2;
	}

	public String getPlanset3() {
		return planset3;
	}

	public void setPlanset3(String planset3) {
		this.planset3 = planset3;
	}

	public String getPlanset4() {
		return planset4;
	}

	public void setPlanset4(String planset4) {
		this.planset4 = planset4;
	}

	public String getPlanset5() {
		return planset5;
	}

	public void setPlanset5(String planset5) {
		this.planset5 = planset5;
	}

	public String getUploadPlanset1() {
		return uploadPlanset1;
	}

	public void setUploadPlanset1(String uploadPlanset1) {
		this.uploadPlanset1 = uploadPlanset1;
	}

	public String getUploadPlanset2() {
		return uploadPlanset2;
	}

	public void setUploadPlanset2(String uploadPlanset2) {
		this.uploadPlanset2 = uploadPlanset2;
	}

	public String getUploadPlanset3() {
		return uploadPlanset3;
	}

	public void setUploadPlanset3(String uploadPlanset3) {
		this.uploadPlanset3 = uploadPlanset3;
	}

	public String getUploadPlanset4() {
		return uploadPlanset4;
	}

	public void setUploadPlanset4(String uploadPlanset4) {
		this.uploadPlanset4 = uploadPlanset4;
	}

	public String getUploadPlanset5() {
		return uploadPlanset5;
	}

	public void setUploadPlanset5(String uploadPlanset5) {
		this.uploadPlanset5 = uploadPlanset5;
	}

	@Override
	public String toString() {
		return "GetPlansetByIdResults [idPermit=" + idPermit + ", planset1=" + planset1 + ", planset2=" + planset2
				+ ", planset3=" + planset3 + ", planset4=" + planset4 + ", planset5=" + planset5 + ", uploadPlanset1="
				+ uploadPlanset1 + ", uploadPlanset2=" + uploadPlanset2 + ", uploadPlanset3=" + uploadPlanset3
				+ ", uploadPlanset4=" + uploadPlanset4 + ", uploadPlanset5=" + uploadPlanset5 + "]";
	}

}
