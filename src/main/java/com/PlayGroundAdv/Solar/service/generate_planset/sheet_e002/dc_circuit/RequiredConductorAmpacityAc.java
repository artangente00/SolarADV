package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.dc_circuit;

import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class RequiredConductorAmpacityAc {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	
	public RequiredConductorAmpacityAc(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
	}
	public E002Model mapRequiredAmpacity(int i, AcroFields form, String iScRef, Cmodulev2 moduleInfo, int numberOfStrings,
			int originCombiner, int sheetIndex, E002Model params) {

		params.setRequiredConductorAmpacity("");
		try {

			form.setField(sheetIndex + "-" + "DC" + i + "-HIGHEST-MODULE-Isc-IN-CIRCUIT", iScRef);
			form.setField(sheetIndex + "-" + "DC" + i + "-MAX-CURRENT-OF-CIRCUIT", "1.25");
			form.setField(sheetIndex + "-" + "DC" + i + "-CONTINUOUS-OPERATION", "1.25");
			if (originCombiner > 0 && i >= originCombiner) {

				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-PARALLEL-MODULES-IN-SERIES-STRING",
						String.valueOf(numberOfStrings));
				if (checkValue.isNumeric(iScRef)) {
					float iscRef = Float.parseFloat(iScRef);
					params.setRequiredConductorAmpacity(String
							.valueOf(new DecimalFormat("##.##").format(iscRef * 1.25 * 1.25 * numberOfStrings)));
				}

			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-PARALLEL-MODULES-IN-SERIES-STRING", "1");
				if (checkValue.isNumeric(iScRef)) {
					float iscRef = Float.parseFloat(iScRef);
					params.setRequiredConductorAmpacity(String.valueOf(new DecimalFormat("##.##").format(iscRef * 1.25 * 1.25)));
				}
			}

			form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY1", params.getRequiredConductorAmpacity());
			form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY2", params.getRequiredConductorAmpacity());

		} catch (NumberFormatException|IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}
	public E002Model mapSystemOptimizerRequiredAmpacity(int i, AcroFields form, String iScRef, String iScOptimizer,
			int numberOfStrings, int originCombiner, int sheetIndex, E002Model params) {

		params.setRequiredConductorAmpacity("");

		try {
			if (i == 1) {

				form.setField(sheetIndex + "-" + "DC" + i + "-HIGHEST-MODULE-Isc-IN-CIRCUIT", iScRef);
				form.setField(sheetIndex + "-" + "DC" + i + "-MAX-CURRENT-OF-CIRCUIT", "1.25");
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-PARALLEL-MODULES-IN-SERIES-STRING", "1");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONTINUOUS-OPERATION", "1.25");

				if (checkValue.isNumeric(iScRef)) {
					float iscRef = Float.parseFloat(iScRef);
					params.setRequiredConductorAmpacity(String.valueOf(new DecimalFormat("##.##").format(iscRef * 1.25 * 1.25)));
				}

				form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY1", params.getRequiredConductorAmpacity());
				form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY2", params.getRequiredConductorAmpacity());

			} else {
				if (originCombiner > 0 && i >= originCombiner) {

					form.setField(sheetIndex + "-" + "DC" + i + "-HIGHEST-MODULE-Isc-IN-CIRCUIT", iScOptimizer);
					form.setField(sheetIndex + "-" + "DC" + i + "-MAX-CURRENT-OF-CIRCUIT", "1");
					form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-PARALLEL-MODULES-IN-SERIES-STRING",
							String.valueOf(numberOfStrings));
					form.setField(sheetIndex + "-" + "DC" + i + "-CONTINUOUS-OPERATION", "1.25");

					if (checkValue.isNumeric(iScOptimizer)) {
						float iscRef = Float.parseFloat(iScOptimizer);
						params.setRequiredConductorAmpacity(String
								.valueOf(new DecimalFormat("##.##").format(iscRef * 1.25 * numberOfStrings)));
					}

					form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY1", params.getRequiredConductorAmpacity());
					form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY2", params.getRequiredConductorAmpacity());

				} else {
					form.setField(sheetIndex + "-" + "DC" + i + "-HIGHEST-MODULE-Isc-IN-CIRCUIT", iScOptimizer);
					form.setField(sheetIndex + "-" + "DC" + i + "-MAX-CURRENT-OF-CIRCUIT", "1");
					form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-PARALLEL-MODULES-IN-SERIES-STRING", "1");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONTINUOUS-OPERATION", "1.25");

					if (checkValue.isNumeric(iScOptimizer)) {
						float iscRef = Float.parseFloat(iScOptimizer);
						params.setRequiredConductorAmpacity(String.valueOf(new DecimalFormat("##.##").format(iscRef * 1.25)));
					}

					form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY1", params.getRequiredConductorAmpacity());
					form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY2", params.getRequiredConductorAmpacity());
				}

			}

		} catch (NumberFormatException|IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}
}
