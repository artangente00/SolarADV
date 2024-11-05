package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJFireSetbacksRoofMounted;

public interface AHJFireSetbacksRoofMountedRepository extends JpaRepository<AHJFireSetbacksRoofMounted, Long>  {
	AHJFireSetbacksRoofMounted findByAhjId(Long ahjId);
}
