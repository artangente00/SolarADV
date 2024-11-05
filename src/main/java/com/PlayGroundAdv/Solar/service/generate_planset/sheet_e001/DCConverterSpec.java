package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCConverterSpec {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;

	public DCConverterSpec(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public void optimizerMapping(PermitArrayEntityResultSecond arrays, DCOptimizerEntity dcOptimizer, AcroFields form,
			int sheetIndex) {
		try {

			if (dcOptimizer != null && checkValue.Equals(arrays.getDeviceToIncorporate(), "System Optimizer")) {

				form.setField(sheetIndex + "-PV-MODULE-1-DC-DC-OPTIMIZER-MANUFACTURER",
						dcOptimizer.getManufacturerMappingValue());
				form.setField(sheetIndex + "-PV-MODULE-1-DC-DC-OPTIMIZER-MODEL-NUMBER",
						dcOptimizer.getModelMappingValue());

				if (checkValue.NotEquals(dcOptimizer.getWeight(), "")) {
					form.setField(sheetIndex + "-PV-MODULE-1-DC-DC-OPTIMIZER-WEIGHT",
							dcOptimizer.getWeight().replace(",", "."));
				}

				form.setField(sheetIndex + "-PV-MODULE-1-DC-DC-OPTIMIZER-Isc", dcOptimizer.getRatedOutputIsc());

				/// --- CR 800 planset mapping update ---///
				form.setField(sheetIndex + "-OPTIMIZER-MAX-OUT-VOLTAGE", dcOptimizer.getMaxOutputVoltage());
				form.setField(sheetIndex + "-OPTIMIZER-MAX-IN-VOLTAGE", dcOptimizer.getMaxInputVoltage());

				/// --------------CR 3785 PM Edit Optimization----------------------///
				if (dcOptimizer.getType() != null && dcOptimizer.getType().equals("DC/DC Rapid Shutdown")) {
					form.setField(sheetIndex + "-DC-DC-CONVERTER", "DC/DC RAPID SHUTDOWN");
				} else {
					form.setField(sheetIndex + "-DC-DC-CONVERTER", "DC/DC OPTIMIZER");
				}

			} else {
				form.setField(sheetIndex + "-PV-MODULE-1-DC-DC-OPTIMIZER-MANUFACTURER", "");
				form.setField(sheetIndex + "-PV-MODULE-1-DC-DC-OPTIMIZER-MODEL-NUMBER", "");
				form.setField(sheetIndex + "-PV-MODULE-1-DC-DC-OPTIMIZER-WEIGHT", "");
				form.setField(sheetIndex + "-PV-MODULE-1-DC-DC-OPTIMIZER-Isc", "");
				form.setField(sheetIndex + "-OPTIMIZER-MAX-OUT-VOLTAGE", "");
				form.setField(sheetIndex + "-OPTIMIZER-MAX-IN-VOLTAGE", "");
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
