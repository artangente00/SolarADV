package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class CircuitEnvironment {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;

	public CircuitEnvironment(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
	}

	public SystemEnvironment getCircuitEnvironment(PermitArrayEntityResultSecond permitArraysEntityResult,
			PermitLayoutEntity permitLayoutEntity, UserSettingEntity userSetting, String necCode) {

		SystemEnvironment systemEnvironment = new SystemEnvironment();
		try {
			String openFrame = "The carport/patio cover will be open frame construction";
			String includeRoofingMaterial = "The carport/patio cover will include roofing material under the modules";

			if (Boolean.TRUE.equals(permitArraysEntityResult.getGroundMounted())
					|| Boolean.TRUE.equals(ciucuitOpenAir(permitArraysEntityResult,
							Boolean.TRUE.equals(permitArraysEntityResult.getCircuitUnderGround()), openFrame))) {

				systemEnvironment.setSystemCircuitEnvironment("OPEN AIR");
				systemEnvironment.setIsThereGroundOrPole(true);

			} else if (Boolean.TRUE.equals(ciucuitOpenAir(permitArraysEntityResult,
					!checkValue.NotEquals(permitArraysEntityResult.getCircuitUnderGround(), false), openFrame))) {

				systemEnvironment.setSystemCircuitEnvironment("OPEN AIR");

			} else if (Boolean.TRUE.equals(ciucuitOpenAir(permitArraysEntityResult,
					Boolean.TRUE.equals(permitArraysEntityResult.getCircuitUnderGround()), includeRoofingMaterial))) {

				systemEnvironment.setSystemCircuitEnvironment("ROOFTOP");

			} else if ((Boolean.TRUE.equals(permitArraysEntityResult.getRoofMounted()) || (carpotOrPatioMounted(
					permitArraysEntityResult)
					&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(), includeRoofingMaterial)
					&& ((Boolean.TRUE.equals(permitArraysEntityResult.getFreeStanding())
							&& Boolean.FALSE.equals(permitArraysEntityResult.getCircuitUnderGround()))
							|| (Boolean.TRUE.equals(permitArraysEntityResult.getAttachedToExtWal())
									|| Boolean.TRUE.equals(permitArraysEntityResult.getAttachedToFascia())
									|| Boolean.TRUE.equals(permitArraysEntityResult.getAttachedToSkylifts())))))) {

				systemEnvironment.setSystemCircuitEnvironment("ROOFTOP");
				systemEnvironment.setIsRoofMounted(true);

			}

			if (checkValue.Equals(permitLayoutEntity.getConduitRun(), "Attic")) {
				systemEnvironment.setCircuitEnvironment("ATTIC");
			} else {
				systemEnvironment.setCircuitEnvironment("ROOFTOP");
			}
			
			// A.B 11/04/2021 CR-3908
			if(systemEnvironment.getCircuitEnvironment().equals("ROOFTOP") || systemEnvironment.getCircuitEnvironment().equals("ATTIC")) {
				setTempAdderSpec(userSetting, systemEnvironment.getCircuitEnvironment(), necCode, permitLayoutEntity.getShowConduitRoofAsHeight(), systemEnvironment);
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return systemEnvironment;
	}

	private Boolean carpotOrPatioMounted(PermitArrayEntityResultSecond permitArraysEntityResult) {
		try {
			return Boolean.TRUE.equals(permitArraysEntityResult.getCarportMounted())
					|| Boolean.TRUE.equals(permitArraysEntityResult.getPatioMounted());
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return false;
		}
	}

	private Boolean ciucuitOpenAir(PermitArrayEntityResultSecond permitArraysEntityResult, Boolean circuitUnderGround,
			String roofFrame) {
		try {
			return carpotOrPatioMounted(permitArraysEntityResult)
					&& Boolean.TRUE.equals(permitArraysEntityResult.getFreeStanding()) && circuitUnderGround
					&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(), roofFrame);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return false;
		}
	}

	private void setTempAdderSpec(UserSettingEntity userSetting, String circuitEnvironment, String necCode, Boolean showConduitRoofAsHeight, SystemEnvironment systemEnvironment) {
		try {
			if(circuitEnvironment.equals("ROOFTOP") || circuitEnvironment.equals("ATTIC")) {
				//A.B 07-12-2022 REV-CR-3908-MOD-001
				if(!checkValue.isStringNotEmpty(necCode) || (checkValue.isStringNotEmpty(necCode) && !necCode.equals("2005") && !necCode.equals("2011") && !necCode.equals("2014"))) {
					systemEnvironment.setHighAboveRoof(">7/8\"");
					systemEnvironment.setTempAdder("0");
				}else {
					if(circuitEnvironment.equals("ROOFTOP")) {
						systemEnvironment.setHighAboveRoof("0.5\" – 3.5\"");
					}else {
						systemEnvironment.setHighAboveRoof("IN ATTIC");
					} 
					if(Boolean.TRUE.equals(showConduitRoofAsHeight)) {
						systemEnvironment.setTempAdder("33");
					}else {
						if(circuitEnvironment.equals("ROOFTOP")) {
							systemEnvironment.setTempAdder("22");
						}else {
							// I.C 10/21/2019 CR 2972
							systemEnvironment.setTempAdder(userSetting.getAtticTemperatureAdder());
						}
					}
				}
			}else {
				systemEnvironment.setHighAboveRoof("N/A");
				systemEnvironment.setTempAdder("0");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
	}
	
	public String getHighAboveRoof(String circuitEnvironment, String necCode, String state) {
		try {
			if(circuitEnvironment != null && (circuitEnvironment.equals("ROOFTOP") || circuitEnvironment.equals("ATTIC"))) {
				//A.B 07-12-2022 REV-CR-3908-MOD-001
				if(!checkValue.isStringNotEmpty(necCode) || (checkValue.isStringNotEmpty(necCode) && !necCode.equals("2005") && !necCode.equals("2011") && !necCode.equals("2014"))) {	//A.B 04-11-2022 REV-CR-3908-MOD-001
					return circuitEnvironment.equals("ROOFTOP") ? ">7/8\"" : "IN ATTIC";
				}else {
					if(checkValue.Equals(state, "CA") && circuitEnvironment.equals("ROOFTOP")) {
						return ">7/8\"";
					}else {
						if(circuitEnvironment.equals("ROOFTOP")) {
							return "0.5\" – 3.5\"";
						}else {
							return "IN ATTIC";
						} 
					}
				}
			}else {
				return "N/A";
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "0.5\" – 3.5\"";
		}
	}
	
	public String getTempAdder(String circuitEnvironment, String necCode, String state, UserSettingEntity userSetting, Boolean showConduitRoofAsHeight) {
		try {
			if(circuitEnvironment != null && (circuitEnvironment.equals("ROOFTOP") || circuitEnvironment.equals("ATTIC"))) {
				//A.B 07-12-2022 REV-CR-3908-MOD-001
				if(!checkValue.isStringNotEmpty(necCode) || (checkValue.isStringNotEmpty(necCode) && !necCode.equals("2005") && !necCode.equals("2011") && !necCode.equals("2014"))) {
					return "0";
				}else {
					if(Boolean.TRUE.equals(showConduitRoofAsHeight)) {
						return "33";
					}else {
						if(checkValue.Equals(state, "CA") && circuitEnvironment.equals("ROOFTOP")) {
							return "0";
						}else if(circuitEnvironment.equals("ROOFTOP")) {
							return "22";
						}else {
							// I.C 10/21/2019 CR 2972
							return userSetting != null ? userSetting.getAtticTemperatureAdder() : "0";
						}
					}
				}
			}else {
				return "0";
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "22";
		}
	}

}
