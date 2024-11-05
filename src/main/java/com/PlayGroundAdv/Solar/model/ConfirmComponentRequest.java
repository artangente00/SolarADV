package com.PlayGroundAdv.Solar.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmComponentRequest {
	private Long id;
	private String type;
	private String manufacturer;
	private String make;
	private String model;

	private String phase;
	private String isc;
	private String dateModif;
	private String formatedDateModif;
	private String ownerFirstName;

	private String ownerLastName;
	private Date addDate;
	private List<String> typeOfSystem;
	private String weight;
	private String ratedOutputIsc;

	private String maxInputVoltage;
	private String maxSeriesFuseRating;
	private String pvModulePower;
	private String minString;
	private String maxString;

	private String maxPowerString;
	private String maxOutputVoltage;
	private String nemaRating;
	private String maxInput;
	private String maxContiOutputCurrent;

	private String maxOutputCurrent;
	private String typeDc;
	private String dropdownOption;
	private String rsd;
	private String disconnectDeviceType;
	private String combinerDeviceType;
	private String numberOfSpaces;

	private String otherNumberOfSpaces;

	private String ratedOperationalVoltage;
	private String ratedCurrent;
	private String numberOfPoles;
	private String qtyOfFuse;
	private String roofType;

	private String numberOfRoof;
	private String utilizeS100;
	private String ocpd;
	private String integrated;
	private String vac;

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

	private String microInverter;
	private String iacmax;
	private String integratedDCDisco;
	private String integratedACDisco;
	private String bipv;

	private String tNoct;
	private String aC;
	private String nS;
	private String iScRef;
	private String vOcRef;

	private String iMpRef;
	private String vMpRef;
	private String stc;
	private String stcRounded;
	private String alphaSc;

	private String betaOc;
	private String aRef;
	private String iIRef;
	private String iORef;
	private String rS;

	private String rShRef;
	private String adjust;
	private String gammaR;
	private String version;
	private String ptc;

	private String technology;
	private String length;
	private String width;
	private String depth;
	private String optimizer;
	private List<String> mountType;
	

}
