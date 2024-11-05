package com.PlayGroundAdv.Solar.service.copy_project;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.ACDisconnectFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.BatteryFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.DcCombinerorDiscFavoriteEntity;
import com.PlayGroundAdv.Solar.entity.libraries.ATS;
import com.PlayGroundAdv.Solar.entity.libraries.Generator;
import com.PlayGroundAdv.Solar.model.libraries.CompoentFavDto;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcComDiscoFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.service.libraries.ATSService;
import com.PlayGroundAdv.Solar.service.libraries.GeneratorService;

@Service
public class CopyBatteryEquipmentFavorite {
	
	final ATSService atsService;
	final GeneratorService generatoeService;
	final DcComDiscoFavoriteRepository dcdFavoriteRepo;
	final DcCombinerDiscoRepository dcdRepo;
	final ACDisconnectRepository acdRepo;
	final ACDisconnectFavRepository acdFavoriteRepo;
	final BatteryRepository batteryRepo;
	final BatteryFavRepository batteryFavoriteRepo;

	public CopyBatteryEquipmentFavorite(ATSService atsService, GeneratorService generatoeService,
			DcComDiscoFavoriteRepository dcdFavoriteRepo, DcCombinerDiscoRepository dcdRepo,
			ACDisconnectRepository acdRepo, ACDisconnectFavRepository acdFavoriteRepo, BatteryRepository batteryRepo,
			BatteryFavRepository batteryFavoriteRepo) {
		super();
		this.atsService = atsService;
		this.generatoeService = generatoeService;
		this.dcdFavoriteRepo = dcdFavoriteRepo;
		this.dcdRepo = dcdRepo;
		this.acdRepo = acdRepo;
		this.acdFavoriteRepo = acdFavoriteRepo;
		this.batteryRepo = batteryRepo;
		this.batteryFavoriteRepo = batteryFavoriteRepo;
	}

	public void copyATSFav(ATS equipment, AuthentificationEntity user, AuthentificationEntity userBy) {
		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.getDeleted())
					&& Boolean.FALSE.equals(atsService.isFavorite(equipment, user.getId()))) {
				CompoentFavDto request = new CompoentFavDto( user.getId(), equipment.getId(),  userBy.getId());
				atsService.addToFav(request);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public void copyGeneratorFav(Generator equipment, AuthentificationEntity user, AuthentificationEntity userBy) {
		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.getDeleted())
					&& Boolean.FALSE.equals(generatoeService.isFavorite(equipment, user.getId()))) {
				CompoentFavDto request = new CompoentFavDto( user.getId(), equipment.getId(),  userBy.getId());
				generatoeService.addToFav(request);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	public void copyDCDFav(DCCombinerDisconnectEntity equipment, AuthentificationEntity user) {
		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.getIsDeleted())
					&& Boolean.FALSE
							.equals(dcdFavoriteRepo.existsByAuthentificationEntityIdAndDcCombinerDisconnectEntityId(
									user.getId(), equipment.getId()))) {
				DcCombinerorDiscFavoriteEntity newFav = new DcCombinerorDiscFavoriteEntity(user, equipment);
				dcdFavoriteRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyACDFav(Long idACD, AuthentificationEntity user) {
		try {
			if (Boolean.FALSE
					.equals(acdFavoriteRepo.existsByAuthentificationEntityIdAndACDisconnectId(user.getId(), idACD))) {
				ACDisconnect equipment = acdRepo.findById(idACD).orElse(new ACDisconnect());
				ACDisconnectFavLibraryEntity newFav = new ACDisconnectFavLibraryEntity(user, equipment);
				acdFavoriteRepo.save(newFav);
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
