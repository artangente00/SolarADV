package com.PlayGroundAdv.Solar.service.user_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.entity.users.UserLicSectionsEntity;
import com.PlayGroundAdv.Solar.model.ContractorLicSectionModel;
import com.PlayGroundAdv.Solar.model.UserLicSectionModel;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserLicSectionRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class LicenseSettingService {

	final AuthenticationRepository authRepo;
	final ContractorInformationRepository contractorInfoRepo;
	final UserLicSectionRepository userLicenseRepo;
	final CheckValueTypesService checkValue;
	
	public LicenseSettingService(AuthenticationRepository authRepo, ContractorInformationRepository contractorInfoRepo,
			UserLicSectionRepository userLicenseRepo, CheckValueTypesService checkValue) {
		super();
		this.authRepo = authRepo;
		this.contractorInfoRepo = contractorInfoRepo;
		this.userLicenseRepo = userLicenseRepo;
		this.checkValue = checkValue;
	}

	public List<ContractorLicSectionModel> getUserLicSections(Long idUser) {
		try {
			List<ContractorLicSectionModel> contractorLicSection = userLicenseRepo.findContractorLicense(idUser);

			if (contractorLicSection.isEmpty()) {
				ContractorInformationEntity userInfo = contractorInfoRepo.findByAuthentificationEntityId(idUser);

				ContractorLicSectionModel firstContractorLicSection = new ContractorLicSectionModel();
				if (checkValue.NotEquals(userInfo.getLicenseNumber(), "")) {
					firstContractorLicSection.setLicenseNumber(userInfo.getLicenseNumber());
				}
				if (checkValue.NotEquals(userInfo.getContractorLicenceState(), "")) {
					firstContractorLicSection.setContractorLicenceState(userInfo.getContractorLicenceState());
				}
				if (checkValue.NotEquals(userInfo.getLicenseExpiration(), "")) {
					firstContractorLicSection.setLicenseExpiration(userInfo.getLicenseExpiration());
				}
				if (checkValue.NotEquals(userInfo.getQualifyingIndividual(), "")) {
					firstContractorLicSection.setQualifyingIndividual(userInfo.getQualifyingIndividual());
				}
				String[] licTypeCode = new String[3];
				String[] licType = new String[3];
				if (checkValue.Equals(userInfo.getContractorLicenceState(), "CA")) {
					if (Boolean.TRUE.equals(userInfo.getContractorLic())) {
						licTypeCode[0] = "C46";
						licType[0] = "Solar";
					}
					if (Boolean.TRUE.equals(userInfo.getContractorLicC10())) {
						licTypeCode[1] = "C10";
						licType[1] = "Electrical";
					}
					if (Boolean.TRUE.equals(userInfo.getContractorLicB())) {
						licTypeCode[2] = "B";
						licType[2] = "General Building";
					}
					firstContractorLicSection.setLicTypeCode(licTypeCode);
					firstContractorLicSection.setLicType(licType);
				}
				contractorLicSection.add(firstContractorLicSection);
			}
			return contractorLicSection;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	//
	public String updateUserLicSection(UserLicSectionModel userLicSections) {

		try {
			AuthentificationEntity authentificationEntity = authRepo.findById(userLicSections.getUserId()).orElse(null);
			List<UserLicSectionsEntity> sectionList = userLicenseRepo
					.findByAuthentificationEntityId(userLicSections.getUserId());

			if (!sectionList.isEmpty()) {
				userLicenseRepo.deleteAll(sectionList);
			}

			for (int i = 0; userLicSections.sectionLength != null && i < userLicSections.sectionLength; i++) {
				UserLicSectionsEntity sectionsEntity = new UserLicSectionsEntity();
				sectionsEntity.setIsInUSorTerritories(userLicSections.getIsInUSorTerritories()[i]);
				sectionsEntity.setContractorLicenceState(userLicSections.getContractorLicenceState()[i]);
				sectionsEntity.setLicenseNumber(userLicSections.getLicenseNumber()[i]);
				sectionsEntity.setLicTypeCode(userLicSections.getLicTypeCode()[i]);
				sectionsEntity.setFirstLicTypeCodeOther(userLicSections.getFirstLicTypeCodeOther()[i]);
				sectionsEntity.setSecondLicTypeCodeOther(userLicSections.getSecondLicTypeCodeOther()[i]);
				sectionsEntity.setThirdLicTypeCodeOther(userLicSections.getThirdLicTypeCodeOther()[i]);
				sectionsEntity.setLicType(userLicSections.getLicType()[i]);
				sectionsEntity.setLicTypeOther(userLicSections.getLicTypeOther()[i]);
				sectionsEntity.setLicenseExpiration(userLicSections.getLicenseExpiration()[i]);
				sectionsEntity.setQualifyingIndividual(userLicSections.getQualifyingIndividual()[i]);
				sectionsEntity.setQualifyingIndividualOther(userLicSections.getQualifyingIndividualOther()[i]);
				sectionsEntity.setAuthentificationEntity(authentificationEntity);
				sectionsEntity.setAdditionalEmail1(userLicSections.getAdditionalEmail1()[i]);
				sectionsEntity.setAdditionalEmail2(userLicSections.getAdditionalEmail2()[i]);
				sectionsEntity.setAdditionalEmail3(userLicSections.getAdditionalEmail3()[i]);
				sectionsEntity.setAdditionalEmail4(userLicSections.getAdditionalEmail4()[i]);
				userLicenseRepo.save(sectionsEntity);
			}
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}
	
}
