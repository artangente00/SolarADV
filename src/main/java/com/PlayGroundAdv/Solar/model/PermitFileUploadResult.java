package com.PlayGroundAdv.Solar.model;

public class PermitFileUploadResult {

	String persistFile;
	String existFile;
	String msgFile;

	public PermitFileUploadResult() {
		super();
	}

	public PermitFileUploadResult(String persistFile, String existFile, String msgFile) {
		super();
		this.persistFile = persistFile;
		this.existFile = existFile;
		this.msgFile = msgFile;
	}

	public String getPersistFile() {
		return persistFile;
	}

	public void setPersistFile(String persistFile) {
		this.persistFile = persistFile;
	}

	public String getExistFile() {
		return existFile;
	}

	public void setExistFile(String existFile) {
		this.existFile = existFile;
	}

	public String getMsgFile() {
		return msgFile;
	}

	public void setMsgFile(String msgFile) {
		this.msgFile = msgFile;
	}

}
