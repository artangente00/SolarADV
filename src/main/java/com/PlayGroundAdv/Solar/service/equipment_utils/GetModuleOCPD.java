package com.PlayGroundAdv.Solar.service.equipment_utils;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
@Service
@Transactional
public class GetModuleOCPD {

	final ModuleRepository moduleRepo;
	final CheckValueTypesService checkValueTypes;
	
	public GetModuleOCPD(ModuleRepository moduleRepo, CheckValueTypesService checkValueTypes) {
		super();
		this.moduleRepo = moduleRepo;
		this.checkValueTypes = checkValueTypes;
	}

	// C.I: 15/01/2020 :
	public double getModuleOcpdNumber(Long moduleId) {
		try {
			double ModuleOcpdNumber = 0;
			if (moduleId != null) {
				String iacmax = moduleRepo.getModuleIacmax(moduleId);
				ModuleOcpdNumber = checkValueTypes.NotEquals(iacmax, "")
						? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
						: 0;
			}
			return ModuleOcpdNumber * 1.25;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
