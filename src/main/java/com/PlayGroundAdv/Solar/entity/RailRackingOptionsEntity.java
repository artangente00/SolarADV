package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Entity
@Table(name = "Rail_Racking_Options_Entity")
public class RailRackingOptionsEntity {

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "VALUE")
	@Getter @Setter
	private String value;

	@Column(name = "TYPE_OPTION")
	@Getter @Setter
	private String typeOption;

	public RailRackingOptionsEntity(String value, String typeOption) {
		super();
		this.value = value;
		this.typeOption = typeOption;
	}	

}
