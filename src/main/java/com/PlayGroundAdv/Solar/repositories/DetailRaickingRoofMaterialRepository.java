package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.DetailRaickingRoofMaterial;

public interface DetailRaickingRoofMaterialRepository extends JpaRepository<DetailRaickingRoofMaterial, Long> {

	Long deleteByDetailRackingIdId(Long detailRackingId);
	Long deleteByDetailRackingIdIdAndRoofMaterialIdIdNotIn(Long detailRackingId, List<Long> roofMaterialIds);
	@Query(value = "SELECT u.roofMaterialId.id from DetailRaickingRoofMaterial u where u.detailRackingId.id = :p1")
	List<Long> findRoofMaterialIds(@Param("p1") Long detailRackingId);
}
