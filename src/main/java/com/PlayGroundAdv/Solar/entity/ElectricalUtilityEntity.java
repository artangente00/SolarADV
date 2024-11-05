package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
  * @author nader
  */
 @Entity
 @Table(name="ElectricalUtilityEntity",
indexes = {@Index(name = "UTILITY_INDX_0", columnList = "ZIP,UTILITYCOMPANYNAME") })
public class ElectricalUtilityEntity {


	/**
		 *  Permit Engineer Entity
		 */
		private static final long serialVersionUID = 1L;
		
		@Id
		@SequenceGenerator(name="hibernate_sequence12", sequenceName = "hibernate_sequence12", allocationSize = 1)
	    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence12")  
		private long id;
		
		@Column(name="UTILITYCOMPANYNAME")
		private String utilityCompanyName;
		
		@Column(name="PHONE")
		private String phone;
		
		@Column(name="STATE")
		private String state;
	 
		@Column(name="ZIP")
		private String zip;
		
		@Column(name="UTILITY_NUMBER")
		private String utilityNumber;
		
		@Column(name="UTILITY_TYPE")
		private String utilityType;
		
		@Column(name="COUNTY")
		private String county;
		
		@Column(name="IS_DELETED")
		private Boolean isDeleted;
        
		@Column(name="ACD_REQ")
		private String aCDReq;
		
		@Column(name="PVM_REQ")
		private String pVMReq;
		
		@Column(name="ACD_PVM_ORIENTATION")
		private String aCDPVMOrientation;
		
		@Column(name="MAPPING_VALUE")
		private String mappingValue;
		
		@JoinColumn(name = "LAST_UPDATED_BY")
		@ManyToOne
		private AuthentificationEntity lastUpdatedBy;
		
		@Column(name="LAST_UPDATE")
		private Date lastUpdate;		
		 
		 public AuthentificationEntity getLastUpdatedBy() {
			return lastUpdatedBy;
		}

		public void setLastUpdatedBy(AuthentificationEntity lastUpdatedBy) {
			this.lastUpdatedBy = lastUpdatedBy;
		}

		public Date getLastUpdate() {
			return lastUpdate;
		}

		public void setLastUpdate(Date lastUpdate) {
			this.lastUpdate = lastUpdate;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getUtilityCompanyName() {
			return utilityCompanyName;
		}

		public void setUtilityCompanyName(String utilityCompanyName) {
			this.utilityCompanyName = utilityCompanyName;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZip() {
			return zip;
		}

		public void setZip(String zip) {
			this.zip = zip;
		}

		public String getUtilityNumber() {
			return utilityNumber;
		}

		public void setUtilityNumber(String utilityNumber) {
			this.utilityNumber = utilityNumber;
		}

		public String getUtilityType() {
			return utilityType;
		}

		public void setUtilityType(String utilityType) {
			this.utilityType = utilityType;
		}

		public String getCounty() {
			return county;
		}

		public void setCounty(String county) {
			this.county = county;
		}

		public Boolean getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public String getaCDReq() {
			return aCDReq;
		}

		public void setaCDReq(String aCDReq) {
			this.aCDReq = aCDReq;
		}

		public String getpVMReq() {
			return pVMReq;
		}

		public void setpVMReq(String pVMReq) {
			this.pVMReq = pVMReq;
		}

		public String getaCDPVMOrientation() {
			return aCDPVMOrientation;
		}

		public void setaCDPVMOrientation(String aCDPVMOrientation) {
			this.aCDPVMOrientation = aCDPVMOrientation;
		}

		public String getMappingValue() {
			return mappingValue;
		}

		public void setMappingValue(String mappingValue) {
			this.mappingValue = mappingValue;
		}
		
		
		
		
}
