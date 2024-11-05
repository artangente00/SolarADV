package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCCircuitMappingMicro {
	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final DefaultRowMapping defaultRowMapping;
	final DCRequipredAmpacity dcRequipredAmpacity;
	final ESSConnectorsRepository essConnectorsRepo;
	final GenerateCircuitList generateCircuitList;

	public DCCircuitMappingMicro(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			DefaultRowMapping defaultRowMapping, DCRequipredAmpacity dcRequipredAmpacity,
			ESSConnectorsRepository essConnectorsRepo, GenerateCircuitList generateCircuitList) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.defaultRowMapping = defaultRowMapping;
		this.dcRequipredAmpacity = dcRequipredAmpacity;
		this.essConnectorsRepo = essConnectorsRepo;
		this.generateCircuitList = generateCircuitList;
	}

	public void dcMapping(AcroFields form, PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity,
			AuthentificationEntity userConnectedEntity, Integer fourPerCentAverageHigh, Cmodulev2 moduleInfo,
			int sheetIndex, String necCode, Boolean showConduitRoofAsHeight, E002Model params, String state) {
		try {
			ESSConnectors dc = essConnectorsRepo.findByIndexAndProjectId(1, permitEntity.getId());
			if (dc != null) {
				// A.B Circuit Origin & Destination
				form.setField(sheetIndex + "-" + "DC1-CIRCUIT-ORGIN", "PV MODULE");
				form.setField(sheetIndex + "-" + "DC1-CIRCUIT-DESTINATION",
						generateCircuitList.getEquipmentName(dc.getTargetID(), null,permitEntity.getId(),"PV MODULE"));

				if ((dc.getCircuitSpec() != null && checkValue.Equals(dc.getCircuitSpec().getExisting(), true))
						|| checkValue.Equals(permitEntity.getExistModule(), true)) {
					
					defaultRowMapping.mapDcDefaultValue(1, form, sheetIndex, "EXISTING");
				} else {
					dcRequipredAmpacity.requipredAmpacityMapping(form, dc, circuit, permitEntity, userConnectedEntity,
							fourPerCentAverageHigh, moduleInfo, sheetIndex, necCode, showConduitRoofAsHeight, params,
							state);
				}
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
