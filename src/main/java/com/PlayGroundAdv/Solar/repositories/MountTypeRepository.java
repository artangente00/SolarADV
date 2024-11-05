package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.MountingTypeEntity;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;

@Repository
public interface MountTypeRepository extends JpaRepository<MountingTypeEntity ,Long>{
	
	List<MountingTypeEntity>findByIdRailId(Long id);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.ComponentModel(u.idRail.id, u.idRail.manufacturer, u.idRail.model) from MountingTypeEntity u WHERE  u.mountingType IN :p1 AND u.idRail.isDeleted = false group by u.idRail.id, u.idRail.manufacturer, u.idRail.model order by u.idRail.manufacturer, u.idRail.model ")
	List<ComponentModel> findRackingByMounting(@Param("p1") List<String> typeOfSystemList);

	// G.R 08/24/2021
	@Query("SELECT u.idRail.id from MountingTypeEntity u WHERE u.mountingType IN :p1 AND u.idRail.isDeleted = false group by u.idRail.id ")
	List<Long> findRackingByMountingTypes(@Param("p1") List<String> typeOfSystemList);
	
	@Query("SELECT u.mountingType from MountingTypeEntity u WHERE u.idRail.id = :p1 ")
	List<String> findMountingType(@Param("p1") Long idRail);

}
