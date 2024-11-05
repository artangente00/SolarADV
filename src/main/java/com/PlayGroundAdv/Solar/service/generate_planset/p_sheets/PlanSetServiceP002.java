package com.PlayGroundAdv.Solar.service.generate_planset.p_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDesignCriteriaModel;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetInverterData;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetWeatherData;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ElectricUtilityNumber;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class PlanSetServiceP002 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final InverterRepository inverterRepo;
	final GetWeatherData getWeatherData;
	final GetPVModuleData getPVModuleData;
	final GetInverterData getInverterData;
	final PlansetLogo_SignatureMappingService logoSignatureMapping;
	final TechnicalProblemMsg technicalProblemMsg;
	final ElectricUtilityNumber electricUtilityNumber;
	final ACCombinerDisconnectMapping accdMapping;

	public PlanSetServiceP002(CheckValueTypesService checkValue, GetPDFReaderService getPDFReaderService,
			InverterRepository inverterRepo, GetWeatherData getWeatherData, GetPVModuleData getPVModuleData,
			GetInverterData getInverterData, PlansetLogo_SignatureMappingService logoSignatureMapping,
			TechnicalProblemMsg technicalProblemMsg, ElectricUtilityNumber electricUtilityNumber,
			ACCombinerDisconnectMapping accdMapping) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.inverterRepo = inverterRepo;
		this.getWeatherData = getWeatherData;
		this.getPVModuleData = getPVModuleData;
		this.getInverterData = getInverterData;
		this.logoSignatureMapping = logoSignatureMapping;
		this.technicalProblemMsg = technicalProblemMsg;
		this.electricUtilityNumber = electricUtilityNumber;
		this.accdMapping = accdMapping;
	}

	static final String PV_SYSTEM = "-PV-SYSTEM-DISCONNECT-";
	static final String STRING_INV = "-STRING-INVERTER-";
	static final String AC_DISCO = "-AC-DISCO-";
	static final String SUB_PANEL = "-AC-SUB-PANEL-";

	public Float permitWeatherInfo(String extremeMinimum, Cmodulev2 moduleInfo) {
		try {
			if (checkValue.NotEquals(extremeMinimum, "")) {
				int correctedTemperature = Integer.valueOf(extremeMinimum) - 25;
				if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getGammaR(), "")) {
					Float tempCoefficient = Float.parseFloat(moduleInfo.getGammaR().replace(',', '.'));
					Float calculation = ((correctedTemperature * tempCoefficient) / 100) + 1;
					String vOcRef = getPVModuleData.getVocRef(moduleInfo);
					if (checkValue.NotEquals(vOcRef, "")) {
						return Float.parseFloat(String.valueOf(new DecimalFormat("##.##").format(calculation)))
								* Float.parseFloat(vOcRef);
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return 0f;
	}

	public void mapInverterInfo(AcroFields form, List<Inverters> inverters, Float systemWatt1, Float maxSystemVoltage,
			Cmodulev2 moduleInfo, int sheetIndex, Inverters secondInverterInfo, Inverters thirdInverterInfo,
			Float systemWatt2, Float systemWatt3, PlansetUtils plansetUtils) {
		try {
			String vMpRef = getPVModuleData.getvMpRef(moduleInfo);
			String iMpRef = getPVModuleData.getiMpRef(moduleInfo);
			String iScRef = getPVModuleData.getiScRef(moduleInfo);
			if (inverters != null && !inverters.isEmpty() && inverters.get(0) != null
					&& checkValue.NotEquals(inverters.get(0).getMake(), "")
					&& inverters.get(0).getMake().contains("SolarEdge")
					&& checkValue.NotEquals(inverters.get(0).getMpptHigh(), "0")
					&& checkValue.NotEquals(inverters.get(0).getMpptHigh(), "")
					&& checkValue.NotEquals(inverters.get(0).getMpptLow(), "0")
					&& checkValue.NotEquals(inverters.get(0).getMpptLow(), "")) {

				float mpptLow = Float.parseFloat(inverters.get(0).getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-VOLTAGE", inverters.get(0).getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-VOLTAGE1", inverters.get(0).getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "1-SHORT-CIRCUIT-CURRENT",
						String.valueOf(new DecimalFormat("##.0").format(plansetUtils.getNumberOfStringsOne() * 15)));
				form.setField(sheetIndex + PV_SYSTEM + "1-SHORT-CIRCUIT-CURRENT1",
						String.valueOf(new DecimalFormat("##.0").format(plansetUtils.getNumberOfStringsOne() * 15)));

				form.setField(sheetIndex + PV_SYSTEM + "1-MAXIMUM-SYSTEM-VOLTAGE", inverters.get(0).getMpptHigh());
				form.setField(sheetIndex + PV_SYSTEM + "1-MAXIMUM-SYSTEM-VOLTAGE1", inverters.get(0).getMpptHigh());

				form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-CURRENT",
						String.valueOf(new DecimalFormat("##.#").format(systemWatt1 / mpptLow)));
				form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-CURRENT1",
						String.valueOf(new DecimalFormat("##.#").format(systemWatt1 / mpptLow)));

			} else {

				if (checkValue.NotEquals(iMpRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-CURRENT",
							String.valueOf(new DecimalFormat("##.#")
									.format((Float.parseFloat(iMpRef) * plansetUtils.getMaxNumberOfStrings()))));
					form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-CURRENT1",
							String.valueOf(new DecimalFormat("##.#")
									.format((Float.parseFloat(iMpRef) * plansetUtils.getMaxNumberOfStrings()))));
				}

				if (checkValue.NotEquals(vMpRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-VOLTAGE", String.valueOf(
							new DecimalFormat("##").format(Float.parseFloat(vMpRef) * plansetUtils.getMaxNumModule())));
					form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-VOLTAGE1", String.valueOf(
							new DecimalFormat("##").format(Float.parseFloat(vMpRef) * plansetUtils.getMaxNumModule())));
				} else {
					form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-VOLTAGE", "");
					form.setField(sheetIndex + PV_SYSTEM + "1-RATED-MPP-VOLTAGE1", "");

				}

				if (checkValue.NotEquals(iScRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "1-SHORT-CIRCUIT-CURRENT",
							String.valueOf(new DecimalFormat("##.0")
									.format(Float.parseFloat(iScRef) * plansetUtils.getNumberOfStringsOne() * 1.25)));
					form.setField(sheetIndex + PV_SYSTEM + "1-SHORT-CIRCUIT-CURRENT1",
							String.valueOf(new DecimalFormat("##.0")
									.format(Float.parseFloat(iScRef) * plansetUtils.getNumberOfStringsOne() * 1.25)));
				} else {
					form.setField(sheetIndex + PV_SYSTEM + "1-SHORT-CIRCUIT-CURRENT", "");
					form.setField(sheetIndex + PV_SYSTEM + "1-SHORT-CIRCUIT-CURRENT1", "");

				}

				System.out.println(maxSystemVoltage);				
				if (checkValue.NotEquals(maxSystemVoltage, 0f)
						&& String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage)).contains(".")) {
					String number2 = String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage))
							.split("\\.")[0];
					String number3 = String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage))
							.split("\\.")[1];
					if (String.valueOf(Integer.parseInt(number2)).length() == 3 && Integer.parseInt(number3) > 5) {
						Integer number4 = Integer.parseInt(number2) + 1;
						form.setField(sheetIndex + PV_SYSTEM + "1-MAXIMUM-SYSTEM-VOLTAGE", number4 + "");
						form.setField(sheetIndex + PV_SYSTEM + "1-MAXIMUM-SYSTEM-VOLTAGE1", number4 + "");
					} else {
						Integer number4 = Integer.parseInt(number2);
						form.setField(sheetIndex + PV_SYSTEM + "1-MAXIMUM-SYSTEM-VOLTAGE", number4 + "");
						form.setField(sheetIndex + PV_SYSTEM + "1-MAXIMUM-SYSTEM-VOLTAGE1", number4 + "");
					}
				}
			}

			if (secondInverterInfo != null && secondInverterInfo.getMake().contains("SolarEdge")
					&& checkValue.NotEquals(secondInverterInfo.getMpptHigh(), "0")
					&& checkValue.NotEquals(secondInverterInfo.getMpptHigh(), "")
					&& checkValue.NotEquals(secondInverterInfo.getMpptLow(), "0")
					&& checkValue.NotEquals(secondInverterInfo.getMpptLow(), "")) {

				float mpptLow2 = Float.parseFloat(secondInverterInfo.getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-CURRENT",
						String.valueOf(new DecimalFormat("##.#").format(systemWatt2 / mpptLow2)));
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-CURRENT1",
						String.valueOf(new DecimalFormat("##.#").format(systemWatt2 / mpptLow2)));
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-VOLTAGE", secondInverterInfo.getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-VOLTAGE1", secondInverterInfo.getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "2-SHORT-CIRCUIT-CURRENT",
						String.valueOf(new DecimalFormat("##.0").format(plansetUtils.getNumberOfStringsTwo() * 15)));
				form.setField(sheetIndex + PV_SYSTEM + "2-SHORT-CIRCUIT-CURRENT1",
						String.valueOf(new DecimalFormat("##.0").format(plansetUtils.getNumberOfStringsTwo() * 15)));
				form.setField(sheetIndex + PV_SYSTEM + "2-MAXIMUM-SYSTEM-VOLTAGE", secondInverterInfo.getMpptHigh());
				form.setField(sheetIndex + PV_SYSTEM + "2-MAXIMUM-SYSTEM-VOLTAGE1", secondInverterInfo.getMpptHigh());

			} else if (secondInverterInfo != null) {
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-CURRENT", String.valueOf(new DecimalFormat("##.#")
						.format((Float.parseFloat(iMpRef) * plansetUtils.getMaxNumberOfStrings()))));
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-CURRENT1", String.valueOf(new DecimalFormat("##.#")
						.format((Float.parseFloat(iMpRef) * plansetUtils.getMaxNumberOfStrings()))));

				if (checkValue.NotEquals(iMpRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-CURRENT",
							String.valueOf(new DecimalFormat("##.#")
									.format((Float.parseFloat(iMpRef) * plansetUtils.getMaxNumberOfStrings()))));
					form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-CURRENT1",
							String.valueOf(new DecimalFormat("##.#")
									.format((Float.parseFloat(iMpRef) * plansetUtils.getMaxNumberOfStrings()))));
				}

				if (checkValue.NotEquals(vMpRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-VOLTAGE", String.valueOf(
							new DecimalFormat("##").format(Float.parseFloat(vMpRef) * plansetUtils.getMaxNumModule())));
					form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-VOLTAGE1", String.valueOf(
							new DecimalFormat("##").format(Float.parseFloat(vMpRef) * plansetUtils.getMaxNumModule())));

				} else {
					form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-VOLTAGE", "");
					form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-VOLTAGE1", "");

				}
				if (checkValue.NotEquals(iScRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "2-SHORT-CIRCUIT-CURRENT",
							String.valueOf(new DecimalFormat("##.0")
									.format(Float.parseFloat(iScRef) * plansetUtils.getNumberOfStringsTwo() * 1.25)));
					form.setField(sheetIndex + PV_SYSTEM + "2-SHORT-CIRCUIT-CURRENT1",
							String.valueOf(new DecimalFormat("##.0")
									.format(Float.parseFloat(iScRef) * plansetUtils.getNumberOfStringsTwo() * 1.25)));

				} else {
					form.setField(sheetIndex + PV_SYSTEM + "2-SHORT-CIRCUIT-CURRENT", "");
					form.setField(sheetIndex + PV_SYSTEM + "2-SHORT-CIRCUIT-CURRENT1", "");

				}
				if (checkValue.NotEquals(maxSystemVoltage, 0f)
						&& String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage)).contains(".")) {
					String number2 = String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage))
							.split("\\.")[0];
					String number3 = String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage))
							.split("\\.")[1];
					if (String.valueOf(Integer.parseInt(number2)).length() == 3 && Integer.parseInt(number3) > 5) {
						Integer number4 = Integer.parseInt(number2) + 1;
						form.setField(sheetIndex + PV_SYSTEM + "2-MAXIMUM-SYSTEM-VOLTAGE", number4 + "");
						form.setField(sheetIndex + PV_SYSTEM + "2-MAXIMUM-SYSTEM-VOLTAGE1", number4 + "");
					} else {
						Integer number4 = Integer.parseInt(number2);
						form.setField(sheetIndex + PV_SYSTEM + "2-MAXIMUM-SYSTEM-VOLTAGE", number4 + "");
						form.setField(sheetIndex + PV_SYSTEM + "2-MAXIMUM-SYSTEM-VOLTAGE1", number4 + "");
					}
				}

			} else {
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-CURRENT", "");
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-CURRENT1", "");
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-VOLTAGE", "");
				form.setField(sheetIndex + PV_SYSTEM + "2-RATED-MPP-VOLTAGE1", "");
				form.setField(sheetIndex + PV_SYSTEM + "2-SHORT-CIRCUIT-CURRENT", "");
				form.setField(sheetIndex + PV_SYSTEM + "2-SHORT-CIRCUIT-CURRENT1", "");
				form.setField(sheetIndex + PV_SYSTEM + "2-MAXIMUM-SYSTEM-VOLTAGE", "");
				form.setField(sheetIndex + PV_SYSTEM + "2-MAXIMUM-SYSTEM-VOLTAGE1", "");
			}
			if (thirdInverterInfo != null && thirdInverterInfo.getMake().contains("SolarEdge")
					&& checkValue.NotEquals(thirdInverterInfo.getMpptHigh(), "")
					&& checkValue.NotEquals(thirdInverterInfo.getMpptHigh(), "0")
					&& checkValue.NotEquals(thirdInverterInfo.getMpptLow(), "")
					&& checkValue.NotEquals(thirdInverterInfo.getMpptLow(), "0")) {

				float mpptLow3 = Float.parseFloat(thirdInverterInfo.getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-CURRENT",
						String.valueOf(new DecimalFormat("##.#").format(systemWatt3 / mpptLow3)));
				form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-CURRENT1",
						String.valueOf(new DecimalFormat("##.#").format(systemWatt3 / mpptLow3)));
				form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE", thirdInverterInfo.getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE1", thirdInverterInfo.getMpptLow());
				form.setField(sheetIndex + PV_SYSTEM + "3-SHORT-CIRCUIT-CURRENT",
						String.valueOf(new DecimalFormat("##.0").format(plansetUtils.getNumberOfStringsThree() * 15)));
				form.setField(sheetIndex + PV_SYSTEM + "3-SHORT-CIRCUIT-CURRENT1",
						String.valueOf(new DecimalFormat("##.0").format(plansetUtils.getNumberOfStringsThree() * 15)));
				form.setField(sheetIndex + PV_SYSTEM + "3-MAXIMUM-SYSTEM-VOLTAGE", thirdInverterInfo.getMpptHigh());
				form.setField(sheetIndex + PV_SYSTEM + "3-MAXIMUM-SYSTEM-VOLTAGE1", thirdInverterInfo.getMpptHigh());

			} else if (thirdInverterInfo != null) {

				if (checkValue.NotEquals(iMpRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-CURRENT",
							String.valueOf(new DecimalFormat("##.#")
									.format((Float.parseFloat(iMpRef) * plansetUtils.getMaxNumberOfStrings()))));
					form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-CURRENT1",
							String.valueOf(new DecimalFormat("##.#")
									.format((Float.parseFloat(iMpRef) * plansetUtils.getMaxNumberOfStrings()))));
				}

				if (checkValue.NotEquals(vMpRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE", String.valueOf(
							new DecimalFormat("##").format(Float.parseFloat(vMpRef) * plansetUtils.getMaxNumModule())));
					form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE1", String.valueOf(
							new DecimalFormat("##").format(Float.parseFloat(vMpRef) * plansetUtils.getMaxNumModule())));

				} else {
					form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE", "");
					form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE1", "");

				}
				if (checkValue.NotEquals(iScRef, "")) {
					form.setField(sheetIndex + PV_SYSTEM + "3-SHORT-CIRCUIT-CURRENT",
							String.valueOf(new DecimalFormat("##.0")
									.format(Float.parseFloat(iScRef) * plansetUtils.getNumberOfStringsThree() * 1.25)));
					form.setField(sheetIndex + PV_SYSTEM + "3-SHORT-CIRCUIT-CURRENT1",
							String.valueOf(new DecimalFormat("##.0")
									.format(Float.parseFloat(iScRef) * plansetUtils.getNumberOfStringsThree() * 1.25)));

				} else {
					form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE", "");
					form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE1", "");

				}
				if (checkValue.NotEquals(maxSystemVoltage, 0f)
						&& String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage)).contains(".")) {
					String number2 = String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage))
							.split("\\.")[0];
					String number3 = String.valueOf(new DecimalFormat("##.##").format(maxSystemVoltage))
							.split("\\.")[1];
					if (String.valueOf(Integer.parseInt(number2)).length() == 3 && Integer.parseInt(number3) > 5) {
						Integer number4 = Integer.parseInt(number2) + 1;
						form.setField(sheetIndex + PV_SYSTEM + "3-MAXIMUM-SYSTEM-VOLTAGE", number4 + "");
						form.setField(sheetIndex + PV_SYSTEM + "3-MAXIMUM-SYSTEM-VOLTAGE1", number4 + "");
					} else {
						Integer number4 = Integer.parseInt(number2);
						form.setField(sheetIndex + PV_SYSTEM + "3-MAXIMUM-SYSTEM-VOLTAGE", number4 + "");
						form.setField(sheetIndex + PV_SYSTEM + "3-MAXIMUM-SYSTEM-VOLTAGE1", number4 + "");
					}
				}
			} else {
				form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-CURRENT", "");
				form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-CURRENT1", "");
				form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE", "");
				form.setField(sheetIndex + PV_SYSTEM + "3-RATED-MPP-VOLTAGE1", "");
				form.setField(sheetIndex + PV_SYSTEM + "3-SHORT-CIRCUIT-CURRENT", "");
				form.setField(sheetIndex + PV_SYSTEM + "3-SHORT-CIRCUIT-CURRENT1", "");
				form.setField(sheetIndex + PV_SYSTEM + "3-MAXIMUM-SYSTEM-VOLTAGE", "");
				form.setField(sheetIndex + PV_SYSTEM + "3-MAXIMUM-SYSTEM-VOLTAGE1", "");
			}
		} catch (IOException | DocumentException | NumberFormatException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapiacmax(ACDisconnect acDisconnect, AcroFields form, Inverters secondInverterInfo,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, Inverters inverterInfo, String sumIacMax,
			int sheetIndex) {
		try {
			String iacMax = getInverterData.getIacmaxString(inverterInfo);
			String iacMax2 = getInverterData.getIacmaxString(secondInverterInfo);
			if (checkValue.NotEquals(sumIacMax, "")) {
				if (acDisconnect != null) {
					form.setField(sheetIndex + STRING_INV + "1-MIN-OCPD-AMPS", sumIacMax);
					form.setField(sheetIndex + STRING_INV + "1-MIN-OCPD-AMPS1", sumIacMax);
					form.setField(sheetIndex + STRING_INV + "1-MIN-OCPD-AMPS2", sumIacMax);
				} else {
					if (checkValue.NotEquals(sumIacMax, "")) {
						// 02-03-2020: Updates p002 new logic
						form.setField(sheetIndex + STRING_INV + "1-MIN-OCPD-AMPS2", sumIacMax);
					} else {
						form.setField(sheetIndex + STRING_INV + "1-MIN-OCPD-AMPS2", "");
					}
					form.setField(sheetIndex + STRING_INV + "1-MIN-OCPD-AMPS", "");
					form.setField(sheetIndex + STRING_INV + "1-MIN-OCPD-AMPS1", "");
				}
				if (permitProjectSiteInfo != null
						&& checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Sub Panel")) {

					form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT", iacMax);
					form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT1", iacMax);
					form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT2", iacMax);
				}
			}
			if (checkValue.NotEquals(iacMax2, "")) {
				if (secondInverterInfo != null) {
					form.setField(sheetIndex + AC_DISCO + "1-RATED-CURRENT",
							String.valueOf(new DecimalFormat("##.0").format(Float.parseFloat(iacMax2))));
					form.setField(sheetIndex + AC_DISCO + "1-RATED-CURRENT1",
							String.valueOf(new DecimalFormat("##.0").format(Float.parseFloat(iacMax2))));

				} else {
					form.setField(sheetIndex + AC_DISCO + "1-RATED-CURRENT", "");
					form.setField(sheetIndex + AC_DISCO + "1-RATED-CURRENT1", "");
				}
			} else {
				form.setField(sheetIndex + AC_DISCO + "1-RATED-CURRENT", "");
				form.setField(sheetIndex + AC_DISCO + "1-RATED-CURRENT1", "");
			}
			if (checkValue.NotEquals(iacMax, "")) {
				if (inverterInfo != null) {
					form.setField(sheetIndex + STRING_INV + "2-MIN-OCPD-AMPS",
							String.valueOf(new DecimalFormat("##.0").format(Float.parseFloat(iacMax))));
					form.setField(sheetIndex + STRING_INV + "2-MIN-OCPD-AMPS1",
							String.valueOf(new DecimalFormat("##.0").format(Float.parseFloat(iacMax))));
				} else {
					form.setField(sheetIndex + STRING_INV + "2-MIN-OCPD-AMPS", "");
					form.setField(sheetIndex + STRING_INV + "2-MIN-OCPD-AMPS1", "");
				}
			}

			// A.B 03-18-2022 CR-PM-766-MOD-001
			if (checkValue.contains(permitProjectSiteInfo.getCombiningACCircuits(),
					"A Proposed AC Combiner Panel (Solar Load Center)")) {
				form.setField(sheetIndex + "-SLC/SUB PANEL", "SOLAR LOAD CENTER");
				form.setField(sheetIndex + "-SLC-RATED-AC-OUTPUT-CURRENT", sumIacMax);
				form.setField(sheetIndex + "-SLC-RATED-AC-OUTPUT-CURRENT#1", sumIacMax);
				form.setField(sheetIndex + "-SLC-NORMAL-OPERATING-VOLTAGE", inverterInfo.getVac());
				form.setField(sheetIndex + "-SLC-NORMAL-OPERATING-VOLTAGE#1", inverterInfo.getVac());
			}
		} catch (IOException | DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapOperatingVoltage(ACDisconnect acDisconnect, AcroFields form, Inverters inverterInfo,
			Inverters secondInverterInfo, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			List<Inverters> inverters) {
		try {
			if (acDisconnect != null && acDisconnect.getRatedOperationalVoltage() != null) {
				if (acDisconnect.getRatedOperationalVoltage().contains("/")) {
					// 02-20-2019: M.A : Many Fields on P-002 Not mapping
					if (acDisconnect.getRatedOperationalVoltage().split("/")[1] != null
							&& acDisconnect.getRatedOperationalVoltage().split("/")[1].contains("V")) {
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT",
								acDisconnect.getRatedOperationalVoltage().split("/")[1].split("V")[0]);
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT1",
								acDisconnect.getRatedOperationalVoltage().split("/")[1].split("V")[0]);
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT2",
								acDisconnect.getRatedOperationalVoltage().split("/")[1].split("V")[0]);
					} else {

						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT",
								acDisconnect.getRatedOperationalVoltage().split("/")[1]);
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT1",
								acDisconnect.getRatedOperationalVoltage().split("/")[1]);
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT2",
								acDisconnect.getRatedOperationalVoltage().split("/")[1]);
					}
				} else {
					// 02-20-2019: M.A : Many Fields on P-002 Not mapping
					if (acDisconnect.getRatedOperationalVoltage().contains("V")) {
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT",
								acDisconnect.getRatedOperationalVoltage().split("V")[0]);
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT1",
								acDisconnect.getRatedOperationalVoltage().split("V")[0]);
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT2",
								acDisconnect.getRatedOperationalVoltage().split("V")[0]);
					} else {

						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT",
								acDisconnect.getRatedOperationalVoltage());
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT1",
								acDisconnect.getRatedOperationalVoltage());
						form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT2",
								acDisconnect.getRatedOperationalVoltage());
					}
				}
			} else {

				form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT", "");
				form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT1", "");
				if (inverterInfo != null && inverterInfo.getVac() != null) {
					form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT2", inverterInfo.getVac());
				} else {
					form.setField(sheetIndex + STRING_INV + "1-NOMINAL-VOLTAGE-OUTPUT2", "");
				}
			}
			if (inverterInfo != null && inverterInfo.getVac() != null) {
				form.setField(sheetIndex + STRING_INV + "2-NOMINAL-VOLTAGE-OUTPUT", inverterInfo.getVac());
				form.setField(sheetIndex + STRING_INV + "2-NOMINAL-VOLTAGE-OUTPUT1", inverterInfo.getVac());
			} else {
				form.setField(sheetIndex + STRING_INV + "2-NOMINAL-VOLTAGE-OUTPUT", "");
				form.setField(sheetIndex + STRING_INV + "2-NOMINAL-VOLTAGE-OUTPUT1", "");
			}
			if (secondInverterInfo != null && secondInverterInfo.getVac() != null) {
				form.setField(sheetIndex + AC_DISCO + "1-RATED-NOMINAL-VOLTAGE", secondInverterInfo.getVac());
				form.setField(sheetIndex + AC_DISCO + "1-RATED-NOMINAL-VOLTAGE1", secondInverterInfo.getVac());
			} else {
				form.setField(sheetIndex + AC_DISCO + "1-RATED-NOMINAL-VOLTAGE", "");
				form.setField(sheetIndex + AC_DISCO + "1-RATED-NOMINAL-VOLTAGE1", "");
			}

//			 A.B 08/31/2021 CR-PM-3862-003
			if (checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
					"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
					|| checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
							"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker")
					|| checkValue.Equals(permitProjectSiteInfo.getThepontOfTheC(), "Sub Panel")) {
				String sumIacMax = getInverterData.getSumIacMax(inverters);
				form.setField(sheetIndex + SUB_PANEL + "1-RATED-CURRENT", sumIacMax);
				form.setField(sheetIndex + SUB_PANEL + "1-RATED-CURRENT1", sumIacMax);
				form.setField(sheetIndex + SUB_PANEL + "1-NOMINAL-VOLTAGE-OUTPUT", inverterInfo.getVac());
				form.setField(sheetIndex + SUB_PANEL + "1-NOMINAL-VOLTAGE-OUTPUT1", inverterInfo.getVac());
			}
		} catch (IOException | DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void operatingVoltageMapping(AcroFields form, int sheetIndex,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, String ratedCurrent, String systemTechnology,
			Inverters microInverterInfo) {
		try {

			String microInverterVac = getInverterData.getVac(microInverterInfo);
//			 A.B 08/31/2021 CR-PM-3862-003
			if (checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
					"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
					|| checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
							"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker")
					|| checkValue.Equals(permitProjectSiteInfo.getThepontOfTheC(), "Sub Panel")) {

				form.setField(sheetIndex + SUB_PANEL + "1-BUSS-BAR-RATING", ratedCurrent);
				form.setField(sheetIndex + SUB_PANEL + "1-RATED-CURRENT", ratedCurrent);
				if (checkValue.Equals(systemTechnology, "Micro Inverter")) {
					form.setField(sheetIndex + SUB_PANEL + "1-RATED-NOMINAL-VOLTAGE", microInverterVac);
				} else {
					form.setField(sheetIndex + SUB_PANEL + "1-RATED-NOMINAL-VOLTAGE", "240");
				}
//				 A.B 08/31/2021 CR-PM-3862-003 Second Sub Panel
				if ((checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
						"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
						|| checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
								"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))
						&& checkValue.Equals(permitProjectSiteInfo.getThepontOfTheC(), "Sub Panel")) {

					form.setField(sheetIndex + SUB_PANEL + "2-BUSS-BAR-RATING", ratedCurrent);
					if (checkValue.Equals(systemTechnology, "Micro Inverter")) {
						form.setField(sheetIndex + SUB_PANEL + "2-RATED-NOMINAL-VOLTAGE", microInverterVac);
					} else {
						form.setField(sheetIndex + SUB_PANEL + "2-RATED-NOMINAL-VOLTAGE", "240");
					}

				} else {
					form.setField(sheetIndex + SUB_PANEL + "2-BUSS-BAR-RATING", "");
					form.setField(sheetIndex + SUB_PANEL + "2-RATED-NOMINAL-VOLTAGE", "");
				}
			} else {
				form.setField(sheetIndex + SUB_PANEL + "1-BUSS-BAR-RATING", "");
				form.setField(sheetIndex + SUB_PANEL + "1-RATED-CURRENT", "");
				form.setField(sheetIndex + SUB_PANEL + "1-RATED-NOMINAL-VOLTAGE", "");
			}

		} catch (IOException | DocumentException | NumberFormatException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public File buildingPDFP002(PermitHomeSiteInfoEntity permitHomeSite, PermitEntity permitEntity,
			PermitArrayEntityResultSecond permitArraysEntityResult, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Cmodulev2 moduleInfo, Inverters inverterInfo, Inverters secondInverterInfo, ACDisconnect acDisconnect,
			ACDisconnect additionalAcDisconnect, ACDisconnect secondacDisconnect, ACDisconnect acDisconnectThree,
			ACCombinerSLC acCombiner, ACCombinerSLC slcAcCombiner, PermtiWeatherEntityResult permtiWeather,
			Inverters microInverterInfo, List<Inverters> inverters, Integer firsttInverterQty,
			Integer secondtInverterQty, ElectricalUtilityEntity electricalCompany, PdfReader reader, int sheetIndex,
			String filePath, AHJDesignCriteriaModel designCriteria, PlansetUtils plansetUtils) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		File fileRe = null;

		Long idPermit = permitEntity.getId();
		Locale.setDefault(new Locale("en", "US"));

		String extremeMinimum = getWeatherData.getExtremeMinimum(permtiWeather, designCriteria);
		Float minSerieFuseRating = permitWeatherInfo(extremeMinimum, moduleInfo);
		Float sctRounded = checkValue.getFloatValue(getPVModuleData.getStcRounded(moduleInfo));
		Float systemWatt1 = plansetUtils.getNumModule1() * sctRounded;
		Float systemWatt2 = plansetUtils.getNumModule2() * sctRounded;
		Float systemWatt3 = plansetUtils.getNumModule3() * sctRounded;

		Float maxSystemVoltage = minSerieFuseRating * plansetUtils.getMaxNumModule();
		String systemTechnology = permitArraysEntityResult.getDeviceToIncorporate();
		Inverters thirdInverterInfo = null;
		if (permitArraysEntityResult.getThirdInverterModel() != null) {
			thirdInverterInfo = inverterRepo.findById(permitArraysEntityResult.getThirdInverterModel()).orElse(null);
		}

		if ((checkValue.Equals(systemTechnology, "Neither")
				|| checkValue.Equals(systemTechnology, "System Optimizer"))) {
			try {

				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-P002-STRING" + idPermit + "-" + sheetIndex + ".pdf");
				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-P002-STRING" + idPermit + "-"
							+ sheetIndex + ".pdf");
				}
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();

				// A.B remove sheet index if exist when the project was uploaded
				if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}

				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-P002-STRING.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-P002-STRING.pdf");

				Float sumIacMaxInt = getInverterData.getSumIacmax(inverters, firsttInverterQty, secondtInverterQty);
				String sumIacMax = String.valueOf(sumIacMaxInt);

				// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
				getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "P002");

				// A.B: Set PDF Font For Revision
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
						sheetIndex);
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
						sheetIndex);

				// A.B CR-3250 03-30 Logo & Signature Mapping
				logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper,
						filePath);

				mapInverterInfo(form, inverters, systemWatt1, maxSystemVoltage, moduleInfo, sheetIndex,
						secondInverterInfo, thirdInverterInfo, systemWatt2, systemWatt3, plansetUtils);

				///// ---------------- AC DISCONNECT, AC SUB-PANEL ---------------//////

				mapiacmax(acDisconnect, form, secondInverterInfo, permitProjectSiteInfo, inverterInfo, sumIacMax,
						sheetIndex);
				// ------------------Ac normal operating voltage-------------//

				mapOperatingVoltage(acDisconnect, form, inverterInfo, secondInverterInfo, sheetIndex,
						permitProjectSiteInfo, inverters);
				// ------------------------------------------------------//
				electricUtilityNumber.mapMeterNumber(form, sheetIndex, "P002", permitHomeSite, electricalCompany);

				stamper.close();
				reader.close();

			} catch (IOException | DocumentException e) {

				technicalProblemMsg.traiterException(e);

			}

		} else if ((checkValue.Equals(systemTechnology, "Micro Inverter")
				|| checkValue.Equals(systemTechnology, "AC Modules"))) {

			try {

				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-P002-MICRO" + idPermit + "-" + sheetIndex + ".pdf");
				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-P002-MICRO" + idPermit + "-" + sheetIndex
							+ ".pdf");
				}
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();

				// A.B remove sheet index if exist when the project was uploaded
				if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}

				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-P002-MICRO.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-P002-MICRO.pdf");

				// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
				getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "P002");

				// A.B: Set PDF Font For Revision
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
						sheetIndex);
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
						sheetIndex);

				// A.B CR-3250 03-30 Logo & Signature Mapping
				logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper,
						filePath);

				// ------------------Ac normal operating voltage-------------//

//				A.B 08/31/2021 CR-PM-3862-003
//				A.B 10/17/2022 REV-CR-PM-766-002
				String moduleIacMax = getPVModuleData.getIacmax(moduleInfo);
				String iacMaxMicro = getInverterData.getIacmaxString(microInverterInfo);
				String ratedCurrent = "";
				if (checkValue.Equals(systemTechnology, "Micro Inverter") && checkValue.isNumeric(iacMaxMicro)) {
					ratedCurrent = plansetUtils.getModulePerMicroInverter() > 0 ? checkValue.decimalFormat(
							plansetUtils.getModulePerMicroInverter() * Float.parseFloat(iacMaxMicro)) : "";
				} else if (checkValue.isNumeric(moduleIacMax)) {
					ratedCurrent = checkValue
							.decimalFormat(plansetUtils.getModuleQty() * Float.parseFloat(moduleIacMax));
				}
				operatingVoltageMapping(form, sheetIndex, permitProjectSiteInfo, ratedCurrent, systemTechnology,
						microInverterInfo);

				// --------------------- AC COMBINER / DISCONNECT -------------------//
//				A.B 05-30-2022 CR-916
				List<ACDisconnect> disconnects = Arrays
						.asList(acDisconnect, additionalAcDisconnect, secondacDisconnect, acDisconnectThree).stream()
						.filter(p -> p != null).collect(Collectors.toList());
				List<ACCombinerSLC> combiners = Arrays.asList(acCombiner, slcAcCombiner).stream().filter(p -> p != null)
						.collect(Collectors.toList());
				int count = 0;
				int maxDisconnect = combiners != null && !combiners.isEmpty() ? 2 : 3;

				for (int i = 0; i < disconnects.size() && count < maxDisconnect; i++) {
					if (disconnects.get(i) != null) {
						accdMapping.mapACDRatedCurrent(form, sheetIndex, count + 1, ratedCurrent);
						accdMapping.mapACDOperationVoltage(form, sheetIndex, count + 1, disconnects.get(i),
								permitHomeSite);
						count++;
					}
				}
				for (int i = 0; i < combiners.size() && count < 3; i++) {
					if (combiners.get(i) != null) {
						accdMapping.mapACCRatedCurrent(form, sheetIndex, count + 1, ratedCurrent);
						accdMapping.mapACCOperationVoltage(form, sheetIndex, count + 1, microInverterInfo);
						count++;
					}
				}
				// ----------------------------------------------------------//
				electricUtilityNumber.mapMeterNumber(form, sheetIndex, "P002", permitHomeSite, electricalCompany);

				stamper.close();
				reader.close();

			} catch (IOException | DocumentException e) {

				technicalProblemMsg.traiterException(e);

			}

		}
		return fileRe;

	}

}
