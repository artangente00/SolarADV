package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.PlayGroundAdv.Solar.entity.HistoryActivityEntity;
import com.PlayGroundAdv.Solar.model.HistoricActivityResult;

@Repository
public interface HistoryActivityRepository extends JpaRepository<HistoryActivityEntity ,Long>{


	@Query("SELECT u "
			+ " from HistoryActivityEntity u, AuthentificationEntity v "
			+ " where u.idUserCo = v.id "
			+ " and( lower(u.typeAction) like %:p1% "
			+ " or lower(CONCAT(v.firstName, ' ',v.lastName)) like %:p1% "
			+ " or lower(u.openDate) like %:p1% "
			+ " or TO_CHAR(u.date, 'dd/mm/yyyy') LIKE  %:p1%) ")
	Page<HistoryActivityEntity> findByInformations(@Param("p1") String filtredValue, Pageable pageable);
	
	@Query("Select new com.PlayGroundAdv.Solar.model.HistoricActivityResult("
			   + "u.date,  "
			   + "u.ipUserCo, " 
			   + "u.timeZoneUserCo, " 
			   + "u.typeAction, " 
			   + "u.idUserCo.firstName,"
			   + "u.idUserCo.lastName, "
			   + "u.numTab, "
			   + "u.sessionId, "
			   + "u.openDate, "
			   + "u.isSuccess ) "
			   + "FROM HistoryActivityEntity u WHERE SPLIT_PART(u.typeAction,';' , 1) = :p1  ORDER BY u.date")
	List<HistoricActivityResult> findlibrariesHistoricTable(@Param("p1") String libraries);
	
}
