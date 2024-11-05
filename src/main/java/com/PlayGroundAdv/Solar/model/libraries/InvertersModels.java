package com.PlayGroundAdv.Solar.model.libraries;

public class InvertersModels {

	private Long pvModuleModEl;
	private Long inverterModel;
	private Long secondInverterModel;
	private Long thirdInverterModel;
	private Long microInverter;

	public InvertersModels() {
		super();
	}

	public InvertersModels(Long pvModuleModEl, Long inverterModel, Long secondInverterModel,
			Long thirdInverterModel, Long microInverter) {
		super();
		this.pvModuleModEl = pvModuleModEl;
		this.inverterModel = inverterModel;
		this.secondInverterModel = secondInverterModel;
		this.thirdInverterModel = thirdInverterModel;
		this.microInverter = microInverter;
	}

	public Long getPvModuleModEl() {
		return pvModuleModEl;
	}

	public void setPvModuleModEl(Long pvModuleModEl) {
		this.pvModuleModEl = pvModuleModEl;
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

	public Long getMicroInverter() {
		return microInverter;
	}

	public void setMicroInverter(Long microInverter) {
		this.microInverter = microInverter;
	}


}
