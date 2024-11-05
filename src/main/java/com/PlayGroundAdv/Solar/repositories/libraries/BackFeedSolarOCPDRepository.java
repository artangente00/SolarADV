package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.PlayGroundAdv.Solar.entity.BackFeedSolarOCPD;
import com.PlayGroundAdv.Solar.model.BackFeedSolarOCPDEntityModel;
import org.springframework.stereotype.Repository;

@Repository
public interface BackFeedSolarOCPDRepository extends JpaRepository<BackFeedSolarOCPD, Long>, JpaSpecificationExecutor<BackFeedSolarOCPD> {
	
	// F.S REF Change Return Type
	public static final String LIST_BACK_FEED_SOLAR_OCPD = "SELECT new com.PlayGroundAdv.Solar.model.BackFeedSolarOCPDEntityModel(u.id, u.backFeed, u.updated, u.isDeleted, u.hasSuperUserEdit, u.addDate, u.authentificationEntity) from BackFeedSolarOCPD u where u.isDeleted = :p1 order by u.backFeed ASC ";

	@Query(value = LIST_BACK_FEED_SOLAR_OCPD)
	List<BackFeedSolarOCPDEntityModel> getListOfBackFeedSolarOCPD(@Param("p1") Boolean isDeleted);

	// C.I 12-25 Get All BackFeedSolarOCPD where the ID In module ID List
	Page<BackFeedSolarOCPD> findByIdIn(List<Long> roofMaterialTypeIdList, Pageable pageable);

	// C.I 12-25
	public static final String FILTER_BACK_FEED_SOLAR_OCPD_LIST_MODEL = "SELECT u.id from BackFeedSolarOCPD u where ((:p1 !='' And STR(u.backFeed) LIKE :p1) OR (:p1 ='')) and u.isDeleted = :p2 ";

	@Query(value = FILTER_BACK_FEED_SOLAR_OCPD_LIST_MODEL)
	public List<Long> filterBackFeedSolarOCPDListModel(@Param("p1") String backFeed, @Param("p2") Boolean isDeleted);

	BackFeedSolarOCPD findByBackFeedAndIsDeleted(Integer backFeed, boolean isDeleted);

	Long countByBackFeedAndIsDeleted(Integer backFeed, boolean isDeleted);

	Page<BackFeedSolarOCPD> findByIsDeleted(Boolean deleted, Pageable pageable);

	Page<BackFeedSolarOCPD> findByBackFeedAndIsDeleted(Integer backFeed, Boolean deleted, Pageable pageable);
}
