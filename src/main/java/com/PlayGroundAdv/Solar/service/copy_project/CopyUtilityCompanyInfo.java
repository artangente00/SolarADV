package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.repositories.PermitCompanyInfoRepository;

@Service
@Transactional
public class CopyUtilityCompanyInfo {

	final PermitCompanyInfoRepository companyInfoRepo;

	public CopyUtilityCompanyInfo(PermitCompanyInfoRepository companyInfoRepo) {
		super();
		this.companyInfoRepo = companyInfoRepo;
	}

	public void copyUtilityCompanyInfo(Long idPermit, Long idNewPermit) {
		try {
			PermitCompanyInfoEntity companyInfoEntity = companyInfoRepo.findByPermitEntityId(idPermit);
			PermitCompanyInfoEntity newCompanyInfoEntity = companyInfoRepo.findByPermitEntityId(idNewPermit);
			newCompanyInfoEntity = clonePermitCompanyInfoEntity(newCompanyInfoEntity, companyInfoEntity);
			companyInfoRepo.save(newCompanyInfoEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PermitCompanyInfoEntity clonePermitCompanyInfoEntity(PermitCompanyInfoEntity newPermitCompanyInfoEntity,
			PermitCompanyInfoEntity permitCompanyInfoEntity) {

		if (newPermitCompanyInfoEntity != null && permitCompanyInfoEntity != null) {
			newPermitCompanyInfoEntity.setAccountNumber(permitCompanyInfoEntity.getAccountNumber());
			newPermitCompanyInfoEntity.setServiceAgreement(permitCompanyInfoEntity.getServiceAgreement());
			newPermitCompanyInfoEntity.setExistingRate(permitCompanyInfoEntity.getExistingRate());
			newPermitCompanyInfoEntity.setNewRate(permitCompanyInfoEntity.getNewRate());
			newPermitCompanyInfoEntity.setCostPaid(permitCompanyInfoEntity.getCostPaid());
			newPermitCompanyInfoEntity.setClaimedFederal(permitCompanyInfoEntity.getClaimedFederal());
			newPermitCompanyInfoEntity.setNameDeveloper(permitCompanyInfoEntity.getNameDeveloper());
			newPermitCompanyInfoEntity.setCheckApply(permitCompanyInfoEntity.getCheckApply());
			newPermitCompanyInfoEntity.setKwhUsage(permitCompanyInfoEntity.getKwhUsage());
			newPermitCompanyInfoEntity.setAuthorizatingAdvanced(permitCompanyInfoEntity.getAuthorizatingAdvanced());
			newPermitCompanyInfoEntity.setContactHomeowner(permitCompanyInfoEntity.getContactHomeowner());
			newPermitCompanyInfoEntity.setScir(permitCompanyInfoEntity.getScir());
			newPermitCompanyInfoEntity.setSystemOwner(permitCompanyInfoEntity.getSystemOwner());
			newPermitCompanyInfoEntity.setPaceFinanced(permitCompanyInfoEntity.getPaceFinanced());
			newPermitCompanyInfoEntity.setMeterAccess(permitCompanyInfoEntity.getMeterAccess());
			newPermitCompanyInfoEntity.setPlannedAnnual(permitCompanyInfoEntity.getPlannedAnnual());
			newPermitCompanyInfoEntity.setNewService(permitCompanyInfoEntity.getNewService());
			newPermitCompanyInfoEntity.setNewSubdivition(permitCompanyInfoEntity.getNewSubdivition());
			newPermitCompanyInfoEntity.setNonProfileStatus(permitCompanyInfoEntity.getNonProfileStatus());
			newPermitCompanyInfoEntity.setSystemMeetDIH(permitCompanyInfoEntity.getSystemMeetDIH());
			newPermitCompanyInfoEntity.setContactType(permitCompanyInfoEntity.getContactType());
			newPermitCompanyInfoEntity.setMeterDisco(permitCompanyInfoEntity.getMeterDisco());
			newPermitCompanyInfoEntity.setMeterBuilding(permitCompanyInfoEntity.getMeterBuilding());
			newPermitCompanyInfoEntity.setUndertrainedAnimal(permitCompanyInfoEntity.getUndertrainedAnimal());
			newPermitCompanyInfoEntity.setOtherSystem(permitCompanyInfoEntity.getOtherSystem());
			newPermitCompanyInfoEntity.setOtherSystemText(permitCompanyInfoEntity.getOtherSystemText());
			newPermitCompanyInfoEntity.setJBoxUsedBetween(permitCompanyInfoEntity.getJBoxUsedBetween());
			newPermitCompanyInfoEntity.setDevelopersName(permitCompanyInfoEntity.getDevelopersName());
			newPermitCompanyInfoEntity.setDevelopmentName(permitCompanyInfoEntity.getDevelopmentName());
			newPermitCompanyInfoEntity.setInterconnectionType(permitCompanyInfoEntity.getInterconnectionType());
			newPermitCompanyInfoEntity.setPeaceFinancedByEntity(permitCompanyInfoEntity.getPeaceFinancedByEntity());
			newPermitCompanyInfoEntity.setNamePartyReceivData(permitCompanyInfoEntity.getNamePartyReceivData());
			newPermitCompanyInfoEntity.setTextOtherRate(permitCompanyInfoEntity.getTextOtherRate());
			newPermitCompanyInfoEntity.setTextOtherNewRate(permitCompanyInfoEntity.getTextOtherNewRate());
			newPermitCompanyInfoEntity.setCustomerParticipated(permitCompanyInfoEntity.getCustomerParticipated());
			newPermitCompanyInfoEntity
					.setPeaceFinancedByEntityOther(permitCompanyInfoEntity.getPeaceFinancedByEntityOther());
			newPermitCompanyInfoEntity.setTypeCustomer(permitCompanyInfoEntity.getTypeCustomer());
			newPermitCompanyInfoEntity.setTypeCustomerOther(permitCompanyInfoEntity.getTypeCustomerOther());
			newPermitCompanyInfoEntity.setTypeCustomerOtherText(permitCompanyInfoEntity.getTypeCustomerOtherText());
			newPermitCompanyInfoEntity.setNewRateCommercial(permitCompanyInfoEntity.getNewRateCommercial());
			newPermitCompanyInfoEntity.setOtherNewRateCommercial(permitCompanyInfoEntity.getOtherNewRateCommercial());
			newPermitCompanyInfoEntity.setSnemApplication(permitCompanyInfoEntity.getSnemApplication());
			newPermitCompanyInfoEntity.setThisIsNewService(permitCompanyInfoEntity.getThisIsNewService());
			newPermitCompanyInfoEntity.setDeveloperrsNam(permitCompanyInfoEntity.getDeveloperrsNam());
			newPermitCompanyInfoEntity.setDevelopersNameSecond(permitCompanyInfoEntity.getDevelopersNameSecond());
			newPermitCompanyInfoEntity.setTheProjectIsLocated(permitCompanyInfoEntity.getTheProjectIsLocated());
			newPermitCompanyInfoEntity.setDevelopemName(permitCompanyInfoEntity.getDevelopemName());
			newPermitCompanyInfoEntity.setProjectIs(permitCompanyInfoEntity.getProjectIs());
			newPermitCompanyInfoEntity.setOtherProjectIs(permitCompanyInfoEntity.getOtherProjectIs());
			newPermitCompanyInfoEntity.setProjectWasPace(permitCompanyInfoEntity.getProjectWasPace());
			newPermitCompanyInfoEntity.setChoosePaceFinanced(permitCompanyInfoEntity.getChoosePaceFinanced());
			newPermitCompanyInfoEntity.setTextOtherChoosePace(permitCompanyInfoEntity.getTextOtherChoosePace());
			newPermitCompanyInfoEntity.setElectriccvehichule1(permitCompanyInfoEntity.getElectriccvehichule1());
			newPermitCompanyInfoEntity.setElectricvehichule2(permitCompanyInfoEntity.getElectricvehichule2());
			newPermitCompanyInfoEntity.setElectricvehichule3(permitCompanyInfoEntity.getElectricvehichule3());
			newPermitCompanyInfoEntity.setElectricvehichule4(permitCompanyInfoEntity.getElectricvehichule4());
			newPermitCompanyInfoEntity.setElectricvehichuleOther(permitCompanyInfoEntity.getElectricvehichuleOther());
			newPermitCompanyInfoEntity.setOtherElectricVe(permitCompanyInfoEntity.getOtherElectricVe());
			newPermitCompanyInfoEntity.setApplicationType(permitCompanyInfoEntity.getApplicationType());
			newPermitCompanyInfoEntity
					.setTheAcDisconnectIsMoreThan(permitCompanyInfoEntity.getTheAcDisconnectIsMoreThan());
			newPermitCompanyInfoEntity.setCoverageamount(permitCompanyInfoEntity.getCoverageamount());
			newPermitCompanyInfoEntity.setInsuringcompanyName(permitCompanyInfoEntity.getInsuringcompanyName());
			newPermitCompanyInfoEntity.setExpectedDate(permitCompanyInfoEntity.getExpectedDate());
			newPermitCompanyInfoEntity.setOneOrMoreOfTheAss(permitCompanyInfoEntity.getOneOrMoreOfTheAss());
			newPermitCompanyInfoEntity.setTheLocalPermitting(permitCompanyInfoEntity.getTheLocalPermitting());
			newPermitCompanyInfoEntity.setRequestPGToshutDown(permitCompanyInfoEntity.getRequestPGToshutDown());
			newPermitCompanyInfoEntity.setRequestPGaEToInstalNewS(permitCompanyInfoEntity.getRequestPGaEToInstalNewS());
			newPermitCompanyInfoEntity.setNameOfDevelopersLease(permitCompanyInfoEntity.getNameOfDevelopersLease());
			newPermitCompanyInfoEntity.setClamedfederalIncomeTax(permitCompanyInfoEntity.getClamedfederalIncomeTax());
			newPermitCompanyInfoEntity.setWhichProgram(permitCompanyInfoEntity.getWhichProgram());
			newPermitCompanyInfoEntity.setWhatRuleOrRules(permitCompanyInfoEntity.getWhatRuleOrRules());
			newPermitCompanyInfoEntity
					.setDeEnergizingOfTheService(permitCompanyInfoEntity.getDeEnergizingOfTheService());
			newPermitCompanyInfoEntity.setWhatDayOfTheWeek(permitCompanyInfoEntity.getWhatDayOfTheWeek());
			newPermitCompanyInfoEntity.setWhatTimeOfDay(permitCompanyInfoEntity.getWhatTimeOfDay());
			newPermitCompanyInfoEntity.setWillYouNeedToObtain(permitCompanyInfoEntity.getWillYouNeedToObtain());
			newPermitCompanyInfoEntity.setDescribeThePointInt(permitCompanyInfoEntity.getDescribeThePointInt());
			newPermitCompanyInfoEntity.setTheScirOfTheMain(permitCompanyInfoEntity.getTheScirOfTheMain());
			newPermitCompanyInfoEntity.setPolicy(permitCompanyInfoEntity.getPolicy());
			newPermitCompanyInfoEntity.setHowManyHours(permitCompanyInfoEntity.getHowManyHours());
			newPermitCompanyInfoEntity.setDeveloperrrsName(permitCompanyInfoEntity.getDeveloperrrsName());
			newPermitCompanyInfoEntity.setPGaEPersonnelWilleNeed(permitCompanyInfoEntity.getPGaEPersonnelWilleNeed());
			newPermitCompanyInfoEntity
					.setMayOurRepresentativesContact(permitCompanyInfoEntity.getMayOurRepresentativesContact());
			newPermitCompanyInfoEntity.setIAuthorizeAdvanced(permitCompanyInfoEntity.getIAuthorizeAdvanced());
			newPermitCompanyInfoEntity
					.setMeterOrACDisconnectInBuilding(permitCompanyInfoEntity.getMeterOrACDisconnectInBuilding());
			newPermitCompanyInfoEntity.setUnrestrainedAnimal(permitCompanyInfoEntity.getUnrestrainedAnimal());
			newPermitCompanyInfoEntity.setCheckTheApplicableOther(permitCompanyInfoEntity.getCheckTheApplicableOther());
			newPermitCompanyInfoEntity
					.setCheckApplicableBoxesOther(permitCompanyInfoEntity.getCheckApplicableBoxesOther());
			newPermitCompanyInfoEntity
					.setCheckIfTheProposedSystemProduce(permitCompanyInfoEntity.getCheckIfTheProposedSystemProduce());
			newPermitCompanyInfoEntity.setPerformanceMonitAndRep(permitCompanyInfoEntity.getPerformanceMonitAndRep());
			newPermitCompanyInfoEntity.setAnExistingSolarOrWind(permitCompanyInfoEntity.getAnExistingSolarOrWind());
			newPermitCompanyInfoEntity
					.setClamedfederalIncomeTaxStr(permitCompanyInfoEntity.getClamedfederalIncomeTaxStr());
			newPermitCompanyInfoEntity.setListAnyVariations(permitCompanyInfoEntity.getListAnyVariations());
			newPermitCompanyInfoEntity.setTextOtherContractType(permitCompanyInfoEntity.getTextOtherContractType());
			newPermitCompanyInfoEntity.setNewRateOthers(permitCompanyInfoEntity.getNewRateOthers());
			newPermitCompanyInfoEntity.setNewRateOthersText(permitCompanyInfoEntity.getNewRateOthersText());
			newPermitCompanyInfoEntity.setAllPagesFile(permitCompanyInfoEntity.getAllPagesFile());
		}
		return newPermitCompanyInfoEntity;

	}
}
