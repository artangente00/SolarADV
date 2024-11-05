package com.PlayGroundAdv.Solar.service.generate_planset.project_details;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetConverterData {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;

	public GetConverterData(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
	}

	public String getIScRef(DCOptimizerEntity dcOptimizer) {
		try {
			if (dcOptimizer != null) {
				if (checkValue.isStringNotEmpty(dcOptimizer.getRatedOutputIsc())) {
					return dcOptimizer.getRatedOutputIsc().replace(',', '.');
				} else {
					return "ISC Update Req";
				}
			} else {
				return "ISC Update Req";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "";
		}
	}
}
