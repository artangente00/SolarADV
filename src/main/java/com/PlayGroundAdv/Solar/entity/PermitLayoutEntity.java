package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PermitLayoutEntity")
public class PermitLayoutEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="hibernate_sequence24", sequenceName = "hibernate_sequence24", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence24")
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	
	@Column(name="CONDUIT_RUN")
	private String conduitRun;
	
	@Column
	private Boolean showConduitRoofAsHeight;
	
	@Column(name="CONDUIT_ROOFTER")
	private String conduitRoofter;
	
	@Column(name="SKETCH_NOTE")
	private String sketchNote;
	
	@Column(name="ASK_Again")
	private Boolean askAgain;
	
	@Column(name="uploadCommentsLayout")
	private String uploadCommentsLayout;
	
	@Column(name="uploadCommentsAddInfo")
	private String uploadCommentsAddInfo;
	
	@Column(name="ignoreVents")
	private Boolean ignoreVents;
	
	@Column(name="firesetbacks")
	private Boolean firesetbacks;
	
	@Column(name="firesetbacksNote")
	private String firesetbacksNote;
	
	@Column(name="fireVariance")
	private Boolean fireVariance;
	
	@Column(name="fireVarianceNote")
	private String fireVarianceNote;
	
	@Column(name="modulesEncroaching")
	private Boolean modulesEncroaching;
	
}
