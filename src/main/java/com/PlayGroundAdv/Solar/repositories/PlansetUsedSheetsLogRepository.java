package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PlansetUsedSheetsLog;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansetUsedSheetsLogRepository extends JpaRepository<PlansetUsedSheetsLog,Long> {
	
	
	
	@Query("SELECT u "
			+ " from PlansetUsedSheetsLog u, PermitEntity v "
			+ " where u.project = v.id "
			+ " and( lower(CONCAT(v.homeOwnLastName,', ', v.homeOwnName)) like %:p1% "
			+ " or lower(v.projectName) like %:p1% "
			+ " or lower(u.pv_001) like %:p1% "
			+ " or lower(u.n_001) like %:p1% "
			+ " or lower(u.pv_100R) like %:p1% "
			+ " or lower(u.pv_100G) like %:p1% "
			+ " or lower(u.pv_101G) like %:p1% "
			+ " or lower(u.s_100) like %:p1% "
			+ " or lower(u.s_200) like %:p1% "
			+ " or lower(u.s_201) like %:p1% "
			+ " or lower(u.s_300) like %:p1% "
			+ " or lower(u.e_001) like %:p1% "
			+ " or lower(u.e_002) like %:p1% "
			+ " or lower(u.e_003) like %:p1% "
			+ " or lower(u.e_100) like %:p1% "
			+ " or lower(u.p_001) like %:p1% "
			+ " or lower(u.p_002) like %:p1% "
			+ " or TO_CHAR(u.date, 'dd/mm/yyyy') LIKE  %:p1%)")
	List<PlansetUsedSheetsLog> findFiltred(@Param("p1") String filtredValue);

	Long deleteByProjectId(Long id);


}
