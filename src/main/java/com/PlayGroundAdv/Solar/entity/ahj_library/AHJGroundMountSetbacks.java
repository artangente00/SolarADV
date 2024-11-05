package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
@Table(name = "AHJ_GROUND_MOUNT_SET_BACKS")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJGroundMountSetbacks {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
	private String bedroomWindowEgressReq;
	private String groundCarportCallOutSetbacks;
	private String groundMtShowDistanceToNearest;
	private String callOutDistanceFromExistingStructures;
	private String callOutArraySolarRoofSectionDimensionsOnLayout;
	private String groundMountStructSetbacks;
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJGroundMountSetbacks(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}
	
	
}
