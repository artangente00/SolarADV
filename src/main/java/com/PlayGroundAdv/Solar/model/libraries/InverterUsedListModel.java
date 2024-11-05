package com.PlayGroundAdv.Solar.model.libraries;

public class InverterUsedListModel {

	private Long inverterModel;
	private Long secondInverterModel;
	private Long thirdInverterModel;
	private Long fourthInverterModel;
	private Long fifthInverterModel;
	private Long microInverterModel;

	public InverterUsedListModel() {
		super();
	}

	public InverterUsedListModel(Long inverterModel, Long secondInverterModel, Long thirdInverterModel,
			Long fourthInverterModel, Long fifthInverterModel, Long microInverterModel) {
		super();
		this.inverterModel = inverterModel;
		this.secondInverterModel = secondInverterModel;
		this.thirdInverterModel = thirdInverterModel;
		this.fourthInverterModel = fourthInverterModel;
		this.fifthInverterModel = fifthInverterModel;
		this.microInverterModel = microInverterModel;
	}

	public Long getInverterModel() {
		return inverterModel;
	}

	public void setInverterModel(Long inverterModel) {
		this.inverterModel = inverterModel;
	}

	public Long getSecondInverterModel() {
		return secondInverterModel;
	}

	public void setSecondInverterModel(Long secondInverterModel) {
		this.secondInverterModel = secondInverterModel;
	}

	public Long getThirdInverterModel() {
		return thirdInverterModel;
	}

	public void setThirdInverterModel(Long thirdInverterModel) {
		this.thirdInverterModel = thirdInverterModel;
	}

	public Long getFourthInverterModel() {
		return fourthInverterModel;
	}

	public void setFourthInverterModel(Long fourthInverterModel) {
		this.fourthInverterModel = fourthInverterModel;
	}

	public Long getFifthInverterModel() {
		return fifthInverterModel;
	}

	public void setFifthInverterModel(Long fifthInverterModel) {
		this.fifthInverterModel = fifthInverterModel;
	}

	public Long getMicroInverterModel() {
		return microInverterModel;
	}

	public void setMicroInverterModel(Long microInverterModel) {
		this.microInverterModel = microInverterModel;
	}

}
