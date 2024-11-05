package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACDisconnectSpec {

	final CheckValueTypesService checkValue;
	final GetACDisconnectQty acdDisconnectQty;

	public ACDisconnectSpec(CheckValueTypesService checkValue, GetACDisconnectQty acdDisconnectQty) {
		super();
		this.checkValue = checkValue;
		this.acdDisconnectQty = acdDisconnectQty;
	}

	static final String AC_DISCONNECT = "-AC-DISCO-";

	public void acDisconnectMapping(AcroFields form, int sheetIndex, ACDisconnect acDisconnect, int acdIndex) {
		try {
			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-MANUFACTURER",
					acDisconnect.getManufacturerMappingValue());
			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-MODEL-NUMBER", acDisconnect.getModelMappingValue());
			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-OCPD-DISCO-TYPE",
					acDisconnect.getDisconnectDeviceType());
			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-RATED-CURRENT", acDisconnect.getRatedCurrent());
			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-NUMBER-OF-POLES", acDisconnect.getNumberOfPoles());
			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-NEMA-RATING", acDisconnect.getNemaRating());

			if (checkValue.Equals(acDisconnect.getRatedOperationalVoltage(), "240VPhase")) {
				form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-RATED-NOMINAL-VOLTAGE", "240");
			} else {
				form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-RATED-NOMINAL-VOLTAGE",
						acDisconnect.getRatedOperationalVoltage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void acDisconnectQuantity(AcroFields form, int sheetIndex, ACDisconnect acDisconnect,
			ACDisconnect acDisconnectTwo, ACDisconnect acDisconnectThree, ACDisconnect acDisconnectFour, int acdIndex,
			Integer acdQty) {
		try {
			Integer qty = 1;
			if (acdIndex == 1) {
				qty = acdDisconnectQty.getStringAcDisconnectQty(acdQty, acDisconnect, acDisconnectThree,
						acDisconnectFour);
			} else if (acdIndex == 2) {
				qty = acdDisconnectQty.getStringAcDisconnectTwoQty(acdQty, acDisconnect, acDisconnectTwo,
						acDisconnectThree, acDisconnectFour);
			}

			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-QTY", String.valueOf(qty));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void acDisconnectCalcul(AcroFields form, int sheetIndex, ACDisconnect acDisconnect, int acdIndex,
			List<Inverters> inverters, Integer firsttInverterQty, Integer secondtInverterQty) {
		try {
			float inputCurrent = getInputCurrent(inverters, firsttInverterQty, secondtInverterQty);
			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-FUSE-RATING",
					fuseRatingMapping(acDisconnect, inputCurrent));

			if (inputCurrent > 0) {
				form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-TOTAL-INPUT-CURRENT",
						new DecimalFormat("##.##").format(inputCurrent) + "");
			} else
				form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-TOTAL-INPUT-CURRENT", "Iacmax Update Req");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void acDisconnectMicroCalcul(AcroFields form, int sheetIndex, ACDisconnect acDisconnect, int acdIndex,
			Inverters microInverterInfo, Integer modulePerMicroInverter, Integer acdQty) {
		try {
			
			form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-QTY", String.valueOf(acdQty));
			if (microInverterInfo != null && checkValue.NotEquals(microInverterInfo.getIacmax(), "")
					&& checkValue.isNumeric(microInverterInfo.getIacmax())) {

				float inputCurrents = (Float.parseFloat(microInverterInfo.getIacmax())
						* modulePerMicroInverter);
				form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-QTY-FUSE-BREAKERS",
						new DecimalFormat("##.##").format(inputCurrents) + "");
				form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-MAX-INPUT-FUSE-BREAKER-RATING-AMPS",
						getMicroFuseRatingMapping(acDisconnect, inputCurrents));

			} else {
				form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-QTY-FUSE-BREAKERS", "N/A");
				form.setField(sheetIndex + AC_DISCONNECT + acdIndex + "-MAX-INPUT-FUSE-BREAKER-RATING-AMPS", "N/A");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public float getInputCurrent(List<Inverters> inverters, Integer firsttInverterQty, Integer secondtInverterQty) {
		float inputCurrent = 0f;
		if (inverters != null && !inverters.isEmpty() && checkValue.NotEquals(inverters.get(0).getIacmax(), "")
				&& checkValue.isNumeric(inverters.get(0).getIacmax()) && firsttInverterQty != null) {
			inputCurrent = firsttInverterQty * Float.parseFloat(inverters.get(0).getIacmax());
		}
		if (inverters != null && inverters.size() > 1 && inverters.get(1) != null
				&& checkValue.NotEquals(inverters.get(1).getIacmax(), "")
				&& checkValue.isNumeric(inverters.get(1).getIacmax()) && secondtInverterQty != null) {
			inputCurrent = inputCurrent + (secondtInverterQty * Float.parseFloat(inverters.get(1).getIacmax()));

			if (inverters.size() > 2 && inverters.get(2) != null
					&& checkValue.NotEquals(inverters.get(2).getIacmax(), "")
					&& checkValue.isNumeric(inverters.get(2).getIacmax())) {
				inputCurrent = inputCurrent + Float.parseFloat(inverters.get(2).getIacmax());

				if (inverters.size() > 3 && inverters.get(3) != null
						&& checkValue.NotEquals(inverters.get(3).getIacmax(), "")
						&& checkValue.isNumeric(inverters.get(3).getIacmax())) {
					inputCurrent = inputCurrent + Float.parseFloat(inverters.get(3).getIacmax());

					if (inverters.size() > 4 && inverters.get(4) != null
							&& checkValue.NotEquals(inverters.get(4).getIacmax(), "")
							&& checkValue.isNumeric(inverters.get(4).getIacmax())) {
						inputCurrent = inputCurrent + Float.parseFloat(inverters.get(4).getIacmax());
					}
				}
			}

		}
		return inputCurrent;
	}

	public String fuseRatingMapping(ACDisconnect acDisconnect, float inputCurrent) {
		String fuseRate = "";
		if (acDisconnect != null && acDisconnect.getDisconnectDeviceType() != null
				&& (checkValue.Equals(acDisconnect.getDisconnectDeviceType().toLowerCase(), "fusible disconnect")
						|| checkValue.Equals(acDisconnect.getDisconnectDeviceType().toLowerCase(), "fusible"))) {
			float fuseRating = (float) (inputCurrent * 1.25);
			Integer numbers[] = new Integer[] { 10, 15, 20, 30, 40, 50, 60, 70, 75, 80, 90, 100, 110, 125, 150, 175,
					200, 225, 250, 300, 350, 400, 450, 500, 600, 800, 1000, 1200, 1400, 1600, 1800, 2000 };
			if (fuseRating > 0) {
				Integer theNumber = 0;
				int i = 0;
				while (numbers[i] < fuseRating && i < numbers.length - 1) {
					i++;
				}
				theNumber = numbers[i];
				fuseRate = theNumber + "";
			} else if (inputCurrent > 0) {
				fuseRate = "N/A";
			} else
				fuseRate = "Iacmax Update Req";
		} else {
			fuseRate = "N/A";
		}
		return fuseRate;
	}

	public String getMicroFuseRatingMapping(ACDisconnect acDisconnect, float inputCurrent) {

		if (acDisconnect != null && acDisconnect.getDisconnectDeviceType() != null
				&& (checkValue.Equals(acDisconnect.getDisconnectDeviceType().toUpperCase(), "FUSIBLE DISCONNECT")
						|| checkValue.Equals(acDisconnect.getDisconnectDeviceType().toUpperCase(), "FUSIBLE"))) {
			Float fuseRating = (float) (inputCurrent * 1.25);
			float numbers[] = new float[] { 10, 15, 20, 30, 40, 50, 60, 70, 75, 80, 90, 100, 110, 125, 150, 175, 200,
					225, 250, 300, 350, 400, 450, 500, 600, 800, 1000, 1200, 1400, 1600, 1800, 2000 };

			if (fuseRating > 0) {
				float theNumber = 0;
				int i = 0;
				while (numbers[i] < fuseRating && i < numbers.length - 1) {
					i++;
				}
				theNumber = numbers[i];
				return theNumber + "";
			} else
				return "N/A";
		} else
			return "N/A";
	}

}
