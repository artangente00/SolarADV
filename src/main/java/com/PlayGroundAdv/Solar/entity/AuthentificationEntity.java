package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.sheets.UserCustomizeSheets;


@Entity
@Table(name = "AuthentificationEntity")
public class AuthentificationEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence1", sequenceName = "hibernate_sequence1", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence1")  
	private Long id;
	
	@Column(name="PASSOWRD")
	private String password;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CONTRACTOR_CODE")
	private String contractorCode;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="ACTIVE")
	private Boolean active;
	
	@Column(name="DELETED")
	private Boolean deleted;
	
	@Column(name="USER_LAST_LOGIN")
	private Date userLastLogin;
	
	
	@Column(name="TEMP_PWD")
	private String tempPwd;
	
	@Column(name="DATE")
	private String date;
	
	@Column(name="SAME_MAILING_ADDRESS")
	private Boolean sameMalingAddress;
	
	@JoinColumn(name = "ID_ROLE")
	@ManyToOne
	private RoleEntity roleEntity;

	@Column
	private Integer mondayId;

	@Column
	private Date addedToMonday;
	
	@OneToMany(mappedBy = "userId",  cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<UserCustomizeSheets> customizeSheets = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContractorCode() {
		return contractorCode;
	}

	public void setContractorCode(String contractorCode) {
		this.contractorCode = contractorCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getTempPwd() {
		return tempPwd;
	}

	public void setTempPwd(String tempPwd) {
		this.tempPwd = tempPwd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Boolean getSameMalingAddress() {
		return sameMalingAddress;
	}

	public void setSameMalingAddress(Boolean sameMalingAddress) {
		this.sameMalingAddress = sameMalingAddress;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public Date getUserLastLogin() {
		return userLastLogin;
	}

	public void setUserLastLogin(Date userLastLogin) {
		this.userLastLogin = userLastLogin;
	}

	public Integer getMondayId() {
		return mondayId;
	}

	public void setMondayId(Integer mondayId) {
		this.mondayId = mondayId;
	}

	public Date getAddedToMonday() {
		return addedToMonday;
	}

	public void setAddedToMonday(Date addedToMonday) {
		this.addedToMonday = addedToMonday;
	}
	
}
