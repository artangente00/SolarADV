package com.PlayGroundAdv.Solar.model.libraries;

import java.util.ArrayList;
import java.util.Date;

import com.PlayGroundAdv.Solar.model.UsersEntityResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class InverterResult {

	private Long id;
	private Boolean isFav;
	private String make;
	private String model;
	private String vac;
	private String iacmax;
	private Boolean hasCorrectionRequest;
	private UsersEntityResult owner;
	private String paco;
	private String pdco;
	private String vdco;
	private String pso;
	private String c0;
	private String c1;
	private String c2;
	private String c3;
	private String pnt;
	private String vdcmax;
	private String idcmax;
	private String mpptLow;
	private String mpptHigh;
	private String powerRating;
	private String weightedEfficiency;
	private Boolean microInverter;
	private String weight;
	private Boolean integratedDCDisco;
	private Boolean integratedACDisco;
	private String dataSheet;
	private Boolean optimizer;
	private String updated;
	private Boolean isDeleted;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String mpptQty;
	private String ocpd;
	private String wireQty;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private Integer modPerMicro;
	private ArrayList<String> serviceVoltage;
	private Boolean neutralConductor;
	private Boolean integratedRsd;
	//A.B CR-793
	private Float peakOutputPower;
	//F.B CR-686
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;
	

}
