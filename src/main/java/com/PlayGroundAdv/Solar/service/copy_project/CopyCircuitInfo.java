package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ConduitConductorCircuitEntity;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.repositories.ConduitConductorCircuitRepository;
import com.PlayGroundAdv.Solar.repositories.PermitConduitConductorSectionRepository;

@Service
@Transactional
public class CopyCircuitInfo {

	final PermitConduitConductorSectionRepository permitConduitConductorSectionRepo;
	final ConduitConductorCircuitRepository conduitConductorCircuitRepo;

	public CopyCircuitInfo(PermitConduitConductorSectionRepository permitConduitConductorSectionRepo,
			ConduitConductorCircuitRepository conduitConductorCircuitRepo) {
		super();
		this.permitConduitConductorSectionRepo = permitConduitConductorSectionRepo;
		this.conduitConductorCircuitRepo = conduitConductorCircuitRepo;
	}

	public void copyCircuitInfo(Long idPermit, Long idNewPermit) {
		try {
			PermitConduitConductorSectionEntity permitConduitConductorSectionEntity = permitConduitConductorSectionRepo
					.findByPermitEntityId(idPermit);
			PermitConduitConductorSectionEntity newPermitConduitConductorSectionEntity = permitConduitConductorSectionRepo
					.findByPermitEntityId(idNewPermit);
			newPermitConduitConductorSectionEntity = clonePermitConduitConductorSectionEntity(
					newPermitConduitConductorSectionEntity, permitConduitConductorSectionEntity);
			permitConduitConductorSectionRepo.save(newPermitConduitConductorSectionEntity);

			// Copy ConduitConductorCircuitEntity
			ConduitConductorCircuitEntity permitConductorCircuit = conduitConductorCircuitRepo
					.findByPermitEntityId(idPermit);
			ConduitConductorCircuitEntity newPermitConductorCircuit = conduitConductorCircuitRepo
					.findByPermitEntityId(idNewPermit);
			newPermitConductorCircuit = cloneConduitConductorCircuitEntity(newPermitConductorCircuit,
					permitConductorCircuit);
			conduitConductorCircuitRepo.save(newPermitConductorCircuit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ConduitConductorCircuitEntity cloneConduitConductorCircuitEntity(
			ConduitConductorCircuitEntity newConduitConductorCircuitEntity,
			ConduitConductorCircuitEntity conduitConductorCircuitEntity) {

		if (newConduitConductorCircuitEntity != null && conduitConductorCircuitEntity != null) {
			newConduitConductorCircuitEntity.setCircuitLength(conduitConductorCircuitEntity.getCircuitLength());
			newConduitConductorCircuitEntity.setConductorQty(conduitConductorCircuitEntity.getConductorQty());
			newConduitConductorCircuitEntity.setConductorSize(conduitConductorCircuitEntity.getConductorSize());
			newConduitConductorCircuitEntity.setConductorType(conduitConductorCircuitEntity.getConductorType());
			newConduitConductorCircuitEntity.setConduitSize(conduitConductorCircuitEntity.getConduitSize());
			newConduitConductorCircuitEntity.setConduitType(conduitConductorCircuitEntity.getConduitType());
			newConduitConductorCircuitEntity
					.setConductorSizeOther(conduitConductorCircuitEntity.getConductorSizeOther());
			newConduitConductorCircuitEntity
					.setConductorTypeOther(conduitConductorCircuitEntity.getConductorTypeOther());
			newConduitConductorCircuitEntity.setConduitSizeOther(conduitConductorCircuitEntity.getConduitSizeOther());
			newConduitConductorCircuitEntity.setConduitTypeOther(conduitConductorCircuitEntity.getConduitTypeOther());
			newConduitConductorCircuitEntity.setConductorQtyTwo(conduitConductorCircuitEntity.getConductorQtyTwo());
			newConduitConductorCircuitEntity.setConductorSizeTwo(conduitConductorCircuitEntity.getConductorSizeTwo());
			newConduitConductorCircuitEntity.setConductorTypeTwo(conduitConductorCircuitEntity.getConductorTypeTwo());
			newConduitConductorCircuitEntity.setConduitSizeTwo(conduitConductorCircuitEntity.getConduitSizeTwo());
			newConduitConductorCircuitEntity.setConduitTypeTwo(conduitConductorCircuitEntity.getConduitTypeTwo());
			newConduitConductorCircuitEntity
					.setConductorSizeTwoOther(conduitConductorCircuitEntity.getConductorSizeTwoOther());
			newConduitConductorCircuitEntity
					.setConductorTypeTwoOther(conduitConductorCircuitEntity.getConductorTypeTwoOther());
			newConduitConductorCircuitEntity
					.setConduitSizeTwoOther(conduitConductorCircuitEntity.getConduitSizeTwoOther());
			newConduitConductorCircuitEntity
					.setConduitTypeTwoOther(conduitConductorCircuitEntity.getConduitTypeTwoOther());
			newConduitConductorCircuitEntity.setConductorQtyThree(conduitConductorCircuitEntity.getConductorQtyThree());
			newConduitConductorCircuitEntity
					.setConductorSizeThree(conduitConductorCircuitEntity.getConductorSizeThree());
			newConduitConductorCircuitEntity
					.setConductorTypeThree(conduitConductorCircuitEntity.getConductorTypeThree());
			newConduitConductorCircuitEntity.setConduitSizeThree(conduitConductorCircuitEntity.getConduitSizeThree());
			newConduitConductorCircuitEntity.setConduitTypeThree(conduitConductorCircuitEntity.getConduitTypeThree());
			newConduitConductorCircuitEntity
					.setConductorSizeThreeOther(conduitConductorCircuitEntity.getConductorSizeThreeOther());
			newConduitConductorCircuitEntity
					.setConductorTypeThreeOther(conduitConductorCircuitEntity.getConductorTypeThreeOther());
			newConduitConductorCircuitEntity
					.setConduitSizeThreeOther(conduitConductorCircuitEntity.getConduitSizeThreeOther());
			newConduitConductorCircuitEntity
					.setConduitTypeThreeOther(conduitConductorCircuitEntity.getConduitTypeThreeOther());
			newConduitConductorCircuitEntity.setConductorQtyFour(conduitConductorCircuitEntity.getConductorQtyFour());
			newConduitConductorCircuitEntity.setConductorSizeFour(conduitConductorCircuitEntity.getConductorSizeFour());
			newConduitConductorCircuitEntity.setConductorTypeFour(conduitConductorCircuitEntity.getConductorTypeFour());
			newConduitConductorCircuitEntity.setConduitSizeFour(conduitConductorCircuitEntity.getConduitSizeFour());
			newConduitConductorCircuitEntity.setConduitTypeFour(conduitConductorCircuitEntity.getConduitTypeFour());
			newConduitConductorCircuitEntity
					.setConductorSizeFourOther(conduitConductorCircuitEntity.getConductorSizeFourOther());
			newConduitConductorCircuitEntity
					.setConductorTypeFourOther(conduitConductorCircuitEntity.getConductorTypeFourOther());
			newConduitConductorCircuitEntity
					.setConduitSizeFourOther(conduitConductorCircuitEntity.getConduitSizeFourOther());
			newConduitConductorCircuitEntity
					.setConduitTypeFourOther(conduitConductorCircuitEntity.getConduitTypeFourOther());
			newConduitConductorCircuitEntity.setConductorQtyFive(conduitConductorCircuitEntity.getConductorQtyFive());
			newConduitConductorCircuitEntity.setConductorSizeFive(conduitConductorCircuitEntity.getConductorSizeFive());
			newConduitConductorCircuitEntity.setConductorTypeFive(conduitConductorCircuitEntity.getConductorTypeFive());
			newConduitConductorCircuitEntity.setConduitSizeFive(conduitConductorCircuitEntity.getConduitSizeFive());
			newConduitConductorCircuitEntity.setConduitTypeFive(conduitConductorCircuitEntity.getConduitTypeFive());
			newConduitConductorCircuitEntity
					.setConductorSizeFiveOther(conduitConductorCircuitEntity.getConductorSizeFiveOther());
			newConduitConductorCircuitEntity
					.setConductorTypeFiveOther(conduitConductorCircuitEntity.getConductorTypeFiveOther());
			newConduitConductorCircuitEntity
					.setConduitSizeFiveOther(conduitConductorCircuitEntity.getConduitSizeFiveOther());
			newConduitConductorCircuitEntity
					.setConduitTypeFiveOther(conduitConductorCircuitEntity.getConduitTypeFiveOther());
			newConduitConductorCircuitEntity.setConductorQtySix(conduitConductorCircuitEntity.getConductorQtySix());
			newConduitConductorCircuitEntity.setConductorSizeSix(conduitConductorCircuitEntity.getConductorSizeSix());
			newConduitConductorCircuitEntity.setConductorTypeSix(conduitConductorCircuitEntity.getConductorTypeSix());
			newConduitConductorCircuitEntity.setConduitSizeSix(conduitConductorCircuitEntity.getConduitSizeSix());
			newConduitConductorCircuitEntity.setConduitTypeSix(conduitConductorCircuitEntity.getConduitTypeSix());
			newConduitConductorCircuitEntity
					.setConductorSizeSixOther(conduitConductorCircuitEntity.getConductorSizeSixOther());
			newConduitConductorCircuitEntity
					.setConductorTypeSixOther(conduitConductorCircuitEntity.getConductorTypeSixOther());
			newConduitConductorCircuitEntity
					.setConduitSizeSixOther(conduitConductorCircuitEntity.getConduitSizeSixOther());
			newConduitConductorCircuitEntity
					.setConduitTypeSixOther(conduitConductorCircuitEntity.getConduitTypeSixOther());
			newConduitConductorCircuitEntity.setConductorQtySeven(conduitConductorCircuitEntity.getConductorQtySeven());
			newConduitConductorCircuitEntity
					.setConductorSizeSeven(conduitConductorCircuitEntity.getConductorSizeSeven());
			newConduitConductorCircuitEntity
					.setConductorTypeSeven(conduitConductorCircuitEntity.getConductorTypeSeven());
			newConduitConductorCircuitEntity.setConduitSizeSeven(conduitConductorCircuitEntity.getConduitSizeSeven());
			newConduitConductorCircuitEntity.setConduitTypeSeven(conduitConductorCircuitEntity.getConduitTypeSeven());
			newConduitConductorCircuitEntity
					.setConductorSizeSevenOther(conduitConductorCircuitEntity.getConductorSizeSevenOther());
			newConduitConductorCircuitEntity
					.setConductorTypeSevenOther(conduitConductorCircuitEntity.getConductorTypeSevenOther());
			newConduitConductorCircuitEntity
					.setConduitSizeSevenOther(conduitConductorCircuitEntity.getConduitSizeSevenOther());
			newConduitConductorCircuitEntity
					.setConduitTypeSevenOther(conduitConductorCircuitEntity.getConduitTypeSevenOther());
			newConduitConductorCircuitEntity.setConductorQtyEight(conduitConductorCircuitEntity.getConductorQtyEight());
			newConduitConductorCircuitEntity
					.setConductorSizeEight(conduitConductorCircuitEntity.getConductorSizeEight());
			newConduitConductorCircuitEntity
					.setConductorTypeEight(conduitConductorCircuitEntity.getConductorTypeEight());
			newConduitConductorCircuitEntity.setConduitSizeEight(conduitConductorCircuitEntity.getConduitSizeEight());
			newConduitConductorCircuitEntity.setConduitTypeEight(conduitConductorCircuitEntity.getConduitTypeEight());
			newConduitConductorCircuitEntity
					.setConductorSizeEightOther(conduitConductorCircuitEntity.getConductorSizeEightOther());
			newConduitConductorCircuitEntity
					.setConductorTypeEightOther(conduitConductorCircuitEntity.getConductorTypeEightOther());
			newConduitConductorCircuitEntity
					.setConduitSizeEightOther(conduitConductorCircuitEntity.getConduitSizeEightOther());
			newConduitConductorCircuitEntity
					.setConduitTypeEightOther(conduitConductorCircuitEntity.getConduitTypeEightOther());
			newConduitConductorCircuitEntity.setConductorTypeNine(conduitConductorCircuitEntity.getConductorTypeNine());
			newConduitConductorCircuitEntity.setConductorSizeNine(conduitConductorCircuitEntity.getConductorSizeNine());
			newConduitConductorCircuitEntity.setConduitTypeNine(conduitConductorCircuitEntity.getConduitTypeNine());
			newConduitConductorCircuitEntity.setConduitSizeNine(conduitConductorCircuitEntity.getConduitSizeNine());
			newConduitConductorCircuitEntity.setConductorQtyNine(conduitConductorCircuitEntity.getConductorQtyNine());
			newConduitConductorCircuitEntity
					.setConductorTypeNineOther(conduitConductorCircuitEntity.getConductorTypeNineOther());
			newConduitConductorCircuitEntity
					.setConductorSizeNineOther(conduitConductorCircuitEntity.getConductorSizeNineOther());
			newConduitConductorCircuitEntity
					.setConduitTypeNineOther(conduitConductorCircuitEntity.getConduitTypeNineOther());
			newConduitConductorCircuitEntity
					.setConduitSizeNineOther(conduitConductorCircuitEntity.getConduitSizeNineOther());
			newConduitConductorCircuitEntity.setConductorTypeTen(conduitConductorCircuitEntity.getConductorTypeTen());
			newConduitConductorCircuitEntity.setConductorSizeTen(conduitConductorCircuitEntity.getConductorSizeTen());
			newConduitConductorCircuitEntity.setConduitTypeTen(conduitConductorCircuitEntity.getConduitTypeTen());
			newConduitConductorCircuitEntity.setConduitSizeTen(conduitConductorCircuitEntity.getConduitSizeTen());
			newConduitConductorCircuitEntity.setConductorQtyTen(conduitConductorCircuitEntity.getConductorQtyTen());
			newConduitConductorCircuitEntity
					.setConductorTypeTenOther(conduitConductorCircuitEntity.getConductorTypeTenOther());
			newConduitConductorCircuitEntity
					.setConductorSizeTenOther(conduitConductorCircuitEntity.getConductorSizeTenOther());
			newConduitConductorCircuitEntity
					.setConduitTypeTenOther(conduitConductorCircuitEntity.getConduitTypeTenOther());
			newConduitConductorCircuitEntity
					.setConduitSizeTenOther(conduitConductorCircuitEntity.getConduitSizeTenOther());
			newConduitConductorCircuitEntity
					.setConductorTypeEleven(conduitConductorCircuitEntity.getConductorTypeEleven());
			newConduitConductorCircuitEntity
					.setConductorSizeEleven(conduitConductorCircuitEntity.getConductorSizeEleven());
			newConduitConductorCircuitEntity.setConduitTypeEleven(conduitConductorCircuitEntity.getConduitTypeEleven());
			newConduitConductorCircuitEntity.setConduitSizeEleven(conduitConductorCircuitEntity.getConduitSizeEleven());
			newConduitConductorCircuitEntity
					.setConductorQtyEleven(conduitConductorCircuitEntity.getConductorQtyEleven());
			newConduitConductorCircuitEntity
					.setConductorTypeElevenOther(conduitConductorCircuitEntity.getConductorTypeElevenOther());
			newConduitConductorCircuitEntity
					.setConductorSizeElevenOther(conduitConductorCircuitEntity.getConductorSizeElevenOther());
			newConduitConductorCircuitEntity
					.setConduitTypeElevenOther(conduitConductorCircuitEntity.getConduitTypeElevenOther());
			newConduitConductorCircuitEntity
					.setConduitSizeElevenOther(conduitConductorCircuitEntity.getConduitSizeElevenOther());
			
			  /* CR-3227-MOD-001 */
			newConduitConductorCircuitEntity.setCircuitEnvironment(conduitConductorCircuitEntity.getCircuitEnvironment());
			newConduitConductorCircuitEntity.setCircuitEnvironmentTwo(conduitConductorCircuitEntity.getCircuitEnvironmentTwo());
			newConduitConductorCircuitEntity.setCircuitEnvironmentThree(conduitConductorCircuitEntity.getCircuitEnvironmentThree());
			newConduitConductorCircuitEntity.setCircuitEnvironmentFour(conduitConductorCircuitEntity.getCircuitEnvironmentFour());
			newConduitConductorCircuitEntity.setCircuitEnvironmentFive(conduitConductorCircuitEntity.getCircuitEnvironmentFive());
			newConduitConductorCircuitEntity.setCircuitEnvironmentSix(conduitConductorCircuitEntity.getCircuitEnvironmentSix());
			newConduitConductorCircuitEntity.setCircuitEnvironmentSeven(conduitConductorCircuitEntity.getCircuitEnvironmentSeven());
			newConduitConductorCircuitEntity.setCircuitEnvironmentEight(conduitConductorCircuitEntity.getCircuitEnvironmentEight());
			newConduitConductorCircuitEntity.setCircuitEnvironmentNine(conduitConductorCircuitEntity.getCircuitEnvironmentNine());
			newConduitConductorCircuitEntity.setCircuitEnvironmentNineTwo(conduitConductorCircuitEntity.getCircuitEnvironmentNineTwo());
			newConduitConductorCircuitEntity.setCircuitEnvironmentTen(conduitConductorCircuitEntity.getCircuitEnvironmentTen());
			newConduitConductorCircuitEntity.setCircuitEnvironmentEleven(conduitConductorCircuitEntity.getCircuitEnvironmentEleven());
			newConduitConductorCircuitEntity.setCircuitEnvironmentTwelve(conduitConductorCircuitEntity.getCircuitEnvironmentTwelve());
		    /* CR-3227-MOD-003 */
			newConduitConductorCircuitEntity.setCircuitLengthOne(conduitConductorCircuitEntity.getCircuitLengthOne());
			newConduitConductorCircuitEntity.setCircuitLengthTwo(conduitConductorCircuitEntity.getCircuitLengthTwo());
			newConduitConductorCircuitEntity.setCircuitLengthThree(conduitConductorCircuitEntity.getCircuitLengthThree());
			newConduitConductorCircuitEntity.setCircuitLengthFour(conduitConductorCircuitEntity.getCircuitLengthFour());
			newConduitConductorCircuitEntity.setCircuitLengthFive(conduitConductorCircuitEntity.getCircuitLengthFive());
			newConduitConductorCircuitEntity.setCircuitLengthSix(conduitConductorCircuitEntity.getCircuitLengthSix());
			newConduitConductorCircuitEntity.setCircuitLengthSeven(conduitConductorCircuitEntity.getCircuitLengthSeven());
			newConduitConductorCircuitEntity.setCircuitLengthEight(conduitConductorCircuitEntity.getCircuitLengthEight());
			newConduitConductorCircuitEntity.setCircuitLengthNine(conduitConductorCircuitEntity.getCircuitLengthNine());
			newConduitConductorCircuitEntity.setCircuitLengthNineTwo(conduitConductorCircuitEntity.getCircuitLengthNineTwo());
			newConduitConductorCircuitEntity.setCircuitLengthTen(conduitConductorCircuitEntity.getCircuitLengthTen());
			newConduitConductorCircuitEntity.setCircuitLengthEleven(conduitConductorCircuitEntity.getCircuitLengthEleven());
			newConduitConductorCircuitEntity.setCircuitLengthTwelve(conduitConductorCircuitEntity.getCircuitLengthTwelve());
		}
		return newConduitConductorCircuitEntity;
	}

	public PermitConduitConductorSectionEntity clonePermitConduitConductorSectionEntity(
			PermitConduitConductorSectionEntity newPermitConduitConductorSectionEntity,
			PermitConduitConductorSectionEntity permitConduitConductorSectionEntity) {

		if (newPermitConduitConductorSectionEntity != null && permitConduitConductorSectionEntity != null) {
			newPermitConduitConductorSectionEntity
					.setImgSegmentOne(permitConduitConductorSectionEntity.getImgSegmentOne());
			newPermitConduitConductorSectionEntity
					.setImgSegmentTwo(permitConduitConductorSectionEntity.getImgSegmentTwo());
			newPermitConduitConductorSectionEntity
					.setImgSegmentThree(permitConduitConductorSectionEntity.getImgSegmentThree());
			newPermitConduitConductorSectionEntity
					.setImgSegmentFour(permitConduitConductorSectionEntity.getImgSegmentFour());
			newPermitConduitConductorSectionEntity
					.setImgSegmentFive(permitConduitConductorSectionEntity.getImgSegmentFive());
			newPermitConduitConductorSectionEntity
					.setImgSegmentSix(permitConduitConductorSectionEntity.getImgSegmentSix());
			newPermitConduitConductorSectionEntity
					.setImgSegmentSeven(permitConduitConductorSectionEntity.getImgSegmentSeven());
			newPermitConduitConductorSectionEntity
					.setImgSegmentEight(permitConduitConductorSectionEntity.getImgSegmentEight());
			newPermitConduitConductorSectionEntity
					.setImgSegmentNine(permitConduitConductorSectionEntity.getImgSegmentNine());
			newPermitConduitConductorSectionEntity
					.setConductorQty(permitConduitConductorSectionEntity.getConductorQty());
			newPermitConduitConductorSectionEntity
					.setConductorSize(permitConduitConductorSectionEntity.getConductorSize());
			newPermitConduitConductorSectionEntity
					.setConductorType(permitConduitConductorSectionEntity.getConductorType());
			newPermitConduitConductorSectionEntity.setConduitSize(permitConduitConductorSectionEntity.getConduitSize());
			newPermitConduitConductorSectionEntity.setConduitType(permitConduitConductorSectionEntity.getConduitType());
			newPermitConduitConductorSectionEntity
					.setConductorSizeOther(permitConduitConductorSectionEntity.getConductorSizeOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeOther(permitConduitConductorSectionEntity.getConductorTypeOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeOther(permitConduitConductorSectionEntity.getConduitSizeOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeOther(permitConduitConductorSectionEntity.getConduitTypeOther());
			newPermitConduitConductorSectionEntity
					.setConductorQtyTwo(permitConduitConductorSectionEntity.getConductorQtyTwo());
			newPermitConduitConductorSectionEntity
					.setConductorSizeTwo(permitConduitConductorSectionEntity.getConductorSizeTwo());
			newPermitConduitConductorSectionEntity
					.setConductorTypeTwo(permitConduitConductorSectionEntity.getConductorTypeTwo());
			newPermitConduitConductorSectionEntity
					.setConduitSizeTwo(permitConduitConductorSectionEntity.getConduitSizeTwo());
			newPermitConduitConductorSectionEntity
					.setConduitTypeTwo(permitConduitConductorSectionEntity.getConduitTypeTwo());
			newPermitConduitConductorSectionEntity
					.setConductorSizeTwoOther(permitConduitConductorSectionEntity.getConductorSizeTwoOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeTwoOther(permitConduitConductorSectionEntity.getConductorTypeTwoOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeTwoOther(permitConduitConductorSectionEntity.getConduitSizeTwoOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeTwoOther(permitConduitConductorSectionEntity.getConduitTypeTwoOther());
			newPermitConduitConductorSectionEntity
					.setConductorQtyThree(permitConduitConductorSectionEntity.getConductorQtyThree());
			newPermitConduitConductorSectionEntity
					.setConductorSizeThree(permitConduitConductorSectionEntity.getConductorSizeThree());
			newPermitConduitConductorSectionEntity
					.setConductorTypeThree(permitConduitConductorSectionEntity.getConductorTypeThree());
			newPermitConduitConductorSectionEntity
					.setConduitSizeThree(permitConduitConductorSectionEntity.getConduitSizeThree());
			newPermitConduitConductorSectionEntity
					.setConduitTypeThree(permitConduitConductorSectionEntity.getConduitTypeThree());
			newPermitConduitConductorSectionEntity
					.setConductorSizeThreeOther(permitConduitConductorSectionEntity.getConductorSizeThreeOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeThreeOther(permitConduitConductorSectionEntity.getConductorTypeThreeOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeThreeOther(permitConduitConductorSectionEntity.getConduitSizeThreeOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeThreeOther(permitConduitConductorSectionEntity.getConduitTypeThreeOther());
			newPermitConduitConductorSectionEntity
					.setConductorQtyFour(permitConduitConductorSectionEntity.getConductorQtyFour());
			newPermitConduitConductorSectionEntity
					.setConductorSizeFour(permitConduitConductorSectionEntity.getConductorSizeFour());
			newPermitConduitConductorSectionEntity
					.setConductorTypeFour(permitConduitConductorSectionEntity.getConductorTypeFour());
			newPermitConduitConductorSectionEntity
					.setConduitSizeFour(permitConduitConductorSectionEntity.getConduitSizeFour());
			newPermitConduitConductorSectionEntity
					.setConduitTypeFour(permitConduitConductorSectionEntity.getConduitTypeFour());
			newPermitConduitConductorSectionEntity
					.setConductorSizeFourOther(permitConduitConductorSectionEntity.getConductorSizeFourOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeFourOther(permitConduitConductorSectionEntity.getConductorTypeFourOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeFourOther(permitConduitConductorSectionEntity.getConduitSizeFourOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeFourOther(permitConduitConductorSectionEntity.getConduitTypeFourOther());
			newPermitConduitConductorSectionEntity
					.setConductorQtyFive(permitConduitConductorSectionEntity.getConductorQtyFive());
			newPermitConduitConductorSectionEntity
					.setConductorSizeFive(permitConduitConductorSectionEntity.getConductorSizeFive());
			newPermitConduitConductorSectionEntity
					.setConductorTypeFive(permitConduitConductorSectionEntity.getConductorTypeFive());
			newPermitConduitConductorSectionEntity
					.setConduitSizeFive(permitConduitConductorSectionEntity.getConduitSizeFive());
			newPermitConduitConductorSectionEntity
					.setConduitTypeFive(permitConduitConductorSectionEntity.getConduitTypeFive());
			newPermitConduitConductorSectionEntity
					.setConductorSizeFiveOther(permitConduitConductorSectionEntity.getConductorSizeFiveOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeFiveOther(permitConduitConductorSectionEntity.getConductorTypeFiveOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeFiveOther(permitConduitConductorSectionEntity.getConduitSizeFiveOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeFiveOther(permitConduitConductorSectionEntity.getConduitTypeFiveOther());
			newPermitConduitConductorSectionEntity
					.setConductorQtySix(permitConduitConductorSectionEntity.getConductorQtySix());
			newPermitConduitConductorSectionEntity
					.setConductorSizeSix(permitConduitConductorSectionEntity.getConductorSizeSix());
			newPermitConduitConductorSectionEntity
					.setConductorTypeSix(permitConduitConductorSectionEntity.getConductorTypeSix());
			newPermitConduitConductorSectionEntity
					.setConduitSizeSix(permitConduitConductorSectionEntity.getConduitSizeSix());
			newPermitConduitConductorSectionEntity
					.setConduitTypeSix(permitConduitConductorSectionEntity.getConduitTypeSix());
			newPermitConduitConductorSectionEntity
					.setConductorSizeSixOther(permitConduitConductorSectionEntity.getConductorSizeSixOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeSixOther(permitConduitConductorSectionEntity.getConductorTypeSixOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeSixOther(permitConduitConductorSectionEntity.getConduitSizeSixOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeSixOther(permitConduitConductorSectionEntity.getConduitTypeSixOther());
			newPermitConduitConductorSectionEntity
					.setConductorQtySeven(permitConduitConductorSectionEntity.getConductorQtySeven());
			newPermitConduitConductorSectionEntity
					.setConductorSizeSeven(permitConduitConductorSectionEntity.getConductorSizeSeven());
			newPermitConduitConductorSectionEntity
					.setConductorTypeSeven(permitConduitConductorSectionEntity.getConductorTypeSeven());
			newPermitConduitConductorSectionEntity
					.setConduitSizeSeven(permitConduitConductorSectionEntity.getConduitSizeSeven());
			newPermitConduitConductorSectionEntity
					.setConduitTypeSeven(permitConduitConductorSectionEntity.getConduitTypeSeven());
			newPermitConduitConductorSectionEntity
					.setConductorSizeSevenOther(permitConduitConductorSectionEntity.getConductorSizeSevenOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeSevenOther(permitConduitConductorSectionEntity.getConductorTypeSevenOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeSevenOther(permitConduitConductorSectionEntity.getConduitSizeSevenOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeSevenOther(permitConduitConductorSectionEntity.getConduitTypeSevenOther());
			newPermitConduitConductorSectionEntity
					.setConductorQtyEight(permitConduitConductorSectionEntity.getConductorQtyEight());
			newPermitConduitConductorSectionEntity
					.setConductorSizeEight(permitConduitConductorSectionEntity.getConductorSizeEight());
			newPermitConduitConductorSectionEntity
					.setConductorTypeEight(permitConduitConductorSectionEntity.getConductorTypeEight());
			newPermitConduitConductorSectionEntity
					.setConduitSizeEight(permitConduitConductorSectionEntity.getConduitSizeEight());
			newPermitConduitConductorSectionEntity
					.setConduitTypeEight(permitConduitConductorSectionEntity.getConduitTypeEight());
			newPermitConduitConductorSectionEntity
					.setConductorSizeEightOther(permitConduitConductorSectionEntity.getConductorSizeEightOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeEightOther(permitConduitConductorSectionEntity.getConductorTypeEightOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeEightOther(permitConduitConductorSectionEntity.getConduitSizeEightOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeEightOther(permitConduitConductorSectionEntity.getConduitTypeEightOther());
			newPermitConduitConductorSectionEntity
					.setQtySegmentOne(permitConduitConductorSectionEntity.getQtySegmentOne());
			newPermitConduitConductorSectionEntity
					.setQtySegmentTwo(permitConduitConductorSectionEntity.getQtySegmentTwo());
			newPermitConduitConductorSectionEntity
					.setQtySegmentThree(permitConduitConductorSectionEntity.getQtySegmentThree());
			newPermitConduitConductorSectionEntity
					.setQtySegmentFour(permitConduitConductorSectionEntity.getQtySegmentFour());
			newPermitConduitConductorSectionEntity
					.setQtySegmentFive(permitConduitConductorSectionEntity.getQtySegmentFive());
			newPermitConduitConductorSectionEntity
					.setQtySegmentSix(permitConduitConductorSectionEntity.getQtySegmentSix());
			newPermitConduitConductorSectionEntity
					.setQtySegmentSeven(permitConduitConductorSectionEntity.getQtySegmentSeven());
			newPermitConduitConductorSectionEntity
					.setQtySegmentEight(permitConduitConductorSectionEntity.getQtySegmentEight());
			newPermitConduitConductorSectionEntity
					.setQtySegmentNine(permitConduitConductorSectionEntity.getQtySegmentNine());
			newPermitConduitConductorSectionEntity
					.setQtySegmentTen(permitConduitConductorSectionEntity.getQtySegmentTen());
			newPermitConduitConductorSectionEntity
					.setQtySegmentEleven(permitConduitConductorSectionEntity.getQtySegmentEleven());
			newPermitConduitConductorSectionEntity
					.setConductorTypeTen(permitConduitConductorSectionEntity.getConductorTypeTen());
			newPermitConduitConductorSectionEntity
					.setConductorSizeTen(permitConduitConductorSectionEntity.getConductorSizeTen());
			newPermitConduitConductorSectionEntity
					.setConduitTypeTen(permitConduitConductorSectionEntity.getConduitTypeTen());
			newPermitConduitConductorSectionEntity
					.setConduitSizeTen(permitConduitConductorSectionEntity.getConduitSizeTen());
			newPermitConduitConductorSectionEntity
					.setConductorQtyTen(permitConduitConductorSectionEntity.getConductorQtyTen());
			newPermitConduitConductorSectionEntity
					.setConductorTypeNine(permitConduitConductorSectionEntity.getConductorTypeNine());
			newPermitConduitConductorSectionEntity
					.setConductorSizeNine(permitConduitConductorSectionEntity.getConductorSizeNine());
			newPermitConduitConductorSectionEntity
					.setConduitTypeNine(permitConduitConductorSectionEntity.getConduitTypeNine());
			newPermitConduitConductorSectionEntity
					.setConduitSizeNine(permitConduitConductorSectionEntity.getConduitSizeNine());
			newPermitConduitConductorSectionEntity
					.setConductorQtyNine(permitConduitConductorSectionEntity.getConductorQtyNine());
			newPermitConduitConductorSectionEntity
					.setConductorTypeTwelve(permitConduitConductorSectionEntity.getConductorTypeTwelve());
			newPermitConduitConductorSectionEntity
					.setConductorSizeTwelve(permitConduitConductorSectionEntity.getConductorSizeTwelve());
			newPermitConduitConductorSectionEntity
					.setConduitTypeTwelve(permitConduitConductorSectionEntity.getConduitTypeTwelve());
			newPermitConduitConductorSectionEntity
					.setConduitSizeTwelve(permitConduitConductorSectionEntity.getConduitSizeTwelve());
			newPermitConduitConductorSectionEntity
					.setConductorQtyTwelve(permitConduitConductorSectionEntity.getConductorQtyTwelve());
			newPermitConduitConductorSectionEntity
					.setConductorTypeEleven(permitConduitConductorSectionEntity.getConductorTypeEleven());
			newPermitConduitConductorSectionEntity
					.setConductorSizeEleven(permitConduitConductorSectionEntity.getConductorSizeEleven());
			newPermitConduitConductorSectionEntity
					.setConduitTypeEleven(permitConduitConductorSectionEntity.getConduitTypeEleven());
			newPermitConduitConductorSectionEntity
					.setConduitSizeEleven(permitConduitConductorSectionEntity.getConduitSizeEleven());
			newPermitConduitConductorSectionEntity
					.setConductorQtyEleven(permitConduitConductorSectionEntity.getConductorQtyEleven());
			newPermitConduitConductorSectionEntity
					.setConductorTypeNineOther(permitConduitConductorSectionEntity.getConductorTypeNineOther());
			newPermitConduitConductorSectionEntity
					.setConductorSizeNineOther(permitConduitConductorSectionEntity.getConductorSizeNineOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeNineOther(permitConduitConductorSectionEntity.getConduitTypeNineOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeNineOther(permitConduitConductorSectionEntity.getConduitSizeNineOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeTenOther(permitConduitConductorSectionEntity.getConductorTypeTenOther());
			newPermitConduitConductorSectionEntity
					.setConductorSizeTenOther(permitConduitConductorSectionEntity.getConductorSizeTenOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeTenOther(permitConduitConductorSectionEntity.getConduitTypeTenOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeTenOther(permitConduitConductorSectionEntity.getConduitSizeTenOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeElevenOther(permitConduitConductorSectionEntity.getConductorTypeElevenOther());
			newPermitConduitConductorSectionEntity
					.setConductorSizeElevenOther(permitConduitConductorSectionEntity.getConductorSizeElevenOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeElevenOther(permitConduitConductorSectionEntity.getConduitTypeElevenOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeElevenOther(permitConduitConductorSectionEntity.getConduitSizeElevenOther());
			newPermitConduitConductorSectionEntity
					.setConductorTypeTwelveOther(permitConduitConductorSectionEntity.getConductorTypeTwelveOther());
			newPermitConduitConductorSectionEntity
					.setConductorSizeTwelveOther(permitConduitConductorSectionEntity.getConductorSizeTwelveOther());
			newPermitConduitConductorSectionEntity
					.setConduitTypeTwelveOther(permitConduitConductorSectionEntity.getConduitTypeTwelveOther());
			newPermitConduitConductorSectionEntity
					.setConduitSizeTwelveOther(permitConduitConductorSectionEntity.getConduitSizeTwelveOther());
			
			  /* CR-3227-MOD-001 */
			newPermitConduitConductorSectionEntity.setCircuitEnvironment(permitConduitConductorSectionEntity.getCircuitEnvironment());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentTwo(permitConduitConductorSectionEntity.getCircuitEnvironmentTwo());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentThree(permitConduitConductorSectionEntity.getCircuitEnvironmentThree());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentFour(permitConduitConductorSectionEntity.getCircuitEnvironmentFour());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentFive(permitConduitConductorSectionEntity.getCircuitEnvironmentFive());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentSix(permitConduitConductorSectionEntity.getCircuitEnvironmentSix());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentSeven(permitConduitConductorSectionEntity.getCircuitEnvironmentSeven());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentEight(permitConduitConductorSectionEntity.getCircuitEnvironmentEight());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentNine(permitConduitConductorSectionEntity.getCircuitEnvironmentNine());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentNineTwo(permitConduitConductorSectionEntity.getCircuitEnvironmentNineTwo());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentTen(permitConduitConductorSectionEntity.getCircuitEnvironmentTen());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentEleven(permitConduitConductorSectionEntity.getCircuitEnvironmentEleven());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentTwelve(permitConduitConductorSectionEntity.getCircuitEnvironmentTwelve());
			 /* CR-3227-MOD-001 */
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentTwoChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentTwoChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentThreeChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentThreeChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentFourChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentFourChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentFiveChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentFiveChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentSixChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentSixChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentSevenChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentSevenChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentEightChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentEightChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentNineChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentNineChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentNineTwoChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentNineTwoChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentTenChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentTenChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentElevenChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentElevenChanged());
			newPermitConduitConductorSectionEntity.setCircuitEnvironmentTwelveChanged(permitConduitConductorSectionEntity.getCircuitEnvironmentTwelveChanged());
		    /* CR-3227-MOD-003 */
			newPermitConduitConductorSectionEntity.setCircuitLengthOne(permitConduitConductorSectionEntity.getCircuitLengthOne());
			newPermitConduitConductorSectionEntity.setCircuitLengthTwo(permitConduitConductorSectionEntity.getCircuitLengthTwo());
			newPermitConduitConductorSectionEntity.setCircuitLengthThree(permitConduitConductorSectionEntity.getCircuitLengthThree());
			newPermitConduitConductorSectionEntity.setCircuitLengthFour(permitConduitConductorSectionEntity.getCircuitLengthFour());
			newPermitConduitConductorSectionEntity.setCircuitLengthFive(permitConduitConductorSectionEntity.getCircuitLengthFive());
			newPermitConduitConductorSectionEntity.setCircuitLengthSix(permitConduitConductorSectionEntity.getCircuitLengthSix());
			newPermitConduitConductorSectionEntity.setCircuitLengthSeven(permitConduitConductorSectionEntity.getCircuitLengthSeven());
			newPermitConduitConductorSectionEntity.setCircuitLengthEight(permitConduitConductorSectionEntity.getCircuitLengthEight());
			newPermitConduitConductorSectionEntity.setCircuitLengthNine(permitConduitConductorSectionEntity.getCircuitLengthNine());
			newPermitConduitConductorSectionEntity.setCircuitLengthNineTwo(permitConduitConductorSectionEntity.getCircuitLengthNineTwo());
			newPermitConduitConductorSectionEntity.setCircuitLengthTen(permitConduitConductorSectionEntity.getCircuitLengthTen());
			newPermitConduitConductorSectionEntity.setCircuitLengthEleven(permitConduitConductorSectionEntity.getCircuitLengthEleven());
			newPermitConduitConductorSectionEntity.setCircuitLengthTwelve(permitConduitConductorSectionEntity.getCircuitLengthTwelve());
		}

		return newPermitConduitConductorSectionEntity;
	}
}
