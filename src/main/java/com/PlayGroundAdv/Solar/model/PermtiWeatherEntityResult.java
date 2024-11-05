package com.PlayGroundAdv.Solar.model;

public class PermtiWeatherEntityResult {

	private String elevation;
	private String quatrePourCentAverageHigh;
	private String deuxPourCentAverageHigh;
	private String extremeMinimum;
	private String quatrePourCentAvHighOther;
	private String deuxPourCentAverageHighOther;
	private String extremeMinimumOther;

	public PermtiWeatherEntityResult() {
		super();
	}

	public PermtiWeatherEntityResult(String elevation, String quatrePourCentAverageHigh, String deuxPourCentAverageHigh,
			String extremeMinimum, String quatrePourCentAvHighOther, String deuxPourCentAverageHighOther,
			String extremeMinimumOther) {
		super();
		this.elevation = elevation;
		this.quatrePourCentAverageHigh = quatrePourCentAverageHigh;
		this.deuxPourCentAverageHigh = deuxPourCentAverageHigh;
		this.extremeMinimum = extremeMinimum;
		this.quatrePourCentAvHighOther = quatrePourCentAvHighOther;
		this.deuxPourCentAverageHighOther = deuxPourCentAverageHighOther;
		this.extremeMinimumOther = extremeMinimumOther;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public String getQuatrePourCentAverageHigh() {
		return quatrePourCentAverageHigh;
	}

	public void setQuatrePourCentAverageHigh(String quatrePourCentAverageHigh) {
		this.quatrePourCentAverageHigh = quatrePourCentAverageHigh;
	}

	public String getDeuxPourCentAverageHigh() {
		return deuxPourCentAverageHigh;
	}

	public void setDeuxPourCentAverageHigh(String deuxPourCentAverageHigh) {
		this.deuxPourCentAverageHigh = deuxPourCentAverageHigh;
	}

	public String getExtremeMinimum() {
		return extremeMinimum;
	}

	public void setExtremeMinimum(String extremeMinimum) {
		this.extremeMinimum = extremeMinimum;
	}

	public String getQuatrePourCentAvHighOther() {
		return quatrePourCentAvHighOther;
	}

	public void setQuatrePourCentAvHighOther(String quatrePourCentAvHighOther) {
		this.quatrePourCentAvHighOther = quatrePourCentAvHighOther;
	}

	public String getDeuxPourCentAverageHighOther() {
		return deuxPourCentAverageHighOther;
	}

	public void setDeuxPourCentAverageHighOther(String deuxPourCentAverageHighOther) {
		this.deuxPourCentAverageHighOther = deuxPourCentAverageHighOther;
	}

	public String getExtremeMinimumOther() {
		return extremeMinimumOther;
	}

	public void setExtremeMinimumOther(String extremeMinimumOther) {
		this.extremeMinimumOther = extremeMinimumOther;
	}

	@Override
	public String toString() {
		return "PermtiWeatherEntityResult [elevation=" + elevation + ", quatrePourCentAverageHigh="
				+ quatrePourCentAverageHigh + ", deuxPourCentAverageHigh=" + deuxPourCentAverageHigh
				+ ", extremeMinimum=" + extremeMinimum + ", quatrePourCentAvHighOther=" + quatrePourCentAvHighOther
				+ ", deuxPourCentAverageHighOther=" + deuxPourCentAverageHighOther + ", extremeMinimumOther="
				+ extremeMinimumOther + "]";
	}

}
