package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class OCPDMainPanelModel {
	
	// A.B minOCPDMainPanelOptions
	private Double minOCPDMainPanelOptions;
	
	private Double ocpdCalculation1;
	private Double ocpdCalculation2;
	private Double ocpdCalculation3;
	private Double ocpdCalculation4;
	private Double ocpdCalculation5;
	
	private Double solarInterconnectionUp1;
	private Double solarInterconnectionUp2;
	private Double solarInterconnectionUp3;
	private Double solarInterconnectionUp4;
	private Double solarInterconnectionUp5;
	
	private Double solarInterconnectionDown1;
	private Double solarInterconnectionDown2;
	private Double solarInterconnectionDown3;
	private Double solarInterconnectionDown4;
	private Double solarInterconnectionDown5;
	

}
