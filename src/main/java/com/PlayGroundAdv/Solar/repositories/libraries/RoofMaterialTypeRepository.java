package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.model.libraries.RoofMaterialTypeModel;

@Repository
public interface RoofMaterialTypeRepository extends JpaRepository<RoofMaterialType,Long>, JpaSpecificationExecutor<RoofMaterialType> {
	
	List<RoofMaterialType> findByIsDeletedOrderByTypeRoofAsc(Boolean isDeleted);
	List<RoofMaterialType> findByIdIn(List<Long> roofIds);
	// C.I 12-25 Get All RoofMaterialType where the ID In module ID List
	Page<RoofMaterialType> findByIdIn(List<Long> roofMaterialList, Pageable pageable);
	
	Page<RoofMaterialType> findByIsDeleted(Boolean isDeleted, Pageable pageable);

	// C.I 12-25
	public static final String FILTER_ROOF_MATERIAL_TYPE_LIST_MODEL = "SELECT u.id from RoofMaterialType u where CONCAT('%',upper(u.typeRoof),'%') LIKE CONCAT('%',upper(:p1),'%') and (u.id NOT IN :p2 OR :p2  = NULL ) and u.isDeleted = :p3 ";


	@Query(value = FILTER_ROOF_MATERIAL_TYPE_LIST_MODEL)
	public List<Long> filterRoofMaterialTypeListModel(@Param("p1") String typeRoof,@Param("p2") List<Long> roofMaterialTypeList, @Param("p3") Boolean isDeleted);
	
	@Query("SELECT u.typeRoof from RoofMaterialType u WHERE u.id= :p1 ")
	String getTypeRoofById(@Param("p1") Long idTypeRoof);
	
	@Query("SELECT  u.typeRoof from RoofMaterialType u where u.typeRoof=:p1")
	List<String> getTypeRoofByTypeRoof(@Param("p1") String typeRoof);
	
	RoofMaterialType findByTypeRoofAndIsDeleted(String typeRoof ,boolean isDeleted);
	Long countByTypeRoofAndIsDeleted(String typeRoof ,boolean isDeleted);
	
	public static final String GET_ALL_MODELS = "Select new com.PlayGroundAdv.Solar.model.libraries.RoofMaterialTypeModel(u.id, u.typeRoof) FROM RoofMaterialType u Where u.isDeleted = false ORDER BY u.typeRoof";

	@Query(value = GET_ALL_MODELS)
	public List<RoofMaterialTypeModel> getAllTypes();
}
