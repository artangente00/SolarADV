package com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.ProjectBattery;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.TiltLegs;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class UnverifiedEquipment {

	final CheckValueTypesService checkValueTypesService;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;

	public UnverifiedEquipment(CheckValueTypesService checkValueTypesService,
			PermitEnergyBatterySystemRepository energyBatterySystemRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
	}

	public void mapUnverifiedEquipment(AcroFields form, int pv001Index, Long idPermit, Cmodulev2 moduleInfo,
			List<Inverters> inverters, Inverters microInverterInfo, DCOptimizerEntity dcOptimizer,
			RailRacking railRacking, RailRacking railRackingGround, RailRacking railRackingPatio,
			RailRacking railRackingCarport, RoofAttachmentsEntity stanchionFoot, Flashing flashing,
			DCCombinerDisconnectEntity rapidShutdownModel, DCCombinerDisconnectEntity dcCombinerDisconnect,
			DCCombinerDisconnectEntity seconddcCombinerDisconnect, DCCombinerDisconnectEntity thirddcCombinerDisconnect,
			DCCombinerDisconnectEntity roofTopDCDisconnect, DCCombinerDisconnectEntity jBox, ACDisconnect acDisconnect,
			ACDisconnect secondacDisconnect, ACDisconnect acDisconnectThree, ACDisconnect acDisconnectFour,
			ACDisconnect additionalAcDisconnect, ACCombinerSLC slcAcCombiner, ACCombinerSLC acCombiner,
			LeasePPAMeter leasePPAMeter, ProposedSubPanel proposedSubPanel, TiltLegs tiltLegs) {
		try {
			List<String> l = new ArrayList<>();

//			PV Module
			if (moduleInfo != null && (Boolean.FALSE.equals(moduleInfo.getIsVerified()) || (moduleInfo.getIsVerified() == null && Boolean.FALSE.equals(moduleInfo.getHasSuperUserEdit())))) {
				l.add(moduleInfo.getManufacturerMappingValue() + " " + moduleInfo.getModelMappingValue());
			}
//			Inverter
			if (microInverterInfo != null && (Boolean.FALSE.equals(microInverterInfo.getIsVerified()) || (microInverterInfo.getIsVerified() == null && Boolean.FALSE.equals(microInverterInfo.getHasSuperUserEdit())))) {
				l.add(microInverterInfo.getManufacturerMappingValue() + " " + microInverterInfo.getModelMappingValue());
			}
			if (!inverters.isEmpty()) {
				for (Inverters inv : inverters) {
					if (inv != null && (Boolean.FALSE.equals(inv.getIsVerified()) || (inv.getIsVerified() == null && Boolean.FALSE.equals(inv.getHasSuperUserEdit())))) {
						l.add(inv.getManufacturerMappingValue() + " " + inv.getModelMappingValue());
					}
				}
			}
//			DC Converter
			if (dcOptimizer != null && (Boolean.FALSE.equals(dcOptimizer.getIsVerified()) || (dcOptimizer.getIsVerified() == null && Boolean.FALSE.equals(dcOptimizer.getHasSuperUserEdit())))) {
				l.add(dcOptimizer.getManufacturerMappingValue() + " " + dcOptimizer.getModelMappingValue());
			}
//			Rail Racking
			List<RailRacking> racking = Arrays.asList(railRacking, railRackingGround, railRackingPatio,
					railRackingCarport);
			for (RailRacking d : racking) {
				if (d != null && (Boolean.FALSE.equals(d.getIsVerified()) || (d.getIsVerified() == null && Boolean.FALSE.equals(d.getHasSuperUserEdit())))) {
					l.add(d.getManufacturerMappingValue() + " " + d.getModelMappingValue());
				}
			}
//			Roof Attachment
			if (stanchionFoot != null && (Boolean.FALSE.equals(stanchionFoot.getIsVerified()) || (stanchionFoot.getIsVerified() == null && Boolean.FALSE.equals(stanchionFoot.getHasSuperUserEdit())))) {
				l.add(stanchionFoot.getManufacturerMappingValue() + " " + stanchionFoot.getModelMappingValue());
			}
//			Flashing
			if (flashing != null && (Boolean.FALSE.equals(flashing.getIsVerified()) || (flashing.getIsVerified() == null && Boolean.FALSE.equals(flashing.getHasSuperUserEdit())))) {
				l.add(flashing.getMappedValue());
			}
//			DCD & JBOX
			List<DCCombinerDisconnectEntity> dcd = Arrays.asList(rapidShutdownModel, dcCombinerDisconnect,
					seconddcCombinerDisconnect, thirddcCombinerDisconnect, roofTopDCDisconnect, jBox);
			for (DCCombinerDisconnectEntity d : dcd) {
				if (d != null && (Boolean.FALSE.equals(d.getIsVerified()) || (d.getIsVerified() == null && Boolean.FALSE.equals(d.getHasSuperUserEdit())))) {
					l.add(d.getManufacturerMappingValue() + " " + d.getModelMappingValue());
				}
			}
//			ACD
			List<ACDisconnect> acd = Arrays.asList(acDisconnect, secondacDisconnect, acDisconnectThree,
					acDisconnectFour, additionalAcDisconnect);
			for (ACDisconnect d : acd) {
				if (d != null && (Boolean.FALSE.equals(d.getIsVerified()) || (d.getIsVerified() == null && Boolean.FALSE.equals(d.getHasSuperUserEdit())))) {
					l.add(d.getManufacturerMappingValue() + " " + d.getModelMappingValue());
				}
			}
//			ACC
			List<ACCombinerSLC> acc = Arrays.asList(slcAcCombiner, acCombiner);
			for (ACCombinerSLC d : acc) {
				if (d != null && (Boolean.FALSE.equals(d.getIsVerified()) || (d.getIsVerified() == null && Boolean.FALSE.equals(d.getHasSuperUserEdit())))) {
					l.add(d.getManufacturerMappingValue() + " " + d.getModelMappingValue());
				}
			}
//			SUB Panel
			if (proposedSubPanel != null && (Boolean.FALSE.equals(proposedSubPanel.getIsVerified()) || (proposedSubPanel.getIsVerified() == null && Boolean.FALSE.equals(proposedSubPanel.getHasSuperUserEdit())))) {
				l.add(proposedSubPanel.getManufacturer() + " " + proposedSubPanel.getModel());
			}
//			PV Meter
			if (leasePPAMeter != null && (Boolean.FALSE.equals(leasePPAMeter.getIsVerified()) || (leasePPAMeter.getIsVerified() == null && Boolean.FALSE.equals(leasePPAMeter.getHasSuperUserEdit())))) {
				l.add(leasePPAMeter.getManufacturer() + " " + leasePPAMeter.getModel());
			}
//			Tilt Legs
			if (tiltLegs != null && (Boolean.FALSE.equals(tiltLegs.getIsVerified()) || (tiltLegs.getIsVerified() == null && Boolean.FALSE.equals(tiltLegs.getHasSuperUserEdit())))) {
				l.add(tiltLegs.getManufacturer() + " " + tiltLegs.getModel());
			}

//			Batteries
			PermitEnergyBatterySystem energyBatterySystem = energyBatterySystemRepo.findByProjectId(idPermit);
			if (energyBatterySystem.getBatteries() != null && !energyBatterySystem.getBatteries().isEmpty()) {
				List<ProjectBattery> batteries = energyBatterySystem.getBatteries();
				for (int i = 0; i < batteries.size(); i++) {
					if (tiltLegs != null && (Boolean.FALSE.equals(batteries.get(i).getBatteryId().getIsVerified()) || (batteries.get(i).getBatteryId().getIsVerified() == null && Boolean.FALSE.equals(batteries.get(i).getBatteryId().getHasSuperUserEdit())))) {
						l.add(batteries.get(i).getBatteryId().getManufacturer() + " "
								+ batteries.get(i).getBatteryId().getModel());
					}
					
				}
			}
//			ATS 
			if (Boolean.TRUE.equals(energyBatterySystem.getAtsIncluded()) && energyBatterySystem.getIdAts() != null && (Boolean.FALSE.equals(energyBatterySystem.getIdAts().getIsVerified()) || (energyBatterySystem.getIdAts().getIsVerified() == null && Boolean.FALSE.equals(energyBatterySystem.getIdAts().getHasSuperUserEdit())))) {
				l.add(energyBatterySystem.getIdAts().getManufacturerMappingValue() + " "
								+ energyBatterySystem.getIdAts().getModelMappingValue());
			}
//			Generator 
			if (Boolean.TRUE.equals(energyBatterySystem.getGeneratorIncluded()) && energyBatterySystem.getIdGenerator() != null && (Boolean.FALSE.equals(energyBatterySystem.getIdGenerator().getIsVerified()) || (energyBatterySystem.getIdGenerator().getIsVerified() == null && Boolean.FALSE.equals(energyBatterySystem.getIdGenerator().getHasSuperUserEdit())))) {
				l.add(energyBatterySystem.getIdGenerator() != null
						? energyBatterySystem.getIdGenerator().getManufacturerMappingValue() + " "
								+ energyBatterySystem.getIdGenerator().getModelMappingValue()
						: "");
			}

			l = l.stream()
				     .distinct()
				     .collect(Collectors.toList());
			if (!l.isEmpty()) {
				StringBuilder text = new StringBuilder("CAUTION: Unverified equipment list:\r");
				for (int i = 0; i < l.size(); i++) {
					text.append("Equip"+(i+1)+" "+l.get(i)+"\r");
				}
				form.setField(pv001Index + "-" + "Unverified-Equipment-List", text.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
