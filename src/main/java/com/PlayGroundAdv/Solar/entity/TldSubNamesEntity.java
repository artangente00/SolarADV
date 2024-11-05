package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "TldSubNamesEntity")
public class TldSubNamesEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String subName;
	
	private String component;
	
	private String description;
	
	private Date lastUpdate;
	
	private Boolean isDeleted;
	
	@JoinColumn(name = "ADDED_BY")
	@ManyToOne
	private AuthentificationEntity addedBy;
	
	@JoinColumn(name = "UPDATED_BY")
	@ManyToOne
	private AuthentificationEntity updatedBy;
	
	private Date deletedOn;
	
	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;

	
}
