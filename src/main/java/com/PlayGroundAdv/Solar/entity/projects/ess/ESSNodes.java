package com.PlayGroundAdv.Solar.entity.projects.ess;

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
public class ESSNodes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn
	@ManyToOne
	private PermitEntity project;
	
	@Column
	private String nodeId;
	
	@Column
	private String title;
	
	@Column
	private String type;
	
	@Column
	private Integer offsetX;
	
	@Column
	private Integer offsetY;
	
	@Column
	private Boolean removed;

	public ESSNodes(PermitEntity project) {
		super();
		this.project = project;
	}

}
