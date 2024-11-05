package com.PlayGroundAdv.Solar.service.project;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.SearchOptionsList;
import com.PlayGroundAdv.Solar.repositories.libraries.ATSRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ElectricalUtilityRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.EngineersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsRepository;
import com.PlayGroundAdv.Solar.service.libraries.ModuleService;
import com.PlayGroundAdv.Solar.service.libraries.InverterService;

@Service
public class SearchProjectService {

	final InverterRepository inverterRepo;
	final ModuleRepository moduleRepo;
	final InverterService inverterService;
	final ModuleService moduleService;
	final RailRackingRepository railRackingRepo;
	final RoofAttachmentsRepository roofAttachmentsRepo;
	final FlashingRepository flashingRepo;
	final LeasePPAMeterRepository leasePPAMeterRepo;
	final BatteryRepository batteryRepo;
	final TiltLegsRepository tiltLegsRepo;
	final ATSRepository atsRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	final ElectricalUtilityRepository electricalUtilityRepo;
	final EngineersRepository engineersRepo;

	public SearchProjectService(InverterRepository inverterRepo, ModuleRepository moduleRepo,
			InverterService inverterService, ModuleService moduleService,
			RailRackingRepository railRackingRepo, RoofAttachmentsRepository roofAttachmentsRepo,
			FlashingRepository flashingRepo, LeasePPAMeterRepository leasePPAMeterRepo, BatteryRepository batteryRepo,
			TiltLegsRepository tiltLegsRepo, ATSRepository atsRepo, RoofMaterialTypeRepository roofMaterialTypeRepo,
			ElectricalUtilityRepository electricalUtilityRepo, EngineersRepository engineersRepo) {
		super();
		this.inverterRepo = inverterRepo;
		this.moduleRepo = moduleRepo;
		this.inverterService = inverterService;
		this.moduleService = moduleService;
		this.railRackingRepo = railRackingRepo;
		this.roofAttachmentsRepo = roofAttachmentsRepo;
		this.flashingRepo = flashingRepo;
		this.leasePPAMeterRepo = leasePPAMeterRepo;
		this.batteryRepo = batteryRepo;
		this.tiltLegsRepo = tiltLegsRepo;
		this.atsRepo = atsRepo;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
		this.electricalUtilityRepo = electricalUtilityRepo;
		this.engineersRepo = engineersRepo;
	}

	public SearchOptionsList getSearchList() {
		try {
			SearchOptionsList l = new SearchOptionsList();
			l.setRoofMaterialTypeList(roofMaterialTypeRepo.getAllTypes());
			l.setElectricalCompany(electricalUtilityRepo.getUtilityList());
			l.setInverters(inverterRepo.getAllModels());
			l.setModules(moduleRepo.getAllModels());
//			l.setInverters(inverterService.getInverterList(new ComponentPageRequest(100, 0)));
//			l.setModules(moduleService.getModuleList(new ComponentPageRequest(2000, 0)));
			l.setRailToRoof(roofAttachmentsRepo.getAllModels());
			l.setRailRackingList(railRackingRepo.getAllModels());
			l.setBatteryList(batteryRepo.getAllModels());
			l.setTiltLegsList(tiltLegsRepo.getAllModels());
			l.setAtsList(atsRepo.getAllModels());
			l.setEngineersList(engineersRepo.nonDeletedEngineersContactAndState());
			return l;
		} catch (Exception e) {
			return new SearchOptionsList();
		}
	}
}
