package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.Date;

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
@Table(name = "AHJ_LOG_ENTITY")
public class AHJLogEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
	@JoinColumn(name = "UPDATED_BY")
	@ManyToOne
	private AuthentificationEntity updatedBy;

	@Column
	private Date lastUpdate;
	@Column
	private String category;
	@Column
	private String cellName;
	@Column
	private String previousValue;
	@Column
	private String newValue;
	@Column
	private Boolean massUpdate;
	
	@JoinColumn(name = "MASS_UPDATE_LOG")
	@ManyToOne
	private MassUpdateLog massUpdateLog;
	
	public AHJLogEntity(AHJChecklistEntity ahj, AuthentificationEntity updatedBy, Date lastUpdate, String category,
			String cellName, String previousValue, String newValue,Boolean massUpdate,MassUpdateLog massUpdateLog) {
		super();
		this.ahj = ahj;
		this.updatedBy = updatedBy;
		this.lastUpdate = lastUpdate;
		this.category = category;
		this.cellName = cellName;
		this.previousValue = previousValue;
		this.newValue = newValue;
		this.massUpdate = massUpdate;
		this.massUpdateLog = massUpdateLog;
	}
	
	

}
