package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJHowMany {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
	private Integer qtyOfWeStampPlansets;
	private Integer wetStampSetsOfCalcs;
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJHowMany(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}
}
