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


@Entity
@Table(name = "PermitToolsEntity")
public class PermitToolsEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="hibernate_sequence18", sequenceName = "hibernate_sequence18", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence18")
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="MSP_LEFT")
	private String mspleft;
	
	@Column(name="MSP_TOP")
	private String msptop;
	
	@Column(name="JBOX_LEFT")
	private String jboxleft;
	
	@Column(name="JBOX_TOP")
	private String jboxtop;
	
	@Column(name="COMBINER_LEFT")
	private String combinerleft;
	
	@Column(name="COMBINER_TOP")
	private String combinertop;
	
	@Column(name="DCDISCO_LEFT")
	private String dcdiscoleft;
	
	@Column(name="DCDISCO_TOP")
	private String dcdiscotop;
	
	@Column(name="INV_LEFT")
	private String invleft;
	
	@Column(name="INV_TOP")
	private String invtop;
	
	@Column(name="ACDISCO_LEFT")
	private String acdiscoleft;
	
	@Column(name="ACDISCO_TOP")
	private String acdiscotop;
	
	@Column(name="OTHER_LEFT")
	private String otherleft;
	
	@Column(name="OTHER_TOP")
	private String othertop;
	
	@Column(name="MODULE_LEFT")
	private String moduleleft;
	
	@Column(name="MODULE_TOP")
	private String moduletop;
	
	@Column(name="MSP_TWO_LEFT")
	private String mspLeftTwo;
	
	@Column(name="MSP_TWO_TOP")
	private String mspTwoTop;
	
	@Column(name="JBOX_TWO_LEFT")
	private String jboxTwoLeft;
	
	@Column(name="JBOX_TWO_TOP")
	private String jboxTwoTop;
	
	@Column(name="COMBINER_TWO_LEFT")
	private String combinerTwoLeft;
	
	@Column(name="COMBINER_TWO_TOP")
	private String combinerTwoTop;
	
	@Column(name="DCDICO_TWO_LEFT")
	private String dcdiscoTwoLeft;
	
	@Column(name="DCDICO_TWO_TOP")
	private String dcdiscoTwoTop;
	
	@Column(name="INV_TWO_LEFT")
	private String invTwoLeft;
	
	@Column(name="INV_TWO_TOP")
	private String invTwoTop;
	
	@Column(name="ACDICO_TWO_LEFT")
	private String acdiscoTwoLeft;		
	
	@Column(name="ACDICO_TWO_TOP")
	private String acdiscoTwoTop;
	
	@Column(name="OTHER_TWO_LEFT")
	private String otherTwoLeft;
	
	@Column(name="OTHER_TWO_TOP")
	private String otherTwoTop;
	
	@Column(name="MODULE_TWO_LEFT")
	private String moduleTwoLeft;
	
	@Column(name="MODULE_TWO_TOP")
	private String moduleTwoTop;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the permitEntity
	 */
	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	/**
	 * @return the mspleft
	 */
	public String getMspleft() {
		return mspleft;
	}

	/**
	 * @return the msptop
	 */
	public String getMsptop() {
		return msptop;
	}

	/**
	 * @return the jboxleft
	 */
	public String getJboxleft() {
		return jboxleft;
	}

	/**
	 * @return the jboxtop
	 */
	public String getJboxtop() {
		return jboxtop;
	}

	/**
	 * @return the combinerleft
	 */
	public String getCombinerleft() {
		return combinerleft;
	}

	/**
	 * @return the combinertop
	 */
	public String getCombinertop() {
		return combinertop;
	}

	/**
	 * @return the dcdiscoleft
	 */
	public String getDcdiscoleft() {
		return dcdiscoleft;
	}

	/**
	 * @return the dcdiscotop
	 */
	public String getDcdiscotop() {
		return dcdiscotop;
	}

	/**
	 * @return the invleft
	 */
	public String getInvleft() {
		return invleft;
	}

	/**
	 * @return the invtop
	 */
	public String getInvtop() {
		return invtop;
	}

	/**
	 * @return the acdiscoleft
	 */
	public String getAcdiscoleft() {
		return acdiscoleft;
	}

	/**
	 * @return the acdiscotop
	 */
	public String getAcdiscotop() {
		return acdiscotop;
	}

	/**
	 * @return the otherleft
	 */
	public String getOtherleft() {
		return otherleft;
	}

	/**
	 * @return the othertop
	 */
	public String getOthertop() {
		return othertop;
	}

	/**
	 * @return the moduleleft
	 */
	public String getModuleleft() {
		return moduleleft;
	}

	/**
	 * @return the moduletop
	 */
	public String getModuletop() {
		return moduletop;
	}

	/**
	 * @return the mspLeftTwo
	 */
	public String getMspLeftTwo() {
		return mspLeftTwo;
	}

	/**
	 * @return the mspTwoTop
	 */
	public String getMspTwoTop() {
		return mspTwoTop;
	}

	/**
	 * @return the jboxTwoLeft
	 */
	public String getJboxTwoLeft() {
		return jboxTwoLeft;
	}

	/**
	 * @return the jboxTwoTop
	 */
	public String getJboxTwoTop() {
		return jboxTwoTop;
	}

	/**
	 * @return the combinerTwoLeft
	 */
	public String getCombinerTwoLeft() {
		return combinerTwoLeft;
	}

	/**
	 * @return the combinerTwoTop
	 */
	public String getCombinerTwoTop() {
		return combinerTwoTop;
	}

	/**
	 * @return the dcdiscoTwoLeft
	 */
	public String getDcdiscoTwoLeft() {
		return dcdiscoTwoLeft;
	}

	/**
	 * @return the dcdiscoTwoTop
	 */
	public String getDcdiscoTwoTop() {
		return dcdiscoTwoTop;
	}

	/**
	 * @return the invTwoLeft
	 */
	public String getInvTwoLeft() {
		return invTwoLeft;
	}

	/**
	 * @return the invTwoTop
	 */
	public String getInvTwoTop() {
		return invTwoTop;
	}

	/**
	 * @return the acdiscoTwoLeft
	 */
	public String getAcdiscoTwoLeft() {
		return acdiscoTwoLeft;
	}

	/**
	 * @return the acdiscoTwoTop
	 */
	public String getAcdiscoTwoTop() {
		return acdiscoTwoTop;
	}

	/**
	 * @return the otherTwoLeft
	 */
	public String getOtherTwoLeft() {
		return otherTwoLeft;
	}

	/**
	 * @return the otherTwoTop
	 */
	public String getOtherTwoTop() {
		return otherTwoTop;
	}

	/**
	 * @return the moduleTwoLeft
	 */
	public String getModuleTwoLeft() {
		return moduleTwoLeft;
	}

	/**
	 * @return the moduleTwoTop
	 */
	public String getModuleTwoTop() {
		return moduleTwoTop;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param permitEntity the permitEntity to set
	 */
	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	/**
	 * @param mspleft the mspleft to set
	 */
	public void setMspleft(String mspleft) {
		this.mspleft = mspleft;
	}

	/**
	 * @param msptop the msptop to set
	 */
	public void setMsptop(String msptop) {
		this.msptop = msptop;
	}

	/**
	 * @param jboxleft the jboxleft to set
	 */
	public void setJboxleft(String jboxleft) {
		this.jboxleft = jboxleft;
	}

	/**
	 * @param jboxtop the jboxtop to set
	 */
	public void setJboxtop(String jboxtop) {
		this.jboxtop = jboxtop;
	}

	/**
	 * @param combinerleft the combinerleft to set
	 */
	public void setCombinerleft(String combinerleft) {
		this.combinerleft = combinerleft;
	}

	/**
	 * @param combinertop the combinertop to set
	 */
	public void setCombinertop(String combinertop) {
		this.combinertop = combinertop;
	}

	/**
	 * @param dcdiscoleft the dcdiscoleft to set
	 */
	public void setDcdiscoleft(String dcdiscoleft) {
		this.dcdiscoleft = dcdiscoleft;
	}

	/**
	 * @param dcdiscotop the dcdiscotop to set
	 */
	public void setDcdiscotop(String dcdiscotop) {
		this.dcdiscotop = dcdiscotop;
	}

	/**
	 * @param invleft the invleft to set
	 */
	public void setInvleft(String invleft) {
		this.invleft = invleft;
	}

	/**
	 * @param invtop the invtop to set
	 */
	public void setInvtop(String invtop) {
		this.invtop = invtop;
	}

	/**
	 * @param acdiscoleft the acdiscoleft to set
	 */
	public void setAcdiscoleft(String acdiscoleft) {
		this.acdiscoleft = acdiscoleft;
	}

	/**
	 * @param acdiscotop the acdiscotop to set
	 */
	public void setAcdiscotop(String acdiscotop) {
		this.acdiscotop = acdiscotop;
	}

	/**
	 * @param otherleft the otherleft to set
	 */
	public void setOtherleft(String otherleft) {
		this.otherleft = otherleft;
	}

	/**
	 * @param othertop the othertop to set
	 */
	public void setOthertop(String othertop) {
		this.othertop = othertop;
	}

	/**
	 * @param moduleleft the moduleleft to set
	 */
	public void setModuleleft(String moduleleft) {
		this.moduleleft = moduleleft;
	}

	/**
	 * @param moduletop the moduletop to set
	 */
	public void setModuletop(String moduletop) {
		this.moduletop = moduletop;
	}

	/**
	 * @param mspLeftTwo the mspLeftTwo to set
	 */
	public void setMspLeftTwo(String mspLeftTwo) {
		this.mspLeftTwo = mspLeftTwo;
	}

	/**
	 * @param mspTwoTop the mspTwoTop to set
	 */
	public void setMspTwoTop(String mspTwoTop) {
		this.mspTwoTop = mspTwoTop;
	}

	/**
	 * @param jboxTwoLeft the jboxTwoLeft to set
	 */
	public void setJboxTwoLeft(String jboxTwoLeft) {
		this.jboxTwoLeft = jboxTwoLeft;
	}

	/**
	 * @param jboxTwoTop the jboxTwoTop to set
	 */
	public void setJboxTwoTop(String jboxTwoTop) {
		this.jboxTwoTop = jboxTwoTop;
	}

	/**
	 * @param combinerTwoLeft the combinerTwoLeft to set
	 */
	public void setCombinerTwoLeft(String combinerTwoLeft) {
		this.combinerTwoLeft = combinerTwoLeft;
	}

	/**
	 * @param combinerTwoTop the combinerTwoTop to set
	 */
	public void setCombinerTwoTop(String combinerTwoTop) {
		this.combinerTwoTop = combinerTwoTop;
	}

	/**
	 * @param dcdiscoTwoLeft the dcdiscoTwoLeft to set
	 */
	public void setDcdiscoTwoLeft(String dcdiscoTwoLeft) {
		this.dcdiscoTwoLeft = dcdiscoTwoLeft;
	}

	/**
	 * @param dcdiscoTwoTop the dcdiscoTwoTop to set
	 */
	public void setDcdiscoTwoTop(String dcdiscoTwoTop) {
		this.dcdiscoTwoTop = dcdiscoTwoTop;
	}

	/**
	 * @param invTwoLeft the invTwoLeft to set
	 */
	public void setInvTwoLeft(String invTwoLeft) {
		this.invTwoLeft = invTwoLeft;
	}

	/**
	 * @param invTwoTop the invTwoTop to set
	 */
	public void setInvTwoTop(String invTwoTop) {
		this.invTwoTop = invTwoTop;
	}

	/**
	 * @param acdiscoTwoLeft the acdiscoTwoLeft to set
	 */
	public void setAcdiscoTwoLeft(String acdiscoTwoLeft) {
		this.acdiscoTwoLeft = acdiscoTwoLeft;
	}

	/**
	 * @param acdiscoTwoTop the acdiscoTwoTop to set
	 */
	public void setAcdiscoTwoTop(String acdiscoTwoTop) {
		this.acdiscoTwoTop = acdiscoTwoTop;
	}

	/**
	 * @param otherTwoLeft the otherTwoLeft to set
	 */
	public void setOtherTwoLeft(String otherTwoLeft) {
		this.otherTwoLeft = otherTwoLeft;
	}

	/**
	 * @param otherTwoTop the otherTwoTop to set
	 */
	public void setOtherTwoTop(String otherTwoTop) {
		this.otherTwoTop = otherTwoTop;
	}

	/**
	 * @param moduleTwoLeft the moduleTwoLeft to set
	 */
	public void setModuleTwoLeft(String moduleTwoLeft) {
		this.moduleTwoLeft = moduleTwoLeft;
	}

	/**
	 * @param moduleTwoTop the moduleTwoTop to set
	 */
	public void setModuleTwoTop(String moduleTwoTop) {
		this.moduleTwoTop = moduleTwoTop;
	}
	
	
	
	
	
}
