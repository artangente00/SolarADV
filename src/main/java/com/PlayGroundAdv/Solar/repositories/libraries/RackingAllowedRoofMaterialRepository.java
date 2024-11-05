package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.RackingAllowedRoofMaterial;
import org.springframework.stereotype.Repository;

@Repository
public interface RackingAllowedRoofMaterialRepository extends JpaRepository<RackingAllowedRoofMaterial, Long> {
	
	List<RackingAllowedRoofMaterial> findByRailRackingIdAndRoofMaterialIdNotIn(Long railRackingId, List<Long>roofMaterialId);
	
	@Query("SELECT u.roofMaterial.id from RackingAllowedRoofMaterial u WHERE u.railRacking.id = :p1 ")
	List<Long> findAllowedRoofMaterial(@Param("p1") Long idRail);

}
