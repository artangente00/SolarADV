package com.PlayGroundAdv.Solar.entity.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.PermitEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class ProjectFiles {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity project;
	
	@Column
	private String nameFile1;
	
	@Column
	private String nameFile2;
	
	@Column
	private String nameFile3;
	
	@Column
	private String nameFile4;
	
	@Column
	private String nameFile5;
	
	@Column
	private String nameFile6;
	
	@Column
	private String nameFile7;
	
	@Column
	private String nameFile8;
	
	@Column
	private String nameFile9;
	
	@Column
	private String nameFile10;
	
	@Column
	private String nameFile11;
	
	@Column
	private String uploadFile1;
	
	@Column
	private String uploadFile2;
	
	@Column
	private String uploadFile3;
	
	@Column
	private String uploadFile4;
	
	@Column
	private String uploadFile5;
	
	@Column
	private String uploadFile6;
	
	@Column
	private String uploadFile7;
	
	@Column
	private String uploadFile8;
	
	@Column
	private String uploadFile9;
	
	@Column
	private String uploadFile10;
	
}
