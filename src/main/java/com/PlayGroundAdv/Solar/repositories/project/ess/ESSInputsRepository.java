package com.PlayGroundAdv.Solar.repositories.project.ess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSInputs;
import com.PlayGroundAdv.Solar.model.project.ess.ESSInputsModel;

public interface ESSInputsRepository extends JpaRepository<ESSInputs, Long> {

	Long deleteByNodeId(Long nodeId);
	Long deleteByNodeIdIn(List<Long> nodeId);
	List<ESSInputs> findByNodeId(Long nodeId);

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.project.ess.ESSInputsModel(n.id, n.value, n.marginLeft, n.marginTop) from ESSInputs n where n.node.nodeId = :p1")
	List<ESSInputsModel> findByNode(@Param("p1") String nodeId);

	@Query(value = "SELECT concat(n.id,'') from ESSInputs n where n.node.project.id = :p1")
	List<String> findByProject(@Param("p1") Long projectId);
}
