package com.PlayGroundAdv.Solar.entity.projects.ess;

import java.util.ArrayList;
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
import javax.persistence.OneToOne;
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
public class ESSConnectors {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity project;
	
	@Column
	private String connectorId;
	
	@Column
	private Integer index;
	
	@Column
	private String sourceID;
	
	@Column
	private String sourcePortID;
	
	@Column
	private String targetID;
	
	@Column
	private String targetPortID;
	
	@OneToMany(targetEntity=ESSSegments.class, mappedBy="connector",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private List<ESSSegments> segments = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private ESSCircuitSpec circuitSpec;
	
	public ESSConnectors(PermitEntity project) {
		super();
		this.project = project;
	}
	
}
