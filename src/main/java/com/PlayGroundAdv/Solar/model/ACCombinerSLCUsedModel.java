package com.PlayGroundAdv.Solar.model;

public class ACCombinerSLCUsedModel {

	private Long roofTopACCombiner;
	private Long aCCombinerInstalled;
	private Long groundLevelACCombinerBoxModel;

	public ACCombinerSLCUsedModel() {
		super();
	}

	public ACCombinerSLCUsedModel(Long roofTopACCombiner, Long aCCombinerInstalled,
			Long groundLevelACCombinerBoxModel) {
		super();
		this.roofTopACCombiner = roofTopACCombiner;
		this.aCCombinerInstalled = aCCombinerInstalled;
		this.groundLevelACCombinerBoxModel = groundLevelACCombinerBoxModel;
	}

	public Long getRoofTopACCombiner() {
		return roofTopACCombiner;
	}

	public void setRoofTopACCombiner(Long roofTopACCombiner) {
		this.roofTopACCombiner = roofTopACCombiner;
	}

	public Long getACCombinerInstalled() {
		return aCCombinerInstalled;
	}

	public void setACCombinerInstalled(Long aCCombinerInstalled) {
		this.aCCombinerInstalled = aCCombinerInstalled;
	}

	public Long getGroundLevelACCombinerBoxModel() {
		return groundLevelACCombinerBoxModel;
	}

	public void setGroundLevelACCombinerBoxModel(Long groundLevelACCombinerBoxModel) {
		this.groundLevelACCombinerBoxModel = groundLevelACCombinerBoxModel;
	}

}
