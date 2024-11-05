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
@Table(name = "AHJ_DESIGN_CRITERIA")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJDesignCriteria {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
	private String asceStandard;
	private String windSpeed;
	private String windSpeedCommercial;
	private String snowLoad;
	private String snowLoadListedHigher;
	private String exposureCategory;
	private String ashraeTemp;
	private String climateZone;
	private String elevation;
	private String highTemp04;
	private String highTemp02;
	private String extremeMinimum;
	private String floodZoneCheckRequiredByAhj;
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJDesignCriteria(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}
	
	
}
