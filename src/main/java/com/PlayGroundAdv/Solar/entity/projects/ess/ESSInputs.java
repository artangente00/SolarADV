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
public class ESSInputs {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "node")
    @OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	private ESSNodes node;
	
	@Column
	private String value;
	
	@Column
	private Integer marginLeft;
	
	@Column
	private Integer marginTop;

	public ESSInputs(ESSNodes node, String value, Integer marginLeft, Integer marginTop) {
		super();
		this.node = node;
		this.value = value;
		this.marginLeft = marginLeft;
		this.marginTop = marginTop;
	}
	

}
