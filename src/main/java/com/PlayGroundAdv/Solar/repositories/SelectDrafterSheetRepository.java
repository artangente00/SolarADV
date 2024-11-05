package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.SelectDrafterSheet;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectDrafterSheetRepository extends JpaRepository<SelectDrafterSheet,Long>{

	List<SelectDrafterSheet> findByIdPermitId(Long permitId);
	
	@Query("SELECT u.pageSheet FROM SelectDrafterSheet u WHERE u.idPermit.id = :p1 AND u.pageNumber = :p2")
	String findPageSheetByIdPermitAndPageNumber(@Param("p1") Long idPermit, @Param("p2") Integer pageNumber);

	Long deleteByIdPermitId(Long id);
}
