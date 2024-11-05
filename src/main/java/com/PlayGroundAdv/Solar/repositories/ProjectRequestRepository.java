package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.ProjectRequestEntity;
import com.PlayGroundAdv.Solar.model.ProjectRequestModel;

@Repository
public interface ProjectRequestRepository extends JpaRepository<ProjectRequestEntity, Long>{
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ProjectRequestModel(u.id, u.firstNameUser, u.lastNameUser, u.requestTitle, "
			+ " u.requestedBy, u.firstNameProjContact, u.lastNameProjContact, u.request, "
			+ " u.requestMethod, u.dateAddNotif, u.daterequestString, u.time, u.lastUpdated)"
			+ " FROM ProjectRequestEntity u WHERE u.permit.id = :p1 order by u.dateAddNotif DESC")
	List<ProjectRequestModel> getProjectRequestModel(@Param("p1") Long userId);

	Long deleteByPermitId(Long id);

}
