package com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class SystemSize {

	final CheckValueTypesService checkValue;
	final UserSettingRepository userSettingRepo;
	public SystemSize(CheckValueTypesService checkValue, UserSettingRepository userSettingRepo) {
		super();
		this.checkValue = checkValue;
		this.userSettingRepo = userSettingRepo;
	}

	static final NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
	static final String MICRO_CONTINUOUS_POWER = "Always use “Microinverter’s continuous Power” to calculate “AC system size”";
	static final String MICRO_PEAK_POWER = "Always use “Microinverter’s peak Power” to calculate “AC system size”";
	static final String MICRO_CONTINUOUS_POWER_ELECTRIC = "Use “Microinverter’s continuous Power” to calculate “AC system size” Only When Electrical engineering is selected";
	static final String MICRO_PEAK_POWER_ELECTRIC = "Use “Microinverter’s peak Power” to calculate “AC system size” Only When Electrical engineering is selected";
	static final String STRING_CONTINUOUS_POWER = "Always use “Continuous inverter AC Output” to calculate “AC system size”";
	static final String STRING_CONTINUOUS_POWER_ELECTRIC = "Use “Continuous inverter AC Output” to calculate “AC system size”";

	private void setFormat() {
		// A.B for trailing zeros:
		nf.setMinimumFractionDigits(3);
		// A.B round to 3 digits:
		nf.setMaximumFractionDigits(3);
	}

	public String systemSizeDC(Cmodulev2 moduleInfo, int totlaModule) {
		try {
			if (moduleInfo != null && checkValue.isNumeric(moduleInfo.getStcRounded())) {
				setFormat();
				return String.valueOf(nf.format(((Float.parseFloat(moduleInfo.getStcRounded()) * totlaModule) / 1000)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String defaultSystemSizeAC(Cmodulev2 moduleInfo, int totlaModule, Inverters inverter) {
		try {
			setFormat();
			if (moduleInfo != null && checkValue.isNumeric(moduleInfo.getPtc()) && inverter != null
					&& checkValue.isNumeric(inverter.getWeightedEfficiency())) {
				return String.valueOf(nf.format((Float.parseFloat(moduleInfo.getPtc()) * totlaModule
						* (Float.parseFloat(inverter.getWeightedEfficiency()) / 100)) / 1000));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String microSystemSizeAC(Cmodulev2 moduleInfo, int totlaModule, Inverters inverter, Long userId,
			Boolean electricalEngineering) {
		try {
			setFormat();
			Integer modulePerMicro = inverter != null && inverter.getModPerMicro() != null ? inverter.getModPerMicro()
					: 1;
			if (moduleInfo != null && inverter != null) {
				String microASystemSize = userSettingRepo.findMicroASystemSizeByUserId(userId);
				if (checkValue.isNumeric(inverter.getPaco())
						&& (checkValue.Equals(microASystemSize, MICRO_CONTINUOUS_POWER)
						|| ((checkValue.Equals(microASystemSize, MICRO_CONTINUOUS_POWER_ELECTRIC)
								|| !checkValue.isStringNotEmpty(microASystemSize))
								&& Boolean.TRUE.equals(electricalEngineering)))) {
					return String.valueOf(
							nf.format(((Float.parseFloat(inverter.getPaco()) * totlaModule) / modulePerMicro) / 1000));
				} else if (inverter.getPeakOutputPower() != null
						&& (checkValue.Equals(microASystemSize, MICRO_PEAK_POWER)
						|| (checkValue.Equals(microASystemSize, MICRO_PEAK_POWER_ELECTRIC)
								&& Boolean.TRUE.equals(electricalEngineering)))) {
					return String.valueOf(
							nf.format(((inverter.getPeakOutputPower() * totlaModule) / modulePerMicro) / 1000));
				} else {
					return defaultSystemSizeAC(moduleInfo, totlaModule, inverter);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String stringSystemSizeAC(Cmodulev2 moduleInfo, int totlaModule, Inverters inverter,
			Long userId, Boolean electricalEngineering) {
		try {
			setFormat();
			if (inverter != null) {
				String stringASystemSize = userSettingRepo.findStringASystemSizeByUserId(userId);
				if (checkValue.isNumeric(inverter.getPowerRating())
						&& (checkValue.Equals(stringASystemSize, STRING_CONTINUOUS_POWER)
								|| ((checkValue.Equals(stringASystemSize, STRING_CONTINUOUS_POWER_ELECTRIC)
										|| !checkValue.isStringNotEmpty(stringASystemSize))
										&& Boolean.TRUE.equals(electricalEngineering)))) {
					return String.valueOf(nf.format(Float.parseFloat(inverter.getPowerRating()) / 1000));
				} else {
					return defaultSystemSizeAC(moduleInfo, totlaModule, inverter);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
