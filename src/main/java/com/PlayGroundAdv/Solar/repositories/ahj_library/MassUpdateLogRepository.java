package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.MassUpdateLog;

public interface MassUpdateLogRepository extends JpaRepository<MassUpdateLog,Long> {

	MassUpdateLog findTopByStateAndGoverningCodeAndMassUpdateCancelledOrderByUpdateDateDesc(String state, String cellName, Boolean cancelled);
}
