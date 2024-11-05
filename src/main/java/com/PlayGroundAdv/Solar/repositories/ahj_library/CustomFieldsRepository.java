package com.PlayGroundAdv.Solar.repositories.ahj_library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJCustomFields;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJCustomFieldsModel;

public interface CustomFieldsRepository extends JpaRepository<AHJCustomFields, Long> {

	AHJCustomFields findByColumnsColumnNameAndAhjId(String columnName, Long ahjId);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.ahj_library.AHJCustomFieldsModel(CASE WHEN (u.columns.subheaderName is not NULL) THEN u.columns.subheaderName ELSE u.columns.headerName END,"
			+ " u.columns.columnName, u.text) from AHJCustomFields u WHERE u.ahj.id = :p1")
	List<AHJCustomFieldsModel> findColumnByAHJ(@Param("p1") Long ahjId);
}
