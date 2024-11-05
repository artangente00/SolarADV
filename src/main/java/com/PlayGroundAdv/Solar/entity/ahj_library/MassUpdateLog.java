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
@Table(name = "MASS_UPDATE_LOG")
public class MassUpdateLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "UPDATED_BY")
	@ManyToOne
	private AuthentificationEntity updatedBy;
	
	@JoinColumn(name = "UNDO_BY")
	@ManyToOne
	private AuthentificationEntity undoBy;

	@Column
	private Date updateDate;
	@Column
	private Date undoDate;
	@Column
	private String governingCode;
	@Column
	private String state;
	@Column
	private String newValue;
	@Column
	private Boolean massUpdateCancelled;
	
	public MassUpdateLog(AuthentificationEntity updatedBy, Date updateDate,
			String governingCode, String state, String newValue) {
		super();
		
		this.updatedBy = updatedBy;
		this.updateDate = updateDate;
		this.governingCode = governingCode;
		this.state = state;
		this.newValue = newValue;
		this.massUpdateCancelled = false;
	}
	
}
