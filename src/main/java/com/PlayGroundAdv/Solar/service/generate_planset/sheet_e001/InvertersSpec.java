package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetInverterData;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class InvertersSpec {

	final CheckValueTypesService checkValue;
	final GetInverterData getInverterData;
	final TechnicalProblemMsg technicalProblemMsg;
	
	public InvertersSpec(CheckValueTypesService checkValue, GetInverterData getInverterData, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.getInverterData = getInverterData;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	static final String INV = "-STRING-INVERTER-";

	public void inverterMapping(AcroFields form, int sheetIndex, Inverters inverter,
			PermitArrayEntityResultSecond arrays, Integer firsttInverterQty, int invIndex) {
		try {

			if (inverter != null) {

				form.setField(sheetIndex + INV + invIndex + "-MANUFACTURER", inverter.getManufacturerMappingValue());
				form.setField(sheetIndex + INV + invIndex + "-MODEL-NUMBER", inverter.getModelMappingValue());
				form.setField(sheetIndex + INV + invIndex + "-QTY", firsttInverterQty + "");
				form.setField(sheetIndex + INV + invIndex + "-NOMINAL-POWER-RATING", inverter.getPaco());
				if (checkValue.NotEquals(inverter.getWeight(), "")) {
					form.setField(sheetIndex + INV + invIndex + "-WEIGHT", inverter.getWeight());
					form.setField(sheetIndex + "-STRING-INVERTER " + invIndex + "-WEIGHT", inverter.getWeight());// For
																													// INV
																													// 2

				} else {
					form.setField(sheetIndex + INV + invIndex + "-WEIGHT", "N/A");
					form.setField(sheetIndex + "-STRING-INVERTER " + invIndex + "-WEIGHT", "N/A");// For INV 2
				}

				form.setField(sheetIndex + INV + invIndex + "-MAX-INPUT-DC-VOLTAGE", inverter.getVdcmax());
				form.setField(sheetIndex + INV + invIndex + "-MIN-MPPT-VOLTAGE-RANGE", inverter.getMpptLow());
				form.setField(sheetIndex + INV + invIndex + "-MAX-MPPT-VOLTAGE-RANGE", inverter.getMpptHigh());
				form.setField(sheetIndex + INV + invIndex + "-MAX-INPUT-CURRENT", inverter.getIdcmax());
				if (checkValue.NotEquals(inverter.getMpptQty(), "")) {
					form.setField(sheetIndex + INV + invIndex + "-MPPT-QUANTITY", inverter.getMpptQty());
				} else
					form.setField(sheetIndex + INV + invIndex + "-MPPT-QUANTITY", "N/A");

				if (arrays != null && arrays.getStringOneInt() != null) {
					form.setField(sheetIndex + INV + invIndex + "-NUMBER-OF-MODULES-PER-STRING-PER-MPPT-TRACKER-1",
							String.valueOf(arrays.getStringOneInt()));
				} else {
					form.setField(sheetIndex + INV + invIndex + "-NUMBER-OF-MODULES-PER-STRING-PER-MPPT-TRACKER-1",
							"N/A");
				}
				if (arrays != null && arrays.getStringTwoInt() != null) {
					form.setField(sheetIndex + INV + invIndex + "-NUMBER-OF-MODULES-PER-STRING-PER-MPPT-TRACKER-2",
							String.valueOf(arrays.getStringTwoInt()));
				} else {
					form.setField(sheetIndex + INV + invIndex + "-NUMBER-OF-MODULES-PER-STRING-PER-MPPT-TRACKER-2",
							"N/A");
				}
				if (arrays != null && arrays.getStringThreeInt() != null) {
					form.setField(sheetIndex + INV + invIndex + "-NUMBER-OF-MODULES-PER-STRING-PER-MPPT-TRACKER-3",
							String.valueOf(arrays.getStringThreeInt()));
				} else {
					form.setField(sheetIndex + INV + invIndex + "-NUMBER-OF-MODULES-PER-STRING-PER-MPPT-TRACKER-3",
							"N/A");
				}

				form.setField(sheetIndex + INV + invIndex + "-INTERGRATED-DC-DISCONNECT", "Yes");
				form.setField(sheetIndex + INV + invIndex + "-INTERGRATED-AC-DISCONNECT", "NO");
				form.setField(sheetIndex + INV + invIndex + "-NOMINAL-VOLTAGE-OUTPUT", inverter.getVac());
				if (checkValue.NotEquals(inverter.getPaco(), "")) {
					form.setField(sheetIndex + INV + invIndex + "-MAX-AC-APPARENT-POWER", inverter.getPaco());
				} else {
					form.setField(sheetIndex + INV + invIndex + "-MAX-AC-APPARENT-POWER", "");

				}
				if (checkValue.NotEquals(inverter.getOcpd(), "")) {
					form.setField(sheetIndex + INV + invIndex + "-MAX-OCPD-AMPS", inverter.getOcpd());
				} else
					form.setField(sheetIndex + INV + invIndex + "-MAX-OCPD-AMPS", "N/A");

				if (checkValue.NotEquals(inverter.getIacmax(), "")) {
					form.setField(sheetIndex + INV + invIndex + "-MAX-OUTPUT-CURRENT", inverter.getIacmax());
				} else
					form.setField(sheetIndex + INV + invIndex + "-MAX-OUTPUT-CURRENT", "Iacmax Update Req");

			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void microInverterMapping(PermitArrayEntityResultSecond arrays, Inverters microInverterInfo, AcroFields form,
			int sheetIndex) {
		try {

			if (checkValue.Equals(arrays.getDeviceToIncorporate(), "Micro Inverter") && microInverterInfo != null) {
				
				String microInverterPaco = getInverterData.getPaco(microInverterInfo);
				String microInverterIacmax = getInverterData.getIacmaxString(microInverterInfo);
				String microInverterIdcmax = getInverterData.getIdcmax(microInverterInfo);
				String microInverterWeight = getInverterData.getWeight(microInverterInfo);
				
				form.setField(sheetIndex + "-MICRO-INVERTER-1-MANUFACTURER",
						microInverterInfo.getManufacturerMappingValue());
				form.setField(sheetIndex + "-MICRO-NVERTER-1-MODEL-NUMBER", microInverterInfo.getModelMappingValue());
				form.setField(sheetIndex + "-MICRO-INVERTER-1-NOMINAL-POWER-RATING", microInverterPaco);
				form.setField(sheetIndex + "-MICRO-INVERTER-1-WEIGHT", microInverterWeight);
				form.setField(sheetIndex + "-MICRO-INVERTER-1-MAX-PV-POWER-AT-MODULE-STC-WATTS", microInverterPaco);
				if (microInverterInfo.getModPerMicro() != null) {
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MODULES-PER-MICRO-INVERTER",
							microInverterInfo.getModPerMicro() + "");
				}
				if (checkValue.NotEquals(microInverterInfo.getVdcmax(), "")) {
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MAX-INPUT-DC-VOLTAGE", microInverterInfo.getVdcmax());
				} else
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MAX-INPUT-DC-VOLTAGE", "N/A");

				// A.B PR-707
				if (checkValue.NotEquals(microInverterIdcmax, "") && checkValue.isNumeric(microInverterIdcmax)) {
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MAX-INPUT-CURRENT",
							String.valueOf(Float.parseFloat(microInverterIdcmax)));
				}

				form.setField(sheetIndex + "-MICRO-INVERTER-1-NOMINAL-VOLTAGE-OUTPUT", microInverterInfo.getVac());
				if (checkValue.NotEquals(microInverterInfo.getOcpd(), "")) {
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS", microInverterInfo.getOcpd() + "");
				} else
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MIN-OCPD-AMPS", "N/A");

				if (checkValue.NotEquals(microInverterIacmax, "") && checkValue.isNumeric(microInverterIacmax)) {
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MAX-OUTPUT-CURRENT",
							String.valueOf(new DecimalFormat("##.##").format(Float.parseFloat(microInverterIacmax))));
				} else
					form.setField(sheetIndex + "-MICRO-INVERTER-1-MAX-OUTPUT-CURRENT", "Iacmax Update Req");

			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

}
