package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.AuthorityHavingJurisdctionEntity;

@Repository
public interface AuthorityHavingJurisdctionRepository extends JpaRepository<AuthorityHavingJurisdctionEntity, Long> {

	AuthorityHavingJurisdctionEntity findFirstByZipCodes(String zipCodes);
}
