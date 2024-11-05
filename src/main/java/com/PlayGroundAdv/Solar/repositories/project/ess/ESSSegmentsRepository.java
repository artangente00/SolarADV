package com.PlayGroundAdv.Solar.repositories.project.ess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSSegments;
import com.PlayGroundAdv.Solar.model.project.ess.ESSSegmentsModel;

@Repository
public interface ESSSegmentsRepository extends JpaRepository<ESSSegments, Long> {

	Long deleteByConnectorId(Long connectorId);

	Long deleteByConnectorIdIn(List<Long> connectorId);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.project.ess.ESSSegmentsModel"
			+ "(u.direction, u.length) from ESSSegments u where u.connector.connectorId = :p1")
	List<ESSSegmentsModel> findByConnector(@Param("p1") String connectorId);

	@Query("SELECT concat(u.id,'') from ESSSegments u where u.connector.project.id = :p1")
	List<String> findByProject(@Param("p1") Long projectId);
}
