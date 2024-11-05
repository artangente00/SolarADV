package com.PlayGroundAdv.Solar.repositories.sheets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.PlayGroundAdv.Solar.entity.AccountBuildEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface AccountBuildRepository extends JpaRepository<AccountBuildEntity, Long>, JpaSpecificationExecutor<AccountBuildEntity>  {
	
	Page<AccountBuildEntity> findByIsDeletedOrderByPdfName(Boolean isDel, Pageable pageable);
	Page<AccountBuildEntity> findByIdIn(List<Long> accountBuildListId, Pageable pageable);	
	
	@Query(value = "SELECT u from AccountBuildEntity u where (:p1 = '' OR (u.pdfName is not NULL AND upper(u.pdfName) LIKE CONCAT('%',:p1,'%')))"
			+ " AND (:p2 = '' OR (u.accountName is not NULL AND upper(u.accountName) LIKE CONCAT('%',:p2,'%'))) "
			+ " AND u.isDeleted =:p3")
	List<AccountBuildEntity> searchAccountBuildSheet(@Param("p1") String pdfName, @Param("p2") String accountName,
			@Param("p3") Boolean isDeleted);
	
	List<AccountBuildEntity> findByAccountAndIsDeleted(Long id, Boolean isDeleted);
	Page<AccountBuildEntity> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}
