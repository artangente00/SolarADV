package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitAdvEntity;
import com.PlayGroundAdv.Solar.entity.PermitEngineerEntity;
import com.PlayGroundAdv.Solar.entity.PermitWeatherEntity;
import com.PlayGroundAdv.Solar.repositories.PermitAdvInputsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEngineerRepository;
import com.PlayGroundAdv.Solar.repositories.PermitWeatherRepository;

@Service
@Transactional
public class CopyADVInputs {

	final PermitAdvInputsRepository advInputsRepo;
	final PermitWeatherRepository weatherRepo;
	final PermitEngineerRepository engineerRepo;

	public CopyADVInputs(PermitAdvInputsRepository advInputsRepo, PermitWeatherRepository weatherRepo,
			PermitEngineerRepository engineerRepo) {
		super();
		this.advInputsRepo = advInputsRepo;
		this.weatherRepo = weatherRepo;
		this.engineerRepo = engineerRepo;
	}

	public void copyADVInputs(Long idPermit, Long idNewPermit) {
		try {
			// Copy Weather
			PermitWeatherEntity weatherEntity = weatherRepo.findByPermitEntityId(idPermit);
			PermitWeatherEntity newWeatherEntity = weatherRepo.findByPermitEntityId(idNewPermit);
			newWeatherEntity = clonePermitWeatherEntity(newWeatherEntity, weatherEntity);
			weatherRepo.save(newWeatherEntity);

			// Copy ADV
			PermitAdvEntity advEntity = advInputsRepo.findByPermitEntityId(idPermit);
			PermitAdvEntity newAdvEntity = advInputsRepo.findByPermitEntityId(idNewPermit);
			newAdvEntity = clonePermitAdvEntity(newAdvEntity, advEntity);
			advInputsRepo.save(newAdvEntity);

			// Copy Engineer
			PermitEngineerEntity engineer = engineerRepo.findByPermitEntityId(idPermit);
			PermitEngineerEntity newEngineer = engineerRepo.findByPermitEntityId(idNewPermit);
			newEngineer = clonePermitEngineerEntity(newEngineer, engineer);
			engineerRepo.save(newEngineer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PermitAdvEntity clonePermitAdvEntity(PermitAdvEntity newPermitAdvEntity, PermitAdvEntity permitAdvEntity) {

		if (newPermitAdvEntity != null && permitAdvEntity != null) {
			newPermitAdvEntity.setWindSpeed(permitAdvEntity.getWindSpeed());
			newPermitAdvEntity.setWindSpeedOther(permitAdvEntity.getWindSpeedOther());
			newPermitAdvEntity.setSnowLoad(permitAdvEntity.getSnowLoad());
			newPermitAdvEntity.setSnowLoadOther(permitAdvEntity.getSnowLoadOther());
			newPermitAdvEntity.setGoogleImage(permitAdvEntity.getGoogleImage());
			newPermitAdvEntity.setMapImage(permitAdvEntity.getMapImage());
			newPermitAdvEntity.setpVRailQte(permitAdvEntity.getpVRailQte());
			newPermitAdvEntity.setpVRailLength(permitAdvEntity.getpVRailLength());
			newPermitAdvEntity.setStanchionQte(permitAdvEntity.getStanchionQte());
			newPermitAdvEntity.setStanchionLength(permitAdvEntity.getStanchionLength());
			newPermitAdvEntity.setSpliceQte(permitAdvEntity.getSpliceQte());
			newPermitAdvEntity.setSpliceLength(permitAdvEntity.getSpliceLength());
			newPermitAdvEntity.setS200Qte(permitAdvEntity.getS200Qte());
			newPermitAdvEntity.setS200Length(permitAdvEntity.getS200Length());
			newPermitAdvEntity.setPv1(permitAdvEntity.getPv1());
			newPermitAdvEntity
					.setCustomersServiceAgreementIDNumber(permitAdvEntity.getCustomersServiceAgreementIDNumber());
			newPermitAdvEntity.setCustomersRateSchedule(permitAdvEntity.getCustomersRateSchedule());
			newPermitAdvEntity.setEngineeringFirm(permitAdvEntity.getEngineeringFirm());
			newPermitAdvEntity.setCustomersAccountNumber(permitAdvEntity.getCustomersAccountNumber());
			newPermitAdvEntity.setCustomerName(permitAdvEntity.getCustomerName());
			newPermitAdvEntity.setModuleLayout(permitAdvEntity.getModuleLayout());
			newPermitAdvEntity.setModuleLayoutOther(permitAdvEntity.getModuleLayoutOther());
			newPermitAdvEntity.setBracedUnbraced(permitAdvEntity.getBracedUnbraced());
			newPermitAdvEntity.setThicknessOfPipe(permitAdvEntity.getThicknessOfPipe());
			newPermitAdvEntity.setThicknessOfPipeOther(permitAdvEntity.getThicknessOfPipeOther());
			newPermitAdvEntity.setFootingDiameter(permitAdvEntity.getFootingDiameter());
			newPermitAdvEntity.setFootingDiameterOther(permitAdvEntity.getFootingDiameterOther());
			newPermitAdvEntity.setEngineeringFirm(permitAdvEntity.getEngineeringFirm());
			newPermitAdvEntity
					.setCustomersServiceAgreementIDNumber(permitAdvEntity.getCustomersServiceAgreementIDNumber());
			newPermitAdvEntity.setCustomersRateSchedule(permitAdvEntity.getCustomersRateSchedule());

			newPermitAdvEntity.setOpenTldLibrary(permitAdvEntity.getOpenTldLibrary());
			newPermitAdvEntity.setTldShortList(permitAdvEntity.getTldShortList());
			newPermitAdvEntity.setTldList(permitAdvEntity.getTldList());
			newPermitAdvEntity.setrSheetList(permitAdvEntity.getrSheetList());
		}
		return newPermitAdvEntity;
	}

	public PermitWeatherEntity clonePermitWeatherEntity(PermitWeatherEntity newPermitWeatherEntity,
			PermitWeatherEntity permitWeatherEntity) {

		if (newPermitWeatherEntity != null && permitWeatherEntity != null) {
			newPermitWeatherEntity.setElevation(permitWeatherEntity.getElevation());
			newPermitWeatherEntity.setQuatrePourCentAverageHigh(permitWeatherEntity.getQuatrePourCentAverageHigh());
			newPermitWeatherEntity.setDeuxPourCentAverageHigh(permitWeatherEntity.getDeuxPourCentAverageHigh());
			newPermitWeatherEntity.setExtremeMinimum(permitWeatherEntity.getExtremeMinimum());
			newPermitWeatherEntity.setQuatrePourCentAvHighOther(permitWeatherEntity.getQuatrePourCentAvHighOther());
			newPermitWeatherEntity
					.setDeuxPourCentAverageHighOther(permitWeatherEntity.getDeuxPourCentAverageHighOther());
			newPermitWeatherEntity.setExtremeMinimumOther(permitWeatherEntity.getExtremeMinimumOther());
		}
		return newPermitWeatherEntity;

	}

	public PermitEngineerEntity clonePermitEngineerEntity(PermitEngineerEntity newPermitEngineerEntity,
			PermitEngineerEntity permitEngineerEntity) {

		if (newPermitEngineerEntity != null && permitEngineerEntity != null) {
			newPermitEngineerEntity.setApplicablEngineering(permitEngineerEntity.getApplicablEngineering());
			newPermitEngineerEntity.setName(permitEngineerEntity.getName());
			newPermitEngineerEntity.setEmail(permitEngineerEntity.getEmail());
			newPermitEngineerEntity.setMobile(permitEngineerEntity.getMobile());
			newPermitEngineerEntity.setPhone(permitEngineerEntity.getPhone());
			newPermitEngineerEntity.setLicenceNumber(permitEngineerEntity.getLicenceNumber());
			newPermitEngineerEntity.setLicenceType(permitEngineerEntity.getLicenceType());
			newPermitEngineerEntity.setCity(permitEngineerEntity.getCity());
			newPermitEngineerEntity.setState(permitEngineerEntity.getState());
			newPermitEngineerEntity.setCodePostale(permitEngineerEntity.getCodePostale());
			newPermitEngineerEntity.setEngineeredBy(permitEngineerEntity.getEngineeredBy());
			newPermitEngineerEntity.setDetermineModification(permitEngineerEntity.getDetermineModification());
			newPermitEngineerEntity.setIsShingles(permitEngineerEntity.getIsShingles());
			newPermitEngineerEntity.setIndicateLayers(permitEngineerEntity.getIndicateLayers());
			newPermitEngineerEntity.setMpptTrachers(permitEngineerEntity.getMpptTrachers());
			newPermitEngineerEntity.setNumberMpptTrachers(permitEngineerEntity.getNumberMpptTrachers());
			newPermitEngineerEntity
					.setNumberStringFirstMpptTrachers(permitEngineerEntity.getNumberStringFirstMpptTrachers());
			newPermitEngineerEntity
					.setNumberStringSecondMpptTrachers(permitEngineerEntity.getNumberStringSecondMpptTrachers());
			newPermitEngineerEntity.setNumberModuleStringFirstMpptTrachers(
					permitEngineerEntity.getNumberModuleStringFirstMpptTrachers());
			newPermitEngineerEntity.setNumberModuleStringSecondMpptTrachers(
					permitEngineerEntity.getNumberModuleStringSecondMpptTrachers());
			newPermitEngineerEntity.setIsTransformless(permitEngineerEntity.getIsTransformless());
			newPermitEngineerEntity.setNumberInputTransformless(permitEngineerEntity.getNumberInputTransformless());
			newPermitEngineerEntity.setIsCombiner(permitEngineerEntity.getIsCombiner());
			newPermitEngineerEntity.setNumberInputCombiner(permitEngineerEntity.getNumberInputCombiner());
			newPermitEngineerEntity.setOverhangArea(permitEngineerEntity.getOverhangArea());
			newPermitEngineerEntity.setRoofPitch(permitEngineerEntity.getRoofPitch());
			newPermitEngineerEntity.setAdressIng(permitEngineerEntity.getAdressIng());
		}
		return newPermitEngineerEntity;
	}
}
