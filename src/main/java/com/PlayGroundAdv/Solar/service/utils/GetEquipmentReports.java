package com.PlayGroundAdv.Solar.service.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.ACCombinerSLCUsedModel;
import com.PlayGroundAdv.Solar.model.ACDisconnectUsedModel;
import com.PlayGroundAdv.Solar.model.libraries.DCCombinerDisconnectUsedModel;
import com.PlayGroundAdv.Solar.model.libraries.InverterUsedListModel;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;

@Service
@Transactional
public class GetEquipmentReports {
	
	final CheckValueTypesService checkValueTypesService;
	final PermitArraysRepository arraysRepo;
	final PermitProjectSiteInfoRepository siteInfoRepo;
	final ModuleRepository moduleRepo;
	final InverterRepository inverterRepo;
	final ConvertersRepository converterRepo;
	final RailRackingRepository railRackingRepo;
	final RoofAttachmentsRepository roofAttachmentRepo;
	final FlashingRepository flashingRepo;
	final DcCombinerDiscoRepository dcCombinerDiscoRepo;
	final AcCombinerSLCRepository acCombinerSLCRepo;
	final ACDisconnectRepository acDisconnectRepo;
	final LeasePPAMeterRepository leasePPAMeterRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;

	public GetEquipmentReports(CheckValueTypesService checkValueTypesService, PermitArraysRepository arraysRepo,
			PermitProjectSiteInfoRepository siteInfoRepo, ModuleRepository moduleRepo, InverterRepository inverterRepo,
			ConvertersRepository converterRepo, RailRackingRepository railRackingRepo, RoofAttachmentsRepository roofAttachmentRepo,
			FlashingRepository flashingRepo, DcCombinerDiscoRepository dcCombinerDiscoRepo, AcCombinerSLCRepository acCombinerSLCRepo,
			ACDisconnectRepository acDisconnectRepo, LeasePPAMeterRepository leasePPAMeterRepo, 
			RoofMaterialTypeRepository roofMaterialTypeRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.arraysRepo = arraysRepo;
		this.siteInfoRepo = siteInfoRepo;
		this.moduleRepo = moduleRepo;
		this.inverterRepo = inverterRepo;
		this.converterRepo = converterRepo;
		this.railRackingRepo = railRackingRepo;
		this.roofAttachmentRepo = roofAttachmentRepo;
		this.flashingRepo = flashingRepo;
		this.dcCombinerDiscoRepo = dcCombinerDiscoRepo;
		this.acCombinerSLCRepo = acCombinerSLCRepo; 
		this.acDisconnectRepo = acDisconnectRepo;
		this.leasePPAMeterRepo = leasePPAMeterRepo;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
	}


	public void getprojectlist() throws IOException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -9);
		Date thendate = cal.getTime();

		LinkedHashMap<Cmodulev2, Integer> modules = getusedModule(thendate);
		LinkedHashMap<Inverters, Integer> inverters = getusedInverter(thendate);
		LinkedHashMap<Inverters, Integer> microInverters = getusedMicroInverter(thendate);
		LinkedHashMap<DCOptimizerEntity, Integer> optimizer = getusedOptimizer(thendate);
		LinkedHashMap<RailRacking, Integer> railRacking = getusedRailRacking(thendate);
		LinkedHashMap<RoofAttachmentsEntity, Integer> roofAttachment = getusedRoofAttachment(thendate);
		LinkedHashMap<Flashing, Integer> flashing = getusedFlashing(thendate);
		LinkedHashMap<DCCombinerDisconnectEntity, Integer> dCCombinerDisconnect = getusedDCCombinerDisconnect(thendate);
		LinkedHashMap<DCCombinerDisconnectEntity, Integer> junctionBox = getusedJunctionBox(thendate);
		LinkedHashMap<ACDisconnect, Integer> aCDisconnect = getusedACDisconnect(thendate);
		LinkedHashMap<ACCombinerSLC, Integer> aCCombinerSLC = getusedACCombinerSLC(thendate);
		LinkedHashMap<LeasePPAMeter, Integer> revenuMeter = getusedRevenuMeter(thendate);
		LinkedHashMap<RoofMaterialType, Integer> roofList = getusedRoofingMaterial(thendate);
		
		
		
		FileOutputStream fileOut;
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet moduleSheet = workbook.createSheet("Module Library");
		HSSFSheet inverterSheet = workbook.createSheet("Inverter Library");
		HSSFSheet microInverterSheet = workbook.createSheet("Micro Inverter Library");
		HSSFSheet optimizerSheet = workbook.createSheet("DC Optimizers Library");
		HSSFSheet railRackingSheet = workbook.createSheet("Rail & Racking Library");
		HSSFSheet attachmentSheet = workbook.createSheet("Roof Attachments Library");
		HSSFSheet flashingSheet = workbook.createSheet("Flashing Library");
		HSSFSheet dcCombinerSheet = workbook.createSheet("DC Combiner Or Disconnects Library");
		HSSFSheet jBoxSheet = workbook.createSheet("Junction Box Library");
		HSSFSheet slcSheet = workbook.createSheet("AC Combiner SLC Library");
		HSSFSheet acDisconnectSheet = workbook.createSheet("AC Disconnect Library");
		HSSFSheet revenuMeterSheet = workbook.createSheet("Revenue or Performance Monitoring Meter Library");
		HSSFSheet roofMaterialSheet = workbook.createSheet("Roofing Material Type Library");
		
		HSSFCellStyle cellStyleOrange = workbook.createCellStyle();
		cellStyleOrange.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
        cellStyleOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        
        /// Module
        Row moduleRow = moduleSheet.createRow(0);
        moduleSheet.setColumnWidth(0, 7000);
        moduleSheet.setColumnWidth(1, 7000);
        moduleSheet.setColumnWidth(2, 7000);
        moduleSheet.setColumnWidth(3, 7000);
        moduleSheet.setColumnWidth(4, 3000);
        moduleRow.createCell(0).setCellStyle(cellStyleOrange);
        moduleRow.createCell(1).setCellStyle(cellStyleOrange);
        moduleRow.createCell(2).setCellStyle(cellStyleOrange);
        moduleRow.createCell(3).setCellStyle(cellStyleOrange);
        moduleRow.createCell(4).setCellStyle(cellStyleOrange);
		 moduleRow.getCell(0).setCellValue("Manufacturer");
		 moduleRow.getCell(1).setCellValue("Manufacturer Mapping Value");
		 moduleRow.getCell(2).setCellValue("Model");
		 moduleRow.getCell(3).setCellValue("Model Mapping Value");
		 moduleRow.getCell(4).setCellValue("Occurrence");
        
		 int i = 1;
		 if (modules != null) {
			 Iterator<Cmodulev2> keySetIteratorModule = modules.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratorModule.hasNext()){
		        	moduleRow = moduleSheet.createRow(i);
		        	Cmodulev2 key = keySetIteratorModule.next();
		       	 
		        	moduleRow.createCell(0).setCellValue(key.getMake());
		        	moduleRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
		        	moduleRow.createCell(2).setCellValue(key.getModel());
		        	moduleRow.createCell(3).setCellValue(key.getModelMappingValue());
		        	moduleRow.createCell(4).setCellValue(modules.get(key)+"");
			         i++;
		        }
		}
        
        
        
        
      /// inverter
        Row inverterRow = inverterSheet.createRow(0);
        inverterSheet.setColumnWidth(0, 7000);
        inverterSheet.setColumnWidth(1, 7000);
        inverterSheet.setColumnWidth(2, 7000);
        inverterSheet.setColumnWidth(3, 7000);
        inverterSheet.setColumnWidth(4, 3000);
        inverterRow.createCell(0).setCellStyle(cellStyleOrange);
        inverterRow.createCell(1).setCellStyle(cellStyleOrange);
        inverterRow.createCell(2).setCellStyle(cellStyleOrange);
        inverterRow.createCell(3).setCellStyle(cellStyleOrange);
        inverterRow.createCell(4).setCellStyle(cellStyleOrange);
		 inverterRow.getCell(0).setCellValue("Manufacturer");
		 inverterRow.getCell(1).setCellValue("Manufacturer Mapping Value");
		 inverterRow.getCell(2).setCellValue("Model");
		 inverterRow.getCell(3).setCellValue("Model Mapping Value");
		 inverterRow.getCell(4).setCellValue("Occurrence");
        
		 if (inverters != null) {
			 Iterator<Inverters> keySetIteratorinverter = inverters.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratorinverter.hasNext()){
		        	inverterRow = inverterSheet.createRow(i);
		        	Inverters key = keySetIteratorinverter.next();
		       	 
		        	inverterRow.createCell(0).setCellValue(key.getMake());
		        	inverterRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
		        	inverterRow.createCell(2).setCellValue(key.getModel());
		        	inverterRow.createCell(3).setCellValue(key.getModelMappingValue());
		        	inverterRow.createCell(4).setCellValue(inverters.get(key)+"");
			         i++;
		        }
			}
		 
        
		
        
      /// microInverter
        Row microInverterRow = microInverterSheet.createRow(0);
        microInverterSheet.setColumnWidth(0, 7000);
        microInverterSheet.setColumnWidth(1, 7000);
        microInverterSheet.setColumnWidth(2, 7000);
        microInverterSheet.setColumnWidth(3, 7000);
        microInverterSheet.setColumnWidth(4, 3000);
        microInverterRow.createCell(0).setCellStyle(cellStyleOrange);
        microInverterRow.createCell(1).setCellStyle(cellStyleOrange);
        microInverterRow.createCell(2).setCellStyle(cellStyleOrange);
        microInverterRow.createCell(3).setCellStyle(cellStyleOrange);
        microInverterRow.createCell(4).setCellStyle(cellStyleOrange);
		 microInverterRow.getCell(0).setCellValue("Manufacturer");
		 microInverterRow.getCell(1).setCellValue("Manufacturer Mapping Value");
		 microInverterRow.getCell(2).setCellValue("Model");
		 microInverterRow.getCell(3).setCellValue("Model Mapping Value");
		 microInverterRow.getCell(4).setCellValue("Occurrence");
		 if (microInverters != null) {
			 Iterator<Inverters> keySetIteratormicroInverter = microInverters.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratormicroInverter.hasNext()){
		        	microInverterRow = microInverterSheet.createRow(i);
		        	Inverters key = keySetIteratormicroInverter.next();
		       	 
		        	microInverterRow.createCell(0).setCellValue(key.getMake());
		        	microInverterRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
		        	microInverterRow.createCell(2).setCellValue(key.getModel());
		        	microInverterRow.createCell(3).setCellValue(key.getModelMappingValue());
		        	microInverterRow.createCell(4).setCellValue(microInverters.get(key)+"");
			         i++;
		        }
			}
        
        
        /// optimizer
        Row optimizerRow = optimizerSheet.createRow(0);
        optimizerSheet.setColumnWidth(0, 7000);
        optimizerSheet.setColumnWidth(1, 7000);
        optimizerSheet.setColumnWidth(2, 7000);
        optimizerSheet.setColumnWidth(3, 7000);
        optimizerSheet.setColumnWidth(4, 3000);
        optimizerRow.createCell(0).setCellStyle(cellStyleOrange);
        optimizerRow.createCell(1).setCellStyle(cellStyleOrange);
        optimizerRow.createCell(2).setCellStyle(cellStyleOrange);
        optimizerRow.createCell(3).setCellStyle(cellStyleOrange);
        optimizerRow.createCell(4).setCellStyle(cellStyleOrange);
		 optimizerRow.getCell(0).setCellValue("Manufacturer");
		 optimizerRow.getCell(1).setCellValue("Manufacturer Mapping Value");
		 optimizerRow.getCell(2).setCellValue("Model");
		 optimizerRow.getCell(3).setCellValue("Model Mapping Value");
		 optimizerRow.getCell(4).setCellValue("Occurrence");
		 if (optimizer != null) {
			 Iterator<DCOptimizerEntity> keySetIteratoroptimizer = optimizer.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratoroptimizer.hasNext()){
		        	optimizerRow = optimizerSheet.createRow(i);
		        	DCOptimizerEntity key = keySetIteratoroptimizer.next();
		       	 
		        	optimizerRow.createCell(0).setCellValue(key.getManufacturer());
		        	optimizerRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
		        	optimizerRow.createCell(2).setCellValue(key.getModel());
		        	optimizerRow.createCell(3).setCellValue(key.getModelMappingValue());
		        	optimizerRow.createCell(4).setCellValue(optimizer.get(key)+"");
			         i++;
		        }
			}
        
        
      /// railRacking
        Row railRackingRow = railRackingSheet.createRow(0);
        railRackingSheet.setColumnWidth(0, 7000);
        railRackingSheet.setColumnWidth(1, 7000);
        railRackingSheet.setColumnWidth(2, 7000);
        railRackingSheet.setColumnWidth(3, 7000);
        railRackingSheet.setColumnWidth(4, 3000);
        railRackingRow.createCell(0).setCellStyle(cellStyleOrange);
        railRackingRow.createCell(1).setCellStyle(cellStyleOrange);
        railRackingRow.createCell(2).setCellStyle(cellStyleOrange);
        railRackingRow.createCell(3).setCellStyle(cellStyleOrange);
        railRackingRow.createCell(4).setCellStyle(cellStyleOrange);
		 railRackingRow.getCell(0).setCellValue("Manufacturer");
		 railRackingRow.getCell(1).setCellValue("Manufacturer Mapping Value");
		 railRackingRow.getCell(2).setCellValue("Model");
		 railRackingRow.getCell(3).setCellValue("Model Mapping Value");
		 railRackingRow.getCell(4).setCellValue("Occurrence");
		 if (railRacking != null) {
			 Iterator<RailRacking> keySetIteratorrailRacking = railRacking.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratorrailRacking.hasNext()){
		        	railRackingRow = railRackingSheet.createRow(i);
		        	RailRacking key = keySetIteratorrailRacking.next();
		       	 
		        	railRackingRow.createCell(0).setCellValue(key.getManufacturer());
		        	railRackingRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
		        	railRackingRow.createCell(2).setCellValue(key.getModel());
		        	railRackingRow.createCell(3).setCellValue(key.getModelMappingValue());
		        	railRackingRow.createCell(4).setCellValue(railRacking.get(key)+"");
			         i++;
		        }
			}
        
        
        
      /// roofAttachment
        Row roofAttachmentRow = attachmentSheet.createRow(0);
        attachmentSheet.setColumnWidth(0, 7000);
        attachmentSheet.setColumnWidth(1, 7000);
        attachmentSheet.setColumnWidth(2, 7000);
        attachmentSheet.setColumnWidth(3, 7000);
        attachmentSheet.setColumnWidth(4, 3000);
        roofAttachmentRow.createCell(0).setCellStyle(cellStyleOrange);
        roofAttachmentRow.createCell(1).setCellStyle(cellStyleOrange);
        roofAttachmentRow.createCell(2).setCellStyle(cellStyleOrange);
        roofAttachmentRow.createCell(3).setCellStyle(cellStyleOrange);
        roofAttachmentRow.createCell(4).setCellStyle(cellStyleOrange);
		 roofAttachmentRow.getCell(0).setCellValue("Manufacturer");
		 roofAttachmentRow.getCell(1).setCellValue("Manufacturer Mapping Value");
		 roofAttachmentRow.getCell(2).setCellValue("Model");
		 roofAttachmentRow.getCell(3).setCellValue("Model Mapping Value");
		 roofAttachmentRow.getCell(4).setCellValue("Occurrence");
		 if (roofAttachment != null) {
			 Iterator<RoofAttachmentsEntity> keySetIteratorroofAttachment = roofAttachment.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratorroofAttachment.hasNext()){
		        	roofAttachmentRow = attachmentSheet.createRow(i);
		        	RoofAttachmentsEntity key = keySetIteratorroofAttachment.next();
		       	 
		        	roofAttachmentRow.createCell(0).setCellValue(key.getManufacturer());
		        	roofAttachmentRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
		        	roofAttachmentRow.createCell(2).setCellValue(key.getModel());
		        	roofAttachmentRow.createCell(3).setCellValue(key.getModelMappingValue());
		        	roofAttachmentRow.createCell(4).setCellValue(roofAttachment.get(key)+"");
			         i++;
		        }
			}
        
        
      /// flashing
        Row flashingRow = flashingSheet.createRow(0);
        flashingSheet.setColumnWidth(0, 7000);
        flashingSheet.setColumnWidth(1, 7000);
        flashingSheet.setColumnWidth(2, 7000);
        flashingSheet.setColumnWidth(3, 7000);
        flashingRow.createCell(0).setCellStyle(cellStyleOrange);
        flashingRow.createCell(1).setCellStyle(cellStyleOrange);
        flashingRow.createCell(2).setCellStyle(cellStyleOrange);
        flashingRow.createCell(3).setCellStyle(cellStyleOrange);
		 flashingRow.getCell(0).setCellValue("Manufacturer");
		 flashingRow.getCell(1).setCellValue("Model");
		 flashingRow.getCell(2).setCellValue("Mapping Value");
		 flashingRow.getCell(3).setCellValue("Occurrence");
		 if (flashing != null) {
			 Iterator<Flashing> keySetIteratorflashing = flashing.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratorflashing.hasNext()){
		        	flashingRow = flashingSheet.createRow(i);
		        	Flashing key = keySetIteratorflashing.next();
		       	 
		        	flashingRow.createCell(0).setCellValue(key.getManufacturer());
		        	flashingRow.createCell(1).setCellValue(key.getModel());
		        	flashingRow.createCell(2).setCellValue(key.getMappedValue());
		        	flashingRow.createCell(3).setCellValue(flashing.get(key)+"");
			         i++;
		        }
			}
       
        
      /// dcCombiner
        Row dcCombinerRow = dcCombinerSheet.createRow(0);
        dcCombinerSheet.setColumnWidth(0, 7000);
        dcCombinerSheet.setColumnWidth(1, 7000);
        dcCombinerSheet.setColumnWidth(2, 7000);
        dcCombinerSheet.setColumnWidth(3, 7000);
        dcCombinerSheet.setColumnWidth(4, 3000);
        dcCombinerRow.createCell(0).setCellStyle(cellStyleOrange);
        dcCombinerRow.createCell(1).setCellStyle(cellStyleOrange);
        dcCombinerRow.createCell(2).setCellStyle(cellStyleOrange);
        dcCombinerRow.createCell(3).setCellStyle(cellStyleOrange);
        dcCombinerRow.createCell(4).setCellStyle(cellStyleOrange);
		 dcCombinerRow.getCell(0).setCellValue("Manufacturer");
		 dcCombinerRow.getCell(1).setCellValue("Manufacturer Mapping Value");
		 dcCombinerRow.getCell(2).setCellValue("Model");
		 dcCombinerRow.getCell(3).setCellValue("Model Mapping Value");
		 dcCombinerRow.getCell(4).setCellValue("Occurrence");
		 if (dCCombinerDisconnect != null) {
			 Iterator<DCCombinerDisconnectEntity> keySetIteratordcCombiner = dCCombinerDisconnect.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratordcCombiner.hasNext()){
		        	dcCombinerRow = dcCombinerSheet.createRow(i);
		        	DCCombinerDisconnectEntity key = keySetIteratordcCombiner.next();
		       	 
		        	dcCombinerRow.createCell(0).setCellValue(key.getManufacturer());
		        	dcCombinerRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
		        	dcCombinerRow.createCell(2).setCellValue(key.getModel());
		        	dcCombinerRow.createCell(3).setCellValue(key.getModelMappingValue());
		        	dcCombinerRow.createCell(4).setCellValue(dCCombinerDisconnect.get(key)+"");
			         i++;
		        }
			}
        
        
      /// junctionBox
        Row jBoxRow = jBoxSheet.createRow(0);
        jBoxSheet.setColumnWidth(0, 7000);
        jBoxSheet.setColumnWidth(1, 7000);
        jBoxSheet.setColumnWidth(2, 7000);
        jBoxSheet.setColumnWidth(3, 7000);
        jBoxSheet.setColumnWidth(4, 3000);
        jBoxRow.createCell(0).setCellStyle(cellStyleOrange);
        jBoxRow.createCell(1).setCellStyle(cellStyleOrange);
        jBoxRow.createCell(2).setCellStyle(cellStyleOrange);
        jBoxRow.createCell(3).setCellStyle(cellStyleOrange);
        jBoxRow.createCell(4).setCellStyle(cellStyleOrange);
		 jBoxRow.getCell(0).setCellValue("Manufacturer");
		 jBoxRow.getCell(1).setCellValue("Manufacturer Mapping Value");
		 jBoxRow.getCell(2).setCellValue("Model");
		 jBoxRow.getCell(3).setCellValue("Model Mapping Value");
		 jBoxRow.getCell(4).setCellValue("Occurrence");
		 if ( junctionBox != null) {
			 Iterator<DCCombinerDisconnectEntity> keySetIteratorjBoxSheet = junctionBox.keySet().iterator();
		        
		        i = 1;
		        while(keySetIteratorjBoxSheet.hasNext()){
		        	jBoxRow = jBoxSheet.createRow(i);
		        	DCCombinerDisconnectEntity key = keySetIteratorjBoxSheet.next();
		       	 
		        	jBoxRow.createCell(0).setCellValue(key.getManufacturer());
		        	jBoxRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
		        	jBoxRow.createCell(2).setCellValue(key.getModel());
		        	jBoxRow.createCell(3).setCellValue(key.getModelMappingValue());
		        	jBoxRow.createCell(4).setCellValue(junctionBox.get(key)+"");
			         i++;
		        }
			}
        
        
        
        /// aCDisconnect 
          Row aCDisconnectRow = acDisconnectSheet.createRow(0);
          acDisconnectSheet.setColumnWidth(0, 7000);
          acDisconnectSheet.setColumnWidth(1, 7000);
          acDisconnectSheet.setColumnWidth(2, 7000);
          acDisconnectSheet.setColumnWidth(3, 7000);
          acDisconnectSheet.setColumnWidth(4, 3000);
          aCDisconnectRow.createCell(0).setCellStyle(cellStyleOrange);
          aCDisconnectRow.createCell(1).setCellStyle(cellStyleOrange);
          aCDisconnectRow.createCell(2).setCellStyle(cellStyleOrange);
          aCDisconnectRow.createCell(3).setCellStyle(cellStyleOrange);
          aCDisconnectRow.createCell(4).setCellStyle(cellStyleOrange);
  		 aCDisconnectRow.getCell(0).setCellValue("Manufacturer");
  		 aCDisconnectRow.getCell(1).setCellValue("Manufacturer Mapping Value");
  		 aCDisconnectRow.getCell(2).setCellValue("Model");
  		 aCDisconnectRow.getCell(3).setCellValue("Model Mapping Value");
  		 aCDisconnectRow.getCell(4).setCellValue("Occurrence");
  		 if (aCDisconnect != null) {
  			 Iterator<ACDisconnect> keySetIteratoraCDisconnectSheet = aCDisconnect.keySet().iterator();
  	          
  	          i = 1;
  	          while(keySetIteratoraCDisconnectSheet.hasNext()){
  	          	aCDisconnectRow = acDisconnectSheet.createRow(i);
  	          	ACDisconnect key = keySetIteratoraCDisconnectSheet.next();
  	         	 
  	          	aCDisconnectRow.createCell(0).setCellValue(key.getManufacturer());
  	          	aCDisconnectRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
  	          	aCDisconnectRow.createCell(2).setCellValue(key.getModel());
  	          	aCDisconnectRow.createCell(3).setCellValue(key.getModelMappingValue());
  	          	aCDisconnectRow.createCell(4).setCellValue(aCDisconnect.get(key)+"");
  	  	         i++;
  	          }
			}
         
          
        /// aC Disconnect  SLC
          Row slcRow = slcSheet.createRow(0);
          slcSheet.setColumnWidth(0, 7000);
          slcSheet.setColumnWidth(1, 7000);
          slcSheet.setColumnWidth(2, 7000);
          slcSheet.setColumnWidth(3, 7000);
          slcSheet.setColumnWidth(4, 3000);
          slcRow.createCell(0).setCellStyle(cellStyleOrange);
          slcRow.createCell(1).setCellStyle(cellStyleOrange);
          slcRow.createCell(2).setCellStyle(cellStyleOrange);
          slcRow.createCell(3).setCellStyle(cellStyleOrange);
          slcRow.createCell(4).setCellStyle(cellStyleOrange);
  		 slcRow.getCell(0).setCellValue("Manufacturer");
  		 slcRow.getCell(1).setCellValue("Manufacturer Mapping Value");
  		 slcRow.getCell(2).setCellValue("Model");
  		 slcRow.getCell(3).setCellValue("Model Mapping Value");
  		 slcRow.getCell(4).setCellValue("Occurrence");
  		 if (aCCombinerSLC != null) {
  			Iterator<ACCombinerSLC> keySetIteratorslcSheet = aCCombinerSLC.keySet().iterator();
            
            i = 1;
            while(keySetIteratorslcSheet.hasNext()){
            	slcRow = slcSheet.createRow(i);
            	ACCombinerSLC key = keySetIteratorslcSheet.next();
           	 
            	slcRow.createCell(0).setCellValue(key.getManufacturer());
            	slcRow.createCell(1).setCellValue(key.getManufacturerMappingValue());
            	slcRow.createCell(2).setCellValue(key.getModel());
            	slcRow.createCell(3).setCellValue(key.getModelMappingValue());
            	slcRow.createCell(4).setCellValue(aCCombinerSLC.get(key)+"");
    	         i++;
            }
			}
          
          
          
          
        /// revenuMeter
          Row revenuMeterRow = revenuMeterSheet.createRow(0);
          revenuMeterSheet.setColumnWidth(0, 7000);
          revenuMeterSheet.setColumnWidth(1, 7000);
          revenuMeterSheet.setColumnWidth(2, 7000);
          revenuMeterSheet.setColumnWidth(3, 7000);
          revenuMeterSheet.setColumnWidth(4, 3000);
          revenuMeterRow.createCell(0).setCellStyle(cellStyleOrange);
          revenuMeterRow.createCell(1).setCellStyle(cellStyleOrange);
          revenuMeterRow.createCell(2).setCellStyle(cellStyleOrange);
          revenuMeterRow.createCell(3).setCellStyle(cellStyleOrange);
  		 revenuMeterRow.getCell(0).setCellValue("Manufacturer");
  		 revenuMeterRow.getCell(1).setCellValue("Model");
  		 revenuMeterRow.getCell(2).setCellValue("Mapping Value");
  		 revenuMeterRow.getCell(3).setCellValue("Occurrence");
  		 if (revenuMeter != null) {
  			Iterator<LeasePPAMeter> keySetIteratorrevenuMeterSheet = revenuMeter.keySet().iterator();
            
            i = 1;
            while(keySetIteratorrevenuMeterSheet.hasNext()){
            	revenuMeterRow = revenuMeterSheet.createRow(i);
            	LeasePPAMeter key = keySetIteratorrevenuMeterSheet.next();
           	 
            	revenuMeterRow.createCell(0).setCellValue(key.getManufacturer());
            	revenuMeterRow.createCell(1).setCellValue(key.getModel());
            	revenuMeterRow.createCell(2).setCellValue(key.getMappedValue());
            	revenuMeterRow.createCell(3).setCellValue(revenuMeter.get(key)+"");
    	         i++;
            }
			}
          
          
          
        /// RoofMaterial
          Row roofMaterialRow = roofMaterialSheet.createRow(0);
          roofMaterialSheet.setColumnWidth(0, 7000);
          roofMaterialSheet.setColumnWidth(1, 7000);
          roofMaterialSheet.setColumnWidth(2, 3000);
          roofMaterialRow.createCell(0).setCellStyle(cellStyleOrange);
          roofMaterialRow.createCell(1).setCellStyle(cellStyleOrange);
          roofMaterialRow.createCell(2).setCellStyle(cellStyleOrange);
  		 roofMaterialRow.getCell(0).setCellValue("Roof Material Type");
  		 roofMaterialRow.getCell(1).setCellValue("Mapping Value");
  		 roofMaterialRow.getCell(2).setCellValue("Occurrence");
  		 if (roofList != null) {
  			 Iterator<RoofMaterialType> keySetIteratorroofMaterialSheet = roofList.keySet().iterator();
  	          
  	          i = 1;
  	          while(keySetIteratorroofMaterialSheet.hasNext()){
  	          	roofMaterialRow = roofMaterialSheet.createRow(i);
  	          	RoofMaterialType key = keySetIteratorroofMaterialSheet.next();
  	         	 
  	          	roofMaterialRow.createCell(0).setCellValue(key.getTypeRoof());
  	          	roofMaterialRow.createCell(1).setCellValue(key.getMappingValue());
  	          	roofMaterialRow.createCell(2).setCellValue(roofList.get(key)+"");
  	  	         i++;
  	          }
			}
         
  		    String filePath ="D:/Equipment.xls";

	        fileOut = new FileOutputStream(filePath);
          
          workbook.write(fileOut);
	        fileOut.flush();
	        fileOut.close();
	        workbook.close();
          
	}
	

	public LinkedHashMap<Cmodulev2, Integer> getusedModule(Date thendate) {
		try {
			LinkedHashMap<Cmodulev2, Integer> modules = new LinkedHashMap<>();
			List<Long> moduleModelList = arraysRepo.findModuleList(thendate);
			List<Cmodulev2> moduleList = moduleRepo.findByIdInAndIsDeleted(moduleModelList, false);
			for (Cmodulev2 cmodulev2 : moduleList) {
				modules.put(cmodulev2, Collections.frequency(moduleModelList, cmodulev2.getId()));
			}
			return modules;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}

	public LinkedHashMap<Inverters, Integer> getusedInverter(Date thendate) {
		try {
			LinkedHashMap<Inverters, Integer> invertersList = new LinkedHashMap<>();
			List<InverterUsedListModel> invertersModelList = arraysRepo.findInvertersList(thendate);
			List<Long> inverters = new ArrayList<>();
			for (InverterUsedListModel inverter : invertersModelList) {
				inverters.add(inverter.getInverterModel());
				inverters.add(inverter.getSecondInverterModel());
				inverters.add(inverter.getThirdInverterModel());
				inverters.add(inverter.getFourthInverterModel());
				inverters.add(inverter.getFifthInverterModel());
			}
				List<Inverters> inverterList = inverterRepo.findByIdInAndIsDeleted(inverters, false);
				for (Inverters inverter : inverterList) {
					invertersList.put(inverter, Collections.frequency(inverters, inverter.getId()));
				}
				return invertersList;
				
				
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}
	
	
	public LinkedHashMap<Inverters, Integer> getusedMicroInverter(Date thendate) {
		try {
			LinkedHashMap<Inverters, Integer> invertersList = new LinkedHashMap<>();
			List<InverterUsedListModel> invertersModelList = arraysRepo.findInvertersList(thendate);
			List<Long> microInverters = new ArrayList<>();
			for (InverterUsedListModel inverter : invertersModelList) {
				microInverters.add(inverter.getMicroInverterModel());
			}
				List<Inverters> microInverterList = inverterRepo.findByIdInAndIsDeleted(microInverters, false);
				for (Inverters inverter : microInverterList) {
					invertersList.put(inverter, Collections.frequency(microInverters, inverter.getId()));
				}
				return invertersList;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}
	
	public LinkedHashMap<DCOptimizerEntity, Integer> getusedOptimizer(Date thendate) {
		try {
			LinkedHashMap<DCOptimizerEntity, Integer> optimizer = new LinkedHashMap<>();
			List<Long> optimizerModelList = arraysRepo.findOptimizerList(thendate);
				List<DCOptimizerEntity> optimizerList = converterRepo.findByIdInAndIsDeleted(optimizerModelList, false);
				for (DCOptimizerEntity op : optimizerList) {
					optimizer.put(op, Collections.frequency(optimizerModelList, op.getId()));
				}
				return optimizer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Roof, Ground & Pole Rail Racking 
	public LinkedHashMap<RailRacking, Integer> getusedRailRacking(Date thendate) {
		try {
			LinkedHashMap<RailRacking, Integer> RailRacking = new LinkedHashMap<>();
			List<RailRacking> roofRailRacking = siteInfoRepo.findRoofRailRackingList(thendate);
			List<RailRacking> groundRailRacking = siteInfoRepo.findGroundRailRackingList(thendate);
			List<Long> roofRailRackingID = new ArrayList<>();
			for(RailRacking s : roofRailRacking) roofRailRackingID.add((s != null && s.getId() != -1 && s.getId() != 0) ? s.getId() : null);
			for(RailRacking s : groundRailRacking) roofRailRackingID.add((s != null && s.getId() != -1 && s.getId() != 0) ? s.getId() : null);
			List<RailRacking> railRackingList = railRackingRepo.findByIdIn(roofRailRackingID);
			for (RailRacking railRacking : railRackingList) {
				RailRacking.put(railRacking, Collections.frequency(roofRailRackingID, railRacking.getId()));
			}
			return RailRacking;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}

	// Roof Attachment
	public LinkedHashMap<RoofAttachmentsEntity, Integer> getusedRoofAttachment(Date thendate) {
		try {
			LinkedHashMap<RoofAttachmentsEntity, Integer> roofAttachment = new LinkedHashMap<>();
			List<String> attachments = siteInfoRepo.findAttachementList(thendate);
			List<Long> attachmentID = new ArrayList<>();
			for(String s : attachments) attachmentID.add(checkValueTypesService.isNumeric(s) ? Long.valueOf(s) : null);
			List<RoofAttachmentsEntity> attachmentList = roofAttachmentRepo.findByIdIn(attachmentID);
			for (RoofAttachmentsEntity attachment : attachmentList) {
				roofAttachment.put(attachment, Collections.frequency(attachmentID, attachment.getId()));
			}
			return roofAttachment;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}
	
	// Flashing
	public LinkedHashMap<Flashing, Integer> getusedFlashing(Date thendate) {
		try {
			LinkedHashMap<Flashing, Integer> Flashing = new LinkedHashMap<>();
			List<String> flashings = siteInfoRepo.findFlashingList(thendate);
			List<Long> flashingID = new ArrayList<>();
			for(String s : flashings) flashingID.add(checkValueTypesService.isNumeric(s) ? Long.valueOf(s) : null);
			List<Flashing> flashingList = flashingRepo.findByIdIn(flashingID);
			for (Flashing flashing : flashingList) {
				Flashing.put(flashing, Collections.frequency(flashingID, flashing.getId()));
			}
			return Flashing;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}

	public LinkedHashMap<DCCombinerDisconnectEntity, Integer> getusedDCCombinerDisconnect(Date thendate) {
		try {
			LinkedHashMap<DCCombinerDisconnectEntity, Integer> dcCombinerDisconnect = new LinkedHashMap<>();
			List<DCCombinerDisconnectUsedModel> dcModelList = siteInfoRepo.findDCList(thendate);
			List<Long> dcModelsList = new ArrayList<>();
			for (DCCombinerDisconnectUsedModel dc : dcModelList) {
				dcModelsList.add(dc.getDisconnectModel());
				dcModelsList.add(dc.getDisconnectModelTwo());
				dcModelsList.add(dc.getDisconnectModelThree());
				dcModelsList.add(dc.getRoofTopDCCombiner());
				dcModelsList.add(dc.getRoofTopDCDisco());
			}
			List<DCCombinerDisconnectEntity> dcList = dcCombinerDiscoRepo.findByIdInAndIsDeleted(dcModelsList, false);
			for (DCCombinerDisconnectEntity dcE : dcList) {
				dcCombinerDisconnect.put(dcE, Collections.frequency(dcModelsList, dcE.getId()));
			}
			return dcCombinerDisconnect;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}
	
	public LinkedHashMap<DCCombinerDisconnectEntity, Integer> getusedJunctionBox(Date thendate) {
		try {
			LinkedHashMap<DCCombinerDisconnectEntity, Integer> JunctionBox = new LinkedHashMap<>();
			
			List<String> junctionBoxlList = siteInfoRepo.findJunctionBoxList(thendate);
			junctionBoxlList.addAll(siteInfoRepo.findJunctionBoxDCList(thendate));
			List<Long> junctionBoxlListID = new ArrayList<>();
			for(String s : junctionBoxlList) junctionBoxlListID.add(checkValueTypesService.isNumeric(s) ? Long.valueOf(s) : null);
			List<DCCombinerDisconnectEntity> dcList = dcCombinerDiscoRepo.findByIdInAndIsDeleted(junctionBoxlListID, false);
			for (DCCombinerDisconnectEntity dcE : dcList) {
				JunctionBox.put(dcE, Collections.frequency(junctionBoxlListID, dcE.getId()));
			}
			return JunctionBox;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}

	public LinkedHashMap<ACCombinerSLC, Integer> getusedACCombinerSLC(Date thendate) {
		try {
			LinkedHashMap<ACCombinerSLC, Integer> aCCombinerSLC = new LinkedHashMap<>();
			List<ACCombinerSLCUsedModel> aclList = siteInfoRepo.findACCombinerSLCList(thendate);
			List<Long> acListID = new ArrayList<>();
			for (ACCombinerSLCUsedModel dc : aclList) {
				acListID.add(dc.getACCombinerInstalled());
				acListID.add(dc.getGroundLevelACCombinerBoxModel());
				acListID.add(dc.getRoofTopACCombiner());
			}
			List<ACCombinerSLC> acList = acCombinerSLCRepo.findByIdInAndIsDeleted(acListID, false);
			for (ACCombinerSLC acE : acList) {
				aCCombinerSLC.put(acE, Collections.frequency(acListID, acE.getId()));
			}
			return aCCombinerSLC;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}
	
	public LinkedHashMap<ACDisconnect, Integer> getusedACDisconnect(Date thendate) {
		try {
			LinkedHashMap<ACDisconnect, Integer> aCDisconnect = new LinkedHashMap<>();
			List<ACDisconnectUsedModel> aclList = siteInfoRepo.findACDisconnectList(thendate);
			List<Long> acListID = new ArrayList<>();
			for (ACDisconnectUsedModel dc : aclList) {
				acListID.add(checkValueTypesService.contains(dc.getRooftopACCombinerModel(), ":") ? Long.valueOf(dc.getRooftopACCombinerModel().split(":")[0]) : null);
				acListID.add(checkValueTypesService.contains(dc.getRooftopACCombinerModelTwo(), ":") ? Long.valueOf(dc.getRooftopACCombinerModelTwo().split(":")[0]) : null);
				acListID.add(checkValueTypesService.contains(dc.getRoofTopACDisco(), ":") ? Long.valueOf(dc.getRoofTopACDisco().split(":")[0]) : null);
			}
			List<ACDisconnect> acList = acDisconnectRepo.findByIdInAndIsDeleted(acListID, false);
			for (ACDisconnect acE : acList) {
					aCDisconnect.put(acE, Collections.frequency(acListID, acE.getId()));
			}
			return aCDisconnect;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}

	public LinkedHashMap<LeasePPAMeter, Integer> getusedRevenuMeter(Date thendate) {
		try {
			LinkedHashMap<LeasePPAMeter, Integer> revenuMeter = new LinkedHashMap<>();
			List<String> revenuMeters = siteInfoRepo.findLeasePPAMeterList(thendate);
			List<Long> revenuMetersID = new ArrayList<>();
			for(String s : revenuMeters) revenuMetersID.add(checkValueTypesService.isNumeric(s) ? Long.valueOf(s) : null);
			List<LeasePPAMeter> revenuMetersist = leasePPAMeterRepo.findByIdIn(revenuMetersID);
			for (LeasePPAMeter rv : revenuMetersist) {
				revenuMeter.put(rv, Collections.frequency(revenuMetersID, rv.getId()));
			}
			return revenuMeter;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}
	
	public LinkedHashMap<RoofMaterialType, Integer> getusedRoofingMaterial(Date thendate) {
		try {
			LinkedHashMap<RoofMaterialType, Integer> roofList = new LinkedHashMap<>();
			List<String> roofMaterial = siteInfoRepo.findRoofMaterialList(thendate);
			List<Long> roofMaterialID = new ArrayList<>();
			for(String s : roofMaterial) roofMaterialID.add(checkValueTypesService.isNumeric(s) ? Long.valueOf(s) : null);
			List<RoofMaterialType> revenuMetersist = roofMaterialTypeRepo.findByIdIn(roofMaterialID);
			for (RoofMaterialType rv : revenuMetersist) {
				roofList.put(rv, Collections.frequency(roofMaterialID, rv.getId()));
			}
			return roofList;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashMap<>();
		}
	}

}
