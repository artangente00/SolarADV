package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.ProjectBattery;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class EssSystemMapping {

	
	final CheckValueTypesService checkValueTypesService;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	
	public EssSystemMapping(CheckValueTypesService checkValueTypesService,
			PermitEnergyBatterySystemRepository energyBatterySystemRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
	}
	
	// S.B CR-PM-164 07/15/2021
	public String getBatteryQty(Long idPermit) {
		PermitEnergyBatterySystem energyBatterySystem = energyBatterySystemRepo.findByProjectId(idPermit);
		if (energyBatterySystem.getBatteries() != null && !energyBatterySystem.getBatteries().isEmpty()) {
			List<ProjectBattery> batteries = energyBatterySystem.getBatteries();
			Integer qty = 0;
			for (int i = 0; i < batteries.size(); i++) {
				qty = qty + batteries.get(i).getQuantity();
			}
			return qty+"";
		}
		return "";
	}
	// S.B CR-PM-164 07/15/2021
	public String getBatteryModel(Long idPermit, String sheet) {
		PermitEnergyBatterySystem energyBatterySystem = energyBatterySystemRepo.findByProjectId(idPermit);
		if (energyBatterySystem.getBatteries() != null && !energyBatterySystem.getBatteries().isEmpty()) {
			List<ProjectBattery> batteries = energyBatterySystem.getBatteries();
			ProjectBattery b = batteries.get(0);
			if (batteries.size() == 1) {
				if(sheet.equals("custom")) {
					return ",    QTY " + b.getQuantity() + " " + b.getBatteryId().getManufacturer()+" "+b.getBatteryId().getModel();
				}else {
					return b.getBatteryId().getManufacturer()+" "+b.getBatteryId().getModel();
				}
				
			} else {
				StringBuilder bld = new StringBuilder(b.getBatteryId().getModel());
				if(sheet.equals("E003")) {
					for (int i = 1; i < batteries.size(); i++) {
						bld.append(", "+batteries.get(i).getBatteryId().getModel());
					}
				}else if(sheet.equals("custom")) {
					return ",    QTY " + batteries.get(0).getQuantity() + " " + batteries.get(0).getBatteryId().getManufacturer()+" "+batteries.get(0).getBatteryId().getModel()
							+ " & QTY " + batteries.get(1).getQuantity() + " " + batteries.get(1).getBatteryId().getManufacturer()+" "+batteries.get(1).getBatteryId().getModel();
				}else {
					for (int i = 1; i < batteries.size() && i < 3; i++) {
						bld.append(", "+batteries.get(i).getBatteryId().getModel());
					}
				}
				return bld.toString();
			}
		}
		return "";
	}

	public void mapBattery(AcroFields form, Long idPermit,int sheetIndex,Integer fieldNumber) {
		try {
			// Updated by S.B CR-PM-164 07/15/2021
			form.setField(sheetIndex+"-"+"BATTERY-MODEL", getBatteryModel(idPermit, "E003"));
			form.setField(sheetIndex+"-"+"EQUIPMENT-COMPONENT-"+fieldNumber+"-QTY",  getBatteryQty(idPermit));
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void mapEssEquipment(AcroFields form, Long idPermit,int sheetIndex,Integer atsQtyField, Integer generatorQtyField) {
		try {
			PermitEnergyBatterySystem energyBatterySystem = energyBatterySystemRepo.findByProjectId(idPermit);
			if (Boolean.TRUE.equals(energyBatterySystem.getAtsIncluded())) {
				String ats = energyBatterySystem.getIdAts() != null ?  energyBatterySystem.getIdAts().getManufacturerMappingValue()+" "+ energyBatterySystem.getIdAts().getModelMappingValue() : "";
				form.setField(sheetIndex+"-"+"ESS-EQUIPMENT-1", "AUTO TRANSFER SWITCH");
				form.setField(sheetIndex+"-"+"ESS-EQUIPMENT-1-MFG-MODEL", ats);
				form.setField(sheetIndex+"-"+"EQUIPMENT-COMPONENT-"+atsQtyField+"-QTY","1");
			}else {
				form.setField(sheetIndex+"-"+"ESS-EQUIPMENT-1", "");
				form.setField(sheetIndex+"-"+"ESS-EQUIPMENT-1-MFG-MODEL", "");
				form.setField(sheetIndex+"-"+"EQUIPMENT-COMPONENT-"+atsQtyField+"-QTY","");
			}
			if (Boolean.TRUE.equals(energyBatterySystem.getGeneratorIncluded()) && energyBatterySystem.getGeneratorStatus() != null) {
				String gen = energyBatterySystem.getIdGenerator() != null ?  energyBatterySystem.getIdGenerator().getManufacturerMappingValue()+" "+ energyBatterySystem.getIdGenerator().getModelMappingValue() : "";
				String label =  energyBatterySystem.getGeneratorStatus().equals("Existing") ? "(E) FUEL GENERATOR" : "(N) FUEL GENERATOR";
				form.setField(sheetIndex+"-"+"ESS-EQUIPMENT-2", label);
				form.setField(sheetIndex+"-"+"ESS-EQUIPMENT-2-MFG-MODEL", gen);
				form.setField(sheetIndex+"-"+"EQUIPMENT-COMPONENT-"+generatorQtyField+"-QTY","1");
			}else {
				form.setField(sheetIndex+"-"+"ESS-EQUIPMENT-2", "");
				form.setField(sheetIndex+"-"+"ESS-EQUIPMENT-2-MFG-MODEL", "");
				form.setField(sheetIndex+"-"+"EQUIPMENT-COMPONENT-"+generatorQtyField+"-QTY","");
			}
			
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
		}
	}

}
