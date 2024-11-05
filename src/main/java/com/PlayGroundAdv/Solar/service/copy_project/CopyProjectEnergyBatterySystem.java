package com.PlayGroundAdv.Solar.service.copy_project;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.ProjectBattery;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;

@Service
public class CopyProjectEnergyBatterySystem {

	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final CopyBatteryEquipmentFavorite copyBatteryEquipment;

	public CopyProjectEnergyBatterySystem(PermitEnergyBatterySystemRepository energyBatterySystemRepo,
			CopyBatteryEquipmentFavorite copyBatteryEquipment) {
		super();
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.copyBatteryEquipment = copyBatteryEquipment;
	}

	public void copyBatterySystem(Long idPermit, Long idNewPermit, AuthentificationEntity user,
			AuthentificationEntity userBy) {
		try {
			PermitEnergyBatterySystem permitEnergyBatterySystem = energyBatterySystemRepo.findByProjectId(idPermit);
			if (permitEnergyBatterySystem != null) {
				PermitEnergyBatterySystem newEnergyBatterySystem = energyBatterySystemRepo.findByProjectId(idNewPermit);
				newEnergyBatterySystem = cloneEnergyBatterySystem(newEnergyBatterySystem, permitEnergyBatterySystem);
				energyBatterySystemRepo.save(newEnergyBatterySystem);

				// Copy Favorites
				if (permitEnergyBatterySystem.getIdAts() != null) {
					copyBatteryEquipment.copyATSFav(permitEnergyBatterySystem.getIdAts(), user, userBy);
				}
				if (permitEnergyBatterySystem.getIdGenerator() != null) {
					copyBatteryEquipment.copyGeneratorFav(permitEnergyBatterySystem.getIdGenerator(), user, userBy);
				}
				if (permitEnergyBatterySystem.getIdAcDisconnect() != null) {
					copyBatteryEquipment.copyACDFav(permitEnergyBatterySystem.getIdAcDisconnect().getId(), user);
				}
				if (permitEnergyBatterySystem.getIdDcDisconnect() != null) {
					copyBatteryEquipment.copyDCDFav(permitEnergyBatterySystem.getIdDcDisconnect(), user);
				}
				if (permitEnergyBatterySystem.getBatteries() != null) {
					for (ProjectBattery b : permitEnergyBatterySystem.getBatteries()) {
						copyBatteryEquipment.copyBatteryFav(b.getBatteryId().getId(), user);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PermitEnergyBatterySystem cloneEnergyBatterySystem(PermitEnergyBatterySystem newEnergyBatterySystem,
			PermitEnergyBatterySystem permitEnergyBatterySystem) {

		if (newEnergyBatterySystem != null && permitEnergyBatterySystem != null) {
			newEnergyBatterySystem.setTypeGridTied(permitEnergyBatterySystem.getTypeGridTied());
			newEnergyBatterySystem.setAtsIncluded(permitEnergyBatterySystem.getAtsIncluded());
			newEnergyBatterySystem.setDcDisconnectIncluded(permitEnergyBatterySystem.getDcDisconnectIncluded());
			newEnergyBatterySystem.setAcDisconnectIncluded(permitEnergyBatterySystem.getAcDisconnectIncluded());
			newEnergyBatterySystem.setGeneratorIncluded(permitEnergyBatterySystem.getGeneratorIncluded());
			newEnergyBatterySystem.setRsdConnected(permitEnergyBatterySystem.getRsdConnected());
			newEnergyBatterySystem.setGeneratorStatus(permitEnergyBatterySystem.getGeneratorStatus());
			newEnergyBatterySystem.setFuelType(permitEnergyBatterySystem.getFuelType());
			newEnergyBatterySystem.setFuelDistributionPipeType(permitEnergyBatterySystem.getFuelDistributionPipeType());
			newEnergyBatterySystem
					.setFuelDistributionPipeTypeOther(permitEnergyBatterySystem.getFuelDistributionPipeTypeOther());
			newEnergyBatterySystem.setPipeSize(permitEnergyBatterySystem.getPipeSize());
			newEnergyBatterySystem.setPipeSizeOther(permitEnergyBatterySystem.getPipeSizeOther());
			newEnergyBatterySystem.setEssSpecificationComment(permitEnergyBatterySystem.getEssSpecificationComment());
			// A.B REV CR-3919-MOD-005
			newEnergyBatterySystem.setQtyAts(permitEnergyBatterySystem.getQtyAts());
			newEnergyBatterySystem.setQtySecondAts(permitEnergyBatterySystem.getQtySecondAts());
			newEnergyBatterySystem.setQtyAcd(permitEnergyBatterySystem.getQtyAcd());
			newEnergyBatterySystem.setQtySecondAcd(permitEnergyBatterySystem.getQtySecondAcd());
			if (permitEnergyBatterySystem.getIdAts() != null) {
				newEnergyBatterySystem.setIdAts(permitEnergyBatterySystem.getIdAts());
			}
			if (permitEnergyBatterySystem.getIdGenerator() != null) {
				newEnergyBatterySystem.setIdGenerator(permitEnergyBatterySystem.getIdGenerator());
			}
			if (permitEnergyBatterySystem.getIdAcDisconnect() != null) {
				newEnergyBatterySystem.setIdAcDisconnect(permitEnergyBatterySystem.getIdAcDisconnect());
			}
			if (permitEnergyBatterySystem.getIdDcDisconnect() != null) {
				newEnergyBatterySystem.setIdDcDisconnect(permitEnergyBatterySystem.getIdDcDisconnect());
			}
			if (permitEnergyBatterySystem.getBatteries() != null) {
				for (ProjectBattery b : permitEnergyBatterySystem.getBatteries()) {
					newEnergyBatterySystem.addBattery(b.getBatteryId(), b.getQuantity());
				}
			}
			// A.B REV CR-3919-MOD-005
			newEnergyBatterySystem.setIdSecondAts(permitEnergyBatterySystem.getIdSecondAts());
			newEnergyBatterySystem.setIdSecondAcDisconnect(permitEnergyBatterySystem.getIdSecondAcDisconnect());
		}
		return newEnergyBatterySystem;
	}
}
