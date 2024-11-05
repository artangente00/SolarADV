package com.PlayGroundAdv.Solar.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "RoofMaterialType")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoofMaterialType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="TYPE_ROOF")
	private String typeRoof;
	
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
	
	@Column(name="MAPPING_VALUE")
	private String mappingValue;
	
	@OneToMany(mappedBy = "roofMaterial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<RackingAllowedRoofMaterial> rackingAllowedRoofMaterial = new HashSet<>();
	
	@OneToMany(mappedBy = "roofMaterialId",  cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<DetailRaickingRoofMaterial> detailRacking = new ArrayList<>();
    
}
