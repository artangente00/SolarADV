package com.PlayGroundAdv.Solar.model.planset_utils;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlansetUtils {


	private Integer moduleQty = 0;
	// A.B 08-13-2021 String Inverter System
	private Integer maxNumModule = 0;
	private Integer minNumModule = 0;
	private Integer inverterQty = 0;
	
	private Integer dcNumbUngroundCond = 0;
	
	// Number Of Strings on each inverter
	private Integer numberOfStringsOne = 0;
	private Integer numberOfStringsTwo = 0;
	private Integer numberOfStringsThree = 0;
	private Integer numberOfStringsFour = 0;
	private Integer numberOfStringsFive = 0;
	private Integer totalNumberOfStrings = 0;
	private Integer maxNumberOfStrings = 0;
	
	// Number Of Branch Circuit
	private Integer numberOfBranchCircuit = 0;
	
	// Number Of Modules on each inverter
	private Integer numModule1 = 0;
	private Integer numModule2 = 0;
	private Integer numModule3 = 0;
	private Integer numModule4 = 0;
	private Integer numModule5 = 0;
	
	// Number Of Modules on each String for E-003 Mapping
	private List<Integer> stringQty = new ArrayList<>();
	
	// A.B 08-13-2021 Micro-inverter & AC Module System
	private Integer maxBranchMods = 0;
	private Integer acNumbUngroundCond = 0;
	private Integer microNumberOfStrings = 0;
	private Integer modulePerMicroInverter = 0;
	private List<String> stringStringE003 = new ArrayList<>();
	
	// A.B 12-15-2021 Component Qty
	private Integer qtyACCombiner = 0;
	private Integer qtyDCConverter = 0;
	private Integer qtyDCDiscoCombiner = 0;
	private Integer qtyDCCombinerBox = 0;
	private Integer qtyACCombinerLoadCenter = 0;
	private Integer qtyJunctionBox = 0;
	private Integer qtyACDiscoGRND = 0;
	private Integer qtyRapidShutdown = 0;
	private Integer qtyACDCInverter = 0;
	private Integer qtyACDCInverterWACDisconnect = 0;
	private Integer qtySubPanel = 0;
	private Integer qtyACDisconnect = 0;
	private Integer qtyProductionMeter = 0;
	private Integer qtyExistingUtilityMeter = 1;

	private String dcComponent = "";

	@Override
	public String toString() {
		return "PlansetUtils [moduleQty=" + moduleQty + ", maxNumModule=" + maxNumModule + ", minNumModule="
				+ minNumModule + ", inverterQty=" + inverterQty + ", dcNumbUngroundCond=" + dcNumbUngroundCond
				+ ", numberOfBranchCircuit=" + numberOfBranchCircuit + ", maxBranchMods=" + maxBranchMods
				+ ", acNumbUngroundCond=" + acNumbUngroundCond + ", microNumberOfStrings=" + microNumberOfStrings
				+ ", modulePerMicroInverter=" + modulePerMicroInverter + "]";
	}
	
}
