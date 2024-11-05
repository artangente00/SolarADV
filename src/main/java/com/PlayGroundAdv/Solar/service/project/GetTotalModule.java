package com.PlayGroundAdv.Solar.service.project;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleQtyOnBranchCicuit;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleQtyOnStringInverter;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetTotalModule {

	final PermitArraysRepository arraysRepo;
	final CheckValueTypesService checkValue;
	final ModuleQtyOnStringInverter moduleQtyInv;
	final ModuleQtyOnBranchCicuit moduleQtyMicro;
	
	public GetTotalModule(PermitArraysRepository arraysRepo, CheckValueTypesService checkValue,
			ModuleQtyOnStringInverter moduleQtyInv, ModuleQtyOnBranchCicuit moduleQtyMicro) {
		super();
		this.arraysRepo = arraysRepo;
		this.checkValue = checkValue;
		this.moduleQtyInv = moduleQtyInv;
		this.moduleQtyMicro = moduleQtyMicro;
	}

	public Integer getModuleQty(Long permitId){
		try {
			PermitArrayEntityResultSecond permitArrays = arraysRepo.getPermitArrayEntityResultSecond(permitId);
			
			if (permitArrays != null
					&& (checkValue.Equals(permitArrays.getDeviceToIncorporate(), "Neither")
							|| checkValue.Equals(permitArrays.getDeviceToIncorporate(), "System Optimizer"))) {
				PlansetUtils utils = moduleQtyInv.getModuleQty(permitArrays);
				return utils.getModuleQty();
			} else {
				PlansetUtils utils = moduleQtyMicro.getQty(permitArrays);
				return utils.getModuleQty();
			}
		} catch (Exception e) {
			return null;
		}
		
	}
}
