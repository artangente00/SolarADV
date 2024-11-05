package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJPreApprovalReq;

public interface AHJPreApprovalReqRepository extends JpaRepository<AHJPreApprovalReq, Long> {
	AHJPreApprovalReq findByAhjId(Long ahjId);
}
