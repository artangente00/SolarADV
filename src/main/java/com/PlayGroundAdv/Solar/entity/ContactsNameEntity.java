package com.PlayGroundAdv.Solar.entity;
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
@Table(name = "ContactsNameEntity")
public class ContactsNameEntity {
	
	@Id
	@SequenceGenerator(name="contactName_sequence", sequenceName = "contactName_sequence", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contactName_sequence")  
	private Long id;
	
	@JoinColumn(name = "ID_USER")
	@ManyToOne
	private AuthentificationEntity idUser;
	
	@Column(name="FIRST_NAME")
	private String firstname;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="PROJECT_CONTACT_PHONE")
	private String projectContactPhone;
	
	@Column(name="PROJECT_CONTACT_EMAIL")
	private String projectContactEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthentificationEntity getIdUser() {
		return idUser;
	}

	public void setIdUser(AuthentificationEntity idUser) {
		this.idUser = idUser;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProjectContactPhone() {
		return projectContactPhone;
	}

	public void setProjectContactPhone(String projectContactPhone) {
		this.projectContactPhone = projectContactPhone;
	}

	public String getProjectContactEmail() {
		return projectContactEmail;
	}

	public void setProjectContactEmail(String projectContactEmail) {
		this.projectContactEmail = projectContactEmail;
	}

	

}
