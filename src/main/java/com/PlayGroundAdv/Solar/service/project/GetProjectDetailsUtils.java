package com.PlayGroundAdv.Solar.service.project;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetProjectDetailsUtils {

	final CheckValueTypesService checkValue;
	
	public GetProjectDetailsUtils(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	public Boolean isStringOrOptimizerProject(String inverterTechnology) {
		return inverterTechnology != null && (inverterTechnology.equals("System Optimizer") || inverterTechnology.equals("Neither"));
	}
	
	public Boolean isMicroOrAcModuleProject(String inverterTechnology) {
		return inverterTechnology != null && (inverterTechnology.equals("Micro Inverter") || inverterTechnology.equals("AC Modules"));
	}
	
	public String getProjectName(PermitEntity permit) {
		try {
			if (!checkValue.isStringNotEmpty(permit.getProjectName())) {
				if (checkValue.isStringNotEmpty(permit.getHomeOwnLastName())) {
					return checkValue.removeSpecialChar(permit.getHomeOwnLastName()) + ", " + checkValue.removeSpecialChar(permit.getHomeOwnName());
				} else {
					return checkValue.removeSpecialChar(permit.getHomeOwnName());
				}

			}else {
				return checkValue.removeSpecialChar(permit.getProjectName());
			}
		} catch (Exception e) {
			return "";
		}
		
	}
}
