package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.AdditionalInfoFiles;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalInfoFilesRepository extends JpaRepository<AdditionalInfoFiles, Long> {
    
    @Query("SELECT u.fileName from AdditionalInfoFiles u where u.permitEntity.id = :p1")
    List<String> getAdditionalInfoFiles(@Param("p1") Long projectId);

    Long deleteByPermitEntityId(Long id);
}
