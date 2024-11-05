package com.PlayGroundAdv.Solar.service.generate_planset.project_details;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetInverterData {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;

	public GetInverterData(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
	}

	public Float getIacMax(Inverters inverter) {
		try {
			if (inverter != null && checkValue.isStringNotEmpty(inverter.getIacmax())) {
				return Float.parseFloat(inverter.getIacmax().replace(',', '.'));
			}
			return 0F;
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return 0F;
		}
	}

	public String getSumIacMax(List<Inverters> inverters) {
		try {
			Float sumIacMax = 0f;
			if (inverters != null && !inverters.isEmpty() && inverters.get(0) != null) {
				for (int i = 0; i < inverters.size(); i++) {
					sumIacMax = sumIacMax + getIacMax(inverters.get(i));
				}
			}
			return String.valueOf(sumIacMax);

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "";
		}
	}

	public String getLargestIacMax(Inverters inv1, Inverters inv2, Inverters inv3, Inverters inv4, Inverters inv5) {
		try {
			List<Float> list = new ArrayList<>();
			list.add(getIacMax(inv1));
			list.add(getIacMax(inv2));
			list.add(getIacMax(inv3));
			list.add(getIacMax(inv4));
			list.add(getIacMax(inv5));
			Float max = getIacMax(inv1);
			for (int i = 0; i < list.size(); i++) {
				if (max < list.get(i)) {
					max = list.get(i);
				}
			}
			return String.valueOf(max);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "0";
		}

	}
	public Float getSumIacmax(List<Inverters> inverters, Integer firsttInverterQty, Integer secondtInverterQty) {
		Float sumIacMaxInt = 0f;
		try {
			if (inverters != null && !inverters.isEmpty() && inverters.get(0) != null) {
				for (int i = 0; i < inverters.size(); i++) {
					if (i == 0 && inverters.get(i).getIacmax() != null) {
						sumIacMaxInt = sumIacMaxInt + (getIacMax(inverters.get(i)) * firsttInverterQty);
					} else if (i == 1 && inverters.get(i).getIacmax() != null) {
						sumIacMaxInt = sumIacMaxInt + (getIacMax(inverters.get(i)) * secondtInverterQty);
					} else if (inverters.get(i).getIacmax() != null) {
						sumIacMaxInt = sumIacMaxInt + getIacMax(inverters.get(i));
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return sumIacMaxInt;
	}
	public String getPaco(Inverters inverter) {
		try {
			if (inverter != null && checkValue.isStringNotEmpty(inverter.getPaco())) {
				return inverter.getPaco().replace(',', '.');
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return "";
	}

	public String getIacmaxString(Inverters inverter) {
		try {
			if (inverter != null && checkValue.isStringNotEmpty(inverter.getIacmax())) {
				return inverter.getIacmax().replace(',', '.');
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return "";
	}

	public String getIdcmax(Inverters inverter) {
		try {
			if (inverter != null && checkValue.isStringNotEmpty(inverter.getIdcmax())) {
				return inverter.getIdcmax().replace(',', '.');
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return "";
	}

	public String getWeight(Inverters inverter) {
		try {
			if (inverter != null && checkValue.isStringNotEmpty(inverter.getWeight())) {
				return inverter.getWeight().replace(',', '.');
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return "";
	}
	public String getVac(Inverters inverter) {
		try {
			if (inverter != null && checkValue.isStringNotEmpty(inverter.getVac())) {
				return inverter.getVac().replace(',', '.');
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return "";
	}
}
