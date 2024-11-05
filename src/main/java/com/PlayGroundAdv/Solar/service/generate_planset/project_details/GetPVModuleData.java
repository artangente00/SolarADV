package com.PlayGroundAdv.Solar.service.generate_planset.project_details;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
@Service
public class GetPVModuleData {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	
	public GetPVModuleData(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
	}

	public String getIScRef(Cmodulev2 moduleInfo) {
		try {
			if (moduleInfo != null && checkValue.isStringNotEmpty(moduleInfo.getiScRef())) {
				return moduleInfo.getiScRef().replace(',', '.');
			} else {
				return "ISC Update Req";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "";
		}
	}

	public String getVocRef(Cmodulev2 moduleInfo) {
		if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getvOcRef(), "")) {
			return moduleInfo.getvOcRef().replace(',', '.');
		}
		return "";
	}

	public String getvMpRef(Cmodulev2 moduleInfo) {
		if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getvMpRef(), "")) {
			return moduleInfo.getvMpRef().replace(',', '.');
		}
		return "";
	}
	
	public String getiMpRef(Cmodulev2 moduleInfo) {
		if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getiMpRef(), "")) {
			return moduleInfo.getiMpRef().replace(',', '.');
		}
		return "";
	}
	
	public String getiScRef(Cmodulev2 moduleInfo) {
		if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getiScRef(), "")) {
			return moduleInfo.getiScRef().replace(',', '.');
		}
		return "";
	}
	
	public String getStcRounded(Cmodulev2 moduleInfo) {
		if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getStcRounded(), "")) {
			return moduleInfo.getStcRounded().replace(',', '.');
		}
		return "";
	}
	
	public String getIacmax(Cmodulev2 moduleInfo) {
		if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getIacmax(), "")) {
			return moduleInfo.getIacmax().replace(',', '.');
		}
		return "";
	}
}
