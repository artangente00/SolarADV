package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJElectrical;

public interface AHJElectricalRepository extends JpaRepository<AHJElectrical, Long>  {
	
	AHJElectrical findByAhjId(Long ahjId);
	
	@Query(value="Select u.reqNoteOnTldForTypeOfAcDisco from AHJElectrical u where u.ahj.id = :p1")
	String findTLDNoteforACD(@Param("p1") Long ahjId);
	
	@Query(value="Select u.groundWireMinOnRoof from AHJElectrical u where u.ahj.id = :p1")
	String findMinGroundWireSize(@Param("p1") Long ahjId);
}
