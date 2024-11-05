package com.PlayGroundAdv.Solar.model;

public class PermitPlansetUploadResult {

	String persistFile;
	String existFile;
	String msgFile;

	public PermitPlansetUploadResult() {
		super();
	}

	public PermitPlansetUploadResult(String persistFile, String existFile, String msgFile) {
		super();
		this.persistFile = persistFile;
		this.existFile = existFile;
		this.msgFile = msgFile;
	}

	/**
	 * @return the persistFile
	 */
	public String getPersistFile() {
		return persistFile;
	}

	/**
	 * @param persistFile the persistFile to set
	 */
	public void setPersistFile(String persistFile) {
		this.persistFile = persistFile;
	}

	/**
	 * @return the existFile
	 */
	public String getExistFile() {
		return existFile;
	}

	/**
	 * @param existFile the existFile to set
	 */
	public void setExistFile(String existFile) {
		this.existFile = existFile;
	}

	/**
	 * @return the msgFile
	 */
	public String getMsgFile() {
		return msgFile;
	}

	/**
	 * @param msgFile the msgFile to set
	 */
	public void setMsgFile(String msgFile) {
		this.msgFile = msgFile;
	}

}
