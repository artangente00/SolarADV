package com.PlayGroundAdv.Solar.service.generate_planset.project_details;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDesignCriteriaModel;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetWeatherData {
	
	final CheckValueTypesService checkValue;
	
	public GetWeatherData(CheckValueTypesService checkValue) {
		super();
		this.checkValue = checkValue;
	}

	public Integer getFourPerCentAverageHigh(PermtiWeatherEntityResult permtiWeather) {
		try {
			if (checkValue.isStringNotEmpty(permtiWeather.getQuatrePourCentAverageHigh())) {
				if (checkValue.NotEquals(permtiWeather.getQuatrePourCentAverageHigh(), "Other")) {
					String numberOnly= permtiWeather.getQuatrePourCentAverageHigh().replaceAll("[^0-9]", "");
					return checkValue.isNumeric(numberOnly) ? Integer.parseInt(numberOnly) : 0;
				} else {
					String numberOnly= permtiWeather.getQuatrePourCentAvHighOther().replaceAll("[^0-9]", "");
					return checkValue.isNumeric(numberOnly) ? Integer.parseInt(numberOnly) : 0;
				}
			}else {
				return 0;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public Integer getTwoPerCentAverageHigh(PermtiWeatherEntityResult permtiWeather) {
		try {
			if (checkValue.isStringNotEmpty(permtiWeather.getDeuxPourCentAverageHigh())) {
				if (checkValue.NotEquals(permtiWeather.getDeuxPourCentAverageHigh(), "Other")) {
					String numberOnly= permtiWeather.getDeuxPourCentAverageHigh().replaceAll("[^0-9]", "");
					return checkValue.isNumeric(numberOnly) ? Integer.parseInt(numberOnly) : 0;
				
				} else {
					String numberOnly= permtiWeather.getDeuxPourCentAverageHighOther().replaceAll("[^0-9]", "");
					return checkValue.isNumeric(numberOnly) ? Integer.parseInt(numberOnly) : 0;
				}
			}else {
				return 0;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getExtremeMinimum(PermtiWeatherEntityResult permtiWeather, AHJDesignCriteriaModel designCriteria) {
		try {
			String extremeMinimum = "";
			if (designCriteria != null && checkValue.NotEquals(designCriteria.getExtremeMinimum(), "")) {
				extremeMinimum = designCriteria.getExtremeMinimum().replaceAll("\\D+", "");
				if (checkValue.NotEquals(extremeMinimum, "")
						&& checkValue.contains(designCriteria.getExtremeMinimum(), "-")) {
					extremeMinimum = "-" + extremeMinimum;
				}
			}
			if (checkValue.Equals(extremeMinimum, "") && permtiWeather != null
					&& checkValue.NotEquals(permtiWeather.getExtremeMinimum(), "")) {
				if (checkValue.Equals(permtiWeather.getExtremeMinimum(), "Other")
						&& checkValue.NotEquals(permtiWeather.getExtremeMinimumOther(), "")) {
					extremeMinimum = permtiWeather.getExtremeMinimumOther().replaceAll("\\D+", "");
					if (checkValue.contains(permtiWeather.getExtremeMinimumOther(), "-")) {
						extremeMinimum = "-" + extremeMinimum;
					}
				} else if (checkValue.isNumeric(permtiWeather.getExtremeMinimum())) {
					extremeMinimum = permtiWeather.getExtremeMinimum();
				}
			}
			return extremeMinimum;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
