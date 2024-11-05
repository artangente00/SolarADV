package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ProjectNotes;
import com.PlayGroundAdv.Solar.model.ProjectContactsModel;
import com.PlayGroundAdv.Solar.model.ProjectNotesDto;
import com.PlayGroundAdv.Solar.model.ProjectRequestModel;

@Repository
public interface ProjectNotesRepository extends JpaRepository<ProjectNotes,Long> {

	Boolean existsByProjectId(Long idProject);
	ProjectNotes findByProjectId(Long idProject);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ProjectNotesDto(u.floridaRafterDesign, u.floridaRafterDesignFileName, u.id)"
			+ " from ProjectNotes u where u.project.id= :p1")
	ProjectNotesDto findByProject(@Param("p1") Long idProject);
	
	@Query(value = "SELECT count(*) from ContactsNameEntity u WHERE u.idUser = :p1")
	int getNumberContactName(@Param("p1") AuthentificationEntity user);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ProjectContactsModel("
			+ "  u.contactFirstName, " + "  u.contactLastName,  " + "  u.secondContactFirstName, "
			+ "   u.secondContactLastName, " + "  u.thirdContact, " + "  u.lastNameContact )  "
			+ "  From ContractorInformationEntity u" + "  Where u.authentificationEntity.id = :p1")
	ProjectContactsModel findByUser(@Param("p1") Long idUser);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ProjectContactsModel("
			+ "  u.contactFirstName, " + "  u.contactLastName,  " + "  u.secondContactFirstName, "
			+ "  u.secondContactLastName, " + "  u.thirdContact, " + "  u.lastNameContact, " + "  v.firstname, "
			+ "  v.lastName, " + "  v.projectContactPhone, " + "  v.projectContactEmail )  "
			+ "  From ContractorInformationEntity u, ContactsNameEntity v "
			+ "  Where u.authentificationEntity.id = :p1 AND v.idUser.id = u.authentificationEntity.id ")
	List<ProjectContactsModel> findByAuthenUser(@Param("p1") Long idUser);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ProjectRequestModel(u.id, u.firstNameUser, u.lastNameUser, u.requestTitle, "
			+ " u.requestedBy, u.firstNameProjContact, u.lastNameProjContact, u.request, "
			+ " u.requestMethod, u.dateAddNotif, u.daterequestString, u.time, u.lastUpdated)"
			+ " FROM ProjectRequestEntity u WHERE u.permit.id = :p1 order by u.dateAddNotif DESC")
	List<ProjectRequestModel> findPRequestByPermit(@Param("p1") Long idUser);
	Long deleteByProjectId(Long id);
	
	
}
