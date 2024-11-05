package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResultPrime;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitCompanyInfoRepository extends JpaRepository<PermitCompanyInfoEntity, Long> {
	
	PermitCompanyInfoEntity findByPermitEntityId(Long idProject);
	PermitCompanyInfoEntity findOneByPermitEntityId(Long idProject);
	PermitCompanyInfoEntity findByWebSocketSessionAndOpenedTrue(String sessionId);
	public static final String PERMIT_COMPANY_INFO_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResultPrime " + 
			" ( u.id, u.accountNumber, u.serviceAgreement, u.existingRate, u.newRate, u.costPaid, u.claimedFederal," + 
			" u.nameDeveloper, u.checkApply, u.kwhUsage, u.authorizatingAdvanced, u.contactHomeowner, u.scir," + 
			" u.systemOwner, u.paceFinanced, u.meterAccess, u.plannedAnnual, u.newService, u.newSubdivition, u.nonProfileStatus," + 
			" u.systemMeetDIH, u.contactType,  u.meterDisco, u.meterBuilding,   u.undertrainedAnimal, u.otherSystem, " + 
			" u.otherSystemText, u.jBoxUsedBetween, u.developersName, u.developmentName,	u.InterconnectionType," + 
			" u.peaceFinancedByEntity,	u.namePartyReceivData, u.textOtherRate, u.textOtherNewRate, u.customerParticipated," + 
			" u.peaceFinancedByEntityOther, u.typeCustomer, u.typeCustomerOther, u.typeCustomerOtherText, u.newRateCommercial," + 
			" u.otherNewRateCommercial, u.snemApplication, u.thisIsNewService, u.developerrsNam, u.developersNameSecond, u.theProjectIsLocated," + 
			" u.developemName, u.projectIs, u.otherProjectIs, u.projectWasPace, u.choosePaceFinanced, u.textOtherChoosePace," + 
			" u.electriccvehichule1, u.electricvehichule2, u.electricvehichule3, u.electricvehichule4, u.electricvehichuleOther, u.otherElectricVe," + 
			" u.applicationType, u.theAcDisconnectIsMoreThan, u.coverageamount, u.insuringcompanyName, u.expectedDate, u.oneOrMoreOfTheAss," + 
			" u.theLocalPermitting, u.requestPGToshutDown, u.RequestPGaEToInstalNewS, u.nameOfDevelopersLease," + 
			" u.clamedfederalIncomeTax, u.whichProgram, u.whatRuleOrRules, u.deEnergizingOfTheService, u.whatDayOfTheWeek, u.whatTimeOfDay," + 
			" u.willYouNeedToObtain, u.describeThePointInt, u.theScirOfTheMain, u.policy, u.howManyHours, u.developerrrsName," + 
			" u.PGaEPersonnelWilleNeed, u.mayOurRepresentativesContact, u.iAuthorizeAdvanced, u.meterOrACDisconnectInBuilding," + 
			" u.unrestrainedAnimal, u.checkTheApplicableOther, u.checkApplicableBoxesOther, u.checkIfTheProposedSystemProduce," + 
			" u.performanceMonitAndRep, u.anExistingSolarOrWind, u.clamedfederalIncomeTaxStr, u.listAnyVariations," + 
			" u.textOtherContractType, u.newRateOthers, u.newRateOthersText, u.uploadCommentsUtility, u.uploadFileExisting," + 
			" u.uploadFileJustification, u.uploadFileListAdreess, u.uploadFileSwitchgear, u.uploadFileInterconnection," + 
			" u.opened, u.openedBy.id)" + 
			" from PermitCompanyInfoEntity u where u.permitEntity.id = :p1";
	
	@Query(value = PERMIT_COMPANY_INFO_RESULT)
	PermitCompanyInfoEntityResultPrime getPermitCompanyInfoEntityResultPrime(@Param("p1") Long idProject);
	
	Long deleteByPermitEntityId(Long id);
}
