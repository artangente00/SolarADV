package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.planset_utils.VoltageDropTable;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACCircuitMappingMicro {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final DefaultRowMapping defaultRowMapping;
	final GenerateCircuitList generateCircuitList;
	final ACRequipredAmpacity acRequipredAmpacity;

	public ACCircuitMappingMicro(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			DefaultRowMapping defaultRowMapping, GenerateCircuitList generateCircuitList,
			ACRequipredAmpacity acRequipredAmpacity) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.defaultRowMapping = defaultRowMapping;
		this.generateCircuitList = generateCircuitList;
		this.acRequipredAmpacity = acRequipredAmpacity;
	}

	public void acMapping(AcroFields form, int i, List<ESSConnectors> acList, PlansetUtils plansetUtils,
			Inverters microInverterInfo, PermitProjectSiteInfoEntity permitProjectSiteInfo, Integer perCentAverageHigh,
			List<String> acCircuitEnvironment, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, AuthentificationEntity userConnectedEntity,
			PermitLayoutEntity permitLayoutEntity, int sheetIndex, UserSettingEntity userSetting,
			PermitHomeSiteInfoEntity permitHomeSite, String necCode, E002Model params, List<VoltageDropTable> voltageDrop,
			Long projectId, Boolean mapFromUserInput, int indexAcCombiner) {
		try {

			if (acList.get(i - 1) != null) {

				// A.B 04-03: Executed Only when the operating Temperature is not High
				params.getIncorrectTradeSize().clear();

				// A.B Circuit Origin & Destination
				String circuitOrigin = generateCircuitList.getEquipmentName(acList.get(i - 1).getSourceID(),
						permitProjectSiteInfo.getMainPanelUpgrade(), projectId,
						i > 1 ? acList.get(i - 2).getSourceID() : "");
				String circuitDestination = generateCircuitList.getEquipmentName(acList.get(i - 1).getTargetID(),
						permitProjectSiteInfo.getMainPanelUpgrade(), projectId, acList.get(i - 1).getSourceID());
				form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-ORGIN", circuitOrigin);
				form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-DESTINATION", circuitDestination);
				
				Boolean existing = Boolean.TRUE.equals(acList.get(i - 1).getCircuitSpec().getExisting())
						|| Boolean.TRUE.equals(generateCircuitList.existingCircuit(acList.get(i - 1), permitEntity,
								permitProjectSiteInfo));
				// A.B Check If Circuit Existing
				if (Boolean.TRUE.equals(existing)) {

					defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
					acNumberOfConductors.add(1);
					acCircuitEnvironment.add("EXISTING");
					acTradeSize.add("EXISTING");

				} else {
					acRequipredAmpacity.requipredAmpacityMapping(form, i, acList.get(i - 1), plansetUtils,
							microInverterInfo, permitProjectSiteInfo, perCentAverageHigh, acCircuitEnvironment,
							acTradeSize, acNumberOfConductors, userConnectedEntity, permitLayoutEntity, sheetIndex,
							userSetting, permitHomeSite, necCode, params, voltageDrop, indexAcCombiner,
							mapFromUserInput, existing, acList, projectId);
				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

}
