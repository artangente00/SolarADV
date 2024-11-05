package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.EMTConduitSize;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.PVCConduitSize;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro.ACCorrectedAmpacity;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACMicroConductorMapping {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final E003SharedUtils sharedUtils;
	final ESSConnectorsRepository essConnectorsRepo;
	final EMTConduitSize emtConduitSize;
	final PVCConduitSize pvcConduitSize;
	final ACCorrectedAmpacity acCorrectedAmpacity;

	public ACMicroConductorMapping(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			E003SharedUtils sharedUtils, ESSConnectorsRepository essConnectorsRepo, EMTConduitSize emtConduitSize,
			PVCConduitSize pvcConduitSize, ACCorrectedAmpacity acCorrectedAmpacity) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.sharedUtils = sharedUtils;
		this.essConnectorsRepo = essConnectorsRepo;
		this.emtConduitSize = emtConduitSize;
		this.pvcConduitSize = pvcConduitSize;
		this.acCorrectedAmpacity = acCorrectedAmpacity;
	}

	public void conductorMapping(AcroFields form, int sheetIndex, UserSettingEntity userSetting,
			String acMinGroundWireSize, List<String> acTradeSize, Long roleId, Long projectId, Boolean mapFromUserInput,
			List<Integer> acNumberOfConductors, List<String> acCircuitEnvironment, String conduitRun,
			PermitHomeSiteInfoEntity permitHomeSite, int microNumberOfStrings) {
		try {
			List<ESSConnectors> acList = essConnectorsRepo.findByIndexGreaterThanAndProjectIdOrderByIndex(1, projectId);
			Integer indexAcCombiner = getIndexByProperty(acList);
			if (acList != null && !acList.isEmpty()) {
				for (int i = 1; i <= acList.size(); i++) {

					if (Boolean.TRUE.equals(acList.get(i - 1).getCircuitSpec().getExisting())) {
						mapExisting(form, sheetIndex, i);

					} else if (roleId == 2 || (roleId != 2 && !Boolean.TRUE.equals(mapFromUserInput))) {

						if (checkValue.contains(acList.get(i - 1).getSourceID(), "MINV")) {
							mapInverter(form, sheetIndex, i, acList.get(i - 1).getCircuitSpec(), acMinGroundWireSize);

						} else {
							Integer conductorQty = 1
									+ acCorrectedAmpacity.numberUndergroundConductor(form, acList.get(i - 1),
											permitHomeSite, i, sheetIndex, microNumberOfStrings, indexAcCombiner);
							mapByLogic(form, sheetIndex, i, acList.get(i - 1).getCircuitSpec(), acMinGroundWireSize,
									conductorQty, acNumberOfConductors, acCircuitEnvironment, conduitRun, acTradeSize,
									userSetting);
						}

					} else if (roleId != 2 && Boolean.TRUE.equals(mapFromUserInput)) {
						mapFromPortal(form, sheetIndex, i, acList.get(i - 1).getCircuitSpec(), userSetting,
								acMinGroundWireSize);

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapInverter(AcroFields form, int sheetIndex, int i, ESSCircuitSpec acSpec, String acMinGroundWireSize) {
		try {
			form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "3");
			form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
					getValueExisting(acSpec.getConductorSize(), "#12 AWG"));
			form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
					getValueExisting(acSpec.getConductorType(), "TRUNK CBL"));
			String condGround = checkValue.isStringNotEmpty(acMinGroundWireSize) ? acMinGroundWireSize : "#6 AWG";
			form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
					getValueExisting(acSpec.getConductorType(), condGround));
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
					getValueExisting(acSpec.getConduitSize(), "N/A"));
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
					getValueExisting(acSpec.getConduitType(), "Open Air"));
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapByLogic(AcroFields form, int sheetIndex, int i, ESSCircuitSpec acSpec, String acMinGroundWireSize,
			int conductorQty, List<Integer> acNumberOfConductors, List<String> acCircuitEnvironment, String conduitRun,
			List<String> acTradeSize, UserSettingEntity userSetting) {
		try {
			
			form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", conductorQty+"");
			
			Integer numberOfConductor = acNumberOfConductors != null && i <= acNumberOfConductors.size()
					&& acNumberOfConductors.get(i - 1) != null ? acNumberOfConductors.get(i - 1) : 1;
			conductorQty = conductorQty * numberOfConductor;
			String tradeSize = acTradeSize != null && i <= acTradeSize.size() && acTradeSize.get(i - 1) != null
					? acTradeSize.get(i - 1)
					: "";
			String acCircuitEnv = acCircuitEnvironment != null && i <= acCircuitEnvironment.size()
					&& acCircuitEnvironment.get(i - 1) != null ? acCircuitEnvironment.get(i - 1) : "";
			String conduitSize = checkValue.Equals(acCircuitEnv, "ATTIC")
					&& checkValue.Equals(conduitRun, "Romex in Attic")
							? "N/A"
							: checkValue.Equals(acCircuitEnv, "UNDERGROUND")
									? pvcConduitSize.getConduitSizePVC(tradeSize, conductorQty)
									: emtConduitSize.getConduitSizeEMT(tradeSize, conductorQty);
			String conduitType = checkValue.Equals(acCircuitEnv, "ATTIC")
					&& checkValue.Equals(conduitRun, "Romex in Attic") ? "Open Air"
							: checkValue.Equals(acCircuitEnv, "UNDERGROUND") ? "PVC" : "EMT";
			String conductorType = checkValue.Equals(acCircuitEnv, "ATTIC")
					&& checkValue.Equals(conduitRun, "Romex in Attic") ? "NM-B" : "THWN-2";

			if (acSpec != null) {
				// ********* CONDUCTOR TABLE *********//
				form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
						getValueExisting(acSpec.getConductorSize(), tradeSize));
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
						getValueExisting(acSpec.getConductorType(), conductorType));

				// ********* CONDUIT TABLE *********//
				String condGround = checkValue.isStringNotEmpty(acMinGroundWireSize) ? acMinGroundWireSize
						: checkValue.Equals(conductorType, "NM-B") ? tradeSize
								: sharedUtils.getGroundSizeValue(acSpec.getConductorSize(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther());
				form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
						getValueExisting(acSpec.getConduitSize(), conduitSize));
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
						getValueExisting(acSpec.getConduitType(), conduitType));
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapFromPortal(AcroFields form, int sheetIndex, int i, ESSCircuitSpec acSpec,
			UserSettingEntity userSetting, String acMinGroundWireSize) {
		try {
			if (acSpec != null) {
				// ********* CONDUCTOR TABLE *********//
				form.setField(sheetIndex + "-AC" + i + "-TOTAL-QTY",
						getQty(getValue(acSpec.getConductorQty(), acSpec.getConductorQtyOther() + "")));
				String tradeSize = getValue(acSpec.getConductorSize(), acSpec.getConductorSizeOther());
				String wireType = getValue(acSpec.getConductorType(), acSpec.getConductorTypeOther());
				form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE-E003", tradeSize);
				form.setField(sheetIndex + "-AC" + i + "-MATERIAL-E003",
						checkValue.Equals(wireType, "Trunk Cable") ? "TRUNK CBL" : wireType);

				// ********* CONDUIT TABLE *********//
				String condGround = checkValue.isStringNotEmpty(acMinGroundWireSize) ? acMinGroundWireSize
						: checkValue.Equals(wireType, "NM-B") ? tradeSize
								: checkValue.Equals(wireType, "PV Wire") || checkValue.Equals(wireType, "Trunk Cable")
										? "#6 AWG"
										: sharedUtils.getGroundSizeValue(acSpec.getConductorSize(), tradeSize,
												userSetting.getMinimumACGroundCon(),
												userSetting.getMinimumACGroConOther());
				form.setField(sheetIndex + "-AC" + i + "-GROUND",
						getValueExisting(acSpec.getConductorSize(), condGround));
				form.setField(sheetIndex + "-AC" + i + "-CONDUIT-SIZE",
						getValue(acSpec.getConduitSize(), acSpec.getConduitSizeOther()));
				form.setField(sheetIndex + "-AC" + i + "-CONDUIT-MATERIAL",
						getValue(acSpec.getConduitType(), acSpec.getConduitTypeOther()));
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void mapExisting(AcroFields form, int sheetIndex, int i) {
		try {
			form.setField(sheetIndex + "-AC" + i + "-TOTAL-QTY", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE-E003", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-MATERIAL-E003", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-GROUND", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-CONDUIT-SIZE", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-CONDUIT-MATERIAL", "EXISTING");
		} catch (Exception e) {
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

	private int getIndexByProperty(List<ESSConnectors> acList) {
		for (int i = 0; i < acList.size(); i++) {
			if (acList.get(i).getSourceID().contains("ACC") || acList.get(i).getSourceID().contains("SLC")) {
				return i + 1;
			}
		}
		return -1;// not ACC in the list list
	}
}
