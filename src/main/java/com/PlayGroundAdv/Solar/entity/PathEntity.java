package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @author Arij
 */
@Entity
@Table(name = "PathEntity")
public class PathEntity {
	
	/*  PathEntity Entity
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence36", sequenceName = "hibernate_sequence36", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence36")  
	private Long id;
	
	
	@Column(name="URL_PATH")
	private String urlPath;
	
	@Column(name="FILE_PATH")
	private String filePath;
	
	@Column(name="IMG_PATH")
	private String imgPath;

	@Column(name="DIR")
	private String dir;
	
	@Column (name="DOWN_DIR")
	private String downDir;
	
	@Column(name="RFI_LINK")
	private String rfiLink;
	
	@Column(name="LOCKED_URL_PATH")
	private String lockedUrlPath;
	
	@Column(name="DB_NAME")
	private String dbName;
	
	@Column(name="DB_PASSWORD")
	private String dbPassword;
	
	@Column(name="DB_PATH")
	private String dbPath;
	
	@Column(name="GOOGLE_DRIVE_FILE_PATH")
	private String googleDriveFilePath;
	
	@Column(name="googleDriveEmail")
	private String googleDriveEmail;
	
	@Column(name="OLD_GOOGLE_DRIVE_FILE_PATH")
	private String oldGoogleDriveFilePath;
	
	public String getOldGoogleDriveFilePath() {
		return oldGoogleDriveFilePath;
	}

	public void setOldGoogleDriveFilePath(String oldGoogleDriveFilePath) {
		this.oldGoogleDriveFilePath = oldGoogleDriveFilePath;
	}

	public String getGoogleDriveEmail() {
		return googleDriveEmail;
	}

	public void setGoogleDriveEmail(String googleDriveEmail) {
		this.googleDriveEmail = googleDriveEmail;
	}

	public String getDbPath() {
		return dbPath;
	}

	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}

	public String getRfiLink() {
		return rfiLink;
	}

	public void setRfiLink(String rfiLink) {
		this.rfiLink = rfiLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getDownDir() {
		return downDir;
	}

	public void setDownDir(String downDir) {
		this.downDir = downDir;
	}

	public String getLockedUrlPath() {
		return lockedUrlPath;
	}

	public void setLockedUrlPath(String lockedUrlPath) {
		this.lockedUrlPath = lockedUrlPath;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getGoogleDriveFilePath() {
		return googleDriveFilePath;
	}

	public void setGoogleDriveFilePath(String googleDriveFilePath) {
		this.googleDriveFilePath = googleDriveFilePath;
	}
	
	
}
