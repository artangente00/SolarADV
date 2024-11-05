package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.EMTConduitSize;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.PVCConduitSize;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCStringConductorMapping {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final E003SharedUtils sharedUtils;
	final ESSConnectorsRepository essConnectorsRepo;
	final EMTConduitSize emtConduitSize;
	final PVCConduitSize pvcConduitSize;

	public DCStringConductorMapping(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			E003SharedUtils sharedUtils, ESSConnectorsRepository essConnectorsRepo, EMTConduitSize emtConduitSize,
			PVCConduitSize pvcConduitSize) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.sharedUtils = sharedUtils;
		this.essConnectorsRepo = essConnectorsRepo;
		this.emtConduitSize = emtConduitSize;
		this.pvcConduitSize = pvcConduitSize;
	}

	public void conductorMapping(AcroFields form, int sheetIndex, List<ESSConnectors> dcList,
			UserSettingEntity userSetting, Long roleId, Boolean mapFromUserInput, List<String> dcCircuitEnvironment,
			List<String> dcTradeSize, String ahjMinGroundWireSize, List<Integer> dcNumberOfConductors,
			PlansetUtils plansetUtils) {
		try {
			if (dcList != null && !dcList.isEmpty()) {
				Boolean stepOptimizer = dcList.get(0).getTargetID().contains("OPT");
				for (int i = 1; i <= dcList.size(); i++) {

					// A.B 07-14-2021 CR-3064
					String minGroundWireSize = dcList.get(i - 1).getSourceID().contains("MOD")
							|| dcList.get(i - 1).getSourceID().contains("OPT") ? ahjMinGroundWireSize : null;

					if (Boolean.TRUE.equals(dcList.get(i - 1).getCircuitSpec().getExisting())) {
						mapExisting(form, sheetIndex, i);

					} else if (roleId == 2 || (roleId != 2 && !Boolean.TRUE.equals(mapFromUserInput))) {

						mapByLogic(form, sheetIndex, i, dcList.get(i - 1), minGroundWireSize, dcCircuitEnvironment,
								dcTradeSize, dcNumberOfConductors, stepOptimizer, plansetUtils, userSetting);

					} else if (roleId != 2 && Boolean.TRUE.equals(mapFromUserInput)) {
						mapFromPortal(form, sheetIndex, i, dcList.get(i - 1).getCircuitSpec(), userSetting,
								minGroundWireSize);

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapByLogic(AcroFields form, int sheetIndex, int i, ESSConnectors ac, String minGroundWireSize,
			List<String> dcCircuitEnvironment, List<String> dcTradeSize, List<Integer> dcNumberOfConductors,
			Boolean stepOptimizer, PlansetUtils plansetUtils, UserSettingEntity userSetting) {
		try {

			int conductorQty = 0;

			Integer numberOfConductor = dcNumberOfConductors != null && i - 1 < dcNumberOfConductors.size()
					&& dcNumberOfConductors.get(i - 1) != null ? dcNumberOfConductors.get(i - 1) : 1;

			if (checkValue.Equals(stepOptimizer, true) && i == 1) {
				conductorQty = 2 * numberOfConductor;
			} else {
				conductorQty = 2 * plansetUtils.getMaxNumberOfStrings() * numberOfConductor + 1;
			}
			// ********* CONDUCTOR QTY *********//
			form.setField(sheetIndex + "-DC" + i + "-TOTAL-QTY", String.valueOf(conductorQty));

			String tradeSize = dcTradeSize != null && i - 1 < dcTradeSize.size() && dcTradeSize.get(i - 1) != null
					? dcTradeSize.get(i - 1)
					: "";
			String conduitSize = dcCircuitEnvironment != null && i - 1 < dcCircuitEnvironment.size()
					&& checkValue.Equals(dcCircuitEnvironment.get(i - 1), "UNDERGROUND")
							? pvcConduitSize.getStringConduitSizePVC(tradeSize, conductorQty * numberOfConductor)
							: emtConduitSize.getConduitSizeEMT(tradeSize, conductorQty * numberOfConductor);
			String conduitType = dcCircuitEnvironment != null && i - 1 < dcCircuitEnvironment.size()
					&& checkValue.Equals(dcCircuitEnvironment.get(i - 1), "UNDERGROUND") ? "PVC" : "EMT";

			if (i == 1 || ac.getSourceID().contains("OPT")) {
				pvModuleMapping(form, sheetIndex, i, ac.getCircuitSpec(), minGroundWireSize, tradeSize);
			} else {
				// ********* CONDUCTOR TABLE *********//
				form.setField(sheetIndex + "-DC" + i + "-TRADE-SIZE-E003",
						getValueExisting(ac.getCircuitSpec().getConductorSize(), tradeSize));
				form.setField(sheetIndex + "-DC" + i + "-MATERIAL-E003",
						getValueExisting(ac.getCircuitSpec().getConductorType(), "THWN-2"));

				// ********* CONDUIT TABLE *********//
				String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
						: sharedUtils.getGroundSizeValue(conduitSize, tradeSize, userSetting.getMinimumDCGroundCon(),
								userSetting.getMinimumDCGroConOther());

				form.setField(sheetIndex + "-DC" + i + "-GROUND",
						getValueExisting(ac.getCircuitSpec().getConductorSize(), condGround));
				form.setField(sheetIndex + "-DC" + i + "-CONDUIT-SIZE",
						getValueExisting(ac.getCircuitSpec().getConduitSize(), conduitSize));
				form.setField(sheetIndex + "-DC" + i + "-CONDUIT-MATERIAL",
						getValueExisting(ac.getCircuitSpec().getConduitType(), conduitType));
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void pvModuleMapping(AcroFields form, int sheetIndex, int i, ESSCircuitSpec acSpec,
			String minGroundWireSize, String tradeSize) {
		try {
			// ********* CONDUCTOR TABLE *********//
			form.setField(sheetIndex + "-DC" + i + "-TRADE-SIZE-E003",
					getValueExisting(acSpec.getConductorSize(), i == 1 ? "#12 AWG" : tradeSize));
			form.setField(sheetIndex + "-DC" + i + "-MATERIAL-E003",
					getValueExisting(acSpec.getConductorType(), "PV Wire"));

			// ********* CONDUIT TABLE *********//
			String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize : "#6 AWG";
			form.setField(sheetIndex + "-DC" + i + "-GROUND", getValueExisting(acSpec.getConductorSize(), condGround));
			form.setField(sheetIndex + "-DC" + i + "-CONDUIT-SIZE", getValueExisting(acSpec.getConduitSize(), "N/A"));
			form.setField(sheetIndex + "-DC" + i + "-CONDUIT-MATERIAL",
					getValueExisting(acSpec.getConduitType(), "Open Air"));
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapFromPortal(AcroFields form, int sheetIndex, int i, ESSCircuitSpec acSpec,
			UserSettingEntity userSetting, String dcMinGroundWireSize) {
		try {
			if (acSpec != null) {
				// ********* CONDUCTOR TABLE *********//
				String qty = getValue(acSpec.getConductorQty(), acSpec.getConductorQtyOther() + "");
				form.setField(sheetIndex + "-DC" + i + "-TOTAL-QTY",
						i == 1 ? qty : getQty(qty));
				String tradeSize = getValue(acSpec.getConductorSize(), acSpec.getConductorSizeOther());
				String wireType = getValue(acSpec.getConductorType(), acSpec.getConductorTypeOther());
				form.setField(sheetIndex + "-DC" + i + "-TRADE-SIZE-E003", tradeSize);
				form.setField(sheetIndex + "-DC" + i + "-MATERIAL-E003",
						checkValue.Equals(wireType, "Trunk Cable") ? "TRUNK CBL" : wireType);

				// ********* CONDUIT TABLE *********//
				String condGround = checkValue.isStringNotEmpty(dcMinGroundWireSize) ? dcMinGroundWireSize
						: checkValue.Equals(wireType, "PV Wire") || checkValue.Equals(wireType, "Trunk Cable")
								? "#6 AWG"
								: sharedUtils.getGroundSizeValue(acSpec.getConductorSize(), tradeSize,
										userSetting.getMinimumDCGroundCon(), userSetting.getMinimumDCGroConOther());
				form.setField(sheetIndex + "-DC" + i + "-GROUND",
						getValueExisting(acSpec.getConductorSize(), condGround));
				form.setField(sheetIndex + "-DC" + i + "-CONDUIT-SIZE",
						getValue(acSpec.getConduitSize(), acSpec.getConduitSizeOther()));
				form.setField(sheetIndex + "-DC" + i + "-CONDUIT-MATERIAL",
						getValue(acSpec.getConduitType(), acSpec.getConduitTypeOther()));
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private String getValue(String value, String otherValue) {
		return checkValue.Equals(value, "EXIST") ? "EXISTING"
				: checkValue.Equals(value, "Other") ? otherValue : value != null ? value : "";
	}

	private String getValueExisting(String value, String v) {
		return checkValue.Equals(value, "EXIST") ? "EXISTING" : v != null ? v : "";
	}

	private String getQty(String qty) {
		return String.valueOf(checkValue.convertToInteger(qty) + 1);
	}

	private void mapExisting(AcroFields form, int sheetIndex, int i) {
		try {
			form.setField(sheetIndex + "-DC" + i + "-TOTAL-QTY", "EXISTING");
			form.setField(sheetIndex + "-DC" + i + "-TRADE-SIZE-E003", "EXISTING");
			form.setField(sheetIndex + "-DC" + i + "-MATERIAL-E003", "EXISTING");
			form.setField(sheetIndex + "-DC" + i + "-GROUND", "EXISTING");
			form.setField(sheetIndex + "-DC" + i + "-CONDUIT-SIZE", "EXISTING");
			form.setField(sheetIndex + "-DC" + i + "-CONDUIT-MATERIAL", "EXISTING");
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

}
