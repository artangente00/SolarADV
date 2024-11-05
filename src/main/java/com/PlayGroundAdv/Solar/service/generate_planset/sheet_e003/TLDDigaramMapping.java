package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpLocation;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpProcessor;

@Service
public class TLDDigaramMapping {

	final CheckValueTypesService checkValue;
	final GetTLDSheet getTLDSheet;
	final TLDValueMapping tldValueMapping;
	final TechnicalProblemMsg technicalProblemMsg;

	public TLDDigaramMapping(CheckValueTypesService checkValue, GetTLDSheet getTLDSheet,
			TLDValueMapping tldValueMapping, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.getTLDSheet = getTLDSheet;
		this.tldValueMapping = tldValueMapping;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public void tldSheetMapping(PdfStamper stamper, Cmodulev2 moduleInfo, PermitEntity permitEntity,
			PermitAdvEntityResult editPermitAdvRequest, PermitConduitConductorSectionEntity circuit,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PermitHomeSiteInfoEntity permitHomeSite,
			PermitArrayEntityResultSecond arrays, Inverters inverterInfo, Inverters secondInverterInfo,
			Inverters microInverterInfo, PlansetUtils plansetUtils, DCOptimizerEntity dcOptimizer,
			ACDisconnect acDisconnect, ACDisconnect secondacDisconnect, Inverters thirdInverterInfo2,
			Inverters fourthInverterInfo, ACCombinerSLC slcAcCombiner, Integer firsttInverterQty,
			Integer secondtInverterQty, List<Inverters> inverters,
			PermitAdditionalInfoEntityResult permitAdditionalInfo, PermitEnergyBatterySystem energyBatterySystem) {
		try {

			String pdfName = getTLDSheet.getpdfName(editPermitAdvRequest, circuit, permitProjectSiteInfo,
					permitHomeSite, arrays, inverterInfo, secondInverterInfo, microInverterInfo, plansetUtils,
					dcOptimizer, acDisconnect, secondacDisconnect, permitAdditionalInfo, energyBatterySystem);
			
			File file3Line = new File(
					Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + pdfName + ".pdf");
			System.out.println("TLD: " + file3Line.exists() + " >> " + pdfName + ".pdf");
			if (file3Line.exists()) {

				try {
					List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
					if (checkValue.Equals(arrays.getDeviceToIncorporate(), "Micro Inverter")
							|| checkValue.Equals(arrays.getDeviceToIncorporate(), "AC Modules")) {
						cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f, 45f, 428f, 750f), BaseColor.WHITE));
						cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f, 45f, 1090f, 380f), BaseColor.WHITE));

					}else {
						cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f,45f, 470f, 750f), BaseColor.WHITE));
						cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f,45f, 1090f, 360f), BaseColor.WHITE));
					}
					PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
					cleaner.cleanUp();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (checkValue.Equals(arrays.getDeviceToIncorporate(), "Micro Inverter")
						|| checkValue.Equals(arrays.getDeviceToIncorporate(), "AC Modules")) {
					tldValueMapping.getThreeLineMappingMicro(moduleInfo, permitEntity, arrays, permitHomeSite,
							microInverterInfo, acDisconnect, permitProjectSiteInfo, slcAcCombiner, secondacDisconnect,
							plansetUtils.getModulePerMicroInverter(), pdfName);
				} else {
					tldValueMapping.getThreeLineMapping(moduleInfo, permitEntity, inverters, arrays, permitHomeSite,
							secondInverterInfo, thirdInverterInfo2, fourthInverterInfo, acDisconnect,
							permitProjectSiteInfo, slcAcCombiner, secondacDisconnect, firsttInverterQty,
							secondtInverterQty, pdfName);
				}
				String[] EXTRA = { Constants.rapportPlansetFolderUrl + pdfName + permitEntity.getId() + ".pdf" };
				File checkfile = new File(Constants.rapportPlansetFolderUrl + pdfName + permitEntity.getId() + ".pdf");
				if (checkfile.exists()) {
					PdfContentByte canvas = stamper.getUnderContent(1);
					PdfReader r;
					PdfImportedPage page;
					for (String path1 : EXTRA) {
						r = new PdfReader(path1);
						page = stamper.getImportedPage(r, 1);
						canvas.addTemplate(page, 10, 10);
						stamper.getWriter().freeReader(r);
						r.close();
					}
				}

				File file3 = new File(Constants.rapportPlansetFolderUrl + pdfName + permitEntity.getId() + ".pdf");
				if (file3.exists()) {
					file3.delete();
				}
			}

			

		} catch (Exception e) {
			technicalProblemMsg.traiterException(e);
		}
	}
}
