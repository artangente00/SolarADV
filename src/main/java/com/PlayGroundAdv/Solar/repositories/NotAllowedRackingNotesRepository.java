package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.NotAllowedRackingNotes;
import org.springframework.stereotype.Repository;

@Repository
public interface NotAllowedRackingNotesRepository extends JpaRepository<NotAllowedRackingNotes, Long> {
	
	
	NotAllowedRackingNotes findByProjectId(Long projectId);

	Long deleteByProjectId(Long id);
}
