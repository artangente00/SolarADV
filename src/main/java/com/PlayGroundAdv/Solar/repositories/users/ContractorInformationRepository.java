package com.PlayGroundAdv.Solar.repositories.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.model.AddContactRfiModel;
import com.PlayGroundAdv.Solar.model.ProjectContactsEmailModel;
import org.springframework.stereotype.Repository;
import com.PlayGroundAdv.Solar.model.ProjectContactsModel;

@Repository
public interface ContractorInformationRepository extends JpaRepository<ContractorInformationEntity ,Long>{

	ContractorInformationEntity findByAuthentificationEntityId(Long userID);
	String findContactEmailByAuthentificationEntityId(Long id);
	String findSecondContactEmailByAuthentificationEntityId(Long id);
	String findThirdContactEmailByAuthentificationEntityId(Long id);
	
	public static final String FIND_BY_CREATION_PERMIT_DATE = "SELECT new com.PlayGroundAdv.Solar.model.ProjectContactsEmailModel(u.contactEmail, u.secondContactEmail, u.thirdContactEmail )" + 
			" From ContractorInformationEntity u" + 
			" Where u.authentificationEntity.id = :p1";
	
	@Query(value = FIND_BY_CREATION_PERMIT_DATE)
	ProjectContactsEmailModel getUserContacts(@Param("p1") Long userID);
	
	@Query(value = "SELECT u.compPhoneNum From ContractorInformationEntity u Where u.authentificationEntity.id = :p1")
	String findPhoneNumber(@Param("p1") Long userID);
	
	@Query(value = "SELECT u.contactEmail From ContractorInformationEntity u Where u.authentificationEntity.id = :p1")
	String findContactEmail(@Param("p1") Long userID);
	
	@Query(value = "SELECT u.secondContactEmail From ContractorInformationEntity u Where u.authentificationEntity.id = :p1")
	String findSecondContactEmail(@Param("p1") Long userID);
	
	@Query(value = "SELECT u.thirdContactEmail From ContractorInformationEntity u Where u.authentificationEntity.id = :p1")
	String findThirdContactEmail(@Param("p1") Long userID);

	
	@Query(value = " SELECT new com.PlayGroundAdv.Solar.model.ProjectContactsModel("
			+ "  u.contactFirstName, " + "  u.contactLastName,  " + "  u.secondContactFirstName, "
			+ "  u.secondContactLastName, " + "  u.thirdContact, " + "  u.lastNameContact )  "
			+ "  From ContractorInformationEntity u" + "  Where u.authentificationEntity.id = :p1")
	ProjectContactsModel getProjectContactMod(@Param("p1") Long userId);
	
	@Query(value = " SELECT new com.PlayGroundAdv.Solar.model.ProjectContactsModel("
			+ "  u.contactFirstName, " + "  u.contactLastName,  " + "  u.secondContactFirstName, "
			+ "  u.secondContactLastName, " + "  u.thirdContact, " + "  u.lastNameContact, " + "  v.firstname, "
			+ "  v.lastName, " + "  v.projectContactPhone, " + "  v.projectContactEmail )  "
			+ "  From ContractorInformationEntity u, ContactsNameEntity v "
			+ "  Where u.authentificationEntity.id = :p1 AND v.idUser.id = u.authentificationEntity.id ")
	List<ProjectContactsModel> getProjectsContactModel(@Param("p1") Long userId);
	
	@Query(value = " SELECT new com.PlayGroundAdv.Solar.model.AddContactRfiModel(" + " u.contactEmail, "
			+ " u.isProjectAddInclud, " + " u.secondContactEmail, " + " u.thirdContactEmail )"
			+ " FROM ContractorInformationEntity u  " + " WHERE u.authentificationEntity.id = :p1 ")
	AddContactRfiModel getContactRfiModel(@Param("p1") Long userId);
	String findContactEmailByAuthentificationEntity(Long userID);
	
}
