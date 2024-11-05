package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDesignCriteriaModel;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetWeatherData;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class PlanSetServiceE001 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final PVModuleSpec pvModuleSpec;
	final GetACDisconnectQty qCDisconnectQty;
	final ACDisconnectSpec acDisconnectSpec;
	final DCDisconnectSpec dcDisconnectSpec;
	final InvertersSpec invertersSpec;
	final DCConverterSpec dcConverterSpec;
	final ACCombinerSpec acCombinerSpec;
	final ACSubPanelSpec acSubPanelSpec;
	final MainServicePanelSpec mainServicePanelSpec;
	final GetWeatherData getWeatherData;
	final PVSystemMaximumVoltage pvSystemMaxVoltage;
	final TechnicalProblemMsg technicalProblemMsg;

	public PlanSetServiceE001(CheckValueTypesService checkValue, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignMapping,
			PVModuleSpec pvModuleSpec, GetACDisconnectQty qCDisconnectQty, ACDisconnectSpec acDisconnectSpec,
			DCDisconnectSpec dcDisconnectSpec, InvertersSpec invertersSpec, DCConverterSpec dcConverterSpec,
			ACCombinerSpec acCombinerSpec, TechnicalProblemMsg technicalProblemMsg, ACSubPanelSpec acSubPanelSpec,
			GetWeatherData getWeatherData, MainServicePanelSpec mainServicePanelSpec,
			PVSystemMaximumVoltage pvSystemMaxVoltage) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignMapping = logoSignMapping;
		this.pvModuleSpec = pvModuleSpec;
		this.qCDisconnectQty = qCDisconnectQty;
		this.acDisconnectSpec = acDisconnectSpec;
		this.dcDisconnectSpec = dcDisconnectSpec;
		this.invertersSpec = invertersSpec;
		this.dcConverterSpec = dcConverterSpec;
		this.acCombinerSpec = acCombinerSpec;
		this.technicalProblemMsg = technicalProblemMsg;
		this.acSubPanelSpec = acSubPanelSpec;
		this.getWeatherData = getWeatherData;
		this.pvSystemMaxVoltage = pvSystemMaxVoltage;
		this.mainServicePanelSpec = mainServicePanelSpec;
	}


	public File buildingPDFE001(PermitHomeSiteInfoEntity permitHomeSite, PermtiWeatherEntityResult permtiWeather,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PermitEntity permitEntity, Cmodulev2 moduleInfo,
			Inverters microInverterInfo, PermitArrayEntityResultSecond permitArraysEntityResult,
			PlansetUtils plansetUtils, DCOptimizerEntity dcOptimizer, DCCombinerDisconnectEntity dcCombinerDisconnect,
			DCCombinerDisconnectEntity seconddcCombinerDisconnect, DCCombinerDisconnectEntity thirddcCombinerDisconnect,
			ACDisconnect acDisconnect, ACDisconnect acDisconnectTwo, ACCombinerSLC acCombiner,
			List<Inverters> inverters, Integer firsttInverterQty, Integer secondtInverterQty,
			PermitConduitConductorSectionEntity circuit, ACCombinerSLC slcAcCombiner,
			DCCombinerDisconnectEntity roofTopDCDisconnect, PdfReader reader, int sheetIndex, String filePath,
			AHJDesignCriteriaModel designCriteria, ACDisconnect acDisconnectThree, ACDisconnect acDisconnectFour) {

		File fileRe = null;
		String extremeMinimum = getWeatherData.getExtremeMinimum(permtiWeather, designCriteria);

		if ((checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")
				|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer"))) {
			try {

				fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E001-STRING" + permitEntity.getId() + "-"
						+ sheetIndex + ".pdf");
				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E001-STRING" + permitEntity.getId() + "-"
							+ sheetIndex + ".pdf");
				}
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();

				// A.B remove sheet index if exist when the project was uploaded
				if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}
				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-E001-STRING.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-E001-STRING.pdf");

				// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
				getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "E001");
				// A.B: Set PDF Font For Revision
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
						sheetIndex);
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
						sheetIndex);

				// A.B CR-3250 03-30 Logo & Signature Mapping
				logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

				//////////////////////// PV Module 1 Specification /////////////////////
				pvModuleSpec.pvModuleMapping(moduleInfo, form, sheetIndex, permitArraysEntityResult);

				//////////////////////// DC Optimizer 1 /////////////////////
				dcConverterSpec.optimizerMapping(permitArraysEntityResult, dcOptimizer, form, sheetIndex);

				//////////////////////// DC Combiner / Disconnect 1 /////////////////////
				dcDisconnectSpec.dcDisconnectMapping(form, sheetIndex, dcCombinerDisconnect, roofTopDCDisconnect,
						moduleInfo, inverters, permitProjectSiteInfo.getLocation(), permitArraysEntityResult,
						plansetUtils, 1);
				//////////////////////// DC Combiner / Disconnect 2/////////////////////
				dcDisconnectSpec.dcDisconnectMapping(form, sheetIndex, seconddcCombinerDisconnect, null, moduleInfo,
						inverters, permitProjectSiteInfo.getLocationTwo(), permitArraysEntityResult, plansetUtils, 2);
				//////////////////////// DC Combiner / Disconnect 3/////////////////////
				dcDisconnectSpec.dcDisconnectMapping(form, sheetIndex, thirddcCombinerDisconnect, null, moduleInfo,
						inverters, permitProjectSiteInfo.getLocationThree(), permitArraysEntityResult, plansetUtils, 3);

				//////////////////////// String Inverter 1 Specification/////////////////////
				if (inverters != null && !inverters.isEmpty()) {
					invertersSpec.inverterMapping(form, sheetIndex, inverters.get(0), permitArraysEntityResult,
							firsttInverterQty, 1);

					//////////////////////// String Inverter 2 Specification/////////////////////
					if (inverters.size() > 1) {
						invertersSpec.inverterMapping(form, sheetIndex, inverters.get(1), permitArraysEntityResult,
								secondtInverterQty, 2);
					}
				}

				//////////////////////// AC Combiner 1/////////////////////
				acCombinerSpec.acCombinerMapping(form, permitProjectSiteInfo, inverters, firsttInverterQty,
						secondtInverterQty, plansetUtils.getInverterQty(), slcAcCombiner, sheetIndex);

				//////////////////////// AC Disco 1/////////////////////
				if (acDisconnect != null) {
					acDisconnectSpec.acDisconnectMapping(form, sheetIndex, acDisconnect, 1);
					acDisconnectSpec.acDisconnectCalcul(form, sheetIndex, acDisconnect, 1, inverters, firsttInverterQty,
							secondtInverterQty);
					acDisconnectSpec.acDisconnectQuantity(form, sheetIndex, acDisconnect, acDisconnectTwo,
							acDisconnectThree, acDisconnectFour, 1, circuit.getQtySegmentNine());
				}
				//////////////////////// AC Disco 2/////////////////////
				if (acDisconnectTwo != null) {
					acDisconnectSpec.acDisconnectMapping(form, sheetIndex, acDisconnectTwo, 2);
					acDisconnectSpec.acDisconnectCalcul(form, sheetIndex, acDisconnectTwo, 2, inverters,
							firsttInverterQty, secondtInverterQty);
					acDisconnectSpec.acDisconnectQuantity(form, sheetIndex, acDisconnect, acDisconnectTwo,
							acDisconnectThree, acDisconnectFour, 2, circuit.getQtySegmentNineTwo());
				}

				//////////////////////// AC Sub Panel 1/////////////////////
				acSubPanelSpec.acSubPanelMapping(permitProjectSiteInfo, form, inverters, sheetIndex);

				//////////////////////// Main Service Panel/////////////////////
				mainServicePanelSpec.mainServicePanelString(permitProjectSiteInfo, form, permitHomeSite, inverters,
						sheetIndex);

				pvSystemMaxVoltage.extremeMinimumString(extremeMinimum, moduleInfo, form, inverters, sheetIndex);

				stamper.close();
				reader.close();

			} catch (IOException | DocumentException e) {
				e.printStackTrace();
				technicalProblemMsg.traiterException(e);
			}
		} else if ((checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")
				|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "AC Modules"))) {

			try {
				fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E001-MICRO" + permitEntity.getId() + "-"
						+ sheetIndex + ".pdf");
				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E001-MICRO" + permitEntity.getId() + "-"
							+ sheetIndex + ".pdf");
				}
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();

				// A.B remove sheet index if exist when the project was uploaded
				if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}
				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-E001-MICRO.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-E001-MICRO.pdf");
				// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
				getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "E001");
				// A.B: Set PDF Font For Revision
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
						sheetIndex);
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
						sheetIndex);

				// A.B CR-3250 03-30 Logo & Signature Mapping
				logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

				//////////////////////// PV Module 1 Specification /////////////////////
				pvModuleSpec.pvModuleMapping(moduleInfo, form, sheetIndex, permitArraysEntityResult);
				//////////////////////// Micro Inverter #1/////////////////////
				invertersSpec.microInverterMapping(permitArraysEntityResult, microInverterInfo, form, sheetIndex);

				//////////////////////// AC Combiner 1/////////////////////
				acCombinerSpec.acCombinerMapping(permitProjectSiteInfo, microInverterInfo, acCombiner, form,
						plansetUtils, slcAcCombiner, sheetIndex);

				//////////////////////// AC Disco 1/////////////////////
				if (acDisconnect != null) {
					acDisconnectSpec.acDisconnectMapping(form, sheetIndex, acDisconnect, 1);
					acDisconnectSpec.acDisconnectMicroCalcul(form, sheetIndex, acDisconnect, 1, microInverterInfo,
							plansetUtils.getModulePerMicroInverter(), circuit.getQtySegmentNine());
				}
				//////////////////////// AC Disco 2/////////////////////
				if (acDisconnectTwo != null) {
					acDisconnectSpec.acDisconnectMapping(form, sheetIndex, acDisconnectTwo, 2);
					acDisconnectSpec.acDisconnectMicroCalcul(form, sheetIndex, acDisconnectTwo, 2, microInverterInfo,
							plansetUtils.getModulePerMicroInverter(), circuit.getQtySegmentNineTwo());
				}
				//////////////////////// AC Sub Panel 1/////////////////////
				acSubPanelSpec.acSubPanelMico(permitProjectSiteInfo, form, permitArraysEntityResult, sheetIndex);

				//////////////////////// Main Service Panel/////////////////////
				mainServicePanelSpec.mainServicePanelMicro(permitProjectSiteInfo, form, permitHomeSite,
						permitArraysEntityResult, sheetIndex);

				pvSystemMaxVoltage.extremeMinimum(extremeMinimum, moduleInfo, form, sheetIndex);

				stamper.close();
				reader.close();

			} catch (IOException | DocumentException e) {
				e.printStackTrace();
				technicalProblemMsg.traiterException(e);
			}
		}

		return fileRe;
	}

}
