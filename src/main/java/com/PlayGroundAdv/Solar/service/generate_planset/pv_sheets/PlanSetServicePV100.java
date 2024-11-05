package com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.SelectDrafterSheetRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.drafter_details.DrfaterDetailsMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ElectricUtilityNumber;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleInverterMfgQty;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;



@Service
@Transactional
public class PlanSetServicePV100 {
	
	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignatureMapping;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	final DrfaterDetailsMapping drfaterDetailsMapping;
	final ModuleInverterMfgQty moduleInverterMfgQty;
	final TechnicalProblemMsg technicalProblemMsg;
	final ElectricUtilityNumber electricUtilityNumber;
	final SelectDrafterSheetRepository selectDrafterSheetRepo;
	
	public PlanSetServicePV100(CheckValueTypesService checkValue, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignatureMapping,
			RoofMaterialTypeRepository roofMaterialTypeRepo, DrfaterDetailsMapping drfaterDetailsMapping,
			ModuleInverterMfgQty moduleInverterMfgQty,TechnicalProblemMsg technicalProblemMsg,
			ElectricUtilityNumber electricUtilityNumber, SelectDrafterSheetRepository selectDrafterSheetRepo) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignatureMapping = logoSignatureMapping;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
		this.drfaterDetailsMapping = drfaterDetailsMapping;
		this.moduleInverterMfgQty = moduleInverterMfgQty;
		this.technicalProblemMsg = technicalProblemMsg;
		this.electricUtilityNumber = electricUtilityNumber;
		this.selectDrafterSheetRepo = selectDrafterSheetRepo;
	}

	public void mapPermitSketchEntity(List <PermitSketchEntity> listPermitSketchEntity, AcroFields form, int sheetIndex) {
		try {
			if(listPermitSketchEntity != null){
				/*****************************************thTH AND TILS TABLE*********************/
				for (int j = 0; j<11;j++) {
					form.setField(sheetIndex+"-"+"SECTION-"+j+"-AZIMUTH","");
					form.setField(sheetIndex+"-"+"SECTION-"+j, "");
					form.setField(sheetIndex+"-"+"SECTION-"+j+"-ROOF-PITCH-TILT","");
				}
				int i = 0;
				for (PermitSketchEntity permitSketchEntity : listPermitSketchEntity) {
					if (checkValue.NotEquals(permitSketchEntity.getAzimuth(),"null")) {
							i++;
							form.setField(sheetIndex+"-"+"SECTION-"+i+"-AZIMUTH", permitSketchEntity.getAzimuth());
							form.setField(sheetIndex+"-"+"SECTION-"+i, "SECTION-"+permitSketchEntity.getArraySketch());
							form.setField(sheetIndex+"-"+"SECTION-"+i+"-ROOF-PITCH-TILT",permitSketchEntity.getModelvalue()+"°" );
						
					}
				}

				for (i = listPermitSketchEntity.size()+1; i<11;i++) {
							form.setField(sheetIndex+"-"+"SECTION-"+i+"-AZIMUTH","");
							form.setField(sheetIndex+"-"+"SECTION-"+i, "");
							form.setField(sheetIndex+"-"+"SECTION-"+i+"-ROOF-PITCH-TILT","");
				}
			}
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapExistingRoof(AcroFields form, PermitProjectSiteInfoEntity permitProjectSiteInfo, int sheetIndex) {
		try {
			if (permitProjectSiteInfo!= null && checkValue.isLongPositive(permitProjectSiteInfo.getRoofMaterialType())) {
				RoofMaterialType roofMaterialType =  roofMaterialTypeRepo.findById(permitProjectSiteInfo.getRoofMaterialType()).orElse(null);
				form.setField(sheetIndex+"-"+"EXISTING-ROOF", roofMaterialType != null ? roofMaterialType.getTypeRoof() : "");
			}
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

 	public File buildingPDFPV100(PermitHomeSiteInfoEntity permitHomeSite, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			PermitEntity permitEntity, List<PermitSketchEntity> listPermitSketchEntity,
			PermitDrafterDataEntity permitDrafterDatanfo,PermitArrayEntityResultSecond permitArraysEntityResult, ElectricalUtilityEntity electricalCompany,
			PdfReader reader,int sheetIndex,String sheetName,String filePath, 
			AHJNotesModel ahjNotes, Inverters inverterInfo, Cmodulev2 moduleInfo, Inverters microInverterInfo,  PlansetUtils plansetUtils){
		
		Locale.setDefault(new Locale("en", "US"));
		try {
			Long idPermit = permitEntity.getId();
			File fileRe = new File( Constants.rapportPlansetFolderUrl + "PDF-"+ sheetName +"R"+idPermit+"-"+sheetIndex+".pdf" );
			if(fileRe.exists()){
				fileRe.delete();
				fileRe = new File( Constants.rapportPlansetFolderUrl + "PDF-"+ sheetName +"R"+idPermit+"-"+sheetIndex+".pdf" );
				}
			
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			//stamper.getPdfLayer
			AcroFields form = stamper.getAcroFields();
			
			//A.B remove sheet index if exist when the project was uploaded
			if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}
			
			PdfReader readerOriginNEC = new PdfReader( Constants.rapportPlansetFolderUrl +"NEC-PDF/" + "PDF-"+ sheetName +"R.pdf" );
			PdfReader readerOriginCEC = new PdfReader( Constants.rapportPlansetFolderUrl + "PDF-"+ sheetName +"R.pdf" );
			//A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, sheetName);
			//A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form, sheetIndex);
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form, sheetIndex);

			//A.B CR-3250 03-30 Logo & Signature Mapping 
			logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);
			
			//A.B 07-14-2021 CR-3064
			form.setField(sheetIndex+"-"+"SPECIAL-NOTE-PV-100R",ahjNotes.getPV100RNote());
			//Get Permit Total Section Entity List
			mapPermitSketchEntity(listPermitSketchEntity, form, sheetIndex);
				/*****************************************END AZIMUTH AND TILS TABLE*********************/
				/***************************Square Footage Table************************************/
			
			mapExistingRoof(form, permitProjectSiteInfo, sheetIndex);	
			//A.B 09-22 CR-3438
			drfaterDetailsMapping.squareFootageMapping(form, sheetIndex, permitDrafterDatanfo, listPermitSketchEntity);
			drfaterDetailsMapping.pv100ScaleMapping(form, sheetIndex, "SCALE-"+ sheetName, permitDrafterDatanfo);
			
			
			// F.B 05-25-2022 CR-915-MOD-002				
			String pageSheet = selectDrafterSheetRepo.findPageSheetByIdPermitAndPageNumber(idPermit,6);
			if (checkValue.Equals(pageSheet, "PV101R"))
				if((checkValue.Equals(permitDrafterDatanfo.getScalePV101(), "Other") && permitDrafterDatanfo.getScalePV101Other() != null && checkValue.NotEquals(permitDrafterDatanfo.getScalePV101Other().trim(), "")) 
						|| checkValue.NotEquals(permitDrafterDatanfo.getScalePV101(), "Other")) {
				drfaterDetailsMapping.pv101XScaleMapping(form, sheetIndex, "SCALE-PV101", permitDrafterDatanfo);				
			}
			
			// A.B 01-11-2022 CR-PM-REV-2197-MOD-15
			String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other")
					? permitHomeSite.getUtilityCompanyNameOther()
					: electricalCompany.getUtilityCompanyName();
			moduleInverterMfgQty.moduleInverterMfgQty(sheetName == "PV100" ? "-PV-100R" : "-PV-101R", form, permitArraysEntityResult.getDeviceToIncorporate(), idPermit,
					sheetIndex, inverterInfo, moduleInfo, microInverterInfo, utility, permitHomeSite.getMeterNumber(),   plansetUtils);
			electricUtilityNumber.mapEsiId(form, sheetIndex, sheetName, permitHomeSite, electricalCompany);

			if (checkValue.NotEquals(permitHomeSite.getState(), "CA")) {
				form.setField(sheetIndex + "-" + sheetName + "-ACD-NOTE",
						"PV AC DISCONNECT LOCATED ON ACCESSIBLE EXTERIOR WALL WITH EXTERNAL HANDLE VISIBLE, LOCKABLE & LABELED WITHIN 10 FEET OF THE METER");
			}
			
			stamper.close();
			reader.close();
			return fileRe;
		} catch (IOException|DocumentException e) {
			technicalProblemMsg.traiterException(e);
			return null;
		}
	}
	
}
