package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

import org.springframework.stereotype.Repository;

@Repository
public interface ProposedSubPanelRepository extends JpaRepository<ProposedSubPanel,Long>, JpaSpecificationExecutor<ProposedSubPanel>{

	// C.I 30-12 Get All ProposedSubPanel where the ID In module ID List
	Page<ProposedSubPanel> findByIdIn(List<Long> proposedSubPanelIDList, Pageable pageable);	

	// C.I 30-12 
	public static final String FILTER_PROPOSED_SUB_PANEL_LIST_MODEL = "SELECT u.id from ProposedSubPanel u where CONCAT('%',upper(u.manufacturer),'%') LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.model),'%') LIKE CONCAT('%',upper(:p2),'%') and (u.id NOT IN :p3 OR :p3  = NULL ) and u.isDeleted = :p4 and (u.id IN :p5 OR :p5  = NULL )";


	@Query(value = FILTER_PROPOSED_SUB_PANEL_LIST_MODEL)
	public List<Long> filterProposedSubPanelListModel(@Param("p1") String manufacturer, @Param("p2") String model,@Param("p3") List<Long> proposedSubPanelList, @Param("p4") Boolean isDeleted, @Param("p5") List<Long> proposedSubPanelFavList);
	
	@Query("SELECT u from ProposedSubPanel u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<ProposedSubPanel> findAllByManufacturerAndModelAndIsDeleted (@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted);
	
	@Query("SELECT u from ProposedSubPanel u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 AND u.id != :p4 ")
	List<ProposedSubPanel> findAllByManufacturerAndModelAndIsDeletedAndIdNot (@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted, @Param("p4") Long id);
	
	@Query("SELECT u from ProposedSubPanel u WHERE TRIM(LOWER(u.manufacturer)) != :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<ProposedSubPanel> findAllByManufacturerNotAndModelAndIsDeleted (@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted);
	
	@Query("SELECT u.model , u.manufacturer from ProposedSubPanel u where u.manufacturer=:p1 and u.model =:p2 ")
	List<String> findModelAndManufacturer(@Param("p1") String manufacturer, @Param("p2") String model);
    
    @Query("SELECT u.isDeleted from ProposedSubPanel u where u.id = :p1")
    Boolean findIfDeleted(@Param("p1") Long id);

	public static final String FIND_BY_PROJECT = "SELECT v from ProposedSubPanelFavLibraryEntity u,ProposedSubPanel v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND v.isDeleted = false AND u.proposedSubPanel.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_PROJECT)
	public List<ProposedSubPanel> findByProject(@Param("p1") Long idPermit);

	List<ProposedSubPanel> findByIsDeleted(boolean b);
	Page<ProposedSubPanel> findByIsDeleted(Boolean b, Pageable pageable);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  ProposedSubPanel u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
}
