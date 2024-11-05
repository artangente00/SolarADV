package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJGroundMountSetbacks;

public interface AHJGroundMountSetbacksRepository extends JpaRepository<AHJGroundMountSetbacks, Long>  {
	AHJGroundMountSetbacks findByAhjId(Long ahjId);
}
