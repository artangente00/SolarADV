package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.BatteryFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.TiltLegs;
import com.PlayGroundAdv.Solar.entity.TiltLegsFavLibraryEntity;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsRepository;

@Service
@Transactional
public class CopyAdditionalInfoEquipmentFavorite {

	final TiltLegsRepository tiltLegsRepo;
	final TiltLegsFavoritesRepository tiltLegsFavoriteRepo;
	final BatteryRepository batteryRepo;
	final BatteryFavRepository batteryFavoriteRepo;

	public CopyAdditionalInfoEquipmentFavorite(TiltLegsRepository tiltLegsRepo,
			TiltLegsFavoritesRepository tiltLegsFavoriteRepo, BatteryRepository batteryRepo,
			BatteryFavRepository batteryFavoriteRepo) {
		super();
		this.tiltLegsRepo = tiltLegsRepo;
		this.tiltLegsFavoriteRepo = tiltLegsFavoriteRepo;
		this.batteryRepo = batteryRepo;
		this.batteryFavoriteRepo = batteryFavoriteRepo;
	}

	public void copyTiltLegsFav(Long idTiltLeg, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE.equals(
					tiltLegsFavoriteRepo.existsByAuthentificationEntityIdAndTiltLegsId(user.getId(), idTiltLeg))) {
				TiltLegs equipment = tiltLegsRepo.findById(idTiltLeg).orElse(new TiltLegs());
				TiltLegsFavLibraryEntity newFav = new TiltLegsFavLibraryEntity(user, equipment);
				tiltLegsFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyBatteryFav(Long idBattery, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE.equals(
					batteryFavoriteRepo.existsByAuthentificationEntityIdAndBatteryId(user.getId(), idBattery))) {
				Battery equipment = batteryRepo.findById(idBattery).orElse(new Battery());
				BatteryFavLibraryEntity newFav = new BatteryFavLibraryEntity(user, equipment);
				batteryFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
