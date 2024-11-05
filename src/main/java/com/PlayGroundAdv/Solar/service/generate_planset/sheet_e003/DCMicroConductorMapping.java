package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCMicroConductorMapping {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final E003SharedUtils sharedUtils;
	final ESSConnectorsRepository essConnectorsRepo;

	public DCMicroConductorMapping(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			E003SharedUtils sharedUtils, ESSConnectorsRepository essConnectorsRepo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.sharedUtils = sharedUtils;
		this.essConnectorsRepo = essConnectorsRepo;
	}

	public void conductorMapping(AcroFields form, int sheetIndex, UserSettingEntity userSetting,
			String dcMinGroundWireSize, Long roleId, Long projectId, Boolean mapFromUserInput) {
		try {
			ESSConnectors dc = essConnectorsRepo.findByIndexAndProjectId(1, projectId);
			if (dc != null) {
				
				if (Boolean.TRUE.equals(dc.getCircuitSpec().getExisting())) {
					mapExisting(form, sheetIndex);

				} else if (roleId == 2 || (roleId != 2 && !Boolean.TRUE.equals(mapFromUserInput))) {
					mapByLogic(form, sheetIndex, dc.getCircuitSpec(), dcMinGroundWireSize);

				} else if (roleId != 2 && Boolean.TRUE.equals(mapFromUserInput)) {
					mapFromPortal(form, sheetIndex, dc.getCircuitSpec(), userSetting, dcMinGroundWireSize);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapByLogic(AcroFields form, int sheetIndex, ESSCircuitSpec acSpec, String dcMinGroundWireSize) {
		try {
			// ********* CONDUCTOR TABLE *********//
			form.setField(sheetIndex + "-DC1-TOTAL-QTY", "2");
			if (acSpec != null) {
				form.setField(sheetIndex + "-DC1-TRADE-SIZE-E003",
						getValueExisting(acSpec.getConductorSize(), "#12 AWG"));
				form.setField(sheetIndex + "-DC1-MATERIAL-E003",
						getValueExisting(acSpec.getConductorType(), "PV Wire"));

				// ********* CONDUIT TABLE *********//
				// 19/06/2019 : CI : CR 2711 : change from "#6 GEC" to "#6 AWG"
				// 16/07/2021 : A.B : CR 3064 : Map from AHJ Min. Ground Wire Size On Roof If
				// exist
				String condGround = checkValue.isStringNotEmpty(dcMinGroundWireSize) ? dcMinGroundWireSize : "#6 AWG";
				form.setField(sheetIndex + "-DC1-GROUND", getValueExisting(acSpec.getConductorSize(), condGround));
				form.setField(sheetIndex + "-DC1-CONDUIT-SIZE", getValueExisting(acSpec.getConduitSize(), "N/A"));
				form.setField(sheetIndex + "-DC1-CONDUIT-MATERIAL",
						getValueExisting(acSpec.getConduitType(), "Open Air"));
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapFromPortal(AcroFields form, int sheetIndex, ESSCircuitSpec acSpec, UserSettingEntity userSetting,
			String dcMinGroundWireSize) {
		try {
			if (acSpec != null) {
				// ********* CONDUCTOR TABLE *********//
				form.setField(sheetIndex + "-DC1-TOTAL-QTY",
						getValue(acSpec.getConductorQty(), acSpec.getConductorQtyOther() + ""));
				String tradeSize = getValue(acSpec.getConductorSize(), acSpec.getConductorSizeOther());
				String wireType = getValue(acSpec.getConductorType(), acSpec.getConductorTypeOther());
				form.setField(sheetIndex + "-DC1-TRADE-SIZE-E003", tradeSize);
				form.setField(sheetIndex + "-DC1-MATERIAL-E003",
						checkValue.Equals(wireType, "Trunk Cable") ? "TRUNK CBL" : wireType);

				// ********* CONDUIT TABLE *********//
				String condGround = checkValue.isStringNotEmpty(dcMinGroundWireSize) ? dcMinGroundWireSize
						: checkValue.Equals(wireType, "PV Wire") || checkValue.Equals(wireType, "Trunk Cable")
								? "#6 AWG"
								: sharedUtils.getGroundSizeValue(acSpec.getConductorSize(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther());
				form.setField(sheetIndex + "-DC1-GROUND", getValueExisting(acSpec.getConductorSize(), condGround));
				form.setField(sheetIndex + "-DC1-CONDUIT-SIZE",
						getValue(acSpec.getConduitSize(), acSpec.getConduitSizeOther()));
				form.setField(sheetIndex + "-DC1-CONDUIT-MATERIAL",
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

	private void mapExisting(AcroFields form, int sheetIndex) {
		try {
			form.setField(sheetIndex + "-DC1-TOTAL-QTY", "EXISTING");
			form.setField(sheetIndex + "-DC1-TRADE-SIZE-E003", "EXISTING");
			form.setField(sheetIndex + "-DC1-MATERIAL-E003", "EXISTING");
			form.setField(sheetIndex + "-DC1-GROUND", "EXISTING");
			form.setField(sheetIndex + "-DC1-CONDUIT-SIZE", "EXISTING");
			form.setField(sheetIndex + "-DC1-CONDUIT-MATERIAL", "EXISTING");
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

}
