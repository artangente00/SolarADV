package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSNodesRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GenerateCircuitList {

	final CheckValueTypesService checkValue;
	final ESSNodesRepository essNodesRepository;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;

	public GenerateCircuitList(CheckValueTypesService checkValue, ESSNodesRepository essNodesRepository,
			PermitEnergyBatterySystemRepository energyBatterySystemRepo) {
		super();
		this.checkValue = checkValue;
		this.essNodesRepository = essNodesRepository;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
	}

	static final String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";

	public String getConductorCircuit(PermitConduitConductorSectionEntity circuit, String system,
			String converterType) {

		try {
			String dc = "";
			Boolean hasConverter = checkValue.Equals(system, "System Optimizer") && circuit.getQtySegmentTwo() != null
					&& circuit.getQtySegmentTwo() != 0;
			if (circuit.getComponentOrder().contains("-INV")) {
				if (Boolean.TRUE.equals(hasConverter)) {
					dc = "PV-OP-" + circuit.getComponentOrder().split("-INV")[0];
				} else {
					dc = "PV-" + circuit.getComponentOrder().split("-INV")[0];
				}
			} else if (checkValue.contains(circuit.getComponentOrder(), "INV")) {
				if (Boolean.TRUE.equals(hasConverter)) {
					dc = "PV-OP";
				} else {
					dc = "PV-";
				}
			}

			return renameDcComponent(dc, converterType);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public String getConduitCircuit(PermitConduitConductorSectionEntity circuit) {

		try {
			String ac = "";
			if (circuit.getComponentOrder().contains("-INV-")) {
				ac = "INV-" + circuit.getComponentOrder().split("-INV-")[1];
			} else if (circuit.getComponentOrder() != null && circuit.getComponentOrder().contains("-INV")) {
				ac = "INV-";
			} else if (circuit.getComponentOrder() != null && circuit.getComponentOrder().contains("INV-")) {
				ac = circuit.getComponentOrder();
			} else if (checkValue.Equals(circuit.getComponentOrder(), "INV")) {
				ac = "INV-";
			}
			return renameAcComponent(ac);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public String renameDcComponent(String dc, String converterType) {
		try {
			dc = dc.replace("PV", "PV MODULE");

			if (checkValue.Equals(converterType, "DC/DC Rapid Shutdown"))
				dc = dc.replace("OP", "RAPID SHUTDOWN");
			else {
				dc = dc.replace("OP", "DC/DC CONVERTER");
			}
			dc = dc.replace("DCJBOX", "JUNCTION BOX");
			dc = dc.replace("DCC", "DC COMBINER");
			dc = dc.replace("DCD", "DC DISCONNECT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dc;
	}

	public String renameAcComponent(String ac) {
		try {
			ac = ac.replace("MINV-", "INVERTER-");
			ac = ac.replace("INV-", "INVERTER-");
			ac = ac.replace("ACJBOX", "JUNCTION BOX");
			ac = ac.replace("ACC", "AC COMBINER/LOAD CENTER");
			ac = ac.replace("ACDTwo", "AC DISCONNECTTwo");
			ac = ac.replace("ACD", "AC DISCONNECT");
			ac = ac.replace("PMETER", "PRODUCTION METER");
			ac = ac.replace("ACSUBPANEL", "SUB PANEL");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	public int getDcCombinerIndex(String dcCircuit) {
		try {
			for (int i = 0; i < dcCircuit.split("-").length; i++) {
				if (dcCircuit.split("-")[i].equals("DC COMBINER")) {
					return i;
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getAcCombinerIndex(String dcCircuit) {
		try {
			for (int i = 0; i < dcCircuit.split("-").length; i++) {
				if (dcCircuit.split("-")[i].equals("AC COMBINER/LOAD CENTER")) {
					return i;
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String getEquipmentName(String nodeId, Boolean mainPanelUpgrade, Long projecId, String previousEquipment,
			String... converterType) {
		try {
			switch (nodeId.split("-")[1]) {
			case "MOD":
				return "PV Module";
			case "OPT":
				if (converterType != null && converterType.length > 0 && checkValue.Equals(converterType[0], "DC/DC Rapid Shutdown")) {
					return "RAPID SHUTDOWN";
				} else {
					return "DC/DC CONVERTER";
				}
			case "DCJBOX":
				return "JUNCTION BOX";
			case "MINV":
			case "INV":
				return "INVERTER";
			case "ACJBOX":
				return "JUNCTION BOX";
			case "SLC":
				return "SOLAR LOAD CENTER";
			case "ACC":
				return "AC COMBINER";
			case "ACD":
			case "ACDF":
			case "ACD1":
			case "ACDF1":
			case "ACD2":
			case "ACDF2":
			case "ACD3":
			case "ACDF3":
			case "ACD4":
			case "ACDF4":
			case "ACD41":
			case "ACDF41":
			case "ACD42":
			case "ACDF42":
			case "ACD43":
			case "ACDF43":
			case "ACD44":
			case "ACDF44":
				return previousEquipment.contains("BAT") || previousEquipment.contains("EB") ? "BATTERY DISCONNECT"
						: "AC DISCONNECT";
			case "ESUBM":case "SUB":
				return "SUB-PANEL";
			case "BAT":
			case "BAT1":
			case "BAT2":
			case "BAT3":
			case "BAT4":
				return getBattery(nodeId);
			case "EB":
				return "EMERGENCY BATTERY DISCONNECT";
			case "ATS":
			case "ATS2":
				return getATS(nodeId);
			case "EGEN":
			case "NGEN":
				return energyBatterySystemRepo.findGeneratorStatus(projecId).equals("Existing") ? "EXISTING GENERATOR"
						: "PROPOSED GENERATOR";
			case "PVM":
				return "PRODUCTION METER";
			case "MSP":
				if (Boolean.TRUE.equals(mainPanelUpgrade)) {
					return "NEW SERVICE PANEL";
				} else {
					return "EXISTING SERVICE PANEL";
				}
			case "METER":case "M":
				return "UTILITY METER";
			default:
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private String getBattery(String nodeId) {
		try {
			String title = essNodesRepository.findTitleById(nodeId);
			if (title.contains("ENPHASE ENCHARGE")) {
				return "ENPHASE ENCHARGE";
			} else if (title.contains("GENERAC")) {
				return "GENERAC PWRcell";
			} else if (title.contains("TESLA")) {
				return "TESLA POWERWALL";
			} else {
				return "BATTERY GENERIC";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private String getATS(String nodeId) {
		try {
			String title = essNodesRepository.findTitleById(nodeId);
			if (title.equals("ATS - AUTO TRANSFER SWITCH")) {
				return "AUTO TRANSFER SWITCH";
			} else if (title.equals("ATS - SMART TRANSFER SWITCH")) {
				return "SMART TRANSFER SWITCH";
			} else if (title.equals("ATS - STORAGE AUTOMATIC BACKUP UNIT")) {
				return "STORAGE AUTOMATIC BACKUP UNIT";
			} else if (title.equals("ATS - SOLAREDGE BACKUP INTERFACE")) {
				return "SOLAREDGE BACKUP INTERFACE";
			} else {
				return "TESLA BACKUP GATEWAY";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Boolean existingCircuit(ESSConnectors ac, PermitEntity permitEntity,
			PermitProjectSiteInfoEntity permitProjectSiteInfo) {
		try {
			switch (ac.getSourceID().split("-")[1]) {
			case "MINV":
				return checkValue.Equals(permitEntity.getExistInverter(), true);
			case "ACJBOX":
				return checkValue.Equals(permitEntity.getExistAcJunctionBox(), true);
			case "SLC":
			case "ACC":
				return checkValue.Equals(permitEntity.getExistAcCombiner(), true);
			case "ACD1":
			case "ACDF1":
			case "ACD2":
			case "ACDF2":
			case "ACD3":
			case "ACDF3":
			case "ACD4":
			case "ACDF4":
				return checkValue.Equals(permitEntity.getExistAcDisconnect(), true);
			case "ESUBM":case "SUB":
				return checkValue.Equals(permitEntity.getExistSubPanel(), true)
						|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL);
			case "BAT":
				return false;
			case "ATS":
			case "ATS2":
				return false;
			case "EGEN":
			case "NGEN":
				return false;
			case "PVM":
				return checkValue.Equals(permitEntity.getExistProductionMeter(), true);
			case "MSP":
				return false;
			case "METER":
				return false;
			default:
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
