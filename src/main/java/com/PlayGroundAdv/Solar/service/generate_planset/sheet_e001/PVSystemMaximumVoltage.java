package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class PVSystemMaximumVoltage {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final GetPVModuleData getPVModuleData;

	public PVSystemMaximumVoltage(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			GetPVModuleData getPVModuleData) {
		super();
		this.checkValue = checkValue;
		this.getPVModuleData = getPVModuleData;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public void extremeMinimumString(String extremeMinimum, Cmodulev2 moduleInfo, AcroFields form,
			List<Inverters> inverters, int sheetIndex) {
		try {
			if (checkValue.isNumeric(extremeMinimum)) {
				
				Float tempCorrected = extremeMinimum(extremeMinimum, moduleInfo, form, sheetIndex);
				form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-TEMP-COEFFICIENT-FORMULA-PLUS-1", "1");
				
				if (inverters.get(0) != null && checkValue.NotEquals(inverters.get(0).getMpptLow(), "")
						&& checkValue.NotEquals(tempCorrected, 0f)) {
					form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-NUMBER OF MODULES-IN-SERIES-AT-110-PERCENT",
							String.valueOf(new DecimalFormat("0")
									.format((float) ((Float.parseFloat(inverters.get(0).getMpptLow()) * 1.1)
											/ tempCorrected))));
					form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-NUMBER-OF-MODULES-IN-SERIES-AT-120-PERCENT",
							String.valueOf(new DecimalFormat("0")
									.format((float) ((Float.parseFloat(inverters.get(0).getMpptLow()) * 1.2)
											/ tempCorrected))));
				} else {
					form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-NUMBER OF MODULES-IN-SERIES-AT-110-PERCENT", "");

					form.setField(sheetIndex + "-STRING-INVERTER-1-MIN-NUMBER-OF-MODULES-IN-SERIES-AT-120-PERCENT", "");
				}
				if (inverters.size() > 1 && inverters.get(1) != null
						&& checkValue.NotEquals(inverters.get(1).getMpptLow(), "")
						&& checkValue.NotEquals(tempCorrected, 0f)) {
					form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-NUMBER-OF-MODULES-IN-SERIES-AT-110-PERCENT",
							String.valueOf(new DecimalFormat("0")
									.format((float) ((Float.parseFloat(inverters.get(1).getMpptLow()) * 1.1)
											/ tempCorrected))));
					form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-NUMBER-OF-MODULES-IN-SERIES-AT-120-PERCENT",
							String.valueOf(new DecimalFormat("0")
									.format((float) ((Float.parseFloat(inverters.get(1).getMpptLow()) * 1.2)
											/ tempCorrected))));
				} else {
					form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-NUMBER-OF-MODULES-IN-SERIES-AT-110-PERCENT", "");
					form.setField(sheetIndex + "-STRING-INVERTER-2-MIN-NUMBER-OF-MODULES-IN-SERIES-AT-120-PERCENT", "");
				}
				if (inverters.get(0) != null && checkValue.NotEquals(inverters.get(0).getVdcmax(), "")
						&& tempCorrected > 0) {
					form.setField(sheetIndex + "-STRING-INVERTER-1-MAX-NUMBER-OF-MODULES-IN-SERIES",
							String.valueOf(new DecimalFormat("0")
									.format(Float.parseFloat(inverters.get(0).getVdcmax()) / tempCorrected)));
				} else {
					form.setField(sheetIndex + "-STRING-INVERTER-1-MAX-NUMBER-OF-MODULES-IN-SERIES", "");
				}
				if (inverters.size() > 1 && inverters.get(1) != null
						&& checkValue.NotEquals(inverters.get(1).getVdcmax(), "") && tempCorrected > 0) {
					form.setField(sheetIndex + "-STRING-INVERTER-2-MAX-NUMBER-OF-MODULES-IN-SERIES",
							String.valueOf(new DecimalFormat("0")
									.format(Float.parseFloat(inverters.get(1).getVdcmax()) / tempCorrected)));
				} else {
					form.setField(sheetIndex + "-STRING-INVERTER-2-MAX-NUMBER-OF-MODULES-IN-SERIES", "");
				}
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public Float extremeMinimum(String extremeMinimum, Cmodulev2 moduleInfo, AcroFields form, int sheetIndex) {
		try {
			if (checkValue.isNumeric(extremeMinimum)) {

				form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-EXTREME-MIN-TEMPERATURE", extremeMinimum);
				form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-STC-TEMPERATURE", "25");
				int correctedTemperature = Integer.valueOf(extremeMinimum) - 25;
				form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-STC-TEMPERATURE1",
						String.valueOf(correctedTemperature));
				if (moduleInfo != null && checkValue.isNumeric(moduleInfo.getGammaR())) {
					Float tempCoefficient = Float.parseFloat(moduleInfo.getGammaR().replace(',', '.'));
					form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-MODULE-Pmax-TEMP-COEFFICIENT",
							String.valueOf(new DecimalFormat("##.##").format(tempCoefficient)) + "%");
					Float calculation = (correctedTemperature * tempCoefficient) / 100;
					form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-TEMP-COEFFICIENT-FORMULA",
							String.valueOf(new DecimalFormat("##.##").format(calculation)));
					Float correctedTempCoef = calculation + 1;
					form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-CORRECTED-COEFFICIENT",
							String.valueOf(new DecimalFormat("##.##").format(correctedTempCoef)));
					form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-TEMP COEFFICIENT-FORMULA-PLUS-1", "1");
					String vOcRef = getPVModuleData.getVocRef(moduleInfo);
					if (checkValue.NotEquals(vOcRef, "")) {
						form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-HIGHEST-MODULE-Voc", vOcRef);
						Float tempCorrected = Float
								.parseFloat(String.valueOf(new DecimalFormat("##.##").format(correctedTempCoef)))
								* Float.parseFloat(vOcRef);
						form.setField(sheetIndex + "-PV-SYSTEM-MAXIMUM-Voc-TEMPERATURE-CORRECTED-Voc",
								String.valueOf(new DecimalFormat("##.##").format(tempCorrected)));
						form.setField(sheetIndex + "-PV-MODULE-1-TEMP-CORRECTED-Voc",
								String.valueOf(new DecimalFormat("##.##").format(tempCorrected)));
						return tempCorrected;
					}
				}
			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return 0f;
	}
}
