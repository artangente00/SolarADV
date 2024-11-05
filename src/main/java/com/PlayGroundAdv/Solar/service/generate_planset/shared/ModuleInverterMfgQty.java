package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitConduitConductorSectionRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003.EssSystemMapping;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleQtyOnBranchCicuit;
@Service
public class ModuleInverterMfgQty {

	final CheckValueTypesService checkValue;
	final PermitConduitConductorSectionRepository circuitRepo;
	final EssSystemMapping essSystemMapping;
	final ModuleQtyOnBranchCicuit moduleQtyOnBranchCicuit;
	 
	
	public ModuleInverterMfgQty(CheckValueTypesService checkValue, PermitConduitConductorSectionRepository circuitRepo,
			EssSystemMapping essSystemMapping, ModuleQtyOnBranchCicuit moduleQtyOnBranchCicuit ) {
		super();
		this.checkValue = checkValue;
		this.circuitRepo = circuitRepo;
		this.essSystemMapping = essSystemMapping;
		this.moduleQtyOnBranchCicuit = moduleQtyOnBranchCicuit;
		 
	}

	static final String FIELD_NAME = "-QTY-MFG-MODEL-MOD-INV";
	static final String FIELD_NAME_S300 = "-MFG-MODEL-MOD-INV";

	public void moduleInverterMfgQty(String sheet, AcroFields form, String system, Long idPermit, int sheetIndex,
			Inverters inverterInfo, Cmodulev2 moduleInfo, Inverters microInverterInfo, String utility, String meterNumber, PlansetUtils plansetUtils) {
		try {

			if (checkValue.Equals(system, "Neither") || checkValue.Equals(system, "System Optimizer")) {
				moduleInverterMfgQtyStringInverter(sheet,form, sheetIndex, idPermit, inverterInfo, moduleInfo,( checkValue.containsCaseInsensitive(utility,"Oncor")||checkValue.EqualsCaseInsensitive(utility,"CenterPoint Energy Houston Electric LLC")), meterNumber);
 
			} else {
				moduleInverterMfgQtyMicro(sheet,form, system, idPermit, sheetIndex, moduleInfo, microInverterInfo, (checkValue.containsCaseInsensitive(utility,"Oncor") || checkValue.EqualsCaseInsensitive(utility,"CenterPoint Energy Houston Electric LLC")), meterNumber,   plansetUtils);
 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moduleInverterMfgQtyStringInverter(String sheet,AcroFields form, int sheetIndex, Long idPermit,
			Inverters inverterInfo, Cmodulev2 moduleInfo, Boolean oncore, String meterNumber) {
		try {
			PermitConduitConductorSectionEntity circuit = circuitRepo.findByPermitEntityId(idPermit);
			int totlaModule = circuit.getQtySegmentOne();
			int inverterQty = circuit.getQtySegmentSix();
			String battery = Boolean.TRUE.equals(oncore) ? essSystemMapping.getBatteryModel(idPermit, "custom") : "";
			String m = Boolean.TRUE.equals(oncore) ? "METER NUMBER: " + (checkValue.isStringNotEmpty(meterNumber) ? meterNumber : "") + "     " : "";
			if (moduleInfo != null && inverterInfo != null) {
				String makeMapping = moduleInfo.getManufacturerMappingValue();
				String modelMapping = moduleInfo.getModelMappingValue();
				String invModelMapping = inverterInfo.getModelMappingValue();
				String invMakeMapping = inverterInfo.getManufacturerMappingValue();  
				if(! sheet.equals("-S-300")) 
				{
				form.setField(sheetIndex+sheet + FIELD_NAME,
						m + "QTY " + totlaModule + "  " + makeMapping + " " + modelMapping + " MODULES   QTY " + inverterQty
								+ "  " + invMakeMapping + " " + invModelMapping + " INVERTER" + battery);
				}
				else 
				{ 
					form.setField(sheetIndex+sheet + FIELD_NAME_S300,
							m + "QTY " + totlaModule + "  " + makeMapping + " " + modelMapping + " MODULES   QTY " + inverterQty
									+ "  " + invMakeMapping + " " + invModelMapping + " INVERTER" + battery);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moduleInverterMfgQtyMicro(String sheet, AcroFields form, String system, Long idPermit, int sheetIndex,
			Cmodulev2 moduleInfo, Inverters microInverterInfo, Boolean oncore, String meterNumber, PlansetUtils plansetUtils) {
		try { 
		
			PermitConduitConductorSectionEntity circuit = circuitRepo.findByPermitEntityId(idPermit);
			int totlaModule = circuit.getQtySegmentOne(); 
			
			// W.A: CR-1060-MOD-001: Update the quantity of Microinverters in the Plan set sheets footers
			int inverterQty = plansetUtils.getModulePerMicroInverter();
			String battery = Boolean.TRUE.equals(oncore) ? essSystemMapping.getBatteryModel(idPermit, "custom") : "";
			String m = Boolean.TRUE.equals(oncore) ? "METER NUMBER: " + (checkValue.isStringNotEmpty(meterNumber) ? meterNumber : "") + "     " : "";

			if (checkValue.Equals(system, "Micro Inverter") && microInverterInfo != null) {
				moduleInverterMfgQtyMicroInverter(sheet, form, totlaModule, inverterQty, sheetIndex, moduleInfo,
						microInverterInfo, battery, m);
			} else if (checkValue.Equals(system, "AC Modules")) {
				moduleInverterMfgQtyACModule(sheet,form, totlaModule, sheetIndex, moduleInfo, battery, m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void moduleInverterMfgQtyMicroInverter(String sheet, AcroFields form, int totlaModule, int inverterQty, int sheetIndex,
			Cmodulev2 moduleInfo, Inverters microInverterInfo, String battery, String meterNumber) {
		try {
			if (moduleInfo != null && microInverterInfo != null) {
				String makeMapping = moduleInfo.getManufacturerMappingValue();
				String modelMapping = moduleInfo.getModelMappingValue();
				if(! sheet.equals("-S-300") )
				{	form.setField(sheetIndex+sheet + FIELD_NAME,
						meterNumber + "QTY " + totlaModule + "  " + makeMapping + " " + modelMapping + " MODULES,     QTY "
								+ inverterQty + "  " + microInverterInfo.getManufacturerMappingValue() + " "
								+ microInverterInfo.getModelMappingValue() + " MICRO INVERTER" + battery);
				}
				else {	
					form.setField(sheetIndex+sheet + FIELD_NAME_S300,
						meterNumber + "QTY " + totlaModule + "  " + makeMapping + " " + modelMapping + " MODULES,     QTY "
								+ inverterQty + "  " + microInverterInfo.getManufacturerMappingValue() + " "
								+ microInverterInfo.getModelMappingValue() + " MICRO INVERTER" + battery);
					}
			
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void moduleInverterMfgQtyACModule(String sheet, AcroFields form, int totlaModule, int sheetIndex, Cmodulev2 moduleInfo,
			String battery, String meterNumber) {
		try {
			if (moduleInfo != null) {
				String makeMapping = moduleInfo.getManufacturerMappingValue();
				String modelMapping = moduleInfo.getModelMappingValue();

				if (checkValue.NotEquals(moduleInfo.getIntegratedMicroModel(), "")
						&& checkValue.NotEquals(moduleInfo.getIntegratedMicroManufacturer(), "")) {
					if(! sheet.equals("-S-300") )
					{
						form.setField(sheetIndex+sheet + FIELD_NAME,
							meterNumber + "QTY " + totlaModule + "  " + makeMapping + " " + modelMapping + " MODULES  "
									+ moduleInfo.getIntegratedMicroManufacturer() + " "
									+ moduleInfo.getIntegratedMicroModel() + " INTEGRATED MICRO INVERTER" + battery);
					}
					else {
						form.setField(sheetIndex+sheet + FIELD_NAME_S300,
											meterNumber + "QTY " + totlaModule + "  " + makeMapping + " " + modelMapping + " MODULES  "
													+ moduleInfo.getIntegratedMicroManufacturer() + " "
													+ moduleInfo.getIntegratedMicroModel() + " INTEGRATED MICRO INVERTER" + battery);
						}
					
				} else {
					if(! sheet.equals("-S-300") )
					{
						form.setField(sheetIndex+sheet + FIELD_NAME,
								meterNumber + "QTY " + totlaModule + "  " + makeMapping + " " + modelMapping + " MODULES" + battery);
			
					}
					else {
						form.setField(sheetIndex+sheet + FIELD_NAME_S300,
								meterNumber + "QTY " + totlaModule + "  " + makeMapping + " " + modelMapping + " MODULES" + battery);
			
					}
							}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
