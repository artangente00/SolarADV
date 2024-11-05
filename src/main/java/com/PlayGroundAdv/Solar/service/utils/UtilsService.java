package com.PlayGroundAdv.Solar.service.utils;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetComponentsIdUtils;

@Service
@Transactional
//F.S 5-29-20 Service created for modification that will only run once and never been used again
public class UtilsService {

	final PathRepository pathRepo;
	final CheckValueTypesService checkValue;
	final PermitRepository projectRepo;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final BatteryRepository batteryRepo;
	final PermitAdditionalInfoRepository permitAdditionalInfoRepo;
	final GetComponentsIdUtils getComponentsId;

	public UtilsService(PathRepository pathRepo, CheckValueTypesService checkValue, PermitRepository projectRepo,
			PermitEnergyBatterySystemRepository energyBatterySystemRepo, BatteryRepository batteryRepo,
			PermitAdditionalInfoRepository permitAdditionalInfoRepo, GetComponentsIdUtils getComponentsId) {
		super();
		this.pathRepo = pathRepo;
		this.checkValue = checkValue;
		this.projectRepo = projectRepo;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.batteryRepo = batteryRepo;
		this.permitAdditionalInfoRepo = permitAdditionalInfoRepo;
		this.getComponentsId = getComponentsId;
	}

	public String updateProjects(ComponentPageRequest request) {
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
		Page<PermitEntity> list = projectRepo.findAll(pageable);
		for (PermitEntity p : list) {
			try {
				PermitEnergyBatterySystem permitEnergyBatterySystem = energyBatterySystemRepo
						.findByProjectId(p.getId());
				if (permitEnergyBatterySystem == null) {
					permitEnergyBatterySystem = new PermitEnergyBatterySystem();
					permitEnergyBatterySystem.setProject(p);
					PermitAdditionalInfoEntityResult permitAdditionalInfoEntityResult = permitAdditionalInfoRepo
							.getPermitAdditionalInfoEntityResult(p.getId());
					if (checkValue.isNumericNotZero(permitAdditionalInfoEntityResult.getBattery())) {
						Battery b;

						b = batteryRepo.findById(Long.valueOf(permitAdditionalInfoEntityResult.getBattery()))
								.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :"
										+ permitAdditionalInfoEntityResult.getBattery()));

						permitEnergyBatterySystem.setBatteries(new ArrayList<>());
						permitEnergyBatterySystem.addBattery(b,
								permitAdditionalInfoEntityResult.getQuantityBatteries());
					}
					energyBatterySystemRepo.save(permitEnergyBatterySystem);
					System.out.println("Done  "+p.getHomeOwnName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Done";
	}

}
