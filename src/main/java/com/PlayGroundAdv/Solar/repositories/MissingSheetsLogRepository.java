package com.PlayGroundAdv.Solar.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.PlayGroundAdv.Solar.entity.MissingSheetsLogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingSheetsLogRepository extends JpaRepository<MissingSheetsLogEntity,Long>{
	
	List<MissingSheetsLogEntity> findBySubmissionIdAndProjectId(Long submissionId, Long projectId);

	Long deleteByProjectId(Long id);
		
}
