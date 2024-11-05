package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.PlayGroundAdv.Solar.entity.ChecklistLocationsEntity;

@Repository
public interface LocationLibraryRepository extends JpaRepository<ChecklistLocationsEntity, Long>, JpaSpecificationExecutor<ChecklistLocationsEntity> {
	
	Page<ChecklistLocationsEntity> findByIdIn(List<Long> listIds, Pageable pageable);

	Page<ChecklistLocationsEntity> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}
