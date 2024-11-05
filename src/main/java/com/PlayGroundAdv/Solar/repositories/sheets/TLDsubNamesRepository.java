package com.PlayGroundAdv.Solar.repositories.sheets;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.TldSubNamesEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TLDsubNamesRepository extends JpaRepository<TldSubNamesEntity, Long>{
	
	Page<TldSubNamesEntity> findByIsDeleted(Boolean isDel, Pageable pageable);
	Page<TldSubNamesEntity> findByIsDeletedAndSubNameIgnoreCaseContaining(Boolean isDel, String subName,Pageable pageable);
	TldSubNamesEntity findBySubNameAndIsDeleted(String subName, Boolean isDeleted);
	Boolean existsBySubNameAndIsDeletedAndIdNot(String subName, Boolean isDeleted, Long id);
	
	@Query("select e.subName from TldSubNamesEntity e WHERE e.isDeleted = :isDeleted" )
	List<String> findSubNameByIsDeleted(@Param("isDeleted") Boolean isDeleted);

}
