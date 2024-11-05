package com.PlayGroundAdv.Solar.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PermitPlansetUploadEntity")
public class PermitPlansetUploadEntity{
	
	@Id
	@SequenceGenerator(name="hibernate_sequenceplanset", sequenceName = "hibernate_sequenceplanset", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequenceplanset")  
	private Long id;
	
	@Column(name="FILE_1")
	private byte[] file_1;
	@Column(name="FILE_2")
	private byte[] file_2;
	@Column(name="FILE_3")
	private byte[] file_3;
	@Column(name="FILE_4")
	private byte[] file_4;
	@Column(name="FILE_5")
	private byte[] file_5;
	@Column(name="NAME_FILE_1")
	private String nameFile_1;
	@Column(name="NAME_FILE_2")
	private String nameFile_2;
	@Column(name="NAME_FILE_3")
	private String nameFile_3;
	@Column(name="NAME_FILE_4")
	private String nameFile_4;
	@Column(name="NAME_FILE_5")
	private String nameFile_5;
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="uploadPlanset1")
	private String uploadPlanset1;
	
	@Column(name="uploadPlanset2")
	private String uploadPlanset2;
	
	@Column(name="uploadPlanset3")
	private String uploadPlanset3;
	
	@Column(name="uploadPlanset4")
	private String uploadPlanset4;
	
	@Column(name="uploadPlanset5")
	private String uploadPlanset5;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the file_1
	 */
	public byte[] getFile_1() {
		return file_1;
	}
	/**
	 * @param file_1 the file_1 to set
	 */
	public void setFile_1(byte[] file_1) {
		this.file_1 = file_1;
	}
	/**
	 * @return the file_2
	 */
	public byte[] getFile_2() {
		return file_2;
	}
	/**
	 * @param file_2 the file_2 to set
	 */
	public void setFile_2(byte[] file_2) {
		this.file_2 = file_2;
	}
	/**
	 * @return the file_3
	 */
	public byte[] getFile_3() {
		return file_3;
	}
	/**
	 * @param file_3 the file_3 to set
	 */
	public void setFile_3(byte[] file_3) {
		this.file_3 = file_3;
	}
	/**
	 * @return the file_4
	 */
	public byte[] getFile_4() {
		return file_4;
	}
	/**
	 * @param file_4 the file_4 to set
	 */
	public void setFile_4(byte[] file_4) {
		this.file_4 = file_4;
	}
	/**
	 * @return the file_5
	 */
	public byte[] getFile_5() {
		return file_5;
	}
	/**
	 * @param file_5 the file_5 to set
	 */
	public void setFile_5(byte[] file_5) {
		this.file_5 = file_5;
	}
	/**
	 * @return the permitEntity
	 */
	public PermitEntity getPermitEntity() {
		return permitEntity;
	}
	/**
	 * @param permitEntity the permitEntity to set
	 */
	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}
	public String getNameFile_1() {
		return nameFile_1;
	}
	public void setNameFile_1(String nameFile_1) {
		this.nameFile_1 = nameFile_1;
	}
	public String getNameFile_2() {
		return nameFile_2;
	}
	public void setNameFile_2(String nameFile_2) {
		this.nameFile_2 = nameFile_2;
	}
	public String getNameFile_3() {
		return nameFile_3;
	}
	public void setNameFile_3(String nameFile_3) {
		this.nameFile_3 = nameFile_3;
	}
	public String getNameFile_4() {
		return nameFile_4;
	}
	public void setNameFile_4(String nameFile_4) {
		this.nameFile_4 = nameFile_4;
	}
	public String getNameFile_5() {
		return nameFile_5;
	}
	public void setNameFile_5(String nameFile_5) {
		this.nameFile_5 = nameFile_5;
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
		return "PermitPlansetUploadEntity [id=" + id + ", file_1=" + Arrays.toString(file_1) + ", file_2="
				+ Arrays.toString(file_2) + ", file_3=" + Arrays.toString(file_3) + ", file_4="
				+ Arrays.toString(file_4) + ", file_5=" + Arrays.toString(file_5) + ", nameFile_1=" + nameFile_1
				+ ", nameFile_2=" + nameFile_2 + ", nameFile_3=" + nameFile_3 + ", nameFile_4=" + nameFile_4
				+ ", nameFile_5=" + nameFile_5 + ", permitEntity=" + permitEntity + ", uploadPlanset1=" + uploadPlanset1
				+ ", uploadPlanset2=" + uploadPlanset2 + ", uploadPlanset3=" + uploadPlanset3 + ", uploadPlanset4="
				+ uploadPlanset4 + ", uploadPlanset5=" + uploadPlanset5 + "]";
	}
	

	

}
