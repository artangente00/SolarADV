package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

public class ModuleFavRequest {

	private Long id;
	private String isFav;
	private String make;
	private String model;

	private String stcRounded;
	private String technology;
	private String version;
	private String vMpRef;

	private String vOcRef;
	private String iScRef;
	private String iMpRef;
	private String length;
	private String width;
	private String depth;

	private String weight;
	private String gammaR;
	private String alphaSc;
	private String betaOc;
	private String date;

	private String ac;
	private String aref;
	private String adjust;
	private String bipv;
	private String iiref;
	private String ioref;
	private String ns;
	private String ptc;
	private String rs;
	private String rshref;
	private String stc;
	private String tnoct;
	private AuthentificationEntity owner;
	private String iacmax;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String mpptQty;
	private String ocpd;
	private String manufacturerMappingValue;
	private String modelMappingValue;
//	S.B 24/04/2020 CR-2197 V12
	private String integratedMicroModel;
	private String integratedMicroManufacturer;

	private AuthentificationEntity firstUpdater;
	private AuthentificationEntity secondUpdater;
	private AuthentificationEntity thirdUpdater;

	private AuthentificationEntity verifiedBy;
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

	public ModuleFavRequest() {
		super();
	}

	public ModuleFavRequest(Long id, String make, String model, String stcRounded, String technology, String version,
			String vMpRef, String vOcRef, String isFav, String iScRef, String iMpRef, String length, String width,
			String depth, String weight, String gammaR, String alphaSc, String betaOc, String date, String ac,
			String aref, String adjust, String bipv, String iiref, String ioref, String ns, String ptc, String rs,
			String rshref, String stc, String tnoct, AuthentificationEntity owner, String iacmax,
			Boolean hasCorrectionRequest, String eroneousContent, String eroneousContentOther,
			String eroneousDescription, String mpptQty, String ocpd, String manufacturerMappingValue,
			String modelMappingValue, String integratedMicroModel, String integratedMicroManufacturer) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.stcRounded = stcRounded;
		this.technology = technology;
		this.version = version;
		this.vMpRef = vMpRef;
		this.vOcRef = vOcRef;
		this.isFav = isFav;
		this.iScRef = iScRef;
		this.iMpRef = iMpRef;
		this.length = length;
		this.width = width;
		this.depth = depth;
		this.weight = weight;
		this.gammaR = gammaR;
		this.alphaSc = alphaSc;
		this.betaOc = betaOc;
		this.date = date;

		this.ac = ac;
		this.aref = aref;
		this.adjust = adjust;
		this.bipv = bipv;
		this.iiref = iiref;
		this.ioref = ioref;
		this.ns = ns;
		this.ptc = ptc;
		this.rs = rs;
		this.rshref = rshref;
		this.stc = stc;
		this.tnoct = tnoct;
		this.owner = owner;
		this.iacmax = iacmax;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.mpptQty = mpptQty;
		this.ocpd = ocpd;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.integratedMicroModel = integratedMicroModel;
		this.integratedMicroManufacturer = integratedMicroManufacturer;
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

	public String getStcRounded() {
		return stcRounded;
	}

	public void setStcRounded(String stcRounded) {
		this.stcRounded = stcRounded;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getIsFav() {
		return isFav;
	}

	public void setIsFav(String isFav) {
		this.isFav = isFav;
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

	public String getGammaR() {
		return gammaR;
	}

	public void setGammaR(String gammaR) {
		this.gammaR = gammaR;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getAref() {
		return aref;
	}

	public void setAref(String aref) {
		this.aref = aref;
	}

	public String getAdjust() {
		return adjust;
	}

	public void setAdjust(String adjust) {
		this.adjust = adjust;
	}

	public String getBipv() {
		return bipv;
	}

	public void setBipv(String bipv) {
		this.bipv = bipv;
	}

	public String getIiref() {
		return iiref;
	}

	public void setIiref(String iiref) {
		this.iiref = iiref;
	}

	public String getIoref() {
		return ioref;
	}

	public void setIoref(String ioref) {
		this.ioref = ioref;
	}

	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}

	public String getPtc() {
		return ptc;
	}

	public void setPtc(String ptc) {
		this.ptc = ptc;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getRshref() {
		return rshref;
	}

	public void setRshref(String rshref) {
		this.rshref = rshref;
	}

	public String getIacmax() {
		return iacmax;
	}

	public void setIacmax(String iacmax) {
		this.iacmax = iacmax;
	}

	public String getStc() {
		return stc;
	}

	public void setStc(String stc) {
		this.stc = stc;
	}

	public String getTnoct() {
		return tnoct;
	}

	public void setTnoct(String tnoct) {
		this.tnoct = tnoct;
	}

	public AuthentificationEntity getOwner() {
		return owner;
	}

	public void setOwner(AuthentificationEntity owner) {
		this.owner = owner;
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
		return "ModuleFavRequest [id=" + id + ", isFav=" + isFav + ", make=" + make + ", model=" + model
				+ ", stcRounded=" + stcRounded + ", technology=" + technology + ", version=" + version + ", vMpRef="
				+ vMpRef + ", vOcRef=" + vOcRef + ", iScRef=" + iScRef + ", iMpRef=" + iMpRef + ", length=" + length
				+ ", width=" + width + ", depth=" + depth + ", weight=" + weight + ", gammaR=" + gammaR + ", alphaSc="
				+ alphaSc + ", betaOc=" + betaOc + ", date=" + date + ", ac=" + ac + ", aref=" + aref + ", adjust="
				+ adjust + ", bipv=" + bipv + ", iiref=" + iiref + ", ioref=" + ioref + ", ns=" + ns + ", ptc=" + ptc
				+ ", rs=" + rs + ", rshref=" + rshref + ", stc=" + stc + ", tnoct=" + tnoct + ", owner=" + owner
				+ ", iacmax=" + iacmax + ", hasCorrectionRequest=" + hasCorrectionRequest + ", eroneousContent="
				+ eroneousContent + ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription="
				+ eroneousDescription + ", mpptQty=" + mpptQty + ", ocpd=" + ocpd + ", manufacturerMappingValue="
				+ manufacturerMappingValue + ", modelMappingValue=" + modelMappingValue + "]";
	}

}

