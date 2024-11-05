package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.NEC_310_16_B_16;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class NecBchart {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final NEC3106B16Repository nec3106B16Repo;

	public NecBchart(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			NEC3106B16Repository nec3106b16Repo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		nec3106B16Repo = nec3106b16Repo;
	}

	public NEC_310_16_B_16 getNecBchart(E002Model params) {
		try {
			Integer nec310 = (int) Math.round(params.getNec31016Column90());
			List<String> notIn = new ArrayList<>();
			if (!params.getIncorrectACTradeSize().isEmpty()) {
				notIn.addAll(params.getIncorrectACTradeSize());
				notIn.add("#12 AWG");
			}
			if (checkValue.NotEquals(params.getRequiredACConductorAmpacity(), "")
					&& Double.parseDouble(params.getRequiredACConductorAmpacity()) > 30) {
				String tradeSizeValue = "";

				if (Boolean.TRUE.equals(params.getIncorrectACTradeSizeLogic())
						&& !params.getIncorrectACTradeSize().isEmpty()) {
					tradeSizeValue = nec3106B16Repo
							.findTradeSizeBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310, "#12 AWG", notIn);
				} else {
					tradeSizeValue = nec3106B16Repo
							.findTradeSizeBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG");
				}
				String tradeNumber = tradeSizeValue != null ? tradeSizeValue.replaceAll("[^0-9]", "") : null;
				if (checkValue.contains(tradeSizeValue, "AWG") && checkValue.contains(tradeSizeValue, "#")
						&& checkValue.notContains(tradeSizeValue, "/") && checkValue.isNumeric(tradeNumber) && Integer.parseInt(tradeNumber) > 8) {
					if (Boolean.TRUE.equals(params.getIncorrectACTradeSizeLogic())
							&& !params.getIncorrectACTradeSize().isEmpty()
							&& params.getIncorrectACTradeSize().contains("#8 AWG")) {
						params.setNEC310(null);
					} else {
						params.setNEC310(nec3106B16Repo.findFirstBytradeSze("#8 AWG"));
					}
				} else {
					params.setNEC310(getCorrectNecChart(params.getIncorrectACTradeSizeLogic(), notIn, nec310));
				}
			} else {
				params.setNEC310(getCorrectNecChart(params.getIncorrectACTradeSizeLogic(), notIn, nec310));
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params.getNEC310();
	}

	private NEC_310_16_B_16 getCorrectNecChart(Boolean incorrectACTradeSizeLogic, List<String> notIn, Integer nec310) {
		try {
			if (Boolean.TRUE.equals(incorrectACTradeSizeLogic) && !notIn.isEmpty()) {
				return nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310, "#12 AWG", notIn);
			} else {
				return nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return null;
		}
	}
}
