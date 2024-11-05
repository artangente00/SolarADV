package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.VoltageDropTable;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.NEC310Values;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.VoltageDropCalculation;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class VoltageDrop {

	final CheckValueTypesService checkValue;
	final VoltageDropCalculation voltageDropCalculation;
	final TechnicalProblemMsg technicalProblemMsg;
	final NEC310Values nec310Values;
	final NEC3106B16Repository nec3106B16Repo;
	final ACCorrectedAmpacity acCorrectedAmpacity;
	final GenerateCircuitList generateCircuitList;

	public VoltageDrop(CheckValueTypesService checkValue, VoltageDropCalculation voltageDropCalculation,
			TechnicalProblemMsg technicalProblemMsg, NEC310Values nec310Values, NEC3106B16Repository nec3106b16Repo,
			ACCorrectedAmpacity acCorrectedAmpacity, GenerateCircuitList generateCircuitList) {
		super();
		this.checkValue = checkValue;
		this.voltageDropCalculation = voltageDropCalculation;
		this.technicalProblemMsg = technicalProblemMsg;
		this.nec310Values = nec310Values;
		nec3106B16Repo = nec3106b16Repo;
		this.acCorrectedAmpacity = acCorrectedAmpacity;
		this.generateCircuitList = generateCircuitList;
	}

	public void voltageDrop(AcroFields form, int sheetIndex, int i, List<VoltageDropTable> voltageDrop,
			List<Integer> acNumberOfConductors, E002Model params, PermitHomeSiteInfoEntity permitHomeSite,
			List<String> acTradeSize, List<ESSConnectors> acList, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Long projectId) {
		try {
			String tradeSize = form.getField(sheetIndex + "-AC" + i + "-TRADE-SIZE");
			if (tradeSize != null && !tradeSize.equals("EXISTING")) {
				String maxInvOutputCurrent = form.getField(sheetIndex + "-AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT");
				Float vd = voltageDropCalculation.acVoltageDropBatterySystem(tradeSize,
						acNumberOfConductors.get(acNumberOfConductors.size() - 1), maxInvOutputCurrent,
						permitHomeSite.getIfServiceVoltage(), permitHomeSite.getServiceVoltage(),
						acList.get(i - 1).getCircuitSpec().getCircuitLength());
				if (vd != 0.0 && vd >= 1.99) {
					Boolean sameTradeSize = false;
					do {

						String nextTradeSize = nec310Values.getNextTradeSize(tradeSize);
						sameTradeSize = checkValue.Equals(nextTradeSize, tradeSize);
						if (Boolean.FALSE.equals(sameTradeSize)) {
							params.setNEC310(nec3106B16Repo.findFirstBytradeSzeAndNumberOfConductors(nextTradeSize,
									acNumberOfConductors.get(acNumberOfConductors.size() - 1)));
							if (params.getNEC310() != null) {
								tradeSize = nextTradeSize;
								params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
								acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
								acTradeSize.remove(acTradeSize.size() - 1);
								acCorrectedAmpacity.correctedAmpacityMapping(form, i, acTradeSize, acNumberOfConductors,
										sheetIndex, params);
								maxInvOutputCurrent = form
										.getField(sheetIndex + "-AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT");
								vd = voltageDropCalculation.acVoltageDropBatterySystem(
										acTradeSize.get(acTradeSize.size() - 1),
										acNumberOfConductors.get(acNumberOfConductors.size() - 1), maxInvOutputCurrent,
										permitHomeSite.getIfServiceVoltage(), permitHomeSite.getServiceVoltage(),
										acList.get(i - 1).getCircuitSpec().getCircuitLength());
							}

						}
					} while (vd != 0.0 && vd >= 1.99 && params.getNEC310() != null
							&& Boolean.FALSE.equals(sameTradeSize));
				}

				if (vd != 0.0) {
					VoltageDropTable v = new VoltageDropTable();
					// Origin
					v.setCircuitOrigin(generateCircuitList.getEquipmentName(acList.get(i - 1).getSourceID(),
							permitProjectSiteInfo.getMainPanelUpgrade(), projectId,
							i > 1 ? acList.get(i - 2).getTargetID() : ""));
					// Destination
					v.setCircuitDestination(generateCircuitList.getEquipmentName(
							acList.get(i - 1).getTargetID(), permitProjectSiteInfo.getMainPanelUpgrade(), projectId,
							i > 1 ? acList.get(i - 1).getSourceID() : ""));

					v.setAcDc("AC");
					v.setVd(vd);
					voltageDrop.add(v);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
