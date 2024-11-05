package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJPVMeter;

public interface AHJPVMeterRepository extends JpaRepository<AHJPVMeter, Long>  {
	AHJPVMeter findByAhjId(Long ahjId);
}
