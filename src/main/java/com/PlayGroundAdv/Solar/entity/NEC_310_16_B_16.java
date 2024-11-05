package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @author Arij
 */
@Entity
@Table(name = "NEC_310_16_B_16")
public class NEC_310_16_B_16 {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence25", sequenceName = "hibernate_sequence25", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence25")  
	private Long id;
	
	@Column(name="TRADE_SZE")
	private String tradeSze;
	
	@Column(name="SEVENTY_FIVE_INSULATION")
	private Integer seventyFiveInsulation;
	
	@Column(name="NINETY_INSULATION")
	private Integer ninetyInsulation;
	
	@Column(name="NUMBER_OF_CONDUCTORS")
	private Integer numberOfConductors;
	
	
	@Column(name="NEC_D_F")
	private String necD;

	public String getNecD() {
		return necD;
	}

	public void setNecD(String necD) {
		this.necD = necD;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeSze() {
		return tradeSze;
	}

	public void setTradeSze(String tradeSze) {
		this.tradeSze = tradeSze;
	}

	public Integer getSeventyFiveInsulation() {
		return seventyFiveInsulation;
	}

	public void setSeventyFiveInsulation(Integer seventyFiveInsulation) {
		this.seventyFiveInsulation = seventyFiveInsulation;
	}

	public Integer getNinetyInsulation() {
		return ninetyInsulation;
	}

	public void setNinetyInsulation(Integer ninetyInsulation) {
		this.ninetyInsulation = ninetyInsulation;
	}

	public Integer getNumberOfConductors() {
		return numberOfConductors;
	}

	public void setNumberOfConductors(Integer numberOfConductors) {
		this.numberOfConductors = numberOfConductors;
	}

	@Override
	public String toString() {
		return "NEC_310_16_B_16 [id=" + id + ", tradeSze=" + tradeSze + ", seventyFiveInsulation="
				+ seventyFiveInsulation + ", ninetyInsulation=" + ninetyInsulation + ", numberOfConductors="
				+ numberOfConductors + ", necD=" + necD + "]";
	}
	
	
 
}
