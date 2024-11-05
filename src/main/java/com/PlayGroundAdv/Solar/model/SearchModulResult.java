package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class SearchModulResult {

	// Module library Column
	private Long id;
	private Boolean isFav;
	private String make;
	private String model;
	private String iScRef;
	private String iMpRef;
	private String vMpRef;
	private String vOcRef;
	private String gammaR;
	private Boolean hasCorrectionRequest;
	private UsersEntityResult owner;

	// Module Spec
	private String bipv;
	private String tNoct;
	private String aC;
	private String nS;
	private String mpptQty;
	private String ocpd;
	private String stc;
	private String stcRounded;
	private String alphaSc;
	private String betaOc;
	private String aRef;
	private String iIRef;
	private String iORef;
	private String iacmax;
	private String rS;
	private String rShRef;
	private String adjust;
	private String version;
	private String ptc;
	private String technology;
	private String length;
	private String width;
	private String depth;
	private String weight;
	private Boolean hasSuperUserEdit;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	
//	S.B 24/04/2020 CR-2197 V12
	private String integratedMicroModel;
	private String integratedMicroManufacturer;
	
	//F.B CR-686
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;
	
	//F.B 04-11-2022 CR-PM-845-MOD-001
	private String maxSeriesFuseRating;		

	public String getMaxSeriesFuseRating() {
		return maxSeriesFuseRating;
	}

	public void setMaxSeriesFuseRating(String maxSeriesFuseRating) {
		this.maxSeriesFuseRating = maxSeriesFuseRating;
	}

	public UsersEntityResult getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(UsersEntityResult verifiedBy) {
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

	public UsersEntityResult getFirstUpdater() {
		return firstUpdater;
	}

	public void setFirstUpdater(UsersEntityResult firstUpdater) {
		this.firstUpdater = firstUpdater;
	}

	public UsersEntityResult getSecondUpdater() {
		return secondUpdater;
	}

	public void setSecondUpdater(UsersEntityResult secondUpdater) {
		this.secondUpdater = secondUpdater;
	}

	public UsersEntityResult getThirdUpdater() {
		return thirdUpdater;
	}

	public void setThirdUpdater(UsersEntityResult thirdUpdater) {
		this.thirdUpdater = thirdUpdater;
	}

	public SearchModulResult() {
		super();
	}

	public SearchModulResult(Long id, Boolean isFav, String make, String model, String iScRef, String iMpRef,
			String vMpRef, String vOcRef, String gammaR, Boolean hasCorrectionRequest, UsersEntityResult owner,
			String bipv, String tNoct, String aC, String nS, String mpptQty, String ocpd, String stc, String stcRounded,
			String alphaSc, String betaOc, String aRef, String iIRef, String iORef, String iacmax, String rS,
			String rShRef, String adjust, String version, String ptc, String technology, String length, String width,
			String depth, String weight, Boolean hasSuperUserEdit, String eroneousContent, String eroneousContentOther,
			String eroneousDescription, String manufacturerMappingValue, String modelMappingValue,
			String integratedMicroModel, String integratedMicroManufacturer, UsersEntityResult firstUpdater,
			UsersEntityResult secondUpdater, UsersEntityResult thirdUpdater, UsersEntityResult verifiedBy,
			Boolean isVerified, Date dateVerification) {
		super();
		this.id = id;
		this.isFav = isFav;
		this.make = make;
		this.model = model;
		this.iScRef = iScRef;
		this.iMpRef = iMpRef;
		this.vMpRef = vMpRef;
		this.vOcRef = vOcRef;
		this.gammaR = gammaR;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.owner = owner;
		this.bipv = bipv;
		this.tNoct = tNoct;
		this.aC = aC;
		this.nS = nS;
		this.mpptQty = mpptQty;
		this.ocpd = ocpd;
		this.stc = stc;
		this.stcRounded = stcRounded;
		this.alphaSc = alphaSc;
		this.betaOc = betaOc;
		this.aRef = aRef;
		this.iIRef = iIRef;
		this.iORef = iORef;
		this.iacmax = iacmax;
		this.rS = rS;
		this.rShRef = rShRef;
		this.adjust = adjust;
		this.version = version;
		this.ptc = ptc;
		this.technology = technology;
		this.length = length;
		this.width = width;
		this.depth = depth;
		this.weight = weight;
		this.hasSuperUserEdit = hasSuperUserEdit;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.integratedMicroModel = integratedMicroModel;
		this.integratedMicroManufacturer = integratedMicroManufacturer;
		this.firstUpdater = firstUpdater;
		this.secondUpdater = secondUpdater;
		this.thirdUpdater = thirdUpdater;
		this.verifiedBy = verifiedBy;
		this.isVerified = isVerified;
		this.dateVerification = dateVerification;
	}
	
	public SearchModulResult(Long id, Boolean isFav, String make, String model, String iScRef, String iMpRef,
			String vMpRef, String vOcRef, String gammaR, Boolean hasCorrectionRequest, UsersEntityResult owner,
			String bipv, String tNoct, String aC, String nS, String mpptQty, String ocpd, String stc, String stcRounded,
			String alphaSc, String betaOc, String aRef, String iIRef, String iORef, String iacmax, String rS,
			String rShRef, String adjust, String version, String ptc, String technology, String length, String width,
			String depth, String weight, Boolean hasSuperUserEdit, String eroneousContent, String eroneousContentOther,
			String eroneousDescription, String manufacturerMappingValue, String modelMappingValue,
			String integratedMicroModel, String integratedMicroManufacturer, UsersEntityResult firstUpdater,
			UsersEntityResult secondUpdater, UsersEntityResult thirdUpdater, UsersEntityResult verifiedBy,
			Boolean isVerified, Date dateVerification, String maxSeriesFuseRating) {
		super();
		this.id = id;
		this.isFav = isFav;
		this.make = make;
		this.model = model;
		this.iScRef = iScRef;
		this.iMpRef = iMpRef;
		this.vMpRef = vMpRef;
		this.vOcRef = vOcRef;
		this.gammaR = gammaR;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.owner = owner;
		this.bipv = bipv;
		this.tNoct = tNoct;
		this.aC = aC;
		this.nS = nS;
		this.mpptQty = mpptQty;
		this.ocpd = ocpd;
		this.stc = stc;
		this.stcRounded = stcRounded;
		this.alphaSc = alphaSc;
		this.betaOc = betaOc;
		this.aRef = aRef;
		this.iIRef = iIRef;
		this.iORef = iORef;
		this.iacmax = iacmax;
		this.rS = rS;
		this.rShRef = rShRef;
		this.adjust = adjust;
		this.version = version;
		this.ptc = ptc;
		this.technology = technology;
		this.length = length;
		this.width = width;
		this.depth = depth;
		this.weight = weight;
		this.hasSuperUserEdit = hasSuperUserEdit;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.integratedMicroModel = integratedMicroModel;
		this.integratedMicroManufacturer = integratedMicroManufacturer;
		this.firstUpdater = firstUpdater;
		this.secondUpdater = secondUpdater;
		this.thirdUpdater = thirdUpdater;
		this.verifiedBy = verifiedBy;
		this.isVerified = isVerified;
		this.dateVerification = dateVerification;
		this.maxSeriesFuseRating = maxSeriesFuseRating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsFav() {
		return isFav;
	}

	public void setIsFav(Boolean isFav) {
		this.isFav = isFav;
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

	public String getiScRef() {
		return iScRef;
	}

	public void setiScRef(String iScRef) {
		this.iScRef = iScRef;
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

	public String getvOcRef() {
		return vOcRef;
	}

	public void setvOcRef(String vOcRef) {
		this.vOcRef = vOcRef;
	}

	public String getGammaR() {
		return gammaR;
	}

	public void setGammaR(String gammaR) {
		this.gammaR = gammaR;
	}

	public Boolean getHasCorrectionRequest() {
		return hasCorrectionRequest;
	}

	public void setHasCorrectionRequest(Boolean hasCorrectionRequest) {
		this.hasCorrectionRequest = hasCorrectionRequest;
	}

	public String getBipv() {
		return bipv;
	}

	public void setBipv(String bipv) {
		this.bipv = bipv;
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

	public String getStc() {
		return stc;
	}

	public void setStc(String stc) {
		this.stc = stc;
	}

	public String getStcRounded() {
		return stcRounded;
	}

	public void setStcRounded(String stcRounded) {
		this.stcRounded = stcRounded;
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

	public String getIacmax() {
		return iacmax;
	}

	public void setIacmax(String iacmax) {
		this.iacmax = iacmax;
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

	public Boolean getHasSuperUserEdit() {
		return hasSuperUserEdit;
	}

	public void setHasSuperUserEdit(Boolean hasSuperUserEdit) {
		this.hasSuperUserEdit = hasSuperUserEdit;
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

	public UsersEntityResult getOwner() {
		return owner;
	}

	public void setOwner(UsersEntityResult owner) {
		this.owner = owner;
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
	
	

}
