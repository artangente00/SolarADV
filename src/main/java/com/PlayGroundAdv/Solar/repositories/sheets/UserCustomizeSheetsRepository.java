package com.PlayGroundAdv.Solar.repositories.sheets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.sheets.UserCustomizeSheets;

public interface UserCustomizeSheetsRepository extends JpaRepository<UserCustomizeSheets, Long> {
	
	Long deleteBySheetIdId(Long sheetId);
	Long deleteBySheetIdIdAndUserIdIdNotIn(Long sheetId, List<Long> usersId);
	
	@Query(value="Select userId.id from UserCustomizeSheets u where u.sheetId.id = :sheetId")
	List<Long> findBySheet(@Param("sheetId") Long sheetId);
}
