package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJColumnsEntity;

public interface AHJColumnsRepository extends JpaRepository<AHJColumnsEntity, Long> {

	Boolean existsByColumnTitleAndHeaderNameAndSubheaderName(String columnTitle, String headerName, String subHeaderName);

	AHJColumnsEntity findByColumnName (String columnName);
	
	@Query(value = "SELECT u.columnTitle from AHJColumnsEntity u WHERE u.columnName = :p1")
	String findColumnTitleByName(@Param("p1") String id);

}
