package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitAdditionalInfoEntity;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class CopyAdditionalInfo {

	final PermitAdditionalInfoRepository additionalInfoRepo;
	final CheckValueTypesService checkValue;
	final CopyAdditionalInfoEquipmentFavorite copyAdditionalInfoFav;
	
	public CopyAdditionalInfo(PermitAdditionalInfoRepository additionalInfoRepo, CheckValueTypesService checkValue,
			CopyAdditionalInfoEquipmentFavorite copyAdditionalInfoFav) {
		super();
		this.additionalInfoRepo = additionalInfoRepo;
		this.checkValue = checkValue;
		this.copyAdditionalInfoFav = copyAdditionalInfoFav;
	}

	public void copyAdditionalInfo(Long idPermit, Long idNewPermit, AuthentificationEntity user) {
		try {
			PermitAdditionalInfoEntity additionalInfo = additionalInfoRepo.findByPermitEntityId(idPermit);
			PermitAdditionalInfoEntity newAdditionalInfo =  additionalInfoRepo.findByPermitEntityId(idNewPermit);
			newAdditionalInfo = clonePermitAdditionalInfoEntity(newAdditionalInfo, additionalInfo);
			additionalInfoRepo.save(newAdditionalInfo);
			
			// Copy tilt legs Favorites
			if (additionalInfo != null && checkValue.isNumericNotZero(additionalInfo.getTiltLegsMod())) {
				copyAdditionalInfoFav.copyTiltLegsFav(Long.valueOf(additionalInfo.getTiltLegsMod()),user);
			}
			// Copy Battery Favorites
			if (additionalInfo != null && checkValue.isNumericNotZero(additionalInfo.getBattery())) {
				copyAdditionalInfoFav.copyBatteryFav(Long.valueOf(additionalInfo.getBattery()),user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PermitAdditionalInfoEntity clonePermitAdditionalInfoEntity(
			PermitAdditionalInfoEntity newPermitAdditionalInfoEntity,
			PermitAdditionalInfoEntity permitAdditionalInfoEntity) {

		if (newPermitAdditionalInfoEntity != null && permitAdditionalInfoEntity != null) {
			newPermitAdditionalInfoEntity.setBattery(permitAdditionalInfoEntity.getBattery());
			newPermitAdditionalInfoEntity.setFormatSize(permitAdditionalInfoEntity.getFormatSize());
			newPermitAdditionalInfoEntity.setBatteryStorage(permitAdditionalInfoEntity.getBatteryStorage());
			newPermitAdditionalInfoEntity.setTiltLegs(permitAdditionalInfoEntity.getTiltLegs());
			newPermitAdditionalInfoEntity.setTiltLegsMod(permitAdditionalInfoEntity.getTiltLegsMod());
			newPermitAdditionalInfoEntity.setGridTiedOrStandalone(permitAdditionalInfoEntity.getGridTiedOrStandalone());
			newPermitAdditionalInfoEntity.setQuantityBatteries(permitAdditionalInfoEntity.getQuantityBatteries());
			newPermitAdditionalInfoEntity.setInformationCovered(permitAdditionalInfoEntity.getInformationCovered());
			newPermitAdditionalInfoEntity
					.setRequiredElectricalStamp(permitAdditionalInfoEntity.getRequiredElectricalStamp());

		}

		return newPermitAdditionalInfoEntity;

	}
}
