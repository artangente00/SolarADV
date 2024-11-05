package com.PlayGroundAdv.Solar.entity.projects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.users.UserCutomUpload;
import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class ProjectCustomFiles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity project;
	
	@JoinColumn(name = "ID_CUSTOM_UPLOAD")
	@ManyToOne
	private UserCutomUpload customUpload;
	
	@Type(type = "list-array")
    @Column(columnDefinition = "text[]")
	private List<String> files = new ArrayList<>();
	
	@Column(length = 3500)
	private String comment;

	public ProjectCustomFiles(PermitEntity project, UserCutomUpload customUpload) {
		super();
		this.project = project;
		this.customUpload = customUpload;
	}
	
}
