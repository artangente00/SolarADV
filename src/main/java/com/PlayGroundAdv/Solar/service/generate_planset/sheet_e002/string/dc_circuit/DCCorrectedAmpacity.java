package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.dc_circuit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCCorrectedAmpacity {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final NEC3106B16Repository nec3106B16Repo;

	public DCCorrectedAmpacity(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			NEC3106B16Repository nec3106b16Repo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		nec3106B16Repo = nec3106b16Repo;
	}

	public E002Model ampacityMapping(AcroFields form, int i, E002Model params, List<Integer> dcNumberOfConductors,
			int sheetIndex) {

		try {
			params.setNEC310(nec3106B16Repo.findFirstBytradeSze(params.getConductorSize()));
			if (checkValue.Equals(params.getConductorSize(), "#12 AWG")) {
				form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL", "(1) CU");
				dcNumberOfConductors.add(1);
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "30");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING", "30");

				form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY",
						String.valueOf(new DecimalFormat("##.##")
								.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.')) * 30
										* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
				form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1",
						String.valueOf(new DecimalFormat("##.##")
								.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.')) * 30
										* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
				params.setCorrectedAmpacity(String.valueOf(new DecimalFormat("##.##")
						.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.')) * 30
								* Float.parseFloat(params.getDcampacityCorrectionB3a()))));

			} else if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {

				form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL",
						"(" + params.getNEC310().getNumberOfConductors() + ") CU");
				dcNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
						String.valueOf(params.getNEC310().getNinetyInsulation()));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING",
						String.valueOf(params.getNEC310().getNinetyInsulation()));
				form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY",
						String.valueOf(new DecimalFormat("##.##")
								.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
										* Float.parseFloat(String.valueOf(params.getNEC310().getNinetyInsulation())
												.replace(',', '.'))
										* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
				form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1",
						String.valueOf(new DecimalFormat("##.##")
								.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
										* Float.parseFloat(String.valueOf(params.getNEC310().getNinetyInsulation())
												.replace(',', '.'))
										* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
				params.setCorrectedAmpacity(String.valueOf(new DecimalFormat("##.##")
						.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
								* Float.parseFloat(
										String.valueOf(params.getNEC310().getNinetyInsulation()).replace(',', '.'))
								* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", "");
				dcNumberOfConductors.add(1);
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}
}
