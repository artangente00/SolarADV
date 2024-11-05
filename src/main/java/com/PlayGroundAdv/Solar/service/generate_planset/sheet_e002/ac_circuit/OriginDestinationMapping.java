package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class OriginDestinationMapping {

	final DefaultRowMapping defaultRowMapping;
	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	
	public OriginDestinationMapping(DefaultRowMapping defaultRowMapping, CheckValueTypesService checkValue,
			TechnicalProblemMsg technicalProblem) {
		super();
		this.defaultRowMapping = defaultRowMapping;
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
	}

	public void orgDestMapping(int i, AcroFields form, String acCircuit,int sheetIndex, int acCircuitLength, Boolean mainPanelUpgrade, Boolean systemString, String circuitCombining, String category) {
		try {
			if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {
				form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-ORGIN", "SUB-PANEL");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {
				form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-ORGIN",
						getACCType(systemString, circuitCombining, category));
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {
				form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-ORGIN", "AC DISCONNECT");

			} else
				form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-ORGIN", acCircuit.split("-")[i - 1]);

			if (i == acCircuitLength) {
				if (Boolean.TRUE.equals(mainPanelUpgrade)) {
					form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-DESTINATION", "NEW SERVICE PANEL");
				} else {
					form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-DESTINATION", "EXISTING SERVICE PANEL");
				}
			} else {
				if (acCircuitLength > 1 && i < acCircuit.split("-").length
						&& checkValue.Equals(acCircuit.split("-")[i], "SUB PANEL")) {
					form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-DESTINATION", "SUB-PANEL");
				} else if (i < acCircuit.split("-").length && acCircuit.split("-")[i] != null
						&& checkValue.Equals(acCircuit.split("-")[i], "AC DISCONNECTTwo")) {
					form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-DESTINATION", "AC DISCONNECT");
				} else if (i < acCircuit.split("-").length && acCircuit.split("-")[i] != null
						&& checkValue.Equals(acCircuit.split("-")[i], "AC COMBINER/LOAD CENTER")) {
					form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-DESTINATION",
							getACCType(systemString, circuitCombining, category));

				} else if (i < acCircuit.split("-").length) {
					form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-DESTINATION", acCircuit.split("-")[i]);
				}
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
	}
//	A.B 04-11-2022 CR-PM-788 
//	A.B 10-17-2022 REV-CR-PM-788-MOD-002
	private String getACCType(Boolean systemString, String circuitCombining, String category) {
		try {
			if(Boolean.TRUE.equals(systemString)) {
				return checkValue.contains(circuitCombining, "A Proposed AC Combiner Panel") ? "SOLAR LOAD CENTER" : "AC COMBINER";
			}else {
				return checkValue.Equals(category, "Solar Load Center") ? "SOLAR LOAD CENTER" : "AC COMBINER";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return "SOLAR LOAD CENTER";
	}
}
