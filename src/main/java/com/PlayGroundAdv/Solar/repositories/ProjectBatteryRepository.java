package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.ProjectBattery;
import com.PlayGroundAdv.Solar.entity.ProjectBatteryKey;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;

public interface ProjectBatteryRepository extends JpaRepository<ProjectBattery, ProjectBatteryKey> {

	@Query(value = "SELECT u.projectId.project.id from ProjectBattery u WHERE u.batteryId.id = :p1 group by u.projectId.project.id")
	public List<Long> findProjectsByBattery(@Param("p1") Long id);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel "
			+ " ( u.homeOwnLastName,  u.homeOwnName,  u.projectName,  u.status, v.firstName,  v.lastName)"
			+ " from PermitEntity u,  AuthentificationEntity v, ProjectBattery b WHERE b.batteryId.id = :p1"
			+ " and u.isDeleted  = false and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = false"
			+ " and u.id = b.projectId.project.id and u.authentificationEntity.id = v.id")
	public List<ProjectForLibrariesModel> findProjectByBattery(@Param("p1") Long id);
}
