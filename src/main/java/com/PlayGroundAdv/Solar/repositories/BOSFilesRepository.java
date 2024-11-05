package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.BOSFiles;
import org.springframework.stereotype.Repository;

@Repository
public interface BOSFilesRepository extends JpaRepository<BOSFiles, Long> {

	Long deleteByPermitEntityId(Long id);

}
