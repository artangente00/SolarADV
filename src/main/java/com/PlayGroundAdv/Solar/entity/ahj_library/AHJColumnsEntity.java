package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "AHJ_COLUMNS_ENTITY")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJColumnsEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String columnName;
	private String columnTitle;
	private String headerName;
	private String subheaderName;
	private String columnType;
	private String category;
	private Boolean custom;
	
	@Type(type = "list-array")
    @Column(name = "OPTIONS",columnDefinition = "text[]")
    private List<String> options;
	
	private Boolean hasAttachements;

	public AHJColumnsEntity(String columnTitle, String headerName, String category, String subheaderName) {
		super();
		this.columnTitle = columnTitle;
		this.headerName = headerName;
		this.category = category;
		this.subheaderName = subheaderName;
		this.columnType = "text";
		this.custom = true;
		this.options = null;
		this.hasAttachements = false;
	}
	
}
