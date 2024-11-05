package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.simple.JSONObject;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
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
@Table(name = "AHJ_CHECKLIST_ENTITY")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJChecklistEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String ahj;
	@Column
	private String county;
	@Column
	private String state;
	@Column
	private Boolean incorporated;
	@Column
	private Date lastUpdate;
	@Column
	private Date addedOn;
	@Column
	private Boolean isDeleted;
	@Column
	private Date deletedon;
	
	@JoinColumn(name = "ADDED_BY")
	@ManyToOne
	private AuthentificationEntity addedBy;
	
	@JoinColumn(name = "LAST_UPDATE_BY")
	@ManyToOne
	private AuthentificationEntity lastUpdateBy;
	
	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;
	
	//Governing Codes
	private String ibc;
	private String irc;
	private String ifc;
	private String nec;
	private String cbc;
	private String cec;
	private String cfc;
	private String crc;
	private String cpc;
	private String cmc;
	private String cgbsc; //A.B 04-11-2022 REV-15-CR-3064
	private String imc;
	private String umc;
	private String ipc;
	private String iecc;
	private String requiredMunicipalSpecificCodeListed;
	private String iBCMustBeRemovedFromGoverningCodes;
	private String ahjRequiresIecc;
	private String bldgEnergyEffStandards;
	private String iebc;
	private String specialbbBuildingCodeNoteMustBeOnE003;
	private String noteWithAllCodesListed;
	
	private String projectName1;
	private String projectlink1;
	private String projectName2;
	private String projectlink2;
	private String projectName3;
	private String projectlink3;
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	
	
	public JSONObject toJSON() {

        JSONObject ahj = new JSONObject();
        ahj.put("ibc", ibc);
        ahj.put("irc", irc);
        ahj.put("ifc", ifc);
        ahj.put("nec", nec);
        ahj.put("cbc", cbc);
        ahj.put("cec", cec);
        ahj.put("cfc", cfc);
    	ahj.put("crc", crc);
        ahj.put("cmc", cmc);
        ahj.put("cgbsc", cgbsc);
        ahj.put("cpc", cpc);
        ahj.put("iecc", iecc);
        ahj.put("imc", imc);
        ahj.put("ipc", ipc);
        ahj.put("umc", umc);
        return ahj;
    }



	public AHJChecklistEntity(String ahj, String county, String state, Boolean incorporated, Date lastUpdate,
			Date addedOn, Boolean isDeleted, AuthentificationEntity addedBy,
			AuthentificationEntity lastUpdateBy, 
			String ibc, String irc, String ifc, 
			String nec, String cbc, String cec, 
			String cfc, String crc, String cpc, 
			String cmc, String cgbsc, String imc, String umc, String ipc,
			String iecc, String requiredMunicipalSpecificCodeListed, String iBCMustBeRemovedFromGoverningCodes,
			String ahjRequiresIecc, String bldgEnergyEffStandards, String iebc,
			String specialbbBuildingCodeNoteMustBeOnE003, String noteWithAllCodesListed, List<String> notes) {
		super();
		this.ahj = ahj;
		this.county = county;
		this.state = state;
		this.incorporated = incorporated;
		this.lastUpdate = lastUpdate;
		this.addedOn = addedOn;
		this.isDeleted = isDeleted;
		this.deletedon = null;
		this.addedBy = addedBy;
		this.lastUpdateBy = lastUpdateBy;
		this.deletedBy = null;
		this.ibc = ibc;
		this.irc = irc;
		this.ifc = ifc;
		this.nec = nec;
		this.cbc = cbc;
		this.cec = cec;
		this.cfc = cfc;
		this.crc = crc;
		this.cpc = cpc;
		this.cmc = cmc;
		this.cgbsc = cgbsc;
		this.imc = imc;
		this.umc = umc;
		this.ipc = ipc;
		this.iecc = iecc;
		this.requiredMunicipalSpecificCodeListed = requiredMunicipalSpecificCodeListed;
		this.iBCMustBeRemovedFromGoverningCodes = iBCMustBeRemovedFromGoverningCodes;
		this.ahjRequiresIecc = ahjRequiresIecc;
		this.bldgEnergyEffStandards = bldgEnergyEffStandards;
		this.iebc = iebc;
		this.specialbbBuildingCodeNoteMustBeOnE003 = specialbbBuildingCodeNoteMustBeOnE003;
		this.noteWithAllCodesListed = noteWithAllCodesListed;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "AHJChecklistEntity [id=" + id + ", ahj=" + ahj + ", county=" + county + ", state=" + state + "]";
	}
	
	
}
