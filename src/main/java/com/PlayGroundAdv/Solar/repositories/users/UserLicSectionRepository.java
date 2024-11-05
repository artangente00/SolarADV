package com.PlayGroundAdv.Solar.repositories.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.users.UserLicSectionsEntity;
import com.PlayGroundAdv.Solar.model.ContractorLicSectionModel;

public interface UserLicSectionRepository extends JpaRepository<UserLicSectionsEntity, Long> {

	List<UserLicSectionsEntity> findByAuthentificationEntityId(Long idUser);

	List<UserLicSectionsEntity> findByAuthentificationEntityIdAndContractorLicenceState(Long idUser, String state);

	public static final String CONTRACTOR_LIC_SECTION_MODEL =  "SELECT new com.PlayGroundAdv.Solar.model.ContractorLicSectionModel( u.isInUSorTerritories, u.contractorLicenceState,u.licenseNumber,"
			+ " u.licTypeCode, u.firstLicTypeCodeOther, u.secondLicTypeCodeOther, u.thirdLicTypeCodeOther, u.licType, u.licTypeOther,u.licenseExpiration,"
			+ " u.qualifyingIndividual, u.qualifyingIndividualOther, u.additionalEmail1, u.additionalEmail2, u.additionalEmail3, u.additionalEmail4)"
			+ " from UserLicSectionsEntity u where u.authentificationEntity.id = :p1";
	@Query(value = CONTRACTOR_LIC_SECTION_MODEL)
	List<ContractorLicSectionModel> findContractorLicense(@Param("p1") Long idUser);
	
	UserLicSectionsEntity findFirstByAuthentificationEntityIdAndContractorLicenceState(Long idUser, String state);

}
