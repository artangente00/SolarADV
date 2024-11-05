package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetFileUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.drafter_details.DrafterSheetsMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetInverterData;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetWeatherData;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.AcCircuitLogic;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.dc_circuit.DcCircuitLogic;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.VoltageDropMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.ac_circuit.ACCircuitLogic;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.dc_circuit.DCCircuitLogic;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class E002StringMappingService {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final GetWeatherData weatherData;
	final GetInverterData getInverterData;
	final PlansetFileUtils plansetFileUtils;
	final TechnicalProblemMsg technicalProblem;
	final DrafterSheetsMapping addDrafterMapping;
	final GenerateCircuitList generateCircuitList;
	final CircuitEnvironment circuitEnvironmentService;
	final DcCircuitLogic dcCircuitLogic;
	final AcCircuitLogic acCircuitLogic;
	final DCCircuitLogic essDcCircuitLogic;
	final ACCircuitLogic essAcCircuitLogic;
	final VoltageDropMapping voltageDropMapping;
	final ESSConnectorsRepository essConnectorsRepo;

	public E002StringMappingService(CheckValueTypesService checkValue, GetPDFReaderService getPDFReaderService,
			PlansetLogo_SignatureMappingService logoSignMapping, GetWeatherData weatherData,
			GetInverterData getInverterData, PlansetFileUtils plansetFileUtils, TechnicalProblemMsg technicalProblem,
			DrafterSheetsMapping addDrafterMapping, GenerateCircuitList generateCircuitList,
			CircuitEnvironment circuitEnvironmentService, DcCircuitLogic dcCircuitLogic, AcCircuitLogic acCircuitLogic,
			VoltageDropMapping voltageDropMapping, DCCircuitLogic essDcCircuitLogic,
			ESSConnectorsRepository essConnectorsRepo, ACCircuitLogic essAcCircuitLogic) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignMapping = logoSignMapping;
		this.weatherData = weatherData;
		this.getInverterData = getInverterData;
		this.plansetFileUtils = plansetFileUtils;
		this.technicalProblem = technicalProblem;
		this.addDrafterMapping = addDrafterMapping;
		this.generateCircuitList = generateCircuitList;
		this.circuitEnvironmentService = circuitEnvironmentService;
		this.dcCircuitLogic = dcCircuitLogic;
		this.acCircuitLogic = acCircuitLogic;
		this.voltageDropMapping = voltageDropMapping;
		this.essDcCircuitLogic = essDcCircuitLogic;
		this.essAcCircuitLogic = essAcCircuitLogic;
		this.essConnectorsRepo = essConnectorsRepo;
	}

	public File buildingPDFE002(PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, PermitEntity permitEntity,
			PermtiWeatherEntityResult permtiWeather, PermitArrayEntityResultSecond permitArraysEntityResult,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, Cmodulev2 moduleInfo, Inverters inverterInfo,
			Inverters secondInverterInfo, PermitLayoutEntity permitLayoutEntity,
			PermitConduitConductorSectionEntity circuit, UserSettingEntity userSetting, DCOptimizerEntity dcOptimizer,
			AuthentificationEntity userConnectedEntity, List<String> dcCircuitEnvironment,
			List<String> acCircuitEnvironment, List<String> dcTradeSize, List<String> acTradeSize,
			List<Integer> dcNumberOfConductors, List<Integer> acNumberOfConductors, String necCode, PdfReader reader,
			int sheetIndex, String filePath, AHJNotesModel ahjNotes, String necOrCecNote, Inverters thirdInverterInfo,
			Inverters fourthInverterInfo, Inverters fifthInverterInfo, PlansetUtils plansetUtils,
			Boolean includeBattery) {

		File fileRe = null;
		try {
			fileRe = new File(
					Constants.rapportPlansetFolderUrl + "PDF-E002-STRING" + idPermit + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-E002-STRING" + idPermit + "-" + sheetIndex + ".pdf");
			}
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();

			// A.B remove sheet index if exist when the project was uploaded
			if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}

			PdfReader readerOriginNEC = new PdfReader(
					Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-E002-STRING.pdf");
			PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-E002-STRING.pdf");

			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "E002");

			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
					sheetIndex);
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
					sheetIndex);

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

			if (checkValue.isStringNotEmpty(ahjNotes.getE002Note())) {
				Rectangle linkLocation1 = new Rectangle(40, 40, 240, 140);
				PdfAnnotation stamp = PdfAnnotation.createPopup(stamper.getWriter(), linkLocation1,
						ahjNotes.getE002Note(), true);
				stamper.addAnnotation(stamp, 1);
			}

			// ***************** Shared DC & AC Attributes *******************//
			E002Model params = new E002Model();
			// ***************** Split DC & AC Circuit *******************//
			String dcCircuit = "";
			if (circuit.getComponentOrder() != null) {
				String converterType = dcOptimizer != null ? dcOptimizer.getType() : null;
				dcCircuit = generateCircuitList.getConductorCircuit(circuit,
						permitArraysEntityResult.getDeviceToIncorporate(), converterType);
			}

			int dcCircuitLength = dcCircuit.split("-").length;

			/// ------------------------- Get Project Weather ---------------------///
			Integer fourPerCentAverageHigh = weatherData.getFourPerCentAverageHigh(permtiWeather);
			Integer twoPerCentAverageHigh = weatherData.getTwoPerCentAverageHigh(permtiWeather);

			String sumIacMax = getInverterData.getSumIacMax(Arrays.asList(inverterInfo, secondInverterInfo,
					thirdInverterInfo, fourthInverterInfo, fifthInverterInfo));
			String largestIacMax = getInverterData.getLargestIacMax(inverterInfo, secondInverterInfo, thirdInverterInfo,
					fourthInverterInfo, fifthInverterInfo);

			/// ------------------------- CIRCUIT ENVIRENMENT ---------------------///
			SystemEnvironment systemEnvironment = circuitEnvironmentService
					.getCircuitEnvironment(permitArraysEntityResult, permitLayoutEntity, userSetting, necCode);

			// S.B 29/09/2020 CR-PM-3365-MOD-001
			form.setField(sheetIndex + "-" + "NEC/CEC-NOTE", necOrCecNote);

			try {
				if (Boolean.TRUE.equals(includeBattery)) {
					List<Integer> inverterIndexList = essConnectorsRepo.findInverterIndex(idPermit);
					Integer inverterIndex = inverterIndexList != null && !inverterIndexList.isEmpty()
							? inverterIndexList.get(0)
							: 2;
					List<ESSConnectors> dcList = essConnectorsRepo
							.findByIndexLessThanAndProjectIdOrderByIndex(inverterIndex, permitEntity.getId());
					/// ------------------------- DC CIRCUIT Mapping ---------------------///
					essDcCircuitLogic.dcMapping(form, dcList, fourPerCentAverageHigh, twoPerCentAverageHigh,
							dcCircuitEnvironment, circuit, permitEntity, dcTradeSize, dcNumberOfConductors,
							permitArraysEntityResult, userConnectedEntity, moduleInfo, sheetIndex, userSetting,
							systemEnvironment, permitHomeSite.getState(), dcOptimizer, permitLayoutEntity, params,
							inverterInfo, plansetUtils, necCode);

					/// ------------------------- AC CIRCUIT Mapping ---------------------///
					List<ESSConnectors> acList = essConnectorsRepo
							.findByIndexGreaterThanAndProjectIdOrderByIndex(inverterIndex - 1, permitEntity.getId());
					essAcCircuitLogic.acMapping(form, acList, sumIacMax, largestIacMax, permitArraysEntityResult,
							permitProjectSiteInfo, fourPerCentAverageHigh, twoPerCentAverageHigh, acCircuitEnvironment,
							circuit, permitEntity, acTradeSize, acNumberOfConductors, userConnectedEntity, sheetIndex,
							permitHomeSite, permitLayoutEntity, userSetting, dcTradeSize, dcNumberOfConductors,
							dcCircuitEnvironment, dcList, moduleInfo, dcOptimizer, params, inverterInfo, plansetUtils,
							necCode);
				} else {
					/// ------------------------- DC CIRCUIT Mapping ---------------------///
					dcCircuitLogic.dcMapping(form, dcCircuit, fourPerCentAverageHigh, twoPerCentAverageHigh,
							dcCircuitEnvironment, circuit, permitEntity, dcTradeSize, dcNumberOfConductors,
							permitArraysEntityResult, userConnectedEntity, moduleInfo, sheetIndex, userSetting,
							systemEnvironment, permitHomeSite.getState(), dcOptimizer, dcCircuitLength,
							permitLayoutEntity, params, inverterInfo, plansetUtils, necCode);

					/// ------------------------- AC CIRCUIT Mapping ---------------------///

					acCircuitLogic.acMapping(form, sumIacMax, largestIacMax, permitArraysEntityResult,
							permitProjectSiteInfo, fourPerCentAverageHigh, twoPerCentAverageHigh, acCircuitEnvironment,
							circuit, permitEntity, acTradeSize, acNumberOfConductors, userConnectedEntity, sheetIndex,
							permitHomeSite, permitLayoutEntity, userSetting, dcTradeSize, dcNumberOfConductors,
							dcCircuitEnvironment, dcCircuitLength, dcCircuit, moduleInfo, dcOptimizer, params,
							inverterInfo, plansetUtils, necCode);
				}

			} catch (Exception e) {
				e.printStackTrace();
				technicalProblem.traiterException(e);
			}

			// ******************************//
			if (params.getVoltageDropTable() != null && !params.getVoltageDropTable().isEmpty()) {
				voltageDropMapping.mapVoltageDrop(sheetIndex, permitEntity.getId(), params.getVoltageDropTable());
				voltageDropMapping.insertVoltageDropTable(sheetIndex, permitEntity.getId(), stamper, 640f, 278f);
			}
			stamper.close();
			reader.close();
			File filePV = new File(
					filePath + "Rapport/E002StringModification" + permitEntity.getId() + "-" + sheetIndex + ".pdf");
			if (filePV.exists()) {
				filePV.delete();
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);

		}

		return fileRe;

	}
}
