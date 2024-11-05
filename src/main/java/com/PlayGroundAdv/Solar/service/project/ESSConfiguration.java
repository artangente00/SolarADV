package com.PlayGroundAdv.Solar.service.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSInputs;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSNodes;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSSegments;
import com.PlayGroundAdv.Solar.model.project.ess.ESSConnectorsModel;
import com.PlayGroundAdv.Solar.model.project.ess.ESSInputsModel;
import com.PlayGroundAdv.Solar.model.project.ess.ESSNodesModel;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSCircuitSpecRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSInputsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSNodesRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSSegmentsRepository;

@Service
@Transactional
public class ESSConfiguration {

	final ESSNodesRepository essNodesRepo;
	final ESSConnectorsRepository essConnectorsRepo;
	final ESSInputsRepository essInputsRepo;
	final ESSSegmentsRepository essSegmentsRepo;
	final ESSCircuitSpecRepository essCircuitSpecRepo;

	public ESSConfiguration(ESSNodesRepository eSSNodesRepo, ESSConnectorsRepository eSSConnectorsRepo,
			ESSInputsRepository essInputsRepo, ESSSegmentsRepository essSegmentsRepo,
			ESSCircuitSpecRepository essCircuitSpecRepo) {
		super();
		this.essNodesRepo = eSSNodesRepo;
		this.essConnectorsRepo = eSSConnectorsRepo;
		this.essInputsRepo = essInputsRepo;
		this.essSegmentsRepo = essSegmentsRepo;
		this.essCircuitSpecRepo = essCircuitSpecRepo;
	}

	public List<ESSNodesModel> getNodes(Long projectId) {
		try {
			List<ESSNodesModel> nodes = essNodesRepo.findByProject(projectId);
			for (ESSNodesModel n : nodes) {
				n.setInputs(essInputsRepo.findByNode(n.getId()));
			}
			return nodes;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<ESSConnectorsModel> getConnectors(Long projectId) {
		try {
			List<ESSConnectorsModel> connectors = essConnectorsRepo.findByProject(projectId);
			for (ESSConnectorsModel c : connectors) {
				c.setSegments(essSegmentsRepo.findByConnector(c.getId()));
				c.setCircuitSpec(essCircuitSpecRepo.findByConnector(c.getId()));
			}
			return connectors;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public void saveNodes(List<ESSNodesModel> nodes, PermitEntity project) {
		for (ESSNodesModel n : nodes) {
			try {
				ESSNodes node = essNodesRepo.findByNodeId(n.getId());
				if (node != null) {
					addNode(node, n);
				} else {
					addNode(new ESSNodes(project), n);
				}
				removeNodes(nodes, project.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String addNode(ESSNodes node, ESSNodesModel n) {
		node.setNodeId(n.getId());
		node.setTitle(n.getTitle());
		node.setType(n.getType());
		node.setOffsetX(n.getOffsetX());
		node.setOffsetY(n.getOffsetY());
		node.setRemoved(n.getRemoved());
		if (node.getId() != null)
			essInputsRepo.deleteByNodeId(node.getId());
		if (n.getInputs() != null && !n.getInputs().isEmpty()) {
			for (ESSInputsModel i : n.getInputs()) {
				essInputsRepo.save(new ESSInputs(node, i.getValue(), i.getLeft(), i.getTop()));
			}
		}

		essNodesRepo.save(node);
		return "n-" + node.getId();
	}

	public void saveConnectors(List<ESSConnectorsModel> connectors, PermitEntity project) {
		for (ESSConnectorsModel c : connectors) {
			try {
				ESSConnectors connector = essConnectorsRepo.findByConnectorId(c.getId());
				if (connector != null) {
					addConnector(connector, c);
				} else {
					addConnector(new ESSConnectors(project), c);
				}
				removeConnector(connectors, project.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addConnector(ESSConnectors connector, ESSConnectorsModel c) {
		try {
			connector.setConnectorId(c.getId());
			connector.setIndex(c.getIndex());
			connector.setSourceID(c.getSourceID());
			connector.setSourcePortID(c.getSourcePortID());
			connector.setTargetID(c.getTargetID());
			connector.setTargetPortID(c.getTargetPortID());
			if (connector.getId() != null)
				essSegmentsRepo.deleteByConnectorId(connector.getId());
			connector.setSegments(new ArrayList<>());
			for (int i = 0; c.getSegments() != null && i < c.getSegments().size(); i++) {
				connector.getSegments().add(new ESSSegments(connector, c.getSegments().get(i).getDirection(),
						c.getSegments().get(i).getLength()));
			}
			if (connector.getCircuitSpec() == null) {
				connector.setCircuitSpec(new ESSCircuitSpec(connector));
			}
			saveCircuitSpec(connector.getCircuitSpec(), c);
			essConnectorsRepo.save(connector);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void saveCircuitSpec(ESSCircuitSpec circuitSpec, ESSConnectorsModel c) {
		try {
			circuitSpec.setConductorQty(c.getCircuitSpec().getConductorQty());
			circuitSpec.setConductorType(c.getCircuitSpec().getConductorType());
			circuitSpec.setConductorSize(c.getCircuitSpec().getConductorSize());
			circuitSpec.setConduitType(c.getCircuitSpec().getConduitType());
			circuitSpec.setConduitSize(c.getCircuitSpec().getConduitSize());
			circuitSpec.setConductorQtyOther(c.getCircuitSpec().getConductorQtyOther());
			circuitSpec.setConductorTypeOther(c.getCircuitSpec().getConductorTypeOther());
			circuitSpec.setConductorSizeOther(c.getCircuitSpec().getConductorSizeOther());
			circuitSpec.setConduitTypeOther(c.getCircuitSpec().getConduitTypeOther());
			circuitSpec.setConduitSizeOther(c.getCircuitSpec().getConduitSizeOther());
			circuitSpec.setExisting(c.getCircuitSpec().getExisting());
			circuitSpec.setConductorNeutral(c.getCircuitSpec().getConductorNeutral());
			circuitSpec.setCircuitEnvironment(c.getCircuitSpec().getCircuitEnvironment());
			circuitSpec.setCircuitLength(c.getCircuitSpec().getCircuitLength());
			essCircuitSpecRepo.save(circuitSpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeNodes(List<ESSNodesModel> nodes, Long projectId) {
		try {
			List<String> ids = new ArrayList<>(nodes.stream().map(o -> o.getId()).collect(Collectors.toList()));
			essNodesRepo.deleteByProjectIdAndNodeIdNotIn(projectId, ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void removeConnector(List<ESSConnectorsModel> connectors, Long projectId) {
		try {
			List<String> ids = new ArrayList<>(connectors.stream().map(o -> o.getId()).collect(Collectors.toList()));
			essConnectorsRepo.deleteByProjectIdAndConnectorIdNotIn(projectId, ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteByProject(Long projectId) {
		List<ESSNodes> nodes = essNodesRepo.findByProjectId(projectId);
		List<Long> ids = new ArrayList<>(nodes.stream().map(o -> o.getId()).collect(Collectors.toList()));
		essInputsRepo.deleteByNodeIdIn(ids);
		List<ESSConnectors> connectors = essConnectorsRepo.findByProjectId(projectId);
		ids = new ArrayList<>(connectors.stream().map(o -> o.getId()).collect(Collectors.toList()));
		essSegmentsRepo.deleteByConnectorIdIn(ids);
		essNodesRepo.deleteByProjectId(projectId);
		essConnectorsRepo.deleteByProjectId(projectId);
	}
}
