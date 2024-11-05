package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * Author Soumaya
 */
@Entity
@Table(name = "DrafterInformationEntity")
public class DrafterInformationEntity {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence60", sequenceName = "hibernate_sequence60", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence60")  
	private Long id;
	
	
	@Column(name="needForArackingLayD")
	private Boolean needForArackingLayD;
	
	@Column(name="roofFeatCallOut")
	private Boolean roofFeatCallOut;
	
	@Column(name="draftSepSitePlan")
	private Boolean draftSepSitePlan;
	
	@Column(name="showingTransfLocation")
	private Boolean showingTransfLocation;
	
	@Column(name="dimenrequirOnArr")
	private Boolean dimenrequirOnArr;
	
	@JoinColumn(name = "idPermit")
	@OneToOne
	private PermitEntity idPermit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getNeedForArackingLayD() {
			return needForArackingLayD;
	}

	public void setNeedForArackingLayD(Boolean needForArackingLayD) {
		this.needForArackingLayD = needForArackingLayD;
	}

	public Boolean getRoofFeatCallOut() {
		return roofFeatCallOut;
	}

	public void setRoofFeatCallOut(Boolean roofFeatCallOut) {
		this.roofFeatCallOut = roofFeatCallOut;
	}

	public Boolean getDraftSepSitePlan() {
		return draftSepSitePlan;
	}

	public void setDraftSepSitePlan(Boolean draftSepSitePlan) {
		this.draftSepSitePlan = draftSepSitePlan;
	}

	public Boolean getShowingTransfLocation() {
			return showingTransfLocation;
	}

	public void setShowingTransfLocation(Boolean showingTransfLocation) {
		this.showingTransfLocation = showingTransfLocation;
	}

	public Boolean getDimenrequirOnArr() {
		return dimenrequirOnArr;
	}

	public void setDimenrequirOnArr(Boolean dimenrequirOnArr) {
		this.dimenrequirOnArr = dimenrequirOnArr;
	}

	public PermitEntity getIdPermit() {
		return idPermit;
	}

	public void setIdPermit(PermitEntity idPermit) {
		this.idPermit = idPermit;
	}

	@Override
	public String toString() {
		return "DrafterInformationEntity [id=" + id + ", needForArackingLayD=" + needForArackingLayD
				+ ", roofFeatCallOut=" + roofFeatCallOut + ", draftSepSitePlan=" + draftSepSitePlan
				+ ", showingTransfLocation=" + showingTransfLocation + ", dimenrequirOnArr=" + dimenrequirOnArr
				+ ", idPermit=" + idPermit + "]";
	}
	
	


}
