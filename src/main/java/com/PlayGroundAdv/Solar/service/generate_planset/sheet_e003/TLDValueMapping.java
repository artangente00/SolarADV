package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class TLDValueMapping {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;

	public TLDValueMapping(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public void getThreeLineMapping(Cmodulev2 moduleInfo, PermitEntity permitEntity, List<Inverters> inverters,
			PermitArrayEntityResultSecond arrays, PermitHomeSiteInfoEntity permitHomeSite,
			Inverters secondInverterInfo, Inverters thirdInverterInfo2, Inverters fourthInverterInfo,
			ACDisconnect acDisconnect, PermitProjectSiteInfoEntity permitProjectSiteInfo, ACCombinerSLC slcAcCombiner,
			ACDisconnect secondacDisconnect, Integer firsttInverterQty, Integer secondtInverterQty, String pdfName)
			throws IOException, DocumentException {
		try {
			PdfReader reader3Line = new PdfReader(
					Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + pdfName + ".pdf");
			File fileR3Line = new File(Constants.rapportPlansetFolderUrl + pdfName + permitEntity.getId() + ".pdf");
			if (fileR3Line.exists()) {
				fileR3Line.delete();
				fileR3Line = new File(Constants.rapportPlansetFolderUrl + pdfName + permitEntity.getId() + ".pdf");
			}
			PdfStamper stamper3Line = new PdfStamper(reader3Line, new FileOutputStream(fileR3Line));
			AcroFields form3Line = stamper3Line.getAcroFields();

			form3Line.setField("MOD-Nameplate-Rate", moduleInfo.getStcRounded());// *****

			if (inverters != null && !inverters.isEmpty()) {
				if (arrays.getStringOneInt() != null) {
					form3Line.setField("MOD-QTY-INV", String.valueOf(arrays.getStringOneInt()));
				}
				if (arrays.getStringTwoInt() != null) {
					form3Line.setField("2MOD-QTY-INV", String.valueOf(arrays.getStringTwoInt()));
				}
				if (arrays.getStringThreeInt() != null) {
					form3Line.setField("3MOD-QTY-INV", String.valueOf(arrays.getStringThreeInt()));
				}
				if (arrays.getStringFourInt() != null) {
					form3Line.setField("4MOD-QTY-INV", String.valueOf(arrays.getStringFourInt()));
				}
				if (arrays.getStringFiveInt() != null) {
					form3Line.setField("5MOD-QTY-INV", String.valueOf(arrays.getStringFiveInt()));
				}
				if (arrays.getSecondStringOneInt() != null) {
					form3Line.setField("MOD-QTY-2INV",
							String.valueOf(arrays.getSecondStringOneInt()));
				}
				if (arrays.getSecondStringTwoInt() != null) {
					form3Line.setField("2MOD-QTY-2INV",
							String.valueOf(arrays.getSecondStringTwoInt()));
				}
				if (arrays.getSecondStringThreeInt() != null) {
					form3Line.setField("3MOD-QTY-2INV",
							String.valueOf(arrays.getSecondStringThreeInt()));
				}
				if (arrays.getSecondStringFourInt() != null) {
					form3Line.setField("4MOD-QTY-2INV",
							String.valueOf(arrays.getSecondStringFourInt()));
				}
				if (arrays.getSecondStringFiveInt() != null) {
					form3Line.setField("5MOD-QTY-2INV",
							String.valueOf(arrays.getSecondStringFiveInt()));
				}
				if (arrays.getThirdStringOne() != null) {
					form3Line.setField("MOD-QTY-3INV", String.valueOf(arrays.getThirdStringOne()));
				}
				if (arrays.getThirdStringTwo() != null) {
					form3Line.setField("2MOD-QTY-3INV", String.valueOf(arrays.getThirdStringTwo()));
				}
				if (arrays.getThirdStringThree() != null) {
					form3Line.setField("3MOD-QTY-3INV", String.valueOf(arrays.getThirdStringThree()));
				}
				if (arrays.getThirdStringFour() != null) {
					form3Line.setField("4MOD-QTY-3INV", String.valueOf(arrays.getThirdStringFour()));
				}
				if (arrays.getThirdStringFive() != null) {
					form3Line.setField("5MOD-QTY-3INV", String.valueOf(arrays.getThirdStringFive()));
				}

				if (arrays.getFourthStringOne() != null) {
					form3Line.setField("MOD-QTY-4INV", String.valueOf(arrays.getFourthStringOne()));
				}
				if (arrays.getFourthStringTwo() != null) {
					form3Line.setField("2MOD-QTY-4INV", String.valueOf(arrays.getFourthStringTwo()));
				}
				if (arrays.getFourthStringThree() != null) {
					form3Line.setField("3MOD-QTY-4INV",
							String.valueOf(arrays.getFourthStringThree()));
				}
				if (arrays.getFourthStringFour() != null) {
					form3Line.setField("4MOD-QTY-4INV", String.valueOf(arrays.getFourthStringFour()));
				}
				if (arrays.getFourthStringFive() != null) {
					form3Line.setField("5MOD-QTY-4INV", String.valueOf(arrays.getFourthStringFive()));
				}

				if (arrays.getFifthStringOne() != null) {
					form3Line.setField("MOD-QTY-5INV", String.valueOf(arrays.getFifthStringOne()));
				}
				if (arrays.getFifthStringTwo() != null) {
					form3Line.setField("2MOD-QTY-5INV", String.valueOf(arrays.getFifthStringTwo()));
				}
				if (arrays.getFifthStringThree() != null) {
					form3Line.setField("3MOD-QTY-5INV", String.valueOf(arrays.getFifthStringThree()));
				}
				if (arrays.getFifthStringFour() != null) {
					form3Line.setField("4MOD-QTY-5INV", String.valueOf(arrays.getFifthStringFour()));
				}
				if (arrays.getFifthStringFive() != null) {
					form3Line.setField("5MOD-QTY-5INV", String.valueOf(arrays.getFifthStringFive()));
				}
			}

			String serviceVoltage = getServiceVoltage(permitHomeSite);

			if (secondInverterInfo != null) {
				form3Line.setField("2INV-Nameplate-Rate", secondInverterInfo.getPaco() + "W" + serviceVoltage);
			}
			if (thirdInverterInfo2 != null) {
				form3Line.setField("3INV-Nameplate-Rate", thirdInverterInfo2.getPaco() + "W" + serviceVoltage);
			}
			if (fourthInverterInfo != null) {
				form3Line.setField("4INV-Nameplate-Rate", fourthInverterInfo.getPaco() + "W" + serviceVoltage);
			}
			if (acDisconnect != null) {
				form3Line.setField("ACD-Nameplate-Rate", acDisconnect.getRatedCurrent() + "W" + serviceVoltage);
			}
			if (checkValue.Equals(permitProjectSiteInfo.getUseDisconectSwith(), "Yes") && secondacDisconnect != null
					&& secondacDisconnect.getId() != null) {
				form3Line.setField("2ACD-Nameplate-Rate", secondacDisconnect.getRatedCurrent() + "W" + serviceVoltage);
			}
//			SB 19/05/2020 CR-1580 V15c
			if (acDisconnect != null) {
				form3Line.setField("ACDF-Nameplate-Rate",
						"RATING " + acDisconnect.getRatedCurrent() + "A " + serviceVoltage);
			}
			float inputCurrent = getInputCurrent(inverters, firsttInverterQty, secondtInverterQty);
			String fuseRating = fuseRatingMapping(acDisconnect, inputCurrent);
			form3Line.setField("ACDF-Fuse-Rate", fuseRating);
			if (checkValue.NotEquals(permitProjectSiteInfo.getSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed1", permitProjectSiteInfo.getSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed1", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSecondSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed2", permitProjectSiteInfo.getSecondSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed2", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getThirdSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed3", permitProjectSiteInfo.getThirdSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed3", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getFourthSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed4", permitProjectSiteInfo.getFourthSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed4", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getFifthSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed5", permitProjectSiteInfo.getFifthSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed5", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSolarInterconnection(), "")) {
				form3Line.setField("SUB-Backfeed1", permitProjectSiteInfo.getSolarInterconnection() + "A");
			} else {
				form3Line.setField("SUB-Backfeed1", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSecondSolarInterconnection(), "")) {
				form3Line.setField("SUB-Backfeed2", permitProjectSiteInfo.getSecondSolarInterconnection() + "A");
			} else {
				form3Line.setField("SUB-Backfeed2", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getThirdSolarInterconnection(), "")) {
				form3Line.setField("SUB-Backfeed3", permitProjectSiteInfo.getThirdSolarInterconnection() + "A");
			} else {
				form3Line.setField("SUB-Backfeed3", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getFourthSolarInterconnection(), "")) {
				form3Line.setField("SUB-Backfeed4", permitProjectSiteInfo.getFourthSolarInterconnection() + "A");
			} else {
				form3Line.setField("SUB-Backfeed4", "");
			}

			if (slcAcCombiner != null) {
				form3Line.setField("SLC-BUS-Rate", slcAcCombiner.getRatedCurrent() + "A");
			} else {
				form3Line.setField("SLC-BUS-Rate", "");
			}
			/// column MAIN C/B RATING not exist yet in LOAD CENTER/SLC
			form3Line.setField("SLC-Main-C/B-Rate", "");
			if (checkValue.NotEquals(permitProjectSiteInfo.getSubPanelBusRating(), "")) {
				form3Line.setField("SUB-BUS-Rate", "RATTING" + permitProjectSiteInfo.getSubPanelBusRating() + "A");
			} else {
				form3Line.setField("SUB-BUS-Rate", "");

			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "") && checkValue
					.NotEquals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "Lug Only")) {
				form3Line.setField("SUB-Main-C/B-Rate",
						"RATTING" + permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating() + "A");

			} else {
				if (checkValue.NotEquals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "")
						&& checkValue.Equals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(),
								"Lug Only")) {
					form3Line.setField("SUB-Main-C/B-Rate", "MLO");
				} else {
					form3Line.setField("SUB-Main-C/B-Rate", "");
				}
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSolarInterconnection(), "")) {
				form3Line.setField("MSP-Backfeed1", permitProjectSiteInfo.getSolarInterconnection() + "A");
			} else {
				form3Line.setField("MSP-Backfeed1", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSecondSolarInterconnection(), "")) {
				form3Line.setField("MSP-Backfeed2", permitProjectSiteInfo.getSecondSolarInterconnection() + "A");
			} else {
				form3Line.setField("MSP-Backfeed2", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getThirdSolarInterconnection(), "")) {
				form3Line.setField("MSP-Backfeed3", permitProjectSiteInfo.getThirdSolarInterconnection() + "A");
			} else {
				form3Line.setField("MSP-Backfeed3", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getFourthSolarInterconnection(), "")) {
				form3Line.setField("MSP-Backfeed4", permitProjectSiteInfo.getFourthSolarInterconnection() + "A");
			} else {
				form3Line.setField("MSP-Backfeed4", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getPanelBusRating(), "")) {
				if (checkValue.Equals(permitProjectSiteInfo.getPanelBusRating(), "Other")) {
					form3Line.setField("MSP-BUS-Rate", permitProjectSiteInfo.getPanelBusRatingOther() + "A");
				} else {
					form3Line.setField("MSP-BUS-Rate", permitProjectSiteInfo.getPanelBusRating() + "A");
				}
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getPanelMainBreakerRating(), "")) {
				if (checkValue.NotEquals(permitProjectSiteInfo.getPanelMainBreakerRating(), "Other")) {
					form3Line.setField("MSP-Main-C/B-Rate", permitProjectSiteInfo.getPanelMainBreakerRating() + "A");
				} else {
					form3Line.setField("MSP-Main-C/B-Rate",
							permitProjectSiteInfo.getPanelMainBreakerRatingOther() + "A");
				}
			}

			stamper3Line.setFormFlattening(true);
			stamper3Line.close();
			reader3Line.close();
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void getThreeLineMappingMicro(Cmodulev2 moduleInfo, PermitEntity permitEntity,
			PermitArrayEntityResultSecond arrays, PermitHomeSiteInfoEntity permitHomeSite,
			Inverters microInverterInfo, ACDisconnect acDisconnect, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			ACCombinerSLC slcAcCombiner, ACDisconnect secondacDisconnect, int modulePerMicroInverter, String pdfName)
			throws IOException, DocumentException {
		try {
			PdfReader reader3Line = new PdfReader(
					Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + pdfName + ".pdf");
			File fileR3Line = new File(Constants.rapportPlansetFolderUrl + pdfName + permitEntity.getId() + ".pdf");
			if (fileR3Line.exists()) {
				fileR3Line.delete();
				fileR3Line = new File(Constants.rapportPlansetFolderUrl + pdfName + permitEntity.getId() + ".pdf");
			}
			PdfStamper stamper3Line = new PdfStamper(reader3Line, new FileOutputStream(fileR3Line));
			AcroFields form3Line = stamper3Line.getAcroFields();
			if (moduleInfo.getStcRounded() != null) {
				form3Line.setField("MOD-Nameplate-Rate", moduleInfo.getStcRounded());
			} // *****

			if (arrays.getNumberModulesACCircuitOne() != null) {
				form3Line.setField("MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitOne()));// ****
			}
			if (arrays.getNumberModulesACCircuitTwo() != null) {
				form3Line.setField("2MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitTwo()));// ****
			}
			if (arrays.getNumberModulesACCircuitThree() != null) {
				form3Line.setField("3MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitThree()));// ****
			}
			if (arrays.getNumberModulesACCircuitFour() != null) {
				form3Line.setField("4MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitFour()));// ****
			}
			if (arrays.getNumberModulesACCircuitFive() != null) {
				form3Line.setField("5MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitFive()));// ****
			}
			if (arrays.getNumberModulesACCircuitSix() != null) {
				form3Line.setField("6MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitSix()));// ****
			}
			if (arrays.getNumberModulesACCircuitSeven() != null) {
				form3Line.setField("7MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitSeven()));// ****
			}
			if (arrays.getNumberModulesACCircuitEight() != null) {
				form3Line.setField("8MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitEight()));// ****
			}
			if (arrays.getNumberModulesACCircuitNine() != null) {
				form3Line.setField("9MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitNine()));// ****
			}
			if (arrays.getNumberModulesACCircuitTen() != null) {
				form3Line.setField("10MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitTen()));// ****
			}

			if (arrays.getNumberModulesACCircuitEleven() != null) {
				form3Line.setField("11MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitEleven()));// ****
			}
			if (arrays.getNumberModulesACCircuitTweleve() != null) {
				form3Line.setField("12MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitTweleve()));// ****
			}
			if (arrays.getNumberModulesACCircuitThirteen() != null) {
				form3Line.setField("13MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitThirteen()));
			}
			if (arrays.getNumberModulesACCircuitFourteen() != null) {
				form3Line.setField("14MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitFourteen()));
			}
			if (arrays.getNumberModulesACCircuitFifteen() != null) {
				form3Line.setField("15MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitFifteen()));// ****
			}
			if (arrays.getNumberModulesACCircuitSixteen() != null) {
				form3Line.setField("16MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitSixteen()));// ****
			}
			if (arrays.getNumberModulesACCircuitSeventeen() != null) {
				form3Line.setField("17MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitSeventeen()));// ****
			}
			if (arrays.getNumberModulesACCircuitEightteen() != null) {
				form3Line.setField("18MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitEightteen()));// ****
			}
			if (arrays.getNumberModulesACCircuitNineteen() != null) {
				form3Line.setField("19MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitNineteen()));// ****
			}
			if (arrays.getNumberModulesACCircuitTwenty() != null) {
				form3Line.setField("20MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitTwenty()));// ****
			}
			if (arrays.getNumberModulesACCircuitTwentyOne() != null) {
				form3Line.setField("21MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitTwentyOne()));// ****
			}
			if (arrays.getNumberModulesACCircuitTwentyTwo() != null) {
				form3Line.setField("22MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitTwentyTwo()));// ****
			}
			if (arrays.getNumberModulesACCircuitTwentyThree() != null) {
				form3Line.setField("23MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitTwentyThree()));// ****
			}
			if (arrays.getNumberModulesACCircuitTwentyFour() != null) {
				form3Line.setField("24MOD-QTY-INV",
						String.valueOf(arrays.getNumberModulesACCircuitTwentyFour()));// ****
			}
			double iacmax = 0;
			if (checkValue.Equals(arrays.getDeviceToIncorporate(), "Micro Inverter")) {
				String iacmax1 = microInverterInfo.getIacmax();
				if (iacmax1 != null) {
					iacmax = (Double.parseDouble(iacmax1.contains(",") ? iacmax1.replace(",", ".") : iacmax1)) * 1.25;
				}
			} else {
				String iacmax1 = moduleInfo.getIacmax();
				if (iacmax1 != null) {
					iacmax = (Double.parseDouble(iacmax1.contains(",") ? iacmax1.replace(",", ".") : iacmax1)) * 1.25;
				}
			}
			if (checkValue.NotEquals(arrays.getNumberModulesACCircuitSix(), "")) {
				double ocpdRatingSix = iacmax
						* Integer.parseInt(arrays.getNumberModulesACCircuitSix());
				form3Line.setField("SLC-Backfeed6", ocpdRatingSix + "A");
			}
			if (checkValue.NotEquals(arrays.getNumberModulesACCircuitSeven(), "")) {
				double ocpdRatingSeven = iacmax
						* Integer.parseInt(arrays.getNumberModulesACCircuitSeven());
				form3Line.setField("SLC-Backfeed7", ocpdRatingSeven + "A");
			}
			if (checkValue.NotEquals(arrays.getNumberModulesACCircuitEight(), "")) {
				double ocpdRatingEight = iacmax
						* Integer.parseInt(arrays.getNumberModulesACCircuitEight());
				form3Line.setField("SLC-Backfeed8", ocpdRatingEight + "A");
			}
			if (checkValue.NotEquals(arrays.getNumberModulesACCircuitNine(), "")) {
				double ocpdRatingNine = iacmax
						* Integer.parseInt(arrays.getNumberModulesACCircuitNine());
				form3Line.setField("SLC-Backfeed9", ocpdRatingNine + "A");
			}
			if (checkValue.NotEquals(arrays.getNumberModulesACCircuitTen(), "")) {
				double ocpdRatingTen = iacmax
						* Integer.parseInt(arrays.getNumberModulesACCircuitTen());
				form3Line.setField("SLC-Backfeed10", ocpdRatingTen + "A");
			}
			if (checkValue.NotEquals(arrays.getNumberModulesACCircuitEleven(), "")) {
				double ocpdRatingEleven = iacmax
						* Integer.parseInt(arrays.getNumberModulesACCircuitEleven());
				form3Line.setField("SLC-Backfeed11", ocpdRatingEleven + "A");
			}
			if (checkValue.NotEquals(arrays.getNumberModulesACCircuitTweleve(), "")) {
				double ocpdRatingTwelve = iacmax
						* Integer.parseInt(arrays.getNumberModulesACCircuitTweleve());
				form3Line.setField("SLC-Backfeed12", ocpdRatingTwelve + "A");
			}

			String serviceVoltage = getServiceVoltage(permitHomeSite);

			if (acDisconnect != null) {
				form3Line.setField("ACD-Nameplate-Rate", acDisconnect.getRatedCurrent() + "W" + serviceVoltage);
			}
			if (checkValue.Equals(permitProjectSiteInfo.getUseDisconectSwith(), "Yes") && secondacDisconnect != null
					&& secondacDisconnect.getId() != null) {
				form3Line.setField("2ACD-Nameplate-Rate", secondacDisconnect.getRatedCurrent() + "W" + serviceVoltage);
			}
//		SB 19/05/2020 CR-1580 V15c
			if (acDisconnect != null) {
				form3Line.setField("ACDF-Nameplate-Rate",
						"RATING " + acDisconnect.getRatedCurrent() + "A " + serviceVoltage);
			}
			String fuseRatingMap = "N/A";
			if (microInverterInfo != null && checkValue.NotEquals(microInverterInfo.getIacmax(), "")
					&& checkValue.isNumeric(microInverterInfo.getIacmax())) {
				float inputCurrent = (float) (Float.parseFloat(microInverterInfo.getIacmax()) * modulePerMicroInverter);
				fuseRatingMap = fuseRatingMapping(acDisconnect, inputCurrent);
			}

			form3Line.setField("ACDF-Fuse-Rate", fuseRatingMap);
			if (checkValue.NotEquals(permitProjectSiteInfo.getSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed1", permitProjectSiteInfo.getSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed1", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSecondSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed2", permitProjectSiteInfo.getSecondSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed2", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getThirdSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed3", permitProjectSiteInfo.getThirdSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed3", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getFourthSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed4", permitProjectSiteInfo.getFourthSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed4", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getFifthSolarInterconnection(), "")) {
				form3Line.setField("SLC-Backfeed5", permitProjectSiteInfo.getFifthSolarInterconnection() + "A");
			} else {
				form3Line.setField("SLC-Backfeed5", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSolarInterconnection(), "")) {
				form3Line.setField("SUB-Backfeed1", permitProjectSiteInfo.getSolarInterconnection() + "A");
			} else {
				form3Line.setField("SUB-Backfeed1", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSecondSolarInterconnection(), "")) {
				form3Line.setField("SUB-Backfeed2", permitProjectSiteInfo.getSecondSolarInterconnection() + "A");
			} else {
				form3Line.setField("SUB-Backfeed2", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getThirdSolarInterconnection(), "")) {
				form3Line.setField("SUB-Backfeed3", permitProjectSiteInfo.getThirdSolarInterconnection() + "A");
			} else {
				form3Line.setField("SUB-Backfeed3", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getFourthSolarInterconnection(), "")) {
				form3Line.setField("SUB-Backfeed4", permitProjectSiteInfo.getFourthSolarInterconnection() + "A");
			} else {
				form3Line.setField("SUB-Backfeed4", "");
			}
			if (slcAcCombiner != null) {
				form3Line.setField("SLC-BUS-Rate", slcAcCombiner.getRatedCurrent() + "A");
			} else {
				form3Line.setField("SLC-BUS-Rate", "");
			}
			form3Line.setField("SLC-Main-C/B-Rate", "");
			if (checkValue.NotEquals(permitProjectSiteInfo.getSubPanelBusRating(), "")) {
				form3Line.setField("SUB-BUS-Rate", "RATTING" + permitProjectSiteInfo.getSubPanelBusRating() + "A");
			} else {
				form3Line.setField("SUB-BUS-Rate", "");

			}

			if (checkValue.NotEquals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "") && checkValue
					.NotEquals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "Lug Only")) {
				form3Line.setField("SUB-Main-C/B-Rate",
						"RATTING" + permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating() + "A");

			} else {
				if (permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating() != null
						&& checkValue.NotEquals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "")
						&& checkValue.Equals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(),
								"Lug Only")) {
					form3Line.setField("SUB-Main-C/B-Rate", "MLO");
				} else {
					form3Line.setField("SUB-Main-C/B-Rate", "");
				}
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSolarInterconnection(), "")) {
				form3Line.setField("MSP-Backfeed1", permitProjectSiteInfo.getSolarInterconnection() + "A");
			} else {
				form3Line.setField("MSP-Backfeed1", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getSecondSolarInterconnection(), "")) {
				form3Line.setField("MSP-Backfeed2", permitProjectSiteInfo.getSecondSolarInterconnection() + "A");
			} else {
				form3Line.setField("MSP-Backfeed2", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getThirdSolarInterconnection(), "")) {
				form3Line.setField("MSP-Backfeed3", permitProjectSiteInfo.getThirdSolarInterconnection() + "A");
			} else {
				form3Line.setField("MSP-Backfeed3", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getFourthSolarInterconnection(), "")) {
				form3Line.setField("MSP-Backfeed4", permitProjectSiteInfo.getFourthSolarInterconnection() + "A");
			} else {
				form3Line.setField("MSP-Backfeed4", "");
			}
			if (checkValue.NotEquals(permitProjectSiteInfo.getPanelBusRating(), "")) {
				if (checkValue.Equals(permitProjectSiteInfo.getPanelBusRating(), "Other")) {
					form3Line.setField("MSP-BUS-Rate", permitProjectSiteInfo.getPanelBusRatingOther() + "A");
				} else {
					form3Line.setField("MSP-BUS-Rate", permitProjectSiteInfo.getPanelBusRating() + "A");
				}
			}
			if (permitProjectSiteInfo.getPanelMainBreakerRating() != null
					&& checkValue.NotEquals(permitProjectSiteInfo.getPanelMainBreakerRating(), "")) {
				if (checkValue.NotEquals(permitProjectSiteInfo.getPanelMainBreakerRating(), "Other")) {
					form3Line.setField("MSP-Main-C/B-Rate", permitProjectSiteInfo.getPanelMainBreakerRating() + "A");
				} else {
					form3Line.setField("MSP-Main-C/B-Rate",
							permitProjectSiteInfo.getPanelMainBreakerRatingOther() + "A");
				}
			}

			stamper3Line.setFormFlattening(true);
			stamper3Line.close();
			reader3Line.close();
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public String getServiceVoltage(PermitHomeSiteInfoEntity permitHomeSite) {
		try {
			if (permitHomeSite != null) {
				if (checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)) {
					return "240V";
				} else if (permitHomeSite.getServiceVoltage() != null) {
					if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "120/208V")) {
						return "120/208V (3Î¦)";
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "277/480V")) {
						return "277/480V (3Î¦)";
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "240V")) {
						return "240V (3Î¦)";
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "400V")) {
						return "400V (3Î¦)";
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "480V")) {
						return "480V (3Î¦)";
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "600V")) {
						return "600V (3Î¦)";
					}
				}
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}
	}

	public float getInputCurrent(List<Inverters> inverters, Integer firsttInverterQty, Integer secondtInverterQty) {
		float inputCurrent = 0f;
		if (inverters != null && !inverters.isEmpty() && checkValue.NotEquals(inverters.get(0).getIacmax(), "")
				&& checkValue.isNumeric(inverters.get(0).getIacmax()) && firsttInverterQty != null) {
			inputCurrent = firsttInverterQty * Float.parseFloat(inverters.get(0).getIacmax());
		}
		if (inverters != null && inverters.size() > 1 && inverters.get(1) != null
				&& checkValue.NotEquals(inverters.get(1).getIacmax(), "")
				&& checkValue.isNumeric(inverters.get(1).getIacmax()) && secondtInverterQty != null) {
			inputCurrent = inputCurrent + (secondtInverterQty * Float.parseFloat(inverters.get(1).getIacmax()));

			if (inverters.size() > 2 && inverters.get(2) != null
					&& checkValue.NotEquals(inverters.get(2).getIacmax(), "")
					&& checkValue.isNumeric(inverters.get(2).getIacmax())) {
				inputCurrent = inputCurrent + Float.parseFloat(inverters.get(2).getIacmax());

				if (inverters.size() > 3 && inverters.get(3) != null
						&& checkValue.NotEquals(inverters.get(3).getIacmax(), "")
						&& checkValue.isNumeric(inverters.get(3).getIacmax())) {
					inputCurrent = inputCurrent + Float.parseFloat(inverters.get(3).getIacmax());

					if (inverters.size() > 4 && inverters.get(4) != null
							&& checkValue.NotEquals(inverters.get(4).getIacmax(), "")
							&& checkValue.isNumeric(inverters.get(4).getIacmax())) {
						inputCurrent = inputCurrent + Float.parseFloat(inverters.get(4).getIacmax());
					}
				}
			}

		}
		return inputCurrent;
	}

	public String fuseRatingMapping(ACDisconnect acDisconnect, float inputCurrent) {
		String fuseRate = "";
		if (acDisconnect != null && acDisconnect.getDisconnectDeviceType() != null
				&& (checkValue.Equals(acDisconnect.getDisconnectDeviceType().toLowerCase(), "fusible disconnect")
						|| checkValue.Equals(acDisconnect.getDisconnectDeviceType().toLowerCase(), "fusible"))) {
			float fuseRating = (float) (inputCurrent * 1.25);
			float numbers[] = new float[] { 10, 15, 20, 30, 40, 50, 60, 70, 75, 80, 90, 100, 110, 125, 150, 175, 200,
					225, 250, 300, 350, 400, 450, 500, 600, 800, 1000, 1200, 1400, 1600, 1800, 2000 };
			if (fuseRating > 0) {
				float theNumber = 0;
				int i = 0;
				while (numbers[i] < fuseRating && i < numbers.length - 1) {
					i++;
				}
				theNumber = numbers[i];
				fuseRate = theNumber + "";
			} else if (inputCurrent > 0) {
				fuseRate = "N/A";
			} else
				fuseRate = "Iacmax Update Req";
		} else {
			fuseRate = "N/A";
		}
		return fuseRate;
	}
}
