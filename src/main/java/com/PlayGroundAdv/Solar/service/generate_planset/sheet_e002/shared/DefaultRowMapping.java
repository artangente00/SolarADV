package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DefaultRowMapping {

	public void mapDcDefaultValue(int i, AcroFields form, int sheetIndex, String v) {
		try {
			if (v.equals("")) {
				form.setField(sheetIndex + "-" + "AC" + i + "-CIRCUIT-ORGIN", v);
				form.setField(sheetIndex + "-" + "AC" + i + "-CIRCUIT-DESTINATION", v);
			}
			form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-TEMPERATURE", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-HIGHEST-MODULE-Isc-IN-CIRCUIT", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-PARALLEL-MODULES-IN-SERIES-STRING", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-MAX-CURRENT-OF-CIRCUIT", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONTINUOUS-OPERATION", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY1", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CIRCUIT-ENVIRONMENT", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-04-PERCENT-AVERAGE-HIGH-TEMPERATURE", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-HEIGHT-ABOVE-ROOF", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-TEMPERATURE-ADDER", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-OPERATING-TEMPERATURE", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION1", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-REQUIRED-AMPACITY2", v);
			form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", v);
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void mapAcDefaultValue(int i, AcroFields form, int sheetIndex, String v) {
		try {
			if (v.equals("")) {
				form.setField(sheetIndex + "-" + "AC" + i + "-CIRCUIT-ORGIN", v);
				form.setField(sheetIndex + "-" + "AC" + i + "-CIRCUIT-DESTINATION", v);
			}
			form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-TEMPERATURE", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONTINUOUS-OPERATION", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY1", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CIRCUIT-ENVIRONMENT", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-04-PERCENT-AVERAGE-HIGH-TEMPERATURE", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-HEIGHT-ABOVE-ROOF", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-TEMPERATURE-ADDER", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-OPERATING-TEMPERATURE", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION1", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY", v);
			form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1", v);
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
		}
	}

}
