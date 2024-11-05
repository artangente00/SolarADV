package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @author Soumeya
 */
@Entity
@Table(name = "RoofTypeandAttachmentEntity")
public class RoofTypeandAttachmentEntity {

	/**
	 *  CLASS ENTITY
	 */
	
    private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="roofAttachementSequeunce",
			           sequenceName="roofAttachementSequeunce",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="roofAttachementSequeunce")  
	private Long id;
	
	@Column(name="COMP_SHINGLE")
	private String compShingle;
	
	@Column(name = "FLAT_CONCRETE")
	private String flatConcrete;
	
	@Column(name = "ROMAN_CURVED")
	private String romanCurved;
	
	@Column(name = "STANDING_METAL")
	private String standingMetal;
	
	@Column(name = "BARREL_CURVE")
	private String barrelCurve;
	
	@Column(name = "ROLLED_COMP")
	private String rolledComp;
	
	@Column(name = "CORRUGATED_METAL")
	private String corrugatedMetal;
	
	@Column(name = "TRAPEZOIDAL_METAL")
	private String trapezoidalMetal;
	
	@Column(name = "WOODOR_CEDAR")
	private String woodorCedar;
	
	@Column(name = "CLAY_TILE")
	private String clayTile;
	
	@Column(name = "FIBER_CEMENT")
	private String fiberCement;
	
	@Column(name = "SLATE")
	private String slate;
	
	@Column(name = "MEMBRANE_TPO")
	private String membraneTpo;
	
	@Column(name = "BUILD_UP")
	private String buildUp;
	
	@Column(name = "ROLLED_AS_PHALT")
	private String rolledAsphalt;
	
	@Column(name = "FOAM")
	private String foam;
	
	@Column(name = "LIQUID_APPLIED")
	private String liquidApplied;
	
	@Column(name = "CORRUGATED_POLY_CARB")
	private String corrugatedPolyCarb;
	
	@JoinColumn(name = "iD_USER")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompShingle() {
		return compShingle;
	}

	public void setCompShingle(String compShingle) {
		this.compShingle = compShingle;
	}

	public String getFlatConcrete() {
		return flatConcrete;
	}

	public void setFlatConcrete(String flatConcrete) {
		this.flatConcrete = flatConcrete;
	}

	public String getRomanCurved() {
		return romanCurved;
	}

	public void setRomanCurved(String romanCurved) {
		this.romanCurved = romanCurved;
	}

	public String getStandingMetal() {
		return standingMetal;
	}

	public void setStandingMetal(String standingMetal) {
		this.standingMetal = standingMetal;
	}

	public String getBarrelCurve() {
		return barrelCurve;
	}

	public void setBarrelCurve(String barrelCurve) {
		this.barrelCurve = barrelCurve;
	}

	public String getRolledComp() {
		return rolledComp;
	}

	public void setRolledComp(String rolledComp) {
		this.rolledComp = rolledComp;
	}

	public String getCorrugatedMetal() {
		return corrugatedMetal;
	}

	public void setCorrugatedMetal(String corrugatedMetal) {
		this.corrugatedMetal = corrugatedMetal;
	}

	public String getTrapezoidalMetal() {
		return trapezoidalMetal;
	}

	public void setTrapezoidalMetal(String trapezoidalMetal) {
		this.trapezoidalMetal = trapezoidalMetal;
	}

	public String getWoodorCedar() {
		return woodorCedar;
	}

	public void setWoodorCedar(String woodorCedar) {
		this.woodorCedar = woodorCedar;
	}

	public String getClayTile() {
		return clayTile;
	}

	public void setClayTile(String clayTile) {
		this.clayTile = clayTile;
	}

	public String getFiberCement() {
		return fiberCement;
	}

	public void setFiberCement(String fiberCement) {
		this.fiberCement = fiberCement;
	}

	public String getSlate() {
		return slate;
	}

	public void setSlate(String slate) {
		this.slate = slate;
	}

	public String getMembraneTpo() {
		return membraneTpo;
	}

	public void setMembraneTpo(String membraneTpo) {
		this.membraneTpo = membraneTpo;
	}

	public String getBuildUp() {
		return buildUp;
	}

	public void setBuildUp(String buildUp) {
		this.buildUp = buildUp;
	}

	public String getRolledAsphalt() {
		return rolledAsphalt;
	}

	public void setRolledAsphalt(String rolledAsphalt) {
		this.rolledAsphalt = rolledAsphalt;
	}

	public String getFoam() {
		return foam;
	}

	public void setFoam(String foam) {
		this.foam = foam;
	}

	public String getLiquidApplied() {
		return liquidApplied;
	}

	public void setLiquidApplied(String liquidApplied) {
		this.liquidApplied = liquidApplied;
	}

	public String getCorrugatedPolyCarb() {
		return corrugatedPolyCarb;
	}

	public void setCorrugatedPolyCarb(String corrugatedPolyCarb) {
		this.corrugatedPolyCarb = corrugatedPolyCarb;
	}

	public AuthentificationEntity getAuthentificationEntity() {
		return authentificationEntity;
	}

	public void setAuthentificationEntity(AuthentificationEntity authentificationEntity) {
		this.authentificationEntity = authentificationEntity;
	}

	@Override
	public String toString() {
		return "RoofTypeandAttachmentEntity [id=" + id + ", compShingle=" + compShingle + ", flatConcrete="
				+ flatConcrete + ", romanCurved=" + romanCurved + ", standingMetal=" + standingMetal + ", barrelCurve="
				+ barrelCurve + ", rolledComp=" + rolledComp + ", corrugatedMetal=" + corrugatedMetal
				+ ", trapezoidalMetal=" + trapezoidalMetal + ", woodorCedar=" + woodorCedar + ", clayTile=" + clayTile
				+ ", fiberCement=" + fiberCement + ", slate=" + slate + ", membraneTpo=" + membraneTpo + ", buildUp="
				+ buildUp + ", rolledAsphalt=" + rolledAsphalt + ", foam=" + foam + ", liquidApplied=" + liquidApplied
				+ ", corrugatedPolyCarb=" + corrugatedPolyCarb + ", authentificationEntity=" + authentificationEntity
				+ "]";
	}

	
}
