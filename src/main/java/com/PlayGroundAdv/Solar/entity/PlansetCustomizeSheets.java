package com.PlayGroundAdv.Solar.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.PlayGroundAdv.Solar.entity.sheets.UserCustomizeSheets;
import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "PlansetCustomizeSheets")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class PlansetCustomizeSheets {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name= "PlansetCustomizeSheets_sequence",
			           sequenceName= "PlansetCustomizeSheets_sequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "PlansetCustomizeSheets_sequence")  
	private Long id;
	
	@Column(name= "PDF_NAME")
	private String pdfName;
	
	@Column(name= "UTILITY_COMPANY")
	private String utilityCompany;
	
	@Type(type = "list-array")
    @Column(columnDefinition = "text[]")
	private List<String> ahj;
	
	@OneToMany(mappedBy = "sheetId", cascade = CascadeType.ALL)
	private List<UserCustomizeSheets> userList = new ArrayList<>();
	
	@JoinColumn(name = "USER_SHEET")
	@OneToOne
	private AuthentificationEntity userSheet;
	
	@Type(type = "list-array")
    @Column(columnDefinition = "text[]")
	private List<String> states;
	
	@Column(name = "BASIC_SYSTEM_TYPE")
	private String basicSystemType;
	
	@Column(name = "BASIC_SYSTEM_TYPE_OTHER")
	private String basicSystemTypeOther;
	
	@Column(name = "INVERTER_TECHNOLOGY")
	private String inverterTechnology;
	
	@JoinColumn(name = "BATTERY_IN_SYSTEM")
	@OneToOne
	private Battery batteryInSystem;
	
	@Column(name = "ROOF_TYPE")
	private String roofType;
	
	@Column(name = "ROOF_TYPE_OTHER")
	private String roofTypeOther;
	
	@JoinColumn(name = "ROOFING_MATERIAL_TYPE")
	@OneToOne
	private RoofMaterialType roofingMaterialType;
	
	@JoinColumn(name = "RAIL_RACKING_MODEL")
	@OneToOne
	private RailRacking railRackingModel;
	
	@Column(name = "ELECT_ENG_STRUCT_ENG")
	private String electEngStructEng;
	
	//A.B 10-14-2021 CR-PM-265
	@Column
	private String necCode;
	
	@Column
	private String ifcCode;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;
	
	@Column(name = "IS_DELETED")
	private Boolean isDeleted;
	
	@JoinColumn(name = "ADDED_BY")
	@ManyToOne
	private AuthentificationEntity addedBy;
	
	@JoinColumn(name = "UPDATED_BY")
	@ManyToOne
	private AuthentificationEntity updatedBy;
	
	public AuthentificationEntity getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(AuthentificationEntity updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "DELETED_ON")
	private Date deletedOn;
	
	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;

	public void addUser(AuthentificationEntity u) {
		UserCustomizeSheets bat = new UserCustomizeSheets(this, u);
		userList.add(bat);
	}

}
