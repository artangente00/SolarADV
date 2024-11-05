package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PermitSketchResults {

	private Integer arraySketch;
	private String azimuth;
	private String roofPitch;
	private Boolean moduleTils;
	private String eaveOverHang;
	private String eaveOverHangOther;
	private Boolean haveSkecth;
	private String modelvalue;
	private String moduleQty;
	private Integer squareFootage;

	public PermitSketchResults(Boolean haveSkecth) {
		super();
		this.haveSkecth = haveSkecth;
	}

	public PermitSketchResults(Integer arraySketch, String azimuth, String roofPitch, Boolean moduleTils,
			String eaveOverHang, String eaveOverHangOther, String modelvalue, String moduleQty, Integer squareFootage) {
		super();
		this.arraySketch = arraySketch;
		this.azimuth = azimuth;
		this.roofPitch = roofPitch;
		this.moduleTils = moduleTils;
		this.eaveOverHang = eaveOverHang;
		this.eaveOverHangOther = eaveOverHangOther;
		this.modelvalue = modelvalue;
		this.moduleQty = moduleQty;
		this.squareFootage = squareFootage;
	}
	//For site survey
	public PermitSketchResults(Integer arraySketch, String azimuth, String roofPitch, Boolean moduleTils,
			String eaveOverHang, String eaveOverHangOther, String modelvalue, String moduleQty) {
		super();
		this.arraySketch = arraySketch;
		this.azimuth = azimuth;
		this.roofPitch = roofPitch;
		this.moduleTils = moduleTils;
		this.eaveOverHang = eaveOverHang;
		this.eaveOverHangOther = eaveOverHangOther;
		this.modelvalue = modelvalue;
		this.moduleQty = moduleQty;
	}
}
