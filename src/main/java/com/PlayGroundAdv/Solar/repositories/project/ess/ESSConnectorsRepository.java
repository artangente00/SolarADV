package com.PlayGroundAdv.Solar.repositories.project.ess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.project.ess.ESSConnectorsModel;

public interface ESSConnectorsRepository extends JpaRepository<ESSConnectors, Long> {

	ESSConnectors findByConnectorId(String connectorId);

	ESSConnectors findByIndexAndProjectId(Integer index, Long projectId);

	List<ESSConnectors> findByProjectId(Long projectId);

	List<ESSConnectors> findByIndexGreaterThanAndProjectIdOrderByIndex(Integer index, Long projectId);

	List<ESSConnectors> findByIndexLessThanAndProjectIdOrderByIndex(Integer index, Long projectId);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.project.ess.ESSConnectorsModel"
			+ "(u.connectorId, u.index, u.sourceID, u.sourcePortID, u.targetID, u.targetPortID)"
			+ " from ESSConnectors u where u.project.id = :p1")
	List<ESSConnectorsModel> findByProject(@Param("p1") Long projectId);

	Long deleteByProjectIdAndConnectorIdNotIn(Long projectId, List<String> ids);

	Long deleteByProjectId(Long projectId);

	@Query("SELECT u.index from ESSConnectors u where u.project.id = :p1 and u.sourceID like '%-INV%'")
	List<Integer> findInverterIndex(@Param("p1") Long projectId);

	@Query(value = "SELECT count(*) >0  from ESSConnectors u where u.connectorId like concat('%-',:p1)")
	boolean idExit(@Param("p1") Integer random_int);

	@Query(value = "SELECT concat(u.circuitSpec.id,'')  from ESSConnectors u where u.project.id = :p1")
	List<String> findSpecIds(@Param("p1") Long projectId);

}
