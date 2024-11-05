package com.PlayGroundAdv.Solar.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.PlayGroundAdv.Solar.entity.libraries.ATS;
import com.PlayGroundAdv.Solar.entity.libraries.Generator;
import com.vladmihalcea.hibernate.type.array.ListArrayType;

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
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class PermitEnergyBatterySystem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAts")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ATS idAts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSecondAts")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ATS idSecondAts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idGenerator")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Generator idGenerator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDcDisconnect")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private DCCombinerDisconnectEntity idDcDisconnect;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAcDisconnect")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ACDisconnect idAcDisconnect;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSecondAcDisconnect")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ACDisconnect idSecondAcDisconnect;

	@OneToMany(mappedBy = "projectId", 
			cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<ProjectBattery> batteries = new ArrayList<>();

	@Column
	private String typeGridTied;

	@Column
	private Boolean atsIncluded;

	@Column
	private Boolean dcDisconnectIncluded;

	@Column
	private Boolean acDisconnectIncluded;

	@Column
	private Boolean generatorIncluded;

	@Column
	private Boolean rsdConnected;

	@Column
	private String generatorStatus;

	@Column
	private String fuelType;

	@Column
	private String fuelDistributionPipeType;

	@Column
	private String fuelDistributionPipeTypeOther;

	@Column
	private String pipeSize;

	@Column
	private String pipeSizeOther;

	@Column
	private Integer qtyAts;

	@Column
	private Integer qtySecondAts;

	@Column
	private Integer qtyAcd;

	@Column
	private Integer qtySecondAcd;

	@Type(type = "list-array")
	@Column(columnDefinition = "text[]")
	private List<String> essSpecificationDetails;

	@Column
	private String essSpecificationComment;

	public void addBattery(Battery b, Integer qty) {
		ProjectBattery bat = new ProjectBattery(this, b, qty);
		batteries.add(bat);
	}

}
