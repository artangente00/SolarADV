package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import java.util.Collections;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.equipment_utils.ProjectNumberOfMicroInverter;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
@Service
public class ModuleQtyOnBranchCicuit {
	final CheckValueTypesService checkValue;
	final ProjectNumberOfMicroInverter projectNumberOfMicroInverter;
	public ModuleQtyOnBranchCicuit(CheckValueTypesService checkValue, ProjectNumberOfMicroInverter projectNumberOfMicroInverter) {
		super();
		this.checkValue = checkValue;
		this.projectNumberOfMicroInverter = projectNumberOfMicroInverter;
	}

	public PlansetUtils getModuleQty(PermitArrayEntityResultSecond permitArraysEntityResult,
			Inverters microInverterInfo) {

		try {
			PlansetUtils plansetUtils = getQty(permitArraysEntityResult);
			if (permitArraysEntityResult != null && !plansetUtils.getStringQty().isEmpty()) {
				plansetUtils.setMaxBranchMods(Collections.max(plansetUtils.getStringQty()));
				plansetUtils.setAcNumbUngroundCond(3 * plansetUtils.getStringQty().size());
				plansetUtils.setModuleQty(plansetUtils.getStringQty().stream().mapToInt(a -> a).sum());
				plansetUtils.setModulePerMicroInverter(plansetUtils.getStringQty().stream().mapToInt(a -> a).sum());
				if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")) {
					plansetUtils.setModulePerMicroInverter(projectNumberOfMicroInverter
							.getNumberMicroInverter(permitArraysEntityResult, microInverterInfo));
					plansetUtils.setMaxBranchMods(projectNumberOfMicroInverter.getMAXNumberMicroInverter(permitArraysEntityResult,
							microInverterInfo)); 
				}
			}
			return plansetUtils;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new PlansetUtils();
		}

	}
	
	public PlansetUtils getQty(PermitArrayEntityResultSecond permitArraysEntityResult) {

		try {
			PlansetUtils plansetUtils = new PlansetUtils();
			if (permitArraysEntityResult != null) {
				if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitOne())) {
					plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitOne()));
					plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdOne());
					if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitTwo())) {
						plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTwo()));
						plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdTwo());
						if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitThree())) {
							plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitThree()));
							plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdThree());
							if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitFour())) {
								plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFour()));
								plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdFour());
								if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitFive())) {
									plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitFive()));
									plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdFive());
									if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitSix())) {
										plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSix()));
										plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdSix());
										if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitSeven())) {
											plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitSeven()));
											plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdSeven());
											if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitEight())) {
												plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEight()));
												plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdEight());
												if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitNine())) {
													plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitNine()));
													plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdNine());
													if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitTen())) {
														plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTen()));
														plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdTen());
														if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitEleven())) {
															plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitEleven()));
															plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdEleven());
															if (checkValue.isNumericNotZero(permitArraysEntityResult.getNumberModulesACCircuitTweleve())) {
																plansetUtils.getStringQty().add(Integer.parseInt(permitArraysEntityResult.getNumberModulesACCircuitTweleve()));
																plansetUtils.getStringStringE003().add(permitArraysEntityResult.getOcpdTwelve());
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				plansetUtils.setNumberOfBranchCircuit(plansetUtils.getStringQty().size());
				if (!plansetUtils.getStringQty().isEmpty()) {
					plansetUtils.setModuleQty(plansetUtils.getStringQty().stream().mapToInt(a -> a).sum());
				}
			}
			
			return plansetUtils;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new PlansetUtils();
		}

	}
	
}
