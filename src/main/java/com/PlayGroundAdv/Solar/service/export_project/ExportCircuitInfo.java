package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportCircuitInfo {

	
	final CheckValueTypesService checkValueTypes;
	final ESSConnectorsRepository essConnectorsRepo;
	final GenerateCircuitList generateCircuitList;
	public ExportCircuitInfo(CheckValueTypesService checkValueTypes, ESSConnectorsRepository essConnectorsRepo,
			GenerateCircuitList generateCircuitList) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.essConnectorsRepo = essConnectorsRepo;
		this.generateCircuitList = generateCircuitList;
	}

	public LinkedHashMap<String, String> conduitContractorSection(GetPermitByIdResult permit) {
		LinkedHashMap<String, String> contractorSystem = new LinkedHashMap<>();
		try {
			Boolean battery = permit.getPermitAdditionalInfoEntity().getBatteryStorage();
//			A.B 09-07-2022 CR-1039
			if (Boolean.TRUE.equals(battery)) {
				List<ESSConnectors> connectors = essConnectorsRepo.findByProjectId(permit.getPermitEntity().getId());
				for (int i = 0; connectors != null && i < connectors.size(); i++) {
					String origin = getEquipmentName(connectors.get(i).getSourceID(),
							permit.getPermitProjectSiteInfoEntityTwo().getMainPanelUpgrade(),
							i > 0 ? connectors.get(i - 1).getSourceID() : "",
							permit.getPermitEnergyBatterySystem().getGeneratorStatus());
					ESSCircuitSpec spec = connectors.get(i).getCircuitSpec();
				
					if (spec != null) {
						contractorSystem.put(origin + " Wire Qty",
								checkValueTypes.Equals(spec.getConductorQty(), "Other")
										? checkValueTypes.convert(spec.getConductorQtyOther() + "")
										: checkValueTypes.convert(spec.getConductorQty()));
						contractorSystem.put(origin + " Conductor Type",
								checkValueTypes.Equals(spec.getConductorType(), "Other")
										? checkValueTypes.convert(spec.getConductorTypeOther())
										: checkValueTypes.convert(spec.getConductorType()));
						contractorSystem.put(origin + " Conductor Size",
								checkValueTypes.Equals(spec.getConductorSize(), "Other")
										? checkValueTypes.convert(spec.getConductorSizeOther())
										: checkValueTypes.convert(spec.getConductorSize()));
						contractorSystem.put(origin + " Conduit Type",
								checkValueTypes.Equals(spec.getConduitType(), "Other")
										? checkValueTypes.convert(spec.getConduitTypeOther())
										: checkValueTypes.convert(spec.getConduitType()));
						contractorSystem.put(origin + " Conduit Size",
								checkValueTypes.Equals(spec.getConduitSize(), "Other")
										? checkValueTypes.convert(spec.getConduitSizeOther())
										: checkValueTypes.convert(spec.getConduitSize()));
						contractorSystem.put(origin + " Circuit Environment",
								checkValueTypes.convert(spec.getCircuitEnvironment()));
						contractorSystem.put(origin + " Circuit Length",
								checkValueTypes.convert(spec.getCircuitLength() + ""));
					}
					
				}
			}else {
				contractorSystem.put("PV Module Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentOne() + ""));
				contractorSystem.put("PV Module Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQty() + ""));
				contractorSystem.put("PV Module Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorType() + ""));
				contractorSystem.put("PV Module Other Conductor type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeOther() + ""));
				contractorSystem.put("PV Module Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSize() + ""));
				contractorSystem.put("PV Module Other Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeOther() + ""));
				contractorSystem.put("PV Module Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitType() + ""));
				contractorSystem.put("PV Module Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeOther() + ""));
				contractorSystem.put("PV Module Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSize() + ""));
				contractorSystem.put("PV Module Other Conduit size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeOther() + ""));
				contractorSystem.put("PV Module Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironment() + ""));
				contractorSystem.put("PV Module Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthOne() + ""));
				
				contractorSystem.put("DC/DC CONVERTER Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentTwo() + ""));
				contractorSystem.put("DC/DC CONVERTER Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyTwo() + ""));
				contractorSystem.put("DC/DC CONVERTER Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeTwo() + ""));
				contractorSystem.put("DC/DC CONVERTER Other Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeTwoOther() + ""));
				contractorSystem.put("DC/DC CONVERTER Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeTwo() + ""));
				contractorSystem.put("DC/DC CONVERTER Other Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeTwoOther() + ""));
				contractorSystem.put("DC/DC CONVERTER Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeTwo() + ""));
				contractorSystem.put("DC/DC CONVERTER Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeTwoOther() + ""));
				contractorSystem.put("DC/DC CONVERTER Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeTwo() + ""));
				contractorSystem.put("DC/DC CONVERTER Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeTwo() + ""));
				contractorSystem.put("DC/DC CONVERTER Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentTwo() + ""));
				contractorSystem.put("DC/DC CONVERTER Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthTwo() + ""));
				
				contractorSystem.put("JUNCTION BOX DC Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentThree() + ""));
				contractorSystem.put("JUNCTION BOX Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyThree() + ""));
				contractorSystem.put("JUNCTION BOX Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeThree() + ""));
				contractorSystem.put("JUNCTION BOX Other Conductor Type", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorTypeThreeOther() + ""));
				contractorSystem.put("JUNCTION BOX Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeThree() + ""));
				contractorSystem.put("JUNCTION BOX Other Conductor Size", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorSizeThreeOther() + ""));
				contractorSystem.put("JUNCTION BOX Conduit type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeThree() + ""));
				contractorSystem.put("JUNCTION BOX Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeThreeOther() + ""));
				contractorSystem.put("JUNCTION BOX Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeThree() + ""));
				contractorSystem.put("JUNCTION BOX Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeThreeOther() + ""));
				contractorSystem.put("JUNCTION BOX Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentThree() + ""));
				contractorSystem.put("JUNCTION BOX Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthThree() + ""));
				
				contractorSystem.put("DC COMBINER DC COMBINER Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentFour() + ""));
				contractorSystem.put("DC COMBINER Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyFour() + ""));
				contractorSystem.put("DC COMBINER Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeFour() + ""));
				contractorSystem.put("DC COMBINER Other Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeFourOther() + ""));
				contractorSystem.put("DC COMBINER Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeFour() + ""));
				contractorSystem.put("DC COMBINER Other Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeFourOther() + ""));
				contractorSystem.put("DC COMBINER Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeFour() + ""));
				contractorSystem.put("DC COMBINER Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeFourOther() + ""));
				contractorSystem.put("DC COMBINER Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeFour() + ""));
				contractorSystem.put("DC COMBINER Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeFourOther() + ""));
				contractorSystem.put("DC COMBINER Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentFour() + ""));
				contractorSystem.put("DC COMBINER Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthFour() + ""));
				
				contractorSystem.put("DC DISCONNECT Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentFive() + ""));
				contractorSystem.put("DC DISCONNECT Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyFive() + ""));
				contractorSystem.put("DC DISCONNECT Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeFive() + ""));
				contractorSystem.put("DC DISCONNECT Other Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeFiveOther() + ""));
				contractorSystem.put("DC DISCONNECT Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeFive() + ""));
				contractorSystem.put("DC DISCONNECT Other Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeFiveOther() + ""));
				contractorSystem.put("DC DISCONNECT Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeFive() + ""));
				contractorSystem.put("DC DISCONNECT Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeFive() + ""));
				contractorSystem.put("DC DISCONNECT Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeFive() + ""));
				contractorSystem.put("DC DISCONNECT Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeFiveOther() + ""));
				contractorSystem.put("DC DISCONNECT Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentFive() + ""));
				contractorSystem.put("DC DISCONNECT Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthFive() + ""));
				
				contractorSystem.put("INVERTER Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentSix() + ""));
				contractorSystem.put("INVERTER Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtySix() + ""));
				contractorSystem.put("INVERTER Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeSix() + ""));
				contractorSystem.put("INVERTER Other Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeSixOther() + ""));
				contractorSystem.put("INVERTER Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeSix() + ""));
				contractorSystem.put("INVERTER Other Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeSixOther() + ""));
				contractorSystem.put("INVERTER Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeSix() + ""));
				contractorSystem.put("INVERTER Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeSixOther() + ""));
				contractorSystem.put("INVERTER Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeSix() + ""));
				contractorSystem.put("INVERTER Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeSixOther() + ""));
				contractorSystem.put("INVERTER Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentSix() + ""));
				contractorSystem.put("INVERTER Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthSix() + ""));
				
				contractorSystem.put("JUNCTION BOX AC Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentSeven() + ""));
				contractorSystem.put("JUNCTION Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtySeven() + ""));
				contractorSystem.put("JUNCTION Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeSeven() + ""));
				contractorSystem.put("JUNCTION Other Conductor Type", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorTypeSevenOther() + ""));
				contractorSystem.put("JUNCTION Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeSeven() + ""));
				contractorSystem.put("JUNCTION Other Conductor Size", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorSizeSevenOther() + ""));
				contractorSystem.put("JUNCTION Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeSeven() + ""));
				contractorSystem.put("JUNCTION Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeSevenOther() + ""));
				contractorSystem.put("JUNCTION Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeSeven() + ""));
				contractorSystem.put("JUNCTION Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeSevenOther() + ""));
				contractorSystem.put("JUNCTION Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentSeven() + ""));
				contractorSystem.put("JUNCTION Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthSeven() + ""));
				
				contractorSystem.put("AC COMBINER/LOAD CENTER Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentEight() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyEight() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeEight() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Other Conductor type", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorTypeEightOther() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeEight() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Other Conductor Size", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorSizeEightOther() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeEight() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeEightOther() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeEight() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeEightOther() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentEight() + ""));
				contractorSystem.put("AC COMBINER/LOAD CENTER Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthEight() + ""));
				
				contractorSystem.put("AC DISCONNECT Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentNine() + ""));
				contractorSystem.put("AC DISCONNECT Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyNine() + ""));
				contractorSystem.put("AC DISCONNECT Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeNine() + ""));
				contractorSystem.put("AC DISCONNECT Other Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeNineOther() + ""));
				contractorSystem.put("AC DISCONNECT Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeNine() + ""));
				contractorSystem.put("AC DISCONNECT Other Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeNineOther() + ""));
				contractorSystem.put("AC DISCONNECT Conduit type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeNine() + ""));
				contractorSystem.put("AC DISCONNECT Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeNineOther() + ""));
				contractorSystem.put("AC DISCONNECT Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeNine() + ""));
				contractorSystem.put("AC DISCONNECT Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeNineOther() + ""));
				contractorSystem.put("AC DISCONNECT Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentNine() + ""));
				contractorSystem.put("AC DISCONNECT Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthNine() + ""));
				
				contractorSystem.put("AC DISCONNECT 2 Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentNineTwo() + ""));
				contractorSystem.put("AC DISCONNECT 2 Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyNineTwo() + ""));
				contractorSystem.put("AC DISCONNECT 2 Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeNineTwo() + ""));
				contractorSystem.put("AC DISCONNECT 2 Other Conductor Type", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorTypeNineTwoOther() + ""));
				contractorSystem.put("AC DISCONNECT 2 Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeNineTwo() + ""));
				contractorSystem.put("AC DISCONNECT 2 Other Conductor Size", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorSizeNineTwoOther() + ""));
				contractorSystem.put("AC DISCONNECT 2 Conduit type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeNineTwo() + ""));
				contractorSystem.put("AC DISCONNECT 2 Other Conduit Type", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConduitTypeNineTwoOther() + ""));
				contractorSystem.put("AC DISCONNECT 2 Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeNineTwo() + ""));
				contractorSystem.put("AC DISCONNECT 2 Other Conduit Size", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConduitSizeNineTwoOther() + ""));
				contractorSystem.put("AC DISCONNECT 2 Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentNineTwo() + ""));
				contractorSystem.put("AC DISCONNECT 2 Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthNineTwo() + ""));
				
				contractorSystem.put("PRODUCTION METER Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentTen() + ""));
				contractorSystem.put("PRODUCTION METER Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyTen() + ""));
				contractorSystem.put("PRODUCTION METER Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeTen() + ""));
				contractorSystem.put("PRODUCTION METER Other Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeTenOther() + ""));
				contractorSystem.put("PRODUCTION METER Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeTen() + ""));
				contractorSystem.put("PRODUCTION METER Other Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeTenOther() + ""));
				contractorSystem.put("PRODUCTION METER Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeTen() + ""));
				contractorSystem.put("PRODUCTION METER Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeTenOther() + ""));
				contractorSystem.put("PRODUCTION METER Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeTen() + ""));
				contractorSystem.put("PRODUCTION METER Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeTenOther() + ""));
				contractorSystem.put("PRODUCTION METER Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentTen() + ""));
				contractorSystem.put("PRODUCTION METER Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthTen() + ""));
				
				contractorSystem.put("SUB PANEL Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getQtySegmentEleven() + ""));
				contractorSystem.put("SUB PANEL Wire Qty", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorQtyEleven() + ""));
				contractorSystem.put("SUB PANEL Conductor Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorTypeEleven() + ""));
				contractorSystem.put("SUB PANEL Other Conductor Type", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorTypeElevenOther() + ""));
				contractorSystem.put("SUB PANEL Conductor Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConductorSizeEleven() + ""));
				contractorSystem.put("SUB PANEL Other Conductor Size", checkValueTypes
						.convert(permit.getPermitConduitConductorSection().getConductorSizeElevenOther() + ""));
				contractorSystem.put("SUB PANEL Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeEleven() + ""));
				contractorSystem.put("SUB PANEL Other Conduit Type", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitTypeElevenOther() + ""));
				contractorSystem.put("SUB PANEL Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeEleven() + ""));
				contractorSystem.put("SUB PANEL Other Conduit Size", checkValueTypes.convert(permit.getPermitConduitConductorSection().getConduitSizeElevenOther() + ""));
				contractorSystem.put("SUB PANEL Circuit Environment", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitEnvironmentEleven() + ""));
				contractorSystem.put("SUB PANEL Circuit Length", checkValueTypes.convert(permit.getPermitConduitConductorSection().getCircuitLengthEleven() + ""));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contractorSystem;

	}
	
	public String getEquipmentName(String nodeId, Boolean mainPanelUpgrade, String previousEquipment,
			String generatorType) {
		try {
			switch (nodeId.split("-")[1]) {
			case "MOD":
				return "PV Module";
			case "OPT":
				return "DC/DC Converter";
			case "DCJBOX":
				return "Junction Box";
			case "MINV":
			case "INV":
				return "Inverter";
			case "ACJBOX":
				return "Junction Box";
			case "SLC":
				return "Solar Load Center";
			case "ACC":
				return "AC Combiner";
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
				return previousEquipment.contains("BAT") || previousEquipment.contains("EB") ? "Battery Disconnect"
						: "AC Disconnect";
			case "ESUBM":case "SUB":
				return "Sub-Panel";
			case "BAT":
			case "BAT1":
			case "BAT2":
			case "BAT3":
			case "BAT4":
				return "Battery";
			case "EB":
				return "Emergency Battery Disconnect";
			case "ATS":
			case "ATS2":
				return "Auto Transfer Switch";
			case "EGEN":
			case "NGEN":
				return generatorType != null && generatorType.equals("Existing") ? "Existing Generator"
						: "Proposed Generator";
			case "PVM":
				return "Production Meter";
			case "MSP":
				if (Boolean.TRUE.equals(mainPanelUpgrade)) {
					return "New Service Panel";
				} else {
					return "Existing Service Panel";
				}
			case "METER":case "M":
				return "Utility Meter";
			default:
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
}
