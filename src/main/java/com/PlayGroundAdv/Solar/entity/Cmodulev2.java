package com.PlayGroundAdv.Solar.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Cmodulev2")
public class Cmodulev2 {
	
	
	@Id
	@SequenceGenerator(name="hibernate_sequence14", sequenceName = "hibernate_sequence14", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence14")  
	private Long id;
	
	
	@Column(name="MAKE")
	private String make;
	
	@Column(name="MODEL")
	private String model;
	
	@Column(name="BIPV")
	private String bipv;
	
	@Column(name="DATE")
	private String date;
	
	@Column(name="T_NOCT")
	private String tNoct;
	
	@Column(name="A_C")
	private String aC;
	
	@Column(name="N_S")
	private String nS;
	
	@Column(name="I_SC_REF")
	private String iScRef;
	
	@Column(name="V_OC_REF")
	private String vOcRef;
	
	@Column(name="I_MP_REF")
	private String iMpRef;
	
	@Column(name="V_MP_REF")
	private String vMpRef;
	
	@Column(name="MPPT_QTY")
	private String mpptQty;

	@Column(name="OCPD")
	private String ocpd;

	@Column(name="STC")
	private String stc;
	
	@Column(name="STC_ROUNDED")
	private String stcRounded;
	
	

	@Column(name="ALPHA_SC")
	private String alphaSc;
	
	@Column(name="BETA_OC")
	private String betaOc;
	
	@Column(name="A_REF")
	private String aRef;
	
	@Column(name="I_I_REF")
	private String iIRef;
	
	@Column(name="I_O_REF")
	private String iORef;
	
	@Column(name="IAC_MAX")
	private String iacmax;
	
	@Column(name="R_S")
	private String rS;
	
	@Column(name="R_SH_REF")
	private String rShRef;
	
	@Column(name="ADJUST")
	private String adjust;
	
	@Column(name="GAMMA_R")
	private String gammaR;
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name="PTC")
	private String ptc;
	
	@Column(name="TECHNOLOGY")
	private String technology;
	
	@Column(name="LENGTH")
	private String length;
	
	@Column(name="WIDTH")
	private String width;
	
	@Column(name="DEPTH")
	private String depth;
	
	@Column(name="WEIGHT")
	private String weight;
	
	@Column(name="isDeleted")
	private boolean isDeleted;
	
	@Column(name="HAS_SUPER_USER_EDIT")
	private Boolean hasSuperUserEdit;
	
	@Column(name="ADD_Date")
	private Date addDate;
	
	@JoinColumn(name = "ID_OWNER")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@Column(name="HAS_CORRECTION_REQUEST")
	private Boolean hasCorrectionRequest;
	
	@Column(name="ERONEOUS_CONTENT")
	private String eroneousContent;
	
	@Column(name="ERONEOUS_CONTENT_OTHER")
	private String eroneousContentOther;
	
	@Column(name="ERONEOUS_DESCRIPTION")
	private String eroneousDescription;
	
	@Column(name="MANUFACTURER_MAPPING_VALUE")
	private String manufacturerMappingValue;
	
	@Column(name="MODEL_MAPPING_VALUE")
	private String modelMappingValue;
	
	@Column(name="INTEGRATED_MICRO_MODEL")
	private String integratedMicroModel;
	
	@Column(name="INTEGRATED_MICRO_MANUFACTURER")
	private String integratedMicroManufacturer;
	
	@JoinColumn(name = "FIRST_UPDATER")
	@ManyToOne
	private AuthentificationEntity firstUpdater;
	
	@JoinColumn(name = "SECOND_UPDATER")
	@ManyToOne
	private AuthentificationEntity secondUpdater;
	
	@JoinColumn(name = "THIRD_UPDATER")
	@ManyToOne
	private AuthentificationEntity thirdUpdater;
	
	@JoinColumn(name = "VERIFIED_BY")
	@ManyToOne
	private AuthentificationEntity verifiedBy;
	
	@Column(name="IS_VERIFIED")
	private Boolean isVerified;
	
	@Column(name="DATE_VERIFICATION")
	private Date dateVerification;		
	
	@Column(name="MAX_SERIES_FUSE_RATING")
	private String maxSeriesFuseRating;
		
	public String getMaxSeriesFuseRating() {
		return maxSeriesFuseRating;
	}

	public void setMaxSeriesFuseRating(String maxSeriesFuseRating) {
		this.maxSeriesFuseRating = maxSeriesFuseRating;
	}

	public AuthentificationEntity getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(AuthentificationEntity verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Date getDateVerification() {
		return dateVerification;
	}

	public void setDateVerification(Date dateVerification) {
		this.dateVerification = dateVerification;
	}

	public AuthentificationEntity getFirstUpdater() {
		return firstUpdater;
	}

	public void setFirstUpdater(AuthentificationEntity firstUpdater) {
		this.firstUpdater = firstUpdater;
	}

	public AuthentificationEntity getSecondUpdater() {
		return secondUpdater;
	}

	public void setSecondUpdater(AuthentificationEntity secondUpdater) {
		this.secondUpdater = secondUpdater;
	}

	public AuthentificationEntity getThirdUpdater() {
		return thirdUpdater;
	}

	public void setThirdUpdater(AuthentificationEntity thirdUpdater) {
		this.thirdUpdater = thirdUpdater;
	}

	public Boolean getHasCorrectionRequest() {
		return hasCorrectionRequest;
	}

	public void setHasCorrectionRequest(Boolean hasCorrectionRequest) {
		this.hasCorrectionRequest = hasCorrectionRequest;
	}

	public String getEroneousContent() {
		return eroneousContent;
	}

	public void setEroneousContent(String eroneousContent) {
		this.eroneousContent = eroneousContent;
	}

	public String getEroneousContentOther() {
		return eroneousContentOther;
	}

	public void setEroneousContentOther(String eroneousContentOther) {
		this.eroneousContentOther = eroneousContentOther;
	}

	public String getEroneousDescription() {
		return eroneousDescription;
	}

	public void setEroneousDescription(String eroneousDescription) {
		this.eroneousDescription = eroneousDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBipv() {
		return bipv;
	}

	public void setBipv(String bipv) {
		this.bipv = bipv;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String gettNoct() {
		return tNoct;
	}

	public void settNoct(String tNoct) {
		this.tNoct = tNoct;
	}

	public String getaC() {
		return aC;
	}

	public void setaC(String aC) {
		this.aC = aC;
	}

	public String getnS() {
		return nS;
	}

	public void setnS(String nS) {
		this.nS = nS;
	}

	public String getiScRef() {
		return iScRef;
	}

	public void setiScRef(String iScRef) {
		this.iScRef = iScRef;
	}

	public String getvOcRef() {
		return vOcRef;
	}

	public void setvOcRef(String vOcRef) {
		this.vOcRef = vOcRef;
	}

	public String getiMpRef() {
		return iMpRef;
	}

	public void setiMpRef(String iMpRef) {
		this.iMpRef = iMpRef;
	}

	public String getvMpRef() {
		return vMpRef;
	}

	public void setvMpRef(String vMpRef) {
		this.vMpRef = vMpRef;
	}
	
	public String getStc() {
		return stc;
	}

	public void setStc(String stc) {
		this.stc = stc;
	}

	public String getAlphaSc() {
		return alphaSc;
	}

	public void setAlphaSc(String alphaSc) {
		this.alphaSc = alphaSc;
	}

	public String getBetaOc() {
		return betaOc;
	}

	public void setBetaOc(String betaOc) {
		this.betaOc = betaOc;
	}

	public String getaRef() {
		return aRef;
	}

	public void setaRef(String aRef) {
		this.aRef = aRef;
	}

	public String getiIRef() {
		return iIRef;
	}

	public void setiIRef(String iIRef) {
		this.iIRef = iIRef;
	}

	public String getiORef() {
		return iORef;
	}

	public void setiORef(String iORef) {
		this.iORef = iORef;
	}

	public String getrS() {
		return rS;
	}

	public void setrS(String rS) {
		this.rS = rS;
	}

	public String getrShRef() {
		return rShRef;
	}

	public void setrShRef(String rShRef) {
		this.rShRef = rShRef;
	}

	public String getAdjust() {
		return adjust;
	}

	public void setAdjust(String adjust) {
		this.adjust = adjust;
	}

	public String getGammaR() {
		return gammaR;
	}

	public void setGammaR(String gammaR) {
		this.gammaR = gammaR;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPtc() {
		return ptc;
	}

	public void setPtc(String ptc) {
		this.ptc = ptc;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
	public String getStcRounded() {
		return stcRounded;
	}

	public void setStcRounded(String stcRounded) {
		this.stcRounded = stcRounded;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public AuthentificationEntity getAuthentificationEntity() {
		return authentificationEntity;
	}

	public void setAuthentificationEntity(AuthentificationEntity authentificationEntity) {
		this.authentificationEntity = authentificationEntity;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getHasSuperUserEdit() {
		return hasSuperUserEdit;
	}

	public void setHasSuperUserEdit(Boolean hasSuperUserEdit) {
		this.hasSuperUserEdit = hasSuperUserEdit;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getIacmax() {
		return iacmax;
	}

	public void setIacmax(String iacmax) {
		this.iacmax = iacmax;
	}

	public String getMpptQty() {
		return mpptQty;
	}

	public void setMpptQty(String mpptQty) {
		this.mpptQty = mpptQty;
	}

	public String getOcpd() {
		return ocpd;
	}

	public void setOcpd(String ocpd) {
		this.ocpd = ocpd;
	}

	public String getManufacturerMappingValue() {
		return manufacturerMappingValue;
	}

	public void setManufacturerMappingValue(String manufacturerMappingValue) {
		this.manufacturerMappingValue = manufacturerMappingValue;
	}

	public String getModelMappingValue() {
		return modelMappingValue;
	}

	public void setModelMappingValue(String modelMappingValue) {
		this.modelMappingValue = modelMappingValue;
	}
	
	public String getIntegratedMicroModel() {
		return integratedMicroModel;
	}

	public void setIntegratedMicroModel(String integratedMicroModel) {
		this.integratedMicroModel = integratedMicroModel;
	}

	public String getIntegratedMicroManufacturer() {
		return integratedMicroManufacturer;
	}

	public void setIntegratedMicroManufacturer(String integratedMicroManufacturer) {
		this.integratedMicroManufacturer = integratedMicroManufacturer;
	}

	@Override
	public String toString() {
		return "Cmodulev2 [id=" + id + ", make=" + make + ", model=" + model + ", bipv=" + bipv + ", date=" + date
				+ ", tNoct=" + tNoct + ", aC=" + aC + ", nS=" + nS + ", iScRef=" + iScRef + ", vOcRef=" + vOcRef
				+ ", iMpRef=" + iMpRef + ", vMpRef=" + vMpRef + ", mpptQty=" + mpptQty + ", ocpd=" + ocpd + ", stc="
				+ stc + ", stcRounded=" + stcRounded + ", alphaSc=" + alphaSc + ", betaOc=" + betaOc + ", aRef=" + aRef
				+ ", iIRef=" + iIRef + ", iORef=" + iORef + ", iacmax=" + iacmax + ", rS=" + rS + ", rShRef=" + rShRef
				+ ", adjust=" + adjust + ", gammaR=" + gammaR + ", version=" + version + ", ptc=" + ptc
				+ ", technology=" + technology + ", length=" + length + ", width=" + width + ", depth=" + depth
				+ ", weight=" + weight + ", isDeleted=" + isDeleted + ", hasSuperUserEdit=" + hasSuperUserEdit
				+ ", addDate=" + addDate + ", authentificationEntity=" + authentificationEntity
				+ ", hasCorrectionRequest=" + hasCorrectionRequest + ", eroneousContent=" + eroneousContent
				+ ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription=" + eroneousDescription
				+ ", manufacturerMappingValue=" + manufacturerMappingValue + ", modelMappingValue=" + modelMappingValue
				+ "]";
	}
	
	
	
}

