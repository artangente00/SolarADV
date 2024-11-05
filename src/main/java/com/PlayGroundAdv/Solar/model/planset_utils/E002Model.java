package com.PlayGroundAdv.Solar.model.planset_utils;

import java.util.ArrayList;
import java.util.List;

import com.PlayGroundAdv.Solar.entity.NEC_310_16_B_16;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class E002Model {

	Integer adder;
	Integer calculatedNumberOfConductor;
	Integer calculatedACNumberOfConductor;
	String conduitFillDerating;
	String correctedAmpacity;
	String correctedACAmpacity;
	String requiredACConductorAmpacity;
	String tradeSizeRepeate;
	String requiredConductorAmpacity;
	String dcampacityCorrectionB3a;
	String conductorSize;
	String ampacityCorrection;
	String lastInvirement;
	String tempAdder;
	String tempDerating;
	Double nec31016Column90;
	Double requiredAmpacity;//requiredACConductorAmpacity
	Boolean incorrectACTradeSizeLogic;
	Boolean operatingTemperatureHigh;
	Boolean incorrectTradeSizeLogic;
	Boolean dcCircuitUpdated;
	List<String> incorrectTradeSize;
	List<String> incorrectACTradeSize;
	NEC_310_16_B_16 nEC310;
	List<VoltageDropTable> voltageDropTable;
	public E002Model() {
		super();
		this.adder = 0;
		this.calculatedNumberOfConductor = 1;
		this.calculatedACNumberOfConductor = 1;
		this.conduitFillDerating = "0";
		this.correctedAmpacity = "";
		this.correctedACAmpacity = "";
		this.requiredACConductorAmpacity = "";
		this.tradeSizeRepeate = "";
		this.requiredConductorAmpacity = "";
		this.dcampacityCorrectionB3a = "1.0";
		this.conductorSize = "";
		this.ampacityCorrection = "1";
		this.lastInvirement = "";
		this.tempAdder = "";
		this.tempDerating = "";
		this.nec31016Column90 = 0d;
		this.requiredAmpacity = 0d;
		this.incorrectACTradeSizeLogic = false;
		this.operatingTemperatureHigh = false;
		this.incorrectTradeSizeLogic = false;
		this.dcCircuitUpdated = false;
		this.incorrectTradeSize = new ArrayList<>();
		this.incorrectACTradeSize = new ArrayList<>();
		this.nEC310 = new NEC_310_16_B_16();
		this.voltageDropTable = new ArrayList<>();
	}
	
	
	
}
