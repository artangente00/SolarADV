package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

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
@Table(name = "TiltLegs")
public class TiltLegs {
	
	private static final long serialVersionUID = 1L;
		
		@Id
		@SequenceGenerator(name="hibernate_sequence15", sequenceName = "hibernate_sequence15", allocationSize = 1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence15")  
		private Long id;
		
		
		@Column(name="MANUFACTURER")
		private String manufacturer;
		
		@Column(name="MODEL")
		private String model;
		
		@Column(name="MAPPEDVALUE")
		private String mappedValue;
		
		@Column(name="WEIGHT")
		private String weight;
		
		@Column(name="UPDATED")
		private String updated;
		
		@Column(name="IS_DELETED")
		private boolean isDeleted;
		
		@Column(name="HAS_SUPER_USER_EDIT")
		private Boolean hasSuperUserEdit;
		
		@Column(name="ADD_Date")
		private Date addDate;
		
		@JoinColumn(name = "ID_OWNER")
		@ManyToOne
		private AuthentificationEntity authentificationEntity;
		
		@Column(name="HAS_CORRECTION_REQUEST")
		private Boolean hasCorrectionRequest;
		
		@Column(name="ERONEOUS_CONTENT")
		private String eroneousContent;
		
		@Column(name="ERONEOUS_CONTENT_OTHER")
		private String eroneousContentOther;
		
		@Column(name="ERONEOUS_DESCRIPTION")
		private String eroneousDescription;
		
		@JoinColumn(name = "FIRST_UPDATER")
		@ManyToOne
		private AuthentificationEntity firstUpdater;
		
		@JoinColumn(name = "SECOND_UPDATER")
		@ManyToOne
		private AuthentificationEntity secondUpdater;
		
		@JoinColumn(name = "THIRD_UPDATER")
		@ManyToOne
		private AuthentificationEntity thirdUpdater;
		
		@JoinColumn(name = "VERIFIED_BY")
		@ManyToOne
		private AuthentificationEntity verifiedBy;
		
		@Column(name="IS_VERIFIED")
		private Boolean isVerified;
		
		@Column(name="DATE_VERIFICATION")
		private Date dateVerification;		
		
		

		public AuthentificationEntity getFirstUpdater() {
			return firstUpdater;
		}

		public void setFirstUpdater(AuthentificationEntity firstUpdater) {
			this.firstUpdater = firstUpdater;
		}

		public AuthentificationEntity getSecondUpdater() {
			return secondUpdater;
		}

		public void setSecondUpdater(AuthentificationEntity secondUpdater) {
			this.secondUpdater = secondUpdater;
		}

		public AuthentificationEntity getThirdUpdater() {
			return thirdUpdater;
		}

		public void setThirdUpdater(AuthentificationEntity thirdUpdater) {
			this.thirdUpdater = thirdUpdater;
		}

		public AuthentificationEntity getVerifiedBy() {
			return verifiedBy;
		}

		public void setVerifiedBy(AuthentificationEntity verifiedBy) {
			this.verifiedBy = verifiedBy;
		}

		public Boolean getIsVerified() {
			return isVerified;
		}

		public void setIsVerified(Boolean isVerified) {
			this.isVerified = isVerified;
		}

		public Date getDateVerification() {
			return dateVerification;
		}

		public void setDateVerification(Date dateVerification) {
			this.dateVerification = dateVerification;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getMappedValue() {
			return mappedValue;
		}

		public void setMappedValue(String mappedValue) {
			this.mappedValue = mappedValue;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getUpdated() {
			return updated;
		}

		public void setUpdated(String updated) {
			this.updated = updated;
		}

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public Boolean getHasSuperUserEdit() {
			return hasSuperUserEdit;
		}

		public void setHasSuperUserEdit(Boolean hasSuperUserEdit) {
			this.hasSuperUserEdit = hasSuperUserEdit;
		}

		public Date getAddDate() {
			return addDate;
		}

		public void setAddDate(Date addDate) {
			this.addDate = addDate;
		}

		public AuthentificationEntity getAuthentificationEntity() {
			return authentificationEntity;
		}

		public void setAuthentificationEntity(AuthentificationEntity authentificationEntity) {
			this.authentificationEntity = authentificationEntity;
		}

		public Boolean getHasCorrectionRequest() {
			return hasCorrectionRequest;
		}

		public void setHasCorrectionRequest(Boolean hasCorrectionRequest) {
			this.hasCorrectionRequest = hasCorrectionRequest;
		}

		public String getEroneousContent() {
			return eroneousContent;
		}

		public void setEroneousContent(String eroneousContent) {
			this.eroneousContent = eroneousContent;
		}

		public String getEroneousContentOther() {
			return eroneousContentOther;
		}

		public void setEroneousContentOther(String eroneousContentOther) {
			this.eroneousContentOther = eroneousContentOther;
		}

		public String getEroneousDescription() {
			return eroneousDescription;
		}

		public void setEroneousDescription(String eroneousDescription) {
			this.eroneousDescription = eroneousDescription;
		}

		@Override
		public String toString() {
			return "TiltLegs [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", mappedValue="
					+ mappedValue + ", weight=" + weight + ", updated=" + updated + ", isDeleted=" + isDeleted
					+ ", hasSuperUserEdit=" + hasSuperUserEdit + ", addDate=" + addDate + ", authentificationEntity="
					+ authentificationEntity + ", hasCorrectionRequest=" + hasCorrectionRequest + ", eroneousContent="
					+ eroneousContent + ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription="
					+ eroneousDescription + "]";
		}
		
		
}
