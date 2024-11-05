package com.PlayGroundAdv.Solar.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

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
public class RoofStructureSetting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String state;
	
	@Column
	private String rafterTrussDesign;
	
	@Column
	private String crossSectionSize;
	
	@Column
	private String rafterTrussDesignOther;
	
	@Column
	private String crossSectionSizeOther;
	
	@JoinColumn(name = "USER_ID")
	@ManyToOne
	private AuthentificationEntity userId;

	public RoofStructureSetting(String state, String rafterTrussDesign, String crossSectionSize,
			String rafterTrussDesignOther, String crossSectionSizeOther, AuthentificationEntity userId) {
		super();
		this.state = state;
		this.rafterTrussDesign = rafterTrussDesign;
		this.crossSectionSize = crossSectionSize;
		this.rafterTrussDesignOther = rafterTrussDesignOther;
		this.crossSectionSizeOther = crossSectionSizeOther;
		this.userId = userId;
	}

}
