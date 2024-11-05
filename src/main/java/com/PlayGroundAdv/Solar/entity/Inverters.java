package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
 * @author Arij
 */
@Entity
@Getter
@Setter
@Table(name = "Inverters" , indexes = {
   @Index(name = "INVERTER_INDX_0",  columnList="MAKE,MODEL")
   
     
      })

public class Inverters {
	
	private static final long serialVersionUID = 1L;
		
		@Id
		@SequenceGenerator(name="hibernate_sequence15", sequenceName = "hibernate_sequence15", allocationSize = 1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence15")  
		private Long id;
		
		
		@Column(name="MAKE")
		private String make;
		
		@Column(name="MODEL")
		private String model;
		
		@Column(name="VAC")
		private String vac;
		
		@Column(name="PACO")
		private String paco;
		
		@Column(name="PDCO")
		private String pdco;
		
		@Column(name="VDCO")
		private String vdco;
		
		@Column(name="PSO")
		private String pso;
		
		@Column(name="C0")
		private String c0;
		
		@Column(name="C1")
		private String c1;
		
		@Column(name="C2")
		private String c2;
		
		@Column(name="C3")
		private String c3;
		
		@Column(name="PNT")
		private String pnt;
		
		@Column(name="VDCMAX")
		private String vdcmax;
		
		@Column(name="IDCMAX")
		private String idcmax;
		
		@Column(name="MPPT_LOW")
		private String mpptLow;
		
		@Column(name="MPPT_HIGH")
		private String mpptHigh;
		
		@Column(name="POWER_RATING")
		private String powerRating;
		
		@Column(name="WEIGHTED_EFFICIENCY")
		private String weightedEfficiency;
		
		@Column(name="MICRO_INVERTER")
		private Boolean microInverter;
		
		@Column(name="IACMAX")
		private String iacmax;
		
		@Column(name="WEIGHT")
		private String weight;
		
		@Column(name="INTEGRATED_DC_DISCO")
		private Boolean integratedDCDisco;
		
		@Column(name="INTEGRATED_AC_DISCO")
		private Boolean integratedACDisco;
		
		@Column(name="DATA_SHEET")
		private String dataSheet;
		
		@Column(name="UPDATED")
		private String updated;
		
		@Column(name="isDeleted")
		private boolean isDeleted;
		
		//Add column Optimizer CR 523
		@Column(name="OPTIMIZER")
		private Boolean optimizer;
		
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
		
		@Column(name="MPPT_QTY")
		private String mpptQty;
		
		@Column(name="OCPD")
		private String ocpd;
		
		@Column(name="WIRE_QTY")
		private String wireQty; 
		
		@Column(name="MANUFACTURER_MAPPING_VALUE")
		private String manufacturerMappingValue;
		
		@Column(name="MODEL_MAPPING_VALUE")
		private String modelMappingValue;
		
		@Column(name="MODULE_PER_MICRO")
		private Integer modPerMicro;
		
		@Column(name="NEUTRAL_CONDUCTOR")
		private Boolean neutralConductor;
		
		@Column(name="INTEGRATED_RSD")
		private Boolean integratedRsd;
		
		@Column //A.B CR-793
		private Float peakOutputPower;
		
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
		
}
