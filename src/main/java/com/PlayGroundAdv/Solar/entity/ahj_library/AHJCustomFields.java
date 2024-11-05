package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class AHJCustomFields {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
	@JoinColumn(name = "COLUMNS")
	@ManyToOne
	private AHJColumnsEntity columns;
	
	private String text;

	public AHJCustomFields(AHJChecklistEntity ahj, AHJColumnsEntity columns, String text) {
		super();
		this.ahj = ahj;
		this.columns = columns;
		this.text = text;
	}
	
}
