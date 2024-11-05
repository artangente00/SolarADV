package com.PlayGroundAdv.Solar.repositories.project;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.AccountingsEntity;

public interface AccountingsRepository extends JpaRepository<AccountingsEntity, Long> {

	AccountingsEntity findByPermitId(Long projectId);

	Long deleteByPermitId(Long id);
}
