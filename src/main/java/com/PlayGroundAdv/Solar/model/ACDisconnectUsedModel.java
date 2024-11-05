package com.PlayGroundAdv.Solar.model;

public class ACDisconnectUsedModel {

	private String roofTopACDisco;
	private String rooftopACCombinerModel;
	private String rooftopACCombinerModelTwo;

	public ACDisconnectUsedModel() {
		super();
	}

	public ACDisconnectUsedModel(String roofTopACDisco, String rooftopACCombinerModel,
			String rooftopACCombinerModelTwo) {
		super();
		this.roofTopACDisco = roofTopACDisco;
		this.rooftopACCombinerModel = rooftopACCombinerModel;
		this.rooftopACCombinerModelTwo = rooftopACCombinerModelTwo;
	}

	public String getRoofTopACDisco() {
		return roofTopACDisco;
	}

	public void setRoofTopACDisco(String roofTopACDisco) {
		this.roofTopACDisco = roofTopACDisco;
	}

	public String getRooftopACCombinerModel() {
		return rooftopACCombinerModel;
	}

	public void setRooftopACCombinerModel(String rooftopACCombinerModel) {
		this.rooftopACCombinerModel = rooftopACCombinerModel;
	}

	public String getRooftopACCombinerModelTwo() {
		return rooftopACCombinerModelTwo;
	}

	public void setRooftopACCombinerModelTwo(String rooftopACCombinerModelTwo) {
		this.rooftopACCombinerModelTwo = rooftopACCombinerModelTwo;
	}

}
