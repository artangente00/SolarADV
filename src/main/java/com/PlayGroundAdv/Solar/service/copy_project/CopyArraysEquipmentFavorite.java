package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.InverterLibraryEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.ModuleLibraryEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerFavoritEntity;
import com.PlayGroundAdv.Solar.repositories.libraries.DcOptimizerFavoritRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InvertersFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleFavoritesRepository;

@Service
@Transactional
public class CopyArraysEquipmentFavorite {

	final ModuleFavoritesRepository moduleFavRepo;
	final InvertersFavoritesRepository inverterFavRepo;
	final DcOptimizerFavoritRepository converterFavRepo;

	public CopyArraysEquipmentFavorite(ModuleFavoritesRepository moduleFavRepo,
			InvertersFavoritesRepository inverterFavRepo, DcOptimizerFavoritRepository converterFavRepo) {
		super();
		this.moduleFavRepo = moduleFavRepo;
		this.inverterFavRepo = inverterFavRepo;
		this.converterFavRepo = converterFavRepo;
	}

	public void copyModuleFav(Cmodulev2 equipment, AuthentificationEntity user) {
		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.isDeleted())
					&& Boolean.FALSE.equals(moduleFavRepo
							.existsByAuthentificationEntityIdAndCmodulev2Id(user.getId(), equipment.getId()))) {
				ModuleLibraryEntity newFav = new ModuleLibraryEntity(user, equipment);
				moduleFavRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void copyInverterFav(Inverters equipment, AuthentificationEntity user) {
		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.isDeleted())
					&& Boolean.FALSE.equals(inverterFavRepo
							.existsByAuthentificationEntityIdAndInvertersId(user.getId(), equipment.getId()))) {
				InverterLibraryEntity newFav = new InverterLibraryEntity(user, equipment);
				inverterFavRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void copyConverterFav(DCOptimizerEntity equipment, AuthentificationEntity user) {
		try {
			if (equipment != null && !Boolean.TRUE.equals(equipment.getIsDeleted())
					&& Boolean.FALSE.equals(converterFavRepo
							.existsByOptimizerIdAndUserId(equipment.getId(), user.getId()))) {
				DCOptimizerFavoritEntity newFav = new DCOptimizerFavoritEntity(equipment, user);
				converterFavRepo.save(newFav);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
