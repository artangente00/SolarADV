package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJContacts;

public interface AHJContactsRepository extends JpaRepository<AHJContacts, Long> {
	AHJContacts findByAhjId(Long ahjId);
}
