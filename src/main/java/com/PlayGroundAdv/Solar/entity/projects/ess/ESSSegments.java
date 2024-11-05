package com.PlayGroundAdv.Solar.entity.projects.ess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class ESSSegments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "connector")
    @OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	private ESSConnectors connector;
	
	@Column
	private String direction;
	
	@Column
	private Float length;

	public ESSSegments(ESSConnectors connector, String direction, Float length) {
		super();
		this.connector = connector;
		this.direction = direction;
		this.length = length;
	}
	
	
}
