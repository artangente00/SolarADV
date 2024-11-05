package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.UtilityBillFiles;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityBillFilesRepository extends JpaRepository<UtilityBillFiles, Long> {

	Long deleteByPermitEntityId(Long id);

}
