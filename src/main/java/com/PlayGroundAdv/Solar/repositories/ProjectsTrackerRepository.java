package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.ProjectsTrackerEntity;
import com.PlayGroundAdv.Solar.model.InterconnectionRequest;

import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsTrackerRepository extends JpaRepository<ProjectsTrackerEntity,Long> {

	ProjectsTrackerEntity findByPermitId(Long idPermit);
	List<ProjectsTrackerEntity> findAllByPermitId(Long idPermit);
	ProjectsTrackerEntity findOneByPermitId(Long idPermit);
	Long countByPermitId(Long idPermit);
	
	@Query("SELECT u from ProjectsTrackerEntity u WHERE (u.permit.status =:p1 OR u.permit.status =:p2 OR u.permit.status =:p3 OR u.permit.status =:p4) AND u.permit.isDeleted =:p5 ORDER BY u.permit.creationPermitDate DESC ")
	List<ProjectsTrackerEntity> getProjectsTrackerEntity(@Param("p1") String status0, @Param("p2") String status1, @Param("p3") String status2, @Param("p4") String status3, @Param("p5") Boolean isDel);

	@Query("SELECT u from ProjectsTrackerEntity u WHERE (u.permit.status =:p1 OR u.permit.status =:p2 OR u.permit.status =:p3 OR u.permit.status =:p4) AND u.permit.isDeleted =:p5 AND u.permit.authentificationEntity.id =:p6 ORDER BY u.permit.creationPermitDate DESC ")
	List<ProjectsTrackerEntity> getProjectsTrackerEntityByContractor(@Param("p1") String status0, @Param("p2") String status1, @Param("p3") String status2, @Param("p4") String status3, @Param("p5") Boolean isDel, @Param("p6") Long id);

	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.InterconnectionRequest  (u.id, u.homeOwnName, u.authentificationEntity.firstName, u.authentificationEntity.company, w.utilityCompanyName, v.applicationType, w.phone, w.utilityNumber, u.homeOwnLastName, u.projectName) "
			+ " FROM  PermitEntity u, PermitCompanyInfoEntity v, ElectricalUtilityEntity w, PermitHomeSiteInfoEntity y WHERE y.permitEntity.id = u.id AND STR(w.id)=y.utilityCompanyName AND v.permitEntity.id = u.id AND u.isDeleted =:p1 ORDER BY u.creationPermitDate DESC ")
	List<InterconnectionRequest> getprojectInterconection(@Param("p1") Boolean isDel);
	Long deleteByPermitId(Long id);

}
