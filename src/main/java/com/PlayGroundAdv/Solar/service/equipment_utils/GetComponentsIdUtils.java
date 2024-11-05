package com.PlayGroundAdv.Solar.service.equipment_utils;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.InverterLibraryEntity;
import com.PlayGroundAdv.Solar.entity.ModuleLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.libraries.ATS;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerFavoritEntity;
import com.PlayGroundAdv.Solar.entity.libraries.Generator;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ATSFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcComDiscoFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcOptimizerFavoritRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.GeneratorFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InvertersFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class GetComponentsIdUtils {

	final CheckValueTypesService checkValue;
	final ModuleRepository moduleRepo;
	final ModuleFavoritesRepository moduleFavRepo;
	final InverterRepository inverterRepo;
	final InvertersFavoritesRepository inverterFavRepo;
	final ConvertersRepository convertersRepo;
	final DcOptimizerFavoritRepository converterRepo;
	final RailRackingRepository railRackingRepo;
	final RailRackingFavoriteRepository railRackingFavRepo;
	final DcCombinerDiscoRepository dcdRepo;
	final DcComDiscoFavoriteRepository dcdFavRepo;
	final AcCombinerSLCRepository accRepo;
	final AcCombinerSLCsFavoritesRepository accFavRepo;
	final ACDisconnectFavRepository acdFavRepo;
	final PermitArraysRepository projectArraysRepo;
	final BatteryFavRepository batteryFavRepo;
	final ATSFavoriteRepository atsFavRepo;
	final GeneratorFavoriteRepository generatorFavRepo;

	public GetComponentsIdUtils(CheckValueTypesService checkValue, ModuleRepository moduleRepo,
			ModuleFavoritesRepository moduleFavRepo, InverterRepository inverterRepo,
			InvertersFavoritesRepository inverterFavRepo, ConvertersRepository convertersRepo,
			DcOptimizerFavoritRepository converterRepo, RailRackingRepository railRackingRepo,
			RailRackingFavoriteRepository railRackingFavRepo, DcCombinerDiscoRepository dcdRepo,
			DcComDiscoFavoriteRepository dcdFavRepo, AcCombinerSLCRepository accRepo,
			AcCombinerSLCsFavoritesRepository accFavRepo, ACDisconnectFavRepository acdFavRepo,
			PermitArraysRepository projectArraysRepo, BatteryFavRepository batteryFavRepo,
			ATSFavoriteRepository atsFavRepo, GeneratorFavoriteRepository generatorFavRepo) {
		super();
		this.checkValue = checkValue;
		this.moduleRepo = moduleRepo;
		this.moduleFavRepo = moduleFavRepo;
		this.inverterRepo = inverterRepo;
		this.inverterFavRepo = inverterFavRepo;
		this.convertersRepo = convertersRepo;
		this.converterRepo = converterRepo;
		this.railRackingRepo = railRackingRepo;
		this.railRackingFavRepo = railRackingFavRepo;
		this.dcdRepo = dcdRepo;
		this.dcdFavRepo = dcdFavRepo;
		this.accRepo = accRepo;
		this.accFavRepo = accFavRepo;
		this.acdFavRepo = acdFavRepo;
		this.projectArraysRepo = projectArraysRepo;
		this.batteryFavRepo = batteryFavRepo;
		this.atsFavRepo = atsFavRepo;
		this.generatorFavRepo = generatorFavRepo;
	}
	
	private Long returnOldValue(String oldValue) {
		if (checkValue.Equals(oldValue, "Fav Removed")) {
			return -1L;
		} else if (checkValue.Equals(oldValue, "Removed")) {
			return 0L;
		} else {
			return null;
		}
	}

	// M.A Get the rail racking of the project
	public Long getIdOfRailRackingEditProj(RailRacking rail, String railRaickingOldValue, Long idUser) {

		if (rail != null) {
			if (Boolean.TRUE.equals(rail.getIsDeleted())) {
				return 0L;
			} else if (Boolean.FALSE.equals(railRackingFavRepo.existsByRailRackingIdAndAuthentificationEntityId(rail.getId(), idUser))) {
				return -1L;
			} else {
				return rail.getId();
			}
		} else {
			return returnOldValue(railRaickingOldValue);
		}
		
	}

	// M.A Get the DC Combiner Disco of the project
	public Long getIdOfDcCombiner(DCCombinerDisconnectEntity dcCombo, String dcComboOldValue, Long idUser) {

		if (dcCombo != null) {
			if (Boolean.TRUE.equals(dcCombo.getIsDeleted())) {
				return 0L;
			} else if (Boolean.FALSE.equals(dcdFavRepo.existsByAuthentificationEntityIdAndDcCombinerDisconnectEntityId(idUser, dcCombo.getId()))) {
				return -1L;
			}else {
				return dcCombo.getId();
			}
		} else {
			return returnOldValue(dcComboOldValue);
		}
	}

	// M.A Get the DC Combiner Disco of the project
	public Long getIdOfACCombinerSLC(ACCombinerSLC acCombo, String acComboOldValue, Long idUser) {

		if (acCombo != null) {

			if (Boolean.TRUE.equals(acCombo.getIsDeleted())) {
				return 0L;
			} else if (Boolean.FALSE.equals(accFavRepo.existsByAcCombinerSlcIdAndAuthentificationEntityId(acCombo.getId(), idUser))) {
				return -1L;
			}else {
				return acCombo.getId();
			}
		} else {
			return returnOldValue(acComboOldValue);
		}
	}

	// A.B 09-03-2021 Get AC Disconnect
	public Long getACDisconnectId(ACDisconnect acd, Long idUser) {
		Long id = acd != null ? acd.getId() : null;
		if (id != null) {
			if (Boolean.TRUE.equals(acd.getIsDeleted())) {
				return -1L;
			} else if (Boolean.FALSE.equals(acdFavRepo.existsByAuthentificationEntityIdAndACDisconnectId(idUser, id))) {
				return 0L;
			}
		}
		return id;
	}

	// A.B 01-16 Test Romoved & Fav Removed
	public Long testInverterRemoved(Long inverterId, Long contractorId, Long permitId, Integer inv) {
		try {
			String inverterModelDeleted = inv == 1 ? projectArraysRepo.findFirstInverterModel(permitId)
					: inv == 2 ? projectArraysRepo.findFirstInverterModel(permitId)
							: inv == 3 ? projectArraysRepo.findSecondInverterModel(permitId)
									: inv == 4 ? projectArraysRepo.findFourthInverterModel(permitId)
											: inv == 5 ? projectArraysRepo.findFifthInverterModel(permitId) : "";

			boolean inverterDeleted = inverterRepo.findIfDeleted(inverterId);
			if (checkValue.Equals(inverterDeleted, true)
					|| checkValue.Equals(inverterModelDeleted, "Removed"))
				return -1L;
			else {
				InverterLibraryEntity inverterFav = inverterFavRepo
						.findOneByAuthentificationEntityIdAndInvertersId(contractorId, inverterId);
				if (inverterFav == null || checkValue.Equals(inverterModelDeleted, "Fav Removed"))
					return 0L;
			}
			return inverterId;
		} catch (Exception e) {
			e.printStackTrace();
			return inverterId;
		}
	}

	public Long testPVModuleRemoved(Long pvModuleId, Long contractorId, Long permitId) {
		try {
			String pvModuleModelDeleted = projectArraysRepo.findPvModuleModel(permitId);
			boolean pvModuleDeleted = moduleRepo.getModuleDeleted(pvModuleId);
			if (checkValue.Equals(pvModuleDeleted, true)
					|| checkValue.Equals(pvModuleModelDeleted, "Removed"))
				return -1L;
			else {
				ModuleLibraryEntity moduleFav = moduleFavRepo
						.findOneByAuthentificationEntityIdAndCmodulev2Id(contractorId, pvModuleId);
				if (moduleFav == null || checkValue.Equals(pvModuleModelDeleted, "Fav Removed"))
					return 0L;
			}
			return pvModuleId;
		} catch (Exception e) {
			e.printStackTrace();
			return pvModuleId;
		}
	}

	public Long testMicroInverterRemoved(Long microInverterId, Long contractorId, Long permitId) {
		try {
			String microInverterModelDeleted = projectArraysRepo.findMicroInverter(permitId);
			boolean inverterDeleted = inverterRepo.findIfDeleted(microInverterId);
			if (checkValue.Equals(inverterDeleted, true)
					|| checkValue.Equals(microInverterModelDeleted, "Removed"))
				return -1L;
			else {
				InverterLibraryEntity inverterFav = inverterFavRepo
						.findOneByAuthentificationEntityIdAndInvertersId(contractorId, microInverterId);
				if (inverterFav == null || checkValue.Equals(microInverterModelDeleted, "Fav Removed"))
					return 0L;
			}
			return microInverterId;
		} catch (Exception e) {
			e.printStackTrace();
			return microInverterId;
		}
	}

	public Long testDCOptimizerRemoved(Long microInverterId, Long contractorId, Long permitId) {
		try {
			String systemOptimizerDeleted = projectArraysRepo.findSystemOptimizerModelManufacturer(permitId);
			boolean inverterDeleted = convertersRepo.getConverterDeleted(microInverterId);
			if (checkValue.Equals(inverterDeleted, true)
					|| checkValue.Equals(systemOptimizerDeleted, "Removed")) {
				return 0L;
			} else {
				DCOptimizerFavoritEntity dcFav = converterRepo.findFirstByOptimizerIdAndUserId(microInverterId,
						contractorId);

				if (dcFav == null || checkValue.Equals(systemOptimizerDeleted, "Fav Removed")) {
					return -1L;
				}
			}
			return microInverterId;
		} catch (Exception e) {
			e.printStackTrace();
			return microInverterId;
		}
	}

	public Long testDCOptimizer(String dcOptimizer) {
		try {
			if (checkValue.Equals(dcOptimizer, "Fav Removed")) {
				return -1L;
			} else if (checkValue.Equals(dcOptimizer, "Removed")) {
				return 0L;
			} else {
				return convertersRepo.findByManufacturerAndModel(dcOptimizer.split(":")[0], dcOptimizer.split(":")[1])
						.getId();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	public Long testDcCombiner(String dcCombi) {
		try {
			if (checkValue.Equals(dcCombi, "Fav Removed")) {
				return -1L;
			} else if (checkValue.Equals(dcCombi, "Removed")) {
				return 0L;
			} else {
				return dcdRepo.findByManufacturerAndModel(dcCombi.split(":")[0], dcCombi.split(":")[1]).getId();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	public Long getBattery(Battery b, Long userId) {
		try {
			if (Boolean.TRUE.equals(b.isDeleted())) {
				return -1L;
			} else if (Boolean.FALSE
					.equals(batteryFavRepo.existsByAuthentificationEntityIdAndBatteryId(userId, b.getId()))) {
				return -2L;
			} else {
				return b.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	public Long getATS(ATS b, Long userId) {
		try {
			if (Boolean.TRUE.equals(b.getDeleted())) {
				return -1L;
			} else if (Boolean.FALSE.equals(atsFavRepo.existsByIdAtsIdAndIdUserId(b.getId(), userId))) {
				return 0L;
			} else {
				return b.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	public Long getGenerator(Generator b, Long userId) {
		try {
			if (Boolean.TRUE.equals(b.getDeleted())) {
				return -1L;
			} else if (Boolean.FALSE.equals(generatorFavRepo.existsByIdGeneratorIdAndIdUserId(b.getId(), userId))) {
				return 0L;
			} else {
				return b.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

}
