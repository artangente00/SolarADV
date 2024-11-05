package com.PlayGroundAdv.Solar.repositories.sheets;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.TLDSheetLibraryEntity;

@Repository
public interface TLDSheetLibraryRepository
		extends JpaRepository<TLDSheetLibraryEntity, Long>, JpaSpecificationExecutor<TLDSheetLibraryEntity> {

	Page<TLDSheetLibraryEntity> findByIsDeletedOrderByPdfName(Boolean isDel, Pageable pageable);

	Page<TLDSheetLibraryEntity> findByIdIn(List<Long> rSheetIds, Pageable pageable);

	List<TLDSheetLibraryEntity> findByPdfNameAndIsDeleted(String pdfName, Boolean isDel);

	TLDSheetLibraryEntity findOneByPdfNameAndIsDeleted(String pdfName, Boolean isDel);

}
