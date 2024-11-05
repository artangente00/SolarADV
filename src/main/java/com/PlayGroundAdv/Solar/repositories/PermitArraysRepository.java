package com.PlayGroundAdv.Solar.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.PermitArraysEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.libraries.InverterUsedListModel;

@Repository
public interface PermitArraysRepository extends JpaRepository<PermitArraysEntity, Long> {

	// A.B 11-12
	public static final String FIND_MODULE_LIST = "SELECT u.pvModule.id from PermitArraysEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%' ";

	@Transactional
	@Modifying
	@Query(value = FIND_MODULE_LIST)
	public List<Long> findModuleList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_INVERTERS_LIST = "SELECT new com.PlayGroundAdv.Solar.model.libraries.InverterUsedListModel"
			+ "(u.firstInverter.id, u.secondInverter.id, u.thirdInverter.id,"
			+ "	u.fourthInverter.id, u.fifthInverter.id, u.microInverterEntity.id) "
			+ "from PermitArraysEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_INVERTERS_LIST)
	public List<InverterUsedListModel> findInvertersList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_OPTIMIZER_LIST = "SELECT u.systemOptimizerModel.id from PermitArraysEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_OPTIMIZER_LIST)
	public List<Long> findOptimizerList(@Param("p1") Date date);

	// C.I 05/12/2019
	PermitArraysEntity findByPermitEntityId(Long idPermitEntity);
	PermitArraysEntity findOneByPermitEntityId(Long idPermitEntity);

	List<PermitArraysEntity> findByPermitEntityAuthentificationEntityIdAndInverterModel(Long idOwner, String model);

	List<PermitArraysEntity> findByPermitEntityAuthentificationEntityIdAndSecondInverterModel(Long idOwner,
			String secondModel);

	List<PermitArraysEntity> findByPermitEntityAuthentificationEntityIdAndThirdInverterModel(Long idOwner,
			String thirdModel);

	List<PermitArraysEntity> findByPermitEntityAuthentificationEntityIdAndFourthInverterModel(Long idOwner,
			String fourthModel);

	List<PermitArraysEntity> findByPermitEntityAuthentificationEntityIdAndFifthInverterModel(Long idOwner,
			String fifthModel);

	List<PermitArraysEntity> findByPermitEntityAuthentificationEntityIdAndMicroInverter(Long idOwner,
			String microInverter);

	@Query(value = "SELECT u FROM PermitArraysEntity u WHERE u.firstInverter.id = :p1 OR u.secondInverter.id = :p1 OR u.thirdInverter.id = :p1 OR u.fourthInverter.id = :p1 OR u.fifthInverter.id = :p1 OR u.microInverterEntity.id = :p1")
	List<PermitArraysEntity> findByAnyInverter(@Param("p1") Long inverterModel);
	
	public static final String FIND_PROJECT_USING_MODEL = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel "
    		+ " (u.homeOwnLastName,  u.homeOwnName, u.projectName,  u.status, v.firstName, v.lastName) from PermitEntity u, AuthentificationEntity v, PermitArraysEntity w"
    		+ " where u.isDeleted = false and u.status != 'Delivered' and u.status != 'Submitted' and u.isTemplate  = false and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id";

	@Query(value = FIND_PROJECT_USING_MODEL+" and w.pvModule.id = :p1")
	public List<ProjectForLibrariesModel> findByPvModuleId(@Param("p1") Long idModule);

	@Query(value = FIND_PROJECT_USING_MODEL+" and (w.firstInverter.id = :p1 OR w.secondInverter.id = :p1 OR w.thirdInverter.id = :p1 OR w.fourthInverter.id = :p1 OR w.fifthInverter.id = :p1 OR w.microInverterEntity.id = :p1)")
	List<ProjectForLibrariesModel> findByInverterId(
			@Param("p1") Long inverterModel);

	// A.B 01-17
	@Query(value = "SELECT u.inverterModel FROM PermitArraysEntity u WHERE u.permitEntity.id = :p1")
	String findFirstInverterModel(@Param("p1") Long projectId);

	// A.B 01-17
	@Query(value = "SELECT u.secondInverterModel FROM PermitArraysEntity u WHERE u.permitEntity.id = :p1")
	String findSecondInverterModel(@Param("p1") Long projectId);

	// A.B 01-17
	@Query(value = "SELECT u.fourthInverterModel FROM PermitArraysEntity u WHERE u.permitEntity.id = :p1")
	String findFourthInverterModel(@Param("p1") Long projectId);

	// A.B 01-17
	@Query(value = "SELECT u.fifthInverterModel FROM PermitArraysEntity u WHERE u.permitEntity.id = :p1")
	String findFifthInverterModel(@Param("p1") Long projectId);

	// CI : 01-18
	@Query(value = "SELECT u.pvModuleModEl FROM PermitArraysEntity u WHERE u.permitEntity.id = :p1")
	String findPvModuleModel(@Param("p1") Long projectId);

	// CI : 03/02/2020
	@Query(value = "SELECT u.microInverter FROM PermitArraysEntity u WHERE u.permitEntity.id = :p1")
	String findMicroInverter(@Param("p1") Long projectId);

	// MA : 02-18
	@Query(value = "SELECT u.systemOptimizerModelManufacturer FROM PermitArraysEntity u WHERE u.permitEntity.id = :p1")
	String findSystemOptimizerModelManufacturer(@Param("p1") Long projectId);

	public static final String PERMIT_ARRAY_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond"
			+ " (u.systemType, u.RequestQuote, u.deviceToIncorporate, u.pvModule.id,"
			+ " u.firstInverter.id, u.stringOne, u.stringTwo, u.stringThree,"
			+ " u.stringFour, u.stringFive,  u.secondInverter.id, u.secondStringOne,"
			+ " u.secondStringTwo, u.secondStringThree, u.secondStringFour,"
			+ " u.secondStringFive, u.roofMounted, u.GroundMounted,"
			+ " u.otherMounted, u.textOther,  u.BatteryManufacturerTrojan,"
			+ " u.BatteryManufacturerMMK,  u.BatteryManufacturerUPG,"
			+ " u.BatteryManufacturerRolls,  u.BatteryManufacturerCrown,"
			+ " u.BatteryManufacturerTesla,  u.BatteryManufacturerOutback,"
			+ " u.BatteryManufacturerFullriver,  u.BatteryManufacturerConcord,"
			+ " u.BatteryManufacturerOther,  u.textBatteryOther, u.qteOfBattery,"
			+ " u.systemOptimizerModel.id, u.thirdInverter.id, u.thirdStringOne,"
			+ " u.thirdStringTwo, u.thirdStringThree, u.thirdStringFour,"
			+ " u.thirdStringFive, u.fourthInverter.id, u.fourthStringOne,"
			+ " u.fourthStringTwo, u.fourthStringThree, u.fourthStringFour,"
			+ " u.fourthStringFive, u.fifthInverter.id, u.fifthStringOne,"
			+ " u.fifthStringTwo, u.fifthStringThree, u.fifthStringFour,"
			+ " u.fifthStringFive, u.microInverterManufacturer, u.microInverterModel,"
			+ " u.numberModulesACCircuitOne, u.numberModulesACCircuitTwo,"
			+ " u.numberModulesACCircuitThree, u.numberModulesACCircuitFour,"
			+ " u.numberModulesACCircuitFive, u.numberModulesACCircuitSix,"
			+ " u.numberModulesACCircuitSeven, u.numberModulesACCircuitEight,"
			+ " u.numberModulesACCircuitNine, u.numberModulesACCircuitTen,"
			+ " u.numberModulesACCircuitEleven, u.numberModulesACCircuitTweleve,"
			+ " u.carportMounted,"
			+ " u.ocpdOne, u.ocpdTwo, u.ocpdThree, u.ocpdFour, u.ocpdFive,"
			+ " u.ocpdSix, u.ocpdSeven, u.ocpdEight, u.ocpdNine, u.ocpdTen," + " u.ocpdEleven, u.ocpdTwelve,"
			+ " u.uploadCommentsLayout,"
			+ " u.uploadCommentsAddInfo, u.inverterLocation, u.inverterLocationOther, u.inverterSameLocation,"
			+ " u.numberModulesACCircuitThirteen, u.numberModulesACCircuitFourteen, u.numberModulesACCircuitFifteen, u.numberModulesACCircuitSixteen, u.numberModulesACCircuitSeventeen,"
			+ " u.numberModulesACCircuitEightteen, u.numberModulesACCircuitNineteen, u.numberModulesACCircuitTwenty, u.numberModulesACCircuitTwentyOne, u.numberModulesACCircuitTwentyTwo,"
			+ " u.numberModulesACCircuitTwentyThree, u.numberModulesACCircuitTwentyFour,"
			+ " u.ocpdThirteen, u.ocpdFourteen, u.ocpdFifteen, u.ocpdSixteen, u.ocpdSeventeen, u.ocpdEightteen, u.ocpdNineteen, u.ocpdTwenty, u.ocpdTwentyOne, u.ocpdTwentyTwo,"
			+ " u.ocpdTwentyThree, u.ocpdTwentyFour, u.stringSix, u.stringSeven, u.stringEight, u.stringNine, u.stringTen, u.stringEleven, u.stringTwelve,"
			+ " u.secondStringSix, u.secondStringSeven, u.secondStringEight, u.secondStringNine, u.secondStringTen, u.secondStringEleven, u.secondStringTwelve,"
			+ " u.thirdStringSix, u.thirdStringSeven, u.thirdStringEight, u.thirdStringNine, u.thirdStringTen, u.thirdStringEleven, u.thirdStringTwelve,"
			+ " u.fourthStringSix, u.fourthStringSeven, u.fourthStringEight, u.fourthStringNine, u.fourthStringTen, u.fourthStringEleven, u.fourthStringTwelve,"
			+ " u.fifthStringSix, u.fifthStringSeven, u.fifthStringEight, u.fifthStringNine, u.fifthStringTen, u.fifthStringEleven, u.fifthStringTwelve,"
			+ "u.microInverterEntity.id, u.roofOrOpenFrame, u.circuitUnderGround, u.inverterInstalledOnRoof, u.enteranyTransformer, u.patioMounted , "
			+ "u.frontAndBack, u.cantelever, u.attachedToExtWal, u.attachedToFascia, u.attachedToSkylifts, u.freeStanding"
			+ ") from PermitArraysEntity u" + " where u.permitEntity.id = :p1";

	@Query(value = PERMIT_ARRAY_RESULT)
	PermitArrayEntityResultSecond getPermitArrayEntityResultSecond(@Param("p1") Long idProject);

	Long deleteByPermitEntityId(Long id);
}
