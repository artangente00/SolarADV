package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class E003SharedUtils {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;

	public E003SharedUtils(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	// A.B 03/12/2019: CR-2515 Test Ground Conductor Size
	public Boolean conductorSizeIs2OrSmaller(String conduitSize) {
		try {
			return checkValue.Equals(conduitSize, "#14 AWG") || checkValue.Equals(conduitSize, "#12 AWG")
					|| checkValue.Equals(conduitSize, "#10 AWG") || checkValue.Equals(conduitSize, "#8 AWG")
					|| checkValue.Equals(conduitSize, "#6 AWG") || checkValue.Equals(conduitSize, "#4 AWG")
					|| checkValue.Equals(conduitSize, "#3 AWG") || checkValue.Equals(conduitSize, "#2 AWG");
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return false;
		}
	}

	// A.B 03/12/2019: CR-2515 Get Ground Conductor Size
	public String getGroundSize(String conduitSize) {

		try {

			if (conduitSize == null || checkValue.Equals(conduitSize, "")) {
				return "";
			} else if (checkValue.Equals(conduitSize, "EXISTING")) {
				return "EXISTING";
			} else if (checkValue.Equals(conduitSize, "#1 AWG") || checkValue.Equals(conduitSize, "#1/0 AWG")) {
				return "#6 AWG";
			} else if (checkValue.Equals(conduitSize, "#2/0 AWG") || checkValue.Equals(conduitSize, "#3/0 AWG")) {
				return "#4 AWG";
			} else if (checkValue.Equals(conduitSize, "#4/0 AWG") || checkValue.Equals(conduitSize, "250 kc")
					|| checkValue.Equals(conduitSize, "300 kc") || checkValue.Equals(conduitSize, "350 kc")) {
				return "#2 AWG";
			} else if (checkValue.Equals(conduitSize, "400 kc") || checkValue.Equals(conduitSize, "500 kc")
					|| checkValue.Equals(conduitSize, "600 kc")) {
				return "#1/0 AWG";
			} else if (checkValue.Equals(conduitSize, "700 kc") || checkValue.Equals(conduitSize, "750 kc")
					|| checkValue.Equals(conduitSize, "800 kc") || checkValue.Equals(conduitSize, "900 kc")
					|| checkValue.Equals(conduitSize, "1000 kc") || checkValue.Equals(conduitSize, "1100 kc")) {
				return "#2/0 AWG";
			} else {
				return "#3/0 AWG";
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}

	}

	// A.B 03/12/2019: CR-2515 Get Ground Conductor Size
	public String getGroundSizeValue(String conduitSize, String tradeSize, String minACGroundCon,
			String minACGroundConOther) {

		try {
			if (checkValue.Equals(conduitSize, "EXISTING")) {
				return "EXISTING";
			}
			return checkValue.Equals(conductorSizeIs2OrSmaller(tradeSize), true)
					? checkValue.isStringNotEmpty(minACGroundCon)
							? checkValue.Equals(minACGroundCon, "Other") ? minACGroundConOther : minACGroundCon
							: "#8 AWG"
					: getGroundSize(tradeSize);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}

	}
}
