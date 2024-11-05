package com.PlayGroundAdv.Solar.repositories.project.ess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSNodes;
import com.PlayGroundAdv.Solar.model.project.ess.ESSNodesModel;

@Repository
public interface ESSNodesRepository extends JpaRepository<ESSNodes, Long> {

	ESSNodes findByNodeId(String nodeId);
	
	List<ESSNodes> findByProjectId(Long projectId);

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.project.ess.ESSNodesModel(u.nodeId, u.title, u.type, u.offsetX, u.offsetY, u.removed)"
			+ " from ESSNodes u where u.project.id = :p1")
	List<ESSNodesModel> findByProject(@Param("p1") Long projectId);

	Long deleteByProjectIdAndNodeIdNotIn(Long projectId, List<String> ids);

	Long deleteByProjectId(Long projectId);
	@Query(value = "SELECT u.title from ESSNodes u where u.nodeId = :p1")
	String findTitleById(@Param("p1") String nodeId);

	@Query(value = "SELECT count(*) >0  from ESSNodes u where u.nodeId like concat('%-',:p1)")
	boolean idExit(@Param("p1") Integer random_int);
}
