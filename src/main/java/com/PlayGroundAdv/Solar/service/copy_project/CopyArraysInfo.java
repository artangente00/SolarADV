package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitArraysEntity;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;

@Service
@Transactional
public class CopyArraysInfo {

	final PermitArraysRepository arraysRepo;
	final CopyArraysEquipmentFavorite copyArraysEquFav;

	public CopyArraysInfo(PermitArraysRepository arraysRepo, CopyArraysEquipmentFavorite copyArraysEquFav) {
		super();
		this.arraysRepo = arraysRepo;
		this.copyArraysEquFav = copyArraysEquFav;
	}

	public void copyArraysInfo(Long idPermit, Long idNewPermit, AuthentificationEntity user) {
		try {
			PermitArraysEntity permitArraysEntity = arraysRepo.findByPermitEntityId(idPermit);
			PermitArraysEntity newPermitArraysEntity = arraysRepo.findByPermitEntityId(idNewPermit);
			newPermitArraysEntity = clonePermitArraysEntity(newPermitArraysEntity, permitArraysEntity);
			arraysRepo.save(newPermitArraysEntity);

			// Copy Module Favorites
			copyArraysEquFav.copyModuleFav(permitArraysEntity.getPvModule(), user);

			// Copy Inverter Favorites
			copyArraysEquFav.copyInverterFav(permitArraysEntity.getFirstInverter(), user);
			copyArraysEquFav.copyInverterFav(permitArraysEntity.getSecondInverter(), user);
			copyArraysEquFav.copyInverterFav(permitArraysEntity.getThirdInverter(), user);
			copyArraysEquFav.copyInverterFav(permitArraysEntity.getFourthInverter(), user);
			copyArraysEquFav.copyInverterFav(permitArraysEntity.getFifthInverter(), user);
			copyArraysEquFav.copyInverterFav(permitArraysEntity.getMicroInverterEntity(), user);

			// Copy Converter Favorites
			copyArraysEquFav.copyConverterFav(permitArraysEntity.getSystemOptimizerModel(), user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PermitArraysEntity clonePermitArraysEntity(PermitArraysEntity newPermitArraysEntity,
			PermitArraysEntity permitArraysEntity) {

		if (newPermitArraysEntity != null && permitArraysEntity != null) {

			newPermitArraysEntity.setSystemType(permitArraysEntity.getSystemType());
			newPermitArraysEntity.setRequestQuote(permitArraysEntity.getRequestQuote());
			newPermitArraysEntity.setDeviceToIncorporate(permitArraysEntity.getDeviceToIncorporate());
			newPermitArraysEntity.setInverterLocation(permitArraysEntity.getInverterLocation());
			newPermitArraysEntity.setInverterLocationOther(permitArraysEntity.getInverterLocationOther());
			newPermitArraysEntity.setInverterSameLocation(permitArraysEntity.getInverterSameLocation());

			// CI : 17/01/2020
			newPermitArraysEntity.setPvModule(permitArraysEntity.getPvModule());

			// A.B 01-20-2020 Copy Inverter
			newPermitArraysEntity.setFirstInverter(permitArraysEntity.getFirstInverter());
			newPermitArraysEntity.setSecondInverter(permitArraysEntity.getSecondInverter());
			newPermitArraysEntity.setThirdInverter(permitArraysEntity.getThirdInverter());
			newPermitArraysEntity.setFourthInverter(permitArraysEntity.getFourthInverter());
			newPermitArraysEntity.setFifthInverter(permitArraysEntity.getFifthInverter());

			newPermitArraysEntity.setStringOne(permitArraysEntity.getStringOne());
			newPermitArraysEntity.setStringTwo(permitArraysEntity.getStringTwo());
			newPermitArraysEntity.setStringThree(permitArraysEntity.getStringThree());
			newPermitArraysEntity.setStringFour(permitArraysEntity.getStringFour());
			newPermitArraysEntity.setStringFive(permitArraysEntity.getStringFive());

			newPermitArraysEntity.setSecondStringOne(permitArraysEntity.getSecondStringOne());
			newPermitArraysEntity.setSecondStringTwo(permitArraysEntity.getSecondStringTwo());
			newPermitArraysEntity.setSecondStringThree(permitArraysEntity.getSecondStringThree());
			newPermitArraysEntity.setSecondStringFour(permitArraysEntity.getSecondStringFour());
			newPermitArraysEntity.setSecondStringFive(permitArraysEntity.getSecondStringFive());

			newPermitArraysEntity.setRoofMounted(permitArraysEntity.getRoofMounted());
			newPermitArraysEntity.setGroundMounted(permitArraysEntity.getGroundMounted());
			newPermitArraysEntity.setCarportMounted(permitArraysEntity.getCarportMounted());
			newPermitArraysEntity.setPatioMounted(permitArraysEntity.getPatioMounted());
			newPermitArraysEntity.setFrontAndBack(permitArraysEntity.getFrontAndBack());
			newPermitArraysEntity.setCantelever(permitArraysEntity.getCantelever());
			newPermitArraysEntity.setAttachedToExtWal(permitArraysEntity.getAttachedToExtWal());
			newPermitArraysEntity.setAttachedToFascia(permitArraysEntity.getAttachedToFascia());
			newPermitArraysEntity.setAttachedToSkylifts(permitArraysEntity.getAttachedToSkylifts());
			newPermitArraysEntity.setFreeStanding(permitArraysEntity.getFreeStanding());
			newPermitArraysEntity.setOtherMounted(permitArraysEntity.getOtherMounted());
			newPermitArraysEntity.setTextOther(permitArraysEntity.getTextOther());

			newPermitArraysEntity.setBatteryManufacturerTrojan(permitArraysEntity.getBatteryManufacturerTrojan());
			newPermitArraysEntity.setBatteryManufacturerMMK(permitArraysEntity.getBatteryManufacturerMMK());
			newPermitArraysEntity.setBatteryManufacturerUPG(permitArraysEntity.getBatteryManufacturerUPG());
			newPermitArraysEntity.setBatteryManufacturerRolls(permitArraysEntity.getBatteryManufacturerRolls());
			newPermitArraysEntity.setBatteryManufacturerCrown(permitArraysEntity.getBatteryManufacturerCrown());
			newPermitArraysEntity.setBatteryManufacturerTesla(permitArraysEntity.getBatteryManufacturerTesla());
			newPermitArraysEntity.setBatteryManufacturerOutback(permitArraysEntity.getBatteryManufacturerOutback());
			newPermitArraysEntity.setBatteryManufacturerFullriver(permitArraysEntity.getBatteryManufacturerFullriver());
			newPermitArraysEntity.setBatteryManufacturerConcord(permitArraysEntity.getBatteryManufacturerConcord());
			newPermitArraysEntity.setBatteryManufacturerOther(permitArraysEntity.getBatteryManufacturerOther());
			newPermitArraysEntity.setTextBatteryOther(permitArraysEntity.getTextBatteryOther());
			newPermitArraysEntity.setQteOfBattery(permitArraysEntity.getQteOfBattery());
			newPermitArraysEntity.setSystemOptimizerModel(permitArraysEntity.getSystemOptimizerModel());

			newPermitArraysEntity.setThirdStringOne(permitArraysEntity.getThirdStringOne());
			newPermitArraysEntity.setThirdStringTwo(permitArraysEntity.getThirdStringTwo());
			newPermitArraysEntity.setThirdStringThree(permitArraysEntity.getThirdStringThree());
			newPermitArraysEntity.setThirdStringFour(permitArraysEntity.getThirdStringFour());
			newPermitArraysEntity.setThirdStringFive(permitArraysEntity.getThirdStringFive());

			newPermitArraysEntity.setFourthStringOne(permitArraysEntity.getFourthStringOne());
			newPermitArraysEntity.setFourthStringTwo(permitArraysEntity.getFourthStringTwo());
			newPermitArraysEntity.setFourthStringThree(permitArraysEntity.getFourthStringThree());
			newPermitArraysEntity.setFourthStringFour(permitArraysEntity.getFourthStringFour());
			newPermitArraysEntity.setFourthStringFive(permitArraysEntity.getFourthStringFive());

			newPermitArraysEntity.setFifthStringOne(permitArraysEntity.getFifthStringOne());
			newPermitArraysEntity.setFifthStringTwo(permitArraysEntity.getFifthStringTwo());
			newPermitArraysEntity.setFifthStringThree(permitArraysEntity.getFifthStringThree());
			newPermitArraysEntity.setFifthStringFour(permitArraysEntity.getFifthStringFour());
			newPermitArraysEntity.setFifthStringFive(permitArraysEntity.getFifthStringFive());

			newPermitArraysEntity.setMicroInverterEntity(permitArraysEntity.getMicroInverterEntity());
			newPermitArraysEntity.setNumberModulesACCircuitOne(permitArraysEntity.getNumberModulesACCircuitOne());
			newPermitArraysEntity.setNumberModulesACCircuitTwo(permitArraysEntity.getNumberModulesACCircuitTwo());
			newPermitArraysEntity.setNumberModulesACCircuitThree(permitArraysEntity.getNumberModulesACCircuitThree());
			newPermitArraysEntity.setNumberModulesACCircuitFour(permitArraysEntity.getNumberModulesACCircuitFour());
			newPermitArraysEntity.setNumberModulesACCircuitFive(permitArraysEntity.getNumberModulesACCircuitFive());
			newPermitArraysEntity.setNumberModulesACCircuitSix(permitArraysEntity.getNumberModulesACCircuitSix());
			newPermitArraysEntity.setNumberModulesACCircuitSeven(permitArraysEntity.getNumberModulesACCircuitSeven());
			newPermitArraysEntity.setNumberModulesACCircuitEight(permitArraysEntity.getNumberModulesACCircuitEight());
			newPermitArraysEntity.setNumberModulesACCircuitNine(permitArraysEntity.getNumberModulesACCircuitNine());
			newPermitArraysEntity.setNumberModulesACCircuitTen(permitArraysEntity.getNumberModulesACCircuitTen());
			newPermitArraysEntity.setNumberModulesACCircuitEleven(permitArraysEntity.getNumberModulesACCircuitEleven());
			newPermitArraysEntity
					.setNumberModulesACCircuitTweleve(permitArraysEntity.getNumberModulesACCircuitTweleve());
			newPermitArraysEntity
					.setNumberModulesACCircuitThirteen(permitArraysEntity.getNumberModulesACCircuitThirteen());
			newPermitArraysEntity
					.setNumberModulesACCircuitFourteen(permitArraysEntity.getNumberModulesACCircuitFourteen());
			newPermitArraysEntity
					.setNumberModulesACCircuitFifteen(permitArraysEntity.getNumberModulesACCircuitFifteen());
			newPermitArraysEntity
					.setNumberModulesACCircuitSixteen(permitArraysEntity.getNumberModulesACCircuitSixteen());
			newPermitArraysEntity
					.setNumberModulesACCircuitSeventeen(permitArraysEntity.getNumberModulesACCircuitSeventeen());
			newPermitArraysEntity
					.setNumberModulesACCircuitEightteen(permitArraysEntity.getNumberModulesACCircuitEightteen());
			newPermitArraysEntity
					.setNumberModulesACCircuitNineteen(permitArraysEntity.getNumberModulesACCircuitNineteen());
			newPermitArraysEntity.setNumberModulesACCircuitTwenty(permitArraysEntity.getNumberModulesACCircuitTwenty());
			newPermitArraysEntity
					.setNumberModulesACCircuitTwentyOne(permitArraysEntity.getNumberModulesACCircuitTwentyOne());
			newPermitArraysEntity
					.setNumberModulesACCircuitTwentyTwo(permitArraysEntity.getNumberModulesACCircuitTwentyTwo());
			newPermitArraysEntity
					.setNumberModulesACCircuitTwentyThree(permitArraysEntity.getNumberModulesACCircuitTwentyThree());
			newPermitArraysEntity
					.setNumberModulesACCircuitTwentyFour(permitArraysEntity.getNumberModulesACCircuitTwentyFour());

			newPermitArraysEntity.setOcpdOne(permitArraysEntity.getOcpdOne());
			newPermitArraysEntity.setOcpdTwo(permitArraysEntity.getOcpdTwo());
			newPermitArraysEntity.setOcpdThree(permitArraysEntity.getOcpdThree());
			newPermitArraysEntity.setOcpdFour(permitArraysEntity.getOcpdFour());
			newPermitArraysEntity.setOcpdFive(permitArraysEntity.getOcpdFive());
			newPermitArraysEntity.setOcpdSix(permitArraysEntity.getOcpdSix());
			newPermitArraysEntity.setOcpdSeven(permitArraysEntity.getOcpdSeven());
			newPermitArraysEntity.setOcpdEight(permitArraysEntity.getOcpdEight());
			newPermitArraysEntity.setOcpdNine(permitArraysEntity.getOcpdNine());
			newPermitArraysEntity.setOcpdTen(permitArraysEntity.getOcpdTen());
			newPermitArraysEntity.setOcpdEleven(permitArraysEntity.getOcpdEleven());
			newPermitArraysEntity.setOcpdTwelve(permitArraysEntity.getOcpdTwelve());
			newPermitArraysEntity.setOcpdThirteen(permitArraysEntity.getOcpdThirteen());
			newPermitArraysEntity.setOcpdFourteen(permitArraysEntity.getOcpdFourteen());
			newPermitArraysEntity.setOcpdFifteen(permitArraysEntity.getOcpdFifteen());
			newPermitArraysEntity.setOcpdSixteen(permitArraysEntity.getOcpdSixteen());
			newPermitArraysEntity.setOcpdSeventeen(permitArraysEntity.getOcpdSeventeen());
			newPermitArraysEntity.setOcpdEightteen(permitArraysEntity.getOcpdEightteen());
			newPermitArraysEntity.setOcpdNineteen(permitArraysEntity.getOcpdNineteen());
			newPermitArraysEntity.setOcpdTwenty(permitArraysEntity.getOcpdTwenty());
			newPermitArraysEntity.setOcpdTwentyOne(permitArraysEntity.getOcpdTwentyOne());
			newPermitArraysEntity.setOcpdTwentyTwo(permitArraysEntity.getOcpdTwentyTwo());
			newPermitArraysEntity.setOcpdTwentyThree(permitArraysEntity.getOcpdTwentyThree());
			newPermitArraysEntity.setOcpdTwentyFour(permitArraysEntity.getOcpdTwentyFour());
			// ***********Begin CR-2222 :Add UP TO 12 String Inputs for Number of Modules in
			// Each String*******************
			newPermitArraysEntity.setStringSix(permitArraysEntity.getStringSix());
			newPermitArraysEntity.setStringSeven(permitArraysEntity.getStringSeven());
			newPermitArraysEntity.setStringEight(permitArraysEntity.getStringEight());
			newPermitArraysEntity.setStringNine(permitArraysEntity.getStringNine());
			newPermitArraysEntity.setStringTen(permitArraysEntity.getStringTen());
			newPermitArraysEntity.setStringEleven(permitArraysEntity.getStringEleven());
			newPermitArraysEntity.setStringTwelve(permitArraysEntity.getStringTwelve());
			newPermitArraysEntity.setSecondStringSix(permitArraysEntity.getSecondStringSix());
			newPermitArraysEntity.setSecondStringSeven(permitArraysEntity.getSecondStringSeven());
			newPermitArraysEntity.setSecondStringEight(permitArraysEntity.getSecondStringEight());
			newPermitArraysEntity.setSecondStringNine(permitArraysEntity.getSecondStringNine());
			newPermitArraysEntity.setSecondStringTen(permitArraysEntity.getSecondStringTen());
			newPermitArraysEntity.setSecondStringEleven(permitArraysEntity.getSecondStringEleven());
			newPermitArraysEntity.setSecondStringTwelve(permitArraysEntity.getSecondStringTwelve());
			newPermitArraysEntity.setThirdStringSix(permitArraysEntity.getThirdStringSix());
			newPermitArraysEntity.setThirdStringSeven(permitArraysEntity.getThirdStringSeven());
			newPermitArraysEntity.setThirdStringEight(permitArraysEntity.getThirdStringEight());
			newPermitArraysEntity.setThirdStringNine(permitArraysEntity.getThirdStringNine());
			newPermitArraysEntity.setThirdStringTen(permitArraysEntity.getThirdStringTen());
			newPermitArraysEntity.setThirdStringEleven(permitArraysEntity.getThirdStringEleven());
			newPermitArraysEntity.setThirdStringTwelve(permitArraysEntity.getThirdStringTwelve());
			newPermitArraysEntity.setFourthStringSix(permitArraysEntity.getFourthStringSix());
			newPermitArraysEntity.setFourthStringSeven(permitArraysEntity.getFourthStringSeven());
			newPermitArraysEntity.setFourthStringEight(permitArraysEntity.getFourthStringEight());
			newPermitArraysEntity.setFourthStringNine(permitArraysEntity.getFourthStringNine());
			newPermitArraysEntity.setFourthStringTen(permitArraysEntity.getFourthStringTen());
			newPermitArraysEntity.setFourthStringEleven(permitArraysEntity.getFourthStringEleven());
			newPermitArraysEntity.setFourthStringTwelve(permitArraysEntity.getFourthStringTwelve());
			newPermitArraysEntity.setFifthStringSix(permitArraysEntity.getFifthStringSix());
			newPermitArraysEntity.setFifthStringSeven(permitArraysEntity.getFifthStringSeven());
			newPermitArraysEntity.setFifthStringEight(permitArraysEntity.getFifthStringEight());
			newPermitArraysEntity.setFifthStringNine(permitArraysEntity.getFifthStringNine());
			newPermitArraysEntity.setFifthStringTen(permitArraysEntity.getFifthStringTen());
			newPermitArraysEntity.setFifthStringEleven(permitArraysEntity.getFifthStringEleven());
			newPermitArraysEntity.setFifthStringTwelve(permitArraysEntity.getFifthStringTwelve());
			newPermitArraysEntity.setRoofOrOpenFrame(permitArraysEntity.getRoofOrOpenFrame());
			newPermitArraysEntity.setCircuitUnderGround(permitArraysEntity.getCircuitUnderGround());
			newPermitArraysEntity.setInverterInstalledOnRoof(permitArraysEntity.getInverterInstalledOnRoof());
			// ************ END CR-2222****************************

			// 02-25-2019: A.B: CR-2313
			newPermitArraysEntity.setRoofOrOpenFrame(permitArraysEntity.getRoofOrOpenFrame());
			newPermitArraysEntity.setCircuitUnderGround(permitArraysEntity.getCircuitUnderGround());
			newPermitArraysEntity.setInverterInstalledOnRoof(permitArraysEntity.getInverterInstalledOnRoof());
		}
		return newPermitArraysEntity;
	}
}
