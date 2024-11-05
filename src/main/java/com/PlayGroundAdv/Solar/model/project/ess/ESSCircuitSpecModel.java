package com.PlayGroundAdv.Solar.model.project.ess;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ESSCircuitSpecModel {
	
	private String conductorQty;
    private String conductorType;
    private String conductorSize;
    private String conduitType;
    private String conduitSize;
    private Integer conductorQtyOther;
    private String conductorTypeOther;
    private String conductorSizeOther;
    private String conduitTypeOther;
    private String conduitSizeOther;
    private Boolean existing;
    private Boolean conductorNeutral;
    private String circuitEnvironment;
    private Float circuitLength;
    
}
