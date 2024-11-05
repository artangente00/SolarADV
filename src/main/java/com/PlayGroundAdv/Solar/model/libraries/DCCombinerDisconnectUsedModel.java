package com.PlayGroundAdv.Solar.model.libraries;

public class DCCombinerDisconnectUsedModel {

	private Long roofTopDCDisco;
	private Long roofTopDCCombiner;
	private Long disconnectModel;
	private Long disconnectModelTwo;
	private Long disconnectModelThree;

	public DCCombinerDisconnectUsedModel() {
		super();
	}

	public Long getRoofTopDCDisco() {
		return roofTopDCDisco;
	}

	public void setRoofTopDCDisco(Long roofTopDCDisco) {
		this.roofTopDCDisco = roofTopDCDisco;
	}

	public Long getRoofTopDCCombiner() {
		return roofTopDCCombiner;
	}

	public void setRoofTopDCCombiner(Long roofTopDCCombiner) {
		this.roofTopDCCombiner = roofTopDCCombiner;
	}

	public Long getDisconnectModel() {
		return disconnectModel;
	}

	public void setDisconnectModel(Long disconnectModel) {
		this.disconnectModel = disconnectModel;
	}

	public Long getDisconnectModelTwo() {
		return disconnectModelTwo;
	}

	public void setDisconnectModelTwo(Long disconnectModelTwo) {
		this.disconnectModelTwo = disconnectModelTwo;
	}

	public Long getDisconnectModelThree() {
		return disconnectModelThree;
	}

	public void setDisconnectModelThree(Long disconnectModelThree) {
		this.disconnectModelThree = disconnectModelThree;
	}

	public DCCombinerDisconnectUsedModel(Long roofTopDCDisco, Long roofTopDCCombiner, Long disconnectModel,
			Long disconnectModelTwo, Long disconnectModelThree) {
		super();
		this.roofTopDCDisco = roofTopDCDisco;
		this.roofTopDCCombiner = roofTopDCCombiner;
		this.disconnectModel = disconnectModel;
		this.disconnectModelTwo = disconnectModelTwo;
		this.disconnectModelThree = disconnectModelThree;
	}

}
