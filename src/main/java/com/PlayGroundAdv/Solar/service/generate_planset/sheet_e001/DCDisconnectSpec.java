package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCDisconnectSpec {
	final CheckValueTypesService checkValue;

	public DCDisconnectSpec(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	final static String DC_DISCONNECT = "-DC-CB-DISCO-";

	public void dcDisconnectMapping(AcroFields form, int sheetIndex, DCCombinerDisconnectEntity dcDisconnect,
			DCCombinerDisconnectEntity roofTopDCDisconnect, Cmodulev2 moduleInfo, List<Inverters> inverters,
			String location, PermitArrayEntityResultSecond arrays, PlansetUtils plansetUtils, int acdIndex) {
		try {

			if ((dcDisconnect != null && dcDisconnect.getId() != null)
					|| (roofTopDCDisconnect != null && roofTopDCDisconnect.getId() != null)) {

				DCCombinerDisconnectEntity dcd = new DCCombinerDisconnectEntity();
				if (dcDisconnect != null && dcDisconnect.getId() != null) {
					dcd = dcDisconnect;
				} else if (roofTopDCDisconnect != null && roofTopDCDisconnect.getId() != null) {
					dcd = roofTopDCDisconnect;
				}

				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-MANUFACTURER",
						dcd.getManufacturerMappingValue());
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-MODEL-NUMBER", dcd.getModelMappingValue());
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-OCPD-DISCO-TYPE", dcd.getOcpd());
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-WEIGHT", dcd.getWeight());
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-NEMA-RATING", dcd.getNemaRating());
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-MAX-INPUT-BREAKER-RATING-AMPS",
						dcd.getMaxInput());
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-MAX-CIRCUIT-OUTPUT-CURRENT",
						dcd.getMaxOutputCurrent());
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-MAX-CONTINUOUS-OUTPUT-CURRENT",
						dcd.getMaxContiOutputCurrent());

				String iScRef = getCorrectValue(moduleInfo.getiScRef());
				String vOcRef = getCorrectValue(moduleInfo.getvOcRef());

				if (checkValue.NotEquals(iScRef, "")) {
					form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-SERIES-FUSE-RATING-FOR-PV-MODULES",
							String.valueOf(new DecimalFormat("##.##").format(Float.parseFloat(iScRef) * 1.25)));
				}
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-HIGHEST-MODULE-Voc", vOcRef);
				if (inverters != null && !inverters.isEmpty()
						&& checkValue.NotEquals(inverters.get(0).getVdcmax(), "")) {
					String vdcmax = getCorrectValue(inverters.get(0).getVdcmax());
					form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-MAX-ALLOWED-INPUT-VOLTAGE", vdcmax);
				}

				if (arrays != null && ((checkValue.Equals(arrays.getRoofMounted(), true))
						|| ((checkValue.Equals(arrays.getCarportMounted(), true)
								|| checkValue.Equals(arrays.getPatioMounted(), true))))) {
					form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-LOCATION", "Roof Top");
				} else {
					if (checkValue.NotEquals(location, "")) {
						form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-LOCATION", location);
					}
				}

				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-QTY-OF-PV-SOURCE-CIRCUITS", plansetUtils.getModuleQty() + "");
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-QTY-OF PV-SOURCE-CIRCUITS", plansetUtils.getModuleQty() + ""); //For DCD-2
				form.setField(sheetIndex + DC_DISCONNECT + acdIndex + "-MAX-QTY-OF-MODULES-IN-PV-SOURCE-CIRCUITS",
						plansetUtils.getMaxNumModule() + "");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCorrectValue(String detail) {
		if (detail != null && checkValue.NotEquals(detail, "")) {
			if (detail.contains(",")) {
				return detail.replace(',', '.');
			} else
				return detail;
		} else
			return "";
	}
}
