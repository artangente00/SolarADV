package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class ACCircuitComponentQty {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;

	public ACCircuitComponentQty(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public void getComponentListQty(PermitConduitConductorSectionEntity circuit, ACDisconnect acDisconnect,
			ACDisconnect secondacDisconnect, ACCombinerSLC slcAcCombiner, PlansetUtils plansetUtils,
			String inverterTechnology, Inverters inverterInfo) {
		try {
			
			if (checkValue.Equals(inverterTechnology, "Neither") || checkValue.Equals(inverterTechnology, "System Optimizer")) {
				Boolean invInfo = inverterInfo.getIntegratedACDisco();
				if (invInfo != null && Boolean.TRUE.equals(invInfo)) {
					plansetUtils.setQtyACDCInverterWACDisconnect(circuit.getQtySegmentSix());
				} else {
					plansetUtils.setQtyACDCInverter(circuit.getQtySegmentSix());
				}
			}
			
			if (circuit != null && circuit.getQtySegmentNine() != null && circuit.getQtySegmentNine() != 0) {
				Boolean firstACFusible = false;
				if (acDisconnect != null) {
					if (checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "FUSIBLE DISCONNECT")
							|| checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "FUSIBLE")
							|| checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "Fusible")) {
						firstACFusible = true;
					}
				}
				plansetUtils.setQtyACDisconnect(circuit.getQtySegmentNine());

				if (secondacDisconnect != null && secondacDisconnect.getId() != null) {

					if (checkValue.Equals(firstACFusible, true)
							&& checkValue.NotEqualsNull(secondacDisconnect.getDisconnectDeviceType(),
									"FUSIBLE DISCONNECT")
							&& checkValue.NotEqualsNull(secondacDisconnect.getDisconnectDeviceType(), "FUSIBLE")
							&& checkValue.NotEqualsNull(secondacDisconnect.getDisconnectDeviceType(), "Fusible")) {
						plansetUtils.setQtyACDiscoGRND(1);
					} else if (checkValue.Equals(firstACFusible, false)
							&& (checkValue.Equals(secondacDisconnect.getDisconnectDeviceType(), "FUSIBLE DISCONNECT")
									|| checkValue.Equals(secondacDisconnect.getDisconnectDeviceType(), "FUSIBLE")
									|| checkValue.Equals(secondacDisconnect.getDisconnectDeviceType(), "Fusible"))) {
						plansetUtils.setQtyACDiscoGRND(1);
					} else if (checkValue.Equals(acDisconnect.getId(), secondacDisconnect.getId())
							&& checkValue.Equals(circuit.getQtySegmentNine(), 1)) {
						plansetUtils.setQtyACDisconnect(plansetUtils.getQtyACDisconnect() + 1);
					}
				}

			}

			if (circuit != null && circuit.getQtySegmentSeven() != null && circuit.getQtySegmentSeven() != 0) {
				plansetUtils.setQtyJunctionBox(plansetUtils.getQtyJunctionBox() + circuit.getQtySegmentSeven());
			}
			if (circuit != null && circuit.getQtySegmentEight() != null && circuit.getQtySegmentEight() != 0) {
				if (slcAcCombiner != null && slcAcCombiner.getId() != null) {
					// A.B Fix Get ID Null pointer exception
					plansetUtils.setQtyACCombinerLoadCenter(circuit.getQtySegmentEight());
				} else {
					plansetUtils.setQtyACCombiner(circuit.getQtySegmentEight());
				}

			}
			if (circuit != null && circuit.getQtySegmentTen() != null && circuit.getQtySegmentTen() != 0) {
				plansetUtils.setQtyProductionMeter(circuit.getQtySegmentTen());
			}


		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
