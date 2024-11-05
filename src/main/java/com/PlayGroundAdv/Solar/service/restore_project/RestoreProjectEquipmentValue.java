package com.PlayGroundAdv.Solar.service.restore_project;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitArraysEntity;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;

@Service
@Transactional
public class RestoreProjectEquipmentValue {
	
	final InverterRepository inverterRepo;
	final ModuleRepository moduleRepo;
	final PermitArraysRepository projectArraysRepo;
	
	public RestoreProjectEquipmentValue(InverterRepository inverterRepo, ModuleRepository moduleRepo,
			PermitArraysRepository projectArraysRepo) {
		super();
		this.inverterRepo = inverterRepo;
		this.moduleRepo = moduleRepo;
		this.projectArraysRepo = projectArraysRepo;
	}
	
	public void checkPVModuleValue(Long idProject,String pvModule) {
		try {
			PermitArraysEntity project = projectArraysRepo.findByPermitEntityId(idProject);
			List<Cmodulev2> module =  moduleRepo.findByMakeAndModel(pvModule.split(":")[0],pvModule.split(":")[1]);
			if (!module.isEmpty()) {
				project.setPvModule(module.get(0));
				projectArraysRepo.save(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkFirstInverterValue(Long idProject,String inverter) {
		try {
			PermitArraysEntity project = projectArraysRepo.findByPermitEntityId(idProject);
			List<Inverters> inverters =  inverterRepo.findAllByMakeAndModel(inverter.split(":")[0],inverter.split(":")[1]);
			if (!inverters.isEmpty()) {
				project.setFirstInverter(inverters.get(0));
				projectArraysRepo.save(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkSecondInverterValue(Long idProject,String inverter) {
		try {
			PermitArraysEntity project = projectArraysRepo.findByPermitEntityId(idProject);
			List<Inverters> inverters =  inverterRepo.findAllByMakeAndModel(inverter.split(":")[0],inverter.split(":")[1]);
			if (!inverters.isEmpty()) {
				project.setSecondInverter(inverters.get(0));
				projectArraysRepo.save(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkThirdInverterValue(Long idProject,String inverter) {
		try {
			PermitArraysEntity project = projectArraysRepo.findByPermitEntityId(idProject);
			List<Inverters> inverters =  inverterRepo.findAllByMakeAndModel(inverter.split(":")[0],inverter.split(":")[1]);
			if (!inverters.isEmpty()) {
				project.setThirdInverter(inverters.get(0));
				projectArraysRepo.save(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkFourthInverterValue(Long idProject,String inverter) {
		try {
			PermitArraysEntity project = projectArraysRepo.findByPermitEntityId(idProject);
			List<Inverters> inverters =  inverterRepo.findAllByMakeAndModel(inverter.split(":")[0],inverter.split(":")[1]);
			if (!inverters.isEmpty()) {
				project.setFourthInverter(inverters.get(0));
				projectArraysRepo.save(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkFifthInverterValue(Long idProject,String inverter) {
		try {
			PermitArraysEntity project = projectArraysRepo.findByPermitEntityId(idProject);
			List<Inverters> inverters =  inverterRepo.findAllByMakeAndModel(inverter.split(":")[0],inverter.split(":")[1]);
			if (!inverters.isEmpty()) {
				project.setFifthInverter(inverters.get(0));
				projectArraysRepo.save(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
