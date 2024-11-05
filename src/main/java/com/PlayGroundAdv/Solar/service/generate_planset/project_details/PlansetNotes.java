package com.PlayGroundAdv.Solar.service.generate_planset.project_details;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJChecklistEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
import com.PlayGroundAdv.Solar.model.ahj_library.GoverningCodesModel;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class PlansetNotes {

	final CheckValueTypesService checkValue;

	public PlansetNotes(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

//	S.B 29/09/2020 CR-PM-3365-MOD-001
	public String necOrCecNoteMapping(GoverningCodesModel governingCodes, String projectState) {

		if (projectState.equals("CA")) {
			if (governingCodes.getCec() != null && !governingCodes.getCec().isEmpty()) {
				return "WIRE COLOR CODING (" + governingCodes.getCec() + ") CEC SECTIONS 250.119 & 200.6";
			} else {
				return "WIRE COLOR CODING (2019) CEC SECTIONS 250.119 & 200.6";
			}

		} else {
//			A.B 11-30-2022 REV-CR-PM-265-MOD-004: Map Customized sheets according to the default governing codes
			String nec = getNecCode(governingCodes, projectState);
			return "WIRE COLOR CODING (" + nec + ") NEC SECTIONS 250.119 & 200.6";
		}
	}

	private String getNecCode(GoverningCodesModel governingCodes, String state) {
		return governingCodes != null && checkValue.isStringNotEmpty(governingCodes.getNec()) ? governingCodes.getNec()
				: checkValue.Equals(state, "ID") || checkValue.Equals(state, "NC") || checkValue.Equals(state, "CO")
						|| state.equals("GA") || checkValue.Equals(state, "TX") || checkValue.Equals(state, "OR")
								? "2017"
								: checkValue.Equals(state, "SC") || checkValue.Equals(state, "OK")
										|| checkValue.Equals(state, "MO") ? "2014"
												: checkValue.Equals(state, "MA") ? "2020" : "2017";
	}

//	S.B 01/10/2020 CR-PM-3365-MOD-004
	public String electricalNote(GoverningCodesModel governingCodes, String projectState, String poc,
			UserSettingEntity userSetting) {

		if (userSetting != null && checkValue.isStringNotEmpty(userSetting.getPointOfConnectionNote())) {
//			A.B 14/10/2021 CR-2228-MOD-003
			return userSetting.getPointOfConnectionNote();
		} else {
			String note = "";
			try {

				String noCenterFed = "NO CENTER-FED MAIN BREAKER. PANEL CONFIGURED PER ";
				String noCenterFedCec = noCenterFed + "CEC ";
				String noCenterFedNec = noCenterFed + "NEC ";
				String code = "705.12";
				String codeA = code + "(A)";
				String codeB = code + "(B)(2)(3)(b)";
				String codeD = code + "(D)(2)(3)(b)";
				String cecCode = governingCodes != null && governingCodes.getCec() != null ? governingCodes.getCec()
						: "";
				String necCode = governingCodes != null && governingCodes.getNec() != null ? governingCodes.getNec()
						: "";
				switch (poc) {
				case "Line-side tap":
					if (projectState.equals("CA")
							&& (cecCode.equals("2019") || cecCode.equals("") || cecCode.equals("2016")))
						note = noCenterFedCec + codeA;
					else if (necCode.equals("2017") || necCode.equals("") || necCode.equals("2014")
							|| necCode.equals("2020"))
						note = noCenterFedNec + codeA;
					break;
				case "Back-fed Breaker":
					if (necCode.equals("2014") || (necCode.equals("") && !projectState.equals("CA")))
						note = noCenterFedNec + codeD;
					else if (cecCode.equals("2016") && projectState.equals("CA"))
						note = noCenterFedCec + codeD;
					else if (!projectState.equals("CA") && (necCode.equals("2017") || necCode.equals("2020")))
						note = noCenterFedNec + codeB;
					else if (projectState.equals("CA") && (cecCode.equals("2019") || cecCode.equals("")))
						note = noCenterFedCec + codeB;
					break;
				default:
					if (projectState.equals("CA") && (cecCode.equals("") || cecCode.equals("2019")))
						note = noCenterFedCec + codeB;
					else if (necCode.equals("2017") || necCode.equals("2020"))
						note = noCenterFedNec + codeB;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return note;
		}

	}
}
