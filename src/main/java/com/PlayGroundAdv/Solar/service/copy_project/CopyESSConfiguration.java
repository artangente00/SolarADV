package com.PlayGroundAdv.Solar.service.copy_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSInputs;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSNodes;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSSegments;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSCircuitSpecRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSInputsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSNodesRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSSegmentsRepository;

@Service
public class CopyESSConfiguration {

	final ESSNodesRepository essNodesRepo;
	final ESSConnectorsRepository essConnectorsRepo;
	final ESSInputsRepository essInputsRepo;
	final ESSSegmentsRepository essSegmentsRepo;
	final ESSCircuitSpecRepository essCircuitSpecRepo;

	public CopyESSConfiguration(ESSNodesRepository eSSNodesRepo, ESSConnectorsRepository eSSConnectorsRepo,
			ESSInputsRepository essInputsRepo, ESSSegmentsRepository essSegmentsRepo,
			ESSCircuitSpecRepository essCircuitSpecRepo) {
		super();
		this.essNodesRepo = eSSNodesRepo;
		this.essConnectorsRepo = eSSConnectorsRepo;
		this.essInputsRepo = essInputsRepo;
		this.essSegmentsRepo = essSegmentsRepo;
		this.essCircuitSpecRepo = essCircuitSpecRepo;
	}

	public void copyEssConfig(Long idPermit, PermitEntity newPermit) {
		try {
//			A.B 09-07-2022 CR-1039
			List<ESSNodes> nodes = essNodesRepo.findByProjectId(idPermit);
			HashMap<String, String> nodeIds = new HashMap<String, String>();
			if (nodes != null && !nodes.isEmpty()) {
				for (ESSNodes n : nodes) {
					nodeIds = cloneNodes(new ESSNodes(newPermit), n, nodeIds);
				}
			}
			List<ESSConnectors> connectors = essConnectorsRepo.findByProjectId(idPermit);
			if (connectors != null && !connectors.isEmpty()) {
				for (ESSConnectors c : connectors) {
					cloneConnectors(new ESSConnectors(newPermit), c, nodeIds);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private HashMap<String, String> cloneNodes(ESSNodes n, ESSNodes o, HashMap<String, String> nodeIds) {
		try {
			String nodeId = o.getNodeId().split("-")[0] + "-" + o.getNodeId().split("-")[1] + "-" + generateNodeId();
			nodeIds.put(o.getNodeId(), nodeId);
			n.setNodeId(nodeId);
			n.setTitle(o.getTitle());
			n.setType(o.getType());
			n.setOffsetX(o.getOffsetX());
			n.setOffsetY(o.getOffsetY());
			n.setRemoved(o.getRemoved());
			essNodesRepo.save(n);
			List<ESSInputs> inputs = essInputsRepo.findByNodeId(o.getId());
			if (inputs != null && !inputs.isEmpty()) {
				for (ESSInputs i : inputs) {
					essInputsRepo.save(new ESSInputs(n, i.getValue(), i.getMarginLeft(), i.getMarginTop()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeIds;
	}

	private void cloneConnectors(ESSConnectors n, ESSConnectors o, HashMap<String, String> nodeIds) {
		try {
			String sourceId = nodeIds.get(o.getSourceID());
			String targetId = nodeIds.get(o.getTargetID());
			n.setConnectorId(o.getConnectorId().replaceAll("\\d", "") + generateConnectorId());
			n.setIndex(o.getIndex());
			n.setSourceID(sourceId);
			n.setSourcePortID(o.getSourcePortID());
			n.setTargetID(targetId);
			n.setTargetPortID(o.getTargetPortID());
			n.setSegments(new ArrayList<>());
			for (int i = 0; o.getSegments() != null && i < o.getSegments().size(); i++) {
				n.getSegments().add(
						new ESSSegments(n, o.getSegments().get(i).getDirection(), o.getSegments().get(i).getLength()));
			}
			if (n.getCircuitSpec() == null) {
				n.setCircuitSpec(new ESSCircuitSpec(n));
			}
			cloneCircuitSpec(n.getCircuitSpec(), o.getCircuitSpec());
			essConnectorsRepo.save(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cloneCircuitSpec(ESSCircuitSpec n, ESSCircuitSpec o) {
		try {
			n.setConductorQty(o.getConductorQty());
			n.setConductorType(o.getConductorType());
			n.setConductorSize(o.getConductorSize());
			n.setConduitType(o.getConduitType());
			n.setConduitSize(o.getConduitSize());
			n.setConductorQtyOther(o.getConductorQtyOther());
			n.setConductorTypeOther(o.getConductorTypeOther());
			n.setConductorSizeOther(o.getConductorSizeOther());
			n.setConduitTypeOther(o.getConduitTypeOther());
			n.setConduitSizeOther(o.getConduitSizeOther());
			n.setExisting(o.getExisting());
			n.setConductorNeutral(o.getConductorNeutral());
			n.setCircuitEnvironment(o.getCircuitEnvironment());
			n.setCircuitLength(o.getCircuitLength());
			essCircuitSpecRepo.save(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Integer generateNodeId() {
		Integer randomInt = (int) Math.floor(Math.random() * (9999999 - 9999 + 1) + 9999);
		try {
			while (essNodesRepo.idExit(randomInt)) {
				randomInt = (int) Math.floor(Math.random() * (9999999 - 9999 + 1) + 9999);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return randomInt;
	}

	private Integer generateConnectorId() {
		Integer randomInt = (int) Math.floor(Math.random() * (9999999 - 9999 + 1) + 9999);
		try {
			while (essConnectorsRepo.idExit(randomInt)) {
				randomInt = (int) Math.floor(Math.random() * (9999999 - 9999 + 1) + 9999);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return randomInt;
	}
}
