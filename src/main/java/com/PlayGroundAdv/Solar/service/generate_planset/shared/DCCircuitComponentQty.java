package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class DCCircuitComponentQty {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final InverterRepository inverterRepo;
	final DcCombinerDiscoRepository dccRepo;
	
	public DCCircuitComponentQty(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			InverterRepository inverterRepo, DcCombinerDiscoRepository dccRepo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.inverterRepo = inverterRepo;
		this.dccRepo = dccRepo;
	}

	public void getComponentQty(PermitConduitConductorSectionEntity circuit,
			DCCombinerDisconnectEntity dcCombinerDisconnect, DCCombinerDisconnectEntity roofTopDCDisconnect,
			DCOptimizerEntity dcOptimizer, PermitArrayEntityResultSecond permitArraysEntityResult,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PlansetUtils plansetUtils) {
		try {
			if (circuit.getQtySegmentTwo() != null && checkValue.NotEquals(circuit.getQtySegmentTwo(), 0)) {
				plansetUtils.setQtyDCConverter(circuit.getQtySegmentTwo());
			}

			if (circuit.getQtySegmentThree() != null && circuit.getQtySegmentThree() != 0) {
				plansetUtils.setQtyJunctionBox(plansetUtils.getQtyJunctionBox() + circuit.getQtySegmentThree());
			}
//			S.B 30/09/2020 CR-PM-3365-MOD-002
			Boolean integratedDcDisco = Optional.ofNullable(inverterRepo.findIntegratedDCDiscoById(permitArraysEntityResult.getInverterModel())).orElse(null);
			Boolean integratedDcDisco2 = Optional.ofNullable(inverterRepo.findIntegratedDCDiscoById(permitArraysEntityResult.getSecondInverterModel())).orElse(null);
			Boolean integratedDcDisco3 = Optional.ofNullable(inverterRepo.findIntegratedDCDiscoById(permitArraysEntityResult.getThirdInverterModel())).orElse(null);
			Boolean integratedDcDisco4 = Optional.ofNullable(inverterRepo.findIntegratedDCDiscoById(permitArraysEntityResult.getFourthInverterModel())).orElse(null);
			Boolean integratedDcDisco5 = Optional.ofNullable(inverterRepo.findIntegratedDCDiscoById(permitArraysEntityResult.getFifthInverterModel())).orElse(null);
			String independentDcDisco = permitProjectSiteInfo.getRoofTopDCDisco() != null ? Optional.ofNullable(dccRepo.findTypeDcById(permitProjectSiteInfo.getRoofTopDCDisco().getId())).orElse(null) : null;
			String installingDcDisco = permitProjectSiteInfo.getDisconnectModel() != null ? Optional.ofNullable(dccRepo.findTypeDcById(permitProjectSiteInfo.getDisconnectModel().getId())).orElse(null) : null;
			String installingDcDiscoTwo = permitProjectSiteInfo.getDisconnectModelTwo() != null ? Optional.ofNullable(dccRepo.findTypeDcById(permitProjectSiteInfo.getDisconnectModelTwo().getId())).orElse(null) : null;
			String installingDcDiscoThree = permitProjectSiteInfo.getDisconnectModelThree() != null ? Optional.ofNullable(dccRepo.findTypeDcById(permitProjectSiteInfo.getDisconnectModelThree().getId())).orElse(null) : null;
			
			int i = 0; 
			int j = 0;
			String dcDisconnect = "DC Disconnect";
			if (checkValue.Equals(integratedDcDisco, true))
				i++;
			if (checkValue.Equals(integratedDcDisco2, true))
				i++;
			if (checkValue.Equals(integratedDcDisco3, true))
				i++;
			if (checkValue.Equals(integratedDcDisco4, true))
				i++;
			if (checkValue.Equals(integratedDcDisco5, true))
				i++;
			if(checkValue.Equals(independentDcDisco, dcDisconnect))
				j++;
			if(checkValue.Equals(installingDcDisco, dcDisconnect))
				j++;
			if(checkValue.Equals(installingDcDiscoTwo, dcDisconnect))
				j++;
			if(checkValue.Equals(installingDcDiscoThree, dcDisconnect))
				j++;
			if(i+j != 0) {
				plansetUtils.setQtyDCDiscoCombiner(i+j);
			} 
			if(i > 0 && checkValue.Equals(independentDcDisco, dcDisconnect))
				plansetUtils.setDcComponent(Optional.ofNullable(dccRepo.findModelMappingValueById(permitProjectSiteInfo.getRoofTopDCDisco().getId())).orElse("")+ " & INTEGRATED IN INV");
			else if(i > 0 && checkValue.Equals(installingDcDisco, dcDisconnect))
				plansetUtils.setDcComponent(Optional.ofNullable(dccRepo.findModelMappingValueById(permitProjectSiteInfo.getDisconnectModel().getId())).orElse("")+ " & INTEGRATED IN INV");
			else 
				plansetUtils.setDcComponent("INTEGRATED IN INV");
			
			if (circuit.getQtySegmentFour() != null && circuit.getQtySegmentFour() != 0) {

				if (dcCombinerDisconnect != null
						&& (checkValue.Equals(dcCombinerDisconnect.getTypeDc(), "Rapid Shutdown")
								|| checkValue.Equals(dcCombinerDisconnect.getRsd(), "Y"))) {
					plansetUtils.setQtyRapidShutdown(plansetUtils.getQtyRapidShutdown() + circuit.getQtySegmentFour()); 
				} else if (dcCombinerDisconnect != null
						&& checkValue.Equals(dcCombinerDisconnect.getTypeDc(), "DC Combiner")) {
					plansetUtils.setQtyDCCombinerBox(circuit.getQtySegmentFour());
				} else if( i+j==0 ) {
					plansetUtils.setQtyDCDiscoCombiner(plansetUtils.getQtyDCDiscoCombiner() + circuit.getQtySegmentFour());
				}
					

				if (roofTopDCDisconnect != null && (checkValue.Equals(roofTopDCDisconnect.getTypeDc(), "Rapid Shutdown")
						|| checkValue.Equals(roofTopDCDisconnect.getRsd(), "Y"))) {
					plansetUtils.setQtyRapidShutdown(plansetUtils.getQtyRapidShutdown() + circuit.getQtySegmentFour()); 
				}

			}
			if (circuit.getQtySegmentFive() != null && circuit.getQtySegmentFive() != 0) {

				if (dcCombinerDisconnect != null
						&& (checkValue.Equals(dcCombinerDisconnect.getTypeDc(), "Rapid Shutdown")
								|| checkValue.Equals(dcCombinerDisconnect.getRsd(), "Y"))) {
					plansetUtils.setQtyRapidShutdown(plansetUtils.getQtyRapidShutdown() + circuit.getQtySegmentFive()); 
				} else if (dcCombinerDisconnect != null
						&& checkValue.Equals(dcCombinerDisconnect.getTypeDc(), "DC Combiner")) {
					plansetUtils.setQtyDCCombinerBox(circuit.getQtySegmentFive());
				} else if( i+j==0 ) {
					plansetUtils.setQtyDCDiscoCombiner(plansetUtils.getQtyDCDiscoCombiner() + circuit.getQtySegmentFive());
				}
					

				if (roofTopDCDisconnect != null && (checkValue.Equals(roofTopDCDisconnect.getTypeDc(), "Rapid Shutdown")
						|| checkValue.Equals(roofTopDCDisconnect.getRsd(), "Y"))) {
					plansetUtils.setQtyRapidShutdown(plansetUtils.getQtyRapidShutdown() + circuit.getQtySegmentFive()); 
				}

			}
			if (circuit.getQtySegmentTwo() != null && circuit.getQtySegmentTwo() != 0) {
				if (dcOptimizer != null && checkValue.Equals(dcOptimizer.getType(), "DC/DC Rapid Shutdown")) {
					plansetUtils.setQtyRapidShutdown(circuit.getQtySegmentTwo()); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
